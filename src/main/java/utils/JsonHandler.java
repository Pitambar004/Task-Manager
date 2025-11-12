package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    private String filePath;
    private Gson gson;

    public JsonHandler(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Task> loadTasks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileReader reader = new FileReader(filePath);
            Task[] taskArray = gson.fromJson(reader, Task[].class);
            reader.close();

            List<Task> tasks = new ArrayList<>();
            if (taskArray != null) {
                for (int i = 0; i < taskArray.length; i++) {
                    tasks.add(taskArray[i]);
                }
            }
            return tasks;
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            gson.toJson(tasks, writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

