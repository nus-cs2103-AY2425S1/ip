package casper.exceptions;

/**
 * An abstract base class for exceptions that provide a solution or suggestion to resolve the error.
 */
public abstract class ExceptionWithSolution extends Exception {
    private final String solution;

    /**
     * Constructs a new {@code ExceptionWithSolution} with the specified detail message and solution.
     *
     * @param message The detail message of the exception.
     * @param solution A suggested solution or resolution for the exception.
     */
    public ExceptionWithSolution(String message, String solution) {
        super(message);
        this.solution = solution;
    }

    /**
     * Returns the detail message of this exception, including the suggested solution.
     *
     * @return A string containing the detail message of this exception and the solution.
     */
    @Override
    public String getMessage() {
        return String.format("ERROR: %s\n%s", super.getMessage(), this.solution);
    }
}
