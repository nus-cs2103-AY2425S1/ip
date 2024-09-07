package hoshi.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import hoshi.exception.HoshiException;
import hoshi.task.Deadline;
import hoshi.task.Event;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;
import hoshi.ui.Ui;

/**
 * Parser class for making sense of user command and calling the relevant function
 */
public class Parser {

    private static final String INPUT_ERROR_MESSAGE = "Hoshi doesn't understand, try a different input?";

    private final Storage storage = new Storage("./data/Hoshi.txt");


    /**
     * Parses all user commands into their respective methods as well as display the bye message once
     * program terminates
     *
     * @param input    the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles all user interaction
     * @return
     */
    public String parseCommand(String input, TaskList taskList, Ui ui) {

        switch (input.split(" ")[0].toLowerCase()) {
        case "initialize":
            return ui.initialize();
        case "bye":
            return ui.displayBye();
        case "list":
            return ui.displayTasks(taskList);
        case "mark":
            return handleMark(input, taskList, ui);
        case "unmark":
            return handleUnmark(input, taskList, ui);
        case "delete":
            return handleDelete(input, taskList, ui);
        case "add":
            return handleAdd(input, taskList, ui);
        case "find":
            return handleFind(input, taskList, ui);
        default:
            return ui.displayError(INPUT_ERROR_MESSAGE);
        }
    }

    /**
     * Handles the mark command when input by user.
     *
     * @param input    the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles all user interaction
     */
    public String handleMark(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() < 5) {
            return ui.displayTaskToMark();
        }

        String trimmedInput = input.trim();

        char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

        // get only the number from the 2nd half of the splitInput
        int markIndex = Character.getNumericValue(taskNo) - 1;

        try {

            // if specified index is not out of bounds
            if (markIndex <= taskList.size() - 1) {

                taskList.get(markIndex).setIsDone(true);
                //ui.displayTaskMarked(taskList.get(markIndex));
                handleSave(ui, taskList);

                return ui.displayTaskMarked(taskList.get(markIndex));

            } else {
                throw new HoshiException("Hoshi doesn't have such a task!");
            }


        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }

    /**
     * Handles the unmark command when input by user.
     *
     * @param input    the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles all user interaction
     */
    public String handleUnmark(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() < 7) {
            return ui.displayTaskToMark();
        }

        String trimmedInput = input.trim();

        char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

        // get only the number from the 2nd half of the splitInput
        int markIndex = Character.getNumericValue(taskNo) - 1;

        try {

            // if specified index is not out of bounds
            if (markIndex <= taskList.size() - 1) {

                // set isDone to false
                taskList.get(markIndex).setIsDone(false);

                handleSave(ui, taskList);

                return ui.displayTaskUnmarked(taskList.get(markIndex));

            } else {
                throw new HoshiException("Hoshi doesn't have such a task!");
            }

        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }

    /**
     * Handles the delete command when input by user.
     *
     * @param input    the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles displaying information to user
     */
    public String handleDelete(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() < 7) {
            return ui.displayError(INPUT_ERROR_MESSAGE);
        }

        String trimmedInput = input.trim();

        char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

        // get only the number from the 2nd half of the splitInput
        int markIndex = Character.getNumericValue(taskNo) - 1;

        try {

            // if specified index is not out of bounds
            if (markIndex <= taskList.size() - 1) {

                Task task = taskList.get(markIndex);
                // delete the task
                taskList.delete(markIndex);

                handleSave(ui, taskList);

                return ui.displayTaskDeleted(task);


            } else {
                throw new HoshiException("Hoshi doesn't have such a task!");
            }

        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }

    /**
     * Adds either to do/deadline/event tasks that are described by the user to TaskList which is to be written to a
     * txt file later
     *
     * @param input    String that represents general user input before add task details are required.
     * @param taskList TaskList of 3 types of tasks that will be added to in this method.
     */
    public String handleAdd(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() < 4) {

            return ui.displayTaskAdd();
        }

        String[] splitInput = input.split(" ");

        String taskInput = splitInput[1];

        switch (taskInput) {
        case "todo" -> {

            ui.displayTodoTask();

            try {

                String desc = splitInput[2];

                if (desc.isEmpty()) {
                    throw new HoshiException("Hoshi doesn't understand! Please follow the example input"
                            + "Add todo Feed Parrot \n");
                }

                Todo newToDo = new Todo(desc);
                taskList.add(newToDo);

                handleSave(ui, taskList);

                return ui.displayTaskAdded(desc);


            } catch (HoshiException e) {
                return ui.displayError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                return ui.displayError("Hoshi wants you to try specifying the task!");
            }

        }
        case "deadline" -> {

            ui.displayDeadlineTask();

            try {

                String desc = splitInput[2];

                if (desc.isEmpty()) {
                    throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                }

                ui.displayDeadlineDue();
                // parse endDate
                LocalDate dateTime = LocalDate.parse(splitInput[3]);

                Deadline newDeadline = new Deadline(desc, dateTime);
                taskList.add(newDeadline);

                handleSave(ui, taskList);

                ui.displayTaskAdded(input);

            } catch (HoshiException e) {
                ui.displayError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.displayError("Hoshi wants you to try specifying the task!");
            } catch (DateTimeParseException e) {
                ui.displayError("Hoshi doesn't understand! Try YYYY-MY-DD format and follow the example layout "
                        + "Add deadline Assignment1 2021-12-20");
            }


        }
        case "event" -> {

            ui.displayEventTask();

            try {

                String desc = splitInput[2];

                if (desc.isEmpty()) {
                    throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                }
                // display event start
                ui.displayEventStart();

                // parse startTime
                LocalDate dateTimeStart = LocalDate.parse(splitInput[3]);

                // display event end
                ui.displayEventEnd();
                // parse endTime
                LocalDate dateTimeEnd = LocalDate.parse(splitInput[4]);

                Event newEvent = new Event(desc, dateTimeStart, dateTimeEnd);
                taskList.add(newEvent);

                handleSave(ui, taskList);

                ui.displayTaskAdded(input);


            } catch (HoshiException e) {
                ui.displayError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.displayError("Hoshi wants you to try specifying the task!");
            } catch (DateTimeParseException e) {
                ui.displayError("Hoshi doesn't understand! Try YYYY-MY-DD format and follow the example layout "
                        + "Add deadline Assignment1 2021-12-20 2022-12-20");
            }
        }
        default ->
                // in event of invalid input
                ui.displayError(INPUT_ERROR_MESSAGE);
        }
        return ui.displayError(INPUT_ERROR_MESSAGE);
    }

    /**
     * Saves the current TaskList and displays a saving error if issues are encountered
     *
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles displaying information to user
     */
    public void handleSave(Ui ui, TaskList taskList) {

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.displaySavingError(e.getMessage());
        }
    }

    /**
     * Adds either to do/deadline/event tasks that are described by the user to TaskList which is to be written to a
     * txt file later
     *
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles displaying information to user
     */
    public String handleFind(String input, TaskList taskList, Ui ui) {

        if (input.trim().length() == 4) {
            return ui.displayError("Hoshi doesn't understand! Please try again with - find {Keyword}");
        }

        String[] parts = input.split(" ");
        String keyword = parts[1].toLowerCase();

        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {

            Task currentTask = taskList.get(i);
            String currentDesc = currentTask.getDesc().toLowerCase();

            if (currentDesc.contains(keyword)) { // Check if the task contains the keyword

                // add currentTask if currentDesc contains the keyword
                matchingTasks.add(currentTask);
            }
        }

        // call ui class to display matching tasks list
        return ui.displayFoundTasks(matchingTasks);

    }


}
