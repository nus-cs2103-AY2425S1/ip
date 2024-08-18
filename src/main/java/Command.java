import java.util.ArrayList;
import java.util.Arrays;

// solution below inspired by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
public class Command {
    private enum CommandsEnum {
        LIST, BYE, TODO, MARK, UNMARK, DEADLINE, EVENT, NULL
    }
    private final CommandsEnum command;
    private String params;
    private final TodoList todoList;

    /**
     * Creates a Command object with the given command and the target storage.
     * @param command The command string which determines the type of command
     * @param todoList The TodoList object which the command will operate on.
     */
    public Command(String command, TodoList todoList) {
        String[] tokens = command.split(" ", 2);
        this.todoList = todoList;
        if (tokens.length > 1) {
            this.params = tokens[1];
        }
        switch (tokens[0]) {
            case "list":
                this.command = CommandsEnum.LIST;
                break;
            case "bye":
                this.command = CommandsEnum.BYE;
                break;
            case "mark":
                this.command = CommandsEnum.MARK;
                break;
            case "unmark":
                this.command = CommandsEnum.UNMARK;
                break;
            case "todo":
                this.command = CommandsEnum.TODO;
                break;
            case "deadline":
                this.command = CommandsEnum.DEADLINE;
                break;
            case "event":
                this.command = CommandsEnum.EVENT;
                break;
            default:
                this.command = CommandsEnum.NULL;
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
                Message.print(todoList.toString());
                break;
            case BYE:
                Message.print("Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            case TODO:
                Todo todo = new Todo(params);
                todoList.add(todo);
                Message.printAddedTask(todo, todoList);
                break;
            case DEADLINE:
                String[] deadlineParams = params.split("/by", 2);
                Deadlines deadlineTodo = new Deadlines(deadlineParams[0].trim(), deadlineParams[1].trim());
                todoList.add(deadlineTodo);
                Message.printAddedTask(deadlineTodo, todoList);
                break;
            case EVENT:
                String[] eventParamsDesc = params.split("/from", 2);
                String[] eventParamsFrom = eventParamsDesc[1].split("/to", 2);
                Event eventTodo = new Event(eventParamsDesc[0].trim(),
                        eventParamsFrom[0].trim(), eventParamsFrom[1].trim());
                todoList.add(eventTodo);
                Message.printAddedTask(eventTodo, todoList);
                break;
            case MARK:
                task = todoList.get(Integer.parseInt(params) - 1);
                task.markAsDone();
                Message.printMarked(task);
                break;
            case UNMARK:
                task = todoList.get(Integer.parseInt(params) - 1);
                task.unmarkAsDone();
                Message.printUnmarked(task);
                break;
            default:
                break;
        }
    }
}
