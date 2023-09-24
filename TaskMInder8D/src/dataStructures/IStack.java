package dataStructures;

import execptions.exceptionThisDataStructureIsVoid;

public interface IStack<V> {

    public int size();
    public boolean isEmpty();
    public boolean push(V value);
    public V pop() throws exceptionThisDataStructureIsVoid;
    public V top() throws exceptionThisDataStructureIsVoid, exceptionThisDataStructureIsVoid;

}
