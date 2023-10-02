package ui;

import java.util.Scanner;

import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;
import model.ControllerAgenda;
public class Main {

    private Scanner sc;
    private ControllerAgenda controllerAgenda;

    public Main() {
        sc = new Scanner(System.in);
        controllerAgenda = new ControllerAgenda();
    }

    public static void main(String[] args)  throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        Main m = new Main();
        m.menu();
    }

    public void menu() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        int option = 0;
        do {
            System.out.println("1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. modify a task");
            System.out.println("4. Show all tasks");
            System.out.println("5. Undo version");
            System.out.println("0 Exit");
            option = sc.nextInt();
            sc.nextLine();
            switch(option) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    modifyTask();
                    break;
                case 4:
                    showTasks();
                    break;
                case 5:
                    controllerAgenda.undo();
                    System.out.println("Undo version, " + controllerAgenda.getAgenda().getTasks().getChangeMessage());
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while(option != 0);
    }

    public void addTask() {
        System.out.println("Enter the title of the task");
        String title = sc.nextLine();
        System.out.println("Enter the description of the task");
        String description = sc.nextLine();
        System.out.println("Enter the date of the task");
        String date = sc.nextLine();
        System.out.println("Enter the priority of the task");
        int priority = sc.nextInt();
        sc.nextLine();
        controllerAgenda.addTask(title, description, date, priority);
    }

    public void removeTask() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{

        System.out.println("Enter the id of the task");
        int id = sc.nextInt();
        sc.nextLine();
        controllerAgenda.removeTask(id);

    }

    public void modifyTask() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        System.out.println("Enter the id of the task");
        int id = sc.nextInt();
        sc.nextLine();
        int option = -1;
        String title = controllerAgenda.searchTask(id).getTitle();
        String description = controllerAgenda.searchTask(id).getDescription();
        String date = controllerAgenda.searchTask(id).getDate();
        int priority = controllerAgenda.searchTask(id).getPriority();

        do{
            System.out.println("1. Modify title, 2. Modify description, 3. Modify date, 4. Modify priority, 0. Exit");
            option = sc.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the new title of the task");
                    sc.nextLine();
                    title = sc.nextLine();
                    break;
                case 2:
                    System.out.println("Enter the new description of the task");
                    sc.nextLine();
                    description = sc.nextLine();
                    break;
                case 3:
                    System.out.println("Enter the new date of the task");
                    sc.nextLine();
                    date = sc.nextLine();

                    break;
                case 4:
                    System.out.println("Enter the new priority of the task");

                    priority = sc.nextInt();
                    sc.nextLine();
                    break;
            }

        }while (option != 0);
        try{
            controllerAgenda.modifyTask(id, title, description, date, priority);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public void showTasks() {
        System.out.println(controllerAgenda.showTasks());
    }



}

