
/**
 * A item is placed in four rooms. The player has to pick up the items and use them to win.
 * 
 * @author (Younse & Tom) 
 * @version (4/30/2018)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String desc;
    private Room pickup;
    private Room use;

    /**
     * Constructor for objects of class Item
     */
    public Item(String initName, String itemDesc)
    {
        // initialise instance variables
        name = initName;
        desc = itemDesc;
    }

    /**
     * return the name of the item
     * @return name
     */
    public String getName(){
    
        // put your code here
        return name;
    }
    
//     /**
//      * return the pickup location of the item
//      * @return pickup
//      */
//     public Room getPickupLocation(){
//     
//         // put your code here
//         return pickup;
//     }
//     
//     /**
//      * return the location where the item will be used
//      * @return use
//      */
//     public Room getUsableLocation(){
//     
//         // put your code here
//         return use;
//     }
    
    /**
     * describe what the player can do with the item
     * @return desc
     */
    public String getDesc(){
        return desc;
    }
    
}
