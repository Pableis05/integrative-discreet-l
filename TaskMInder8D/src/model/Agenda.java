package model;

import dataStructures.HashTable;
import dataStructures.Heap;

import dataStructures.Node;
import dataStructures.Queue;
import exceptions.*;

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
                removeNonPriorityTask(task);
            }else{
                priorityTasks.delete(task);
            }
            return true;
        } catch (exceptionTheObjectDoesntExist e) {
            return false;
        } catch (exceptionThisDataStructureIsVoid e){
            return false;
        }
    }

    public boolean removeNonPriorityTask(Task task) throws exceptionThisDataStructureIsVoid {

        try {
            Queue<Task> tempQueue = new Queue<>();
            while (!nonPriorityTasks.isEmpty()) {
                Task elemento = nonPriorityTasks.poll();
                if (!elemento.equals(task)) {
                    tempQueue.offer(elemento);
                }
            }

            if (tempQueue.size() == nonPriorityTasks.size()) {
                return false;
            }

            int aux = tempQueue.size();
            for (int i = 0; i < aux; i++) {
                nonPriorityTasks.offer(tempQueue.poll());
            }
            return true;

        } catch (exceptionThisDataStructureIsVoid e){
            return false;
        }

    }

    public boolean modifyTask(Integer id, String title, String description, String date, Integer priority) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        try {
            Task task = searchTask(id);
            Task newTask = new Task(id, title, description, date, priority);
            tasks.modify(id, newTask);
            if (priority == 0) {
                nonPriorityTasks.offer(newTask);
                removeNonPriorityTask(task);
            }else{
                priorityTasks.delete(task);
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
        int auxCount = count;
        return "Agenda tasks\n" + tasks + "\nnonPriorityTasks=\n" + printNonPriorityTasks() + "\npriorityTasks=\n" + printPriorityTasks() + "\ncount=" + --auxCount;
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

    public String printPriorityTasks(){
        String msg = "";
        Heap<Task> clone = priorityTasks.clone();
        while(!clone.isEmpty()){
                msg += clone.extractMax().toString() + "\n";
        }
        return msg;
    }




}
