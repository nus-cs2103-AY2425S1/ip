import java.time.format.DateTimeFormatter;

public class Arts {
    private static final DateTimeFormatter[] INPUT_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Arts(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (ArtsException e) {
            ui.showError(e.getMessage());
            tempTasks = new TaskList();
        }
        tasks = tempTasks;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                CommandType command = parser.parseCommand(input);
                String[] parts = parser.parseArguments(input);

                switch (command) {
                    case BYE:
                        ui.showGoodbye();
                        isExit = true;
                        break;
                    case LIST:
                        ui.showMessage(listTasks());
                        break;
                    case MARK:
                        new MarkCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case UNMARK:
                        new UnmarkCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case DELETE:
                        new DeleteCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case TODO:
                        new AddTodoCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case DEADLINE:
                        new AddDeadlineCommand(tasks, storage, ui, parts[1], INPUT_FORMATTERS).execute();
                        break;
                    case EVENT:
                        new AddEventCommand(tasks, storage, ui, parts[1], INPUT_FORMATTERS).execute();
                        break;
                    default:
                        throw new ArtsException("I'm sorry, but I don't know what that means.");
                }
            } catch (ArtsException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks yet! Why not add some?";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        new Arts("./data/tasks.txt").run();
    }
}
