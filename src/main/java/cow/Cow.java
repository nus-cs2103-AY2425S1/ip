package cow;

import java.io.IOException;

import cow.commands.Command;
import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.parser.Parser;
import cow.todolist.TodoList;

/**
 * Represents the Cow program.
 */
public class Cow {
    // solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private TodoList todoList;
    private final FileSaver fs;
    private final Ui ui;
    private String commandType;

    /**
     * Creates a Cow object.
     *
     * @param filePath The file path to load data from.
     * @throws IllegalArgumentException if the file path is empty.
     */
    public Cow(String filePath) {
        assert !filePath.isEmpty() : "File path should not be empty";
        this.ui = new Ui();
        this.fs = new FileSaver(filePath);
        try {
            todoList = fs.loadData();
        } catch (IOException | CowExceptions e) {
            handleCowException();
        }
    }

    /**
     * Handles exceptions that occur during the loading of data.
     * Prints a loading error message and initializes an empty TodoList.
     */
    private void handleCowException() {
        ui.printLoadingError();
        todoList = new TodoList();
    }

    /**
     * Runs the main loop of the Cow program.
     * Continuously reads and executes commands until an exit command is issued.
     */
    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(todoList, ui, fs);
                isExit = c.isExit();
            } catch (CowExceptions e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Processes the input command and returns the response.
     *
     * @param input The input command as a string.
     * @return The response from executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(todoList, ui, fs);
            commandType = c.getClass().getSimpleName();
            return ui.getCurrentText();
        } catch (CowExceptions e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message.
     *
     * @return The greeting message as a string.
     */
    public String getGreetings() {
        return ui.printGreetings();
    }

    /**
     * The main method to run the Cow program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Cow("data/cow.txt").run();
    }

    /**
     * Returns the type of the last executed command.
     *
     * @return The type of the last executed command.
     */
    public String getCommandType() {
        return commandType;
    }
}
