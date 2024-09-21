package atreides.ui;

/**
 * Represents a response message that can be printed with a specific format.
 * The message is indented and framed with lines to enhance readability.
 */
public class Response {
    private static final String LINE = "____________________________________________________________";
    private final String msg;

    Response(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return LINE.indent(4) + "\n"
                        + this.msg.indent(4) + "\n"
                        + LINE.indent(4);
    }
}
