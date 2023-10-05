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

    public void setUpMinderIsVoid() {
        minder = new Agenda();
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
        ArrayList<Task> taskList = setUpTaskForUsed();
        for (int i = 0; i < taskList.size(); i++) {
            minder.addTask(taskList.get(i).getTitle(), taskList.get(i).getDescription(), taskList.get(i).getDate(), Integer.valueOf(taskList.get(i).getPriority()));
        }
    }

    public ArrayList<Task> setupTaskExtremeForUsed() {
        ArrayList<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int priority = 0;//random.nextInt(4);
            taskList.add(new Task(i + 1, "Tittle " + i, i * i + "", "00-00-0000", priority++));
            if (priority == 4)
                priority = 0;
        }
        return taskList;
    }

    public void setUpMinderIsExtreme() {
        minder = new Agenda();
        ArrayList<Task> taskList = setupTaskExtremeForUsed();
        for (int i = 0; i < taskList.size(); i++) {
            minder.addTask(taskList.get(i).getTitle(), taskList.get(i).getDescription(), taskList.get(i).getDate(), Integer.valueOf(taskList.get(i).getPriority()));
        }
    }


    @Test
    public void addTaskVoid() {
        setUpMinderIsVoid();
        boolean added = minder.addTask("Aristizabal Lord of the night", "I should finish my book", "22-02-2024", 2);
        assertTrue("The task was  added", added);
    }

    @Test
    public void addTaskInMinderWithMoreTasks() {
        setUpMinderIsNormal();
        boolean added = minder.addTask("The questions for the monitors", "Jhoan and Richard are good guys", "22-02-2024", 4);
        assertTrue("The task was  added", added);
    }

    @Test
    public void searchTaskInMinderVoid() {
        setUpMinderIsVoid();
        try {

            try {
                minder.searchTask(1);
                fail();
            } catch (exceptionTheObjectDoesntExist e) {
                fail();
            }

        } catch (exceptionThisDataStructureIsVoid e) {
            assertNotNull(e);
        }
    }


    @Test
    public void searchTaskInMinderATaskWhatDontExist() {
        setUpMinderIsNormal();
        try {
            try {
                minder.searchTask(100);
                fail();
            } catch (exceptionThisDataStructureIsVoid e) {
                fail();
            }

        } catch (exceptionTheObjectDoesntExist e) {
            assertNotNull(e);
        }

    }

    @Test
    public void searchTaskInMinderATaskWhatExist() {
        setUpMinderIsNormal();
        Task task = null;
        try {
            task = minder.searchTask(7);
        } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
            fail();
        }
        ArrayList taskList = setUpTaskForUsed();
        assertEquals("The task was found", task.toString(), taskList.get(6).toString());
    }

    @Test
    public void removeTaskInMinderVoid() {
        setUpMinderIsVoid();
        boolean removed = minder.removeTask(1);
        assertFalse("The task was not removed because the list is void", removed);
    }

    @Test
    public void removeTaskInMinderATaskWhatDontExist() {
        setUpMinderIsNormal();
        boolean removed = minder.removeTask(100);
        assertFalse("The task was not removed because the task dont exist", removed);
    }

    @Test
    public void removedTaskInMinderATaskWhatExist() {
        setUpMinderIsNormal();
        boolean removed = minder.removeTask(7);
        assertTrue("The task was removed", removed);
        try {
            try {
                minder.searchTask(7);
            } catch (exceptionThisDataStructureIsVoid e) {
                fail();
            }
        } catch (exceptionTheObjectDoesntExist e) {
            assertNotNull(e);
        }
    }

    @Test
    public void removedTaskInMinderAndTheOtherTaskStillExist() {
        setUpMinderIsNormal();
        int id = 7;//The task what will be removed by its id
        ArrayList<Task> taskList = setUpTaskForUsed();
        boolean removed = minder.removeTask(id);
        assertTrue("The task was removed", removed);

        for (int i = 0; i < taskList.size(); i++) {
            if (i != id - 1) {
                try {
                    assertEquals("The task was not removed", taskList.get(i).toString(), minder.searchTask(i + 1).toString());
                } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                    fail();
                }
            }

        }

    }

    @Test
    public void modifyTaskInMinderVoid() {
        setUpMinderIsVoid();
        try {

            boolean modified = false;
            try {
                modified = minder.modifyTask(1, "Aristizabal Lord of the night", "I should finish my book", "22-02-2024", 2);
            } catch (exceptionTheObjectDoesntExist e) {
                fail();
            }
            assertFalse("The task was not modified because the list is void", modified);
        } catch (exceptionThisDataStructureIsVoid e) {
            assertNotNull(e);
        }
    }


    @Test
    public void modifyTaskInMinderNormal() {
        setUpMinderIsNormal();
        String title = "Hi, I am German";
        String Description = " And the video at today";
        String date = "22-02-2025";
        int priority = 3;
        boolean modified = false;
        try {
            modified = minder.modifyTask(1, title, Description, date, priority);
        } catch (exceptionThisDataStructureIsVoid | exceptionTheObjectDoesntExist e) {
            fail();
        }
        assertTrue("The task was modified", modified);

        Task task = null;
        try {
            task = minder.searchTask(1);

        } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
            fail();
        }
        assertEquals("The task was modified", title, task.getTitle());
        assertEquals("The task was modified", Description, task.getDescription());
        assertEquals("The task was modified", date, task.getDate());
        assertEquals("The task was modified", priority, task.getPriority());
        ArrayList<Task> taskList = setUpTaskForUsed();
        assertNotEquals("The task was modified", taskList.get(0).toString(), task.toString());
        for (int i = 1; i < taskList.size(); i++) {
            try {
                assertEquals("The task was not removed", taskList.get(i).toString(), minder.searchTask(i + 1).toString());
            } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }

        }
    }

    @Test
    public void modifyTaskInMinderAndThisDontExist() {
        setUpMinderIsNormal();
        String title = "Hi, I am German";
        String Description = " And the video at today";
        String date = "22-02-2025";
        int priority = 3;
        boolean modified = false;
        try {
            modified = minder.modifyTask(100, title, Description, date, priority);
        } catch (exceptionThisDataStructureIsVoid | exceptionTheObjectDoesntExist e) {
            fail();
        }
        assertFalse("The task was not modified because the task dont exist", modified);
    }

    @Test
    public void ClonedAgenda() {
        setUpMinderIsNormal();
        Agenda clone = minder.clone();
        assertEquals("The agenda was cloned", minder.toString(), clone.toString());
        assertFalse("The agenda was cloned", minder == clone);
    }

    @Test
    public void CloneadWithElementsWithTheSameContentDifferentReference() {
        setUpMinderIsNormal();

        Agenda clone = minder.clone();
        ArrayList<Task> taskList = setUpTaskForUsed();

        for (int i = 0; i < taskList.size(); i++) {
            try {
                assertEquals("The agenda was cloned", taskList.get(i).toString(), clone.searchTask(i + 1).toString());
                assertFalse("The agenda was cloned", taskList.get(i) == clone.searchTask(i + 1));
            } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }
        }
    }

    @Test
    public void allTheTaskSearchWasAdded() {
        setUpMinderIsExtreme();
        ArrayList<Task> taskList = setupTaskExtremeForUsed();
        for (int i = 0; i < taskList.size(); i++) {
            try {
                assertEquals("The task was added: ", taskList.get(i).toString(), minder.searchTask(i + 1).toString());
            } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }
        }
    }

    @Test
    public void removedHalfTaskInMinderAndTheOtherTaskStillExistExtreme() {
        setUpMinderIsExtreme();
        ArrayList<Task> taskList = setupTaskExtremeForUsed();
        int size = taskList.size();

        for (int i = 0; i < size / 2; i++) {
            boolean removed = minder.removeTask(i + 1);
            try {
                minder.searchTask(i + 1);
                fail();
            } catch (exceptionTheObjectDoesntExist e) {
                assertNotNull(e);
            } catch (exceptionThisDataStructureIsVoid e) {
                fail();
            }
            assertTrue("The task was removed", removed);
            Task task = taskList.get(i);
            taskList.remove(0);

        }
        int j = 0;
        for (int i = size / 2; i < size; i++) {
            try {
                assertEquals("The task was not removed", taskList.get(j++).toString(), minder.searchTask(i + 1).toString());
            } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }
        }
    }

    @Test
    public void modifyHalfTaskInMinderAndTheOtherTaskStillSameExtreme(){
        setUpMinderIsExtreme();
        ArrayList<Task> taskList = setupTaskExtremeForUsed();
        int size = taskList.size();
        for (int i = 0; i < size / 2; i++) {
            try {
                boolean modified = minder.modifyTask(i +1, "Hi, I am German "+i*i, " And the video at today "+i*i, "22-02-2025", 3);
                assertTrue("The task was modified", modified);

            }catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }
        }
        for (int i = 0; i < size; i++) {
            if(i<size/2) {
                try {
                    assertNotEquals("The tasks arent equals", taskList.get(i).toString(), minder.searchTask(i + 1).toString());
                } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                    fail();
                }
            }else{
                try {
                    assertEquals("The tasks are equals", taskList.get(i).toString(), minder.searchTask(i + 1).toString());
                } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                    fail();
                }
            }
        }
    }

    @Test
    public void clonedMinderWithExtremeData(){
        setUpMinderIsExtreme();
        Agenda clone = minder.clone();
        ArrayList<Task> taskList = setupTaskExtremeForUsed();
        assertNotEquals("The reference to the object must be different: ",minder,clone);
        for (int i = 0; i < taskList.size(); i++) {
            try {
                assertEquals("The agenda was cloned and tasks content are equals", taskList.get(i).toString(), clone.searchTask(i + 1).toString());
                assertFalse("The agenda was cloned and Tasks reference are not equals", taskList.get(i) == clone.searchTask(i + 1));
            } catch (exceptionTheObjectDoesntExist | exceptionThisDataStructureIsVoid e) {
                fail();
            }
        }
    }

}
