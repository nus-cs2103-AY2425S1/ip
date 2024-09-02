package rizzler.command;

import rizzler.Storage;
import rizzler.task.Deadline;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(String deadlineDesc, String deadlineTime) {
        super();
        this.deadline = new Deadline(deadlineDesc, deadlineTime);
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        taskLog.addTask(deadline);
        storage.storeTasks(taskLog);
        speech.say("certainly, i'll keep track of this deadline for you ;)");
        speech.say("\t" + deadline);
        speech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
        speech.say();
    }
}
