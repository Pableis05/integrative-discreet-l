package test.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Agenda;
import java.util.ArrayList;
import model.Task;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;


public class AgendaTest {
    private Agenda minder;

    public void setUpMinderIsVoid(){
        minder= new Agenda();
    }

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
        taskList.add(new Task(idCounter++, "Pay Bills", "Utility and credit card bills", "05-03-2024", 0));
        taskList.add(new Task(idCounter++, "Family Dinner", "Plan a family dinner", "15-04-2024", 1));
        taskList.add(new Task(idCounter, "Plan Vacation", "Research and book flights", "30-04-2024", 4));

        return taskList;
    }


    public void setUpMinderIsNormal() {
        minder = new Agenda();
        minder.addTask("Aristizabal Lord of the night", "I should finish my book", "22-02-2024", 2);
        minder.addTask("Meeting with Client", "Prepare presentation", "15-03-2024", 1);
        minder.addTask("Grocery Shopping", "Buy groceries for the week", "25-02-2024", 0);
        minder.addTask("Doctor's Appointment", "Checkup with Dr. Smith", "10-03-2024", 1);
        minder.addTask("Clean the House", "Vacuum and mop", "05-03-2024", 3);
        minder.addTask("Birthday Party", "Buy gifts and decorations", "12-04-2024", 2);
        minder.addTask("Workout", "Go to the gym", "28-02-2024", 0);
        minder.addTask("Submit Project Report", "Complete and submit report", "18-03-2024", 4);
        minder.addTask("Dentist Appointment", "Dental checkup", "08-03-2024", 1);
        minder.addTask("Call Mom", "Check-in with Mom", "01-03-2024", 0);
        minder.addTask("Write a Blog Post", "Topic: Technology trends", "10-04-2024", 2);
        minder.addTask("Study for Exam", "Prepare for the final exam", "20-03-2024", 3);
        minder.addTask("Pay Bills", "Utility and credit card bills", "05-03-2024", 0);
        minder.addTask("Family Dinner", "Plan a family dinner", "15-04-2024", 1);
        minder.addTask("Plan Vacation", "Research and book flights", "30-04-2024", 4);
    }

    @Test
    public void addTaskVoid(){
        setUpMinderIsVoid();
        boolean added= minder.addTask("Aristizabal Lord of the night", "I should finish my book", "22-02-2024", 2);
        assertTrue("The task was  added",added);
    }

    @Test
    public void addTaskInMinderWithMoreTasks(){
        setUpMinderIsNormal();
        boolean added= minder.addTask("The questions for the monitors", "Jhoan and Richard are good guys", "22-02-2024", 4);
        assertTrue("The task was  added",added);
    }

    @Test
    public void searchTaskInMinderVoid()throws exceptionTheObjectDoesntExist{
        setUpMinderIsVoid();
        try {
            minder.searchTask(1);
            fail();
        }catch (exceptionThisDataStructureIsVoid e){
            assertNotNull(e);
        }
    }

    @Test
    public void searchTaskInMinderATaskWhatDontExist()throws exceptionThisDataStructureIsVoid{
        setUpMinderIsNormal();
        try {
            minder.searchTask(100);
            fail();
        }catch (exceptionTheObjectDoesntExist e){
            assertNotNull(e);
        }

    }

    @Test
    public void searchTaskInMinderATaskWhatExist()throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        setUpMinderIsNormal();
        Task task= minder.searchTask(7);
        ArrayList taskList= setUpTaskForUsed();
        assertEquals("The task was found",task.toString(),taskList.get(6).toString());
    }

    @Test
    public void removeTaskInMinderVoid()throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        setUpMinderIsVoid();
        boolean removed= minder.removeTask(1);
        assertFalse("The task was not removed because the list is void",removed);
    }

    @Test
    public void removeTaskInMinderATaskWhatDontExist()throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        setUpMinderIsNormal();
        boolean removed= minder.removeTask(100);
        assertFalse("The task was not removed because the task dont exist",removed);
    }

    @Test
    public void removedTaskInMinderATaskWhatExist()throws exceptionThisDataStructureIsVoid{
        setUpMinderIsNormal();
        boolean removed= minder.removeTask(7);
        assertTrue("The task was removed",removed);
        try {
            minder.searchTask(7);
            fail();
        }catch (exceptionTheObjectDoesntExist e){
            assertNotNull(e);
        }
    }

    @Test
    public void removedTaskInMinderAndTheOtherTaskStillExist() throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        setUpMinderIsNormal();
        int id=7;//The task what will be removed by its id
        ArrayList<Task> taskList= setUpTaskForUsed();
        boolean removed= minder.removeTask(id);
        assertTrue("The task was removed",removed);
        for (int i = 0; i < taskList.size(); i++) {
            if(i!=id-1)
                assertEquals("The task was not removed",taskList.get(i).toString(),minder.searchTask(i+1).toString());

        }
    }

    @Test
    public void modifyTaskInMinderVoid() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        setUpMinderIsVoid();
        try {

            boolean modified= minder.modifyTask(1,"Aristizabal Lord of the night", "I should finish my book", "22-02-2024", 2);
            assertFalse("The task was not modified because the list is void",modified);
        }catch (exceptionThisDataStructureIsVoid e){
            assertNotNull(e);
        }
    }

    @Test
    public void modifyTaskInMinderNormal() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        setUpMinderIsNormal();
        String title="Hi, I am German";
        String Description=" And the video at today";
        String date="22-02-2025";
        int priority=3;
        boolean modified= minder.modifyTask(1,title, Description, date, priority);
        assertTrue("The task was modified",modified);
        Task task= minder.searchTask(1);
        assertEquals("The task was modified",title,task.getTitle());
        assertEquals("The task was modified",Description,task.getDescription());
        assertEquals("The task was modified",date,task.getDate());
        assertEquals("The task was modified",priority,task.getPriority());
        ArrayList<Task> taskList= setUpTaskForUsed();
        assertNotEquals("The task was modified",taskList.get(0).toString(),task.toString());
        for (int i = 1; i < taskList.size(); i++) {
                assertEquals("The task was not removed",taskList.get(i).toString(),minder.searchTask(i+1).toString());

        }
    }

    @Test
    public void modifyTaskInMinderAndThisDontExist() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        setUpMinderIsNormal();
        String title="Hi, I am German";
        String Description=" And the video at today";
        String date="22-02-2025";
        int priority=3;
        boolean modified = minder.modifyTask(100, title, Description, date, priority);
        assertFalse("The task was not modified because the task dont exist", modified);
    }

    @Test
    public void ClonedAgenda(){
        setUpMinderIsNormal();
        Agenda clone= minder.clone();
        assertEquals("The agenda was cloned",minder.toString(),clone.toString());
        assertFalse("The agenda was cloned",minder==clone);
    }
    @Test
    public void CloneadWithElementsWithTheSameContentDifferentReference() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid{
        setUpMinderIsNormal();

        Agenda clone= minder.clone();
        ArrayList<Task> taskList= setUpTaskForUsed();

        for (int i = 0; i < taskList.size(); i++) {
            assertEquals("The agenda was cloned",taskList.get(i).toString(),clone.searchTask(i+1).toString());
            assertFalse("The agenda was cloned",taskList.get(i)==clone.searchTask(i+1));
        }
    }












}
