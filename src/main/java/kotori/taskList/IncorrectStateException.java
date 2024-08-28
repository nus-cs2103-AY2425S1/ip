package kotori.taskList;//A-enum is used here

/**
 * This is the exception thrown when marking a marked task
 * or unmarking an unmarked task.
 * */

public class IncorrectStateException extends Exception {
    enum State {mark,unmark}
    State state;
    public IncorrectStateException(String message) {
        if (message.equals("mark")) {
            state = State.mark;
        } else {
            state = State.unmark;
        }
    }

    /**
     * Get the error message of this exception.
     *
     * @return The error message.
     * */
    public String getMessage() {
        switch (state) {
            case mark -> {
                return "Sorry~ This task has already be marked.";
            }
            case unmark -> {
                return "Sorry~ This take is not marked yet";
            }
            default -> {
                return null;
            }
        }
    }
}
