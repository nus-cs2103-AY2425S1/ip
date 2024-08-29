package misc;
import java.util.Scanner;

public class Parser {
    Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Takes user input, splits it into a command and the rest of the input,
     * and returns them as a String array.
     * 
     * @return An array of two strings is being returned. The first element of the array contains the
     * command extracted from the user input, and the second element contains the rest of the input text
     * after the command.
     */
    public String[] requestInput() {
        System.out.print("Text: ");
        String input = scanner.nextLine();
        String cmd = input.split(" ", 2)[0];
        String rest = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";
        String[] retStrArr = new String[]{cmd, rest};
        return retStrArr;
    }
}
