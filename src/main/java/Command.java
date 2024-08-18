import java.util.ArrayList;

// solution below inspired by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
public class Command {
    private enum CommandsEnum {
        LIST, BYE, TODO
    }
    private CommandsEnum command;
    private String params;
    private static final Message message = new Message("");

    private TodoList todoList;

    /**
     * Creates a Command object with the given command and the target storage.
     * @param command The command string which determines the type of command
     * @param todoList The TodoList object which the command will operate on.
     */
    public Command(String command, TodoList todoList) {
        this.todoList = todoList;
        switch (command) {
            case "list":
                this.command = CommandsEnum.LIST;
                break;
            case "bye":
                this.command = CommandsEnum.BYE;
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
            default:
                break;
        }
    }
}
