package tecna.exception;

import java.util.Optional;

public class WrongFormatException extends Exception {
    private String task;
    private String solution;
    private String message;

    public WrongFormatException(String task, String solution) {
        super("Wrong format of ");
        this.task = task;
        this.solution = solution;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        Optional.ofNullable(message)
                .ifPresentOrElse(x -> {},
                        () -> setMessage((new StringBuilder(super.getMessage())).append(task).append(". ").append(solution).toString()));
        return message;
    }
}
