package Arona;

import java.time.LocalDate;

public class Parser {
    public Parser() {}

    public static void parse(String input, Storage storage, TaskList taskList, Ui ui) throws Exception {
        // Bye command
        if (input.equalsIgnoreCase("bye")) {

            // Save task list to data.txt
            storage.save(taskList);

            // Reply
            ui.showFarewell();
        }

        // List command
        else if (input.equalsIgnoreCase("list")) {
            ui.showList(taskList);
        }

        // Delete
        else if (input.toLowerCase().startsWith("delete")) {

            // Extract and save task number
            String[] data = input.split("delete", 2);

            // Check if number
            if (!data[1].stripLeading().matches("^[1-9]\\d*$")) {
                throw new AronaException("Error! Please input a positive number");
            }

            // Save index as integer
            int index = Integer.parseInt(data[1].stripLeading());

            // Check if exist
            if ((index > taskList.size())) {
                throw new AronaException("Error! This task does not exist!");
            }

            // Process
            Task task = taskList.remove(index - 1);

            // Reply
            ui.showDelete(taskList.size(), task);
        }

        // Mark and unmark command
        else if (input.toLowerCase().startsWith("mark") || input.toLowerCase().startsWith("unmark")) {

            // Extract and save task number
            String[] data = input.split("mark", 2);

            // Extract and save action
            boolean action = input.toLowerCase().startsWith("mark");

            // Check if number
            if (!data[1].stripLeading().matches("^[1-9]\\d*$")) {
                throw new AronaException("Error! Please input a positive number");
            }

            // Save index as integer
            int index = Integer.parseInt(data[1].stripLeading());

            // Check if exist
            if ((index > taskList.size())) {
                throw new AronaException("Error! This task does not exist!");
            }

            // Process
            Task task = taskList.setStatus(index - 1, action);

            // Reply
            ui.showMark(task, action);
        }

        // Todos or events or deadline command
        else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("event") || input.toLowerCase().startsWith("deadline")) {
            // Data from command
            String[] data;

            // Extract and save description
            data = input.split(" ", 2);

            // Check if empty
            if (data.length == 1 || data[1].isBlank()) {
                throw new AronaException("Error! Please input a task description");
            }

            // Process
            switch (data[0]) {
                case "todo": {
                    taskList.add(data[1]);
                    break;
                }
                case "deadline": {
                    String[] taskData = data[1].split(" /by ", 2);
                    LocalDate byDate;
                    if (taskData.length == 2) {
                        try {
                            byDate = LocalDate.parse(taskData[1]);
                        } catch (Exception e) {
                            throw new AronaException("Please input your date in yyyy-mm-dd format");
                        }
                        taskList.add(taskData[0], byDate);
                    } else {
                        throw new AronaException("Error! Please specify a by");
                    }
                    break;
                }
                case "event": {
                    String[] taskData = data[1].split(" /from | /to ", 3);
                    LocalDate fromDate;
                    LocalDate toDate;
                    if (taskData.length == 3) {
                        try {
                            fromDate = LocalDate.parse(taskData[1]);
                            toDate = LocalDate.parse(taskData[2]);
                        } catch (Exception e) {
                            throw new AronaException("Please input your date in yyyy-mm-dd format");
                        }
                        taskList.add(taskData[0], fromDate, toDate);
                    } else {
                        throw new AronaException("Error! Please specify a from and to");
                    }
                    break;
                }
            }

            // Reply
            ui.showAdd(taskList.size(), taskList.peek());
        }

        // All other unrecognised commands
        else {
            throw new AronaException("Error, unrecognised command.");
        }
    }
}
