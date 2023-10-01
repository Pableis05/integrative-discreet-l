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
    public void removeAllValuesInQueueMustBeEmpty() {
        setupStage4();
        try {
            while (queue.size()!=0){
                queue.poll();
            }
        }catch (exceptionThisDataStructureIsVoid e) {
            throw new RuntimeException(e);
        }
        assertTrue(queue.isEmpty());
    }
    @Test
    public void QueuesSizeSameAsValuesAdded() {
        setupStage4();
        int size=queue.size();
        assertEquals(4,size);
    }

    @Test
    public void CurrectlyClone(){
        setupStage4();
        Queue<String> clone=queue.clone();
        assertNotEquals("The reference to the object must be different: ",queue,clone);
        assertEquals("The size of the object must be the same: ",queue.size(),clone.size());
        try {
            while (!queue.isEmpty()){
                assertEquals("The value of the object must be the same: ",queue.poll(),clone.poll());
            }
        }catch (exceptionThisDataStructureIsVoid e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void differenteObjectReferenceOnClonedNodes() {
        setupStage4();
        Queue<String> clone=queue.clone();
        assertNotEquals("The reference to the object must be different: ",queue,clone);
       try {
           queue.poll();
           clone.offer("AristizabalGod");
           assertNotEquals("The reference to the object must be different: ",queue.poll(),clone.poll());

       }catch (exceptionThisDataStructureIsVoid e){
           throw new RuntimeException(e);
       }



    }
}
