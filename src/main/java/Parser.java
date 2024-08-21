public class Parser {
    public static Query parse(String raw) {
        String[] split = raw.split(" ", 2);
        String command = split[0].strip();
        if (split.length > 1) {
            String details = split[1];
            return new Query(command, details);
        }
        // for standalone commands (list, etc)
        return new Query(command);
    }
}
