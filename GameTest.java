
/**
 * The test class GameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GameTest extends junit.framework.TestCase
{
    private Command command1;
    private Command command2;
    private Room room1;
    private Game game1;
    private Game game2;

    
    


    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {        
        game1 = new Game();
        game1.createRoomsPlayerAndItems();
        command1 = new Command("take", "ID");
        command2 = new Command("go", "down");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void testDefaultConstructor()
    {
        Room room1 = game1.getPlayer().getCurrentRoom();
        assertEquals("in the Holland Union Building, the middle of campus", room1.getDescription());
    }

    public void testPrintWelcome()
    {
        game1.printWelcome();
    }

    public void testPrintHelp()
    {
        game1.printHelp();
    }

    public void testProcessCommandNull()
    {
        assertEquals(false, game1.processCommand(null));
    }

    public void testProcessCommandHelp()
    {
        Command command3 = new Command("help", null);
        assertEquals(false, game1.processCommand(command3));
    }

    public void testProcessCommandGo()
    {
        Command command3 = new Command("go", "north");
        assertEquals(false, game1.processCommand(command3));
    }

    public void testProcessCommandQuit()
    {
        Command command3 = new Command("quit", null);
        assertEquals(true, game1.processCommand(command3));
    }

    public void testGoRoomNoDirection()
    {
        Command command3 = new Command("go", null);
        game1.goRoom(command3);
        Room room1 = game1.getPlayer().getCurrentRoom();
        assertEquals("in the Holland Union Building, the middle of campus", room1.getDescription());
    }   

    public void testGoRoomInvalidDirection()
    {
        Command command3 = new Command("go", "left");
        game1.goRoom(command3);
        Room room1 = game1.getPlayer().getCurrentRoom();
        //Command command2 = new Command("go", "left");
        assertEquals("in the Holland Union Building, the middle of campus", room1.getDescription());
    }

    public void testGoRoomValid()
    {
        Command command3 = new Command("go", "north");
        game1.goRoom(command3);
        Room room1 = game1.getPlayer().getCurrentRoom();
        assertEquals("in Denny Hall, the social sciences and anthropology building", room1.getDescription());
    }

    public void testQuit()
    {
        Command command3 = new Command("quit", null);
        assertEquals(true, game1.quit(command3));
    }

    public void testQuitError()
    {
        Command command3 = new Command("quit", "smoking");
        assertEquals(false, game1.quit(command3));
    }

    public  void TestTakeItem1() {
        Command command3 = new Command("take", "2");
        game1.take(command3);
        assertEquals(true, game1.getPlayer().checkItem("2"));
    }

    public void testTakeItemNoSecond() {
        Command command3 = new Command("take", null);   
        game1.take(command3);
    }
    
    public void testTakeNotInTheRoom() {
        Command command3 = new Command("take", "Key");
        game1.take(command3);
    }

    public void testTakeNoItem()
    {
        Command command1 = new Command("take", "ID");
        Command command3 = new Command("go", "north");
        game1.goRoom(command3);
        game1.take(command1);
    }

    public void testInventory()
    {
        Command command1 = new Command("take", "ID");
        game1.take(command1);
        game1.inventory();
    }

    public void testInventoryEmpty()
    {
        game1.inventory();
    }


    public void testDrop()
    {
        game1.take(command1);
        Command command3 = new Command("go", "down");
        Command command4 = new Command("take", "SocialScienceEssay");
        Command command5 = new Command("drop", "SocialScienceEssay");
        game1.goRoom(command3);
        game1.take(command4);
        game1.drop(command5);
        game1.inventory();
    }

    public void testDropNoSecond()
    {
        Command command3 = new Command("drop", null);
        game1.take(command1);
        game1.drop(command3);
        game1.inventory();
    }
}














