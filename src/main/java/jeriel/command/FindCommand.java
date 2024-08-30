package jeriel.command;

import jeriel.util.*;
import jeriel.task.*;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println(" Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.println(" " + count + "." + tasks.get(i));
                count++;
            }
        }
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
