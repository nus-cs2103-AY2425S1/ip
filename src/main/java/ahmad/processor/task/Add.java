package ahmad.processor.task;

import ahmad.exceptions.deadline.DeadlineEmptyNameException;
import ahmad.exceptions.deadline.DeadlineInvalidArgsException;
import ahmad.exceptions.deadline.DeadlineInvalidTimeException;
import ahmad.exceptions.event.EventEmptyNameException;
import ahmad.exceptions.event.EventInvalidArgsException;
import ahmad.exceptions.event.EventInvalidTimeException;
import ahmad.exceptions.todo.TodoEmptyNameException;
import ahmad.response.Response;

/**
 * Add processor class.
 */
public class Add {
    private static Response process(Task newTask) {
        ahmad.processor.task.TaskList.addTask(newTask);
        return new Response(java.util.List.of("Got it! I have added:\n  "
                + newTask + "\n" + "You now have " + TaskList.getTaskCount() + " tasks!"), false, true);
    }

    /**
     * Returns a response based on the prompt for a todo command.
     *
     * @param prompt Prompt/argument for todo command.
     * @return Appropriate response for todo command.
     * @throws TodoEmptyNameException       If name is empty.
     * @throws DeadlineInvalidArgsException If prompt/argument is invalid.
     * @throws DeadlineEmptyNameException   If name is empty.
     * @throws EventEmptyNameException      If name is empty.
     * @throws EventInvalidArgsException    If prompt/argument is invalid.
     * @throws DeadlineInvalidTimeException If given time is invalid.
     * @throws EventInvalidTimeException    If given time is invalid.
     */
    public static Response createTodo(String prompt) throws TodoEmptyNameException, DeadlineInvalidArgsException,
            DeadlineEmptyNameException, EventEmptyNameException, EventInvalidArgsException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        if (prompt.length() < 5) {
            throw new TodoEmptyNameException();
        }
        final String extractedPrompt = prompt.substring(4);

        final Task newTask = Task.of(TaskType.Todo, extractedPrompt);
        return Add.process(newTask);
    }

    /**
     * Returns a response based on the prompt for a deadline command.
     *
     * @param prompt Prompt/argument for deadline command.
     * @return Appropriate response for deadline command.
     * @throws TodoEmptyNameException       If name is empty.
     * @throws DeadlineInvalidArgsException If prompt/argument is invalid.
     * @throws DeadlineEmptyNameException   If name is empty.
     * @throws EventEmptyNameException      If name is empty.
     * @throws EventInvalidArgsException    If prompt/argument is invalid.
     * @throws DeadlineInvalidTimeException If given time is invalid.
     * @throws EventInvalidTimeException    If given time is invalid.
     */
    public static Response createDeadline(String prompt) throws DeadlineEmptyNameException,
            DeadlineInvalidArgsException, EventEmptyNameException, EventInvalidArgsException, TodoEmptyNameException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        if (prompt.length() < 10) {
            throw new DeadlineEmptyNameException();
        }
        final String extractedPrompt = prompt.substring(9);

        final Task newTask = Task.of(TaskType.Deadline, extractedPrompt);
        return Add.process(newTask);
    }

    /**
     * Returns a response based on the prompt for an event command.
     *
     * @param prompt Prompt/argument for event command.
     * @return Appropriate response for event command.
     * @throws TodoEmptyNameException       If name is empty.
     * @throws DeadlineInvalidArgsException If prompt/argument is invalid.
     * @throws DeadlineEmptyNameException   If name is empty.
     * @throws EventEmptyNameException      If name is empty.
     * @throws EventInvalidArgsException    If prompt/argument is invalid.
     * @throws DeadlineInvalidTimeException If given time is invalid.
     * @throws EventInvalidTimeException    If given time is invalid.
     */
    public static Response createEvent(String prompt) throws DeadlineInvalidArgsException, DeadlineEmptyNameException,
            EventEmptyNameException, EventInvalidArgsException, TodoEmptyNameException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        if (prompt.length() < 7) {
            throw new EventEmptyNameException();
        }
        final String extractedPrompt = prompt.substring(6);

        final Task newTask = Task.of(TaskType.Event, extractedPrompt);
        return Add.process(newTask);
    }
}
