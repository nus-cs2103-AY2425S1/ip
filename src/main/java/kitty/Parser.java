package kitty;

import kitty.command.ListCommand;
import kitty.command.AddCommand;
import kitty.command.FindCommand;
import kitty.command.MarkCommand;
import kitty.command.UnmarkCommand;
import kitty.command.DeleteCommand;
import kitty.kittyexceptions.FindException;
import kitty.kittyexceptions.MarksException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public static final Pattern INDEX_COMMAND_PATTERN =
            Pattern.compile("(mark|unmark|delete)\\s+(\\d+)\\s*$");
    public static final Pattern FIND_PATTERN =
            Pattern.compile("^find\\s+(.+)$\\s*");

    public static String parseFirstWord(String str, Ui ui, TaskList tasks, Storage storage) throws FindException,
            MarksException, NumberFormatException, IndexOutOfBoundsException {
        String[] aux = str.split(" ", 2);

        switch (aux[0].trim()) {
        case "find" -> {
            return parseFind(str, ui, tasks);
        }
        case "list" -> {
            return new ListCommand(ui, tasks).run();
        }
        case "mark", "delete", "unmark" -> {
            return parseMarks(str, ui, storage, tasks);
        }
        case "todo", "deadline", "event" -> {
            return new AddCommand(ui, tasks, str, storage).run();
        }
        default -> {
            String unknownCommandResponse = "Burrrrr~ What is this??? I have no idea about it...";
            return ui.showErrorMessage(unknownCommandResponse);
        }
        }
    }


    private static String parseFind(String str, Ui ui, TaskList tasks) throws FindException {
        assert str.contains("find");

        Matcher matcher = FIND_PATTERN.matcher(str);

        if (!matcher.matches()) {
            return new FindException().toString();
        }

        String keyword = matcher.group(1);
        return new FindCommand(ui, tasks, keyword).run();
    }

    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.parse(str, DATE_TIME_FORMAT);
    }

    private static String parseMarks(String str, Ui ui, Storage storage, TaskList tasks) throws MarksException,
            NumberFormatException, IndexOutOfBoundsException {
        assert str.contains("mark") || str.contains("unmark") || str.contains("delete");

        Matcher matcher = INDEX_COMMAND_PATTERN.matcher(str);

        if (!matcher.matches()) {
            return new MarksException().toString();
        }

        String type = matcher.group(1);
        int index = Integer.parseInt(matcher.group(2));

        switch (type) {
        case "mark" -> {
            return new MarkCommand(ui, tasks, index, storage).run();
        }
        case "unmark" -> {
            return new UnmarkCommand(ui, tasks, index, storage).run();
        }
        case "delete" -> {
            return new DeleteCommand(ui, tasks, index, storage).run();
        }
        default -> {
            return "";
        }
        }
    }

}
