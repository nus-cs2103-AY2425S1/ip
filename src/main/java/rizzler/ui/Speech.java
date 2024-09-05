package rizzler.ui;

/**
 * Represents an abstract class that's able to print out messages to the user with formatting.
 */
public abstract class Speech {
    private static final String DEFAULT_LINEBREAK = "#################################################################";
    private String line;

    Speech() {
        this(DEFAULT_LINEBREAK);
    }

    Speech(String customLine) {
        this.line = customLine;
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
