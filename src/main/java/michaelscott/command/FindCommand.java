package michaelscott.command;

import michaelscott.utils.MichaelScottException;
import michaelscott.task.TaskList;
import michaelscott.task.Task;

import java.util.ArrayList;

public class FindCommand implements Command{
    private final String keyWord;

    public FindCommand(String keyWord) throws MichaelScottException {
        if (keyWord.trim().isEmpty()) {
            throw new MichaelScottException("The search keyword cannot be empty.");
        }
        this.keyWord = keyWord.trim().toLowerCase();
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException{
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.toString().toLowerCase().contains(this.keyWord)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(i + 1).append(".").append(matchingTasks.get(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }
}
