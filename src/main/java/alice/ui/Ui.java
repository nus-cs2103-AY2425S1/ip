package alice.ui;

public class Ui {
    private static final String FACE = "(ᵕ—ᴗ—)";
    private static final String DIVIDER = "____________________________________________________________";

    public void say(String line) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%s: %s", FACE, line));
        System.out.println(DIVIDER);
    }

    public void say(String[] lines) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%s: %s", FACE, lines[0]));
        for (int i = 1; i < lines.length; i++) {
            System.out.println(String.format("\t%s", lines[i]));
        }
        System.out.println(DIVIDER);
    }

    public void warn(String line) {
        say(String.format("⚠ Oops! %s", line));
    }

    public void warn(String[] lines) {
        lines[0] = String.format("⚠ Oops! %s", lines[0]);
        say(lines);
    }
}