package lewis;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;


/**
 * This class implements Lewis, a chatbot who is designed to interact with the user.
 */
public class Lewis {
    /**
     * Private constructor for Lewis
     */
    private Lewis() {
    }
    /**
     * Initialises the data for Lewis bot to function. This includes loading the tasklist from
     * the hard drive and the sorted command list that Lewis can parse.
     */
    private static void init() {
        ArrayList<String> savedTasks = Storage.load();
        TaskList.load(savedTasks);
    }
    /**
     * Factory method for producing a Lewis bot. When called, Lewis will try to read
     * the existing save file from disk.
     * @return A Lewis bot
     */
    public static Lewis of() {
        init();
        return new Lewis();
    }

    /**
     * Parses the user input, then executes the associated command.
     * Returns the output that Lewis should tell the user
     * @return a string output
     */
    public String getResponse(String input) {
        try {
            //
            assert input != null : "Input cannot be null";

            /* If the input field is empty, return the hello message */
            Command command;
            if (input.isEmpty()) {
                command = HelloCommand.of();
                command.execute();
            } else {
                command = Parser.parseCommand(input);
                command.execute();
            }

            if (command.isExit()) {
                Platform.exit(); //Closes the GUI window
                System.exit(0);
            }

            List<String> output = Ui.flush();
            StringBuilder response = new StringBuilder();

            for (String line : output) {
                response.append(line).append("\n");
            }

            return response.toString().trim();

        } catch (LewisException e) {
            return "Error: " + e.getMessage();
        }
    }
}
