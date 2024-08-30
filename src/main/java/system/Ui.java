package system;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

public class Ui {
    /**
     * Displays messages greeting the user as well as providing instructions to use the chatbot.
     */
    public void greet() {
        line();
        System.out.println("Hello! I'm Tanjiro!");
        System.out.println("What can I do for you?");
        line();
        System.out.println("Instructions:");
        System.out.println("todo ___");
        System.out.println("deadline ___ /by ____");
        System.out.println("event ___ /from ___ /to ___");
        System.out.println("Please input the date format in: yyyy-mm-dd hhmm.");
        line();
    }

    /**
     * Displays a message saying goodbye as a salutation.
     */
    public void goodbye() {
        System.out.println("Bye! Hope to see you again!");
        line();
    }

    /**
     * Displays a message indicating that the task list is currently empty.
     */
    public void emptyList() {
        System.out.println("There are currently zero tasks in the list!");
    }

    /**
     * Displays a message indicating that the task does not exist.
     */
    public void doesNotExist() {
        System.out.println("Task does not exist!");
    }

    /**
     * Displays a message indicating that the task has already been marked.
     */
    public void alreadyMarked() {
        System.out.println("Task has already been marked!");
    }

    /**
     * Displays a message indicating that the task has already been unmarked.
     */
    public void alreadyUnmarked() {
        System.out.println("Task has already been unmarked!");
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     * The method outputs details about the removed task, such as the name, status, and relevant date information whenever applicable.
     * It also displays the updated number of tasks in the list.
     *
     * @param t Task that has been removed, which can be an instance of ToDos, Deadlines, or Events.
     */
    public void delete_message(Task t) {
        System.out.println("Noted. I've removed this task:");
        if (t instanceof ToDos) {
            if (t.getCurrentStatus()== Task.Status.MARKED) {
                System.out.println("[T][X] " + t.getName());
            } else {
                System.out.println("[T][ ] " + t.getName());
            }
        } else if (t instanceof Deadlines) {
            if (t.getCurrentStatus()== Task.Status.MARKED) {
                System.out.println("[D][X] " + t.getName() + "(by: " + t.getDate() + ")");
            } else {
                System.out.println("[D][ ] " + t.getName() + "(by: " + t.getDate() + ")");
            }
        } else if (t instanceof Events) {
            if (t.getCurrentStatus()== Task.Status.MARKED) {
                System.out.println("[E][X] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
            } else {
                System.out.println("[E][ ] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
            }
        }
        System.out.println("Now you have " + t.get_list_size() + " tasks in the list.");
    }

    /**
     * Displays a message indicating the matching tasks in a list.
     * This method prints a header message followed by the content of the StringBuilder object which contains the found tasks.
     *
     * @param sb StringBuilder object that holds the found tasks to be displayed.
     */
    public void findTaskMessage(StringBuilder sb) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(sb.toString());
    }

    /**
     * Displays a message indicating that the date provided is invalid.
     */
    public void date() {
        System.out.println("The date provided is invalid!");
    }

    /**
     * Displays a message containing the list of tasks in a given specific format.
     *
     * @param information Information string containing the list of tasks.
     */
    public void list_task_message(String information) {
        System.out.println(information);
    }

    /**
     * Displays a message indicating that the task has been marked.
     */
    public void mark_message(String s) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + s);
    }

    /**
     * Displays a message indicating that the task has been unmarked.
     */
    public void unmark_message(String s) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[_] " + s);
    }

    /**
     * Displays a message indicating the line separator.
     */
    private void line() {
        System.out.println("========================================");
    }

    /**
     * Displays a message indicating that the todotask cannot be empty.
     */
    public void empty_todo() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Displays a message indicating that the deadline format is invalid.
     */
    public void empty_deadline() {
        System.out.println("OOPS!!! The format of a deadline is wrong.");
    }

    /**
     * Displays a message indicating that the event format is invalid.
     */
    public void empty_event() {
        System.out.println("OOPS!!! The format of a event is wrong.");
    }

    /**
     * Displays a message indicating that the input format is not understandable.
     */
    public void invalid_input() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
