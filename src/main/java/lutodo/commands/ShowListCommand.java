package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

public class ShowListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any task now ~(∠・ω< )⌒★");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("Tips: Tasks marked as [X] are already completed (∠・ω< )");
        return;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
