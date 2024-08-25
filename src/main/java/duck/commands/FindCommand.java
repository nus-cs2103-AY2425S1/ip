package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

import java.util.concurrent.atomic.AtomicInteger;

public class FindCommand extends Command {
    public FindCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        if (message.length() <= 5) {
            throw new DuckException("Quack, you need to provide a keyword to search for!\n");
        }


        String keyword = message.substring(5);
        TaskList matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            System.out.println("Quack, there are no tasks matching that keyword!");
        }

        else {
            System.out.println("Quack, I'm found these related tasks for you!");
            AtomicInteger idx = new AtomicInteger(1);
            matchingTasks.forEach(task -> System.out.println(idx.getAndIncrement() + "." + task.toString()));
        }

        System.out.println();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
