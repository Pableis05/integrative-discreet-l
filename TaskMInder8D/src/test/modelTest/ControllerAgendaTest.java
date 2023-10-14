package test.modelTest;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Task;
import model.Agenda;
import model.ControllerAgenda;
import java.util.ArrayList;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;


public class ControllerAgendaTest {
    private ControllerAgenda control;
    private ArrayList<Agenda> agenda;
    private ArrayList<Task> tasks;
    private final int MAX_SIZE=100;
    private ArrayList<String> changes;

    public ArrayList setUpTaskForUsed() {
        ArrayList<Task> taskList = new ArrayList<>();
        int idCounter = 1; // Iniciar el contador de ID en 1
        taskList.add(new Task(idCounter++, "Aristizabal Lord of the night", "I should finish my book", "22-02-2024", 2));
        taskList.add(new Task(idCounter++, "Meeting with Client", "Prepare presentation", "15-03-2024", 1));
        taskList.add(new Task(idCounter++, "Grocery Shopping", "Buy groceries for the week", "25-02-2024", 0));
        taskList.add(new Task(idCounter++, "Doctor's Appointment", "Checkup with Dr. Smith", "10-03-2024", 1));
        taskList.add(new Task(idCounter++, "Clean the House", "Vacuum and mop", "05-03-2024", 3));
        taskList.add(new Task(idCounter++, "Birthday Party", "Buy gifts and decorations", "12-04-2024", 2));
        taskList.add(new Task(idCounter++, "Workout", "Go to the gym", "28-02-2024", 0));
        taskList.add(new Task(idCounter++, "Submit Project Report", "Complete and submit report", "18-03-2024", 4));
        taskList.add(new Task(idCounter++, "Dentist Appointment", "Dental checkup", "08-03-2024", 1));
        taskList.add(new Task(idCounter++, "Call Mom", "Check-in with Mom", "01-03-2024", 0));
        taskList.add(new Task(idCounter++, "Write a Blog Post", "Topic: Technology trends", "10-04-2024", 2));
        taskList.add(new Task(idCounter++, "Study for Exam", "Prepare for the final exam", "20-03-2024", 3));
        taskList.add(new Task(idCounter++, "Family Dinner", "Plan a family dinner", "15-04-2024", 1));
        return taskList;
    }

    public void setUpMinderIsNormal() {
        control = new ControllerAgenda();
        ArrayList<Task> taskList = setUpTaskForUsed();
        for (int i = 0; i < taskList.size(); i++) {
            control.addTask(taskList.get(i).getTitle(), taskList.get(i).getDescription(), taskList.get(i).getDate(), Integer.valueOf(taskList.get(i).getPriority()));
        }
    }

    public void setUpVersionWithAddDeleteAndModify() {
        setUpMinderIsNormal();
        agenda = new ArrayList<>();
        changes = new ArrayList<>();
        try {
            control.removeTask(1);
            changes.add("The task Submit Project Report was removed");
            agenda.add(control.getAgenda().clone());
            control.removeTask(2);
            changes.add("The task Grocery Shopping was removed");
            agenda.add(control.getAgenda().clone());
            control.addTask("Plan Vacation", "Research and book flights", "30-04-2024", 4);
            changes.add("Add new task: Plan Vacation");
            agenda.add(control.getAgenda().clone());
            control.addTask("Pay Bills", "Utility and credit card bills", "05-03-2024", 0);
            changes.add("Add new task: Pay Bills");
            agenda.add(control.getAgenda().clone());
            control.modifyTask(3, "Call Mom", "Check-in with Mom", "01-03-2024", 0);
            changes.add("modify task by title: Call Mom");
            agenda.add(control.getAgenda().clone());
            control.removeTask(1);
            changes.add("The task Plan Vacation was removed");
            agenda.add(control.getAgenda().clone());
            control.removeTask(2);
            changes.add("The task Workout was removed");
            agenda.add(control.getAgenda().clone());
        } catch (exceptionThisDataStructureIsVoid | exceptionTheObjectDoesntExist e) {
            fail();
        }
    }

    private void setupStage1(){
        control = new ControllerAgenda();
        agenda = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    private void setupStageAddTask(){
        setupStage1();
        int j=0;
        for (int i = 1; i <=MAX_SIZE; i++) {
            control.addTask("title"+i, "description"+i, "date"+i, j++);
            tasks.add(new Task(i,"title"+i, "description"+i, "date"+i, j));
            if(j==4){
                j=0;
            }
            if(i%10==0){
                agenda.add(control.getAgenda().clone());
            }
        }
    }

    private void setupStageWithTasksRemove(){
        setupStageAddTask();
        agenda = new ArrayList<>();
        tasks = new ArrayList<>();
        int id=100;
        for (int i = 0; i < 10; i++) {
            try {
                tasks.add(0,control.searchTask(id-i));
                control.removeTask(id-i);
                agenda.add(0,control.getAgenda().clone());

            } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }
        }
    }


    @Test
    public void undoVersionWithoutAnyChanges(){
        setupStage1();
        assertEquals("There is no more versions to undo", control.undo());
    }

    @Test
    public void undoVersionINAddVersion(){
        setupStageAddTask();
        int j=agenda.size()-1;
        for (int i = 100; i > 1; i--) {
            try {
                Task task = control.searchTask(i-1);
                Agenda agendaCurrent= control.getAgenda();
                assertEquals("Undo version: Add new task: "+task.getTitle(), control.undo());
                Agenda agendaPrevious= control.getAgenda();
                assertNotEquals("The Agenda versions should be different by its changes: ",agendaCurrent.toString(), agendaPrevious.toString());

            }catch(exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e ) {
                fail();
            }
        }
    }

    @Test
    public void undoVersionShouldBeEqualsTheContentWithCopyFromArray(){
        setupStageAddTask();
        int j=agenda.size()-1;
        for (int i = 100; i <=10 ; i--) {
            if(i%10==0){
                assertEquals("The Agenda copy from ArrayList should by equals in its contend by mod of 10 ",control.getAgenda().toString(), agenda.get(j).toString());
                if(j-1!=-1)
                    assertNotEquals("The mod 10 version of agenda must be different from a smaller copy of the Agenda arrayList-> ",control.getAgenda().toString(), agenda.get(j-1).toString());
                j--;
            }
            control.undo();
        }
    }

    @Test
    public void undoVersionWithAddDeleteModify(){
        setUpVersionWithAddDeleteAndModify();
        int j=agenda.size()-2;
        for (int i = 0; i < changes.size()-1; i++) {
            String msgChange=control.undo();
            assertEquals("Version change messages must be the same between the undo function and the array. This ensures that it is correctly saving the reason for the modification of that version of the Agenda",
                    msgChange,"Undo version: "+ changes.get(j));
            Agenda contro1 = control.getAgenda();
            assertEquals("When printing all the elements of the agenda version after applying the undo function, it should have the same information as the version of that agenda cloned in an array.",
                    contro1.toString(), agenda.get(j ).toString());
            if(j-1!=-1) {
                assertNotEquals("The agenda version must be different from a smaller copy of the Agenda arrayList-> ",
                        agenda.get(j-1),contro1);
            }
            j--;
        }
    }

}
