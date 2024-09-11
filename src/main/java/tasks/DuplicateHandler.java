package tasks;

import applemazer.TaskList;

import java.util.HashMap;

/**
 * Class that handles instances of duplicate tasks being added to the list.
 */
public class DuplicateHandler {
    private final HashMap<String, Integer> todoCount = new HashMap<>();
    private final HashMap<String, Integer> deadlineCount = new HashMap<>();
    private final HashMap<String, Integer> eventCount = new HashMap<>();

    /**
     * Constructor for a {@code DuplicateHandler} object.
     * <p>
     * When a {@code DuplicateHandler} is created, it stores all current {@code Task}
     * in the given {@code TaskList} into their respective {@code HashMap}'s.
     * @param tasks The {@code TaskList} to use.
     */
    public DuplicateHandler(TaskList tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i) instanceof Todo) {
                todoCount.put(tasks.get(i).toString(), 1);
            } else if (tasks.get(i) instanceof Deadline) {
                deadlineCount.put(((Deadline) tasks.get(i)).getKey(), 1);
            } else {
                eventCount.put(((Event) tasks.get(i)).getKey(), 1);
            }
        }
    }

    /**
     * Checks if a {@code Todo} with the given description already exists.
     * @param key The description of the {@code Todo}.
     * @return {@code true} if a {@code Todo} with the same description
     *         already exists.
     */
    public boolean hasTodoDuplicate(String key) {
        return todoCount.get(key) != null;
    }

    /**
     * Registers a {@code Todo} description to a {@code HashMap}.
     * @param key The description of the {@code Todo}.
     */
    public void addNewTodo(String key) {
        todoCount.put(key, 1);
    }

    /**
     * Deletes a {@code Todo} description from its respective {@code HashMap}.
     * @param key The description of the {@code Todo}.
     */
    public void deleteTodo(String key) {
        todoCount.remove(key);
    }

    /**
     * Checks if a {@code Deadline} with the given key already exists.
     * <p>
     * The key is created by concatenating {@code desc} and {@code deadline}.
     * @param desc The description of the {@code Deadline}.
     * @param deadline The deadline of the {@code Deadline}.
     * @return {@code true} if a {@code Deadline} with the same key already exists.
     */
    public boolean hasDeadlineDuplicate(String desc, String deadline) {
        String key = desc + deadline;
        return deadlineCount.get(key) != null;
    }

    /**
     * Registers a {@code Deadline} key to a {@code HashMap}.
     * @param key The key of the {@code Deadline}.
     */
    public void addNewDeadline(String key) {
        deadlineCount.put(key, 1);
    }

    /**
     * Deletes a {@code Deadline} key from its respective {@code HashMap}.
     * @param key The key of the {@code Deadline}.
     */
    public void deleteDeadline(String key) {
        deadlineCount.remove(key);
    }

    /**
     * Checks if an {@code Event} with the given key already exists.
     * <p>
     * The key is created by concatenating {@code desc}, {@code from}, and {@code to}.
     * @param desc The description of the {@code Event}.
     * @param from The start date of the {@code Event}.
     * @param to The end date of the {@code Event}.
     * @return {@code true} if an {@code Event} with the same key already exists.
     */
    public boolean hasEventDuplicate(String desc, String from, String to) {
        String key = desc + from + to;
        return eventCount.get(key) != null;
    }

    /**
     * Registers an {@code Event} key to a {@code HashMap}.
     * @param key The key of the {@code Event}.
     */
    public void addNewEvent(String key) {
        eventCount.put(key, 1);
    }

    /**
     * Deletes an {@code Event} key from its respective {@code HashMap}.
     * @param key The key of the {@code Event}.
     */
    public void deleteEvent(String key) {
        eventCount.remove(key);
    }
}
