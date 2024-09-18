package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Task;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents an archive command.
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "archive";
    private int index;

    /**
     * Creates an archive command.
     *
     * @param input Array of input strings
     */
    public ArchiveCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        validateInput(input);
        try {
            if (input[1].equals("all")) {
                this.index = -1;
            } else {
                this.index = Integer.parseInt(input[1]) - 1;
            }
        } catch (NumberFormatException e) {
            throw new SkibidiException("Error: delete index must be a number. "
                    + "\nTo delete a task, use the format: delete <index>");
        }
    }
    /**
     * Validates the input.
     *
     * @param input Array of input strings
     */
    public void validateInput(String[] input) throws SkibidiException {
        if (input.length < 2) {
            throw new SkibidiException("Error: archive index must not be empty. "
                    + "\nTo archive a task, use the format: archive <index>");
        }
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException, SkibidiException {
        TaskList archiveList = storage.loadArchive();
        TaskList temp = new TaskList();
        if (index == -1) {
            for (int i = 0; i < taskList.size(); i++) {
                archiveList.addTask(taskList.getTask(i));
                temp.addTask(taskList.getTask(i));
            }
            taskList.clear();
        } else {
            Task task = taskList.deleteTask(index);
            archiveList.addTask(task);
            temp.addTask(task);
        }
        storage.archive(archiveList);
        storage.save(taskList);
        return "Gyatt. I've archived this goon quest:\n" + temp + "\nNow you have "
                + taskList.size() + " quests in the list.";
    }
}
