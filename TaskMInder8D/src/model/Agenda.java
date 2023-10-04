package model;

import dataStructures.HashTable;
import dataStructures.Heap;

import dataStructures.Node;
import dataStructures.Queue;
import exceptions.*;

public class Agenda implements Cloneable{

    /**tasks: is declaring a private instance variable named `tasks` of type `HashTable<Integer, Task>`. This variable is used to store a collection of `Task` objects, where each task is associated with a unique integer key.
     */ 
    private HashTable<Integer, Task> tasks;

    /**nonPriorityTasks: is declaring a private instance variable named `nonPriorityTasks` of type `Queue<Task>`. This variable is used to store a collection of `Task` objects that are considered non-priority tasks. The `Queue` data structure is used to maintain the order of the tasks, where the first task added is the first task to be processed.
 */
    private Queue<Task> nonPriorityTasks;

    /**priorityTasks: is declaring a private instance variable named `priorityTasks` of type `Heap<Task>`. This variable is used to store a collection of `Task` objects that are considered priority tasks. The `Heap` data structure is used to maintain the order of the tasks based on their priority, where the task with the highest priority is at the root of the heap.
 */
    private Heap<Task> priorityTasks;
    /**ount: is declaring a private instance variable named `count` of type `int`. This variable is used to keep track of the number of tasks in the agenda. It is incremented every time a new task is added to the agenda.
 */
    private int count;

    /**
     * The function sets the tasks hashtable for a given object.
     * 
     * @param tasks The "tasks" parameter is a HashTable data structure that stores Task objects. The keys in the HashTable are of type Integer, and the values are of type Task.
     */
    public void setTasks(HashTable<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * The function sets the non-priority tasks queue for a given object.
     * 
     * @param nonPriorityTasks The nonPriorityTasks parameter is a Queue of Task objects.
     */
    public void setNonPriorityTasks(Queue<Task> nonPriorityTasks) {
        this.nonPriorityTasks = nonPriorityTasks;
    }

  /**
   * The function sets the priorityTasks heap for a given object.
   * 
   * @param priorityTasks The parameter "priorityTasks" is of type Heap<Task>. It is used to set the value of the instance variable "priorityTasks" in the current object.
   */
    public void setPriorityTasks(Heap<Task> priorityTasks) {
        this.priorityTasks = priorityTasks;
    }

    public Agenda(){
        tasks = new HashTable<Integer, Task>(100);
        nonPriorityTasks = new Queue<Task>();
        priorityTasks = new Heap<Task>();
        count = 1;
    }

/**
 * The addTask function adds a new task to a list of tasks, with a given title, description, date, and priority.
 * 
 * @param title The title of the task.
 * @param description The description parameter is a String that represents the description of the task. It provides additional details or information about the task.
 * @param date The "date" parameter is a string that represents the date of the task.
 * @param priority The priority parameter is an Integer value representing the priority level of the task. A higher priority value indicates a higher priority task.
 * @return The method is returning a boolean value. It returns true if the task was successfully added, and false if there was an exception object already existing with the same key.
 */
    public boolean addTask(String title, String description, String date, Integer priority){

        Task task = new Task(count, description, title, date, priority);

        try {
            tasks.add(count, task);
            if (priority == 0) {
                nonPriorityTasks.offer(task);
            }else{
                priorityTasks.insert(priority, task);
            }
            count++;
            return true;
        } catch (exceptionObjectAlredyExistWithThatKey e) {
            return false;
        }
    }

/**
 * The removeTask function removes a task from a list of tasks based on its ID and updates the appropriate data structure.
 * 
 * @param id The id parameter is an Integer that represents the unique identifier of the task that needs to be removed.
 * @return The method is returning a boolean value. It returns true if the task with the given id was successfully removed, and false if the task does not exist or if the data structure is empty.
 */


    public boolean removeTask(Integer id){
        try {
            Task task = searchTask(id);
            tasks.remove(id);
            if (task.getPriority() == 0) {
                removeNonPriorityTask(task);
            }else{
                removePriorityTask(task);
            }
            return true;
        } catch (exceptionTheObjectDoesntExist e) {
            return false;
        } catch (exceptionThisDataStructureIsVoid e){
            return false;
        }
    }


    public boolean removePriorityTask(Task task) {
        Heap<Task> tempHeap = new Heap<>();
        int sizePriority = priorityTasks.size();
        try {
            while (!priorityTasks.isEmpty()) {
                Task element = null;

                element = priorityTasks.extractMax();

                if (!element.equals(task)) {
                    tempHeap.insert(element.getPriority(), element);
                }
            }
        }catch (exceptionThisDataStructureIsVoid e){
            return false;
        }

        if (tempHeap.size() == sizePriority)
            return false;


        while (!tempHeap.isEmpty()) {

            Task element = null;
            try {
                element = tempHeap.extractMax();
                priorityTasks.insert(element.getPriority(), element);

            } catch (exceptionThisDataStructureIsVoid e) {
                throw new RuntimeException(e);
            }
        }
        return true;

    }

    public boolean removeNonPriorityTask(Task task) throws exceptionThisDataStructureIsVoid {

        try {
            Queue<Task> tempQueue = new Queue<>();
            while (!nonPriorityTasks.isEmpty()) {
                Task element = nonPriorityTasks.poll();
                if (!element.equals(task)) {
                    tempQueue.offer(element);
                }
            }

            if (tempQueue.size() == nonPriorityTasks.size()) {
                return false;
            }

            int auxSize = tempQueue.size();
            for (int i = 0; i < auxSize; i++) {
                nonPriorityTasks.offer(tempQueue.poll());
            }
            return true;

        } catch (exceptionThisDataStructureIsVoid e){
            return false;
        }

    }

    /**
     * This function modifies a task by updating its title, description, date, and priority, and then reorganizes the task in the appropriate data structure based on its priority.
     *
     * @param id The unique identifier of the task that needs to be modified.
     * @param title The title of the task.
     * @param description The description parameter is a String that represents the new description for the task.
     * @param date The "date" parameter is a string representing the date of the task.
     * @param priority The priority parameter is an Integer that represents the priority level of the task. A priority of 0 indicates that the task is a non-priority task, while any other positive integer represents the priority level of the task.
     * @return The method is returning a boolean value. If the task with the given id is found and successfully modified, it will return true. If the task with the given id does not exist, it will return false.
     */
    public boolean modifyTask(Integer id, String title, String description, String date, Integer priority) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        try {
            Task task = searchTask(id);
            Task newTask = new Task(id, title, description, date, priority);
            tasks.modify(id, newTask);

            if (priority == 0) {
                modifyTaskNonPriorityTask(task, newTask);
            }else{
                modifyTaskPriorityTask(task, newTask);
            }
            return true;
        } catch (exceptionTheObjectDoesntExist e) {
            return false;
        }
    }

