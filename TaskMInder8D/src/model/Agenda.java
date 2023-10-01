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
        List<String> strings = List.of(title, description, date, priority+"");
        Task task = new Task(count, strings);

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
            if (task.getStrings().get(3).equals("0")) {

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
            List<String> strings = List.of(title, description, date, priority+"");
            Task newTask = new Task(id, strings);
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


    public List<String> getTaskStrings(Integer id) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        return searchTask(id).getStrings();
    }

    public String showTasks() {
        String message = "";
        message=tasks.toString();
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
        return "Agenda{" + "tasks=" + tasks + ", nonPriorityTasks=" + nonPriorityTasks + ", priorityTasks=" + priorityTasks + ", count=" + count + '}';
    }

    public Agenda clone(){
        Agenda clone = new Agenda();
        clone.setCounter(count);
        clone.setTasks(tasks.clone());
        clone.setNonPriorityTasks(nonPriorityTasks.clone());
        clone.setPriorityTasks(priorityTasks.clone()); ;
        return clone;
    }


}
