public class Parser {
    public static Query parse(String raw) {
        String[] split = raw.split(" ", 1);
        String command = split[0];
        String details = split[1];
        return new Query(command, details);
    }
}
