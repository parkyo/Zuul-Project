import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 3
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author (YOUR NAME HERE)
 * @version 1.0 (February 2002)
 */

public class Room 
{
    private String description; //description of the room
    private Item usableItem; //item utilizable that exists in a room.

    //     private Room northExit; //exit to the north 
    //     private Room southExit; //exit to the south
    //     private Room eastExit;  //exit to the east
    //     private Room westExit;  //exit to the west
    //     private Room upExit; //exit upwards
    //     private Room downExit; //exit downwards

    //defined constants for exit directions
    //     public static final Room EXIT_SLIDELEFT = tome;
    //     public static final Room EXIT_SLIDERIGHT = hub;
    //     public static final Room EXIT_TRAMPOLINEUP = denny;
    //     public static final Room EXIT_TRAMPOLINEDOWN = hub;
    //     public static final Room EXIT_ = "up";
    //     public static final Room EXIT_DOWN = "down";
    //     
    private HashMap<String,Room>roomList;
    //     private HashMap<Room,Item>itemList;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param rmdesc the description of the room
     */
    public Room(String rmdesc) 
    {
        description = rmdesc;
        roomList = new HashMap<String,Room>();
        usableItem = null;
        //         itemList = new HashMap<Room,Item>();
    }

    /**
     * Define one exit of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction the direction of the exit
     * @param exit the Room that lies in that direction
     */
    public void setExit(String direction, Room exit) 
    {
        roomList.put(direction, exit);
        //         if (direction.equals("north")) {
        //             northExit = exit;
        //         } 
        //         else if (direction.equals("east")) {
        //             eastExit = exit;
        //         } 
        //         else if (direction.equals("south")) {
        //             southExit = exit;
        //         } 
        //         else if (direction.equals("west")) {
        //             westExit = exit;
        //         } else if (direction.equals("up")) {
        //             upExit = exit;
        //         } else if ( direction.equals("down")) {
        //             downExit = exit;
        //         }
    }

    /**
     * Return the room that lies in the indicated direction, or null if there
     * is no exit in that direction.
     * @param direction the direction of the exit
     * @return the Room that lies in that direction
     */
    public Room getExit(String direction) {
        return roomList.get(direction);
        //         if (direction.equals("north")) {
        //             return northExit;
        //         } 
        //         else if (direction.equals("east")) {
        //             return eastExit;
        //         } 
        //         else if (direction.equals("south")) {
        //             return southExit;
        //         } 
        //         else if (direction.equals("west")) {
        //             return westExit;
        //         } 
        //         else if (direction.equals("up")) {
        //             return upExit;
        //         } 
        //         else if (direction.equals("down")) {
        //             return downExit;        
        //         }
        //         else {
        //             return null;
        //         }
    }

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * return a String listing the exits from the room
     * @return exits the String that lists the exits
     */
    public String getExitString(){
        String exits = "";

        Set<String> keys = roomList.keySet();
        for (String direction : keys){
            System.out.println(direction);
            exits = exits + direction + " ";
            //         String exits = "";
            //         if (getExit(Room.EXIT_NORTH) != null){
            //             exits = "north ";
            //         }
            //         if (getExit(Room.EXIT_EAST) != null) {
            //             exits = exits + "east ";
            //         }
            //         if (getExit(Room.EXIT_SOUTH) != null) {
            //             exits = exits + "south ";
            //         }
            //         if (getExit(Room.EXIT_WEST) != null) {
            //             exits = exits + "west ";
            //         }
            //         if (getExit(Room.EXIT_UP) != null) {
            //             exits = exits + "up";
            //         }
            //         if (getExit(Room.EXIT_DOWN) != null) {
            //             exits = exits + "down";
            //         }
            //         return exits;
        }
        return exits;
    }

    /**
     * return a String containing the description and exits of a room
     * @return desc
     */
    public String getLongDescription(){
        String desc = "You are ";
        desc = desc + getDescription() + " with " + this.getExitString() + " exit(s)";
        if(getItem() != null){
            desc = desc + " and " + getItem() + " item(s).";
        }
        return desc;
    }

    /**
     * place the specific item in the room
     * @parm theItem the specific item
     */
    public void placeItems(Item theItem) {
       usableItem = theItem;
        
        
        //if(getItem() == null){
       
       // }else if (theItem != usableItem) {
       //     usableItem = theItem;
        //    System.out.println("The Item has changed to :" + theItem);
        //} else {
         //   System.out.println("Item already in room");
        //}
    }

    /**
     * return the item in the room
     * @return usableItem
     */
    public Item getItem(){
        return usableItem;
    }

    /** remove items from room
     */
    public void removeItem(){
        if(getItem() != null){
            usableItem = null;
        }else{
            System.out.println("Item already removed from room");
        }
    }

    /**
     * check if there is an item in the room
     * @return true if there is, false otherwise
     */
    public boolean checkRoomItem(){
        if (usableItem == null){
            return false;
        } else {
            return true;
        }
    }
}
