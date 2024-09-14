package command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import exception.InvalidArgumentException;
import exception.LevelHundredException;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import utility.Parser;

/**
 * The FindCommand class finds tasks with matching keyword
 */
public class FindCommand extends UserCommand {
    private String createFindResponse(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found...";
        } else {
            StringBuilder res = new StringBuilder("Here are the matching tasks in your list\n");
            for (int i = 0; i < tasks.size(); i++) {
                res.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }

            return res.toString();
        }
    }

    /**
     * Finds tasks in the task list that matches the keyword
     * Optionally, the user can find tasks of a certain type (E.g. Event)
     *
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        List<Predicate<Task>> predicates = new ArrayList<>();
        String[] tokens = new String[] { "/byType" };
        String[] tokenValues = Parser.parseInputByTokens(userInput, tokens);

        String keyword = tokenValues[0];
        if (keyword.isEmpty()) {
            LevelHundredException e = new InvalidArgumentException("find", keyword);
            this.setResponse(e.toString());
            ui.printException(e);
            return;
        }
        assert (keyword != "") : "Keyword should not be empty";
        predicates.add(TaskList.getKeywordPredicate(keyword));

        String taskType = tokenValues[1];
        if (!taskType.isEmpty()) {
            predicates.add(TaskList.getTaskTypePredicate(taskType));
        }

        ArrayList<Task> tasks = taskList.filterByPredicates(predicates);
        this.setResponse(createFindResponse(tasks));

        ui.printMatchingTasks(tasks);
    }
}