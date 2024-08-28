public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Check if the list is empty
        if (tasks.isEmpty()) {
            throw new JeffException("List is empty!");
        }

        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < tasks.size(); i++) {
            listString.append(Integer.toString(i + 1)).append(".").append(tasks.get(i).toString());

            // Only add a new line when it is not the last task in the list
            if (i != tasks.size() - 1) {
                listString.append("\n ");
            }

        }

        ui.printText(listString.toString());
    }
}
