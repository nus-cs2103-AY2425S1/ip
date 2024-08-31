package bob;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(task);
            storage.save(tasks);
            ui.show(
                    String.format("Here's the added task:\n" +
                            "\t%s\n" +
                            "Now you have %s tasks in the list.", task, tasks.size())
            );
        } catch (IOException e) {
            ui.show("I can't remember that for some reason T T");
        }
    }
}

