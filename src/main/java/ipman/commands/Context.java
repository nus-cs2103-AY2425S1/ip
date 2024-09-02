package ipman.commands;

import ipman.models.TaskList;
import ipman.models.TasksFileManager;
import ipman.ui.Ui;

public record Context(TaskList tasks, Ui ui, TasksFileManager fileManager) {}
