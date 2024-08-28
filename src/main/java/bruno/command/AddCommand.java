package bruno.command;

import bruno.Bruno;
import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

public class AddCommand extends Command {
    private String description;
    private Bruno.TaskType type;
    public AddCommand(TaskList tasks, String description, Bruno.TaskType type) {
        super(tasks);
        this.description = description;
        this.type = type;
    }
    @Override
    public void execute() {
        try {
            getTasks().addTask(description, type);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}
