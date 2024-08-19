/**
 * This class parses user input for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Parser {

    private static ArrayList<String> tokens = new ArrayList<>();
    /**
     * Method to read user input.
     *
     * @return user input as a string.
     */
    public static String getMessage() {
        Scanner scanner = new Scanner(System.in);
        Parser.tokens.clear();
        String msg = scanner.nextLine();
        Parser.tokens = new ArrayList<>(Arrays.asList(msg.split(" ")));
        return msg;
    }

    /**
     * Method to return individual words of a message as an ArrayList.
     * @return individual words of the message string as an ArrayList.
     */
    public static ArrayList<String> getTokens() {
        return Parser.tokens;
    }
}
