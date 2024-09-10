package broski.output;

import java.util.Scanner;

import broski.exception.DeadlineException;
import broski.exception.EventException;
import broski.exception.InvalidDateTimeException;
import broski.exception.TodoException;
import broski.exception.WrongInputException;
import broski.parser.DateTimeParser;
import broski.parser.Parser;
import broski.taskRelated.TaskList;
import broski.taskRelated.TaskManager;

/**
 * A class to hold the general functions to run the chatbot.
 */
public class Broski {
    private final Scanner scanner = new Scanner(System.in);
    private TaskList taskList;
    private final TaskManager manager = new TaskManager();
    private final DateTimeParser dateTimeParser = new DateTimeParser();
    private final Parser parser = new Parser();
    private final Ui ui = new Ui();

    /**
     * Starts the chatbot with the initial prompts.
     */
    public String start() {
        this.taskList = new TaskList(this.manager.loadTasks());
        return ui.greeting();
    }

    /**
     * The main content of the chatbot and all its possible responses.
     * @throws TodoException if there are todo input errors.
     * @throws DeadlineException if there are deadline input errors.
     * @throws EventException if there are event input errors.
     * @throws WrongInputException if there is invalid input.
     */
    public void chatbot(String reply) throws TodoException, DeadlineException,
            EventException, WrongInputException {
        if (reply.equals("list")) {
            ui.list(taskList);
        } else if (reply.equals("bye")) {
            ui.exit();
        } else if (reply.length() > 5 && reply.startsWith("mark")) {
            ui.mark(taskList, parser, reply);
            this.save();
        } else if (reply.length() > 7 && reply.startsWith("unmark")) {
            ui.unmark(taskList, parser, reply);
            this.save();
        } else if (reply.length() > 7 && reply.startsWith("delete")) {
            ui.delete(taskList, parser, reply);
            this.save();
        } else if (reply.length() > 5 && reply.startsWith("find")) {
            ui.find(taskList, reply);
        } else {
            assert !reply.startsWith("list") : "reply should not start with list";
            ui.mainResponse(taskList, parser, reply, dateTimeParser);
            this.save();
        }
    }

    /**
     * Saves all content in the taskList to the hard disk.
     */
    public void save() {
        this.manager.saveTasks(this.taskList.getList());
    }

    /**
     * Runs the chatbot and handles all exceptions.
     */
    public void run(String reply) {
        try {
            this.chatbot(reply);
        } catch (TodoException e) {
            ui.todoException();
        } catch (DeadlineException e) {
            ui.deadlineException();
        } catch (EventException e) {
            ui.eventException();
        } catch (WrongInputException e) {
            ui.wrongInputException();
        } catch (InvalidDateTimeException e) {
            ui.invalidDateTimeException();
        }
    }

    public static void main(String[] args) {
        Broski bot = new Broski();
        bot.start();
    }
}
