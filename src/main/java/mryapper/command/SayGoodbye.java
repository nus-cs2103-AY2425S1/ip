package mryapper.command;

import mryapper.storage.Storage;
import mryapper.task.TaskList;

/**
 * A command which makes the Chatbot say the goodbye message
 */
public class SayGoodbye extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
