package tars.storage;

import tars.exceptions.TarsException;

import tars.parsers.DeadlineParser;
import tars.parsers.EventParser;

import tars.tasks.Task;
import tars.tasks.TaskList;
import tars.tasks.ToDo;
import tars.tasks.Deadline;
import tars.tasks.Event;



import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the reading and writing of tasks to and from a file.
 *
 * <p>The {@code Storage} class is responsible for loading tasks from a file
 * at the start of the application and saving tasks back to the file upon
 * any update. It uses parsers to handle different types of tasks and their
 * conversions.</p>
 */
public class Storage {

    private final static String FILEPATH = "./tarsTasks.txt";

    DeadlineParser deadlineParser = new DeadlineParser();
    EventParser eventParser = new EventParser();

    /**
     * Reads tasks from the storage file.
     *
     * @return A list of strings representing the saved tasks in the file.
     * @throws TarsException if an I/O error occurs during file creation or reading.
     */
    private List<String> readFile() {
        List<String> savedTasks = new ArrayList<>();

        try {
            File saved = new File(FILEPATH);

            if (!saved.createNewFile()) {
                Scanner scanner = new Scanner(saved);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    savedTasks.add(data);

                }
                scanner.close();
            }

        } catch (IOException e) {
            throw new TarsException("An expected error occurred when creating file");
        }

        return savedTasks;
    }

    /**
     * Converts a list of strings representing tasks into a list of {@link Task} objects.
     *
     * @param taskString A list of strings, where each string represents a task.
     * @return A list of {@link Task} objects created from the provided strings.
     * @throws TarsException if the file format is invalid.
     */
    private List<Task> convertToTask(List<String> taskString){

        List<Task> tasks = new ArrayList<>();

        for (String s: taskString) {
            String[] taskInfo = s.split("\\|", 5);

            Task t;

            String taskType = taskInfo[0];
            String taskDone = taskInfo[1];
            String taskName = taskInfo[2];
            if (taskName.isEmpty()) {
                throw new TarsException("Invalid file format");
            }

            //noinspection EnhancedSwitchMigration
            switch(taskType) {
                case "T":
                    t = new ToDo(taskName);
                    break;
                case "D":
                    LocalDate date = deadlineParser.validateCommand(new String[]{"by", taskInfo[3]});
                    t = new Deadline(taskInfo[2], date);
                    break;
                case "E":
                    LocalDate[] dates = eventParser.validateCommand(new String[]{"from", taskInfo[3]}, new String[]{"to", taskInfo[4]});
                    t = new Event(taskName, dates[0], dates[1]);
                    break;

                default:
                    throw new TarsException("Invalid file format");
            }

            if (taskDone.equals("1")) {
                t.markDone();
            }

            tasks.add(t);
        }

        return tasks;
    }

    /**
     * Updates the task list by reading from the storage file and converting the data to {@link Task} objects.
     *
     * @return A list of {@link Task} objects loaded from the file.
     */
    public List<Task> updateTasks() {
        return convertToTask(readFile());
    }

    /**
     * Saves the current list of tasks to the storage file with each task
     * being saved to a new line.
     *
     * @param taskList The {@link TaskList} containing the tasks to be saved.
     * @throws TarsException if an I/O error occurs during writing to the file.
     */
    public void saveTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        try (FileWriter writer = new FileWriter(FILEPATH);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (Task t : tasks) {
                String taskInfo = t.saveTask();
                bufferedWriter.write(taskInfo);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new TarsException("Unexpected error occurred when writing to file");
        }
    }

}
