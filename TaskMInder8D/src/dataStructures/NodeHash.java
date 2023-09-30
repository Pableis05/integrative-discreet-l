package dataStructures;

public class NodeHash<K extends Comparable<K>,V>  extends Node<V> implements  Cloneable{
    private K key;
    private NodeHash<K,V> next;
    private NodeHash<K,V> prev;

    /**The `public NodeHash(K key, V value)` constructor is initializing a new `NodeHash` object with the given
     key and value. It sets the `key` and `value` fields of the nodeHash to the provided values. It also
     sets the `next` and `prev` fields to `null`, indicating that this nodeHash is not currently linked
     to any other nodeHashs. */
    public NodeHash(K key, V value) {
        super(value);
        this.key = key;
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
    * The getNext() function returns the next nodeHash in a linked list.
    * 
    * @return The method is returning a NodeHash object with generic types K and V.
    */
    public NodeHash<K, V> getNext() {
        return next;
    }

    /**
     * The function sets the next nodeHash in a linked list.
     * 
     * @param next The "next" parameter is of type NodeHash<K, V>, which represents the next nodeHash in a
     * linked list.
     */
    public void setNext(NodeHash<K, V> next) {
        this.next = next;
    }

    /**
     * The getPrev() function returns the previous nodeHash in a linked list.
     * 
     * @return The method is returning a NodeHash object with generic types K and V.
     */
    public NodeHash<K, V> getPrev() {
        return prev;
    }

    /**
     * The function sets the previous nodeHash of a given nodeHash.
     * 
     * @param prev The "prev" parameter is of type NodeHash<K, V>, which represents the previous nodeHash in a
     * linked list.
     */
    public void setPrev(NodeHash<K, V> prev) {
        this.prev = prev;
    }


    public V getValue() {
        return super.getValue();
    }

    public K getKeyHash() {
        return key;
    }

    public void setValue(V value) {
        super.setValue(value);
    }



}
