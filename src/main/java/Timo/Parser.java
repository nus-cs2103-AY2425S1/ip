package Timo;

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

    /**
     * Parser to process the commands and give the output
     * @param ui
     * @param storage
     * @param taskList
     * @param commandList
     */
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
                return this.ui.printCommandError(new TimoException("Error! Usage todo: todo <task> (need argument)"));
            }

            if (todoCommands[1].trim().isEmpty()) {
                return this.ui.printCommandError(new TimoException("Error! Need to include task for todo!"));
            }

            Todo todo = new Todo(false, todoCommands[1]);
            this.taskList.add(todo);
            this.commandList.add(new Tuple<Task>(command, todo));
            return this.ui.printTodo(todo, this.taskList);

        case "deadline":
            String[] deadlineCommands = command.split("deadline |/by ");
            if (deadlineCommands.length != 3) {
                return this.ui.printCommandError(new TimoException("Error!"
                        + " Usage deadline: deadline <task> /by yyyy-MM-dd"
                        + " HHmm"));
            }

            if (deadlineCommands[1].trim().isEmpty()) {
                return this.ui.printCommandError(new TimoException("Error! Need to include task for deadline!"));
            }
            String deadlineDescription = deadlineCommands[1];
            String deadlineDatetime = deadlineCommands[2].trim();
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            try {
                LocalDateTime date = LocalDateTime.parse(deadlineDatetime, inputFormat);
                Deadline deadline = new Deadline(false, deadlineDescription, date);
                this.taskList.add(deadline);
                this.commandList.add(new Tuple<Task>(command, deadline));
                return this.ui.printDeadline(deadline, this.taskList);
            } catch (DateTimeException e) {
                return this.ui.printDeadlineError();
            }

        case "event":
            String[] eventCommands = command.split("event |/from |/to ");
            if (eventCommands.length != 4) {
                return this.ui.printCommandError(new TimoException("Error! "
                        + "Usage event: event <task> /from yyyy-MM-dd HHmm /to "
                        + "yyyy-MM-dd HHmm"));
            }

            if (eventCommands[1].trim().isEmpty()) {
                return this.ui.printCommandError(new TimoException("Error! Need to include task for event!"));
            }
            String eventDescription = eventCommands[1];
            String eventFromInDatetimeFormat = eventCommands[2].trim();
            String eventToInDatetimeFormat = eventCommands[3].trim();
            try {
                LocalDateTime fromDate = LocalDateTime.parse(eventFromInDatetimeFormat,
                                         DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime toDate = LocalDateTime.parse(eventToInDatetimeFormat,
                                       DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Event event = new Event(false, eventDescription, fromDate, toDate);
                this.taskList.add(event);
                this.commandList.add(new Tuple<Task>(command, event));
                return this.ui.printEvent(event, this.taskList);
            } catch (DateTimeException e) {
                return this.ui.printEventError();
            }

        case "mark":
            String[] markCommands = command.split(" ");
            if (markCommands.length != 2) {
                return this.ui.printCommandError(new TimoException("mark usage: mark <number>"));
            }
            String taskNumberToMark = markCommands[1];
            int markTaskNumber;

            try {
                markTaskNumber = Integer.valueOf(taskNumberToMark);
            } catch (NumberFormatException e) {
                return this.ui.printCommandError(new TimoException("Has to be a number!"));
            }

            if (markTaskNumber <= 0 || markTaskNumber > this.taskList.showList().size()) {
                return this.ui.printCommandError(new TimoException("Invalid number!"));
            }

            //find the task to mark
            Task markedTask = this.taskList.mark(markTaskNumber);

            this.commandList.add(new Tuple<Task>(command, markedTask));
            return this.ui.printMark(markedTask);

        case "unmark":
            String[] unmarkCommands = command.split(" ");
            if (unmarkCommands.length != 2) {
                return this.ui.printCommandError(new TimoException("unmark usage: unmark <number>"));
            }
            String taskNumberToUnmark = unmarkCommands[1];

            //get the Task number to unmark
            int unmarkTarget = Integer.parseInt(taskNumberToUnmark);

            if (unmarkTarget <= 0 || unmarkTarget > this.taskList.showList().size()) {
                return this.ui.printCommandError(new TimoException("Invalid number"));
            }
            //find the task to unmark
            Task unmarkedTask = this.taskList.unmark(unmarkTarget);

            this.commandList.add(new Tuple<Task>(command, unmarkedTask));
            return this.ui.printUnmark(unmarkedTask);

        case "list":
            String[] listCommands = command.split(" ");
            if (listCommands.length != 1) {
                return this.ui.printCommandError(new TimoException("list usage: list"));
            }
            return this.ui.printList(this.taskList);


        case "delete":
            String[] deleteCommands = command.split(" ");
            if (deleteCommands.length != 2) {
                return this.ui.printCommandError(new TimoException("delete usage: delete <number>"));
            }
            //get the Task number to delete
            int deleteTarget = Integer.valueOf(deleteCommands[1]);

            if (deleteTarget <= 0 || deleteTarget > this.taskList.showList().size()) {
                return this.ui.printCommandError(new TimoException("Invalid number"));
            }

            // Task that is deleted
            Task deleteTask = this.taskList.delete(deleteTarget - 1);

            //delete target and deleteTask in String format and concatenated
            this.commandList.add(new Tuple<Task>(command, deleteTask));

            return this.ui.printDelete(deleteTask, this.taskList);

        case "find":
            String[] findCommands = command.split(" ");
            if (findCommands.length != 2) {
                return this.ui.printCommandError(new TimoException("find usage: find <phrase>"));
            }
            String phrase = findCommands[1];

            // TaskList to print out
            TaskList temporaryList = new TaskList();

            for (Task currentTask: this.taskList.showList()) {
                if (currentTask.toString().contains(phrase)) {
                    temporaryList.add(currentTask);
                }
            }
            return this.ui.printList(temporaryList);

        case "bye":
            String[] byeCommands = command.split(" ");
            if (byeCommands.length != 1) {
                return this.ui.printCommandError(new TimoException("bye usage: bye"));
            }
            this.storage.store(this.taskList.showList());
            return this.ui.bye();

        case "undo":
            String[] undoCommands = command.split(" ");
            if (undoCommands.length != 1) {
                return this.ui.printCommandError(new TimoException("undo usage: undo"));
            }
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
                    return undoTodoCommand + this.ui.printDelete(undoTodo, this.taskList);
                case "deadline":
                    Task undoDeadline = this.taskList.delete(this.taskList.showList().size() - 1);
                    String undoDeadlineCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    return undoDeadlineCommand + this.ui.printDelete(undoDeadline, this.taskList);
                case "event":
                    Task undoEvent = this.taskList.delete(this.taskList.showList().size() - 1);
                    String undoEventCommand = "----------------------------\n" + "undo command: " + commandToUndo
                            + "\n";
                    return undoEventCommand + this.ui.printDelete(undoEvent, this.taskList);
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
                case "delete":
                    int positionToAddDeletedTask = Integer.valueOf(undo.getFirst().split(" ", 2)[1]);
                    Task taskToAddBack = undo.getSecond();
                    this.taskList.add(taskToAddBack, positionToAddDeletedTask);
                    String undoDeleteCommand = "----------------------------\n"
                            + "undo command: " + undo.getFirst() + "\n"
                            + "----------------------------\n"
                            + "added " + undo.getSecond() + "\n"
                            + this.ui.listSize(this.taskList);
                    return undoDeleteCommand;
                default:
                    assert false : "Problem with command list";
                }
            } catch (EmptyStackException e) {
                return this.ui.undoError();
            }
        default:
            return this.ui.printCommandError(new TimoException("I'm sorry, I do not know what that means"));
        }
    }
}
