package rizzler.command;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    DeadlineCommand(String deadlineDesc, String deadlineTime) {
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
