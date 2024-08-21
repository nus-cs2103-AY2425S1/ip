package task;

import java.util.*;

public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String line) {
        this.description = parseDescription(line);
        this.isCompleted = false;
    }

    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    protected String parseDescription(String line) {
        // TODO: error handling
        return line.split(" ", 2)[1].trim().split("/", 2)[0].trim();
    }

    protected Map<String, String> parseFlags(String line) {
        String[] tokens = line.split("/");
        Map<String, String> arguments = new HashMap<>();
        for (int i = 1; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
            int splitIndex = tokens[i].indexOf(" ");
            if (splitIndex == -1) {
                continue;
            }

            String argumentName = tokens[i].substring(0, splitIndex).trim();
            String argumentValue = tokens[i].substring(splitIndex + 1).trim();
            arguments.put(argumentName, argumentValue);
        }
        return arguments;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
    }
}
