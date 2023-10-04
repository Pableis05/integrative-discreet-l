package dataStructures;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;

import java.util.ArrayList;

public class Heap<V> implements IPriorityQueue<V>, Cloneable{

    private ArrayList<NodePriorityQueue<V>> heapArr;

    public Heap() {
        heapArr = new ArrayList<>(); heapArr.add(null);
    }


    /**
     * The insert function adds a new node with a given priority to the priority queue and then reorganizes the queue to maintain the max heap property.
     * 
     * @param priority The priority of the node being inserted into the priority queue. This determines the order in which the nodes will be removed from the queue.
     * @param node The "node" parameter represents the element that you want to insert into the priority queue.
     */
    @Override
    public void insert(int priority, V node) {
        NodePriorityQueue<V> newNode = new NodePriorityQueue<>(priority, node);
        heapArr.add(newNode);
        buildMaxHeapify();
    }

    /**
     * The function "buildMaxHeapify" builds a max heap by calling the "maxHeapify" function on each element of the array in reverse order.
     */
    public void buildMaxHeapify() {

        for(int i = heapArr.size()-1; i >=1 ;i--)
            maxHeapify(i);

    }

    /**
     * The maxHeapify function is used to maintain the max heap property in a priority queue represented as an array.
     * 
     * @param index The index parameter represents the index of the element in the heap that needs to be heapified.
     */
    public void maxHeapify(int index) {
        int largest = index;

        int right = 2 * index + 1;

        int left = 2 * index;

        if (left <= heapArr.size() - 1 && heapArr.get(left).getPriority() > heapArr.get(index).getPriority())
            largest = left;



        if (right <= heapArr.size() - 1 && heapArr.get(right).getPriority() > heapArr.get(largest).getPriority())
            largest = right;


        if (largest != index) {
            NodePriorityQueue<V> temp1 = heapArr.get(index);
            NodePriorityQueue<V> temp2 = heapArr.get(largest);
            heapArr.set(index, temp2);
            heapArr.set(largest, temp1);
            maxHeapify(largest);
        }


    }


    /**
     * The function performs a heap sort on an array by building a max heap and repeatedly swapping the root element with the last element and then heapifying the remaining elements.
     */
    @Override
    public void heapSort() {
        buildMaxHeapify();
        for(int i = heapArr.size() - 1; i >= 1; i--) {
            NodePriorityQueue<V> temp = heapArr.get(1);
            heapArr.set(1, heapArr.get(i));
            heapArr.set(i, temp);
            maxHeapify(1);
        }
    }


    /**
     * The function extracts the maximum element from a max heap and maintains the heap property.
     * 
     * @return The method is returning the maximum value from the heap.
     */
    @Override
    public V extractMax() throws exceptionThisDataStructureIsVoid{

        V highest = null;

        if(heapArr.size() >=2) {

            highest = heapArr.get(1).getNode();

            heapArr.set(1,heapArr.get(heapArr.size()-1));

            heapArr.remove(heapArr.size()-1);

            maxHeapify(1);

        }

        if(highest==null)
            throw new exceptionThisDataStructureIsVoid();

        return highest;
    }

    /**
     * The function increases the priority of a node in a max heap and then restores the max heap property.
     * 
     * @param node The node parameter represents the node whose priority needs to be increased.
     * @param newPriority The new priority value that you want to assign to the node.
     */
    @Override
    public void increaseKey(V node, int newPriority) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid {

        if(isEmpty())
            throw new exceptionThisDataStructureIsVoid();

        int index = -1;

        for (int i = 1; i < heapArr.size(); i++) {
            if(heapArr.get(i).getNode().equals(node))
                index = i;

        }

        if(index==-1)
            throw new exceptionTheObjectDoesntExist(node+"");

        if(newPriority > heapArr.get(index).getPriority()) {
            heapArr.get(index).setPriority(heapArr.get(index).getPriority()+newPriority);

            buildMaxHeapify();
        }
    }

    /**
     * The function searches for a specific node in an array and returns it if found.
     * 
     * @param node The parameter "node" is the value that you want to search for in the "arr" list.
     * @return The method is returning the node that matches the input node.
     */
    @Override
    public V search(V node) {

        V search = null;

        for (int i = 1; i < heapArr.size(); i++) {

            if(heapArr.get(i).getNode().equals(node))
                search = heapArr.get(i).getNode();

        }
        return search;
    }

    /**
     * The show() function returns a string representation of the priority and node values stored in an ArrayList.
     * 
     * @return The method is returning a string representation of the priority and node values stored in the `arr` list. Each priority and node value is concatenated with a space and a newline character.
     */
    @Override
    public String show() {
        String msg = "";
        for (int i = 1; i < heapArr.size() ; i++) {
            msg+= heapArr.get(i).getPriority() + " "+ heapArr.get(i).getNode() + "\n";
        }
        return msg;
    }

    /**
     * The showMax() function returns the maximum value in the arr list, or null if the list is empty.
     * 
     * @return The method is returning the maximum value in the "arr" list.
     */
    @Override
    public V showMax() {
        if(heapArr.size()>1)
            return heapArr.get(1).getNode();
        else
            return null;

    }

    /**
     * The `clone` function creates a deep copy of the current heap object.
     * 
     * @return The method is returning a clone of the Heap object.
     */
    @Override
    public Heap<V> clone() {
        Heap<V> clone = new Heap<>();
        for (int i = 1; i < heapArr.size(); i++) {
            clone.insert(heapArr.get(i).getPriority(), heapArr.get(i).getNode());
        }
        return clone;
    }

    /**
     * The size() function returns the number of elements in the arr list.
     * 
     * @return The size of the array.
     */
    public int size(){
        return heapArr.size();
    }

    /**
     * The function checks if the array is empty by comparing its size to 1.
     * 
     * @return The method is returning a boolean value, which indicates whether the "arr" list is empty or not. If the size of the list is 1, it means that the list only contains one element (which is not considered empty), so the method will return false. Otherwise, if the size of the list is greater than 1, it means that the list contains more than one element (which
     */
    public boolean isEmpty(){
        return heapArr.size() == 1;
    }

    /**
     * The toString() function iterates through an array and extracts the maximum value, appending it to a string message.
     * 
     * @return The method is returning a string representation of the elements in the "arr" list, with each element on a new line.
     */
    public String toString() {
        String msg = "";

        for (int i = 1; i < heapArr.size(); i++) {
            try {

                msg += extractMax() + "\n";

            } catch (exceptionThisDataStructureIsVoid e) {

                msg=e.getMessage();

            }
        }
        return msg;
    }

}
