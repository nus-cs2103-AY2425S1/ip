package denim;

import java.util.Scanner;

import denim.commands.Command;
import denim.commands.CommandResult;
import denim.exceptions.DenimException;
import denim.storage.TaskIo;

/**
 * The main class for the Denim application.
 * This class initializes the necessary components, starts the application,
 * and runs the command loop until the exit command is received.
 */
public class Denim {
    public static final String FILE_PATH = "data/denim.txt";
    public static final int INDEX_OFFSET = 1;

    private TaskIo taskIo;

    private Parser parser = new Parser();
    private TaskList taskList;



    /**
     * Starts the Denim Application first by reading the file with tasks stored in it.
     */
    public void start() {
        taskIo = new TaskIo(FILE_PATH);
        taskList = new TaskList();

        try {
            Scanner tempScanner = new Scanner(System.in);
            taskIo.readTaskData(taskList, tempScanner);
        } catch (DenimException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * parses the input from the GUI using a Parser object.
     *
     * @param input the user input received from the GUI.
     *
     * @return command
     */
    public Command parseGuiCommand(String input) {
        Command command = parser.parseCommand(input);
        return command;
    }

    public CommandResult executeGuiCommand(Command command) {
        return command.execute(taskList, taskIo);
    }

    /**
     * Exits the application by terminating the program.
     */
    public void exit() {
        System.exit(0);
    }
}


