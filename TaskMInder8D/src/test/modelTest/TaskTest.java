package test.modelTest;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Task;
public class TaskTest {


    private Task task;
    public void setUpVoid(){
        task = null;
    }

    public void setupStageWittStack(){
        task = new Task(12345678,"Marco Aurelio ","he owes me money","22-02-2034",2);
    }
    public void testTaskVoid(){
        setUpVoid();
        assertNull(task);
    }

    @Test
    public void testTask(){
        setupStageWittStack();
        assertEquals(12345678,task.getId());
        assertEquals("Marco Aurelio ",task.getTitle());
        assertEquals("he owes me money",task.getDescription());
        assertEquals("22-02-2034",task.getDate());
        assertEquals(2,task.getPriority());
    }


}
