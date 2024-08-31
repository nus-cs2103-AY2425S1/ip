package talkabot;

import talkabot.exceptions.InvalidEditException;
import talkabot.exceptions.InvalidScheduleException;
import talkabot.exceptions.NoInputException;
import talkabot.exceptions.UnknownInputException;
import talkabot.exceptions.TalkaBotException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * TalkaBot Class handles the running of Talk-a-Bot
 * and responds according to user input.
 */
public class TalkaBot {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for TalkaBot class.
     *
     * @param filePath path of file to be saved in hard drive.
     */
    public TalkaBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            this.ui.error(e.getMessage());
        }
    }

    /**
     * Runs the Talk-a-Bot, responding based on user input.
     */
    private void run() {
        boolean end = false;
        this.ui.hello();
        ui.dashedLine();
        while (!end) {
            try {
                String input = this.ui.getLine();
                ui.dashedLine();
                if (input == "") {
                    throw new NoInputException();
                } else if (input.equalsIgnoreCase("bye")) {
                    end = true;
                } else if (input.equalsIgnoreCase("list")) {
                    this.ui.displayList(this.taskList);
                } else if (input.toLowerCase().startsWith("mark")) {
                    this.mark(input);
                } else if (input.toLowerCase().startsWith("unmark")) {
                    this.unmark(input);
                } else if (input.toLowerCase().startsWith("delete")) {
                    this.delete(input);
                } else if (input.toLowerCase().startsWith("get day")) {
                    this.getDay(input);
                } else if (input.toLowerCase().startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new ToDo(input.substring(5));
                    this.taskList.add(curr);
                    this.ui.echo(curr, this.taskList.size());
                    storage.save(this.taskList);
                } else if (input.toLowerCase().startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new Deadline(Parser.getDeadline(input));
                    this.taskList.add(curr);
                    this.ui.echo(curr, this.taskList.size());
                    storage.save(this.taskList);
                } else if (input.toLowerCase().startsWith("event")) {
                    if (input.length() < 7) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new Event(Parser.getEvent(input));
                    this.taskList.add(curr);
                    this.ui.echo(curr, this.taskList.size());
                    storage.save(this.taskList);
                } else {
                    if (input == "") {
                    } else {
                        throw new UnknownInputException(input);
                    }
                }
            } catch (TalkaBotException e) {
                this.ui.error(e.getMessage());
            } catch (DateTimeException e) {
                this.ui.error("Sorry, I need a valid date format! For example: yyyy-mm-dd");
            } catch (IOException e) {
                this.ui.error(e.getMessage());
            }
            if (!end) {
                ui.dashedLine();
            }
        }
        this.ui.goodbye();
    }

    private boolean isValidNumber(String str, int len) {
        try {
            return str.length() > len
                    && Integer.parseInt(str.substring(len)) <= this.taskList.size()
                    && Integer.parseInt(str.substring(len)) >= 1;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void mark(String input) throws InvalidEditException, IOException {
        if (!isValidNumber(input, 5)) {
            throw new InvalidEditException("mark");
        }
        Task task = this.taskList.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        this.ui.mark(task);
        storage.save(this.taskList);
    }

    private void unmark(String input) throws InvalidEditException, IOException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("unmark");
        }
        Task task = this.taskList.get(Integer.parseInt(input.substring(7)) - 1);
        task.markAsUndone();
        this.ui.unmark(task);
        storage.save(this.taskList);
    }

    private void delete(String input) throws InvalidEditException, IOException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("delete");
        }
        Task task = this.taskList.delete(Integer.parseInt(input.substring(7)) - 1);
        this.ui.delete(task, this.taskList.size());
        storage.save(this.taskList);
    }

    private void getDay(String input) throws InvalidEditException {
        if (!isValidNumber(input, 8)) {
            throw new InvalidEditException("get the day of");
        }
        Task task = this.taskList.get(Integer.parseInt(input.substring(8)) - 1);
        this.ui.getDay(task);
    }

    public static void main(String[] args) {
        new TalkaBot("data/tasks.txt").run();
    }
}
