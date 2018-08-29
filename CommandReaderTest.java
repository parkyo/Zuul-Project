

/**
 * The test class CommandReaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandReaderTest extends junit.framework.TestCase
{
	private CommandReader commandR1;

    /**
     * Default constructor for test class CommandReaderTest
     */
    public CommandReaderTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		commandR1 = new CommandReader();
	}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	public void testConstructor()
	{
		assertEquals("", commandR1.allCommands());
	}

	public void testAddAndIsCommand()
	{
		commandR1.addCommand("go");
		commandR1.addCommand("quit");
		commandR1.addCommand("help");
		assertEquals(true, commandR1.isCommand("help"));
		assertEquals(true, commandR1.isCommand("quit"));
		assertEquals(true, commandR1.isCommand("go"));
	}

	public void testIsCommandFalse()
	{
		commandR1.addCommand("go");
		commandR1.addCommand("quit");
		commandR1.addCommand("help");
		assertEquals(false, commandR1.isCommand("sleepy"));
	}

	public void testIsCommandEmpty()
	{
		assertEquals(false, commandR1.isCommand("quit"));
	}

	public void testAllCommands()
	{
		commandR1.addCommand("go");
		commandR1.addCommand("quit");
		commandR1.addCommand("help");
		assertEquals("go quit help ", commandR1.allCommands());
	}

	public void testAllCommandsEmpty()
	{
		assertEquals("", commandR1.allCommands());
	}
}





