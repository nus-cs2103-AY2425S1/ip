import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private Scanner input;
    private PrintStream output;
    public Ui(InputStream inputStream, PrintStream outputStream) {
        input = new Scanner(inputStream);
        output = outputStream;
    }

    /**
     * Outputs a string with an indentation, to differentiate Vecrosen's dialog from the user's.
     * @param s The string to be printed.
     */
    public void speak(String s) {
        output.print("    ");
        output.println(s);
    }

    /**
     * Outputs an alert informing the user they have formatted their command incorrectly,
     * and elaborates on how they were supposed to use said command.
     * @param usage The proper usage of the command.
     */
    public void invalidFormat(String usage) {
        speak("Invalid format.");
        speak("Usage: " + usage);
    }

    public String readline() {
        return input.nextLine();
    }
}
