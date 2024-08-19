package tasklist;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * List of tasks.
     */
    private List<tasklist.Task> list;

    /**
     * Different types of tasks
     */
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Different types of commands
     */
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Process user input (if not exiting program). Able to add new task to 
     * list based in types, mark and unmark task done state. Then returns 
     * action done as string.
     * 
     * @param input
     * @return The action completed
     */
    public String process(String input) {
        Task toAdd = null;
        String[] token = input.split(" ", 2);
        switch (token[0].toLowerCase()) {
            case MARK:
                Task done = list.get(Integer.parseInt(token[1]) - 1);
                return done.markAsDone();

            case UNMARK:
                Task undone = list.get(Integer.parseInt(token[1]) - 1);
                return undone.unmarkAsDone();

            case TODO:
                toAdd = new Todo(token[1]);
                break;
            
            case DEADLINE:
                token = token[1].split(" /by ", 2);
                toAdd = new Deadline(token[0], token[1]);
                break;

            case EVENT:
                token = token[1].split(" /", 3);
                if (token[1].startsWith("from")) {
                    toAdd = new Event(token[0], token[1].substring(5), token[2].substring(3));
                } else {
                    toAdd = new Event(token[0], token[2].substring(3), token[1].substring(5));
                }
                break;

            default:
                return "    Fail to add...";
        }
        list.add(toAdd);
        return "    Got it! I've added this task:\n      " + toAdd
            + "\n    Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * String representation of TaskList
     * 
     * @return The list of tasks
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += "    " + (i + 1) + "." + list.get(i) + "\n";
        }
        return output.stripTrailing();
    }
}