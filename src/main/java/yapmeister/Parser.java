package yapmeister;

import java.io.IOException;

import yapmeister.commands.ByeCommand;
import yapmeister.commands.Command;
import yapmeister.commands.DeadlineCommand;
import yapmeister.commands.DeleteCommand;
import yapmeister.commands.EventCommand;
import yapmeister.commands.FindCommand;
import yapmeister.commands.InvalidInputException;
import yapmeister.commands.ListCommand;
import yapmeister.commands.MarkCommand;
import yapmeister.commands.TodoCommand;
import yapmeister.commands.UnmarkCommand;
import yapmeister.task.InvalidMarkException;
import yapmeister.task.TaskList;

/**
 * Parser that handles the logic for YapMeister
 * @author BlazeChron
 */
public class Parser {
    private TaskList tasks;
    private final Storage storage;
    private final UI ui;

    /**
     * Creates a Parser.
     * @param storage Storage where information is stored and saved.
     * @param tasks TaskList storing the tasks.
     */
    public Parser(Storage storage, TaskList tasks, UI ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }
    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles input given by UI by user.
     * @param input Input from user through UI.
     * @return boolean to indicate whether UI should continue running or terminate.
     */
    public boolean processInput(String input) {
        String[] inputs = input.split(" ");
        Command command = null;
        try {
            switch (inputs[0]) {
            case "bye":
                command = new ByeCommand().parse(input);
                return false;
            case "list":
                command = new ListCommand().parse(input);
                break;
            case "mark":
                command = new MarkCommand().parse(input);
                break;
            case "unmark":
                command = new UnmarkCommand().parse(input);
                break;
            case "todo":
                command = new TodoCommand().parse(input);
                break;
            case "deadline":
                command = new DeadlineCommand().parse(input);
                break;
            case "event":
                command = new EventCommand().parse(input);
                break;
            case "delete":
                command = new DeleteCommand().parse(input);
                break;
            case "find":
                command = new FindCommand().parse(input);
                break;
            default:
                ui.displayString("Invalid input please yap yapology");
            }
            if (command != null) {
                command.execute(tasks, storage, ui);
            }
            storage.saveLoadedTasks(tasks); //save after each input
        } catch (InvalidInputException | InvalidMarkException | IOException e) {
            ui.displayString(e.getMessage());
        } catch (NumberFormatException e) {
            ui.displayString("Invalid number format");
        } catch (Exception e) {
            ui.displayString("Unaccounted for exception. "
                    + "Please contact @BlazeChron because he forgot something.");
        }
        return true;
    }
}
