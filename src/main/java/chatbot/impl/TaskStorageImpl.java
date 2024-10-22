package chatbot.impl;

import chatbot.impl.task.Deadline;
import chatbot.impl.task.Event;
import chatbot.impl.task.Task;
import chatbot.impl.task.ToDo;
import chatbot.impl.utils.TaskSorter;
import chatbot.interfaces.TaskStorage;
import chatbot.interfaces.TaskStorageResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskStorageImpl implements TaskStorage<Command> {

    private final Path FILE_PATH;
    private final ArrayList<Task> tasks;

    public TaskStorageImpl(String filePathStr) {
        tasks = new ArrayList<>();
        assert filePathStr != null: "File path cannot be null";
        FILE_PATH = Paths.get(filePathStr);
    }

    /**
     * <p>writes all existing tasks to storage</p>
     * @return a TaskStorageResult containing all the current tasks
     */
    public TaskStorageResult<Command> getTasks(boolean sort) {
        assert tasks != null : "Tasks list should not be null";

        if (tasks.isEmpty()) {
            return new TaskStorageResultImpl("<EMPTY>\n" + "\nThere is nothing for you to do! Yay!");
        }

        ArrayList<Task> taskToPrint = tasks;
        if (sort) {
            taskToPrint = TaskSorter.cloneAndSortByDate(tasks);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskToPrint.size(); i++) {
            sb.append((i + 1)).append(".").append(taskToPrint.get(i)).append("\n");
        }

        return new TaskStorageResultImpl(sb.toString());
    }

    /**
     * <p>writes all existing tasks to storage</p>
     * @param inputParts information input by the user
     * @param command the command of the input
     * @return a TaskStorageResult indicating the status of the save
     */
    public TaskStorageResult<Command> addTask(String[] inputParts, Command command) {
        assert inputParts != null && inputParts.length > 0 : "Input parts cannot be null or empty";
        assert command != null : "Command cannot be null";

        return switch (command) {
            case ToDo -> addToDo(inputParts);
            case Event -> addEvent(inputParts);
            case Deadline -> addDeadline(inputParts);
            default -> null;  // should never happen
        };
    }

    private TaskStorageResult<Command> addToDo(String[] inputParts) {
        assert inputParts != null && inputParts.length > 1 : "Input parts for ToDo must have at least 2 elements";

        String description = arrayJoin(inputParts, 1, inputParts.length);
        if (description.isBlank()) {
            return handleMissingDescription();
        }
        tasks.add(new ToDo(description));
        return handleAddSuccess();
    }

    private TaskStorageResult<Command> addEvent(String[] inputParts) {
        assert inputParts != null && inputParts.length > 5 : "Input parts for Event must have at least 6 elements";

        int idx = Arrays.asList(inputParts).indexOf("/from");
        if (idx == -1) {
            return handleMissingFrom();
        }
        int idx2 = Arrays.asList(inputParts).indexOf("/to");
        if (idx2 == -1) {
            return handleMissingTo();
        }
        String description = arrayJoin(inputParts, 1, Math.min(idx, idx2));
        if (description.isBlank()) {
            return handleMissingDescription();
        }
        String from = arrayJoin(inputParts, idx + 1, idx2 > idx ? idx2 : inputParts.length);
        if (from.isBlank()) {
            return handleMissingFrom();
        }
        String to = arrayJoin(inputParts, idx2 + 1, idx2 > idx ? inputParts.length : idx);
        if (to.isBlank()) {
            return handleMissingTo();
        }
        try {
            tasks.add(new Event(description, from, to));
            return handleAddSuccess();
        } catch (IllegalArgumentException e) {
            return new TaskStorageResultImpl(e.getMessage());
        }
    }

    private TaskStorageResult<Command> addDeadline(String[] inputParts) {
        assert inputParts != null && inputParts.length > 3 : "Input parts for Deadline must have at least 4 elements";

        int idx = Arrays.asList(inputParts).indexOf("/by");
        if (idx == -1) {
            return handleMissingDeadline();
        }
        String description = arrayJoin(inputParts, 1, idx);
        if (description.isBlank()) {
            return handleMissingDescription();
        }
        String by = arrayJoin(inputParts, idx + 1, inputParts.length);
        if (by.isBlank()) {
            return handleMissingDeadline();
        }
        try {
            tasks.add(new Deadline(description, by));
            return handleAddSuccess();
        } catch (IllegalArgumentException e) {
            return new TaskStorageResultImpl(e.getMessage());
        }
    }

    /**
     * <p>Deletes a task</p>
     * @param inputParts the input of the user
     * @return a TaskStorageResult indicating the status of the deletion
     */
    @Override
    public TaskStorageResult<Command> deleteTask(String[] inputParts) {
        assert inputParts != null && inputParts.length > 1 : "Input parts for delete must have at least 2 elements";

        int index = Integer.parseInt(inputParts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            return handleIllegalIndex(index);
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        return handleDeleteSuccess(task);
    }

    /**
     * <p>Finds tasks matching a certain keyword</p>
     * @param inputParts the input of the user
     * @return a TaskStorageResult containing all the matching tasks
     */
    @Override
    public TaskStorageResult<Command> findTasks(String[] inputParts) {
        assert inputParts != null && inputParts.length > 1 : "Input parts for find must have at least 2 elements";

        String phrase = arrayJoin(inputParts, 1, inputParts.length);
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : tasks) {
            if (task.descriptionContains(phrase)) {
                res.add(task);
            }
        }
        return handleFindSuccess(res);
    }

    /**
     * <p>Marks or unmarks a task</p>
     * @param inputParts the input of the user
     * @return a TaskStorageResult indicating the status of the marking
     */
    @Override
    public TaskStorageResult<Command> setTaskDone(String[] inputParts, boolean status) {
        assert inputParts != null && inputParts.length > 1 : "Input parts for setTaskDone must have at least 2 elements";

        int index = Integer.parseInt(inputParts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            return handleIllegalIndex(index);
        }
        tasks.get(index).setDone(status);
        return handleMarkSuccess(index, status);
    }

    /**
     * <p>writes all existing tasks to storage</p>
     * @return a TaskStorageResult indicating the status of the save
     */
    @Override
    public TaskStorageResult<Command> saveTasks() {
        assert tasks != null : "Tasks list should not be null when saving";

        // check if file exists
        // if the file does exist, write to it
        // else if file does not exist, create file and write to it
        try {
            Files.createDirectories(FILE_PATH.getParent());
            File myObj = FILE_PATH.toFile();

            // Write to the file
            FileWriter writer = new FileWriter(myObj, false); // false for overwrite mode
            for (Task task : tasks) {
                writer.write(task.serialize() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            return new TaskStorageResultImpl("Something went wrong when saving");
        }
        return new TaskStorageResultImpl(null);
    }

    /**
     * <p>loads all existing tasks from storage</p>
     * @return a TaskStorageResult indicating the status of the load
     */
    public TaskStorageResult<Command> loadTasks() {
        assert FILE_PATH != null : "File path should not be null when loading tasks";

        try {
            File myObj = FILE_PATH.toFile();
            if (myObj.createNewFile()) {
//                 System.out.println("File created: " + myObj.getName());
            } else {
//                 System.out.println("File already exists. Loading tasks...");
            }

            // Read from the file
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(FILE_PATH));
            for (String line : lines) {
                Task newTask = deserialize(line);
                tasks.add(newTask);
            }
        } catch (IOException e) {
            return new TaskStorageResultImpl("Something went wrong when loading");
        }
        return new TaskStorageResultImpl(null);
    }

    private static Task deserialize(String line) throws IOException {
        assert line != null && !line.isEmpty() : "Line to deserialize cannot be null or empty";

        String[] task = line.split("\\|");
        if (task.length < 3) {
            throw new IOException("Invalid task format");
        }
        Task newTask;
        switch (task[0]) {
        case "T":
            newTask = new ToDo(task[2]);
            break;
        case "D":
            newTask = new Deadline(task[2], task[3]);
            break;
        case "E":
            newTask = new Event(task[2], task[3], task[4]);
            break;
        default:
            newTask = new Task(task[2]);
            break;
        }
        newTask.setDone(task[1].equals("X"));
        return newTask;
    }

    /* ----------------------------- HELPER ----------------------------- */

    private static String arrayJoin(String[] arr, int start, int end) {
        if (start >= end) {
            return "";
        }
        return String.join(" ", Arrays.copyOfRange(arr, start, end));
    }

    /* ----------------------------- HELPER ----------------------------- */

    private TaskStorageResult<Command> handleAddSuccess() {
        String s = "Got it. I've added this task:\n" +
                "  " + tasks.get(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + " task(s) in the list";
        return new TaskStorageResultImpl(s);
    }

    private TaskStorageResult<Command> handleDeleteSuccess(Task task) {
        String s = "Noted. I've removed this task:" +
                "  " + task +
                "\nNow you have " + tasks.size() + " task(s) in the list";
        return new TaskStorageResultImpl(s);
    }

    private TaskStorageResult<Command> handleFindSuccess(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return new TaskStorageResultImpl(sb.toString());
    }

    private TaskStorageResult<Command> handleMarkSuccess(int index, boolean status) {
        String message = status ? "Nice! I've marked this task as done:"
                : "OK! I've marked this task as not done yet:";
        return new TaskStorageResultImpl(message + "\n  " + tasks.get(index));
    }

    private static TaskStorageResult<Command> handleMissingDescription() {
        return new TaskStorageResultImpl("Oopsie! The description of a task cannot be empty.");
    }

    private static TaskStorageResult<Command> handleMissingFrom() {
        return new TaskStorageResultImpl("Uh oh!! The event details cannot be empty.\nIndicate when the event starts by using \"/from <start>\"");
    }

    private static TaskStorageResult<Command> handleMissingTo() {
        return new TaskStorageResultImpl("Oh no!! The event details cannot be empty.\nIndicate when the event starts by using \"/to <end>\"");
    }

    private static TaskStorageResult<Command> handleMissingDeadline() {
        return new TaskStorageResultImpl("Oh no!! The deadline of a task cannot be empty.\nIndicate the deadline by using \"/by <deadline>\"");
    }

    private static TaskStorageResult<Command> handleIllegalIndex(int index) {
        return new TaskStorageResultImpl("Oopsie! The task number " + (index + 1) + " does not exist");
    }

}
