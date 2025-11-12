package manager;

import model.Priority;
import model.Status;
import model.Task;
import utils.JsonHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;
    private final JsonHandler jsonHandler;

    // Private constructor for Singleton pattern
    private TaskManager() {
        this.jsonHandler = new JsonHandler("tasks.json");
        this.tasks = jsonHandler.loadTasks();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

 
    public void addTask(Task task) {
        tasks.add(task);
        save();
    }

    public boolean removeTask(String id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        if (removed) {
            save();
        }
        return removed;
    }

    public boolean updateTask(Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(updatedTask.getId())) {
                tasks.set(i, updatedTask);
                save();
                return true;
            }
        }
        return false;
    }

   
    public boolean moveTask(String id, Status newStatus) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setStatus(newStatus);
                save();
                return true;
            }
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

   
    public Task getTaskById(String id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

   
    public List<Task> getTasksByStatus(Status status) {
        return tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

   
    public List<Task> sortByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing((Task t) -> {
                    switch (t.getPriority()) {
                        case HIGH: return 0;
                        case MEDIUM: return 1;
                        case LOW: return 2;
                        default: return 3;
                    }
                }))
                .collect(Collectors.toList());
    }

   
    public List<Task> sortByDeadline() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    
    private void save() {
        jsonHandler.saveTasks(tasks);
    }
}

