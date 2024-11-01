package exception;

/**
 * Represents an exception where the prohibited characters are used.
 */
public class BlitzProhibitedCharacterException extends BlitzException {
    private String prohibitedChar;

    /**
     * Constructs a new BlitzProhibitedCharacterException object with specified prohibited characters.
     *
     * @param prohibitedChar String indicating the prohibited characters.
     */
    public BlitzProhibitedCharacterException(String prohibitedChar) {
        this.prohibitedChar = prohibitedChar;
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String indicates the prohibited characters should not be used.
     */
    @Override
    public String toString() {
        return "You should not include \"" + prohibitedChar + "\" in any part of the command!";
    }
}
