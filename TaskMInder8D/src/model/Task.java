package model;
import java.util.List;
public class Task implements Cloneable{

    private int id;
    private String title, description, date;
    private int priority;

    public Task(int id, String title, String description, String date, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return "Task{" + "id=" + id + ", Title=" + title + ", description=" + description + ", date=" + date + ", priority=" + priority + '}';
    }

}
