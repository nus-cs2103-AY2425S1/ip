package panorama.exception;

import panorama.Message;

/**
 * Thrown to indicate that the search keyword for find is empty.
 */
public class EmptyKeywordException extends PanoramaException {
    /**
     * Constructs a new {@code EmptyKeywordException} with a default error message.
     */
    public EmptyKeywordException() {
        super(Message.MyException.EMPTY_KEYWORD);
    }
}
