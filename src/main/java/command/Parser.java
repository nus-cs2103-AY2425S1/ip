package command;

import java.util.ArrayList;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;


/**
 * Handles the parsing and execution of user commands in the ChatterBox chatbot.
 * It processes the input commands and delegates the execution to the respective
 * methods, modifying the tasklist and updating the storage as necessary.
 */
public class Parser {

    /**
     * Parses and executes user command based on the input string.
     *
     * @param input The command input from the user.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    public void parseExecute(String input, TaskList taskList, Storage storage, Ui ui) {
        if (input.startsWith("mark")) {
            markCommand(input, taskList, storage, ui);
        } else if (input.startsWith("unmark")) {
            unmarkCommand(input, taskList, storage, ui);
        } else if (input.startsWith("todo")) {
            todoCommand(input, taskList, storage, ui);
        } else if (input.startsWith("deadline")) {
            deadlineCommand(input, taskList, storage, ui);
        } else if (input.startsWith("event")) {
            eventCommand(input, taskList, storage, ui);
        } else if (input.startsWith("delete")) {
            deleteCommand(input, taskList, storage, ui);
        } else if (input.startsWith("list")) {
            listCommand(input, taskList, storage, ui);
        } else if (input.startsWith("bye")) {
            byeCommand(input, taskList, storage, ui);
        } else if (input.startsWith("find")) {
            findCommand(input, taskList, storage, ui);
        } else {
            ui.showErrorUnknownCommand();
        }
    }

    
    /**
     * Adds a ToDo task based on the user command.
     *
     * @param input The command input specifying the ToDo task.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void todoCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        if (input.length() <= 4) {
            ui.showErrorEmptyTodoDescription();
            return;
        }
        String description = input.substring(5).trim();
        Task task = new ToDo(description);
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(task, taskList.size());
    }

    /**
     * Adds a Deadline task based on the user command.
     *
     * @param input The command input specifying the Deadline task.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void deadlineCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        if (!input.contains("/by")) {
            ui.showError("Deadline format should be: deadline DESCRIPTION /by DATE");
        }
        if (input.length() == 8) {
            ui.showErrorEmptyDeadlineDescription();
        } else {
            String description = getDeadlineDescription(input);
            String deadline = getDeadlineDate(input);
            Deadline task = new Deadline(description, deadline);
            taskList.addTask(task);
            ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }

    /**
     * Adds an Event task based on the user command.
     *
     * @param input The command input specifying the Event task.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void eventCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        if (!input.contains("/from") || !input.contains("/to")) {
            ui.showError("event format should be: event DESCRIPTION /from DATE/to DATE");
        }
        if (input.length() == 5) {
            ui.showErrorEmptyEventDescription();
        } else {
            String description = getEventDescription(input);
            String dateStart = getEventStartDate(input);
            String dateEnd = getEventEndDate(input);
            Event task = new Event(description, dateStart, dateEnd);
            taskList.addTask(task);
            ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }

    /**
     * Marks task as done based on the user command.
     *
     * @param input The command input specifying the task to mark.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui  The user interface handler for displaying messages.
     */
    private void markCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (isValidTask(taskIndex, taskList.size())) {
                taskList.getTask(taskIndex).markAsDone();
                storage.saveTasks(taskList.getTasks());
                ui.showTaskMarkedAsDone(taskList.getTask(taskIndex));
            } else {
                ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.showError("Command must be followed by a specific task number");
        }
    }

    /**
     * Marks task as not done based on the user command.
     *
     * @param input The command input specifying the task to unmark.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void unmarkCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (isValidTask(taskIndex, taskList.size())) {
                taskList.getTask(taskIndex).markAsNotDone();
                storage.saveTasks(taskList.getTasks());
                ui.showTaskMarkedAsNotDone(taskList.getTask(taskIndex));
            } else {
                ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.showError("Command must be followed by a specific task number");
        }
    }

    /**
     * Deletes a task based on user command.
     *
     * @param input The command input specifying the task to delete.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void deleteCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (isValidTask(taskIndex, taskList.size())) {
                taskList.getTask(taskIndex).markAsNotDone();
                ui.showTaskRemoved(taskList.getTask(taskIndex), taskList.size() - 1);
                taskList.deleteTask(taskIndex);
                storage.saveTasks(taskList.getTasks());
            } else {
                ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.showError("Command must be followed by a specific task number");
        }
    }

     private void findCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            ui.showError("Please provide a description to search for");
        } else {
            ArrayList<Task> matchingTasks = taskList.findTasks(description);
            if (matchingTasks.isEmpty()) {
                ui.showError("No tasks found matching: " + description);
            } else {
                ui.showFindTaskList(matchingTasks);
            }
        }
    }
    
    /**
     * Displays the list of tasks currently in the task list.
     *
     * @param input The command input from the user.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void listCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        ui.showTaskList(taskList.getTasks());
    }

    /**
     * Handles the bye command, ending the chatbot session.
     *
     * @param input The command input from the user.
     * @param taskList The list of tasks managed by the chatbot.
     * @param storage The storage handler responsible for saving tasks.
     * @param ui The user interface handler for displaying messages.
     */
    private void byeCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        ui.showGoodbye();
    }


    private String getDeadlineDescription(String input) {
        int index = input.indexOf("/");

        String description = input.substring(9, index);
        return description;
    }

    private String getDeadlineDate(String input) {
        int tempIndex = input.indexOf("y");
        String deadline = input.substring(tempIndex + 2);
        return deadline;
    }

    private String getEventDescription(String input) {
        int index = input.indexOf("/");
        String description = input.substring(6, index);
        return description;
    }

    private String getEventStartDate(String input) {
        int index = input.indexOf("/");
        String temp = input.substring(index + 1);
        int index2 = temp.indexOf("/");
        int indexM = temp.indexOf("m");
        String dateStart = temp.substring(indexM + 1, index2);
        return dateStart;
    }

    private String getEventEndDate(String input) {
        int index = input.indexOf("/");
        String temp = input.substring(index + 1);
        int index2 = temp.indexOf("/");
        int indexM = temp.indexOf("m");
        String dateEnd = temp.substring(index2 + 4);
        return dateEnd;
    }

    boolean isValidTask(int index, int size) {
        return index >= 0 && index < size;
    }
}
