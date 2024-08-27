public class StringJoiner {
    public static String join(String[] s, int start, int end, YapperConcern timeType) throws YapperException {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(s[i]);
            if (i != end - 1) {
                sb.append(' ');
            }
        }
        if (sb.isEmpty()) {
            String message = null;
            switch (timeType) {
            case DESC:
                throw new EmptyDescException("(E.g. todo [DESC], deadline [DESC] /by [DEADLINE], etc)");
            case DEADLINE:
                throw new EndingTimeException("(Format: deadline [DESC] /by [DEADLINE])");
            case FROM:
                throw new StartingTimeException("(Format: event [DESC] /from [FROM] /to [TO])");
            case TO:
                throw new EndingTimeException("(Format: event [DESC] /from [FROM] /to [TO])");
            default:
                message = "(Something went wrong)";
                break;
            }
            throw new YapperFormatException(message);
        }
        return sb.toString();
    }
}
