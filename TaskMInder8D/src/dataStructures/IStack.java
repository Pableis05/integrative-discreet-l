package dataStructures;

public interface IStack<K extends Comparable<K>,V> {

    public int size();
    public boolean isEmpty();
    public void push(Node<K,V> value);
    public V pop();
    public V top();


}