    private boolean modifyTaskPriorityTask(Task task, Task newTask) throws exceptionThisDataStructureIsVoid {
        Heap<Task> tempHeap = new Heap<>();
        int sizePriority = priorityTasks.size();
        while (!priorityTasks.isEmpty()) {
            Task element = priorityTasks.extractMax();
            if (!element.equals(task)) {
                tempHeap.insert(element.getPriority(), element);
            } else {
                tempHeap.insert(newTask.getPriority(), newTask);
            }
        }

        while (!tempHeap.isEmpty()) {
            Task element = tempHeap.extractMax();
            priorityTasks.insert(element.getPriority(), element);
        }
        return true;

    }

    private boolean modifyTaskNonPriorityTask(Task task, Task newTask) throws exceptionThisDataStructureIsVoid{
        try {
            Queue<Task> tempQueue = new Queue<>();
            while (!nonPriorityTasks.isEmpty()) {
                Task element = nonPriorityTasks.poll();
                if (!element.equals(task)) {
                    tempQueue.offer(element);
                }else {
                    tempQueue.offer(newTask);
                }
            }

            int auxSize = tempQueue.size();
            for (int i = 0; i < auxSize; i++) {
                nonPriorityTasks.offer(tempQueue.poll());
            }
            return true;

        } catch (exceptionThisDataStructureIsVoid e){
            return false;
        }
    }

    /**
     * The function searches for a task with a given ID and returns it.
     *
     * @param id The id parameter is an Integer that represents the unique identifier of the task that you want to search for.
     * @return The method is returning a Task object.
     */
    public Task searchTask(Integer id) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        Task task=tasks.search(id);
        return task;
    }

    /**
     * The function returns a string representation of a list of tasks.
     * 
     * @return The method is returning a string representation of the tasks.
     */
    public String showTasks() {
        String message = tasks.toString();
        return message;
    }

   /**
    * The function returns a HashTable containing tasks, with Integer keys and Task values.
    * 
    * @return A HashTable object with Integer keys and Task values is being returned.
    */
    public HashTable<Integer, Task> getTasks() {
        return tasks;
    }

    /**
     * The function returns the value of the count variable.
     * 
     * @return The method is returning the value of the variable "count".
     */
    public int getCount() {
        return count;
    }

    public void setCounter(int counter) {
    // The code snippet you provided is a setter method for the `count` variable in the `Agenda` class. It sets the value of `count` to the value passed as a parameter (`counter`).
        this.count = counter;
    }

    /**
     * The toString() function returns a string representation of the Agenda object, including the tasks, non-priority tasks, priority tasks, and the count.
     * 
     * @return The toString() method is returning a string representation of the object. The returned string includes the tasks, non-priority tasks, priority tasks, and the count of tasks.
     */
    @Override
    public String toString() {
        int auxCount = count;
        return "\nnonPriorityTasks=\n" + printNonPriorityTasks() + "\npriorityTasks=\n" + printPriorityTasks();
    }

    /**
     * The function clone() creates a deep copy of an Agenda object.
     * 
     * @return The method is returning a clone of the Agenda object.
     */
    public Agenda clone(){
        try {
            Agenda clone = (Agenda) super.clone();
            clone.setCounter(this.count);
            clone.setTasks(this.tasks.clone());
            clone.setNonPriorityTasks(this.nonPriorityTasks.clone());
            clone.setPriorityTasks(this.priorityTasks.clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The function prints out the non-priority tasks in a queue.
     * 
     * @return The method is returning a string representation of all the non-priority tasks in the queue.
     */
    public String printNonPriorityTasks(){
        String msg = "";
        Queue<Task> clone = nonPriorityTasks.clone();
        while(!clone.isEmpty()){
            try {
                msg += clone.poll().toString() + "\n";
            } catch (exceptionThisDataStructureIsVoid e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * The function "printPriorityTasks" returns a string representation of the highest priority tasks in a heap.
     * 
     * @return The method is returning a string that contains the string representation of each task in the priorityTasks heap, separated by new lines.
     */
    public String printPriorityTasks(){
        String msg = "";
        Heap<Task> clone = priorityTasks.clone();

        while(!clone.isEmpty()){
            try {
                msg += clone.extractMax().toString() + "\n";
            }catch (exceptionThisDataStructureIsVoid e){
                msg="The priority tasks is empty";
            }
        }
        return msg;
    }




}
