package count.action;

import count.TaskList;

/**
 * The Delete class is used to list all the Tasks in the TaskList
 */
public class ListReply extends Action {
    private TaskList ls;

    /**
     * Constructor for ListReply
     * @param ls TaskList being printed out
     */
    public ListReply(TaskList ls) {
        this.ls = ls;
    }

    /**
     * The run method lists all the Tasks in the TaskList
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        if (ls.getList().isEmpty()) {
            return "Croak! Looks like things are empty around here...";
        }

        String ans = "Here are the tasks in your list:\n";
        for (int i = 0; i < ls.getList().size(); i++) {
            if (i != ls.getList().size() - 1) {
                ans += (i + 1) + "." + ls.getList().get(i).toString() + "\n";
            } else {
                ans += (i + 1) + "." + ls.getList().get(i).toString();
            }
        }
        return ans;
    }
}
