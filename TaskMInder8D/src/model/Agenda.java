package model;

import dataStructures.HashTable;
import dataStructures.Heap;
import dataStructures.NodeHash;
import dataStructures.Queue;
import exceptions.*;
import java.util.List;

public class Agenda{

    private HashTable<Integer, Task> tasks;

    private Queue<Task> nonPriorityTasks;

    private Heap<Task> priorityTasks;
    private int count;


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

    public boolean modifyTask(Integer id, String title, String description, String date, Integer priority){
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

    public Task searchTask(Integer id){
        for (NodeHash<Integer, Task> node : tasks.getArr()) {
            if (node != null && node.getKey().equals(id)) {
                return node.getValue();
            }
        }
        return null;
    }

    public List<String> getTaskStrings(Integer id){
        return searchTask(id).getStrings();
    }

    public String showTasks() {
        String message = "";
        for (NodeHash<Integer, Task> node : tasks.getArr()) {
            if (node != null) {
                message += node.getValue().toString() + "\n";
            }
        }
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

    public NodeHash<Integer, Task>[] getArr() {
        return tasks.getArr();
    }

    public void setArr(NodeHash<Integer, Task>[] arr) {
        this.tasks.setArr(arr);
    }

}
