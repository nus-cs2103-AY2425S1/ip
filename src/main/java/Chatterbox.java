enum Commands {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
}

public class Chatterbox {
    private final StoredList taskList;
    private final Storage storage;
    private final Ui ui;

    public Chatterbox(String filePath) {
        storage = new Storage("data/chatterbox_save.txt");
        taskList = storage.readFromSave();
        ui = new Ui();
        ui.printWelcome();
    }

    public void run() {
        boolean isRunningProgram = true;
        while (isRunningProgram) {
            String userInput = ui.getUserInput();
            try {
                isRunningProgram = doCommand(userInput);
            } catch (ChatterBoxError e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public boolean doCommand(String input) throws ChatterBoxError {
        String message;
        try {
            String[] command = Parser.processInput(input);
            switch (Commands.valueOf(command[0].toUpperCase())) {
            case BYE:
                storage.writeToSave(taskList);
                ui.printBye();
                return false;
            case LIST:
                ui.printTasks(taskList);
                break;
            case MARK:
                message = taskList.getItem(Integer.parseInt(command[1])).setCompleted(true);
                ui.printMessage(message);
                break;
            case UNMARK:
                message = taskList.getItem(Integer.parseInt(command[1])).setCompleted(false);
                ui.printMessage(message);
                break;
            case DELETE:
                message = taskList.removeItem(Integer.parseInt(command[1]));
                ui.printMessage(message);
            case TODO:
                message = taskList.addItem(new ToDo(command[1]));
                ui.printMessage(message);
                break;
            case DEADLINE:
                message = taskList.addItem(
                        new Deadline(command[1], Parser.processDateTime(command[2]))
                );
                ui.printMessage(message);
                break;
            case EVENT:
                message = taskList.addItem(
                        new Event(command[1], Parser.processDateTime(command[2]),
                                Parser.processDateTime(command[3]))
                );
                ui.printMessage(message);
                break;
            }
        } catch (ChatterBoxError e) {
            throw e;
        }
        return true;
    }

    public static void main(String[] args) {
        new Chatterbox("data/chatterbox_save.txt").run();
    }
}