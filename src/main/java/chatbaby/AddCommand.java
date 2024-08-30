package chatbaby;

import chatbaby.Command;
import chatbaby.Storage;
import chatbaby.Task;
import chatbaby.TaskType;
import chatbaby.TaskList;
import chatbaby.Ui;
import chatbaby.Parser;
import chatbaby.ChatBabyException;
import chatbaby.Deadline;
import chatbaby.Event;
import chatbaby.ToDo;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommand extends Command {
    int prefixLength;
    TaskType type;

    public AddCommand(String commandBody, TaskType type) throws ChatBabyException {
        super(commandBody);
        this.type = type;
        switch (type) {
            case TODO -> prefixLength = 5;
            case DEADLINE -> prefixLength = 9;
            case EVENT -> prefixLength = 6;
        }

        String description = commandBody.substring(prefixLength).trim();
        if (description.isEmpty()) {
            throw new ChatBabyException("Oh no!!! The description of a " +
                    type.name().toLowerCase() + " cannot be empty or invalid.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        Task newTask;
        switch (type) {
            case TODO:
                newTask = new ToDo(commandBody);
                break;
            case DEADLINE:
                String[] deadlineParts = commandBody.split("/by ");
                if (deadlineParts.length == 2) {
                    try {
                        String taskDescription = deadlineParts[0].trim();
                        String deadline = deadlineParts[1].trim();
                        newTask = new Deadline(taskDescription, deadline);
                    } catch (DateTimeParseException e) {
                        throw new ChatBabyException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
                    }
                } else {
                    throw new ChatBabyException("Oh no!!! The description of a " +
                            type.name().toLowerCase() + " cannot be empty.");
                }
                break;
            case EVENT:
                String[] eventParts = commandBody.split("/from ");
                if (eventParts.length == 2) {
                    String name = eventParts[0].trim();
                    String[] eventTimes = eventParts[1].split("/to ");
                    if (eventTimes.length == 2) {
                        try {
                            String from = eventTimes[0].trim();
                            String to = eventTimes[1].trim();
                            newTask = new Event(name, from, to);
                        } catch (DateTimeParseException e) {
                            throw new ChatBabyException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
                        }
                    } else {
                        throw new ChatBabyException("Oh no!!! The description of a " +
                                type.name().toLowerCase() + " cannot be empty.");
                    }
                } else {
                    throw new ChatBabyException("Oh no!!! The description of a " +
                            type.name().toLowerCase() + " cannot be empty.");
                }
                break;

            default:
                throw new ChatBabyException("Oh no!!! I'm sorry, but I don't understand that command.");
        }
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task:\n"
                + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");

    }
}
