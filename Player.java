import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class represents a player of the Zuul game.  It keeps track of the
 * player's name and current location, and implements methods corresponding
 * to actions that the player can take.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Tim Wahls
 * @author  Grant Braught
 * @version 3.0 (November 2006)
 */
public class Player
{
    private String name;
    private Room currentRoom; // current location of the player
    private ArrayList<Item>inventory; //items in a player's inventory
    /**
     * Create a new Player with the specified name and initial location
     * @param name the name of the player
     * @param initRoom the initial location of the player
     */
    public Player(String name, Room initRoom)
    {
        this.name = name;
        currentRoom = initRoom;
        inventory = new ArrayList<Item>();
    }

    /**
     * Get the name of the player
     * @return the name of the player 
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the current room of the player
     * @return the current room of the player
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room and return true.  If there is no exit, stay in the
     * same room and return false.
     * @param direction the direction to go
     */
    public boolean goRoom(String direction) 
    {
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    /**
     * check if the player has the item
     * 
     * @param itemName name of Item to be checked
     * @return item in inventory or not
     */
    public boolean checkItem(String itemName){
        int index = 0;
        while(index < inventory.size()){
            Item current = inventory.get(index);
            String currentName= current.getName();
            if(currentName.equals(itemName)){
                return true;
            }else{
                index++;
            }
        } 
        return false;
    }

    /** 
     * Player picks up item if item is not already in
     *  inventory. 
     *  @param newItem Item player is picking up
     */
    public void pickUpItem(Item pickUp){        
        Item current = getCurrentRoom().getItem();
        if (current != null){
            if (pickUp.getName().equals(current.getName())){
                if (!checkItem(current.getName())){
                    inventory.add(current);
                }else{
                    System.out.println("Item already in the inventory.");
                }
            }else{
                System.out.println("the specific item not in the room");
            }
        }else{
                System.out.println("no item in the room");
        }
    }


        /** 
         * Player gets rid of an item from their inventory.
         *  @param chosenItem Item player is removing
         */
        public void removeItem(Item chosenItem){
        if(this.checkItem(chosenItem.getName())){
            inventory.remove(chosenItem);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    /**
     * print out all the items in the inventory
     */
    public void printItemList(){
        String print = "";
        for (int index = 0; index < inventory.size(); index++){
            print = print + inventory.get(index).getName();
        } 
        if(print.equals("")) {
            System.out.println("Error: inventory is empty.");
        }
        
        System.out.println(print);
    }

    /**
     * 
     */
    public Item getItemByName(String searchName){
        int index = 0;
        while(index < inventory.size()){
            Item current = inventory.get(index);
            if(current.getName().equals(searchName)){
                return current;
            } else {
                index++;
            }
        }
        return null;
    }
}
