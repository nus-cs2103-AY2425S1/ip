package shnoop.command;

import java.io.IOException;

import shnoop.exceptions.IncompleteEventOrDeadlineException;
import shnoop.storage.Storage;
import shnoop.tasks.Deadline;
import shnoop.tasks.Event;
import shnoop.tasks.Task;
import shnoop.tasks.TaskList;
import shnoop.tasks.Todo;
import shnoop.ui.Parser;
import shnoop.ui.Ui;


/**
 * Encapsulates all the relevant actions to be taken when an Add Command is issued.
 */
public class AddCommand extends Command {
    private String taskDescription;
    private Parser.Commands taskType = Parser.Commands.UNDEFINED;

    /**
     * Creates an instance of an AddCommand to be executed after.
     *
     * @param taskDescription Description of Task to be added.
     * @param taskType Type of Task to be added.
     */
    public AddCommand(String taskDescription, Parser.Commands taskType) {
        this.taskDescription = taskDescription;
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IncompleteEventOrDeadlineException {
        Task task = null;
        switch (taskType) {
        case TODO:
            task = new Todo(taskDescription);
            break;
        case DEADLINE:
            task = new Deadline(taskDescription);
            break;
        case EVENT:
            task = new Event(taskDescription);
            break;
        default:
            throw new IOException();
        }
        tasks.add(task);
        String result = ui.addTask(task, tasks.size());
        storage.save(tasks, task);
        return result;
    }
}
