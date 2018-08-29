/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author Tim Wahls
 * @author Grant Braught
 * @author Tom & Younse
 * @version 3.0 (November 2006)
 */

public class Game 
{
    private CommandReader reader;
    private Player thePlayer;
    private Item id;
    private Item key;
    private Item essay;
    private Item barbell;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //createItems();
        createRoomsPlayerAndItems();
        reader = new CommandReader();
        reader.addCommand("go");
        reader.addCommand("quit");
        reader.addCommand("help");
        reader.addCommand("take");
        reader.addCommand("drop");
        reader.addCommand("inventory");
    }

    /**
     * Create all the rooms and link their exits together.  Also, create
     * the player and set the player's initial room.
     */
    public void createRoomsPlayerAndItems()
    {
        // create the rooms
        Room hub = new Room("in the Holland Union Building, the middle of campus");
        Room kauffmann = new Room("in the Kauffmann building for natural sciences and facilities");
        Room dps = new Room("at the Dickinson Public Safety Office");
        Room tome = new Room("in Tome Hall, the mathematics & sciences building");
        Room denny = new Room("in Denny Hall, the social sciences and anthropology building");
        Room kline = new Room("in Kline Athletic Center, a gymnasium");
        Room dorms = new Room("in the dormitories");
        Room library = new Room("in the Waidner-Spair Library");        

        // initialise room exits
        hub.setExit("north", denny);
        hub.setExit("east", dorms);
        hub.setExit("south", kauffmann);
        hub.setExit("west", tome);
        hub.setExit("up", kline);
        hub.setExit("down", library);
        dorms.setExit("west", hub);
        tome.setExit("east", hub);
        kauffmann.setExit("north", hub);
        kauffmann.setExit("west", dps);
        dps.setExit("east", kauffmann);
        denny.setExit("south", hub);
        kline.setExit("down", hub);
        library.setExit("up", hub);

        Item id = new Item("ID","pick up your ID at the ID office in HUB");
        Item key = new Item("Key","pick up your key from DPS and access your dorm");
        Item essay = new Item("SocialScienceEssay","print out your essay in the library and take it to Denny to submit");
        Item barbell = new Item("Barbell","pick up the barbell at the gym to work out");

        hub.placeItems(id);
        dps.placeItems(key);
        library.placeItems(essay);
        kline.placeItems(barbell);

        thePlayer = new Player("student", hub);  // start game outside
    }

    /**
     * Return the player
     * @return the player
     */
    public Player getPlayer() {
        return thePlayer;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = reader.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the description of the exits of a room the player is in. 
     */
    public void printRoomDesc() {
        Room currentRoom = thePlayer.getCurrentRoom();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        currentRoom.getExitString();
        System.out.println();
    }

    /**
     * Print out the opening message for the player.
     */
    public void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Dickinson College simulation!");
        System.out.println("Dickinson College simulation is a new,incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        this.printRoomDesc();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param command the command to process
     */
    public boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if (command == null) {
            System.out.println("I don't know what you mean ...");
            return false;
        } else {
            String commandWord = command.getCommandWord();
            if (commandWord.equals("help")) {
                printHelp();
            } else if (commandWord.equals("go")) {
                goRoom(command);
            } else if (commandWord.equals("quit")) {
                wantToQuit = quit(command);
            } else if (commandWord.equals("take")) {
                take(command);
            } else if (commandWord.equals("drop")) {
                drop(command);
            } else if (commandWord.equals("inventory")) {
                this.inventory();
            }
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   " + reader.allCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command the "go" command
     */
    public void goRoom(Command command) 
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
        } else {
            String direction = command.getSecondWord();

            // Try to leave current room.
            boolean hasLeft = thePlayer.goRoom(direction);

            if (!hasLeft) {
                System.out.println("There is no door!");
                //this.printRoomDesc();
            } else {
                this.printRoomDesc();
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     * @param command the "quit" command
     */
    public boolean quit(Command command) 
    {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /** Main method used to operate game outside of the BlueJ program
     * @param args
     */
    public static void main(String[] args){
        Game game1= new Game();
        game1.play();
    }

    //     /**
    //      * create all the items
    //      */
    //     public void createItems(){
    //         Item id = new Item("ID","pick up your ID at the ID office in HUB");
    //         Item key = new Item("Key","pick up your key from DPS and access your dorm");
    //         Item essay = new Item("SocialScienceEssay","print out your essay in the library and take it to Denny to submit");
    //         Item barbell = new Item("Barbell","pick up the barbell at the gym to work out");
    //     }

    /**
     * take the item from the room if 'take' command is typed
     * @param 
     */
    public void take(Command command){
        if (!command.hasSecondWord()){
            System.out.println ("Take what?");
        }else{
            String itemName = command.getSecondWord();
            Room location = thePlayer.getCurrentRoom();
            if (location.getItem() != null){
                if (location.getItem().getName().equals(itemName)){
                    thePlayer.pickUpItem(location.getItem());
                }else{
                    System.out.println("it is an invalid object");
                }
            }else{
                System.out.println("no item in the room");
            }
        }
    }
    //         if (!command.hasSecondWord()) {
    //             // if there is no second word, we don't know what to take...
    //             System.out.println("Take what?");
    //         } else {
    //             //
    //             String specificItem = command.getSecondWord();
    //             Room currentRoom = thePlayer.getCurrentRoom();
    //             if(currentRoom.checkRoomItem()){
    //                 String currentItem = currentRoom.getItem().getName();
    //                 if (specificItem.equals(currentItem)){
    //                     thePlayer.pickUpItem();
    //                 }else{
    //                     System.out.println("the specific item is not in the room");
    //                 }
    //             }else{
    //                 System.out.println("there is no item in the room");
    //             }
    //         }

    /**
     * print out all the item names in the inventory
     */
    public void inventory(){
        thePlayer.printItemList();
    }

    /**
     * take the item from the room if 'take' command is typed
     * @param 
     */
    public void drop(Command command){
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("drop what?");
        } else {
            //
            String specificItem = command.getSecondWord();
            Item current = thePlayer.getItemByName(specificItem);
            thePlayer.removeItem(current);
        }
    }
}
