package dataStructures;
import exceptions.exceptionThisDataStructureIsVoid;

public class Queue <V> implements IQueue< V>, Cloneable{

    public Node<V> first;
    public Node<V> last;
    public int size;

    /**The `public Queue()` is a constructor for the `Queue` class. It initializes the `first` and
    `last` nodes to `null` and sets the `size` of the queue to 0. This constructor is called when a
     new instance of the `Queue` class is created.*/
    public Queue(){
        first=null;
        last=null;
        size=0;
    }
    
    /**
     * The function checks if a linked list is empty by checking if the first node is null.
     * 
     * @return The method is returning a boolean value.
     */
    public boolean isEmpty(){
        return first==null;
    }
    
    /**
     * The function adds a node to the end of a linked list and returns true if the operation was
     * successful.
     * 
     * @param Value The value parameter represents the node that is being added to the linked list.
     * @return The method is returning a boolean value indicating whether the node was successfully
     * added to the linked list.
     */
    @Override
    public boolean offer(V Value){
        Node<V> value=new Node<>(Value);
        boolean added=false;
        if(isEmpty()){
            first=value;
            last=value;
            size++;
            added=true;
        }else{
            last.setNext(value);
            value.setPrev(last);
            last=value;
            size++;
            added=true;
        }
        return added;
    }



    /**
     * The poll() function removes and returns the value of the first element in a linked list.
     * 
     * @return The method is returning the value of the first element in the linked list.
     */
    public V poll() throws exceptionThisDataStructureIsVoid {
        V value=null;
        if(!isEmpty()){
            value=first.getValue();
            first=first.getNext();
            if(first!=null)
                first.setPrev(null);
            size--;
        }else{
            throw new exceptionThisDataStructureIsVoid();
        }
        return value;
    }

    /**
     * The above function returns the value of the first element in a data structure, or null if the
     * data structure is empty.
     * 
     * @return The method is returning the value of the first element in the data structure.
     */
    @Override
    public V front() throws exceptionThisDataStructureIsVoid {
        V value=null;
        if(!isEmpty()){
            value=first.getValue();
        }else{
            throw new exceptionThisDataStructureIsVoid();
        }
        return value;
    }
    /**
     * The function returns the size of an object.
     * 
     * @return The size of the object or collection.
     */
    public int size(){
        return size;
    }
    @Override
    public Queue<V> clone(){
        Queue<V> clone = new Queue<>();
        Node<V> current = first;

        while(current != null){
            clone.offer(current.getValue());
            current = current.getNext();
        }
        return clone;
    }


}
