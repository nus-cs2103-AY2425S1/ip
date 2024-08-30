public class AddTodoCommand extends Command {

    private String input;

    public AddTodoCommand(String input){
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() == 5) {
            System.out.println("Error: No description for ToDo task.");
            System.out.println("Please input a description for the task");
            ui.showLine();
        } else {
            String desc = input.substring(5);
            Todo current = new Todo(desc);
            taskList.add(current, storage);
            System.out.println("Added this task:");
            System.out.println(current.toString());
            ui.showLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
