package strand.exception;

/**
 * Thrown when an index is out of the valid range.
 */
public class StrandWrongIndexException extends StrandException {
    protected int size;

    /**
     * Constructs a {@code StrandWrongIndexException} with the maximum valid index.
     *
     * @param size The maximum valid index.
     */
    public StrandWrongIndexException(int size) {
        this.size = size;
    }

    @Override
    public String getMessage() {
        return "Index out of range. Please input a number within range (minimum 1, maximum " + this.size + ")";
    }
}
