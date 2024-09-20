package tecna.exception;

import java.util.Optional;

/**
 * Indicates errors while interpreting user's commands.
 *
 * @author DennieDan.
 */
public class WrongFormatException extends Exception {
    private String type;
    private String solution;
    private String message;

    /**
     * Constructs a WrongFormatException instance.
     *
     * @param type Type of command.
     * @param solution How to fix the error.
     */
    public WrongFormatException(String type, String solution) {
        super("Wrong format of ");
        this.type= type;
        this.solution = solution;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        Optional.ofNullable(message)
                .ifPresentOrElse(x -> {},
                        () -> setMessage((new StringBuilder(super.getMessage())).append(type).append(". \n").append(solution).toString()));
        return message;
    }
}
