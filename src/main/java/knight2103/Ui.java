package knight2103;

import knight2103.tasks.Task;
import knight2103.tasks.TaskList;

public class Ui {
    public String showWelcome(String message) {
        return message;
    }

    public String showList(TaskList tasks) {
        return "Here's the list of tasks:\n" + tasks.formatToFullList();
    }

    public String showAdd(Task taskToAdd, TaskList tasks) {
        return "Task added:\n" + taskToAdd + "\n Total number of tasks in list: "
                + tasks.getSize() + "\n Type command \"list\" to see full list of tasks.";
    }

    public String showMark(Task taskAffected) {
        return "Mark this task as done!:\n" + taskAffected
                + "\n Type command \"list\" to see updated list of tasks.";
    }

    public String showUnmark(Task taskAffected) {
        return "Mark this task as not done yet!:\n" + taskAffected
                + "\n Type command \"list\" to see updated list of tasks.";
    }

    public String showDelete(Task taskAffected, TaskList tasks) {
        return "Task removed:\n" + taskAffected + "\n Total number of tasks in list: "
                + tasks.getSize() + "\n Type command \"list\" to see full list of tasks.";
    }

    public String showFind(TaskList tasks, String searchWord) {
        return "Here are the matching tasks in your list:\n"
                + tasks.formatToMatchedList(searchWord);
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}