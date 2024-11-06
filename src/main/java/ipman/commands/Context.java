package ipman.commands;

import ipman.models.TaskList;
import ipman.models.TasksFileManager;
import ipman.ui.Ui;

/**
 * Represents all the arguments needed for every <code>Command</code> to execute
 *
 * @param tasks
 * @param ui
 * @param fileManager
 * @see Command
 */
public record Context(TaskList tasks, Ui ui, TasksFileManager fileManager) {}
