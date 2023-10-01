package model;
import java.util.List;
public class Task   {

    private int id;

    private List<String> strings;
    public Task(int id, List<String> strings) {
        this.id = id;
        this.strings = strings;
    }

    public int getId() {
        return id;
    }
    
    public List<String> getStrings() {
        return strings;
    }

    public String toString() {
        return "Task{" + "id=" + id + ", strings=" + strings + '}';
    }


}
