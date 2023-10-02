package dataStructures;

public class NodePriorityQueue <T> {
    private int priority;
    private T node;
    public NodePriorityQueue(int priority, T node){
        this.priority = priority;
        this.node = node;
    }

    public int getPriority(){
        return priority;
    }

    public T getNode(){
        return node;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }
    public void setNode(T node){
        this.node = node;
    }

    public T search(T node){return node;}

    public boolean delete(T node){return true;}

}
