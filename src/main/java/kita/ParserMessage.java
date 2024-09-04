package kita;

public class ParserMessage {
    private boolean shouldEnd;
    private String msg;

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
