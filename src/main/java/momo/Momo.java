package momo;

import momo.command.ArchiveCommand;
import momo.command.CommandType;
import momo.command.DeadlineCommand;
import momo.command.DeleteCommand;
import momo.command.EventCommand;
import momo.command.FindCommand;
import momo.command.MarkCommand;
import momo.command.TodoCommand;
import momo.command.UnmarkCommand;
import momo.exception.MomoException;
import momo.task.TaskList;

/**
 * Handles the main control flow of the chatbot program, responsible
 * for initialising the programs necessary components like {@link Storage}, {@link TaskList},
 * and {@link Ui} to get the program running
 */
public class Momo {

    public static final String FILE_PATH = "data/momo.txt";
    public static final String ARCHIVE_FILE_PATH = "data/archive.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * hihi
     * @param filePath
     */
    public Momo(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the main control loop of the chatbot program,
     * greeting the user then facilitating a continuous reading
     * of their input, triggering ui, task related commands or
     * reminders on properly formatting the input depending on user
     * input until the 'bye' command is triggered
     */


    public String processCommand(String input, CommandType command) {

        try {
            if (command == CommandType.BYE) {
                ui.showFarewell();
            } else if (command == CommandType.LIST) {
                return ui.printList(tasks);
            } else if (command == CommandType.FIND) {
                return FindCommand.run(input, tasks);
            } else if (command == CommandType.MARK) {
                return MarkCommand.run(input, tasks, storage);
            } else if (command == CommandType.UNMARK) {
                return UnmarkCommand.run(input, tasks, storage);
            } else if (command == CommandType.DELETE) {
                return DeleteCommand.run(input, tasks, storage);
            } else {
                if (command == CommandType.TODO) {
                    return TodoCommand.run(input, storage, tasks);
                } else if (command == CommandType.DEADLINE) {
                    return DeadlineCommand.run(input, storage, tasks);
                } else if (command == CommandType.EVENT) {
                    return EventCommand.run(input, storage, tasks);
                } else if (command == CommandType.ARCHIVE) {
                    return ArchiveCommand.run(input, tasks, storage);
                }
            }
        } catch (MomoException e) {
            return e.getMessage();
        }

        return "";
    }

    public String getResponse(String input) {
        try {
            CommandType command = Parser.parseInput(input);
            assert command != null;
            return processCommand(input, command);
        } catch (MomoException e) {
            return e.getMessage();
        }
    }

}










