package luna;

import luna.command.Command;
import luna.command.UndoCommand;

/**
 * Represents a chatbot that allows users to add, remove and manipulate tasks.
 */
public class Luna {

    private Storage storage;
    private TaskList tasks;
    private Command previousCommand;

    /**
     * Creates a chatbot session.
     */
    public Luna() {
        this.storage = new Storage();
        previousCommand = null;

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
            Command command = Parser.parse(input, previousCommand);
            if (command instanceof UndoCommand) {
                if (previousCommand == null) {
                    return "No command to undo";
                } else {
                    String response = previousCommand.undo(tasks, storage);
                    previousCommand = previousCommand.getPreviousCommand();
                    return response;
                }
            } else {
                previousCommand = command;
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
