import java.util.ArrayList;
import java.util.List;

public class Parser {
    /* goal of the parser class is to take in a string input and determine the
    right command. After which, invokes the respective classes*/
    private String command;
    private String remainder;
    private TaskList allTasks;
    private String description;
    private Storage storage;
    private boolean carryOn = true;
    private Ui ui;
    public Parser(String input, TaskList allTasks, Storage s, Ui ui) throws EmptyStringException, CommandNotFoundException,  CommandFoundButInvalidException {
        this.allTasks = allTasks;
        this.ui = ui;
        if (input.isEmpty()) {
            throw new EmptyStringException();
        }
        if (input.split(" ", 2).length == 1) {
            this.command = input.split(" ", 2)[0];
            this.remainder = "";
        }
        if (input.split(" ", 2).length == 2) {
            this.command = input.split(" ", 2)[0];
            this.remainder = input.split(" ", 2)[1];
        }
        Commands cmd = Commands.fromString(command);
        switch(cmd) {
            case TODO:
                allTasks.addTodo(remainder);
                s.put(allTasks.getAllTasks());
                System.out.println(ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize()));
                break;
            case DEADLINE:
                allTasks.addDeadline(remainder);
                s.put(allTasks.getAllTasks());
                System.out.println(ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize()));
                break;
            case EVENT:
                allTasks.addEvent(remainder);
                s.put(allTasks.getAllTasks());
                System.out.println(ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize()));
                break;
            case DELETE:
                allTasks.delete(remainder);
                s.put(this.allTasks.getAllTasks());
                System.out.println(ui.deleteMessage(this.allTasks.getLastDeleted(), this.allTasks.getSize()));
                break;
            case LIST:
                // throw to the ListAll class
                ListAll list = new ListAll(remainder, allTasks.getAllTasks());
                System.out.println(list.message());
                break;
            case MARK:
                allTasks.mark(remainder, allTasks.getAllTasks());
                s.put(allTasks.getAllTasks());
                System.out.println(ui.markedMessage(allTasks.getLastMarked()));
                break;
            case UNMARK:
                allTasks.unmark(remainder, allTasks.getAllTasks());
                s.put(allTasks.getAllTasks());
                System.out.println(ui.unmarkedMessage(allTasks.getLastUnmarked()));
                break;
            case BYE:
                System.out.println(ui.bye());
                this.carryOn = false;
                break;
        }
    }

    public boolean carryOn() {
        return this.carryOn;
    }
}
