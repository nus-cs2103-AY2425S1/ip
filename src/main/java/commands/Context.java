package commands;

import models.Task;
import models.TasksFileManager;
import ui.Ui;

import java.util.List;

public record Context(List<Task> tasks, Ui ui, TasksFileManager fileManager) {}
