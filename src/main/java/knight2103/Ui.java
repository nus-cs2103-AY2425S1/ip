package knight2103;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;

public class Ui {
    public String showWelcome(String message) {
        return message;
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

    public String showList(TaskList tasks) {
        return "Here's the list of tasks:\n" + formatToFullList(tasks);
    }

    public String showFind(TaskList tasks, String searchWord) {
        String listString = formatToMatchedList(tasks, searchWord);
        return listString.isEmpty()
                ? "NIL: There is no matching tasks.\n"
                : "Here are the matching tasks in your list:\n" + listString;
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    private String formatToMatchedList(TaskList tasks, String wordSearch) {
        return formatToList(tasks, wordSearch);
    }

    private String formatToFullList(TaskList tasks) {
        return formatToList(tasks);
    }

    private String formatToList(TaskList tasks, String...filterWords) {
        String stringToReturn = "";
        int bulletPoint = 0;
        for (int i = 0; i < tasks.getSize(); i++) { // IndexOutOfBounds possibility
            Task currentTask = tasks.getTask(i);
            if (!(filterWords.length == 0 || currentTask.getDescription().contains(filterWords[0]))) {
                continue;
            }
            bulletPoint = i + 1;
            stringToReturn += bulletPoint + ". " + currentTask + "\n";
        }
        return stringToReturn;
    }
}