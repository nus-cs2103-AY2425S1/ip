package evelyn;

import evelyn.command.ui.gui.Gui;
import evelyn.exception.InvalidInputException;
import evelyn.exception.NoInputException;
import javafx.application.Application;

import evelyn.command.Parser;
import evelyn.command.Storage;
import evelyn.command.TaskList;
import evelyn.command.ui.textbased.TextBasedUi;

import java.io.IOException;

public class Evelyn {

    private TaskList taskList;
    private Parser parser;
    private static final String DEFAULT_FILE_PATH = "src/main/data/evelyn.txt";

    public Evelyn() {
        this.taskList = new TaskList(new Storage(DEFAULT_FILE_PATH));
        this.parser = new Parser(this.taskList);
    }

    public String getResponse(String input) {
        try {
            String response = parser.parse(input);
            return response;
        } catch (InvalidInputException e) {
            String response = "You did not use the keywords properly!\n"
                    + "todo [task description]\n"
                    +"deadline [task description] /by [date in YYYY-MM-DD] [Optional: time]\n"
                    + "event [task description] /from [date in YYYY-MM-DD] [Optional: time] /to [date in YYYY-MM-DD] [Optional: time]\n"
                    + "list (Lists out all the current tasks)\n"
                    + "mark [index] (Marks the tasks at the specified index)\n"
                    + "unmark [index] (Unmarks the tasks at the specified index)\n"
                    + "delete [index] (deletes the tasks at the specified index)\n"
                    + "find [keyword] (Unmarks the tasks at the specified index)";
            return response;
        } catch (NoInputException e) {
            String response = "You did not use the keywords properly!\n"
                    + "todo [task description]\n"
                    +"deadline [task description] /by [date in YYYY-MM-DD] [Optional: time]\n"
                    + "event [task description] /from [date in YYYY-MM-DD] [Optional: time] /to [date in YYYY-MM-DD] [Optional: time]\n"
                    + "list (Lists out all the current tasks)\n"
                    + "mark [index] (Marks the tasks at the specified index)\n"
                    + "unmark [index] (Unmarks the tasks at the specified index)\n"
                    + "delete [index] (deletes the tasks at the specified index)\n"
                    + "find [keyword] (Unmarks the tasks at the specified index)";
            return response;
        }
    }

    public static void main(String[] args) throws IOException {
        String dataFilePath = "src/main/data/evelyn.txt";
        Application.launch(Gui.class, args);
    }

}
