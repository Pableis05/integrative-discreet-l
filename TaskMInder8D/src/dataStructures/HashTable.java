package dataStructures;
import  exceptions.exceptionObjectAlredyExistWithThatKey;
import exceptions.exceptionThisDataStructureIsVoid;
import exceptions.exceptionTheObjectDoesntExist;
public class HashTable <K extends Comparable<K>, V> implements IHashTable<K, V> {

    private   NodeHash<K,V>[] table;
    private   int size;
    private String changeMessage;
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
        int hash=(int) Math.abs(Math.floor(key.hashCode())) % table.length;

        return hash;
    }

    /**
     * The function sets the capacity of a hash table by creating a new array of nodeHashs with the
     * specified capacity.
     * 
     * @param capacity The capacity parameter represents the desired capacity of the hash table. It
     * determines the number of buckets or slots that the hash table will have.
     */
    private void capacityHashTable(int capacity ){
        table = new NodeHash[capacity];
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
    public boolean add(K key, V value) throws exceptionObjectAlredyExistWithThatKey {

            boolean added = false;


            int hash = hash(key);
            NodeHash<K, V> newNodeHash = new NodeHash<>(key, value);
            if (table[hash] == null) {
                table[hash] = newNodeHash;
                size++;
                added = true;
            } else {
                NodeHash<K, V> current = table[hash];
                while (current.getNext() != null) {
                    if (current.getKey().equals(key)) {
                        throw new exceptionObjectAlredyExistWithThatKey(current.getValue()+"", key+"");
                    }
                    current = current.getNext();
                }
                current.setNext(newNodeHash);
                newNodeHash.setPrev(current);
                size++;
                added = true;
            }

        return added;

    }

    /**
     * The function retrieves the value associated with a given key in a data structure.
     * 
     * @param key The key parameter is the key of the entry that we want to retrieve the value for.
     * @return The value associated with the specified key.
     */
    public V search(K key) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist {
        V value = null;
        if(size==0){
            throw new exceptionThisDataStructureIsVoid();
        }
        if(getNodeHash(key)==null){
            throw new exceptionTheObjectDoesntExist(key+"");
        }
        value = getNodeHash(key).getValue();


        return value;
    }

    /**
     * The function `getNodeHash` returns the nodeHash with the specified key from a hash table.
     * 
     * @param key The key parameter is the key of the nodeHash that we are searching for in the hash table.
     * @return The method is returning a NodeHash object.
     */
    private NodeHash<K,V> getNodeHash(K key){
        NodeHash<K,V> nodeHash = null;

        if (key == null) {
            nodeHash = null;
        }
        int hash = hash(key);
        if (table[hash] == null) {
            nodeHash = null;
        } else {
            boolean isFound = false;
            NodeHash<K, V> current = table[hash];

            while (current != null && !isFound) {
                if (current.getKey().equals(key)) {
                    nodeHash = current;
                    isFound = true;
                }
                current = current.getNext();
            }
        }
        return nodeHash;
    }

    /**
     * The function removes a nodeHash with a given key from a hash table and returns true if successful.
     * 
     * @param key The key is the value that you want to remove from the data structure.
     * @return The method is returning a boolean value indicating whether the removal of the specified
     * key was successful or not.
     */
    public boolean remove(K key) throws exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        boolean removed = false;
        if(size==0){
            throw new exceptionThisDataStructureIsVoid();
        }

        int hash = hash(key);
        NodeHash<K, V> current =getNodeHash(key);

        if(current!=null){
            if(current==table[hash]){
                table[hash]=current.getNext();
            }else{
                NodeHash<K,V> prev = current.getPrev();
                NodeHash<K,V> next=current.getNext();
                if(prev==null && next!=null){
                    current=next;
                    current.setPrev(null);
                }else if(prev!=null && next==null) {
                    current = prev;
                    current.setNext(null);
                }else if(prev==null && next==null){
                    current=null;
                }else{
                    current=next;
                    current.setPrev(prev);
                    prev.setNext(current);
                }

            }
            size--;
            removed=true;
        }

        if (!removed) {
            throw new exceptionTheObjectDoesntExist(key+"");
        }
        return removed;

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public NodeHash<K, V>[] getArr() {
        return table;
    }

    public void setArr(NodeHash<K, V>[] arr) {
        this.table = arr;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }

    public String getChangeMessage() {
        return changeMessage;
    }

    public void modify(K key, V value) throws exceptionTheObjectDoesntExist {
        NodeHash<K, V> nodeHash = getNodeHash(key);
        if (nodeHash != null) {
            nodeHash.setValue(value);
        } else {
            throw new exceptionTheObjectDoesntExist(key+"");
        }
    }



}
