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
    static final String FILE_PATH = "data/denim.txt";
    private TextBasedUi ui;
    private TaskIo taskIo;
    private Parser parser = new Parser();

    private TaskList taskList;

    /**
     * The main method that serves as the entry point of the Denim application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Denim denim = new Denim();
        denim.start();
        denim.runCommandLoopUntilExitCommand();
        denim.exit();
    }

    void start() {
        taskIo = new TaskIo(FILE_PATH);
        taskList = new TaskList();

        try {
            Scanner tempScanner = new Scanner(System.in);
            taskIo.readTaskData(taskList, tempScanner);
        } catch (DenimException e) {
            System.out.println(e.getMessage());
            return;
        }
        this.ui = new TextBasedUi();
        ui.displayGreetingMessage();
    }

    /**
     * Runs the command loop, repeatedly reading user input,
     * executing commands, and displaying results until the exit command is given.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = command.execute(taskList, taskIo);
            ui.displayReplyMessage(result);
        } while (!command.isExit());
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


