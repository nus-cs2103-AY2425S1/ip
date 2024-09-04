package yapmeister;

import yapmeister.commands.*;
import yapmeister.task.Task;
import yapmeister.task.ToDo;
import yapmeister.task.TaskList;
import yapmeister.task.Deadline;
import yapmeister.task.Event;
import yapmeister.task.InvalidDescriptionException;
import yapmeister.task.InvalidMarkException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

/**
 * Parser that handles the logic for YapMeister
 * @author BlazeChron
 */
public class Parser {
    TaskList tasks;
    Storage storage;
    UI ui;

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
