package makima.command;

/**
 * State to handle deletion of task
 */
public class DeleteState extends State {
    @Override
    public String getResponse(String input, Makima makima) {
        int idx;
        try {
            idx = Integer.parseInt(input);
            if (makima.isValidTaskListIndex(idx)) {
                makima.setState(new WaitingState());
                makima.delete(idx);
                return "Task deleted successfully!\n";
            } else {
                return "Please input a valid index!\n";
            }
        } catch (NumberFormatException e) {
            return "Please input an integer!\n";
        }
    }
}
