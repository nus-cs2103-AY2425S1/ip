public class PrinceException extends Exception {
    public PrinceException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

/*
 * ERRORS TO HANDLE
 * 
 * todo/deadline/event with spaces before or after the words
 * incorrect formatting of deadline/event
 * words such as unmarking/marking on its own should throw errors
 * "todo unmarking a player" should be added as a legit event
 * case sensitivity
 * punctuations
 */
