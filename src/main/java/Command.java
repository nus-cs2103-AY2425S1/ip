public class Command {
    private CommandType commandType;
    private String arguments;

    public Command(CommandType commandType, String arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getArguments() {
        return arguments;
    }

    public void execute(TasksList tasksList, Ui ui, Storage storage) {
        try {
            switch (commandType) {
            case BYE:
                ui.showGoodbye();
                break;
            case LIST:
                ui.showTasksList(tasksList.getAllTasks());
                break;
            case MARK:
                int markIndex = Integer.parseInt(arguments) - 1;
                Task taskToMark = tasksList.getTask(markIndex);
                taskToMark.markAsDone();
                ui.showMarkTask(taskToMark);
                break;
            case UNMARK:
                int unmarkIndex = Integer.parseInt(arguments) - 1;
                Task taskToUnmark = tasksList.getTask(unmarkIndex);
                taskToUnmark.unmarkAsNotDone();
                ui.showUnmarkTask(taskToUnmark);
                break;
            case TODO:
                Task newToDo = new ToDo(arguments);
                tasksList.addTask(newToDo);
                ui.showAddTask(newToDo, tasksList.size());
                break;
            case DEADLINE:
                String[] deadlineParts = arguments.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new InvalidInputFormatException("OOPS!!! The deadline command requires a description and a date/time after '/by'.");
                }
                Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasksList.addTask(newDeadline);
                ui.showAddTask(newDeadline, tasksList.size());
                break;
            case EVENT:
                String[] eventParts = arguments.split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new InvalidInputFormatException("OOPS!!! The event command requires a description, start time after '/from', and end time after '/to'.");
                }
                Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                tasksList.addTask(newEvent);
                ui.showAddTask(newEvent, tasksList.size());
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(arguments) - 1;
                if (deleteIndex < 1 || deleteIndex >= tasksList.size()) {
                    throw new InvalidTaskNumberException();
                }
                Task taskToDelete = tasksList.getTask(deleteIndex);
                tasksList.deleteTask(deleteIndex);
                ui.showDeleteTask(taskToDelete, tasksList.size());
                break;
            default:
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
            storage.saveTasks(tasksList.getAllTasks());
        } catch (Exception e) {
            ui.showError("Error: " + e.getMessage());
        }
    }

}
