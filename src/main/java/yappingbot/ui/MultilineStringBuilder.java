package yappingbot.ui;

public class MultilineStringBuilder {
    private final StringBuilder sb = new StringBuilder();

    public void addLine(String s) {
        Ui.quoteSinglelineText(s, sb);
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
