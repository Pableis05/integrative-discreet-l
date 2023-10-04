package ui;

import javax.swing.JOptionPane;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;
import model.ControllerAgenda;

public class Main {

    private ControllerAgenda controllerAgenda;

    public Main() {
        controllerAgenda = new ControllerAgenda();
    }

    public static void main(String[] args) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        Main m = new Main();
        m.menu();
    }

    public void menu() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        int option = 0;
        JOptionPane.showMessageDialog(null, "WELCOME TO YOUR TASK MANAGER");
        do {
            String[] options = {"Add a task", "Remove a task", "Modify a task", "Show all tasks", "Undo version", "Exit"};
            option = JOptionPane.showOptionDialog(null, "Select an option", "Menu", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            switch(option) {
                case 0:
                    addTask();
                    break;
                case 1:
                    removeTask();
                    break;
                case 2:
                    modifyTask();
                    break;
                case 3:
                    showTasks();
                    break;
                case 4:
                    controllerAgenda.undo();
                    JOptionPane.showMessageDialog(null, "Undo version, " + controllerAgenda.getAgenda().getTasks().getChangeMessage());
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Exit");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }
        } while(option != 5);
    }

    public void addTask() {
        String title = JOptionPane.showInputDialog("Enter the title of the task");
        String description = JOptionPane.showInputDialog("Enter the description of the task");
        String date = JOptionPane.showInputDialog("Enter the date of the task");
        int priority = Integer.parseInt(JOptionPane.showInputDialog("Enter the priority of the task"));
        controllerAgenda.addTask(title, description, date, priority);
    }

    public void removeTask() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the task"));
        controllerAgenda.removeTask(id);
    }

    public void modifyTask() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the task"));

        String[] options = {"Modify title", "Modify description", "Modify date", "Modify priority"};
        int option = JOptionPane.showOptionDialog(null, "Select an option", "Modify Task", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

        String title = controllerAgenda.searchTask(id).getTitle();
        String description = controllerAgenda.searchTask(id).getDescription();
        String date = controllerAgenda.searchTask(id).getDate();
        int priority = controllerAgenda.searchTask(id).getPriority();

        String newValue = JOptionPane.showInputDialog("Enter the new value");

        try {
            switch (option) {
                case 0:
                    controllerAgenda.modifyTask(id, newValue, description, date, priority);
                    break;
                case 1:
                    controllerAgenda.modifyTask(id, title, newValue, date, priority);
                    break;
                case 2:
                    controllerAgenda.modifyTask(id, title, description, newValue, priority);
                    break;
                case 3:
                    controllerAgenda.modifyTask(id, title, description, date, Integer.parseInt(newValue));
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void showTasks() {
        JOptionPane.showMessageDialog(null, controllerAgenda.showTasks());
    }
}

