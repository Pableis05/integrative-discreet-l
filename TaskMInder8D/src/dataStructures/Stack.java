package dataStructures;

import exceptions.exceptionThisDataStructureIsVoid;

public class Stack <V> implements IStack<V>, Cloneable {

    private Node<V> head;
    private int size;

    /**  The `public Stack()` constructor is initializing the `head` variable to `null` and the `size`
     variable to `0`. This means that when a new `Stack` object is created, it starts with an empty
     stack (no elements) and the `head` is set to `null`*/
    public Stack(){
        head=null;
        size=0;
    }

    /**
     * The function checks if the head of a linked list is null, indicating that the list is empty.
     * 
     * @return The method is returning a boolean value.
     */
    public boolean isEmpty(){
        return head==null;
    }

    /**
     * The push function adds a new node to the beginning of a linked list.
     * 
     * @param Value The value to be pushed onto the stack. It is of type Node<K,V>, where K is the key
     * type and V is the value type.
     */
    public boolean push(V Value){
        boolean added=false;
        Node<V> value=new Node<>(Value);
        if(isEmpty()){
            head=value;
            size++;
            added=true;
        }else{
            value.setNext(head);
            head=value;
            size++;
            added=true;
            
        }
        return added;
    }

    /**
     * The top() function returns the value of the head node in a linked list.
     * 
     * @return The method is returning the value of the head node in the linked list.
     */
    public V top() throws exceptionThisDataStructureIsVoid {
        if(head==null){
            throw new exceptionThisDataStructureIsVoid();
        }
        return head.getValue();

    }

    /**
     * The pop() function removes and returns the value at the head of a doubly linked list.
     * 
     * @return The method is returning the value of the element that is being removed from the head of
     * the linked list.
     */
    public V pop() throws exceptionThisDataStructureIsVoid{
        V value=null;
        if(!isEmpty() ){
            size--;
            value=head.getValue();
            head=head.getNext();
            if(head!=null)
                head.setPrev(null);
        }
        else{
            throw new exceptionThisDataStructureIsVoid();
        }
        return value;
    }

    /**
     * The function returns the size of a data structure.
     * 
     * @return The size of the object.
     */
    @Override
    public int size() {
        return size;
    }

    public Stack<V> clone(){
        Stack<V> clone=new Stack<>();
        Stack<V> temp=new Stack<>();
        Node<V> current=head;
        while(current!=null){
            temp.push(current.getValue());
            current=current.getNext();
        }
        while(!temp.isEmpty()){
            try {
                clone.push(temp.pop());
            } catch (exceptionThisDataStructureIsVoid e) {
                throw new RuntimeException(e);
            }
        }
        return clone;
    }
}
