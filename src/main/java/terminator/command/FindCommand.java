package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

/**
 * Concrete class representing a command to find an item in the task list.
 */
public class FindCommand extends Command {

    private static final String ERR_MSG = """
            String to search for cannot be empty.\n
            Usage: find <string>""";

    public FindCommand(String input) {
        super(input);
    }

    /**
     * Prints out all tasks in the task list that have a description string that matches the user input.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the string to search for is empty.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }
        String searchText = input.trim();
        ArrayList<Task> temp = new ArrayList<>();
        int matches = 0;
        for (int i = 0; i < todoList.size(); i++) {
            Task t = todoList.get(i);
            if (t.getDescription().contains(searchText)) {
                matches++;
                temp.add(t);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (matches == 0) {
            sb.append("No matches found.");
        } else {
            sb.append("Match found.");
            for (int i = 0; i < temp.size(); i++) {
                sb.append("\n" + String.valueOf(i + 1) + "." + temp.get(i));
            }
        }
        return sb.toString();
    }
}
