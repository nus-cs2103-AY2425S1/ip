package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    String searchContent;
    public FindCommand (String searchContent) {
        super();
        this.searchContent = searchContent;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException {
        Ui ui = new Ui();

        ArrayList<Task> results = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskDescription = task.getDescription().toLowerCase();

            if (taskDescription.contains(searchContent)) {
                results.add(task);
            }
        }

        ui.printList(results);
    }
}
