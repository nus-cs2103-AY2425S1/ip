import java.util.ArrayList;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Merchandise;
import task.MerchandiseManager;
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

    public void parseExecute(String input, TaskList taskList, MerchandiseManager merchandiseList, Storage storage, ChatterBoxResponse response) {
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
        } else if(input.startsWith("add merchandise")) {
            merchandiseCommand(input, merchandiseList, response);
        } else if(input.startsWith("modify merchandise description")) {
            merchandiseModifyDescription(input, merchandiseList, response);
        } else if(input.startsWith("modify merchandise name")) {
            merchandiseModifyName(input, merchandiseList, response);
        } else if (input.startsWith("merchandise")) {
            merchandiseDisplayCommand(input, merchandiseList, response);
        } else if(input.startsWith("search merchandise")) {
            merchandiseFindCommand(input, merchandiseList, response);
        } else if(input.startsWith("remove")) {
            removeCommand(input, merchandiseList, response);
        } else {
            this.response = response.showErrorUnknownCommand();
        }
    }

    private void todoCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        if (input.length() <= 4) {
            response = ui.showErrorEmptyTodoDescription();
            return;
        }
        String description = input.substring(5).trim();
        assert !description.isEmpty() : "Description cannot be empty";
        Task task = new ToDo(description);
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        response = ui.showTaskAdded(task, taskList.size());
    }
    private void deadlineCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        assert input.contains("/by") : "Deadline format should be: deadline DESCRIPTION /by DATE";
        if (!input.contains("/by")) {
            response = ui.showError("Deadline format should be: deadline DESCRIPTION /by DATE");
            return;
        }
        if (input.length() == 8) {
            response = ui.showErrorEmptyDeadlineDescription();
        } else {
            String deadline = getDeadlineDate(input);
            String description = getDeadlineDescription(input);
            assert !description.isEmpty() : "Description cannot be empty";
            Deadline task = new Deadline(description, deadline);
            taskList.addTask(task);
            response = ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }
    private void eventCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        assert input.contains("/from") && input.contains("/to") : "Event format should be: "
                + "event DESCRIPTION /from DATE/to DATE";
        if (!input.contains("/from") || !input.contains("/to")) {
            response = ui.showError("Event format should be: event DESCRIPTION /from DATE/to DATE");
            return;
        }
        if (input.length() == 5) {
            response = ui.showErrorEmptyEventDescription();
        } else {
            String description = getEventDescription(input);
            String dateStart = getEventStartDate(input);
            String dateEnd = getEventEndDate(input);
            assert !description.isEmpty() : "Description cannot be empty";
            Event task = new Event(description, dateStart, dateEnd);
            taskList.addTask(task);
            response = ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }

    private void markCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        try {
            assert input != null && input.matches("mark \\d+") : "Command must be followed by a task number";
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            assert taskIndex >= 0 && taskIndex < taskList.size() : "Invalid task number";
            if (isValidTask(taskIndex, taskList.size())) {
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
            assert input != null && input.matches("unmark \\d+") : "Command must be followed by a task number";
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            assert taskIndex >= 0 && taskIndex < taskList.size() : "Invalid task number";
            if (isValidTask(taskIndex, taskList.size())) {
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

    private void deleteCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        assert input != null && input.matches("delete \\d+") : "Command must be followed by a task number";
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            assert taskIndex >= 0 && taskIndex < taskList.size() : "Invalid task number";
            if (isValidTask(taskIndex, taskList.size())) {
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

    private void findCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        String description = input.substring(4).trim();
        assert !description.isEmpty() : "Description cannot be empty";
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

    private void listCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        assert taskList != null : "TaskList cannot be null";
        response = ui.showTaskList(taskList.getTasks());
    }

    public String getResponse() {
        return this.response;
    }

    private void byeCommand(String input, TaskList taskList, Storage storage, ChatterBoxResponse ui) {
        response = ui.showGoodbye();
    }

    private void merchandiseCommand(String input, MerchandiseManager merchandiseList, ChatterBoxResponse ui) {
        String[] components = input.split(",", 4);
        if(components.length < 4) {
            response = ui.showIncorrectFormatForMerchandise();
            return;
        }
        String id = components[1].trim();
        String name = components[2].trim();
        String description = components[3].trim();
        Merchandise merchandise = new Merchandise(id, name, description);
        merchandiseList.addMerchandise(merchandise);
        response = ui.showMerchandiseAdded(merchandise, merchandiseList.size());
    }

    private void merchandiseModifyDescription(String input, MerchandiseManager merchandiseList, ChatterBoxResponse ui) {
        String[] components = input.split(",", 3);
        if(components.length < 3) {
            response = ui.showIncorrectFormatForUpdatingDescription();
            return;
        }
        String id = components[1].trim();
        String description = components[2].trim();
        Merchandise merchandise = merchandiseList.updateMerchandiseDescription(id, description);
        response = ui.showMerchandiseDescriptionUpdated(merchandise);
    }

    private void merchandiseModifyName(String input, MerchandiseManager merchandiseList, ChatterBoxResponse ui) {
        String[] components = input.split(",", 3);
        if(components.length < 3) {
            response = ui.showIncorrectFormatForUpdatingDescription();
            return;
        }
        String id = components[1].trim();
        String name = components[2].trim();
        Merchandise merchandise = merchandiseList.updateMerchandiseName(id, name);
        response = ui.showMerchandiseNameUpdated(merchandise);
    }

    private void merchandiseFindCommand(String input, MerchandiseManager merchandiseList, ChatterBoxResponse ui) {
            String name = input.substring(18).trim();
            if (name.isEmpty()) {
                response = ui.showError("Please provide a name to search for");
            } else {
                ArrayList<Merchandise> matchingMerchandise = merchandiseList.findMerchandisebyName(name);
                if (matchingMerchandise.isEmpty()) {
                    response = ui.showError("No merchandise found matching: " + name);
                } else {
                    response = ui.showFindMerchandiseList(matchingMerchandise);
                }
            }
    }

    private void removeCommand(String input, MerchandiseManager merchandiseList, ChatterBoxResponse ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int merchandiseIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (isValidTask(merchandiseIndex, merchandiseList.size())) {
                merchandiseList.getMerchandise(merchandiseIndex);
                response = ui.showMerchandiseRemoved(merchandiseList.getMerchandise(merchandiseIndex), merchandiseList.size() - 1);
                merchandiseList.removeMerchandise(merchandiseIndex);
            } else {
                response = ui.showErrorInvalidMerchandiseNumber();
            }
        } catch (NumberFormatException e) {
            response = ui.showError("Command must be followed by a specific merchandise number");
        }
    }
    private void merchandiseDisplayCommand(String input, MerchandiseManager merchandiseList, ChatterBoxResponse ui) {
        response = ui.showMerchandiseList(merchandiseList.getMerchandise());
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