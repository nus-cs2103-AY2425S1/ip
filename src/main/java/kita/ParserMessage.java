package kita;

/**
 * Wrapper for a return message from the parser
 */
public class ParserMessage {
    private boolean shouldEnd;
    private String msg;

    /**
     * Initialises a parserMessage
     */
    public ParserMessage(String msg, boolean shouldEnd) {
        this.msg = msg;
        this.shouldEnd = shouldEnd;
    }

    public String getMsg() {
        return msg;
    }

    public boolean getEnd() {
        return shouldEnd;
    }
}
