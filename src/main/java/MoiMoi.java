import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MoiMoi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MoiMoi(String path) {
        try {
            this.storage = new Storage(path);
            this.ui = new Ui();
            this.tasks = new TaskList(this.storage.load());
        } catch (MoiMoiException e) {
            this.ui.showMoiMoiException(e);
            this.tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {

        this.ui.showGreeting();

        this.ui.showUserHeader();
        String input = this.ui.getInput();
        String command = Parser.inputToCommand(input);

        while (!command.equals("bye")) {
            try {
                this.ui.showMoiMoiHeader();
                switch (command) {
                case "todo":
                    this.todo(Parser.inputToArgs(input));
                    break;
                case "deadline":
                    this.deadline(Parser.inputToArgs(input));
                    break;
                case "event":
                    this.event(Parser.inputToArgs(input));
                    break;
                case "delete":
                    this.delete(Parser.inputToArgs(input));
                    break;
                case "mark":
                    this.mark(Parser.inputToArgs(input));
                    break;
                case "unmark":
                    this.unmark(Parser.inputToArgs(input));
                    break;
                case "list":
                    this.list();
                    break;
                case "filter":
                    this.filter(Parser.inputToArgs(input));
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (MoiMoiException e) {
                this.ui.showMoiMoiException(e);
            }
            this.ui.showUserHeader();
            input = this.ui.getInput();
            command = Parser.inputToCommand(input);
        }

        try {
            storage.save(tasks);
        } catch (StorageIOException e) {
            this.ui.showMoiMoiException(e);
        }

        this.ui.showExitMessage();

    }

    public void todo(String description) {
        Todo task = new Todo(description);
        this.tasks.add(task);
        this.ui.showCompletionMessage("Aight! Todo task added: " + task.stringUI()
                + "\nWe have " + tasks.size() + " tasks in the bag~");
    }

    public void deadline(String description) throws MoiMoiException {
        try {
            String[] descBy = description.split(" /by ", 2);
            String desc = descBy[0];
            String byString = descBy[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse(byString, formatter);
            Deadline task = new Deadline(desc, by);

            this.tasks.add(task);
            this.ui.showCompletionMessage("Got it! Deadline task added: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks in the bag~");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date-time");
        }
    }

    public void event(String description) throws MoiMoiException {
        try {
            String[] descFromTo = description.split(" /from ", 2);
            String desc = descFromTo[0];
            String[] fromTo = descFromTo[1].split(" /to ", 2);
            String fromString = fromTo[0];
            String toString = fromTo[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            Event task = new Event(desc, from, to);

            this.tasks.add(task);
            this.ui.showCompletionMessage("Here you go! Event task added: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks in the bag~");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date-time");
        }
    }

    public void delete(String index) throws InvalidIndexException {
        try {
            int i = Integer.parseInt(index);
            Task task = tasks.get(i);
            tasks.delete(i);
            this.ui.showCompletionMessage("Aju nice! I've got rid of this task: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks left in the bag~");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void mark(String index) throws InvalidIndexException {
        try {
            Task task = tasks.get(Integer.parseInt(index));
            task.mark();
            this.ui.showCompletionMessage("YAY!! One down!!\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void unmark(String index) throws InvalidIndexException {
        try {
            Task task = tasks.get(Integer.parseInt(index));
            task.unmark();
            this.ui.showCompletionMessage("Oof, it's OK! Let's get it done soon ;)\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void list() {
        this.ui.showList(tasks);
    }

    public void filter(String dateString) throws InvalidDateTimeException {
        try {
            LocalDate date = LocalDate.parse(dateString);
            this.ui.showList(tasks, date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date");
        }
    }

    public static void main(String[] args) {
        new MoiMoi("data/moimoi.txt").run();
    }

}
