package reo;

public class Parser {
    enum Command {
        BYE,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE,
        UNKNOWN,
    }
    String input;
    String[] words;
    Command command;
    TaskList tasks;
    Ui ui;
    Storage storage;
    public Parser(String input, TaskList tasks, Ui ui, Storage storage) {
        this.input = input;
        this.tasks = tasks;
        this.words = input.split("\\s+");
        this.ui = ui;
        this.storage = storage;

        try {
            this.command = Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            this.command = Command.UNKNOWN;
        }
    }

    /**
     * Interpret user's command, make the necessary file changes,
     * and display the corresponding UI.
     *
     * @return The String representation of the stored ArrayList so far.
     */
    public void parse() {
        switch (command) {
        case LIST:
            ui.list();
            break;
        case TODO:
            try {
                String[] parts1 = input.split(" ", 2);
                Todo toPush = new Todo(parts1[1], false);
                tasks.addTodo(toPush);
                storage.writeFile(toPush);
                ui.addTodo(toPush);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.addTodoError();
            }
            break;
        case MARK:
            try {
                tasks.markTask(Integer.valueOf(words[1]) - 1);
                storage.editLine(Integer.valueOf(words[1]), "mark");
                ui.mark(tasks.get(Integer.valueOf(words[1]) - 1));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.markError();
            }
            break;
        case UNMARK:
            try {
                tasks.unmarkTask(Integer.valueOf(words[1]) - 1);
                storage.editLine(Integer.valueOf(words[1]), "unmark");
                ui.unmark(tasks.get(Integer.valueOf(words[1]) - 1));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.unmarkError();
            }
            break;
        case DEADLINE:
            try {
                String[] parts = input.split("/by", 2);
                String[] firstPart = parts[0].split(" ", 2);

                String name = firstPart[1];
                String deadline = parts[1].trim();

                Deadline toPush1 = new Deadline(name, false, deadline);
                tasks.addDeadline(toPush1);
                storage.writeFile(toPush1);
                ui.addDeadline(toPush1);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.addDeadlineError();
            }
            break;
        case EVENT:
            try {
                String[] parts = input.split("/from", 2);
                String[] firstPart = parts[0].split(" ", 2);
                String[] secondPart = parts[1].split("/to", 2);

                String name = firstPart[1];
                String from = secondPart[0].trim();
                String to = secondPart[1].trim();

                Event toPush2 = new Event(name, false, to, from);
                tasks.addEvent(toPush2);
                storage.writeFile(toPush2);
                ui.addEvent(toPush2);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.addEventError();
            }
            break;
        case DELETE:
            try {
                int index = Integer.valueOf(words[1]) - 1;
                // reo.Task toRemove = tasks.get(index);
                tasks.deleteTask(index);
                storage.removeLine(index + 1);
                ui.delete(tasks.get(index));
            } catch (IndexOutOfBoundsException e) {
                ui.deleteError();
            }
            break;
        case BYE:
            ui.exit();
            break;
        case UNKNOWN:
            ui.enterCommandError();
            break;
        }
    }
}
