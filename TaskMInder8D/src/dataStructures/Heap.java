package dataStructures;

import java.util.ArrayList;

public class Heap<V> implements IPriorityQueue<V>, Cloneable{

    private ArrayList<NodePriorityQueue<V>> arr;

    public Heap(){
        arr = new ArrayList<>();
        arr.add(null);
    }


    @Override
    public void insert(int priority, V node) {
        arr.add(new NodePriorityQueue(priority, node));
        buildMaxHeapify();

    }

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
    public void buildMaxHeapify() {
        for(int i= arr.size()-1; i >=1;i--){
            maxHeapify(i);
        }
    }

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

    @Override
    public String show() {
        String ans = "";
        for (int i = 1; i < arr.size() ; i++) {
            ans+= arr.get(i).getPriority() + " "+ arr.get(i).getNode() + "\n";
        }
        return ans;
    }

    @Override
    public V showMax() {
        if(arr.size()>1) {
            return arr.get(1).getNode();
        }else {
            return null;
        }
    }
    @Override
    public Heap<V> clone() {
        Heap<V> clone = new Heap<>();
        for (int i = 0; i < arr.size(); i++) {
            NodePriorityQueue<V> node = new NodePriorityQueue<>(arr.get(i).getPriority(), arr.get(i).getNode());
            clone.arr.add(node);
        }
        return clone;
    }
}
