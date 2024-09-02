package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;
import rizzler.ui.RizzlerSpeech;

public class MarkCommand extends Command {
    private final int taskToMark;

    public MarkCommand(int taskToMark) {
        super();
        this.taskToMark = taskToMark;
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        try {
            Task markedTask = taskLog.doTask(taskToMark);
            storage.storeTasks(taskLog);
            speech.say("aight, i'll note that you've completed this.");
            speech.say("\t" + markedTask);
            speech.say();
        } catch (RizzlerException e) {
            speech.say(e.getMessage());
            speech.say();
        }
    }
}
