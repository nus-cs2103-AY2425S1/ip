import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import orangecat.TheOrangeCat;
import parser.Command;
import parser.Parser;
import storage.Storage;
import tasks.Task;


/**
 * The main class that overruns the entire Chatbot
 */
public class TheOrangeRatchetCat {
    // Without the GUI, this is the main method
    public static void main(String[] args) {
        TheOrangeCat.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        List<Task> tasks = new ArrayList<>();
        Task.assignTaskCountZero(0);
        Storage.loadTasks(tasks);
        Parser.initialiseMap(); // To add all the command key-value pairs in Parser
        Scanner scanner = new Scanner(System.in);
        String resultToShow = "";
        if (input.equals("bye")) {
            resultToShow = "MEOW! GOODBYE";
        }

        while (!input.equals("bye")) {
            String commandKey = input.split(" ")[0].trim(); // Get the command keyword
            Command command = Parser.commands.get(commandKey);

            try {
                if (command != null) {
                    resultToShow = command.execute(input, tasks, scanner);
                    break;
                } else {
                    System.out.print("Inappropriate Command. Try again with a valid command: ");
                    input = "bye";
                    resultToShow = "Inappropriate Command. Try again with a valid command";
                }
            } catch (DateTimeParseException e) { //TheOrangeRatchetCatException |
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
        Storage.saveTasks(tasks);
        return "RatchetCat says: \n" + resultToShow;
    }
}
