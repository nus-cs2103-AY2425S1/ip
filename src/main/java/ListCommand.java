public class ListCommand extends Command {

    public void execute(TaskList tasks,Ui ui, Storage storage) {
        System.out.println("    Here are the tasks in your tasks:");
        for (int i = 0; i < tasks.getLength(); i++) {
            String message = String.format("    %d. %s", i + 1, tasks.get(i).toString());
            System.out.println(message);
        }
    }

}
