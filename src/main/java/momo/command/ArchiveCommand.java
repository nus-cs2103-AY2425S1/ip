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
 * Class for handling the archiving of a task
 */
public class ArchiveCommand extends Command {
    public static final int COMMAND_PREFIX_OFFSET = 7;

    public static String run(String input, TaskList tasks, Storage storage) throws InvalidCommandException,
            StorageException {

        // if "Archive" only, archive all tasks
        if (input.equals("archive all")) {
            try {
                archiveAllTasks(tasks, storage);
                return "Noted. I've archived all tasks";
            } catch (IOException e) {
                throw new StorageException(e.getMessage());
            }
        }

        // if "Archive index", archive specific index
        try {
            int index = Integer.parseInt(input.substring(COMMAND_PREFIX_OFFSET).trim()) - 1;
            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only archive a number your task list contains");
            }

            Task archivedTask = tasks.getTask(index);

            try {
                storage.addTaskToFile("data/archive.txt", archivedTask.toFileString());
                tasks.deleteTask(index);
                storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

                return "Noted. I've archived this task:\n " + archivedTask
                        + String.format("\nNow you have %d task(s)"
                        + " in the active list%n", tasks.getCount());

            } catch (IOException ioe) {
                throw new StorageException(ioe.getMessage());
            }

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did not format your number properly...");
        }
    }

    public static void archiveAllTasks(TaskList tasks, Storage storage) throws IOException {
        Files.write(Paths.get("data/archive.txt"), Files.readAllLines(Paths.get(FILE_PATH)),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        tasks.getTaskList().clear();

        new FileWriter(FILE_PATH, false).close();


    }





}
