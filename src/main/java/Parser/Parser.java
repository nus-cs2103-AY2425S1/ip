package Parser;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Tasks.*;
import Exceptions.*;
import Ui.Ui;
import Storage.Storage;

public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private Scanner scanner = new Scanner(System.in);

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

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

    public void getUserInput() {
        String userInput = scanner.nextLine();
        parse(userInput);
    }
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

}
