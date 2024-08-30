package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    " +"Bye!!! Thanks for chatting!");
        super.quitBot();
    }


}
