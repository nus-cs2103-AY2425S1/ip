package duke.tasks;

import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid task index.");
    }

    public Task getLastTask() {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks available.");
        }
        return tasks.get(tasks.size() - 1);
    }

    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        throw new IndexOutOfBoundsException("Invalid task index.");
    }

    public void addTask(String userInput) throws UnknownMessageException, EmptyTodoDescriptionException {
        TaskEnum taskType = determineTaskType(userInput);

        switch (taskType) {
            case TODOS:
                try {
                    String todoDescription = userInput.split(" ", 2)[1];
                    tasks.add(new ToDos(todoDescription));
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyTodoDescriptionException("Please include a description of your todo task!");
                }
            case DEADLINE:
                String[] deadlineInfo = userInput.split("/by");
                String deadlineDescription = deadlineInfo[0].replace("deadline", "").trim();
                String deadlineDate = deadlineInfo[1].trim();
                tasks.add(new Deadline(deadlineDescription, deadlineDate));
                break;
            case EVENT:
                String[] eventInfo = userInput.split("/from");
                String eventDescription = eventInfo[0].replace("event", "").trim();
                String[] eventTime = eventInfo[1].split("/to");
                String start = eventTime[0].trim();
                String end = eventTime[1].trim();
                tasks.add(new Event(eventDescription, start, end));
                break;
            default:
                throw new UnknownMessageException("Unknown task type.");
        }
    }

    private TaskEnum determineTaskType(String input) throws UnknownMessageException {
        if (input.startsWith("todo")) {
            return TaskEnum.TODOS;
        } else if (input.startsWith("deadline")) {
            return TaskEnum.DEADLINE;
        } else if (input.startsWith("event")) {
            return TaskEnum.EVENT;
        } else {
            throw new UnknownMessageException();
        }
    }
}
