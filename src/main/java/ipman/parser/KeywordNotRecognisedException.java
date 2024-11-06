package ipman.parser;

/**
 * Thrown when the keyword provided by the user cannot be matched to any known
 * command.
 *
 * @see ipman.commands.Command
 */
public class KeywordNotRecognisedException extends RuntimeException {
    /**
     * Constructs a <code>KeywordNotRecognisedException</code> which was thrown
     * because of the keyword.
     *
     * @param keyword the keyword that cannot be matched to a known command
     */
    public KeywordNotRecognisedException(String keyword) {
        super(String.format("The keyword %s is not recognised.", keyword));
    }
}
