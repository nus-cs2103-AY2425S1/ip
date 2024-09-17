package talkabot;

import java.io.IOException;
import java.time.DateTimeException;

import talkabot.exceptions.InvalidEditException;
import talkabot.exceptions.InvalidScheduleException;
import talkabot.exceptions.NoInputException;
import talkabot.exceptions.TalkaBotException;
import talkabot.exceptions.UnknownInputException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

/**
 * TalkaBot Class handles the running of Talk-a-Bot
 * and responds according to user input.
 */
public class TalkaBot {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    private String commandType;

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
     * Constructor for TalkaBot class.
     *
     */
    public TalkaBot() {
        this("data/tasks.txt");
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
                    System.out.println(this.ui.displayList(this.taskList));
                } else if (input.toLowerCase().startsWith("mark")) {
                    System.out.println(this.mark(input));
                } else if (input.toLowerCase().startsWith("unmark")) {
                    System.out.println(this.unmark(input));
                } else if (input.toLowerCase().startsWith("delete")) {
                    System.out.println(this.delete(input));
                } else if (input.toLowerCase().startsWith("get day")) {
                    System.out.println(this.getDay(input));
                } else if (input.toLowerCase().startsWith("find")) {
                    if (input.length() < 6) {
                        throw new InvalidEditException("find");
                    }
                    System.out.println(this.ui.returnMatches(this.taskList
                            .find(input.substring(5))));
                } else if (input.toLowerCase().startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new ToDo(input.substring(5));
                    this.taskList.add(curr);
                    System.out.println(this.ui.echo(curr, this.taskList.size()));
                    storage.save(this.taskList);
                } else if (input.toLowerCase().startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new Deadline(Parser.getDeadline(input));
                    this.taskList.add(curr);
                    System.out.println(this.ui.echo(curr, this.taskList.size()));
                    storage.save(this.taskList);
                } else if (input.toLowerCase().startsWith("event")) {
                    if (input.length() < 7) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new Event(Parser.getEvent(input));
                    this.taskList.add(curr);
                    System.out.println(this.ui.echo(curr, this.taskList.size()));
                    storage.save(this.taskList);
                } else {
                    throw new UnknownInputException(input);
                }
            } catch (TalkaBotException e) {
                System.out.println(this.ui.error(e.getMessage()));
            } catch (DateTimeException e) {
                System.out.println(this.ui
                        .error("Sorry, I need a valid date format! For example: yyyy-mm-dd"));
            } catch (IOException e) {
                System.out.println(this.ui.error(e.getMessage()));
            }
            if (!end) {
                ui.dashedLine();
            }
        }
        System.out.println(this.ui.goodbye());
    }

    public String getResponse(String input) {
        try {
            if (input == "") {
                throw new NoInputException();
            } else if (input.equalsIgnoreCase("bye")) {
                this.commandType = "GreetingCommand";
                return this.ui.goodbye();
            } else if (input.equalsIgnoreCase("list")) {
                this.commandType = "RetrieveCommand";
                return this.ui.displayList(this.taskList);
            } else if (input.toLowerCase().startsWith("mark")) {
                this.commandType = "ChangeMarkCommand";
                return this.mark(input);
            } else if (input.toLowerCase().startsWith("unmark")) {
                this.commandType = "ChangeMarkCommand";
                return this.unmark(input);
            } else if (input.toLowerCase().startsWith("delete")) {
                this.commandType = "DeleteCommand";
                return this.delete(input);
            } else if (input.toLowerCase().startsWith("get day")) {
                this.commandType = "RetrieveCommand";
                return this.getDay(input);
            } else if (input.toLowerCase().startsWith("find")) {
                this.commandType = "RetrieveCommand";
                if (input.length() < 6) {
                    throw new InvalidEditException("find");
                }
                return this.ui.returnMatches(this.taskList
                        .find(input.substring(5)));
            } else if (input.toLowerCase().startsWith("todo")) {
                this.commandType = "AddCommand";
                if (input.length() < 6) {
                    throw new InvalidScheduleException();
                }
                Task curr = new ToDo(input.substring(5));
                this.taskList.add(curr);
                storage.save(this.taskList);
                return this.ui.echo(curr, this.taskList.size());
            } else if (input.toLowerCase().startsWith("deadline")) {
                this.commandType = "AddCommand";
                if (input.length() < 10) {
                    throw new InvalidScheduleException();
                }
                Task curr = new Deadline(Parser.getDeadline(input));
                this.taskList.add(curr);
                storage.save(this.taskList);
                return this.ui.echo(curr, this.taskList.size());
            } else if (input.toLowerCase().startsWith("event")) {
                this.commandType = "AddCommand";
                if (input.length() < 7) {
                    throw new InvalidScheduleException();
                }
                Task curr = new Event(Parser.getEvent(input));
                this.taskList.add(curr);
                storage.save(this.taskList);
                return this.ui.echo(curr, this.taskList.size());
            } else {
                throw new UnknownInputException(input);
            }
        } catch (TalkaBotException e) {
            this.commandType = "Error";
            return this.ui.error(e.getMessage());
        } catch (DateTimeException e) {
            this.commandType = "Error";
            return this.ui
                    .error("Sorry, I need a valid date format! For example: yyyy-mm-dd");
        } catch (IOException e) {
            this.commandType = "Error";
            return this.ui.error(e.getMessage());
        }
    }

    private boolean isValidNumber(String str, int len) {
        try {
            return str.length() > len
                    && Integer.parseInt(str.substring(len)) <= this.taskList.size()
                    && Integer.parseInt(str.substring(len)) >= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String mark(String input) throws InvalidEditException, IOException {
        if (!isValidNumber(input, 5)) {
            throw new InvalidEditException("mark");
        }
        Task task = this.taskList.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        storage.save(this.taskList);
        return this.ui.mark(task);
    }

    private String unmark(String input) throws InvalidEditException, IOException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("unmark");
        }
        Task task = this.taskList.get(Integer.parseInt(input.substring(7)) - 1);
        task.markAsUndone();
        storage.save(this.taskList);
        return this.ui.unmark(task);
    }

    private String delete(String input) throws InvalidEditException, IOException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("delete");
        }
        Task task = this.taskList.delete(Integer.parseInt(input.substring(7)) - 1);
        storage.save(this.taskList);
        return this.ui.delete(task, this.taskList.size());
    }

    private String getDay(String input) throws InvalidEditException {
        if (!isValidNumber(input, 8)) {
            throw new InvalidEditException("get the day of");
        }
        Task task = this.taskList.get(Integer.parseInt(input.substring(8)) - 1);
        return this.ui.getDay(task);
    }

    public String getCommandType() {
        return this.commandType;
    }

    public static void main(String[] args) {
        /*new TalkaBot("data/tasks.txt").run();*/
    }
}
