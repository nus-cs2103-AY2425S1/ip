/**
 * TohruException catches errors that are due to improper prompts
 */
public class TohruException extends Exception {
    /**
     * Raises TohruException
     *
     * @param message A description of the exception
     */
    public TohruException(String message) {
        super(message);
    }
}
