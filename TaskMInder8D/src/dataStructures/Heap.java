package dataStructures;

import java.util.ArrayList;

public class Heap<V> implements IPriorityQueue<V>, Cloneable{

    private ArrayList<NodePriorityQueue<V>> arr;

    public Heap(){
        arr = new ArrayList<>();
        arr.add(null);
    }


    /**
     * The insert function adds a new node with a given priority to the priority queue and then reorganizes the queue to maintain the max heap property.
     * 
     * @param priority The priority of the node being inserted into the priority queue. This determines the order in which the nodes will be removed from the queue.
     * @param node The "node" parameter represents the element that you want to insert into the priority queue.
     */
    @Override
    public void insert(int priority, V node) {
        arr.add(new NodePriorityQueue(priority, node));
        buildMaxHeapify();
    }

    /**
     * The maxHeapify function is used to maintain the max heap property in a priority queue represented as an array.
     * 
     * @param index The index parameter represents the index of the element in the heap that needs to be heapified.
     */
    public void maxHeapify(int index) {
        int l = 2*index;
        int r = 2*index + 1;
        int largest;
        if(l <= arr.size()-1 && arr.get(l).getPriority() > arr.get(index).getPriority()) {
            largest = l;
        } else {
            largest = index;
        }
        if(r<= arr.size()-1 && arr.get(r).getPriority()  > arr.get(largest).getPriority() ) {
            largest = r;
        }
        if(largest != index) {
            NodePriorityQueue<V> temp1= arr.get(index);
            NodePriorityQueue<V> temp2= arr.get(largest);
            arr.set(index,temp2);
            arr.set(largest,temp1);
            maxHeapify(largest);
        }
    }
    /**
     * The function "buildMaxHeapify" builds a max heap by calling the "maxHeapify" function on each element of the array in reverse order.
     */
    public void buildMaxHeapify() {
        for(int i= arr.size()-1; i >=1;i--){
            maxHeapify(i);
        }
    }

    /**
     * The function performs a heap sort on an array by building a max heap and repeatedly swapping the root element with the last element and then heapifying the remaining elements.
     */
    public void heapSort() {
        buildMaxHeapify();

        for(int i = arr.size() - 1; i >= 1; i--) {
            NodePriorityQueue<V> temp = arr.get(1);
            arr.set(1, arr.get(i));
            arr.set(i, temp);
            maxHeapify(1);
        }
    }


    /**
     * The function extracts the maximum element from a max heap and maintains the heap property.
     * 
     * @return The method is returning the maximum value from the heap.
     */
    @Override
    public V extractMax() {
        V max=null;
        if(arr.size() >=2) {
            max = arr.get(1).getNode();
            arr.set(1,arr.get(arr.size()-1));
            arr.remove(arr.size()-1);
            maxHeapify(1);
        }
        return max;
    }

    /**
     * The function increases the priority of a node in a max heap and then restores the max heap property.
     * 
     * @param node The node parameter represents the node whose priority needs to be increased.
     * @param newPriority The new priority value that you want to assign to the node.
     */
    @Override
    public void increaseKey(V node, int newPriority) {
        int index = -1;
        for (int i = 1; i < arr.size(); i++) {
            if(arr.get(i).getNode().equals(node)){
                index = i;
            }
        }
        if(newPriority > arr.get(index).getPriority()) {
            arr.get(index).setPriority(arr.get(index).getPriority()+newPriority);
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
        V ans = null;
        for (int i = 1; i < arr.size(); i++) {
            if(arr.get(i).getNode().equals(node)){
                ans = arr.get(i).getNode();
            }
        }
        return ans;
    }


    public boolean delete(V node) {
        boolean ans = false;
        for (int i = 1; i < arr.size(); i++) {
            if(arr.get(i).getNode().equals(node)){
                arr.remove(i);
                ans = true;
            }
        }
        return ans;
    }

    /**
     * The show() function returns a string representation of the priority and node values stored in an ArrayList.
     * 
     * @return The method is returning a string representation of the priority and node values stored in the `arr` list. Each priority and node value is concatenated with a space and a newline character.
     */
    @Override
    public String show() {
        String ans = "";
        for (int i = 1; i < arr.size() ; i++) {
            ans+= arr.get(i).getPriority() + " "+ arr.get(i).getNode() + "\n";
        }
        return ans;
    }

    /**
     * The showMax() function returns the maximum value in the arr list, or null if the list is empty.
     * 
     * @return The method is returning the maximum value in the "arr" list.
     */
    @Override
    public V showMax() {
        if(arr.size()>1) {
            return arr.get(1).getNode();
        }else {
            return null;
        }
    }

    /**
     * The `clone` function creates a deep copy of the current heap object.
     * 
     * @return The method is returning a clone of the Heap object.
     */
    @Override
    public Heap<V> clone() {
        Heap<V> clone = new Heap<>();
        for (int i = 1; i < arr.size(); i++) {
            clone.insert(arr.get(i).getPriority(), arr.get(i).getNode());
        }
        return clone;
    }

    /**
     * The size() function returns the number of elements in the arr list.
     * 
     * @return The size of the array.
     */
    public int size(){
        return arr.size();
    }

    /**
     * The function checks if the array is empty by comparing its size to 1.
     * 
     * @return The method is returning a boolean value, which indicates whether the "arr" list is empty or not. If the size of the list is 1, it means that the list only contains one element (which is not considered empty), so the method will return false. Otherwise, if the size of the list is greater than 1, it means that the list contains more than one element (which
     */
    public boolean isEmpty(){
        return arr.size() == 1;
    }

    /**
     * The toString() function iterates through an array and extracts the maximum value, appending it to a string message.
     * 
     * @return The method is returning a string representation of the elements in the "arr" list, with each element on a new line.
     */
    public String toString(){
        String msg = "";
        for (int i = 1; i < arr.size(); i++) {
            msg += extractMax() + "\n";
        }
        return msg;
    }

}
