package dataStructures;

public class Node<V> {

    protected V value;
    private Node<V> next;
    private Node<V> prev;


    public Node(V value) {
        this.value = value;
        next = null;
        prev = null;
    }

    /**
     * The function returns the value stored in a variable.
     *
     * @return The method is returning the value of type V.
     */
    public V getValue() {
        return value;
    }

    /**
     * The function sets the value of a variable.
     *
     * @param value The parameter "value" is of type V, which means it can be any type of object.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * The getNext() function returns the next node in a linked list.
     *
     * @return The method is returning a Node object with generic types K and V.
     */
    public Node< V> getNext() {
        return next;
    }

    /**
     * The function sets the next node in a linked list.
     *
     * @param next The "next" parameter is of type Node< V>, which represents the next node in a
     * linked list.
     */
    public void setNext(Node< V> next) {
        this.next = next;
    }

    /**
     * The getPrev() function returns the previous node in a linked list.
     *
     * @return The method is returning a Node object with generic types K and V.
     */
    public Node<V> getPrev() {
        return prev;
    }

    /**
     * The function sets the previous node of a given node.
     *
     * @param prev The "prev" parameter is of type Node< V>, which represents the previous node in a
     * linked list.
     */
    public void setPrev(Node< V> prev) {
        this.prev = prev;
    }
}





