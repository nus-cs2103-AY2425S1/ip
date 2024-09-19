package chatbot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.EmptyArgsException;
import chatbot.exception.InputException;
import chatbot.logic.TaskList;
import chatbot.task.Deadline;
import chatbot.task.Task;

/**
 * Represents the "deadline" command that creates a new Deadline task
 */
public class DeadlineCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** Name of the task */
    private String name;
    /** Deadline datetime of the task in string representation */
    private String deadline;

    /**
     * Constructs the DeadlineCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param name The name of the task
     * @param deadline The deadline of the task
     */
    public DeadlineCommand(TaskList taskList, String name, String deadline) {
        this.taskList = taskList;
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Executes the Deadline command, adds a Deadline task to the tasklist
     *
     * @return The String result of the command after it is run
     * @throws InputException A potential Input Exception
     */
    @Override
    public String run() throws InputException {
        if (name.trim().isEmpty() || deadline.trim().isEmpty()) {
            throw new EmptyArgsException();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task newTask = new Deadline(name, LocalDateTime.parse(deadline, formatter));
            return taskList.add(newTask);
        } catch (DateTimeParseException e) {
            return "Error: Unable to parse datetime. Enter date time in yyyy-MM-dd HH:mm format";
        }
    }
}
