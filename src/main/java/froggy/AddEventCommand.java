package froggy;

/**
 * Adds an Event to the task list.
 */
public class AddEventCommand extends Command {

    private final String input;

    /**
     * Constructor that takes in raw input as String.
     *
     * @param input raw input.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int firstIndex = input.toLowerCase().indexOf("/from ");
        int secondIndex = input.toLowerCase().indexOf("/to ");
        if (firstIndex != -1) {
            String desc = input.substring(6, firstIndex - 1);
            String from = input.substring(firstIndex + 6, secondIndex - 1);
            String to = input.substring(secondIndex + 4);
            Event current = new Event(desc, from, to);
            taskList.add(current, storage);
            System.out.println("Added this task:");
            System.out.println(current.toString());
            ui.showLine();
        }
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        int firstIndex = input.toLowerCase().indexOf("/from ");
        int secondIndex = input.toLowerCase().indexOf("/to ");
        String output = "";
        if (firstIndex != -1) {
            String desc = input.substring(6, firstIndex - 1);
            String from = input.substring(firstIndex + 6, secondIndex - 1);
            String to = input.substring(secondIndex + 4);
            Event current = new Event(desc, from, to);
            taskList.add(current, storage);
            taskList.add(current, storage);
            output = current.toString() + "\n";
        }
        return "Added this task:\n" + output + ui.getLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
