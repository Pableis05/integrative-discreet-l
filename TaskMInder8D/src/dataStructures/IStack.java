package dataStructures;

public interface IStack<V> {

    public int size();
    public boolean isEmpty();
    public void push(Node<V> value);
    public V pop();
    public V top();

}
