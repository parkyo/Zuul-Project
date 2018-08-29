

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest extends junit.framework.TestCase
{
    private Player thePlayer;
    private Item item1;
    private Item item2;
    private Item item3;


    
    
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        Room outside;
        Room theatre;
        Room pub;
        Room lab;
        Room office;
        Room hub;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        hub = new Room("in the HUB");
        
        // initialise room exits
        outside.setExit("north", hub);
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theatre.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        hub.setExit("south", outside);
                                                                                
        thePlayer = new Player("Zed", outside);
        item1 = new Item("thing1", "11");
        item2 = new Item("thing2", "22");
        item3 = new Item("thing3", "33");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    public void testConstructor() {
        assertEquals(thePlayer.getName(), "Zed");
        assertEquals(thePlayer.getCurrentRoom().getDescription(),
                     "outside the main entrance of the university");
    }
    
	public void testGoRoomNorth()
	{
		thePlayer.goRoom("north");
		Room room1 = thePlayer.getCurrentRoom();
		assertEquals("in the HUB", room1.getDescription());
	}

	public void testGoRoomEast()
	{
		thePlayer.goRoom("east");
		Room room1 = thePlayer.getCurrentRoom();
		assertEquals("in a lecture theatre", room1.getDescription());
	}

	public void testGoRoomSouth()
	{
		thePlayer.goRoom("south");
		Room room1 = thePlayer.getCurrentRoom();
		assertEquals("in a computing lab", room1.getDescription());
	}

	public void testGoRoomWest()
	{
		thePlayer.goRoom("west");
		Room room1 = thePlayer.getCurrentRoom();
		assertEquals("in the campus pub", room1.getDescription());
	}

	public void testGoRoomInvalid()
	{
		thePlayer.goRoom("left");
		Room room1 = thePlayer.getCurrentRoom();
		assertEquals("outside the main entrance of the university",
		             room1.getDescription());
	}    

    public void testCheckItemFalse()
    {
        thePlayer.pickUpItem(item1);
        assertEquals(false, thePlayer.checkItem("thing3"));
    }

    public void testRemoveItemElse()
    {
        thePlayer.pickUpItem(item3);
        thePlayer.removeItem(item1);
    }
    
    public void testPrintItemList()
    { 
        thePlayer.printItemList();
    }

    public void testPickupEmptyRoom()
    {
        Room room1 = thePlayer.getCurrentRoom();
        room1.removeItem();
        thePlayer.pickUpItem(item1);
    }

    public void testPickupAlreadyThere()
    {
        thePlayer.pickUpItem(item1);
        Room room1 = thePlayer.getCurrentRoom();
        room1.placeItems(item1);
        thePlayer.pickUpItem(item1);
        thePlayer.pickUpItem(item1);
    }

    public void testPickupNoSpecific()
    {
        Room room1 = thePlayer.getCurrentRoom();
        room1.placeItems(item2);
        thePlayer.pickUpItem(item1);
    }

    public void testCheckItem()
    {
        thePlayer.pickUpItem(item1);
        Room room1 = thePlayer.getCurrentRoom();
        room1.placeItems(item1);
        thePlayer.pickUpItem(item1);
        assertEquals(true, thePlayer.checkItem("thing1"));
    }

    public void testRemoveItem()
    {
        Room room1 = thePlayer.getCurrentRoom();
        room1.placeItems(item1);
        room1.placeItems(item2);
        thePlayer.pickUpItem(item1);
        thePlayer.pickUpItem(item2);
        thePlayer.removeItem(item1);
        assertEquals(false, thePlayer.checkItem("thing1"));
        assertEquals(true, thePlayer.checkItem("thing2"));
    }
}










