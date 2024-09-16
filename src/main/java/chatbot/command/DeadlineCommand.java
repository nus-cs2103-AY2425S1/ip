package chatbot.command;

import chatbot.exception.EmptyArgsException;
import chatbot.exception.InputException;
import chatbot.logic.TaskList;
import chatbot.task.Deadline;
import chatbot.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String name;
    private String deadline;

    public DeadlineCommand(TaskList taskList, String name, String deadline) {
        this.taskList = taskList;
        this.name = name;
        this.deadline = deadline;
    }

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
