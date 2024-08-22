public class LineWrapper {
    static final String lineRule = "--------------------------------------------\n";

    public static String wrap(String content) {
        return String.format("%s%s\n%s", lineRule, content, lineRule);
    }
}
