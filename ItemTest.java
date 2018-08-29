

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ItemTest
{
    private Item item1;

    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
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
        item1 = new Item("thing", "a thing you use");
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
    public void TestInitializeItem()
    {
        assertEquals("a thing you use", item1.getDesc());
        assertEquals("thing", item1.getName());
    }
}

