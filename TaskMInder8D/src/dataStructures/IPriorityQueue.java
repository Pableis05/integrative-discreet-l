package dataStructures;

public interface IPriorityQueue<K> {

    public void insert(int priority, K node);
    public K extractMax();

    public void increaseKey(K node, int newPriority);

    K search(K node);

    boolean delete(K node);

    public String show();

    public K showMax();

    public Heap<K> clone();

    public void heapSort();


}
