import java.util.Date;
/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * Upadated 01/2018 - The game scenario puts the player in the role of a prisoner. The goal
 * is to escape from the high risk cell block of Zuul prison.
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes & Brian Hurst
 * @version 2018.01.22
 *
 * - Modified printWelcome method
 * - Modified createRooms method
 * - Modified printHelp method
 *
 * - Created printLocationInfo method
 * - Modified goRoom method to call printLocationInfo
 * - Modified printWelcome method to call printLocationInfo
 *
 * - Modified goRoom method to call getExit
 * - Modified printLocationInfo method to use getExitString method in the Room class
 * - Modified createRooms method
 * - Modified printLocationInfo method
 * - Modified printLocationInfo method to call printLongDescription
 *
 * - Created look method
 * - Modified processCommand method
 * - Added sneak method
 * - Modified processCommand method
 * - Added toggle field
 * - Added toggleCommand method
 * - Modified sneak method
 * - Added unSneak method
 * - Modified processCommand method
 * - Modified unSneak method
 * - Modified sneak method
 * - Modified printHelp method
 * - Modified goRoom method
 * - Modified printLocationInfo method
 * - Modified createRooms method to add items
 * - Modified createRooms method. (added 2 more items.)
 * - Imported Date
 * - Modified printWelcome method to display the date
 *
 *   //TODO : - (implement a player class and create an
 *   inventory system using a collection)
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private boolean toggle;     //turns commands on or off 

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Look around the room
     */
    private void look() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Toggle a command on or off
     */
    private void toggleCommand() {
        toggle ^= true;
    }

    /**
     * Enter sneak mode
     */
    private void sneak() {
        if (toggle == false) {
            toggleCommand();
            System.out.println("You are sneaking around");
        } else {
            System.out.println("Aready sneaking...");
        }
    }

    /**
     * Exit sneak mode
     */
    private void unSneak() {
        if (toggle == true) {
            toggleCommand();
            System.out.println("You are no longer sneaking around");
        } else {
            System.out.println("Not sneaking yet...");
        }
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room freeWorld, intake, eastHall, messHall, southHall, courtYard, northHall, westCellBlock,
                highRiskCellBlock, administration;

        // create the rooms
        freeWorld = new Room("in the free world! Run away!");
        intake = new Room("in the prisoner intake area");
        eastHall = new Room("in the east hall");
        messHall = new Room("in the mess hall, watch yourself in here.");
        southHall = new Room("in the south hall");
        courtYard = new Room("in the courtyard, fresh air!");
        northHall = new Room("in the north hall. What is that on the ground?");
        westCellBlock = new Room("in the west cell block. It stinks something awful in here!");
        highRiskCellBlock = new Room("in the high risk cell block. Let's get out of here!");
        administration = new Room("in what appears to be the administration area.");

        // put items in the rooms
        highRiskCellBlock.addItem(new Item("kite", "a letter containing a rough sketch of a map", 1));
        administration.addItem(new Item("shackles", "prisoner shackles", 3));
        administration.addItem(new Item("paper clips", "a pile of paper clips", 1));
        intake.addItem(new Item("court papers", "a prisoner's court paperwork", 1));
        intake.addItem(new Item("prison jumpsuit", "a neatly pressed, state-issued uniform", 3));
        courtYard.addItem(new Item("plastic shiv", "a toothbrush disguised as shrubbery and sharpened at one end", 2));
        courtYard.addItem(new Item("shrubs", "plants growing in the grass", 2));
        northHall.addItem(new Item("fire extinguisher", "a large fire extinguisher", 15));
        westCellBlock.addItem(new Item("laundry pile", "dirty laundry in the hall", 7));
        westCellBlock.addItem(new Item("Amorphous pile", "an unidentifiable organic mass", 6));
        messHall.addItem(new Item("food tray", "a dirty food tray", 4));
        messHall.addItem(new Item("hot coffee", "a mug full of hot liquid", 4));
        messHall.addItem(new Item("nutri-loaf", " a flavorless but nutritional food loaf", 3));
        messHall.addItem(new Item("orange", " a moldy citrus friut", 2));
        eastHall.addItem(new Item("trash", "a small pile of rubbish", 2));
        southHall.addItem(new Item("Wire", "mangled electrical wiring", 1));
        southHall.addItem(new Item("pipe", "a lead pipe", 7));
        freeWorld.addItem(new Item("shrubs", "plants growing in the grass", 2));


        // initialise room exits
        freeWorld.setExit("west", intake);

        intake.setExit("east", freeWorld);
        intake.setExit("west", eastHall);

        eastHall.setExit("north", northHall);
        eastHall.setExit("east", intake);
        eastHall.setExit("south", messHall);
        eastHall.setExit("west", southHall);

        messHall.setExit("north", eastHall);

        southHall.setExit("west", westCellBlock);
        southHall.setExit("east", eastHall);
        southHall.setExit("north", courtYard);

        courtYard.setExit("north", northHall);
        courtYard.setExit("south", southHall);

        northHall.setExit("west", westCellBlock);
        northHall.setExit("south", courtYard);
        northHall.setExit("east", eastHall);

        westCellBlock.setExit("west", administration);
        westCellBlock.setExit("south", southHall);
        westCellBlock.setExit("east", northHall);
        westCellBlock.setExit("north", highRiskCellBlock);

        highRiskCellBlock.setExit("south", westCellBlock);

        administration.setExit("east", westCellBlock);

        currentRoom = highRiskCellBlock;  // start game in the high risk cell block
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print the location info depending on which room the player is in.
     */
    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println(new Date());
        System.out.println();
        System.out.println("Welcome to Zuul Prison!");
        System.out.println("Your goal is to escape to the free world.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("sneak")) {
            sneak();
        } else if (commandWord.equals("unsneak")) {
            unSneak();
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the Zuul Prison.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            printLocationInfo();
            currentRoom.printListOfItems();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}
