

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RoomTest
{
    private Room room1;
    private Room room2;
    private Room room3;

    
    
    

    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        room1 = new Room("a lab");
        room2 = new Room("kitchen");
        room3 = new Room("bedroom");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void TestRoomConstructor()
    {
        Room room4 = new Room("bathroom");
        assertEquals("bathroom", room4.getDescription());
    }

    @Test
    public void TestSetGetExit1()
    {
        room1.setExit("north", room2);
        room1.setExit("east", room2);
        room1.setExit("south", room2);
        room1.setExit("west", room2);
        Room room4 = room1.getExit("north");
        assertEquals("kitchen", room4.getDescription());
        Room room5 = room1.getExit("east");
        assertEquals("kitchen", room4.getDescription());
        Room room6 = room1.getExit("south");
        assertEquals("kitchen", room4.getDescription());
        Room room7 = room1.getExit("west");
        assertEquals("kitchen", room4.getDescription());
    }

    @Test
    public void TestGetExitNull()
    {
        room1.setExit("north", room2);
        room2.setExit("east", room3);
        room2.getDescription();
        assertEquals(null, room2.getExit("west"));
    }

    @Test
    public void testGetExitString()
    {
        room1.setExit("north", room2);
        room1.setExit("east", room2);
        room1.setExit("south", room2);
        room1.setExit("west", room2);
        assertEquals("east south north west ", room1.getExitString());
    }

    @Test
    public void getExitString2()
    {
        room1.setExit("west", room2);
        assertEquals("west ", room1.getExitString());
    }

    @Test
    public void testGetExitStringEmpty()
    {
        assertEquals("", room1.getExitString());
    }

    @Test
    public void testGetLongDesc()
    {
        room1.setExit("west", room2);
        assertEquals("You are a lab with west  exit(s)", room1.getLongDescription());
    }

    @Test
    public void TestPlaceItems()
    {
        Item item1 = new Item("thing", "thing");
        room1.placeItems(item1);
        room1.getItem();
        Item item2 = room1.getItem();
        assertEquals("thing", item2.getName());
    }

    @Test
    public void TestRemoveItem()
    {
        Item item1 = new Item("thing", "thing");
        room1.placeItems(item1);
        room1.removeItem();
        room1.getItem();
    }

    @Test
    public void TestPlaceItemsInvalid()
    {
        Item item1 = new Item("thing", "thing");
        room1.placeItems(item1);
        room1.placeItems(item1);
    }

    @Test
    public void TestRemoveItemInvalid()
    {
        Item item1 = new Item("thing", "thing");
        room1.removeItem();
    }
}













