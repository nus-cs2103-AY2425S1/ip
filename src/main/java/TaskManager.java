public class TaskManager {
    enum Command {
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    public void processInput(TaskList taskList, TaskIO taskIO, String input) {
        String[] inputComponents = input.split(" ", 2);
        String command = inputComponents[0];
        String argument = inputComponents.length > 1 ? inputComponents[1] : "";
        Task task;
        int index;

        try {
            TaskManager.Command cmd = parseCommand(command);
            switch (cmd) {
            case LIST:
                Denim.displayListMessage(taskList);
                break;
            case MARK:
                index = handleMark(taskList, argument);
                taskIO.markTask(taskList);
                Denim.displayMarkMessage(taskList, index);
                break;
            case UNMARK:
                index = handleUnmark(taskList, argument);
                handleUnmark(taskList, argument);
                taskIO.unmarkTask(taskList);
                Denim.displayUnmarkMessage(taskList,index);
                break;
            case DELETE:
                task = handleDelete(taskList, argument);
                taskIO.deleteTask(taskList);
                Denim.displayDeleteMessage(task, taskList);
                break;
            case TODO:
                task = handleTodo(taskList, argument);
                taskIO.writeTaskData(task);
                Denim.displayTaskAdditionMessage(taskList, task);
                break;
            case DEADLINE:
                task = handleDeadline(taskList, argument);
                taskIO.writeTaskData(task);
                Denim.displayTaskAdditionMessage(taskList, task);
                break;
            case EVENT:
                task = handleEvent(taskList, argument);
                taskIO.writeTaskData(task);
                Denim.displayTaskAdditionMessage(taskList, task);
                break;
            default:
                handleDefault();
                break;
            }
        } catch (DenimException e) {
            Denim.displayErrorMessage(e);
        }
    }

    private TaskManager.Command parseCommand(String input) throws DenimException {
        try {
            return TaskManager.Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DenimException("Unknown Command!");
        }
    }

    private int handleMark(TaskList taskList, String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("I do not know what you are trying to mark! ಠ▃ಠ");
        }

        try {
            int index = Integer.parseInt(argument) - 1;

            if (index < 0 || index >= taskList.getTaskListSize()) {
                throw new DenimException("OOPS!!! The task number is out of range. >=C");
            }

            taskList.getTask(index).setIsDone(true);
            return index;

        } catch (NumberFormatException e) {
            throw new DenimException("y u do dis! Argument must be a valid numb3r! ( =ω=)..nyaa");
        }
    }

    private int handleUnmark(TaskList taskList, String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("I do not know what you are trying to unmark! (◡︿◡✿)");
        }

        try {
            int index = Integer.parseInt(argument) - 1;

            if (index < 0 || index >= taskList.getTaskListSize()) {
                throw new DenimException("OOPS!!! The task number is out of range. >=C");
            }

            taskList.getTask(index).setIsDone(false);
            return index;

        } catch (NumberFormatException e) {
            throw new DenimException("y u do dis! Argument must be a valid numb3r! ( =ω=)..nyaa");
        }
    }

    private Task handleDelete(TaskList taskList, String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("I do not know what you are trying to delete! >.<");
        }

        try {
            int index = Integer.parseInt(argument) - 1;

            if (index < 0 || index >= taskList.getTaskListSize()) {
                throw new DenimException("OOPS!!! The task number is out of range. >=C");
            }

            Task deletedTask = taskList.getTask(index);
            taskList.deleteTask(index);
            return deletedTask;

        } catch (NumberFormatException e) {
            throw new DenimException("y u do dis! Argument must be a valid numb3r! ( =ω=)..nyaa");
        }
    }

    private Task handleTaskAddition(TaskList taskList, Task task) {
        taskList.addTask(task);
        return task;
    }

    private Task handleTodo(TaskList taskList, String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("The description of a todo cannot be empty! >.<");
        }
        Task toDoTask = new Todo(argument);
        return handleTaskAddition(taskList, toDoTask);
    }

    private Task handleDeadline(TaskList taskList, String argument) throws DenimException {
        String[] components = argument.split(" /by ");

        if (argument.isEmpty()) {
            throw new DenimException("The description of a deadline cannot be empty! >.<");
        }

        if (components.length < 2) {
            throw new DenimException("(,,◕　⋏　◕,,) Deadlines must include a 'by' slot!\n"
                    + " Example Usage: deadline homework /by 6pm wednesday");
        }

        Task deadlineTask = new Deadline(components[0], components[1]);
        return handleTaskAddition(taskList, deadlineTask);
    }

    private Task handleEvent(TaskList taskList, String argument) throws DenimException {
        String[] components = argument.split(" /from | /to ");

        if (argument.isEmpty()) {
            throw new DenimException("The description of an event cannot be empty! >.<");
        }

        if (components.length < 3) {
            throw new DenimException("!I'm Tired X.X T.T! The event must include both '/from' and '/to' time slots.\n"
                    + " Example Usage: event dinner /from 6pm /to 8pm");
        }

        Task eventTask = new Event(components[0], components[1], components[2]);
        return handleTaskAddition(taskList, eventTask);
    }

    private void handleDefault() throws DenimException {
        throw new DenimException("Invalid Command!");
    }
}
