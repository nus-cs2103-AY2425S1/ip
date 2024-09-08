package elysia.ui;

import elysia.tasks.Deadline;
import elysia.tasks.Event;
import elysia.tasks.TaskList;
import elysia.tasks.Todo;


import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.Objects;
import java.util.Scanner;

/**
 * Manages reading from and writing to a file for the Elysia application.
 * Supports creating a file, saving the current task list to a file, and loading tasks from a file.
 */
public class FileReaderWriter {
    java.nio.file.Path path;
    TaskList taskList;

    /**
     * Constructs a FileReaderWriter with a reference to the task list to be managed.
     * The file used for storing tasks is set to "data.txt" by default.
     *
     * @param taskList The TaskList instance that will be saved to and loaded from the file.
     */
    public FileReaderWriter(TaskList taskList) {
        path = java.nio.file.Paths.get("data.txt");
        this.taskList = taskList;
    }

    /**
     * Creates a new file if it does not already exist.
     *
     * @return A message indicating whether the file was successfully created or if an error occurred.
     */
    public String createFile() {
        String output = "";
        try {
            File myObj = new File(String.valueOf(path));
            if (myObj.createNewFile()) {
                output = ("I created " + myObj.getName() + " for you~");
            }
        } catch (IOException e) {
            output = "Aww something went wrong :(\n";
        }
        return output;
    }

    /**
     * Writes the current task list to the file in a format suitable for storage.
     *
     * @return A message indicating whether the tasks were successfully saved or if an error occurred.
     */
    public String writeFile() {
        String output = "";
        try {
            Files.writeString(path, taskList.toFile(), StandardCharsets.UTF_8);
            output = "Saving all your tasks for you~";
        } catch (IOException e) {
            output = "Aww something went wrong :(\n";
        }
        return output;
    }

    /**
     * Reads tasks from the file and loads them into the task list.
     * Supports loading tasks of type ToDo, Deadline, and Event.
     *
     * @return A message indicating whether the tasks were successfully loaded or if the file was not found.
     */
    public String readFile() {
        String output = "";
        File file = new File(path.toString());
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskInput = sc.nextLine();
                String[] taskParts = taskInput.split("\\|");
                switch(taskParts[0]) {
                case "T":
                    Todo newTodo = new Todo(taskParts[2]);
                    if (Objects.equals(taskParts[1], "1")) {
                        newTodo.updateStatus(true);
                    }
                    taskList.addTask(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(taskParts[2], taskParts[3]);
                    if (Objects.equals(taskParts[1], "1")) {
                        newDeadline.updateStatus(true);
                    }
                    taskList.addTask(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(taskParts[2], taskParts[3], taskParts[4]);
                    if (Objects.equals(taskParts[1], "1")) {
                        newEvent.updateStatus(true);
                    }
                    taskList.addTask(newEvent);
                    break;
                }
            }
            output = "I loaded your past data! Aren't I amazing? Come on praise me!";
        } catch (FileNotFoundException ignored) {}
        return output;
    }
}
