package makima.command;

/**
 * State to handle marking events
 */
public class MarkState extends State {
    @Override
    public String getResponse(String input, Makima makima) {
        try {
            int idx = Integer.parseInt(input);
            if (makima.isValidTaskListIndex(idx)) {
                makima.setState(new WaitingState());
                makima.mark(idx);
                return "Task marked successfully!\n";
            } else {
                return "Please input a valid index!\n";
            }
        } catch (NumberFormatException e) {
            return "Please input an integer!\n";
        }
    }
}
