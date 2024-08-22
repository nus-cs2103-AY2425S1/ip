public class Response {
    String msg;
    String LINE = "____________________________________________________________";

    Response(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        String output = LINE.indent(4) + "\n"
                        + this.msg.indent(4) + "\n"
                        + LINE.indent(4) + "\n";
        return output;
    }
}
