package ahmad.processor.task;

import java.util.Arrays;
import java.util.List;

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
    public static Response todo(String prompt) throws TodoEmptyNameException, DeadlineInvalidArgsException,
            DeadlineEmptyNameException, EventEmptyNameException, EventInvalidArgsException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        final List<String> prompts = Arrays.asList(prompt.split("todo "));
        if (prompts.size() < 2) {
            throw new TodoEmptyNameException();
        }
        final Task newTask = Task.of(TaskType.Todo, prompts.get(1));
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
    public static Response deadline(String prompt) throws DeadlineEmptyNameException, DeadlineInvalidArgsException,
            EventEmptyNameException, EventInvalidArgsException, TodoEmptyNameException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        final List<String> prompts = Arrays.asList(prompt.split("deadline "));
        if (prompts.size() < 2) {
            throw new DeadlineEmptyNameException();
        }
        final Task newTask = Task.of(TaskType.Deadline, prompts.get(1));
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
    public static Response event(String prompt) throws DeadlineInvalidArgsException, DeadlineEmptyNameException,
            EventEmptyNameException, EventInvalidArgsException, TodoEmptyNameException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        final List<String> prompts = Arrays.asList(prompt.split("event "));
        if (prompts.size() < 2) {
            throw new EventEmptyNameException();
        }
        final Task newTask = Task.of(TaskType.Event, prompts.get(1));
        return Add.process(newTask);
    }
}
