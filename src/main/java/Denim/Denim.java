package denim;

import java.util.Scanner;

import denim.commands.Command;
import denim.commands.CommandResult;
import denim.exceptions.DenimFileException;
import denim.storage.ReadTaskFile;
import denim.storage.WriteTaskFile;

/**
 * The main class for the Denim application.
 * This class initializes the necessary components, starts the application,
 * and runs the command loop until the exit command is received.
 */
public class Denim {
    public static final String FILE_PATH = "data/denim.txt";
    public static final int INDEX_OFFSET = 1;

    private ReadTaskFile readTaskFile;
    private WriteTaskFile writeTaskFile;

    private Parser parser;
    private TaskList taskList;



    /**
     * Starts the Denim Application first by reading the file with tasks stored in it.
     */
    public void start() {
        initialize();

        if (!ableToReadFile()) {

        }
    }

    public void initialize() {
        readTaskFile = new ReadTaskFile(FILE_PATH);
        writeTaskFile = new WriteTaskFile(FILE_PATH);
        parser = new Parser();
        taskList = new TaskList();
    }

    public boolean ableToReadFile() {
        try {
            Scanner tempScanner = new Scanner(System.in);
            readTaskFile.readTaskData(taskList, tempScanner);
        } catch (DenimFileException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
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
        return command.execute(taskList, writeTaskFile);
    }

    /**
     * Exits the application by terminating the program.
     */
    public void exit() {
        System.exit(0);
    }
}


