package chatbaby;

public class FindCommand extends Command {
    public FindCommand(String commandBody) {
        super(commandBody);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            String target = commandBody.substring(5).trim();
            boolean hasMatch = false;
            TaskList matchedList = new TaskList();
            for (Task task: tasks.getTasks()) {
                if (task.name.contains(target)) {
                    matchedList.addTask(task);
                    hasMatch = true;
                }
            }
            if (!hasMatch) {
                System.out.println("There is no task that matches.");
            } else {
                matchedList.listTasks();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBabyException("Oh no!!! The search keyword cannot be empty!");
        }
    }
}
