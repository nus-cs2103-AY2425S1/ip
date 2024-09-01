package janet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the entry point of Janet.
 */
public class Janet {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    Janet(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.textFileToArrayList());
        } catch (JanetException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Janet program.
     */
    public void run() {
        ui.showWelcome();
        Scanner input = new Scanner(System.in);
        boolean hasTypedBye = false;

        while (input.hasNext()) {
            String userCommand = input.nextLine();
            String[] commandDetails = Parser.getCommandDetails(userCommand);    // converts user input into a String array
            try {
                Parser.checkUserInput(commandDetails, tasks.getNumberOfTasks());    // runs all the checks to verify user input

                CommandType commandType = Parser.getCommand(commandDetails);    // get the CommandType
                switch (commandType) {
                case BYE:
                    ui.exitMessage();
                    hasTypedBye = true;
                    break;
                case LIST:
                    ui.showTasks(tasks);
                    break;
                case MARK:
                    String markedResult = tasks.markAsDone(Integer.parseInt(commandDetails[1]));
                    ui.showMarkedMessage(markedResult, tasks.getTask(Integer.parseInt(commandDetails[1]) - 1));
                    break;
                case UNMARK:
                    String unmarkResult = tasks.unmark(Integer.parseInt(commandDetails[1]));
                    ui.showUnmarkedMessage(unmarkResult, tasks.getTask(Integer.parseInt(commandDetails[1]) - 1));
                    break;
                case DELETE:
                    ui.showDeleteTaskMessage(tasks.getTask(Integer.parseInt(commandDetails[1]) - 1),
                            tasks.getNumberOfTasks() - 1);
                    tasks.deleteTask(Integer.parseInt(commandDetails[1]));
                    break;
                case TODO:
                    Task todo = new ToDo(userCommand);
                    tasks.addTaskToList(todo);
                    ui.showSuccessfulTaskAddition(todo, tasks.getNumberOfTasks());
                    break;
                case DEADLINE:
                    Task deadline = new Deadline(userCommand);
                    tasks.addTaskToList(deadline);
                    ui.showSuccessfulTaskAddition(deadline, tasks.getNumberOfTasks());
                    break;
                case EVENT:
                    Task event = new Event(userCommand);
                    tasks.addTaskToList(event);
                    ui.showSuccessfulTaskAddition(event, tasks.getNumberOfTasks());
                    break;
                case FIND:
                    String keyword = String.join(" ",
                            Arrays.copyOfRange(commandDetails, 1, commandDetails.length));
                    ui.showFindMessage(tasks.findTasks(keyword));
                    break;
                }
                if (hasTypedBye) {
                    break;
                }
            } catch (JanetException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            storage.saveToJanetTextFile(tasks.getListOfTasks());
        } catch (JanetException e) {
            ui.showSavingError();
        }
    }

    public static void main(String[] args) {
        new Janet("janet.txt").run();
    }

}
