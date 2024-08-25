package calebyyy.commands;

import calebyyy.Ui;
import calebyyy.Calebyyy;
import calebyyy.TaskList;


public class FindCommand extends Command {

    public FindCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    @Override
    public void execute(String input) {
        String keyword = input.substring(input.indexOf(' ') + 1);
        taskList.findKeyword(keyword);
    }
    
}
