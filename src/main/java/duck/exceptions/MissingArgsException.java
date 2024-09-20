package duck.exceptions;

import java.util.List;

public class MissingArgsException extends Exception {
    public MissingArgsException(List<String> missingArgs, UsageException usageException) {
        super(String.format("%s\nMissing args: %s", usageException.toString(), String.join(", ", missingArgs)));
    }
}
