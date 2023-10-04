package model;
public class Task implements Cloneable{

    /**id; is declaring a private integer variable named `id` in the `Task` class. This variable is used to store the unique identifier of a task object.
 */
    private int id;

    /**title: is declaring a private String variable named `title` in the `Task` class. This variable is used to store the title of a task object.
 */
    private String title;

    /**description: is declaring a private String variable named `description` in the `Task` class. This variable is used to store the description of a task object. It allows us to store and retrieve the description of a task using getter and setter methods.
 */
    private String description;
    
    private String date;
    /**priority: is declaring a private integer variable named `priority` in the `Task` class. This variable is used to store the priority of a task object. It allows us to store and retrieve the priority of a task using getter and setter methods.
 */
    private int priority;

    public Task(int id, String title, String description, String date, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    /**
     * The function returns the value of the id variable.
     * 
     * @return The method is returning the value of the variable "id".
     */
    public int getId() {
        return id;
    }
    
    /**
     * The getTitle() function returns the title of an object.
     * 
     * @return The method is returning the value of the variable "title".
     */
    public String getTitle() {
        return title;
    }

    /**
     * The getDescription() function returns the description of an object.
     * 
     * @return The method is returning a String value.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The function returns the date as a string.
     * 
     * @return The method is returning a String value.
     */
    public String getDate() {
        return date;
    }

    /**
     * The function returns the priority value.
     * 
     * @return The method is returning the value of the variable "priority".
     */
    public int getPriority() {
        return priority;
    }

    /**
     * The toString() function returns a string representation of a Task object.
     * 
     * @return The toString() method is returning a string representation of a Task object. The string includes the values of the id, title, description, date, and priority properties of the Task object.
     */
    public String toString() {
        return "Task [ " + "ID: " + id + "/ Title: " + title + "/ Description: " + description + "/ Date: " + date + "/ Priority: " + priority + " ]";
    }

}
