package dataStructures;

import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;

public interface IPriorityQueue<K> {

    public void insert(int priority, K node);
    public K extractMax() throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid;

    public void increaseKey(K node, int newPriority) throws exceptionTheObjectDoesntExist, exceptionThisDataStructureIsVoid;

    K search(K node);

    public String show();

    public K showMax();

    public void heapSort();


}
