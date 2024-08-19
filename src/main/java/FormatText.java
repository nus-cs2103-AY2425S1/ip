public class FormatText {
    private String message;
    public FormatText (String message) {
        this.message = message;
    }

    public String wrapLines() {
        return "____________________________________________________________\n" + message + "\n"
                + "____________________________________________________________";
    }
}
