package test.dataStructureTest;


import dataStructures.Heap;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTest {

    // Inserting a single element and extracting it
    @Test
    public void test_insert_and_extract_single_element() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, 10);
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Inserting multiple elements and extracting them in order
    @Test
    public void test_insert_and_extract_multiple_elements() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(3, 10);
        heap.insert(2, 20);
        heap.insert(1, 30);
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(20), heap.extractMax());
        assertEquals(Integer.valueOf(30), heap.extractMax());
    }

    // Increasing the priority of an element and extracting it
    @Test
    public void test_increase_priority_and_extract_element() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, 10);
        heap.insert(2, 20);
        heap.insert(3, 30);
        heap.increaseKey(10, 4);
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Extracting from an empty heap
    @Test
    public void test_extract_from_empty_heap() {
        Heap<Integer> heap = new Heap<>();
        assertNull(heap.extractMax());
    }

    // Inserting null element
    @Test
    public void test_insert_null_element() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, null);
        assertNull(heap.extractMax());
    }

    // Inserting element with negative priority
    @Test
    public void test_insert_element_with_negative_priority() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(-1, 10);
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Increasing priority of an element to a negative value
    @Test
    public void test_increase_priority_to_negative_value() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, 10);
        heap.increaseKey(10, -1);
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Increasing priority of an element not present in the heap
    @Test
    public void test_increase_priority_of_nonexistent_element() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(2, 12);
        heap.insert(1, 10);
        heap.increaseKey(10, 4);
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Inserting and extracting elements with large priority values
    @Test
    public void test_insert_and_extract_large_priority_elements() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(Integer.MAX_VALUE, 10);
        heap.insert(Integer.MIN_VALUE, 20);
        heap.insert(Integer.MAX_VALUE, 30);
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(30), heap.extractMax());
        assertEquals(Integer.valueOf(20), heap.extractMax());
    }

    // Inserting and extracting elements with large node values
    @Test
    public void test_insert_and_extract_large_node_elements() {
        Heap<Long> heap = new Heap<>();
        heap.insert(Integer.MAX_VALUE, Long.MAX_VALUE);
        heap.insert(2, Long.MIN_VALUE);
        assertEquals(Long.valueOf(Long.MAX_VALUE), heap.extractMax());
        assertEquals(Long.valueOf(Long.MIN_VALUE), heap.extractMax());
    }

    // Inserting and extracting elements with same node but different priority values
    @Test
    public void test_insert_and_extract_same_node_different_priority_elements() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, 10);
        heap.insert(2, 10);
        heap.insert(3, 10);
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(10), heap.extractMax());
    }

    // Inserting a single element and extracting it
    @Test
    public void test_insert_and_extract() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(5, 10);
        assertEquals(10, heap.extractMax().intValue());
    }

    // Inserting multiple elements and extracting them in order
    @Test
    public void test_insert_and_extract_in_order() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(3, 10);
        heap.insert(2, 20);
        heap.insert(1, 30);
        heap.insert(4, 40);
    
        assertEquals(40, (int) heap.extractMax());
        assertEquals(10, (int) heap.extractMax());
        assertEquals(20, (int) heap.extractMax());
        assertEquals(30, (int) heap.extractMax());
    }

    // Inserting elements with same priority and extracting them in order of insertion
    @Test
    public void test_insertion_order() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, 10);
        heap.insert(1, 20);
        heap.insert(1, 30);
        heap.insert(1, 40);
        heap.insert(1, 50);

        assertEquals(Integer.valueOf(10), heap.extractMax());
        assertEquals(Integer.valueOf(50), heap.extractMax());
        assertEquals(Integer.valueOf(40), heap.extractMax());
        assertEquals(Integer.valueOf(30),heap.extractMax());
        assertEquals(Integer.valueOf(20), heap.extractMax());
    }

    // Cloning the heap and verifying the clone is equal to the original heap
    @Test
    public void test_clone_heap() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1, 10);
        heap.insert(2, 20);
        heap.insert(3, 30);
        Heap<Integer> clone = heap.clone();
        assertEquals(heap.show(), clone.show());
    }



}