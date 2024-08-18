import java.util.ArrayList;
import java.util.Arrays;

// solution below inspired by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
public class Command {
    private enum CommandsEnum {
        LIST, BYE, TODO, MARK, UNMARK
    }
    private final CommandsEnum command;
    private String params;
    private static final Message message = new Message("");

    private final TodoList todoList;

    /**
     * Creates a Command object with the given command and the target storage.
     * @param command The command string which determines the type of command
     * @param todoList The TodoList object which the command will operate on.
     */
    public Command(String command, TodoList todoList) {
        String[] tokens = command.split(" ", 2);
        this.todoList = todoList;
        switch (tokens[0]) {
            case "list":
                this.command = CommandsEnum.LIST;
                break;
            case "bye":
                this.command = CommandsEnum.BYE;
                break;
            case "mark":
                this.command = CommandsEnum.MARK;
                this.params = tokens[1];
                break;
            case "unmark":
                this.command = CommandsEnum.UNMARK;
                this.params = tokens[1];
                break;
            default:
                this.command = CommandsEnum.TODO;
                this.params = command;
                break;
        }
    }

    /**
     * Executes the action based on the command and target TodoList
     */
    public void action() {
        Task task;
        switch (command) {
            case LIST:
                Command.message.text(todoList.toString()).print();
                break;
            case BYE:
                Command.message.text("Bye. Hope to see you again soon!").print();
                System.exit(0);
                break;
            case TODO:
                todoList.add(params);
                Command.message.text("added: " + params).print();
                break;
            case MARK:
                task = todoList.get(Integer.parseInt(params) - 1);
                task.markAsDone();
                Command.message.text("Nice! I've marked this task as done:\n  " + task).print();
                break;
            case UNMARK:
                task = todoList.get(Integer.parseInt(params) - 1);
                task.unmarkAsDone();
                Command.message.text("OK, I've marked this task as not done yet:\n  "  + task).print();
                break;
            default:
                break;
        }
    }
}
