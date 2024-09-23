package makima.command;

import makima.task.Task;

/**
 * State for changing priority of input
 */
public class PriorityState extends State {

    private enum State {
        TASK,
        PRIORITY_LEVEL
    };
    private int idx;
    private State state = State.TASK;
    @Override
    public String getResponse(String input, Makima makima) {
        switch (state) {
        case TASK:
            idx = Integer.parseInt(input) - 1;
            if (makima.isValidTaskListIndex(idx)) {
                state = State.PRIORITY_LEVEL;
                return "What priority level would you like to set it to? High / Low?\n";
            } else {
                return "Please input a valid index!\n";
            }
        case PRIORITY_LEVEL:
            switch (input.toLowerCase()) {
            case "high":
                makima.setPriority(idx, Task.PriorityLevel.HIGH);
                makima.setState(new WaitingState());
                return "Successfully set priority of task to high!\n";
            case "low":
                makima.setPriority(idx, Task.PriorityLevel.LOW);
                makima.setState(new WaitingState());
                return "Successfully set priority of task to low!\n";
            default:
                return "Please input a valid priority level!\n";
            }
        default:
            return null;
        }
    }
}
