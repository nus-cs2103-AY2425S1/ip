package makima.command;

/**
 * State for handling commands
 */
public class WaitingState extends State {

    @Override
    public String getResponse(String input, Makima makima) {
        switch (input) {
        case "bye":
            makima.setState(new DeactivatedState());
            return Makima.FAREWELL;

        case "list":
            return makima.getTaskList();

        case "mark":
            makima.setState(new MarkState());
            return makima.getTaskList() + "Which item would you like to mark?\n";

        case "unmark":
            makima.setState(new UnmarkState());
            return makima.getTaskList() + "Which item would you like to unmark?\n";

        case "todo":
            makima.setState(new ToDoState());
            return "What is the task name?\n";

        case "deadline":
            makima.setState(new DeadlineState());
            return "What is the deadline name?\n";

        case "event":
            makima.setState(new EventState());
            return "What is the event name?\n";

        case "delete":
            makima.setState(new DeleteState());
            return "Which item would you like to delete?\n";

        case "find":
            makima.setState(new FindState());
            return "What tasks are you searching for?\n";

        default:
            return "Unknown command!\n";
        }
    }
}
