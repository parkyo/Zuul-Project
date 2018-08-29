

/**
 * The test class CommandTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CommandTest
     */
    public CommandTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
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
		Command command1 = new Command("go", "east");
		assertEquals("go", command1.getCommandWord());
		assertEquals("east", command1.getSecondWord());
	}

	public void testIsUnknownTrue()
	{
		Command command1 = new Command(null, null);
		assertEquals(true, command1.isUnknown());
	}

	public void testIsUnknownFalse()
	{
		Command command1 = new Command("go", "east");
		assertEquals(false, command1.isUnknown());
	}

	public void testHasSecondWordTrue()
	{
		Command command1 = new Command("go", "east");
		assertEquals(true, command1.hasSecondWord());
	}
	
	public void testHasSecondWordFalse()
	{
		Command command1 = new Command("look", null);
		assertEquals(false, command1.hasSecondWord());
	}
}



