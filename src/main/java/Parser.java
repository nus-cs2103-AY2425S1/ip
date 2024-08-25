import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Parser class for making sense of user command and calling the relevant function
 */
public class Parser {


    /**
     * Parses all user commands into their respective methods as well as display the bye message once
     * program terminates
     *
     * @param input the input that the user entered which is used to check which command to execute
     * @param scanner scanner object that scans user input
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui Ui that handles all user interaction
     * @param storage Storage that handles all I/O operations
     */
    public void parseCommand(String input, Scanner scanner, TaskList taskList, Ui ui, Storage storage) {

        switch (input.split(" ")[0].toLowerCase()) {
        case "bye":
            ui.displayBye();
            break;

        case "list":
            ui.displayTasks(taskList);
            break;

        case "mark":
            handleMark(input, taskList, ui);
            break;

        case "unmark":
            handleUnmark(input, taskList, ui);
            break;

        case "delete":
            handleDelete(input, taskList, ui);
            break;

        case "add":
            handleAdd(input, scanner, taskList, ui);
            break;

        default:
            ui.displayError("Hoshi doesn't understand, try a different input?");
            break;
        }
    }

    /**
     * Handles the mark command when input by user.
     *
     * @param input the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui Ui that handles all user interaction
     */
    private void handleMark(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() < 5) {

            ui.displayTaskToMark();

        } else {

            String trimmedInput = input.trim();

            char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

            // get only the number from the 2nd half of the splitInput
            int markIndex = Character.getNumericValue(taskNo) - 1;

            try {

                // if specified index is not out of bounds
                if (markIndex <= taskList.size() - 1) {

                    taskList.get(markIndex).setIsDone(true);
                    ui.displayTaskMarked(taskList.get(markIndex));

                } else {
                    throw new HoshiException("Hoshi doesn't have such a task!");
                }


            } catch (HoshiException e) {
                ui.displayError(e.getMessage());
            }

        }
    }

    /**
     * Handles the unmark command when input by user.
     *
     * @param input the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui Ui that handles all user interaction
     */
    private void handleUnmark(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() < 7) {
            ui.displayTaskToMark();

        } else {

            String trimmedInput = input.trim();

            char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

            // get only the number from the 2nd half of the splitInput
            int markIndex = Character.getNumericValue(taskNo) - 1;

            try {

                // if specified index is not out of bounds
                if (markIndex <= taskList.size() - 1) {

                    // set isDone to false
                    taskList.get(markIndex).setIsDone(false);

                    ui.displayTaskMarked(taskList.get(markIndex));

                } else {
                    throw new HoshiException("Hoshi doesn't have such a task! \n");
                }

            } catch (HoshiException e) {
                ui.displayError(e.getMessage());
            }
        }
    }

    /**
     * Handles the delete command when input by user.
     *
     * @param input the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui Ui that handles all user interaction
     */
    private void handleDelete(String input, TaskList taskList, Ui ui) {

        if (input.length() < 7) {
            ui.displayTaskToDelete();
        } else {

            String trimmedInput = input.trim();

            char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

            // get only the number from the 2nd half of the splitInput
            int markIndex = Character.getNumericValue(taskNo) - 1;

            taskList.delete(markIndex);
            ui.displayTaskDeleted(taskList.get(markIndex));

        }
    }

    /**
     * Adds either to do/deadline/event tasks that are described by the user to TaskList which is to be written to a
     * txt file later
     *
     * @param input  String that represents general user input before add task details are required.
     * @param scanner Scanner that allows user input to be read.
     * @param taskList TaskList of 3 types of tasks that will be added to in this method.
     */
    private static void handleAdd(String input, Scanner scanner, TaskList taskList, Ui ui) {

        if (input.trim().length() < 4) {

            ui.displayTaskAdd();

        } else {

            String[] splitInput = input.split(" ");

            String taskInput = splitInput[1];

            switch (taskInput) {
            case "todo" -> {

                ui.displayTodoTask();
                String desc = scanner.nextLine();

                try {

                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    Todo newToDo = new Todo(desc);
                    taskList.add(newToDo);
                    ui.displayTaskAdded(input);

                } catch (HoshiException e) {
                    ui.displayError(e.getMessage());
                }

            }
            case "deadline" -> {

                ui.displayDeadlineTask();
                String desc = scanner.nextLine();

                try {

                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    ui.displayDeadlineDue();

                    // take in input
                    String endTime = scanner.nextLine();

                    LocalDate dateTime = LocalDate.parse(endTime);

                    Deadline newDeadline = new Deadline(desc, dateTime);
                    taskList.add(newDeadline);
                    ui.displayTaskAdded(input);

                } catch (HoshiException e) {
                    ui.displayError(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.displayError("Hoshi doesn't understand! Try YYYY-MY-DD format");
                }


            }
            case "event" -> {

                ui.displayEventTask();
                String desc = scanner.nextLine();

                try {
                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    ui.displayEventStart();

                    // take in input
                    String startTime = scanner.nextLine();

                    LocalDate dateTimeStart = LocalDate.parse(startTime);

                    ui.displayEventEnd();

                    // take in input
                    String endTime = scanner.nextLine();

                    LocalDate dateTimeEnd = LocalDate.parse(endTime);

                    Event newEvent = new Event(desc, dateTimeStart, dateTimeEnd);
                    taskList.add(newEvent);
                    ui.displayTaskAdded(input);


                } catch (HoshiException e) {
                    ui.displayError(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.displayError("Hoshi doesn't understand! Try YYYY-MY-DD format");
                }


            }
            default ->

                // in event of invalid input
                    ui.displayError("Hoshi doesn't understand! Please try again with the above keywords");

            }

        }

    }



}
