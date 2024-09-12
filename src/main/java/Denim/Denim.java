package denim;


import denim.commands.Command;
import denim.commands.CommandResult;
import denim.exceptions.DenimDirectoryException;
import denim.exceptions.DenimFileException;
import denim.storage.ReadTaskFile;
import denim.storage.WriteTaskFile;

/**
 * The main class for the Denim application.
 * This class initializes the necessary components, starts the application,
 * and runs the command loop until the exit command is received.
 */
public class Denim {
    public static final int INDEX_OFFSET = 1;

    private ReadTaskFile readTaskFile;
    private WriteTaskFile writeTaskFile;
    private String filePath;

    private Parser parser;
    private TaskList taskList;

    public Denim(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Starts the Denim Application first by reading the file with tasks stored in it.
     */
    public void start() throws DenimFileException, DenimDirectoryException {
        initialize();
        readFile();
    }

    /**
     * Initializes the required fields for the application.
     */
    public void initialize() {
        readTaskFile = new ReadTaskFile(filePath);
        writeTaskFile = new WriteTaskFile(filePath);
        parser = new Parser();
        taskList = new TaskList();
    }

    /**
     * Attempt to read file given in readTaskFile.
     *
     * @throws DenimFileException if File is not found.
     * @throws DenimDirectoryException if Directory is not found.
     */
    public void readFile() throws DenimFileException, DenimDirectoryException {
        readTaskFile.readTaskData(taskList);
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

    public void handleDirectoryNotFound() throws DenimDirectoryException {
        readTaskFile.handleDirectoryNotFound();
    }

    public void handleFileNotFound() throws DenimFileException {
        readTaskFile.handleFileNotFound();
    }
}


