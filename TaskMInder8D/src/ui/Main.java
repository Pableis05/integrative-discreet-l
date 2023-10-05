package ui;

import javax.swing.JOptionPane;
import exceptions.*;
import model.ControllerAgenda;
import model.Task;

public class Main {

    private ControllerAgenda controllerAgenda;

    public Main() {
        controllerAgenda = new ControllerAgenda();
    }

    public static void main(String[] args) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        Main m = new Main();
        m.menu();
    }

    public void menu() {
        int option = -1;
        JOptionPane.showMessageDialog(null, "WELCOME TO YOUR TASK MANAGER");

        do {
            String[] options = {"Add a task", "Remove a task", "Modify a task", "Show all tasks", "Undo version", "Exit"};
            option = JOptionPane.showOptionDialog(null, "Select an option", "Menu", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if (option == -1) {
                return;
            }

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
                    JOptionPane.showMessageDialog(null, controllerAgenda.undo());
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
        String title;
        do {
            title = JOptionPane.showInputDialog("Enter the title of the task");

            if (title == null ) {
                return;
            }
            if(title.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Error: Title cannot be empty");
            }
        } while (title == null || title.trim().isEmpty());


        String description;
        do {
            description = JOptionPane.showInputDialog("Enter the description of the task");
            if(description == null)
                return;
            if (description.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: description cannot be empty");
            }

        } while (description == null || description.trim().isEmpty());


        String date = "";
        boolean validDate;
        try{
            date = JOptionPane.showInputDialog("Enter the date of the task (DD-MM-YYYY)");
            if(date == null)
                return;
            validDate = validateDate(date);
            if (!validDate) {
                throw new exceptionNotDateFormat(date);
            }

            String priority = (JOptionPane.showInputDialog("Enter the priority of the task \n 0. No priority \n 1. Low \n 2. Medium \n 3. Urgent"));
            if(priority == null)
                return;
            if (!priority.matches("[0-3]+") ) {
                throw new exceptionNotIntegerFormat(priority+ "");
            }
            controllerAgenda.addTask(title, description, date, Integer.parseInt(priority));
        }catch (exceptionNotDateFormat e ){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }catch (exceptionNotIntegerFormat e ) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public boolean validateDate(String date) {
        String regex = "\\d{2}-\\d{2}-\\d{4}";
        return date.matches(regex);
    }

    public void removeTask(){

        try {
            String id;
            do {
                id = JOptionPane.showInputDialog(controllerAgenda.showTasks()+"\nEnter the id of the task");
                if (id == null) {
                    return;
                }
                if (!id.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Error: " + id + " is not a valid integer");
                }
            } while (!id.matches("\\d+"));
            controllerAgenda.removeTask(Integer.parseInt(id));
        } catch (exceptionTheObjectDoesntExist e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (exceptionThisDataStructureIsVoid e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void modifyTask()  {
        try {
            String idString;
            do {
                idString = JOptionPane.showInputDialog(controllerAgenda.showTasks()+"\nEnter the id of the task");
                if (idString == null) {
                    return;
                }
                if (!idString.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Error: " + idString + " is not a valid integer");
                }
            } while (!idString.matches("\\d+"));
            int id = Integer.parseInt(idString);
            Task task = controllerAgenda.searchTask(Integer.parseInt(idString));
            if(controllerAgenda.getAgenda().getTasks().isEmpty()){
                throw new exceptionThisDataStructureIsVoid();
            }
            if (task == null) {
                throw new exceptionTheObjectDoesntExist(id + "");
            }

            String[] options = {"Modify title", "Modify description", "Modify date", "Modify priority"};
            int option = JOptionPane.showOptionDialog(null, "Select an option", "Modify Task", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            String title = task.getTitle();
            String description = task.getDescription();
            String date = task.getDate();
            int priority = task.getPriority();

            String newValue = "";
            do {
                if (option == 2) {
                    newValue = JOptionPane.showInputDialog("Enter the date of the task (DD-MM-YYYY)");

                } else if (option == 3) {
                    newValue = JOptionPane.showInputDialog("Enter the priority of the task \n 0. No priority \n 1. Low \n 2. Medium \n 3. Urgent");
                } else {
                    newValue = JOptionPane.showInputDialog("Enter the new value of the task");
                }

                if (newValue == null) {
                    return;
                }

                if (newValue.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: the new value cannot be empty");
                }
            } while (newValue == null || newValue.trim().isEmpty());

            switch (option) {
                case 0:
                    controllerAgenda.modifyTask(id, newValue, description, date, priority);
                    break;
                case 1:
                    controllerAgenda.modifyTask(id, title, newValue, date, priority);
                    break;
                case 2:
                    try {
                        if (!validateDate(newValue)) {
                            throw new exceptionNotDateFormat(newValue);
                        }
                        controllerAgenda.modifyTask(id, title, description, newValue, priority);
                    } catch (exceptionNotDateFormat e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        if (!newValue.matches("[0-3]+")) {
                            throw new exceptionNotIntegerFormat(newValue + "");
                        }
                        controllerAgenda.modifyTask(id, title, description, date, Integer.parseInt(newValue));
                    } catch (exceptionNotIntegerFormat e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }

        } catch (exceptionTheObjectDoesntExist e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (exceptionThisDataStructureIsVoid e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void showTasks() {
        JOptionPane.showMessageDialog(null, controllerAgenda.showTasks());
    }
}

