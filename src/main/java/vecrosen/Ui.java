package vecrosen;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles receiving input and displaying output to/from the user.
 */
public class Ui {
    private Scanner input;
    private PrintStream output;

    /**
     * Initializes the UI
     *
     * @param inputStream Where the text the user inputs will be received
     * @param outputStream Where the text Vecrosen will respond with is sent
     */
    public Ui(InputStream inputStream, PrintStream outputStream) {
        input = new Scanner(inputStream);
        output = outputStream;
    }

    /**
     * Outputs a string with an indentation, to differentiate Vecrosen's dialog from the user's.
     *
     * @param s The string to be printed.
     */
    public void speak(String s) {
        output.print("    ");
        output.println(s);
    }

    /**
     * Outputs an alert informing the user they have formatted their command incorrectly,
     * and elaborates on how they were supposed to use said command.
     *
     * @param usage The proper usage of the command.
     */
    public void alertInvalidFormat(String usage) {
        speak("Invalid format.");
        speak("Usage: " + usage);
    }

    /**
     * Prints the intro, followed by the tasks in the list.
     *
     * @param list The list of tasks to print
     * @param intro Sentence to print before the list of tasks
     * @param noTasks Sentence to print if the list is empty
     */
    public void printList(ArrayList<Task> list, ArrayList<Integer> indices, String intro, String noTasks) {
        if (list.isEmpty()) {
            speak(noTasks);
            return;
        }
        speak(intro);
        for (int i = 0; i < list.size(); ++i) {
            speak(indices.get(i) + "." + list.get(i).toString());
        }
    }

    /**
     * Obtains the next line that the user had entered.
     *
     * @return The line the user entered
     */
    public String readLine() {
        return input.nextLine();
    }
}
