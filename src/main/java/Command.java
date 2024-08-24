import java.io.IOException;

import FileSaver.FileSaver;
import message.Message;
import tasks.Deadlines;
import tasks.Event;
import tasks.Task;
import TodoList.TodoList;
import exceptions.*;
import tasks.Todo;

// solution below inspired by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
public class Command {
    private enum CommandsEnum {
        LIST, BYE, TODO, MARK, UNMARK, DEADLINE, EVENT, DELETE, NULL, DUE
    }
    private final CommandsEnum command;
    private String params;
    private final TodoList todoList;
    private final FileSaver fs;

    /**
     * Creates a Command object with the given command and the target storage.
     * @param command The command string which determines the type of command
     * @param todoList The TodoList.TodoList object which the command will operate on.
     */
    public Command(String command, TodoList todoList, FileSaver fs) throws IOException {
        String[] tokens = command.split(" ", 2);
        this.todoList = todoList;
        this.fs = fs;
        if (tokens.length > 1) {
            this.params = tokens[1].trim();
        }
        switch (tokens[0].toLowerCase()) {
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
            case "delete":
                this.command = CommandsEnum.DELETE;
                break;
            case "due":
                this.command = CommandsEnum.DUE;
                break;
            default:
                this.command = CommandsEnum.NULL;
                break;
        }
    }

    /**
     * Executes the action based on the command and target TodoList.TodoList
     */
//    public void action() throws UnknownCommandException, MissingParametersException, InvalidTaskException, IOException {
//        Task task;
//        switch (command) {
//            case DUE:
//                String command = "due";
//                String error = "due 2/12/2019";
//                if (params == null || params.isEmpty()) {
//                    throw new MissingParametersException(command, error);
//                }
//                Message.printDue(params, todoList);
//                break;
//            case LIST:
//                Message.print(todoList.toString());
//                break;
//            case BYE:
//                Message.print("Bye. Hope to see you again soon!");
//                System.exit(0);
//                break;
//            case TODO:
//                Todo todo = getTodo();
//                todoList.add(todo);
//                Message.printAddedTask(todo, todoList);
//                break;
//            case DEADLINE:
//                Deadlines deadlineTodo = getDeadlines();
//                todoList.add(deadlineTodo);
//                Message.printAddedTask(deadlineTodo, todoList);
//                break;
//            case EVENT:
//                Event eventTodo = getEvent();
//                todoList.add(eventTodo);
//                Message.printAddedTask(eventTodo, todoList);
//                break;
//            case MARK:
//                try {
//                    task = todoList.get(Integer.parseInt(params.trim()) - 1);
//                } catch (IndexOutOfBoundsException | NumberFormatException e) {
//                    throw new InvalidTaskException();
//                }
//                task.markAsDone();
//                Message.printMarked(task);
//                break;
//            case UNMARK:
//                try {
//                    task = todoList.get(Integer.parseInt(params.trim()) - 1);
//                } catch (IndexOutOfBoundsException | NumberFormatException e) {
//                    throw new InvalidTaskException();
//                }
//                task.unmarkAsDone();
//                Message.printUnmarked(task);
//                break;
//            case DELETE:
//                try {
//                    task = todoList.delete(Integer.parseInt(params) - 1);
//                } catch (IndexOutOfBoundsException | NumberFormatException e) {
//                    throw new InvalidTaskException();
//                }
//                Message.printDeletedTask(task, todoList);
//                break;
//            default:
//                throw new UnknownCommandException();
//        }
//        this.fs.saveData(todoList);
//    }

    /**
     * Creates a deadline task from the command.
     * @return A deadline object.
     * @throws MissingParametersException Command is malformed
     */
//    private Deadlines getDeadlines() throws MissingParametersException {
//        String command = "deadline";
//        String error = "deadline return book /by 2/12/2019 1800";
//        if (params == null || params.isEmpty()) {
//            throw new MissingParametersException(command, error);
//        }
//        String[] deadlineParams = params.split("/by", 2);
//        if (deadlineParams.length < 2 || deadlineParams[0].trim().isEmpty() || deadlineParams[1].trim().isEmpty()) {
//            throw new MissingParametersException(command, error);
//        }
//        return new Deadlines(deadlineParams[0].trim(), deadlineParams[1].trim());
//    }

    /**
     * Creates a todo task from the command.
     * @return A todo object.
     * @throws MissingParametersException Command is malformed
     */
    private Todo getTodo() throws MissingParametersException {
        if (params == null || params.isEmpty()) {
            throw new MissingParametersException("todo", "todo read book");
        }
        return new Todo(params);
    }

    /**
     * Creates an event task from the command.
     * @return event object.
     * @throws MissingParametersException Command is malformed
     */
    private Event getEvent() throws MissingParametersException {
        String command = "event";
        String error = "event project meeting /from Mon 2pm /to 4pm";
        if (params == null || params.isEmpty()) {
            throw new MissingParametersException(command, error);
        }
        String[] eventParams = params.split("/from|/to", 3);
        if (eventParams.length < 3 || eventParams[0].trim().isEmpty() ||
                eventParams[1].trim().isEmpty() || eventParams[2].trim().isEmpty()) {
            throw new MissingParametersException(command, error);
        }
        return new Event(eventParams[0].trim(),
                eventParams[1].trim(), eventParams[2].trim());
    }
}
