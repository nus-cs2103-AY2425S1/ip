package duck.exceptions;

import java.util.List;

public class MissingArgsException extends Exception {
    private List<String> missingArgs;
    private UsageException usageException;

    public MissingArgsException(List<String> missingArgs, UsageException usageException) {
        this.missingArgs = missingArgs;
        this.usageException = usageException;
    }

    @Override
    public String toString() {
        return String.format("%s\nMissing args: %s", usageException.toString(), String.join(", ", missingArgs));
    }
}
