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
         hashTable = new HashTable<>(10);

            hashTable.add(0.3, "Hola");
            hashTable.add(0.4, "Juan");
            hashTable.add(2.0, "Monitor");
            hashTable.add(2.4, "Lau");
            hashTable.add(4.5, ":D");
            hashTable.add(6.5, "Vaina");
            hashTable.add(8.5, "TQM");

    }

    private void setupStageWithColosion()throws exceptionObjectAlredyExistWithThatKey {
        setupStage2();
        hashTable.add(1.6, "Alejadro Magno");
        hashTable.add(14.12, "Aurelio");
        hashTable.add(1.37,"Aristizabal Lord of the Night");

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
            setupStageWithColosion();
            boolean added;
            added = hashTable.add(0.36, "Marco");
            assertEquals("The nodes haves the same positon",hashTable.hash(0.36),hashTable.hash(1.6),hashTable.hash(14.12));
            assertEquals("The nodes haves the same positon",hashTable.hash(0.36),hashTable.hash(1.37));
            assertTrue(added);
        }

        @Test
        public void addObjectWithCollisionAndKeyAlreadyExist () throws exceptionObjectAlredyExistWithThatKey {
                setupStage2();
                hashTable.add(0.4, "Alejadro Magno");
                try {
                    hashTable.add(0.4, "MonitorGod");
                    fail();
                } catch (exceptionObjectAlredyExistWithThatKey e) {
                    assertNotNull("Exception object with the same key that other",e);
                }
        }
        @Test
        public void searchObjectInListVoid() {
            setupStage1();
            try {
                hashTable.search(0.3);
                fail();
            } catch (exceptionThisDataStructureIsVoid e) {
                assertNotNull("Exception list is void",e);
            } catch (exceptionTheObjectDoesntExist e) {
                throw new RuntimeException(e);
            }

        }

    @Test
    public void searchObjectInList() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist{
        setupStage2();
        String value = hashTable.search(8.5);
        assertEquals("TQM",value);
    }
    @Test
    public void searchObjectInListWithCollision() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStageWithColosion();
        String value = hashTable.search(14.12);
        assertEquals("Aurelio",value);
    }
    @Test
    public void searchObjectDoesntExistInList() throws exceptionObjectAlredyExistWithThatKey {
        setupStage2();
        try {
            hashTable.search(0.26);
            fail();
        } catch (exceptionTheObjectDoesntExist e) {
            assertNotNull("Exception object doesnt exist",e);
        } catch (exceptionThisDataStructureIsVoid e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void removeObjectInListVoid() {
        setupStage1();
        try {
            hashTable.remove(0.3);
            fail();
        } catch (exceptionThisDataStructureIsVoid e) {
            assertNotNull("Exception list is void",e);
        } catch (exceptionTheObjectDoesntExist e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void removeObjectDoesntExistInList() throws exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStage2();
        try {
            hashTable.remove(0.26);
            fail();
        } catch (exceptionTheObjectDoesntExist e) {
            assertNotNull("Exception object doesnt exist",e);
        } catch (exceptionThisDataStructureIsVoid e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void removeObjectInList() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStage2();
        boolean removed = hashTable.remove(0.3);
        assertTrue(removed);
    }
    @Test
    public void removeObjectInListWithCollision() throws exceptionThisDataStructureIsVoid, exceptionObjectAlredyExistWithThatKey, exceptionTheObjectDoesntExist {
        setupStageWithColosion();
        assertEquals("The nodes haves the same positon",hashTable.hash(0.36),hashTable.hash(1.6),hashTable.hash(14.12));
        assertEquals("The nodes haves the same positon",hashTable.hash(0.36),hashTable.hash(1.37));
        hashTable.add(0.36, "Marco");
        boolean removed = hashTable.remove(14.12); //Remove->"Aurelio"
        assertTrue("The object was removed",removed);
    }

    @Test
    public void listIsEmpty() throws exceptionObjectAlredyExistWithThatKey, exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist {
        setupStage2();
        hashTable.remove(0.3);
        hashTable.remove(0.4);
        hashTable.remove(2.0);
        hashTable.remove(2.4);
        hashTable.remove(4.5);
        hashTable.remove(6.5);
        hashTable.remove(8.5);
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void currectlyClone() throws exceptionObjectAlredyExistWithThatKey, exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist {
        setupStage2();
        HashTable<Double,String> clone = hashTable.clone();
        assertNotEquals("The reference to the object must be different: ",hashTable,clone);
        assertEquals("The size of the object must be the same: ",hashTable.getSize(),clone.getSize());
        assertEquals("The keys must return the same elements",hashTable.search(0.3),clone.search(0.3));
        assertEquals("The keys must return the same elements",hashTable.search(0.4),clone.search(0.4));
        assertEquals("The keys must return the same elements",hashTable.search(2.0),clone.search(2.0));
        assertEquals("The keys must return the same elements",hashTable.search(2.4),clone.search(2.4));
        assertEquals("The keys must return the same elements",hashTable.search(4.5),clone.search(4.5));
        assertEquals("The keys must return the same elements",hashTable.search(6.5),clone.search(6.5));
        assertEquals("The keys must return the same elements",hashTable.search(8.5),clone.search(8.5));
    }

    @Test
    public void differenteObjectReferenceOnClonedNodes() throws exceptionObjectAlredyExistWithThatKey, exceptionThisDataStructureIsVoid, exceptionTheObjectDoesntExist{
        setupStage2();
        System.out.println("Este medoto se comprueba mediante la eliminacion de nodos, es decir si se elimina el nodo original el clonado debe seguir existiendo ya que no apunta al mismo objeto porque son idependientes");
        HashTable<Double,String> clone = hashTable.clone();
        hashTable.remove(0.3);
        try {
            hashTable.search(0.3);
            fail();
        } catch (exceptionTheObjectDoesntExist e) {
            assertNotNull("Exception object doesnt exist",e);
        } catch (exceptionThisDataStructureIsVoid e) {
            throw new RuntimeException(e);
        }

        assertEquals("The key must return the same elements",clone.search(0.3),"Hola");

        clone.add(-30.0,"Hi my bro");
        try {
            hashTable.search(-30.0);
            fail();
        } catch (exceptionTheObjectDoesntExist e) {
            assertNotNull("Exception object doesnt exist",e);
        } catch (exceptionThisDataStructureIsVoid e) {
            throw new RuntimeException(e);
        }

    }



}
