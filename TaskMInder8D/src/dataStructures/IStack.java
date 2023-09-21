package dataStructures;

public interface IStack<K extends Comparable<K>,V> {

    public int size();
    public boolean isEmpty();
    public boolean push(K key, V value);
    public V pop();
    public V top();


}
