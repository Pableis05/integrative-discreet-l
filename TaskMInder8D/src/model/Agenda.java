package model;

import dataStructures.HashTable;
import dataStructures.Heap;

import dataStructures.Queue;
import exceptions.*;
import java.util.List;

public class Agenda implements Cloneable{

    private HashTable<Integer, Task> tasks;

    private Queue<Task> nonPriorityTasks;

    private Heap<Task> priorityTasks;
    private int count;

    public void setTasks(HashTable<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public void setNonPriorityTasks(Queue<Task> nonPriorityTasks) {
        this.nonPriorityTasks = nonPriorityTasks;
    }

    public void setPriorityTasks(Heap<Task> priorityTasks) {
        this.priorityTasks = priorityTasks;
    }

    public Agenda(){
        tasks = new HashTable<Integer, Task>(100);
        nonPriorityTasks = new Queue<Task>();
        priorityTasks = new Heap<Task>();
        count = 1;
    }

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

    public boolean removeTask(Integer id){
        try {
            Task task = searchTask(id);
            tasks.remove(id);
            if (task.getPriority() == 0) {

                //nonPriorityTasks.remove(task);
            }else{
                //priorityTasks.remove(task);
            }
            return true;
        } catch (exceptionTheObjectDoesntExist e) {
            return false;
        } catch (exceptionThisDataStructureIsVoid e){
            return false;
        }
    }

    public boolean modifyTask(Integer id, String title, String description, String date, Integer priority) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        try {
            Task task = searchTask(id);
            Task newTask = new Task(id, description, title, date, priority);
            tasks.modify(id, newTask);
            if (priority == 0) {
                nonPriorityTasks.offer(newTask);
            }else{
                priorityTasks.insert(priority, newTask);
            }
            return true;
        } catch (exceptionTheObjectDoesntExist e) {
            return false;
        }
    }

    public Task searchTask(Integer id) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        Task task=tasks.search(id);
        return task;
    }

    public String showTasks() {
        String message = tasks.toString();
        return message;
    }

    public HashTable<Integer, Task> getTasks() {
        return tasks;
    }

    public int getCount() {
        return count;
    }

    public void setCounter(int counter) {
        this.count = counter;
    }

    @Override
    public String toString() {
        return "Agenda tasks\n" + tasks + "\nnonPriorityTasks=\n" + nonPriorityTasks + "\npriorityTasks=\n" + priorityTasks + "\ncount=" + --count ;
    }

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

    public String nonPriorityTasksToString(){
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

    public String priorityTasksToString(){
        return priorityTasks.toString();
    }


}
