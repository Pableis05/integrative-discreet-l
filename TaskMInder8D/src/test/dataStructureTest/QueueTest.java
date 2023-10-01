package test.dataStructureTest;

import dataStructures.Queue;
import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.exceptionThisDataStructureIsVoid;

public class QueueTest {

    private Queue<String> queue;

    private void setupStage3(){
        queue= new Queue<>();
    }
    private void setupStage4(){
        queue= new Queue<>();
        queue.offer("Hola");
        queue.offer("Mundo");
        queue.offer("Cruel");
        queue.offer("XD jsjsjs");

    }
    @Test
    public void addValueInQueueVoid(){
        setupStage3();
        boolean added=queue.offer("Hola");
        assertTrue(added);
    }
    @Test
    public void addValueInQueue(){
        setupStage4();
        boolean added=queue.offer("Hola");
        assertTrue(added);
    }
    @Test
    public void removeValueInQueueVoid(){
        setupStage3();
        try{
            queue.poll();
            fail();
        }
        catch (exceptionThisDataStructureIsVoid e){
            assertNotNull(e);
        }

    }
    @Test
    public void removeFrontValueInQueue() throws exceptionThisDataStructureIsVoid {
        setupStage4();
        String front=queue.poll();
        assertEquals("Hola",front);
        assertNotEquals(front,queue.front());
    }
    @Test
    public void frontValueInQueueVoid()  {
        setupStage3();

        try{
           queue.front();
           fail();
        }catch (exceptionThisDataStructureIsVoid e){
            assertNotNull(e);
        }
    }
    @Test
    public void returnFrontValueInQueue() throws exceptionThisDataStructureIsVoid {
        setupStage4();
        String front=queue.front();
        assertEquals("Hola",front);
    }

    @Test
    public void removeAllValuesInQueueMustBeEmpty() throws exceptionThisDataStructureIsVoid {
        setupStage4();
        while (queue.size()!=0){
            queue.poll();
        }
        assertTrue(queue.isEmpty());
    }
    @Test
    public void QueuesSizeSameAsValuesAdded() {
        setupStage4();
        int size=queue.size();
        assertEquals(4,size);
    }
}
