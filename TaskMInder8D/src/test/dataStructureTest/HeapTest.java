package test.dataStructureTest;

import dataStructures.Heap;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

    private Heap<Integer> heap;

    // Inserting a single element and extracting it
    private void setupStageHeapVoid(){
       heap= new Heap<>();
    }
    private void setupStageHeapNormal(){
        heap= new Heap<>();
        heap.insert(0, 10);
        heap.insert(1, 30);
        heap.insert(2, 20);
        heap.insert(3, 40);
        heap.insert(-1,4);
    }


    private void setupStageHeapWithNegative(){
        heap= new Heap<>();
        heap.insert(-1, 10);
        heap.insert(-10, 40);
        heap.insert(-2, 20);
        heap.insert(-3, 10);

    }



    private void setupStageExtractLargePriority(){
        heap= new Heap<>();
        heap.insert(Byte.MAX_VALUE, 40);
        heap.insert(Integer.MIN_VALUE, 20);
        heap.insert(Integer.MAX_VALUE, 10);
        heap.insert(Integer.MAX_VALUE, 30);
    }

    public void extract_same_node_different_priority_elements(){
        heap= new Heap<>();
        heap.insert(1, 10);
        heap.insert(2, 10);
        heap.insert(3, 10);
        heap.insert(4, 10);
        heap.insert(5, 10);

    }

    public  void setupStageHeapInOrden(){
        heap= new Heap<>();
        heap.insert(1, 30);
        heap.insert(2, 20);
        heap.insert(3, 10);
        heap.insert(4, 40);

    }
    public  void inOrdenNodeWithKeySame(){
        heap= new Heap<>();
        heap.insert(1, 10);
        heap.insert(1, 20);
        heap.insert(1, 30);
        heap.insert(1, 40);
        heap.insert(1, 50);

    }
    @Test
    public void test_insert_and_extract_single_element() throws exceptionThisDataStructureIsVoid {
        setupStageHeapVoid();
        heap.insert(1, 10);
        assertEquals(Integer.valueOf(10), heap.extractMax());

    }

    // Inserting multiple elements and extracting them in order
    @Test
    public void test_insert_and_extract_multiple_elements() throws exceptionThisDataStructureIsVoid {
        setupStageHeapNormal();
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(20), heap.extractMax());
        assertEquals(Integer.valueOf(30), heap.extractMax());
    }

    // Increasing the priority of an element and extracting it
    @Test
    public void test_increase_priority_and_extract_element() throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist {
        setupStageHeapNormal();
        heap.increaseKey(10, 4);
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }


    // Extracting from an empty heap
    @Test
    public void test_extract_from_empty_heap() throws exceptionThisDataStructureIsVoid {
        setupStageHeapVoid();
        try {
            assertNull(heap.extractMax());
            fail();
        }catch (exceptionThisDataStructureIsVoid e){
            assertNotNull(e);
        }

    }


    // Inserting element with negative priority
    @Test
    public void test_insert_element_with_negative_priority() throws exceptionThisDataStructureIsVoid {
        setupStageHeapWithNegative();
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Increasing priority of an element not present in the heap
    @Test
    public void test_increase_priority_of_nonexistent_element() throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist {
        setupStageHeapNormal();
        try {
            heap.increaseKey(15, 4);
            assertEquals(Integer.valueOf(30), heap.extractMax());
            fail();
        }catch (exceptionTheObjectDoesntExist e){
            assertNotNull(e);
        }

    }

    // Inserting and extracting elements with large priority values
    @Test
    public void test_insert_and_extract_large_priority_elements()throws exceptionThisDataStructureIsVoid  {
        setupStageExtractLargePriority();
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(30), heap.extractMax());
        assertEquals(Integer.valueOf(40), heap.extractMax());
        assertEquals(Integer.valueOf(20), heap.extractMax());
    }

    // Inserting and extracting elements with same node but different priority values
    @Test
    public void test_insert_and_extract_same_node_different_priority_elements() throws exceptionThisDataStructureIsVoid {
        extract_same_node_different_priority_elements();
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(10), heap.extractMax());

    }

    // Inserting a single element and extracting it
    @Test
    public void test_insert_and_extract()throws exceptionThisDataStructureIsVoid  {
        setupStageHeapVoid();
        
        heap.insert(5, 10);
        assertEquals(10, heap.extractMax().intValue()) ;
    }

    // Inserting multiple elements and extracting them in order
    @Test
    public void testInsertAndExtractInOrder()throws exceptionThisDataStructureIsVoid  {
        setupStageHeapInOrden();
        assertEquals(40, (int) heap.extractMax());
        assertEquals(10, (int) heap.extractMax());
        assertEquals(20, (int) heap.extractMax());
        assertEquals(30, (int) heap.extractMax());
    }

    // Inserting elements with same priority and extracting them in order of insertion
    @Test
    public void testInsertionOrder() throws exceptionThisDataStructureIsVoid {
        inOrdenNodeWithKeySame();

        assertEquals(Integer.valueOf(10), heap.extractMax());//Because in the heap the first element is the last element inserted
        assertEquals(Integer.valueOf(50), heap.extractMax());
        assertEquals(Integer.valueOf(40), heap.extractMax());
        assertEquals(Integer.valueOf(30),heap.extractMax());
        assertEquals(Integer.valueOf(20), heap.extractMax());
    }

    // Cloning the heap and verifying the clone is equal to the original heap
    @Test
    public void testCloneHeap()throws exceptionThisDataStructureIsVoid  {
        setupStageHeapNormal();
        Heap<Integer> clone = heap.clone();
        Integer aux=0;
        assertNotEquals("The reference to the object must be different: ",heap,clone);
        do{
            aux=heap.extractMax();
            assertEquals(aux,clone.extractMax());
        }while (!heap.isEmpty());
    }



}