package momo.command;

import static momo.Momo.FILE_PATH;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import momo.Storage;
import momo.StorageException;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;


/**
 * Class for handling the archiving of a task or archiving of all tasks.
 */
public class ArchiveCommand extends Command {
    public static final int COMMAND_PREFIX_OFFSET = 7;
    public static final String ARCHIVE_FILE_PATH = "data/archive.txt";

    /**
     * Runs the archive command based on user input, returning the string showing the confirmation of the exact task
     * added. It archives either one task based on index or all tasks based on what the user inputs.
     *
     * @param input The user input that triggers the archive command.
     * @param tasks The list of tasks to be processed by the archive command.
     * @param storage The storage handler responsible for file operations related to tasks.
     * @return A string message indicating the result of the archive command, typically a success message.
     * @throws InvalidCommandException If the user input is invalid or cannot be processed.
     * @throws StorageException If there is an error interacting with the task storage (e.g., file read/write issues).
     */
    public static String run(String input, TaskList tasks, Storage storage) throws InvalidCommandException,
            StorageException {

        if (input.equals("archive all")) {
            try {
                archiveAllTasks(tasks);
                return "Noted. I've archived all tasks";
            } catch (IOException e) {
                throw new StorageException(e.getMessage());
            }
        }

        int archiveIndex = checkIndex(input, tasks);
        Task archivedTask = archiveOneTask(archiveIndex, tasks, storage);

        return "Noted. I've archived this task:\n " + archivedTask + String.format("\n"
                + "Now you have %d task(s)"
                + " in the active list%n", tasks.getCount());

    }

    /**
     * Archives all tasks from the current task list by appending them to an archive file
     * and then clearing the current task list. This method reads the current tasks from
     * the file, writes them to an archive file, and then clears the tasks from both the
     * task list and the task file.
     *
     * @param tasks The TaskList of tasks to be archived and cleared.
     * @throws IOException If an I/O error occurs while reading from or writing to the files.
     */
    public static void archiveAllTasks(TaskList tasks) throws IOException {
        Files.write(Paths.get("data/archive.txt"), Files.readAllLines(Paths.get(FILE_PATH)),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        tasks.getTaskList().clear();

        new FileWriter(FILE_PATH, false).close();


    }

    /**
     * Archives all tasks from the current task list by appending them to an archive file
     * and then clearing the current task list. This method reads the current tasks from
     * the file, writes them to an archive file, and then clears the tasks from both the
     * task list and the task file.
     *
     * @param tasks The TaskList of tasks to be archived and cleared.
     * @throws IOException If an I/O error occurs while reading from or writing to the files.
     */
    public static Task archiveOneTask(int index, TaskList tasks, Storage storage) throws StorageException {

        Task archivedTask = tasks.getTask(index);

        try {
            storage.addTaskToFile(ARCHIVE_FILE_PATH, archivedTask.toFileString());
            tasks.deleteTask(index);
            storage.rewriteTasksToFile(FILE_PATH, tasks.getTaskList());

            return archivedTask;


        } catch (IOException ioe) {
            throw new StorageException(ioe.getMessage());
        }
    }


    /**
     * Validates if user input index provided to archive is a valid number and is a valid number in the list.
     *
     * @param input Represents the user input string.
     * @param tasks Represents the list of tasks.
     * @return The integer representing the index to be parsed.
     * @throws InvalidCommandException Exception thrown when string input is not formatted correctly.
     */
    public static int checkIndex(String input, TaskList tasks) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(input.substring(COMMAND_PREFIX_OFFSET).trim()) - 1;
            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only archive a number your task list contains");
            }

            return index;

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out mortal: You did not format your number properly...");
        }
    }








}
