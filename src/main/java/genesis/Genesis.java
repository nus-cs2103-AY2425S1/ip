package genesis;

import commandparser.CommandParser;
import storage.Storage;
import taskmanager.TaskManager;
import ui.Ui;

/**
 * Represents a generic task.
 */







/**
 * Manages a list of tasks.
 */

/**
 * Handles loading tasks from and writing tasks to a file.
 */






/**
 * The main class for running the Genesis task management application.
 */
public class Genesis {
    public TaskManager taskManager;
    public Storage storage;
    public CommandParser parser;
    public Ui ui;

    public Genesis() {
        this.taskManager = new TaskManager();

        this.storage = new Storage(taskManager);
        this.parser = new CommandParser(taskManager, storage);
        this.ui = new Ui(taskManager, parser);
        storage.loadTasks(parser, ui);
    }


    public String initialise() {
        return "Hello! I'm Genesis!\nWhat can I do for you?\n";

    }
}

