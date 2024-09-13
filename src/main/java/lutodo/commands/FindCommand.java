package lutodo.commands;

import lutodo.parser.Parser;
import lutodo.storage.Storage;
import lutodo.tasklist.TaskList;

import static java.lang.Integer.parseInt;

/**
 * Represents a search command to search for tasks.
 */
public class FindCommand extends Command{

    private String fullCommand;
    private String toSearch;

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
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            System.out.println("You are not telling me which key should I search for :-(");
        }
        toSearch = Parser.splitTaskInfo(fullCommand)[1];
        assert toSearch != null : "search key cannot be null";
        if (tasks.isEmpty()) {
            System.out.println("You don't have any task now :-)");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i - 1).getDescription().contains(toSearch)) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
        }
    }

    /**
     * Performs searching tasks and print matching tasks to users.
     *
     * @param tasks The TaskList to search in.
     * @param storage The Storage object.
     */
    @Override
    public String executeAndRespond(TaskList tasks, Storage storage) {
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            return "You are not telling me which task should I search for :-(.";
        }
        if (tasks.isEmpty()) {
            return "You don't have any task now :-)";
        } else {
            String response = "Here are the matching tasks in your list:";
            for (int i = 1; i <= tasks.size(); i++) {
                if (tasks.get(i - 1).getDescription().contains(toSearch)) {
                    response = response + "\n" + i + "." + tasks.get(i - 1);
                }
            }
            return response;
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
