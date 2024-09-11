package count.action;

import count.TaskList;

/**
 * The ListReply class is used to list all the Tasks in the TaskList
 */
public class ListReply extends Action {
    private TaskList ls;

    /**
     * Constructs ListReply object
     * @param ls TaskList being printed out
     */
    public ListReply(TaskList ls) {
        this.ls = ls;
    }

    /**
     * Lists all the Tasks in the TaskList
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        if (ls.getTaskList().isEmpty()) {
            return "Croak! Looks like things are empty around here...";
        }

        String ans = "Here are the tasks in your list:\n";
        for (int i = 0; i < ls.getTaskList().size(); i++) {
            if (i != ls.getTaskList().size() - 1) {
                ans += (i + 1) + "." + ls.getTaskList().get(i).toString() + "\n";
            } else {
                ans += (i + 1) + "." + ls.getTaskList().get(i).toString();
            }
        }
        return ans;
    }
}
