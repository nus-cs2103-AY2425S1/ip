import java.util.ArrayList;
import java.util.List;

public class Parser {
    /* goal of the parser class is to take in a string input and determine the
    right command. After which, invokes the respective classes*/
    private String command;
    private String remainder;
    private List<Task> allTasks;
    private String description;
    private boolean carryOn = true;
    public Parser(String input, List<Task> allTasks) throws EmptyStringException, CommandNotFoundException,  CommandFoundButInvalidException {
        this.allTasks = allTasks;
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
                // throw to the ToDos class
                ToDos todo = new ToDos(remainder);
                allTasks.add(todo);
                // should print the message here
                System.out.println(todo.message(allTasks));
                break;
            case DEADLINE:
                // throw to the Deadlines class
                Deadlines deadline = new Deadlines(remainder);
                allTasks.add(deadline);
                // should print the message here
                System.out.println(deadline.message(allTasks));
                break;
            case EVENT:
                // throw to the Events class
                Events event = new Events(remainder);
                allTasks.add(event);
                // should print the message here
                System.out.println(event.message(allTasks));
                break;
            case DELETE:
                // throw to the Delete class
                Delete delete = new Delete(remainder, allTasks);
                System.out.println(delete.message());
                break;
            case LIST:
                // throw to the ListAll class
                ListAll list = new ListAll(remainder, allTasks);
                System.out.println(list.message());
                break;
            case MARK:
                // throw to the mark class
                Mark mark = new Mark(remainder, allTasks);
                System.out.println(mark.message());
                break;
            case UNMARK:
                // throw to the unmark class
                Unmark unmark = new Unmark(remainder, allTasks);
                System.out.println(unmark.message());
                break;
            case BYE:
                // throw to the Bye class
                Bye bye = new Bye(remainder);
                System.out.println(bye.message());
                this.carryOn = false;
                break;
        }
    }

    public boolean carryOn() {
        return this.carryOn;
    }
}
