package knight2103;

import knight2103.tasks.Task;
import knight2103.tasks.TaskList;

public class Ui {

    public String showLine() {
        return "_____________";
    }

    public String showBye() {
        return showLine() + "\n" + "Bye. Hope to see you again soon!" + "\n" + showLine();
    }

    public String showLoadingError() {
        return "File cannot be loaded. Create list of tasks instead.";
    }

    public String showList(TaskList tasks) {
        return showLine() + "\n" + tasks.printToList() + showLine();
    }

    public String showAdd(Task taskToAdd, TaskList tasks) {
        return showLine() + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have "
                + tasks.getSize() + " tasks in the list.\n" + showLine();
    }

    public String showMark(Task taskAffected) {
        return showLine() + "\nNice! I've marked this task as done:\n"
                + taskAffected + "\n" + showLine();
    }

    public String showUnmark(Task taskAffected) {
        return showLine() + "\nOK, I've marked this task as not done yet:\n"
                + taskAffected + "\n" + showLine();
    }

    public String showDelete(Task taskAffected, TaskList tasks) {
        return showLine() + "\nNoted. I've removed this task:\n" + taskAffected
                + "\n Now you have " + tasks.getSize() + " tasks in the list.\n" + showLine();
    }

    public String showFind(TaskList tasks, String searchWord) {
        return showLine() + "\nHere are the matching tasks in your list:\n"
                + tasks.searchPrintToList(searchWord) + showLine();
    }
}