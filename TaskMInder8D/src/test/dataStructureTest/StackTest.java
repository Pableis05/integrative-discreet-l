package test.dataStructureTest;

import dataStructures.Stack;
import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.exceptionThisDataStructureIsVoid;

public class StackTest {

    private Stack<String> stack;

    private void setupStage5(){
        stack= new Stack<>();
    }
    private void setupStage6(){
        stack= new Stack<>();
        stack.push("Michael");
        stack.push("Jackson");
        stack.push("is very");
        stack.push("Amazing");

    }

    @Test
    public void addValueInStackVoid(){
        setupStage5();
        boolean added=stack.push("Hola");
        assertTrue(added);
    }

    @Test
    public void addValueInStack(){
        setupStage6();
        boolean added=stack.push("Hola");
        assertTrue(added);
    }

    @Test
    public void removeValueInStackVoid()  {
        setupStage5();
        try {
            stack.pop();
            fail();
        }catch (exceptionThisDataStructureIsVoid e) {
            assertNotNull(e);
        }

    }

    @Test
    public void removeFrontValueInStack() throws exceptionThisDataStructureIsVoid {
        setupStage6();
        String top=stack.pop();
        assertEquals("Amazing",top);
        assertNotEquals(top,stack.top());
    }

    @Test
    public void topValueInStackVoid()  {
        setupStage5();
        try {
            stack.top();
            fail();
        }catch (exceptionThisDataStructureIsVoid e) {
            assertNotNull(e);
        }
    }

    @Test
    public void returnTopValueInStack() throws exceptionThisDataStructureIsVoid {
        setupStage6();
        String top=stack.top();
        assertEquals("Amazing",top);
    }

    @Test
    public void removeAllValuesInStackMustBeEmpty() throws exceptionThisDataStructureIsVoid {
        setupStage6();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue("Exception the list is void",stack.isEmpty());
    }
    @Test
    public void StacksSizeSameAsValuesAdded() throws exceptionThisDataStructureIsVoid {
        setupStage6();
        stack.push("Beat beat");
        int size=stack.size();
        assertEquals(5,size);

    }

    @Test
    public void CurrectlyClone(){
        setupStage6();
        Stack<String> clone = stack.clone();
        assertNotEquals("The reference to the object must be different: ",stack,clone);
        assertEquals("The size of the object must be the same: ",stack.size(),clone.size());
        try {
            while (!stack.isEmpty())
                assertEquals("The values of the object mush be the same: ",stack.pop(),clone.pop());
        }catch (exceptionThisDataStructureIsVoid e) {
            throw new RuntimeException(e);
        }
        assertTrue("The object must be empty: ",stack.isEmpty());
    }





}
