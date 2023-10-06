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


    private void setupStage1(){
        control = new ControllerAgenda();
        agenda = new ArrayList<>();
        tasks = new ArrayList<>();
    }


    private void setupStageAddTask(){
        setupStage1();
        int j=0;
        for (int i = 1; i <=100; i++) {
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
    public void undoVersionINRemoveVersion(){
        setupStageWithTasksRemove();
        int j=agenda.size()-1;
        for (int i = 0; i < 10; i++) {
            if(i+1<10) {
                Task task = tasks.get(i + 1);
                Agenda agendaCurrent = control.getAgenda();
                assertEquals("Undo version: remove task by title: " + task.getTitle(), control.undo());
                Agenda agendaPrevious = control.getAgenda();
                assertNotEquals("The Agenda versions should be different by its changes of removed: ", agendaCurrent.toString(), agendaPrevious.toString());
            }
        }
    }







}
