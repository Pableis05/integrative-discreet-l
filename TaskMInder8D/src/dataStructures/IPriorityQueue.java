package dataStructures;

public interface IPriorityQueue<K> {

    public void insert(int priority, K node);
    public K extractMax();

    public void increaseKey(K node, int newPriority);

    public String show();

    public K showMax();

}
