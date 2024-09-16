package chatbot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.EmptyArgsException;
import chatbot.exception.InputException;
import chatbot.logic.TaskList;
import chatbot.task.Event;
import chatbot.task.Task;

/**
 * Represents the "event" command that creates a new Event task
 */
public class EventCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** Name of the task */
    private String name;
    /** Starting datetime of the task in string representation */
    private String from;
    /** Ending datetime of the task in string representation */
    private String to;

    /**
     * Constructs the DeadlineCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param name The name of the task
     * @param from The starting datetime of the task
     * @param to The ending datetime of the task
     */
    public EventCommand(TaskList taskList, String name, String from, String to) {
        this.taskList = taskList;
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the Event command, adds a Event task to the tasklist
     *
     * @return The String result of the command after it is run
     * @throws InputException A potential Input Exception
     */
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
