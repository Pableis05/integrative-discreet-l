package dataStructures;

public class NodePriorityQueue <T> {
    private int priority;
    private T node;
    public NodePriorityQueue(int priority, T node){
        this.priority = priority;
        this.node = node;
    }

    /**
     * The function returns the priority value.
     * 
     * @return The method is returning the value of the variable "priority".
     */
    public int getPriority(){
        return priority;
    }

    /**
     * The function returns the value of the node.
     * 
     * @return The method is returning a variable of type T, which is the type of the node.
     */
    public T getNode(){
        return node;
    }

    /**
     * The function sets the priority of an object.
     * 
     * @param priority The "priority" parameter is an integer value that represents the priority level of an object or task.
     */
    public void setPriority(int priority){
        this.priority = priority;
    }
    /**
     * The function sets the value of a node in a Java program.
     * 
     * @param node The parameter "node" is of type T, which means it can be any type of object.
     */
    public void setNode(T node){
        this.node = node;
    }

    /**
     * The function searches for a given node and returns it.
     * 
     * @param node The parameter "node" is of type T, which means it can be any type specified when the method is called. It represents the node that needs to be searched.
     * @return The method is returning the same node that is passed as an argument.
     */
    public T search(T node){return node;}

    /**
     * The function "delete" in Java returns a boolean value indicating whether the deletion of a node was successful.
     * 
     * @param node The parameter "node" is of type T, which means it can be any type of object.
     * @return The method is returning a boolean value, specifically "true".
     */
    public boolean delete(T node){return true;}

}
