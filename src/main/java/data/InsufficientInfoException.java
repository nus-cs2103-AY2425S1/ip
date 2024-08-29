package data;

import task.TaskType;

public class InsufficientInfoException extends Exception {

    public InsufficientInfoException(TaskType type) {
        super(switch (type) {
            case TODO -> "Oops. task.Task title is required. Please try again with: todo <task.Task Title>.";
            case EVENT ->
                    "Oops. Seems like we are missing some information. Please try again with: event <task.Task Title> /from <dd-mm-yyyy hhmm> /to <dd-mm-yyyy hhmm>";
            case DEADLINE ->
                    "Oops. Seems like we are missing some information. Please try again with: deadline <task.Task Title> /by <dd-mm-yyyy hhmm>.";
        });
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
