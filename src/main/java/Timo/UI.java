package Timo;

/**
 * Deals with outputting information to the user
 */
public class UI {

    /**
     * says goodbye to the user
     */
    public String bye() {
        return "----------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "----------------------------";
    }

    public String listSize(TaskList taskList) {
        return "You now have " + taskList.showList().size() + " tasks in your list";
    }

    /**
     * given a TaskList, prints our the list items
     * @param lst the tasklist
     *
     * @see TaskList
     */
    public String printList(TaskList lst) {

        StringBuilder listString = new StringBuilder();

        for (int i = 1; i <= lst.showList().size(); i++) {
            Task chosen = lst.showList().get(i - 1);
            listString.append(i).append(". ").append(chosen).append("\n");
        }
        return "----------------------------\n"
                + "Here are the tasks in your list:\n"
                + listString
                + "----------------------------";
    }


    /**
     * given Task, prints out that the Task is marked
     * @param chosen the task that is marked
     */
    public String printMark(Task chosen) {
        return "----------------------------\n"
                + "Nice! I've marked this task as done:\n"
                + chosen + "\n"
                + "----------------------------";
    }

    /**
     * given Task, prints out that the Task is unmarked
     * @param chosen the task that is unmarked
     */
    public String printUnmark(Task chosen) {
        return "----------------------------\n"
                + "Nice! I've marked this task as undone:\n"
                + chosen + "\n"
                + "----------------------------";
    }

    /**
     * given Todo and size, prints out that Todo task has been added, and prints out the total size of array
     * @param todo the todo task
     * @param taskList
     */
    public String printTodo(Task todo, TaskList taskList) {
        return "----------------------------\n"
                + "Got it. I've added this task:\n"
                + todo + "\n"
                + listSize(taskList) + "\n"
                + "----------------------------";
    }

    /**
     * given Deadline and size, prints out that Deadline task has been added, and prints out the total size of array
     * @param deadline the deadline task
     * @param taskList the size of the task list
     */
    public String printDeadline(Deadline deadline, TaskList taskList) {
        return "----------------------------\n"
                + "Got it. I've added this task:\n"
                + deadline + "\n"
                + listSize(taskList) + "\n"
                + "----------------------------";
    }

    /**
     * @return Deadline error
     */
    public String printDeadlineError() {
        return "----------------------------\n"
                + "deadline usage: deadline <task> /by <yyyy-mm-dd> <time/24hr format>\n"
                + "----------------------------";
    }

    /**
     * given Event task and size, prints out that Event task has been added, and prints out the total size of the array
     * @param event the event task
     * @param taskList
     */
    public String printEvent(Event event, TaskList taskList) {
        return "----------------------------\n"
                + "Got it. I've added this task:\n"
                + event + "\n"
                + listSize(taskList) + "\n"
                + "----------------------------";
    }

    /**
     * @return Event error
     */
    public String printEventError() {
        return "----------------------------\n"
                + "event usage: event <task> /from <yyyy-mm-dd> <time in 24hr format> "
                + "/to <yyyy-mm-dd> <time in 24hr format>\n"
                + "----------------------------";
    }

    /**
     * given the task and the size, prints out the task that was deleted and the size of the array
     * @param task the task
     * @param taskList
     */
    public String printDelete(Task task, TaskList taskList) {
        return "----------------------------\n"
                + "Got it. I've removed this task:\n"
                + task + "\n"
                + listSize(taskList) + "\n"
                + "----------------------------";
    }

    /**
     * prints out the error
     * @param e the unknown command error
     */
    public String printCommandError(TimoException e) {
        return "----------------------------\n"
                + e.getMessage() + "\n"
                + "----------------------------";
    }

    /**
     * method to print out undo error
     * @return
     */
    public String undoError() {
        return "----------------------------\n"
                + "Nothing to undo :)\n"
                + "----------------------------\n";
    }
}
