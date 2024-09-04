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
public class ParserGui {

    private String response = "";

    public void parseExecute(String input, TaskList taskList, Storage storage, ChatterBoxResponse response) {
        if (input.startsWith("mark")) {
            markCommand(input, taskList, storage, response);
        } else if (input.startsWith("unmark")) {
            unmarkCommand(input, taskList, storage, response);
        } else if (input.startsWith("todo")) {
            todoCommand(input, taskList, storage, response);
        } else if (input.startsWith("deadline")) {
            deadlineCommand(input, taskList, storage, response);
        } else if (input.startsWith("event")) {
            eventCommand(input, taskList, storage, response);
        } else if (input.startsWith("delete")) {
            deleteCommand(input, taskList, storage, response);
        } else if (input.startsWith("list")) {
            listCommand(input, taskList, storage, response);
        } else if (input.startsWith("bye")) {
            byeCommand(input, taskList, storage, response);
        } else if (input.startsWith("find")) {
            findCommand(input, taskList, storage, response);
        } else {
            this.response = response.showErrorUnknownCommand();
        }
    }
    private void markCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.getTask(taskIndex).markAsDone();
                storage.saveTasks(taskList.getTasks());
                response = ui.showTaskMarkedAsDone(taskList.getTask(taskIndex));
            } else {
                response = ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            response = ui.showError("Command must be followed by a specific task number");
        }
    }
    private void unmarkCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.getTask(taskIndex).markAsNotDone();
                storage.saveTasks(taskList.getTasks());
                response = ui.showTaskMarkedAsNotDone(taskList.getTask(taskIndex));
            } else {
                response = ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            response = ui.showError("Command must be followed by a specific task number");
        }
    }
    private void todoCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        if (input.length() <= 4) {
            response = ui.showErrorEmptyTodoDescription();
            return;
        }
        String description = input.substring(5).trim();
        Task task = new ToDo(description);
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        response = ui.showTaskAdded(task, taskList.size());
    }
    private void deadlineCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        if (!input.contains("/by")) {
            response = ui.showError("task.Deadline format should be: deadline DESCRIPTION /by DATE");
        }
        if (input.length() == 8) {
            response = ui.showErrorEmptyDeadlineDescription();
        } else {
            int index = input.indexOf("/");
            int tempIndex = input.indexOf("y");
            String deadline = input.substring(tempIndex + 2);
            String description = input.substring(9, index);
            Deadline task = new Deadline(description, deadline);
            taskList.addTask(task);
            response = ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }
    private void eventCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        if (!input.contains("/from") || !input.contains("/to")) {
            response = ui.showError("event format should be: event DESCRIPTION /from DATE/to DATE");
        }
        if (input.length() == 5) {
            response = ui.showErrorEmptyEventDescription();
        } else {
            int index = input.indexOf("/");
            String description = input.substring(6, index);
            String temp = input.substring(index + 1);
            int index2 = temp.indexOf("/");
            int indexM = temp.indexOf("m");
            String dateStart = temp.substring(indexM + 1, index2);
            String dateEnd = temp.substring(index2 + 4);
            Event task = new Event(description, dateStart, dateEnd);
            taskList.addTask(task);
            response = ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }

    private void deleteCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.getTask(taskIndex).markAsNotDone();
                response = ui.showTaskRemoved(taskList.getTask(taskIndex), taskList.size() - 1);
                taskList.deleteTask(taskIndex);
                storage.saveTasks(taskList.getTasks());
            } else {
                response = ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            response = ui.showError("Command must be followed by a specific task number");
        }
    }

    private void listCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        response = ui.showTaskList(taskList.getTasks());
    }

    private void byeCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        response = ui.showGoodbye();
    }

    private void findCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            response = ui.showError("Please provide a description to search for");
        } else {
            ArrayList<Task> matchingTasks = taskList.findTasks(description);
            if (matchingTasks.isEmpty()) {
                response = ui.showError("No tasks found matching: " + description);
            } else {
                response = ui.showFindTaskList(matchingTasks);
            }
        }
    }

    public String getResponse() {
        return this.response;
    }
}