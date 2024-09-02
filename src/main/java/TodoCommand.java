public class TodoCommand extends Command {
    private final ToDo newTodo;

    TodoCommand(String todoDesc) {
        super();
        this.newTodo = new ToDo(todoDesc);
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        taskLog.addTask(newTodo);
        storage.storeTasks(taskLog);
        speech.say("certainly, i'll keep track of this todo for you ;)");
        speech.say("\t" + newTodo);
        speech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
        speech.say();
    }
}
