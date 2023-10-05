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
    public void test_insert_and_extract_single_element(){
        setupStageHeapVoid();
        heap.insert(1, 10);
        try {
            assertEquals(Integer.valueOf(10), heap.extractMax());
        } catch (exceptionThisDataStructureIsVoid e) {
            fail();
        }

    }

    // Inserting multiple elements and extracting them in order
    @Test
    public void test_insert_and_extract_multiple_elements() {
        setupStageHeapNormal();
        try {
            assertEquals(Integer.valueOf(40), heap.extractMax());
            assertEquals(Integer.valueOf(20), heap.extractMax());
            assertEquals(Integer.valueOf(30), heap.extractMax());
        } catch (exceptionThisDataStructureIsVoid e) {
            fail();
        }
    }

    // Increasing the priority of an element and extracting it
    @Test
    public void test_increase_priority_and_extract_element() {
        setupStageHeapNormal();
        try {

            heap.increaseKey(10, 4);
            assertEquals(Integer.valueOf(10), heap.extractMax());
        } catch (exceptionThisDataStructureIsVoid  | exceptionTheObjectDoesntExist e) {
            fail();
        }
    }


    // Extracting from an empty heap
    @Test
    public void test_extract_from_empty_heap() {
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
    public void test_insert_element_with_negative_priority() {
        setupStageHeapWithNegative();
        try {
            assertEquals(Integer.valueOf(10), heap.extractMax());
        } catch (exceptionThisDataStructureIsVoid e) {
            fail();
        }
    }

    // Increasing priority of an element not present in the heap
    @Test
    public void test_increase_priority_of_nonexistent_element()  {
        setupStageHeapNormal();
        try {
            heap.increaseKey(15, 4);
            assertEquals(Integer.valueOf(30), heap.extractMax());
            fail();
        }catch (exceptionTheObjectDoesntExist e){
            assertNotNull(e);
        } catch (exceptionThisDataStructureIsVoid e) {
            fail();
        }
    }

    @Test
    public void test_increase_priority_of_nonexistent_element2() {
        setupStageHeapNormal();
        try {
            heap.increaseKey(15, 4);
            assertEquals(Integer.valueOf(30), heap.extractMax());
            fail();
        }catch (exceptionTheObjectDoesntExist e){
            assertNotNull(e);
        }catch (exceptionThisDataStructureIsVoid e){
            fail();
        }

    }

    // Inserting and extracting elements with large priority values
    @Test
    public void test_insert_and_extract_large_priority_elements(){
        setupStageExtractLargePriority();
        try {

            assertEquals(Integer.valueOf(10), heap.extractMax());
            assertEquals(Integer.valueOf(30), heap.extractMax());
            assertEquals(Integer.valueOf(40), heap.extractMax());
            assertEquals(Integer.valueOf(20), heap.extractMax());
        }catch (exceptionThisDataStructureIsVoid e){
            fail();
        }
    }

    // Inserting and extracting elements with same node but different priority values
    @Test
    public void test_insert_and_extract_same_node_different_priority_elements() {
        extract_same_node_different_priority_elements();
        try {
            assertEquals(Integer.valueOf(10), heap.extractMax());
            assertEquals(Integer.valueOf(10), heap.extractMax());
            assertEquals(Integer.valueOf(10), heap.extractMax());
            assertEquals(Integer.valueOf(10), heap.extractMax());
            assertEquals(Integer.valueOf(10), heap.extractMax());
        } catch (exceptionThisDataStructureIsVoid e) {
            fail();
        }

    }

    // Inserting a single element and extracting it
    @Test
    public void test_insert_and_extract(){
        setupStageHeapVoid();
        
        heap.insert(5, 10);
        try {
            assertEquals(10, heap.extractMax().intValue());
        } catch (exceptionThisDataStructureIsVoid e) {
            fail();
        }
    }

    // Inserting multiple elements and extracting them in order
    @Test
    public void testInsertAndExtractInOrder()  {
        setupStageHeapInOrden();
        try {

            assertEquals(40, (int) heap.extractMax());
            assertEquals(10, (int) heap.extractMax());
            assertEquals(20, (int) heap.extractMax());
            assertEquals(30, (int) heap.extractMax());
        }catch (exceptionThisDataStructureIsVoid e){
            fail();
        }
    }

    // Inserting elements with same priority and extracting them in order of insertion
    @Test
    public void testInsertionOrder()  {
        inOrdenNodeWithKeySame();
        try {

            assertEquals(Integer.valueOf(10), heap.extractMax());//Because in the heap the first element is the last element inserted
            assertEquals(Integer.valueOf(50), heap.extractMax());
            assertEquals(Integer.valueOf(40), heap.extractMax());
            assertEquals(Integer.valueOf(30),heap.extractMax());
            assertEquals(Integer.valueOf(20), heap.extractMax());
        }catch (exceptionThisDataStructureIsVoid e){
            fail();
        }
    }

    // Cloning the heap and verifying the clone is equal to the original heap
    @Test
    public void testCloneHeap()  {
        setupStageHeapNormal();
        Heap<Integer> clone = heap.clone();
        Integer aux=0;
        assertNotEquals("The reference to the object must be different: ",heap,clone);
        try {

            do{
                aux=heap.extractMax();
                assertEquals(aux,clone.extractMax());
            }while (!heap.isEmpty());
        }catch (exceptionThisDataStructureIsVoid e){
            fail();
        }
    }



}