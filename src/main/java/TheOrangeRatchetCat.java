import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import orangecat.TheOrangeCat;
import parser.Command;
import parser.Parser;
import storage.Storage;
import tasks.Task;


/**
 * TheOrangeRatchetCat is the main class for the Orange Ratchet Cat chatbot.
 * It is the entry point for the JavaFX application.
 */
public class TheOrangeRatchetCat {
    /**
     * The main method for the Orange Ratchet Cat chatbot when using CLI
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        TheOrangeCat.run();
    }

    /**
     * Returns the response of the Orange Ratchet Cat chatbot.
     *
     * @param input The input from the user.
     * @return The response of the Orange Ratchet Cat chatbot.
     */
    public String getResponse(String input) {
        List<Task> tasks = new ArrayList<>();
        Task.assignTaskCountZero(0);
        Storage.loadTasks(tasks);
        Parser.initialiseMap(); // To add all the command key-value pairs in Parser
        String resultToShow = "";
        if (input.equals("bye")) {
            resultToShow = TheOrangeRatchetCat.ratchetCatUniqueDialogue();
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit()); // Exits the JavaFX application
            pause.play();
        }
        resultToShow = getInputResult(input, resultToShow, tasks);
        Storage.saveTasks(tasks);
        return "RatchetCat says: \n" + resultToShow;
    }

    /**
     * Returns the result of the input command.
     *
     * @param input The input from the user.
     * @param resultToShow The result to show to the user.
     * @param tasks The list of tasks.
     * @return The result of the input command.
     */
    private static String getInputResult(String input, String resultToShow, List<Task> tasks) {
        while (!input.equals("bye")) {
            String commandKey = input.split(" ")[0].trim(); // Get the command keyword
            Command command = Parser.commands.get(commandKey);
            try {
                if (command != null) {
                    resultToShow = command.execute(input, tasks);
                    break;
                } else {
                    System.out.print("Inappropriate Command. Try again with a valid command: ");
                    input = "bye";
                    resultToShow = "Inappropriate Command. Try again with a valid command";
                }
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                input = "bye";
                resultToShow = "Incorrect date format. Please do <YYYY>-<MM>-<DD>";
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
                input = "bye";
                resultToShow = "Correct input format for adding event: event <Task> /from <input> /to <input>";
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number for the command.");
                input = "bye";
                resultToShow = "Please provide a valid number for the command.";
            }
        }
        return resultToShow;
    }
    /**
     * Returns a unique dialogue for the RatchetCat
     */
    private static String ratchetCatUniqueDialogue() {
        return "MEOW! GOOD IS BYE!";
    }
}
