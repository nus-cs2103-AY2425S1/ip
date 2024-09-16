package echochat;

public class ChatBot {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public ChatBot(String name) {
        this.ui = new Ui(name);
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.taskList.loadTasksFromFile();
        ui.greet();
    }
    public boolean isDuplicate(Task task) {
        for (Task existingTask : taskList.getTaskList()) {
            if (task.equals(existingTask)) {
                return true;
            }
        }
        return false;
    }
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);

            if (command == null) {
                ui.showError("Invalid input.");
            }

            switch (command.getType()) {
            case EXIT:
                return ui.goodbye();
            case LIST:
                return ui.showTaskList(taskList.getTaskList());
            case MARK:
                Task taskToMark = taskList.getTaskList().get(command.getIndex() - 1);
                taskToMark.markDone();
                return ui.showTaskMarked(taskToMark);
            case UNMARK:
                Task taskToUnmark = taskList.getTaskList().get(command.getIndex() - 1);
                taskToUnmark.markUndone();
                return ui.showTaskUnmarked(taskToUnmark);
            case DELETE:
                Task removedTask = taskList.delete(command.getIndex());
                return ui.showTaskDeleted(removedTask, taskList.getTaskList().size());
            case FIND:
                return ui.showFoundTasks(command.getDescription(), taskList.getTaskList());
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newTask = command.getTask();
                if (isDuplicate(newTask)) {
                    return ui.showDuplicate();
                }
                taskList.addToList(newTask);
                return ui.showTaskAdded(newTask, taskList.getTaskList().size());
            case UNKNOWN:
                return ui.showError("Unknown command.");
            default:
                return ui.showError("Unknown command.");

            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the chatbot, awaiting user input.
     */
    public void run() {
        while (true) {
            try {
                String input = ui.getUserInput();
                Command command = parser.parse(input);

                if (command == null) {
                    ui.showError("Invalid input.");
                    continue;
                }

                switch (command.getType()) {
                case EXIT:
                    ui.goodbye();
                    return;
                case LIST:
                    ui.showTaskList(taskList.getTaskList());
                    break;
                case MARK:
                    Task taskToMark = taskList.getTaskList().get(command.getIndex() - 1);
                    taskToMark.markDone();
                    ui.showTaskMarked(taskToMark);
                    break;
                case UNMARK:
                    Task taskToUnmark = taskList.getTaskList().get(command.getIndex() - 1);
                    taskToUnmark.markUndone();
                    ui.showTaskUnmarked(taskToUnmark);
                    break;
                case DELETE:
                    Task removedTask = taskList.delete(command.getIndex());
                    ui.showTaskDeleted(removedTask, taskList.getTaskList().size());
                    break;
                case FIND:
                    ui.showFoundTasks(command.getDescription(), taskList.getTaskList());
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task newTask = command.getTask();
                    taskList.addToList(newTask);
                    ui.showTaskAdded(newTask, taskList.getTaskList().size());
                    break;
                case UNKNOWN:
                    break;
                default:
                    ui.showError("Unknown command.");
                    break;
                }

            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
