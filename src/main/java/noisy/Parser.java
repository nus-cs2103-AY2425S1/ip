package noisy;

import java.time.LocalDate;

public class Parser {

    public LocalDate parseDate(String date) {
        assert date != null && !date.isEmpty() : "Date string cannot be null or empty.";
        return LocalDate.parse(date);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // The string can be parsed as an integer
        } catch (NumberFormatException e) {
            return false; // The string is not a valid integer
        }
    }


    public String parseInput(String input, TaskList taskList, Storage storage, Ui ui) {

        assert input != null : "Input cannot be null.";
        assert taskList != null : "TaskList cannot be null.";
        assert storage != null : "Storage cannot be null.";
        assert ui != null : "Ui cannot be null.";

        Task task = null; // Initialize as null

        try {
            switch (input.split(" ")[0]) {
                case "bye":
                    return ui.printGoodbye();
                case "list":
                    return ui.printList(taskList);
                case "mark":
                    String[] string = input.split(" ");
                    if (string.length < 2) {
                        throw new NoisyException("OOPS!!! Please specify the task number to mark as done.");
                    }

                    if (!isInteger(string[1])) {
                        throw new NoisyException("OOPS!!! Please enter a valid task number.");
                    }

                    int index = Integer.parseInt(string[1]);
                    if (index < 1 || index > taskList.getListSize()) {
                        throw new NoisyException("OOPS!!! Task number must be between 1 and " + taskList.getListSize() + ".");
                    }

                    taskList.markDoneFromList(index - 1);
                    return ui.printMark(index, taskList);

                case "find":
                    String keyword = input.split(" ", 2)[1];
                    return ui.printFind(taskList, keyword);
                case "todo":
                    if (input.split(" ", 2).length < 2) {
                        throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    task = new Todo(input.split(" ", 2)[1], false);
                    break;
                case "Deadline":
                    input = input.trim();
                    String[] deadlineParts = input.split(" ", 3);
                    task = new Deadline(deadlineParts[1], this.parseDate(deadlineParts[2]));
                    break;
                case "Event":
                    String[] eventParts = input.split(" ", 4);
                    task = new Event(eventParts[1], this.parseDate(eventParts[2]), this.parseDate(eventParts[3]));
                    break;
                case "delete":
                    String[] deleteParts = input.split(" ", 2);
                    index = Integer.parseInt(deleteParts[1]);
                    Task deletedTask = taskList.getTask(index - 1);
                    taskList.deleteFromList(index - 1);
                    int taskListSize = taskList.getListSize();
                    return ui.printDelete(deletedTask, taskListSize);
                case "snooze":
                    String[] snoozeParts = input.split(" ", 3);
                    int snoozeIndex = Integer.parseInt(snoozeParts[1]);
                    LocalDate newDate = this.parseDate(snoozeParts[2]);

                    Task taskToSnooze = taskList.getTask(snoozeIndex - 1);

                    if (taskToSnooze instanceof Snoozable) {
                        ((Snoozable) taskToSnooze).snooze(newDate);
                        storage.saveTasks(taskList.getTasks());
                        return ui.printSnooze(taskToSnooze, newDate);
                    }
                    break;
                default:
                    throw new NoisyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            // Only add and save the task if it was created
            if (task != null) {
                taskList.addToList(task);
                storage.saveTasks(taskList.getTasks());
                return ui.printAdd(task, taskList);
            }

        } catch (NoisyException e) {
            return e.getMessage(); // Return the custom exception message
        }

        return "Unknown command";
    }

}

