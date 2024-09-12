package broski.output;

import java.util.ArrayList;

import broski.exception.DeadlineException;
import broski.exception.EventException;
import broski.exception.TodoException;
import broski.exception.WrongInputException;
import broski.parser.DateTimeParser;
import broski.parser.Parser;
import broski.task.Deadline;
import broski.task.Event;
import broski.task.Task;
import broski.task.Todo;
import broski.taskrelated.Archive;
import broski.taskrelated.TaskList;

/**
 * Class that handles output such as println.
 */
public class Ui {
    private static final String LINE = "_________________________________________";

    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Greets the user.
     */
    public String greeting() {
        return "Wassup! I'm Broski!\n"
                + "What can I do for you bro?";
    }

    /**
     * Says bye to the user.
     */
    public void exit() {
        System.out.println("Bye, bro. See ya around!");
    }

    /**
     * Displays the entire list to the user.
     * @param taskList stores all prior task input.
     */
    public void list(TaskList taskList) {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
    }

    /**
     * Displays the entire list to the user.
     * @param archive stores all prior task input.
     */
    public void listArchive(Archive archive) {
        for (int i = 1; i <= archive.size(); i++) {
            System.out.println(i + ". " + archive.get(i - 1));
        }
    }

    /**
     * Informs the user that the task has been marked as done.
     * @param taskList stores all prior task input.
     * @param parser parse user input.
     * @param reply user input.
     */
    public void mark(TaskList taskList, Parser parser, String reply) {
        int i = parser.parseIndex(reply);
        taskList.get(i).mark();
        System.out.println("Solid! Marked as done for you:");
        System.out.println(taskList.get(i));
    }

    /**
     * Informs the user that the task has been marked as incomplete.
     * @param taskList stores all prior task input.
     * @param parser parse user input.
     * @param reply user input.
     */
    public void unmark(TaskList taskList, Parser parser, String reply) {
        int i = parser.parseIndex(reply);
        taskList.get(i).unmark();
        System.out.println("Alright, I've marked the task as undone:");
        System.out.println(taskList.get(i));
    }

    /**
     * Informs the user that the task has been deleted.
     * @param taskList stores all prior task input.
     * @param parser parse user input.
     * @param reply user input.
     */
    public void delete(TaskList taskList, Parser parser, String reply) {
        int i = parser.parseIndex(reply);
        String temp = taskList.get(i).toString();
        taskList.remove(i);
        System.out.println("Gotcha, I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Displays the list of tasks that match what user is looking for.
     * @param taskList stores all prior task input.
     * @param reply user input.
     */
    public void find(TaskList taskList, String reply) {
        String lookingFor = reply.replaceFirst("find ", "");
        ArrayList<Task> tempList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(lookingFor)) {
                tempList.add(taskList.get(i));
            }
        }
        System.out.println("Here are the matching tasks in your list bro:");
        for (int i = 1; i <= tempList.size(); i++) {
            System.out.println(i + ". " + tempList.get(i - 1));
        }
    }

    /**
     * Removes a task from the main taskList and archives it.
     * @param taskList stores all prior task input
     * @param archive old tasks that are to be stored
     * @param parser parser user input
     * @param reply user input
     */
    public void archive(TaskList taskList, Archive archive, Parser parser, String reply) {
        int i = parser.parseIndex(reply);
        Task temp = taskList.get(i);
        taskList.remove(i);
        archive.add(temp);
        System.out.println("Your task has been archived bro:");
        System.out.println(temp);
    }

    /**
     * Main response towards the user regarding the 3 tasks.
     * @param taskList stores all prior task input.
     * @param parser parse user input.
     * @param reply user input.
     * @param dateTimeParser parse date and time.
     * @throws TodoException if there are todo input errors.
     * @throws DeadlineException if there are deadline input errors.
     * @throws EventException if there are event input errors.
     * @throws WrongInputException if there is invalid input.
     */
    public void mainResponse(
            TaskList taskList, Parser parser, String reply,
            DateTimeParser dateTimeParser) throws TodoException, DeadlineException,
            EventException, WrongInputException {
        if (reply.length() == 4 && reply.startsWith("todo")) {
            throw new TodoException();
        }
        if ((reply.length() == 8 && reply.startsWith("deadline"))
                || (reply.startsWith("deadline") && parser.parseLength(reply) != 2)) {
            throw new DeadlineException();
        }
        if ((reply.length() == 5 && reply.startsWith("event"))
                || (reply.startsWith("event") && parser.parseLength(reply) != 3)) {
            throw new EventException();
        }
        if (!(reply.startsWith("todo") || reply.startsWith("deadline")
                || reply.startsWith("event"))) {
            throw new WrongInputException();
        }
        if (reply.length() > 5 && reply.startsWith("todo")) {
            Todo todo = new Todo(reply.replaceFirst("todo ", ""));
            taskList.add(todo);
            System.out.println("Gotcha! I've added this task:");
            System.out.println("  " + todo);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else if (reply.length() > 9 && reply.startsWith("deadline")) {
            Deadline deadline = new Deadline(
                    reply.replaceFirst("deadline ", "").split(" /")[0],
                    dateTimeParser.parseDateTime(reply.split(" /")[1]));
            taskList.add(deadline);
            System.out.println("Gotcha! I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            String[] splitter = reply.split(" /");
            Event event = new Event(
                    splitter[0].replaceFirst("event ", ""),
                    dateTimeParser.parseDateTime(splitter[1]),
                    dateTimeParser.parseDateTime(splitter[2]));
            taskList.add(event);
            System.out.println("Gotcha! I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    /**
     * Bot's response to todo-related input errors.
     */
    public void todoException() {
        System.out.println("Hey, your task description is empty bro.");
    }

    /**
     * Bot's response to deadline-related input errors.
     */
    public void deadlineException() {
        System.out.println("Hey, your task description"
                + " is either empty or your deadline is missing/wonky bro.");
    }

    /**
     * Bot's response to event-related input errors.
     */
    public void eventException() {
        System.out.println("Hey, your task description"
                + " is either empty or your duration is missing/wonky bro.");
    }

    /**
     * Bot's response to input errors that cannot be understood.
     */
    public void wrongInputException() {
        System.out.println("I'm sorry but I can't understand you bro."
                + " Use todo, deadline or event please!");
    }

    /**
     * Bot's response to date/time input errors.
     */
    public void invalidDateTimeException() {
        System.out.println("Invalid date/time format. Please use dd/MM/yyyy HHmm format.");
    }
}
