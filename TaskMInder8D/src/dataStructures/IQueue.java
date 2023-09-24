package dataStructures;

import execptions.exceptionThisDataStructureIsVoid;

public interface IQueue <V>{

    public int size();
    public boolean isEmpty();
    public boolean offer(V value);
    public V poll() throws exceptionThisDataStructureIsVoid;
    public V front() throws exceptionThisDataStructureIsVoid;

    
}
