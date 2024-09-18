package pochat.bot;

import pochat.tasks.Task;

class Ui {
    public String getGoodbyeMessage() {
        return "Bye! Hope to see you again soon :)";
    }

    public String getAddTaskMessage(Task task, int numTasks) {
        return "Got it. I've added this task:\n" + task + "\nNow you have "
                + numTasks + " tasks in the list.";

    }

    public String getListOfTextsEntered(TaskList taskList) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            message.append((i + 1) + ". " + taskList.get(i) + "\n");
        }

        return message.toString();
    }

    public String getMarkTaskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String getMarkTaskUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public String getInvalidInputMessage() {
        return "Please enter a valid input and try again! Some examples of valid inputs are:\n"
                + "todo [description]\ndeadline [description] /by [dd/mm/yyyy HHMM]\n"
                + "event [description] /from [dd/mm/yyyy HHMM] /to [dd/mm/yyyy HHMM]\n"
                + "For more commands, refer to the project's README";
    }

    public String getDeleteTaskMessage(Task task, int numTasks) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have "
                + numTasks + " tasks in the list.";
    }

    public String getDuplicateTaskMessage() {
        return "Sorry! You already have this task in your list. Please try adding "
                + "another task";
    }

    public String findMatchingTasks(String keyword, TaskList taskList) {
        return taskList.findMatchingTasks(keyword);
    }
}
