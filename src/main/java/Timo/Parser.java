import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Parser class that deadls with making sense of the commands and sending commands to UI
 */
public class Parser {
    private final UI ui;
    private final Storage storage;
    private final TaskList taskList;

    private final Stack<Tuple<Task>> commandList;

    public Parser(UI ui, Storage storage, TaskList taskList, Stack<Tuple<Task>> commandList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
        this.commandList = commandList;
    }

    /**
     * Parses and executes a given command string.
     * <p>
     * The method interprets the command and performs the corresponding action, such as marking tasks,
     * adding tasks (Todo, Deadline, Event), deleting tasks, printing the task list, or exiting the application.
     * If the command is not recognized or lacks necessary arguments, a `TimoException` is thrown.
     * </p>
     *
     * @param command The command string input by the user.
     *
     * @throws TimoException If the command is invalid or does not include the necessary arguments.
     *
     * @see TaskList
     * @see Task
     * @see Todo
     * @see Deadline
     * @see Event
     * @see TimoException
     */
    public String parse(String command) throws TimoException {
        String cmd = command.split(" ", 2)[0];
        switch (cmd) {
        case "todo":
            String[] todoCommands = command.split(" ", 2);

            //checks if todo command is correct
            if (todoCommands.length != 2) {
                throw new TimoException("Usage todo: todo <task> (need argument)");
            }

            Todo todo = new Todo(false, todoCommands[1]);
            this.taskList.add(todo);
            this.commandList.add(new Tuple<Task>(command, todo));
            return this.ui.printTodo(todo, this.taskList.showList());

        case "deadline":
            String[] deadlineCommands = command.split("deadline |/by ");
            String description = deadlineCommands[1];
            String datetime = deadlineCommands[2].trim();
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            try {
                LocalDateTime date = LocalDateTime.parse(datetime, inputFormat);
                Deadline deadline = new Deadline(false, description, date);
                this.taskList.add(deadline);
                this.commandList.add(new Tuple<Task>(command, deadline));
                return this.ui.printDeadline(deadline, this.taskList.showList());
            } catch (DateTimeException e) {
                return this.ui.printDeadlineError();
            }

        case "event":
            String[] eventCommands = command.split("event |/from |/to ");
            Event event = new Event(false, eventCommands[1], eventCommands[2], eventCommands[3]);
            this.taskList.add(event);
            this.commandList.add(new Tuple<Task>(command, event));
            return this.ui.printEvent(event, this.taskList.showList());

        case "mark":
            String taskNumberToMark = String.valueOf(command.charAt(command.length() - 1));

            //get the Task number to mark
            int markTarget = Integer.parseInt(taskNumberToMark);

            //find the task to mark
            Task markedTask = this.taskList.mark(markTarget);

            this.commandList.add(new Tuple<Task>(command, markedTask));
            return this.ui.printMark(markedTask);

        case "unmark":

            String taskNumberToUnmark = String.valueOf(command.charAt(command.length() - 1));

            //get the Task number to unmark
            int unmarkTarget = Integer.parseInt(taskNumberToUnmark);

            //find the task to unmark
            Task unmarkedTask = this.taskList.unmark(unmarkTarget);

            this.commandList.add(new Tuple<Task>(command, unmarkedTask));
            return this.ui.printUnmark(unmarkedTask);

        case "list":
            this.commandList.add(new Tuple<Task>(command, null));
            return this.ui.printList(this.taskList);


        case "delete":
            //get the Task number to delete
            int deleteTarget = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));

            // Task that is deleted
            Task deleteTask = this.taskList.delete(deleteTarget - 1);

            //delete target and deleteTask in String format and concatenated
            this.commandList.add(new Tuple<Task>(command, deleteTask));

            return this.ui.printDelete(deleteTask, this.taskList.showList());

        case "find":
            String phrase = command.split(" ", 2)[1];

            // TaskList to print out
            TaskList temporaryList = new TaskList();

            for (Task currentTask: this.taskList.showList()) {
                if (currentTask.toString().contains(phrase)) {
                    temporaryList.add(currentTask);
                }
            }
            this.commandList.add(new Tuple<Task>(command, null));
            return this.ui.printList(temporaryList);

        case "bye":
            this.storage.store(this.taskList.showList());
            return this.ui.bye();

        case "undo":
            try {
                Tuple<Task> undo = this.commandList.pop();
                String commandToUndo = undo.getFirst();
                String action = undo.getFirst().split(" ", 2)[0];

                //do the opposite of the one in commandList, then thats it
                //undo the command: show command, then show what we did like we add it, then we have
                //how many tasks left
                switch (action) {
                case "todo":
                    Task undoTodo = this.taskList.delete(this.taskList.showList().size() - 1);
                    String undoTodoCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    return undoTodoCommand + this.ui.printDelete(undoTodo, this.taskList.showList());
                case "deadline":
                    Task undoDeadline = this.taskList.delete(this.taskList.showList().size() - 1);
                    String undoDeadlineCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    return undoDeadlineCommand + this.ui.printDelete(undoDeadline, this.taskList.showList());
                case "event":
                    Task undoEvent = this.taskList.delete(this.taskList.showList().size() - 1);
                    String undoEventCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    return undoEventCommand + this.ui.printDelete(undoEvent, this.taskList.showList());
                case "mark":
                    String undoMarkCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    int numberToUnmark = Integer.valueOf(undo.getFirst().split(" ", 2)[1]);
                    Task markedTaskToUndo = this.taskList.unmark(numberToUnmark);
                    return undoMarkCommand + this.ui.printUnmark(markedTaskToUndo);
                case "unmark":
                    String undoUnmarkCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    int numberToMark = Integer.valueOf(undo.getFirst().split(" ", 2)[1]);
                    Task unmarkedTaskToUndo = this.taskList.unmark(numberToMark);
                    return undoUnmarkCommand + this.ui.printUnmark(unmarkedTaskToUndo);
                case "list":
                    String undoListCommand = "----------------------------\n"
                            + "Seriously? U want to undo list? If you insist...\n"
                            + this.ui.listSize(this.taskList);
                    return undoListCommand;
                case "delete":
                    int positionToAddDeletedTask = Integer.valueOf(undo.getFirst().split(" ", 2)[1]);
                    Task taskToAddBack = undo.getSecond();
                    this.taskList.add(taskToAddBack, positionToAddDeletedTask);
                    String undoDeleteCommand = "----------------------------\n"
                            + "undo command: " + undo.getFirst() + "\n"
                            + "----------------------------\n"
                            + this.ui.listSize(this.taskList);
                    return undoDeleteCommand;
                default:
                    return "hi";
                }
            } catch (EmptyStackException e) {
                return this.ui.undoError();
            }
        default:
            return this.ui.printUnknownCommandError(new TimoException("I'm sorry, I do not know what that means"));
        }
    }
}
