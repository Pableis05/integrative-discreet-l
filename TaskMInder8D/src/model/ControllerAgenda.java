package model;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;
import java.util.Stack;

public class ControllerAgenda {

    private Agenda agenda;
    private  Stack<Agenda> undoStack;

    public ControllerAgenda(){
        agenda = new Agenda();
        undoStack = new Stack<>();
        saveState("Basic initial version");
    }

    /**
     * The addTask function adds a new task to the agenda with the given title, description, date, and priority, and saves the state of the agenda.
     * 
     * @param title The title of the task that you want to add. It is a string value.
     * @param description The description parameter is a String that represents the description of the task. It provides additional information or details about the task.
     * @param date The "date" parameter is a string that represents the date of the task. It could be in any format that is suitable for your application, such as "yyyy-MM-dd" or "MM/dd/yyyy".
     * @param priority The priority parameter is an Integer that represents the priority level of the task. It is used to determine the importance or urgency of the task.
     */
    public void addTask(String title, String description, String date, Integer priority){
        agenda.addTask(title, description, date, priority);
        saveState("Add new task: " + title);
    }

    /**
     * The function removes a task from the agenda based on the given option and returns a message indicating the task that was removed.
     * 
     * @param option The "option" parameter is an Integer that represents the type of task to be removed. If the value is 1, it indicates a priority task, and if the value is 2, it indicates a non-priority task.
     * @return The method is returning a String value.
     */
    public String removeTask(Integer option) throws exceptionThisDataStructureIsVoid{
        String task = "";
        if(option == 1 && agenda.getPriorityTasks().isEmpty()){
            throw new exceptionThisDataStructureIsVoid();
        }else if(option == 2 && agenda.getNonPriorityTasks().isEmpty()){
            throw new exceptionThisDataStructureIsVoid();
        }else {
            task = "The task "+agenda.removeTask(option)+" was removed";
            saveState(task);
        }
        return task;
    }

    /**
     * The function modifies a task in an agenda by updating its title, description, date, and priority, and saves the state of the modification.
     * 
     * @param id The unique identifier of the task that needs to be modified.
     * @param title The title of the task to be modified.
     * @param description The "description" parameter is a String that represents the updated description of the task.
     * @param date The "date" parameter is a string that represents the date of the task. It could be in any format that is suitable for your application, such as "yyyy-MM-dd" or "MM/dd/yyyy".
     * @param priority The priority parameter is an Integer that represents the priority level of the task. It is used to indicate the importance or urgency of the task.
     */
    public void modifyTask(Integer id, String title, String description, String date, Integer priority) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        agenda.modifyTask(id, title, description, date, priority);
        saveState("modify task by title: " + title);
    }

    /**
     * The function searches for a task with a given ID in an agenda and returns it.
     * 
     * @param id The id parameter is an Integer that represents the unique identifier of the task that you want to search for.
     * @return The method is returning a Task object.
     */
    public Task searchTask(Integer id)  throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        return agenda.searchTask(id);
    }

    /**
     * The function "showTasks" returns a string representation of the agenda.
     * 
     * @return The method is returning a string that contains the value of the variable "agenda" followed by a new line character.
     */
    public String showTasks() {
        String msg = "";
        msg += agenda + "\n";
        return msg;
    }

    /**
     * The saveState function creates a copy of the agenda, sets the counter and change message, and pushes it onto the undo stack.
     * 
     * @param changeMessage The changeMessage parameter is a string that represents the message or description of the change that is being made to the agenda.
     */
    private void saveState(String changeMessage) {
        Agenda copyOfAgenda = agenda.clone();
        copyOfAgenda.setCounter(agenda.getCount());
        copyOfAgenda.setChangeMessage(changeMessage);
        undoStack.push(copyOfAgenda);
    }

    /**
     * The function "undo" pops the top element from the undoStack and sets the agenda to the new top element, returning a message indicating the change that was undone.
     * 
     * @return The method is returning a String message.
     */
    public String undo() {
        String msg = "";
        if (undoStack.size() > 1){
            undoStack.pop();
            agenda = undoStack.peek();
            msg = "Undo version: " + agenda.getChangeMessage();
        }else
            msg = "There is no more versions to undo";

        return msg;
    }
    public Agenda getAgenda() {
        return agenda;
    }

}