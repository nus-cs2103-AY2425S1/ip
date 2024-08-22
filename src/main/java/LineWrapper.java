public class LineWrapper {
    static final String lineRule = "--------------------------------------------\n";
    public static String wrap(String content) {
        return lineRule + content + "\n" + lineRule;
    }
}
