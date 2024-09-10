package luna;

import luna.command.Command;
import luna.command.UndoCommand;

/**
 * Represents a chatbot that allows users to add, remove and manipulate tasks.
 */
public class Luna {

    private Storage storage;
    private TaskList tasks;
    private Command lastCommand;

    /**
     * Creates a chatbot session.
     */
    public Luna() {
        this.storage = new Storage();

        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (LunaException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot until exit command is entered.
     *
     * @param input Command entered by user.
     * @return Response to be shown to user.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            if (command instanceof UndoCommand) {
                if (lastCommand == null) {
                    return "No command to undo";
                } else {
                    return lastCommand.undo(tasks, storage);
                }
            } else {
                lastCommand = command;
                return command.execute(tasks, storage);
            }
        } catch (LunaException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Luna().run("Luna started");
    }
}
