package torne.command.argument;

import torne.command.Command;

import java.util.List;
import java.util.Objects;

/**
 * Class that encapsulates the set of arguments a {@link Command} accepts. A list of {@link CommandArgument}s.
 * <br>
 * This is necessary to get only specific lists of information regarding arguments, e.g. just the list of argument
 * names for parsing.
 */
public class CommandArgumentList {
    private final List<CommandArgument> argList;

    public CommandArgumentList(List<CommandArgument> argList) {
        this.argList = argList;
    }

    public static CommandArgumentList empty() {
        return new CommandArgumentList(List.of());
    }

    /**
     * Returns the list of strings representing argument flags that are required for the {@link torne.command.Parser}.
     * Skips default arguments.
     *
     * @return List of argument flags.
     */
    public List<String> getArgParserStringList() {
        // STREAMS BAYBEH
        return argList
                .stream()
                .map(CommandArgument::getName)
                .filter((s) -> !Objects.equals(s, ""))
                .toList();
    }

    public List<CommandArgument> getArgList() {
        return argList;
    }
}
