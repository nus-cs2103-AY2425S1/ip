package main.java.impl;

import main.java.impl.task.Deadline;
import main.java.impl.task.Event;
import main.java.impl.task.Task;
import main.java.impl.task.ToDo;
import main.java.interfaces.TaskStorage;
import main.java.interfaces.TaskStorageResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskStorageImpl implements TaskStorage<Command> {

    private static final Path FILE_PATH = Paths.get("./data/tasks.txt");

    private final ArrayList<Task> tasks;

    public TaskStorageImpl() {
        tasks = new ArrayList<>();
    }

    public TaskStorageResult<Command> getTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }

        if (tasks.isEmpty()) {
            sb.append("<EMPTY>\n");
            sb.append("\nThere is nothing for you to do! Yay!");
        }
        return new TaskStorageResultImpl(sb.toString());
    }

    public TaskStorageResult<Command> addTask(String[] inputParts, Command command) {
        return switch (command) {
            case ToDo -> addToDo(inputParts);
            case Event -> addEvent(inputParts);
            case Deadline -> addDeadline(inputParts);
            default -> null;  // should never happen
        };
    }

    private TaskStorageResult<Command> addToDo(String[] inputParts) {
        String description = arrayJoin(inputParts, 1, inputParts.length);
        if (description.isBlank()) {
            return handleMissingDescription();
        }
        tasks.add(new ToDo(description));
        return handleAddSuccess();
    }

    private TaskStorageResult<Command> addEvent(String[] inputParts) {
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
        tasks.add(new Event(description, from, to));
        return handleAddSuccess();
    }

    private TaskStorageResult<Command> addDeadline(String[] inputParts) {
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
        tasks.add(new Deadline(description, by));
        return handleAddSuccess();
    }

    @Override
    public TaskStorageResult<Command> deleteTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            return handleIllegalIndex(index);
        }
        tasks.remove(index);
        return handleDeleteSuccess(index);
    }

    @Override
    public TaskStorageResult<Command> setTaskDone(String[] inputParts, boolean status) {
        int index = Integer.parseInt(inputParts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            return handleIllegalIndex(index);
        }
        tasks.get(index).setDone(status);
        return handleMarkSuccess(index, status);
    }

    @Override
    public TaskStorageResult<Command> saveTasks() {
        // check if file exists
        // if the file does exist, write to it
        // else if file does not exist, create file and write to it
        try {
            Files.createDirectories(Paths.get("data/"));
            File myObj = new File("data/atlas.txt");
            if (myObj.createNewFile()) {
                // System.out.println("File created: " + myObj.getName());
            } else {
                // System.out.println("File already exists. Writing to file...");
            }

            // Write to the file
            FileWriter writer = new FileWriter(myObj, false); // false for overwrite mode
            for (Task task : tasks) {
                writer.write(task.serialize() + "\n");
            }
            writer.close();

            // System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            return new TaskStorageResultImpl("Something went wrong when saving");
        }
        return new TaskStorageResultImpl(null);
    }

    public TaskStorageResult<Command> loadTasks() {
        try {
            File myObj = new File("data/atlas.txt");
            if (myObj.createNewFile()) {
//                 System.out.println("File created: " + myObj.getName());
            } else {
//                 System.out.println("File already exists. Loading tasks...");
            }

            // Read from the file
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("data/atlas.txt")));
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

    private TaskStorageResult<Command> handleDeleteSuccess(int index) {
        String s = "Noted. I've removed this task:" +
                "  " + tasks.get(index) +
                "\nNow you have " + tasks.size() + " task(s) in the list";
        return new TaskStorageResultImpl(s);
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
