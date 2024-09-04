package system;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.time.LocalDateTime;

public class Ui {
    /**
     * Displays messages greeting the user as well as providing instructions to use the chatbot.
     */
    public String greet() {
        String response = "Hello! I'm Tanjiro!\n" +
                "What can I do for you?\n" +
                "Instructions:\n" +
                "todo ___\n" +
                "deadline ___ /by ____\n" +
                "event ___ /from ___ /to ___\n" +
                "Please input the date format in: yyyy-mm-dd hhmm.";

        return response;
    }

    /**
     * Displays a message saying goodbye as a salutation.
     */
    public String goodbye() {
        return "Bye! Hope to see you again!";
    }

    /**
     * Displays a message indicating that the task list is currently empty.
     */
    public String emptyList() {
        return "There are currently zero tasks in the list!";
    }

    /**
     * Displays a message indicating that the task does not exist.
     */
    public String doesNotExist() {
        return "Task does not exist!";
    }

    /**
     * Displays a message indicating that the task has already been marked.
     */
    public String alreadyMarked() {
        return "Task has already been marked!";
    }

    /**
     * Displays a message indicating that the task has already been unmarked.
     */
    public String alreadyUnmarked() {
        return "Task has already been unmarked!";
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     * The method outputs details about the removed task, such as the name, status, and relevant date information whenever applicable.
     * It also displays the updated number of tasks in the list.
     *
     * @param t Task that has been removed, which can be an instance of ToDos, Deadlines, or Events.
     */
    public String delete_message(Task t) {
        StringBuilder response = new StringBuilder();
        DateTimeSystem dateTimeSystem = new DateTimeSystem();
        response.append("Noted. I've removed this task:\n");
        if (t instanceof ToDos) {
            if (t.getCurrentStatus() == Task.Status.MARKED) {
                response.append("[T][X] ").append(t.getName()).append("\n");
            } else {
                response.append("[T][ ] ").append(t.getName()).append("\n");
            }
        } else if (t instanceof Deadlines) {
            if (t.getCurrentStatus() == Task.Status.MARKED) {
                response.append("[D][X] ").append(t.getName()).append(" (by: ").append(dateTimeSystem.format(t.getDate())).append(")\n");
            } else {
                response.append("[D][ ] ").append(t.getName()).append(" (by: ").append(dateTimeSystem.format(t.getDate())).append(")\n");
            }
        } else if (t instanceof Events) {
            if (t.getCurrentStatus() == Task.Status.MARKED) {
                response.append("[E][X] ").append(t.getName()).append(" (from: ").append(dateTimeSystem.format(t.getStart())).append(" to: ").append(dateTimeSystem.format(t.getEnd())).append(")\n");
            } else {
                response.append("[E][ ] ").append(t.getName()).append(" (from: ").append(dateTimeSystem.format(t.getStart())).append(" to: ").append(dateTimeSystem.format(t.getEnd())).append(")\n");
            }
        }
        response.append("Now you have ").append(t.get_list_size()).append(" tasks in the list.");

        return response.toString();
    }

    /**
     * Displays a message indicating the matching tasks in a list.
     * This method prints a header message followed by the content of the StringBuilder object which contains the found tasks.
     *
     * @param sb StringBuilder object that holds the found tasks to be displayed.
     */
    public String findTaskMessage(StringBuilder sb) {
        return "Here are the matching tasks in your list:\n" +
                sb.toString();
    }

    /**
     * Displays a message indicating that the date provided is invalid.
     */
    public String invalidDate() {
        return "The date provided is invalid! Please type in this format: yyyy-mm-dd HHmm. Thanks!";
    }

    /**
     * Displays a message indicating that the deadline format provided is invalid.
     */
    public String invalidDeadlineInput() {
        return "Deadline input is invalid. Please type in this format: deadline ___ /by yyyy-mm-dd HHmm. Thanks!";
    }

    /**
     * Displays a message indicating that the event format provided is invalid.
     */
    public String invalidEventInput() {
        return "Event input is invalid. Please type in this format: event ___ /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm. Thanks!";
    }

    public String twentyFourHourClock() {
        return "Please enter the time in the 24-hour time format. Thanks!";
    }

    /**
     * Displays a message containing the list of tasks in a given specific format.
     *
     * @param information Information string containing the list of tasks.
     */
    public String list_task_message(String information) {
        return information;
    }

    /**
     * Displays a message indicating that the task has been marked.
     */
    public String mark_message(String s) {
        return "Nice! I've marked this task as done:\n" +
                "[X] " + s;
    }

    /**
     * Displays a message indicating that the task has been unmarked.
     */
    public String unmark_message(String s) {
        return "OK, I've marked this task as not done yet:\n" +
                "[_] " + s;
    }

    /**
     * Displays a message indicating that the todotask cannot be empty.
     */
    public String empty_todo() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    public String addTodoMessage(ToDos t) {
        return "Got it. I've added this task:\n" +
                "[T][_] " + t.getName() +
                "\nNow you have " + t.get_list_size() + " tasks in the list.";
    }

    public String addDeadlineMessage(Deadlines d, LocalDateTime date) {
        DateTimeSystem dateTimeSystem = new DateTimeSystem();
        return "Got it. I've added this task:\n" +
                "[D][_] " + d.getName() + "(by: " + dateTimeSystem.format(date) + ")\n" +
                "Now you have " + d.get_list_size() + " tasks in the list.";
    }

    public String addEventMessage(Events e, LocalDateTime start, LocalDateTime end) {
        DateTimeSystem dateTimeSystem = new DateTimeSystem();

        return "Got it. I've added this task:\n" +
                "[E][_] " + e.getName() + "(from: " + dateTimeSystem.format(start) + " to: " + dateTimeSystem.format(end) + ")\n" +
                "Now you have " + e.get_list_size() + " tasks in the list.";
    }

    /**
     * Displays a message indicating that the deadline format is invalid.
     */
    public String empty_deadline() {
        return "OOPS!!! The format of a deadline is wrong.";
    }

    /**
     * Displays a message indicating that the event format is invalid.
     */
    public String empty_event() {
        return "OOPS!!! The format of a event is wrong.";
    }

    /**
     * Displays a message indicating that the input format is not understandable.
     */
    public String invalid_input() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
