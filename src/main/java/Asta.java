public class Asta {
    private static final String FILE_PATH = "./data/asta.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Asta(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AstaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Asta(FILE_PATH).run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            ui.showLine();
            try {
                switch (command) {
                case BYE:
                    isExit = true;
                    ui.showMessage("Bye. Hope to see you again soon!");
                    break;
                case LIST:
                    tasks.listTasks(ui);
                    break;
                case MARK:
                    int markTaskNumber = tasks.getTaskNumber(fullCommand, "mark");
                    tasks.markTask(markTaskNumber, true);
                    ui.showMessage("Nice! I've marked this task as done:");
                    ui.showMessage(tasks.getTask(markTaskNumber).toString());
                    storage.save(tasks.getTasks());
                    break;
                case UNMARK:
                    int unmarkTaskNumber = tasks.getTaskNumber(fullCommand, "unmark");
                    tasks.markTask(unmarkTaskNumber, false);
                    ui.showMessage("OK, I've marked this task as not done yet:");
                    ui.showMessage(tasks.getTask(unmarkTaskNumber).toString());
                    storage.save(tasks.getTasks());
                    break;
                case TODO:
                    String todoDescription = fullCommand.substring(5).trim();
                    tasks.addTodoTask(todoDescription);
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage("[T][ ] " + todoDescription);
                    storage.save(tasks.getTasks());
                    break;
                case DEADLINE:
                    String[] deadlineParts = fullCommand.substring(9).split(" /by ");
                    tasks.addDeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.getTask(tasks.getSize() - 1).toString());
                    storage.save(tasks.getTasks());
                    break;
                case EVENT:
                    String[] eventParts = fullCommand.substring(6).split(" /from | /to ");
                    tasks.addEventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.getTask(tasks.getSize() - 1).toString());
                    storage.save(tasks.getTasks());
                    break;
                case DELETE:
                    int deleteTaskNumber = tasks.getTaskNumber(fullCommand, "delete");
                    Task removedTask = tasks.deleteTask(deleteTaskNumber);
                    ui.showMessage("Noted. I've removed this task:");
                    ui.showMessage(removedTask.toString());
                    storage.save(tasks.getTasks());
                    break;
                default:
                    ui.showError("Unfortunately, Asta doesn't know what that means...");
                }
            } catch (AstaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
