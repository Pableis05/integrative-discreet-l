package dataStructures;

public interface IQueue <K extends Comparable<K>,V>{
    public int size();
    public boolean isEmpty();
    public boolean offer(K key, V value);
    public V poll();
    public V front();
}
