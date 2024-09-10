package com.commands;

import com.exceptions.InvalidArgumentException;
import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * Find all task with the specified keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String argument) {
        this.keyword = argument;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws InvalidArgumentException {
        ui.showFoundTask(tasks.findAllContains(keyword));
    }
}
