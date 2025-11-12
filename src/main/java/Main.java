import manager.TaskManager;
import model.Priority;
import model.Status;
import model.Task;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Main CLI interface for the Task Management application.
 */
public class Main {
    private static TaskManager taskManager = TaskManager.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Welcome to Task Manager CLI");
        System.out.println("========================================\n");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewAllTasks();
                    break;
                case 3:
                    viewTasksByStatus();
                    break;
                case 4:
                    editTask();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    moveTask();
                    break;
                case 7:
                    sortTasks();
                    break;
                case 8:
                    System.out.println("\nThank you for using Task Manager! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. View Tasks by Status");
        System.out.println("4. Edit Task");
        System.out.println("5. Delete Task");
        System.out.println("6. Move Task (Change Status)");
        System.out.println("7. Sort Tasks");
        System.out.println("8. Exit");
        System.out.println();
    }

    private static void addTask() {
        System.out.println("\n--- Add New Task ---");
        String title = getStringInput("Enter task title: ");
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        
        String description = getStringInput("Enter task description: ");
        if (description == null) {
            description = "";
        }
        
        Priority priority = getPriorityInput();
        String deadline = getStringInput("Enter deadline (yyyy-MM-dd): ");
        if (deadline == null) {
            deadline = "";
        }

        String id = UUID.randomUUID().toString();
        Task task = new Task(id, title, description, priority, deadline, Status.TODO);
        taskManager.addTask(task);
        System.out.println("\nTask added successfully!");
        System.out.println("Task ID: " + task.getId());
    }

    private static void viewAllTasks() {
        System.out.println("\n--- All Tasks ---");
        List<Task> tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            displayTasks(tasks);
        }
    }

    private static void viewTasksByStatus() {
        System.out.println("\n--- View Tasks by Status ---");
        Status status = getStatusInput();
        List<Task> tasks = taskManager.getTasksByStatus(status);
        
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks found with status: " + status);
        } else {
            System.out.println("\nTasks with status: " + status);
            displayTasks(tasks);
        }
    }

    private static void editTask() {
        System.out.println("\n--- Edit Task ---");
        String id = getStringInput("Enter task ID: ");
        Task task = taskManager.getTaskById(id);

        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        System.out.println("\nCurrent task details:");
        System.out.println(task);

        System.out.println("\nEnter new details (press Enter to keep current value):");
        String newTitle = getStringInput("New title [" + task.getTitle() + "]: ");
        if (!newTitle.isEmpty()) {
            task.setTitle(newTitle);
        }

        String newDescription = getStringInput("New description [" + task.getDescription() + "]: ");
        if (!newDescription.isEmpty()) {
            task.setDescription(newDescription);
        }

        System.out.println("New priority (1-LOW, 2-MEDIUM, 3-HIGH) [" + task.getPriority() + "]: ");
        String priorityInput = scanner.nextLine();
        if (!priorityInput.isEmpty()) {
            try {
                int priorityChoice = Integer.parseInt(priorityInput);
                Priority newPriority = getPriorityFromChoice(priorityChoice);
                if (newPriority != null) {
                    task.setPriority(newPriority);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid priority, keeping current value.");
            }
        }

        String currentDeadline = task.getDeadline();
        String deadlineDisplay = (currentDeadline != null) ? currentDeadline : "";
        String newDeadline = getStringInput("New deadline (yyyy-MM-dd) [" + deadlineDisplay + "]: ");
        if (!newDeadline.isEmpty()) {
            task.setDeadline(newDeadline);
        }

        taskManager.updateTask(task);
        System.out.println("\n✓ Task updated successfully!");
    }

    private static void deleteTask() {
        System.out.println("\n--- Delete Task ---");
        String id = getStringInput("Enter task ID to delete: ");
        
        if (taskManager.removeTask(id)) {
            System.out.println("\n✓ Task deleted successfully!");
        } else {
            System.out.println("\n✗ Task not found!");
        }
    }

    private static void moveTask() {
        System.out.println("\n--- Move Task ---");
        String id = getStringInput("Enter task ID: ");
        Task task = taskManager.getTaskById(id);

        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        System.out.println("\nCurrent status: " + task.getStatus());
        Status newStatus = getStatusInput();

        if (taskManager.moveTask(id, newStatus)) {
            System.out.println("\n✓ Task moved to " + newStatus + " successfully!");
        } else {
            System.out.println("\n✗ Failed to move task!");
        }
    }

    private static void sortTasks() {
        System.out.println("\n--- Sort Tasks ---");
        System.out.println("1. Sort by Priority");
        System.out.println("2. Sort by Deadline");
        int choice = getIntInput("Enter your choice: ");

        List<Task> sortedTasks;
        switch (choice) {
            case 1:
                sortedTasks = taskManager.sortByPriority();
                System.out.println("\n--- Tasks Sorted by Priority ---");
                break;
            case 2:
                sortedTasks = taskManager.sortByDeadline();
                System.out.println("\n--- Tasks Sorted by Deadline ---");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (sortedTasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            displayTasks(sortedTasks);
        }
    }

    private static void displayTasks(List<Task> tasks) {
        String separator = "============================================================";
        System.out.println("\n" + separator);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            System.out.println(tasks.get(i));
            System.out.println("------------------------------------------------------------");
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static Priority getPriorityInput() {
        while (true) {
            System.out.println("Select priority:");
            System.out.println("1. LOW");
            System.out.println("2. MEDIUM");
            System.out.println("3. HIGH");
            int choice = getIntInput("Enter choice (1-3): ");
            Priority priority = getPriorityFromChoice(choice);
            if (priority != null) {
                return priority;
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static Priority getPriorityFromChoice(int choice) {
        switch (choice) {
            case 1: return Priority.LOW;
            case 2: return Priority.MEDIUM;
            case 3: return Priority.HIGH;
            default: return null;
        }
    }

    private static Status getStatusInput() {
        while (true) {
            System.out.println("Select status:");
            System.out.println("1. TODO");
            System.out.println("2. IN_PROGRESS");
            System.out.println("3. DONE");
            int choice = getIntInput("Enter choice (1-3): ");
            Status status = getStatusFromChoice(choice);
            if (status != null) {
                return status;
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static Status getStatusFromChoice(int choice) {
        switch (choice) {
            case 1: return Status.TODO;
            case 2: return Status.IN_PROGRESS;
            case 3: return Status.DONE;
            default: return null;
        }
    }

}

