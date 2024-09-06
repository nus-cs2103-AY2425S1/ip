package atreides.ui;

public class Response {
    private static final String LINE = "____________________________________________________________";
    private final String msg;

    Response(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        String output = LINE.indent(4) + "\n"
                        + this.msg.indent(4) + "\n"
                        + LINE.indent(4);
        return output;
    }
}
