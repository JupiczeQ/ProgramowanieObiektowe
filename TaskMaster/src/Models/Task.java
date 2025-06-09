package Models;

import java.sql.Timestamp;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private Category category;
    private int userID;
    private Timestamp createdAt;

    public Task(){
        this.priority = TaskPriority.MEDIUM;
        this.status = TaskStatus.TODO;
    }

    public Task(int id, String title, String description, TaskPriority priority, TaskStatus status, Category category, int userID, Timestamp createdAt){
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.userID = userID;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
