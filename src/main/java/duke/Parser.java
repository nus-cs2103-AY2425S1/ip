package duke;

/**
 * A class that deals converting user input into different data output we want.
 */
public class Parser {
    /**
     * Returns the number user tries to key in from scanner.
     */
    public static int splitGetNum(String input){
        String[] words = input.split(" ");
        return Integer.parseInt(words[1]) - 1;
    }

    /**
     * Returns the tasks user want to store after main todo, event, deadline command.
     */
    public static String splitGetWords(String input){
        String[] words = input.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
}
