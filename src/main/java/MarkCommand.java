public class MarkCommand extends Command {
    private final int taskToMark;

    MarkCommand(int taskToMark) {
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
            speech.say("\t" + markedTask.toString());
            speech.say();
        } catch (RizzlerException e) {
            speech.say(e.getMessage());
            speech.say();
        }
    }
}
