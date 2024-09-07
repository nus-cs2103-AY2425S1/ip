package elliot;

import java.util.Scanner;

import command.Command;
import command.CommandType;
import exception.ElliotException;
import utility.Storage;
import utility.Strip;
import utility.TaskList;
import utility.Ui;

/**
 * The Elliot class is the main driver class and central component for the whole chatbot.
 */
public class Elliot {
    private Storage storage;
    private TaskList taskList;

    public Elliot() {
        storage = new Storage("./data/ElliotTaskList.ser");
        taskList = storage.loadTaskList();
    }

    /**
     * Main entry for the class and chatbot as a whole.
     */
    public static void main(String[] args) {
        new Elliot().run();
    }

    /**
     * This method seeks to abstract out the logic of running the chatbot away from the main
     * method.
     *
     * The function of this method is to engage all of the sub classes and its functions to
     * provide user input and chatbot output to the command line.
     */
    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Ui.introSay();
        while (isRunning) {
            System.out.print("> ");
            String userInput = scanner.nextLine().strip();
            String[] commandString = Strip.stripStringArray(userInput
                    .toLowerCase().split(" ", 2));
            Ui.say("");
            try {
                CommandType commandType = CommandType.parseStringToCommand(commandString[0]);
                Command command = commandType.getCommand();
                command = command.parseArguments(commandString.length < 2
                        ? ""
                        : commandString[1]);
                taskList = command.runCommand(taskList, storage);
                isRunning = !(commandType == CommandType.BYE);
            } catch (ElliotException e) {
                continue;
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
