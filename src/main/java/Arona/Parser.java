package Arona;

import Arona.AronaExceptions.AronaException;
import Arona.AronaExceptions.DateFormatException;
import Arona.AronaExceptions.MissingArgumentException;

import Arona.Tasks.Task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Parser(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Handles processing of input by calling the necessary classes
     * @param  input  String input from user
     */
    public String parse(String input) throws Exception {

        // Bye command
        if (input.equalsIgnoreCase("bye")) {
            // Process and Reply
            storage.save(taskList);
            return ui.showFarewell();
        }

        // List command
        else if (input.equalsIgnoreCase("list")) {
            // Reply
            return ui.showList(taskList);
        }

        // Delete command
        else if (input.toLowerCase().startsWith("delete ")) {

            // Extract and save task number
            String[] data = input.split("delete ", 2);
            assert data.length == 2 : "delete command should have 2 parts after split";

            // Check if number and save index
            int index;
            try {
                index = Integer.parseInt(data[1]);
            }
            catch (NumberFormatException e) {
                throw new MissingArgumentException(Command.DELETE);
            }

            // Process and Reply
            int listSize = taskList.size() - 1;
            Task removedTask = taskList.remove(index - 1);
            storage.save(taskList);
            return ui.showDelete(listSize, removedTask);
        }

        // Mark and unmark command
        else if (input.toLowerCase().startsWith("mark ") || input.toLowerCase().startsWith("unmark ")) {

            // Extract and save task number
            String[] data = input.split("mark ", 2);
            assert data.length == 2 : "Mark/unmark command should have 2 parts after split";

            // Extract and save action
            boolean action = input.toLowerCase().startsWith("mark");

            // Check if number and save index
            int index;
            try {
                index = Integer.parseInt(data[1]);
            }
            catch (NumberFormatException e) {
                throw new MissingArgumentException(Command.DELETE);
            }

            // Process and Reply
            Task markedTask = taskList.setStatus(index - 1, action);
            storage.save(taskList);
            return ui.showMark(markedTask, action);
        }

        // Todos or events or deadline command
        else if (input.toLowerCase().startsWith("todo ") || input.toLowerCase().startsWith("event ") || input.toLowerCase().startsWith("deadline ")) {

            // Data from command
            String[] data;

            // Extract and save description
            data = input.split(" ", 2);

            // Process
            switch (data[0]) {
                case "todo": {
                    if (data.length != 2 || data[1].isEmpty()) {
                        throw new MissingArgumentException(Command.TODO);
                    }

                    taskList.add(data[1]);
                    break;
                }
                case "deadline": {
                    String[] taskData = data[1].split(" /by ", 2);
                    LocalDate byDate;

                    if (taskData.length != 2) {
                        throw new MissingArgumentException(Command.DEADLINE);
                    }

                    try {
                        byDate = LocalDate.parse(taskData[1]);
                    } catch (DateTimeParseException e) {
                        throw new DateFormatException();
                    }

                    taskList.add(taskData[0], byDate);
                    break;
                }
                case "event": {
                    String[] taskData = data[1].split(" /from | /to ", 3);
                    LocalDate fromDate;
                    LocalDate toDate;

                    if (taskData.length != 3) {
                        throw new MissingArgumentException(Command.EVENT);
                    }

                    try {
                        fromDate = LocalDate.parse(taskData[1]);
                        toDate = LocalDate.parse(taskData[2]);
                    } catch (DateTimeParseException e) {
                        throw new DateFormatException();
                    }

                    taskList.add(taskData[0], fromDate, toDate);
                    break;
                }
            }

            // Process and Reply
            int listSize = taskList.size();
            Task addedTask = taskList.peek();
            storage.save(taskList);
            return ui.showAdd(listSize, addedTask);
        }

        // Find command
        else if (input.toLowerCase().startsWith("find ")) {

            // Extract and save keyword
            String keyword = input.split("find", 2)[1].strip().toLowerCase();

            // Check if empty
            if (keyword.isEmpty()) {
                throw new MissingArgumentException(Command.FIND);
            }

            // Process and Reply
            ArrayList<Task> filteredList = taskList.filter(keyword);
            return ui.showFilteredList(filteredList);
        }

        // Archive
        else if (input.toLowerCase().startsWith("archive ")) {

            // Extract and save file name
            String[] data = input.split("archive ", 2);
            assert data.length == 2 : "archive command should have 2 parts after split";

            // Check if name is more than 260 chars or blank
            if (data[1].isBlank() || data[1].length() > 260) {
                throw new MissingArgumentException(Command.ARCHIVE);
            }

            // Process and Reply
            storage.save(taskList, data[1]);
            taskList.deleteAll();
            storage.save(taskList);
            return ui.showArchive(data[1]);
        }

        // All other unrecognised commands
        else {
            throw new AronaException("Error, unrecognised command.");
        }
    }
}
