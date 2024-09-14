package wiggly.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import wiggly.exception.WigglyException;
import wiggly.task.TaskList;
import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * A class representation of the archive command
 */
public class ArchiveCommand extends Command {

    private static final File ARCHIVE_FILE = new File("./data/archive.txt");

    /**
     * Constructs an ArchiveCommand object.
     * This constructor checks if the parent directory of the archive file exists.
     * If not, it creates the necessary directories. It also checks if the archive file
     * itself exists and, if not, attempts to create a new file.
     * If an IOException occurs while trying to create the file, an error message is printed.
     */
    public ArchiveCommand() {

        if (!ARCHIVE_FILE.getParentFile().exists()) {
            ARCHIVE_FILE.getParentFile().mkdirs();
        }

        if (!ARCHIVE_FILE.exists()) {
            try {
                ARCHIVE_FILE.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating an archive file");
            }
        }
    }

    /**
     * Executes the archiving process for the current list of tasks. This method reads all tasks from the
     * {@code Storage}, writes them to the archive file, clears the task list, and saves the cleared list.
     *
     * @param taskList The current list of tasks that will be archived and then cleared.
     * @param ui       The user interface that interacts with the user (not used in this method).
     * @param storage  The storage system responsible for saving and loading tasks.
     * @return A message indicating whether the archiving process was successful or if there was nothing to archive.
     * @throws WigglyException If an error occurs while reading from the storage or writing to the archive file.
     */

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WigglyException {
        try {
            String contentToArchive = storage.contentsToString();

            if (contentToArchive.isEmpty()) {
                return "There is nothing to archive";
            }

            FileWriter archiveWriter = new FileWriter(ARCHIVE_FILE);
            archiveWriter.write(contentToArchive);
            archiveWriter.close();

            taskList.clearTasks();
            storage.save(taskList);
        } catch (IOException e) {
            throw new WigglyException("An error occurred while trying to read from storage!");
        }
        return "Your list of tasks has been archived!";
    }

}
