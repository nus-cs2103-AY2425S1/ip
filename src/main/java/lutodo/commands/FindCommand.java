package lutodo.commands;

import lutodo.parser.Parser;
import lutodo.storage.Storage;
import lutodo.tasklist.TaskList;
import lutodo.tasks.Task;

import static java.lang.Integer.parseInt;

/**
 * Represents a search command to search for tasks.
 */
public class FindCommand extends Command{

    private String fullCommand;
    private String toSearch;

    private void manageToSearch() {
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            System.out.println("You are not telling me which key should I search for :-(");
            return;
        }
        toSearch = Parser.splitTaskInfo(fullCommand)[1];
    }

    private String manageToSearchAndReturn() {
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            return "You are not telling me which task should I search for :-(.";
        }
        toSearch = Parser.splitTaskInfo(fullCommand)[1];
        return "";
    }

    private void searchAndPrint(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any task now :-)");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(toSearch)) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private boolean isMatching(Task task) {
        // find items even if the keyword, deadline date or event start/end time
        // matches the item only partially.
        return task.toString().contains(toSearch);
    }

    private String searchAndReturn(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "You don't have any task now :-)";
        } else {
            String response = "Here are the matching tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                if (isMatching(tasks.get(i))) {
                    response = response + "\n" + (i + 1) + "." + tasks.get(i);
                }
            }
            return response;
        }
    }

    /**
     * Constructs a FindCommand object with the key word to search for.
     *
     * @param fullCommand The command that user inputs.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Performs searching tasks and print matching tasks to users.
     *
     * @param tasks The TaskList to search in.
     * @param storage The Storage object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert fullCommand != null : "task message cannot be null";
        manageToSearch();
        searchAndPrint(tasks);
    }

    /**
     * Performs searching tasks and print matching tasks to users.
     *
     * @param tasks The TaskList to search in.
     * @param storage The Storage object.
     * @return The string that should be shown in the Ui.
     */
    @Override
    public String executeAndRespond(TaskList tasks, Storage storage) {
        assert fullCommand != null : "task message cannot be null";
        if (!manageToSearchAndReturn().isEmpty()) {
            return manageToSearchAndReturn();
        } else {
            return searchAndReturn(tasks);
        }

    }


    /**
     * Returns false since this command is not exit command.
     *
     * @return Whether this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
