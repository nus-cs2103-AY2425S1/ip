package chatbot.command;

import chatbot.exception.EmptyArgsException;
import chatbot.exception.InputException;
import chatbot.logic.TaskList;
import chatbot.task.Event;
import chatbot.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private TaskList taskList;
    private String name;
    private String from;
    private String to;

    public EventCommand(TaskList taskList, String name, String from, String to) {
        this.taskList = taskList;
        this.name = name;
        this.from = from;
        this.to = to;
    }
    @Override
    public String run() throws InputException {
        if (name.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
            throw new EmptyArgsException();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task newTask = new Event(name, LocalDateTime.parse(from, formatter),
                    LocalDateTime.parse(to, formatter));
            return taskList.add(newTask);
        } catch (DateTimeParseException e) {
            return "Error: Unable to parse datetime. Enter date time in yyyy-MM-dd HH:mm format";
        }
    }
}
