public class AddEventCommand extends Command {

    private String input;

    public AddEventCommand(String input){
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = input.toLowerCase().indexOf("/from ");
        int index2 = input.toLowerCase().indexOf("/to ");
        if (index != -1) {
            String desc = input.substring(6, index - 1);
            String from = input.substring(index + 6, index2 - 1);
            String to = input.substring(index2 + 4);
            Event current = new Event(desc, from, to);
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
