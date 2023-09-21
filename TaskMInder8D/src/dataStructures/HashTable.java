package dataStructures;

public class HashTable <K extends Comparable<K>, V> implements IHashTable<K, V> {

    private   Node<K,V>[] table;
    private   int size;

    
    public HashTable(int capacity){
        capacityHashTable(capacity);
        size=0;
    }

   /**
    * The hash function takes a key and returns the index where the key should be stored in a hash
    * table.
    * 
    * @param key The key parameter is of type K, which represents the type of the key that will be used
    * for hashing.
    * @return The method is returning the hash value of the given key.
    */
    @Override
    public int hash(K key) {
        int hash= key.hashCode() % table.length;
        return hash;
    }

    /**
     * The function sets the capacity of a hash table by creating a new array of nodes with the
     * specified capacity.
     * 
     * @param capacity The capacity parameter represents the desired capacity of the hash table. It
     * determines the number of buckets or slots that the hash table will have.
     */
    private void capacityHashTable(int capacity ){
        table = new Node[capacity];
    }

    /**
     * The function adds a key-value pair to a hash table, updating the value if the key already
     * exists.
     * 
     * @param key The key is the unique identifier for the value being added to the data structure. It
     * is used to determine the position of the value in the underlying data structure (e.g., an array
     * or a linked list).
     * @param value The value parameter is the value associated with the key that we want to add to the
     * hash table.
     * @return The method is returning a boolean value indicating whether the key-value pair was
     * successfully added to the hash table.
     */
    public boolean add(K key, V value) {
        boolean added = false;


        int hash = hash(key);
        Node<K, V> newNode = new Node<>(key, value);
        if (table[hash] == null) {
            table[hash] = newNode;
            size++;
            added= true;
        } else {
            Node<K, V> current = table[hash];
            while (current.getNext() != null ) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    added= true;
                }
                current = current.getNext();
            }

            current.setNext(newNode);
            newNode.setPrev(current);
            size++;
            added= true;
        }
        return added;
    }

    /**
     * The function retrieves the value associated with a given key in a data structure.
     * 
     * @param key The key parameter is the key of the entry that we want to retrieve the value for.
     * @return The value associated with the specified key.
     */
    public V get(K key) {
        V value = null;
        value = getNode(key).getValue();
        return value;
    }

    /**
     * The function `getNode` returns the node with the specified key from a hash table.
     * 
     * @param key The key parameter is the key of the node that we are searching for in the hash table.
     * @return The method is returning a Node object.
     */
    private Node<K,V> getNode(K key){
        Node<K,V> node = null;

        if (key == null) {
            node = null;
        }
        int hash = hash(key);
        if (table[hash] == null) {
            node = null;
        } else {
            boolean isFound = false;
            Node<K, V> current = table[hash];

            while (current != null && !isFound) {
                if (current.getKey().equals(key)) {
                    node = current;
                    isFound = true;
                }
                current = current.getNext();
            }
        }
        return node;
    }

    /**
     * The function removes a node with a given key from a hash table and returns true if successful.
     * 
     * @param key The key is the value that you want to remove from the data structure.
     * @return The method is returning a boolean value indicating whether the removal of the specified
     * key was successful or not.
     */
    public boolean remove(K key){
        boolean removed = false;

        int hash = hash(key);
        Node<K, V> current =getNode(key);
        if(current!=null){
            if(current.getPrev()==null){
                table[hash]=current.getNext();
                current.getNext().setPrev(null);
                size--;
                removed=true;
            }else if(current.getNext()==null){
                current.getPrev().setNext(null);
                size--;
                removed=true;
            }else{
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                size--;
                removed=true;
            }
        }
        return removed;

    }


}
