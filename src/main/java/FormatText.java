public class FormatText {
    private final String message;
    public FormatText (String message) {
        this.message = message;
    }

    public String wrapLines() {
        return "_______________________________________________________________" +
                "_________________________________________________________\n" + message.indent(3)
                + "_______________________________________________________________" +
                "_________________________________________________________";
    }
}
