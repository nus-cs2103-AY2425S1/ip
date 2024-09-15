package nebula.command;

import nebula.storage.Storage;
import nebula.task.Deadline;
import nebula.task.Task;
import nebula.task.TaskList;
import nebula.ui.Parser;
import nebula.ui.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        String taskInformation = Parser.splitCommandAndTaskDescription(command);
        String taskDescriptionDeadline = Parser.splitDeadlineCommand(taskInformation)[0];
        String taskDeadline = Parser.splitDeadlineCommand(taskInformation)[1];

        Task newDeadline = new Deadline(taskDescriptionDeadline, taskDeadline);
        String addedDeadline = tasks.addTask(newDeadline);

        System.out.println(addedDeadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
