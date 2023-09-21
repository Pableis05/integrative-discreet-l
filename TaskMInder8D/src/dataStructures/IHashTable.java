package dataStructures;

public interface IHashTable<K extends Comparable<K>,V> {

    public int hash(K key);
    public boolean add(K key, V value);
    public V search(K key);
    public boolean remove(K key);


}
