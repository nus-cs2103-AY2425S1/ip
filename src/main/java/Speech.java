public abstract class Speech {
    private static final String DEFAULT_LINEBREAK = "#############################";
    private String line;

    Speech() {
        this(DEFAULT_LINEBREAK);
    }

    Speech(String custom_line) {
        this.line = custom_line;
    }

    public void changeLineBreak(String newLineBreak) {
        this.line = newLineBreak;
    }

    public void say(String text) {
        System.out.println(text);
    }

    public void lineBreak() {
        System.out.println(this.line);
    }

    public void lineBreak(String prefix, String postfix) {
        System.out.println(prefix + this.line + postfix);
    }
}
