package knight2103;

import knight2103.tasks.Task;
import knight2103.tasks.TaskList;

public class Ui {
    public String showWelcome(String message) {
        return message;
    }

    public String showList(TaskList tasks) {
        return tasks.printToList();
    }

    public String showAdd(Task taskToAdd, TaskList tasks) {
        return "Got it. I've added this task:\n" + taskToAdd + "\n Now you have "
                + tasks.getSize() + " tasks in the list.";
    }

    public String showMark(Task taskAffected) {
        return "Nice! I've marked this task as done:\n" + taskAffected;
    }

    public String showUnmark(Task taskAffected) {
        return "OK, I've marked this task as not done yet:\n" + taskAffected;
    }

    public String showDelete(Task taskAffected, TaskList tasks) {
        return "Noted. I've removed this task:\n" + taskAffected
                + "\n Now you have " + tasks.getSize() + " tasks in the list.";
    }

    public String showFind(TaskList tasks, String searchWord) {
        return "Here are the matching tasks in your list:\n"
                + tasks.searchPrintToList(searchWord);
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}