package test.dataStructureTest;
import dataStructures.HashTable;
import exceptions.exceptionObjectAlredyExistWithThatKey;
import exceptions.exceptionTheObjectDoesntExist;
import exceptions.exceptionThisDataStructureIsVoid;
import org.junit.Test;
import static org.junit.Assert.*;


public class HashTableTest {
    private HashTable<Double,String > hashTable;

    private void setupStage1(){
        hashTable = new HashTable<Double,String >(10);
    }

    private void setupStage2()throws exceptionObjectAlredyExistWithThatKey {
        {
            hashTable = new HashTable<>(10);
            hashTable.add(0.3, "Hola");
            hashTable.add(0.4, "Juan");
            hashTable.add(2.0, "Monitor");
            hashTable.add(2.4, "Lau");
            hashTable.add(4.5, ":D");
            hashTable.add(6.5, "Vaina");
            hashTable.add(8.5, "TQM");
        }
    }

        @Test
        public void addObjectWithKeyBMajorThanCapacity() throws exceptionObjectAlredyExistWithThatKey{
            setupStage1();
            boolean added;
            added = hashTable.add(11.0, "Hola");

            assertTrue(added);
        }
        @Test
        public void addObjectWithCollision () throws exceptionObjectAlredyExistWithThatKey {

                setupStage2();
                hashTable.add(0.37, "Alejadro Magno");
                boolean added;
                added = hashTable.add(0.35, "Marco");
                assertTrue(added);

        }

        @Test
        public void addObjectWithCollisionAndKeyAlreadyExist () throws exceptionObjectAlredyExistWithThatKey {
                setupStage2();
                hashTable.add(0.3, "Alejadro Magno");
                assertThrows("Exception object with the same key that other",exceptionObjectAlredyExistWithThatKey.class, () ->
                    hashTable.add(0.3, "MonitorGod")
                );

        }
        @Test
        public void searchObjectInListVoid() {
            setupStage1();
            assertThrows("Exception list is void", exceptionThisDataStructureIsVoid.class, () ->
                    hashTable.search(0.3)
            );
        }

    @Test
    public void searchObjectInList() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist{
        setupStage2();
        String value = hashTable.search(0.3);
        assertEquals("Hola",value);
    }
    @Test
    public void searchObjectInListWithCollision() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStage2();
        hashTable.add(0.39, "Marco Aurelio");
        hashTable.add(0.37, "Alejadro Magno");
        String value = hashTable.search(0.37);
        assertEquals("Alejadro Magno",value);
    }
    @Test
    public void searchObjectDoesntExistInList() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey,  exceptionTheObjectDoesntExist {
        setupStage2();
        assertThrows("Exception object doesnt exist",exceptionTheObjectDoesntExist.class, () ->
                hashTable.search(0.26)
        );
    }

    @Test
    public void removeObjectInListVoid() {
        setupStage1();
        assertThrows("Exception list is void", exceptionThisDataStructureIsVoid.class, () ->
                hashTable.remove(0.3)
        );
    }
    @Test
    public void removeObjectDoesntExistInList() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStage2();
        assertThrows("Exception object doesnt exist",exceptionTheObjectDoesntExist.class, () ->
                hashTable.remove(0.38)
        );
    }

    @Test
    public void removeObjectInList() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStage2();
        boolean removed = hashTable.remove(0.3);
        assertTrue(removed);
    }
    @Test
    public void removeObjectInListWithCollision() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStage2();
        hashTable.add(0.39, "Marco Aurelio");
        hashTable.add(0.37, "Alejadro Magno");
        boolean removed = hashTable.remove(0.37);


        assertTrue(removed);
    }

    @Test
    public void listIsEmpty() throws exceptionObjectAlredyExistWithThatKey, exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist {
        setupStage1();
        hashTable.add(0.3, "Hola");
        hashTable.add(0.4, "buen");
        hashTable.add(2.0, "Monitor");
        hashTable.remove(0.3);
        hashTable.remove(0.4);
        hashTable.remove(2.0);

        assertTrue(hashTable.isEmpty());
    }

}
