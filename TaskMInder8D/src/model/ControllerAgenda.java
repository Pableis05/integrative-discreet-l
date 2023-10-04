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
        undoStack.push(agenda);

    }

    public void addTask(String title, String description, String date, Integer priority){
        agenda.addTask(title, description, date, priority);
        saveState("Add new task: " + title);
    }

    public void removeTask(Integer id) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        saveState("remove task by title: " + searchTask(id).getTitle());
        agenda.removeTask(id);
    }


    public void modifyTask(Integer id, String title, String description, String date, Integer priority) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        agenda.modifyTask(id, title, description, date, priority);
        saveState("modify task by title: " + title);
    }

    public Task searchTask(Integer id)  throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {
        return agenda.searchTask(id);
    }

    public String showTasks() {
        String msg = "";
        msg += agenda + "\n";
        return msg;
    }

    private void saveState(String changeMessage) {
        Agenda copyOfAgenda = agenda.clone();
        copyOfAgenda.setCounter(agenda.getCount());
        copyOfAgenda.setChangeMessage(changeMessage);
        undoStack.push(copyOfAgenda);
    }

    public void undo() {
        if (undoStack.size() > 1){
            undoStack.pop();
            agenda = undoStack.peek();
        }
    }
    public Agenda getAgenda() {
        return agenda;
    }

}