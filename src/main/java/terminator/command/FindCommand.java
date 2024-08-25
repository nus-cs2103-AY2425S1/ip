package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

/**
 * Concrete class representing a command to find an item in the task list.
 */
public class FindCommand extends Command {

    private static final String ERR_MSG = """
            String to search for cannot be empty. 
            
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
    public void execute(ArrayList<Task> todoList) throws TerminatorException {
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
        if (matches == 0) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Match found.");
            for (int i = 0; i < temp.size(); i++) {
                System.out.println(String.valueOf(i + 1) + "." + temp.get(i));
            }
        }
    }
}
