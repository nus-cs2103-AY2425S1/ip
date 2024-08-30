package Parser;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Tasks.TaskList;
import Tasks.Task;
import Exceptions.TestamentException;
import Exceptions.CommandNotRecognisedException;
import Ui.Ui;
import Storage.Storage;

/**
 * In charge of retrieving, parsing, and handling user input.
 */
public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for Parser.
     *
     * @param ui Ui for testament chatbot.
     * @param taskList taskList for testament chatbot.
     * @param storage storage for testament chatbot.
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parses user input, and then performs the corresponding actions based on user input.
     *
     * @param userInput User input.
     */
    public void parse(String userInput) {
        String[] splitUserInput = userInput.split(" ", 2);
        String identifier = splitUserInput[0];
        Enumerations enumeration = Enumerations.convertString(identifier);
        try{
            int taskNumber;
            Task retrievedTask;
            switch(enumeration) {
            case BYE:
                ui.bye();
                break;
            case SCHEDULE:
                ui.schedule(taskList);
                getUserInput();
                break;
            case MARK:
                taskNumber = validateTaskNumber(splitUserInput);

                //mark the task
                taskList.mark(taskNumber);
                retrievedTask = taskList.getTask(taskNumber);

                //print mark message
                ui.mark(retrievedTask);

                getUserInput();
                break;
            case UNMARK:
                taskNumber = validateTaskNumber(splitUserInput);

                //unmark the task
                taskList.unMark(taskNumber);
                retrievedTask = taskList.getTask(taskNumber);

                //print unmark message
                ui.unMark(retrievedTask);

                getUserInput();
                break;
            case DELETE:
                taskNumber = validateTaskNumber(splitUserInput);

                //delete the task
                retrievedTask = taskList.getTask(taskNumber);
                taskList.deleteTask(taskNumber);

                //print delete message
                ui.delete(retrievedTask);

                getUserInput();
                break;
            case FIND:
                findTask(splitUserInput);
                getUserInput();
                break;
            default:
                try {
                    taskList.add(Task.of(userInput));
                } catch (DateTimeParseException e) {
                    throw new CommandNotRecognisedException("Dates should be in the format below:\nyyyy-mm-dd");
                }

                ui.add(taskList);
                getUserInput();
                break;
            }
            storage.save();
        } catch(TestamentException e) {
            ui.exception(e);
            getUserInput();
        }

    }

    /**
     * Retrieves user input using a scanner.
     */
    public void getUserInput() {
        String userInput = scanner.nextLine();
        parse(userInput);
    }

    /**
     * Checks if user has properly entered a task number in their user input.
     * If properly entered, converts user input into task number.
     *
     * @param splitUserInput User input.
     * @return Task number.
     * @throws TestamentException thrown if user did not enter task number in input, or if format is wrong.
     */
    private int validateTaskNumber(String[] splitUserInput) throws TestamentException {
        //check if user has specified task number
        if (splitUserInput.length < 2) {
            throw new CommandNotRecognisedException("Please specify task number");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(splitUserInput[1]);
        } catch (NumberFormatException e) {
            throw new CommandNotRecognisedException("Please specify task number");
        }

        return taskNumber;
    }

    private void findTask(String[] userInput) throws TestamentException {
        //check if user has specified details
        if (userInput.length < 2) {
            throw new CommandNotRecognisedException("Please specify details");
        }

        String description = userInput[1];
        TaskList relevantTasks = taskList.findDescription(description);
        ui.find(relevantTasks);
    }

}
