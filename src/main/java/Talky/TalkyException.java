package Talky;

/**
 * Customized Exception for Talky
 */
public class TalkyException extends Exception {
    /**
     * Returns Exception that occurs
     *
     * @param err
     */
    public TalkyException(String err) {
        super(err);
    }
}
