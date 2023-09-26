package dataStructures;

import exceptions.exceptionObjectAlredyExistWithThatKey;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;

public interface IHashTable<K extends Comparable<K>,V> {

    public int hash(K key);
    public boolean add(K key, V value) throws exceptionObjectAlredyExistWithThatKey;
    public V search(K key) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist;
    public boolean remove(K key) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist;

}
