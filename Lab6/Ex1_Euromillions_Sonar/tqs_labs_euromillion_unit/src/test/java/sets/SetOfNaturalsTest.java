/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


    @Test
    void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");
        assertTrue(setC.intersects(setB), "intersection not reported as it was supposed to");
    }

    //------------ 2b
    @Test
    void testAddNotNaturalEl(){

        // must fail with exception
        assertThrows(IllegalArgumentException.class,() -> {setA.add(-1);});
    }

    @Test
    void testAddDuplicatedValue(){

        // must fail with exception
        assertThrows(IllegalArgumentException.class,() -> {setD.add(60);});
    }

    @Test
    void testSetSize() {
        assertEquals(0, setA.size(),"size: empty array but wrong set size returned");
    }

    @Test
    void testContains() {
        assertTrue(setD.contains(30),"contains: element not found in set");
        assertFalse(setD.contains(1),"contains: element found in set when not supposed to");
    }

    @Test
    void testAddDuplicateArray() {
        int[] elemArray = {2,50,2};
        assertThrows(IllegalArgumentException.class, () -> setA.add(elemArray));

        int[] elemArrayB = {40,60,2};
        assertThrows(IllegalArgumentException.class, () -> setB.add(elemArrayB));
    }

    //Check that you if you create a set from an array, if that array has duplicates an IllegalArgumentException is raised
    @Test
    void testNewSetFromArray() {
        int[] elemArray = {1,2,3,1};
        assertThrows(IllegalArgumentException.class, () -> SetOfNaturals.fromArray(elemArray));
    }


}
