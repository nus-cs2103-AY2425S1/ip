package johncena;

import java.util.ArrayList;

import johncena.commands.Command;
import johncena.commands.HelloCommand;
import johncena.exceptions.CenaException;
import johncena.parser.InputHandler;
import johncena.storage.Storage;
import johncena.tasks.Task;



/**
 * The {@code JohnCena} class is the main class of the John Cena program.
 */
public class JohnCena {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static InputHandler inputHandler;

    /**
     * Initializes the John Cena program.
     */
    public JohnCena() {
        tasks = Storage.loadTasks();
        inputHandler = new InputHandler(tasks);
        HelloCommand helloCommand = new HelloCommand();
        helloCommand.execute();
    }


    /**
     * Generates a response for the user's chat message.
     *
     * @param input the user input
     * @return the response
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            Command command = inputHandler.handleInput(input);
            return command.execute();
        } catch (CenaException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

}
