import enums.CommandName;

public class Parser {

    public int getTaskNumber(String command) throws JarException {
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            try {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber < 0) {
                    throw new JarException("Task number must be greater than 0.");
                }
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new JarException("Invalid task number format.");
            }
        } else {
            throw new JarException("Invalid task command format.");
        }
    }

    public Task parseTask(String command) throws JarException {
        CommandName commandType = parseCommandType(command);

        switch (commandType) {
            case TODO:
                String todoDescription = command.substring(4).trim();
                if (todoDescription.isEmpty()) {
                    throw new JarException("The description of a todo cannot be empty.");
                }
                return new ToDo(todoDescription);

            case DEADLINE:
                String[] deadlineParts = command.substring(8).split("/by", 2);
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new JarException("Invalid deadline format. Use: deadline <description> /by <date>");
                }
                return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());

            case EVENT:
                String[] eventParts = command.substring(5).split("/from", 2);
                if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
                    throw new JarException("Invalid event format. Use: event <description> /from <start time> /to <end time>");
                }
                String[] timeParts = eventParts[1].split("/to", 2);
                if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                    throw new JarException("Invalid event time format. Use: event <description> /from <start time> /to <end time>");
                }
                return new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());

            default:
                throw new JarException("Unknown command: " + command + ". Please enter a valid command.");
        }
    }

    public boolean handleCommand(String command, TaskList taskList, Ui ui) throws JarException {
        CommandName commandType = parseCommandType(command);

        switch (commandType) {
            case EXIT:
                ui.showGoodbye();
                return false;

            case LIST:
                ui.showTaskList(taskList.listTasks());
                break;

            case MARK:
                int markNumber = getTaskNumber(command);
                Task markTask = taskList.getTask(markNumber);
                if (markTask != null) {
                    taskList.markTaskAsDone(markNumber);
                    ui.showTaskMarked(markTask);
                } else {
                    throw new JarException("Invalid task number.");
                }
                break;

            case UNMARK:
                int unmarkNumber = getTaskNumber(command);
                Task unmarkTask = taskList.getTask(unmarkNumber);
                if (unmarkTask != null) {
                    taskList.markTaskAsUndone(unmarkNumber);
                    ui.showTaskUnmarked(unmarkTask);
                } else {
                    throw new JarException("Invalid task number.");
                }
                break;

            case DELETE:
                int deleteNumber = getTaskNumber(command);
                Task deleteTask = taskList.getTask(deleteNumber);
                taskList.deleteTask(deleteNumber);
                ui.showDeleteTask(deleteTask);
                ui.showTaskCount(taskList.getTaskCount());
                break;

            case TODO:
            case DEADLINE:
            case EVENT:
                Task task = parseTask(command);
                taskList.addTask(task);
                ui.showTaskAdded(task.toString());
                ui.showTaskCount(taskList.getTaskCount());
                break;

            default:
                throw new JarException("Unknown command: " + command + ". Please enter a valid command.");
        }

        return true;
    }

    public CommandName parseCommandType(String command) {
        if (command.startsWith("todo")) {
            return CommandName.TODO;
        } else if (command.startsWith("deadline")) {
            return CommandName.DEADLINE;
        } else if (command.startsWith("event")) {
            return CommandName.EVENT;
        } else if (command.startsWith("mark")) {
            return CommandName.MARK;
        } else if (command.startsWith("unmark")) {
            return CommandName.UNMARK;
        } else if (command.startsWith("delete")) {
            return CommandName.DELETE;
        } else if (command.equalsIgnoreCase("list")) {
            return CommandName.LIST;
        } else if (command.equalsIgnoreCase("bye")) {
            return CommandName.EXIT;
        } else {
            return CommandName.UNKNOWN;
        }
    }

}
