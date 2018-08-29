import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This reader reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The reader has a list of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns null.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Tim Wahls
 * @author  Grant Braught
 * @version 3.0 (November 2006)
 */

public class CommandReader 
{

    private BufferedReader reader; // to read from
    private ArrayList<String> validCommands; // holds valid command words

    /**
     * create a new command reader to process commands
     */
    public CommandReader() 
    {
        reader = new BufferedReader(new InputStreamReader(System.in));
        validCommands = new ArrayList<String>();
    }

    /**
     * add a command word.  It must be only one word (no spaces allowed).
     * @param word the new command word
     */
    public void addCommand(String word) {
        if (word.indexOf(" ") == -1) {
            validCommands.add(word);
        } else {
            System.out.println("Error: a command word must be a single word!");
        }
    }
    
    /**
     * check whether a word is a valid command word.
     * @param word the word to check
     */
    public boolean isCommand(String word) {
        for (String com : validCommands) {
            if (word.equals(com)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return a string of all valid command words, separated by spaces.
     * @return a string containing all valid command words
     */
    public String allCommands() {
        String res = "";
        for (String com : validCommands) {
            res = res + com + " ";
        }
        return res;
    }
    
    /**
     * read a command from the terminal and return it as a Command object
     * @return the Command constructed from the user input
     */
    public Command getCommand() 
    {
        String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        System.out.print("> ");     // print prompt

        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("There was an error during reading: "
                                + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if (tokenizer.hasMoreTokens()) {
            word1 = tokenizer.nextToken();      // get first word
        } else {
            word1 = null;
        }
        if (tokenizer.hasMoreTokens()) {
            word2 = tokenizer.nextToken();      // get second word
        } else {
            word2 = null;
        }

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if (word1 != null && isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return null;
        }
    }
}
