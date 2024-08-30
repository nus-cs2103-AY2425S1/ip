package wenjieBot.commands;

import wenjieBot.Storage;
import wenjieBot.TaskList;
import wenjieBot.Ui;
import wenjieBot.exceptions.DukeException;
import wenjieBot.tasks.Task;

import java.util.ArrayList;
import java.util.Objects;

public class FindCommand extends Command{
    public FindCommand(boolean isExit, String input) {
        super(false, input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(parts[1])) {
                result.add(task);
            }
        }

        ui.showLine();
        System.out.println(displayList(result));
        ui.showLine();
    }

    public static String displayList(ArrayList<Task> list) {
        String result = "";
        int i = 0;
        while (i < list.size()){
            String newLine = (i + 1) + ". " + list.get(i) + "\n";
            result += newLine;
            i++;
        }
        return result;
    }
}
