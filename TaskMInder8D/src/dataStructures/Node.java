package dataStructures;

public class Node<K extends Comparable<K>,V> {
    private K key;
    private V value;
    private Node<K,V> next;
    private Node<K,V> prev;

    /**The `public Node(K key, V value)` constructor is initializing a new `Node` object with the given
     key and value. It sets the `key` and `value` fields of the node to the provided values. It also
     sets the `next` and `prev` fields to `null`, indicating that this node is not currently linked
     to any other nodes. */
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
        prev = null;
    }

   /**
    * The function returns the key value.
    * 
    * @return The method is returning the value of the variable "key".
    */
    public K getKey() {
        return key;
    }

    /**
     * The function sets the value of the "key" variable.
     * 
     * @param key The "key" parameter is of type K, which means it can be any type specified by the
     * user when calling the method.
     */
    public void setKey(K key) {
        this.key = key;
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
    public Node<K, V> getNext() {
        return next;
    }

    /**
     * The function sets the next node in a linked list.
     * 
     * @param next The "next" parameter is of type Node<K, V>, which represents the next node in a
     * linked list.
     */
    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    /**
     * The getPrev() function returns the previous node in a linked list.
     * 
     * @return The method is returning a Node object with generic types K and V.
     */
    public Node<K, V> getPrev() {
        return prev;
    }

    /**
     * The function sets the previous node of a given node.
     * 
     * @param prev The "prev" parameter is of type Node<K, V>, which represents the previous node in a
     * linked list.
     */
    public void setPrev(Node<K, V> prev) {
        this.prev = prev;
    }
}
