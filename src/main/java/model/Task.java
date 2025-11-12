package model;

public class Task {
    private String id;
    private String title;
    private String description;
    private Priority priority;
    private String deadline;
    private Status status;

    public Task() {
    }

    public Task(String id, String title, String description, Priority priority, String deadline, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        String result = "Task [ID: " + id + "]\n";
        result += "  Title: " + title + "\n";
        result += "  Description: " + description + "\n";
        result += "  Priority: " + priority + "\n";
        result += "  Deadline: " + deadline + "\n";
        result += "  Status: " + status + "\n";
        return result;
    }
}

