public class LineWrapper {
    static final String lineRule = "--------------------------------------------";

    public static String wrap(String content) {
        return String.format("%s\n%s\n%s", lineRule, content, lineRule);
    }
}
