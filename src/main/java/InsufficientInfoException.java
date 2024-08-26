public class InsufficientInfoException extends Exception {

    public InsufficientInfoException(TaskType type) {
        super(switch (type) {
            case TODO -> "Oops. Task title is required. Please try again with: todo <Task Title>.";
            case EVENT ->
                    "Oops. Seems like we are missing some information. Please try again with: event <Task Title> /from <Start DateTime> /to <End DateTime>/";
            case DEADLINE ->
                    "Oops. Seems like we are missing some information. Please try again with: deadline <Task Title> /by <Deadline>.";
        });
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
