package commands;

import models.TaskList;
import models.TasksFileManager;
import ui.Ui;

public record Context(TaskList tasks, Ui ui, TasksFileManager fileManager) {}
