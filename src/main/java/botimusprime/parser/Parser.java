package botimusprime.parser;

import botimusprime.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private botimusprime.tasks.TaskList taskList;
    private Ui ui;
    private botimusprime.storage.Storage storage;

    public Parser(botimusprime.tasks.TaskList taskList, Ui ui, botimusprime.storage.Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public static LocalDateTime stringToDateTime(String timeString) {
        DateTimeFormatter withTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter withoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter slashWithTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter slashWithoutTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDateTime.parse(timeString, withTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(timeString, withoutTime);
                return date.atStartOfDay();
            } catch (DateTimeParseException e2) {
                try {
                    LocalDate date = LocalDate.parse(timeString, slashWithoutTime);
                    return date.atStartOfDay();
                } catch (DateTimeParseException e3) {
                    try {
                        return LocalDateTime.parse(timeString, slashWithTime);
                    } catch (DateTimeParseException e4) {
                        throw new IllegalArgumentException("Wrong date" + timeString);
                    }
                }
            }
        }
    }

    public boolean parse(String input) {
        if (input.equals("bye")) {
            return true;
        } else if (input.equals("list")) {
            taskList.showList();
        } else if (input.startsWith("mark")) {
            taskList.markDone(input);
        } else if (input.startsWith("unmark")) {
            taskList.markUndone(input);
        } else if (input.startsWith("todo")) {
            taskList.addToDo(input);
        } else if (input.startsWith("deadline")) {
            taskList.addDeadline(input);
        } else if (input.startsWith("event")) {
            taskList.addEvent(input);
        } else if (input.startsWith("delete")) {
            taskList.delete(input);
        } else if (input.startsWith("find")) {
            taskList.findTask(input);
        } else {
            System.out.println("bro out here speaking nonsense issit");
        }
        return false;
    }

}