import java.util.Arrays;

public class Parser {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isExit;

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public Command parse(String command) throws PikappiException {
        if (command.equals("bye")) {
            isExit = true;
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            return new MarkCommand(Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("todo")) {
            try {
                return new TodoCommand(command.split("todo ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        } else if (command.startsWith("deadline")) {
            try {
                return new DeadlineCommand(command.split("deadline ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        } else if (command.startsWith("event")) {
            try {
                return new EventCommand(command.split("event ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(command.split(" ")[1]));
        } else {
            throw new PikappiException("Pi-ka..?? I don't understand..");
        }
    }
}
