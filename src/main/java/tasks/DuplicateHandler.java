package tasks;

import applemazer.TaskList;

import java.util.HashMap;

public class DuplicateHandler {
    private final HashMap<String, Integer> todoCount = new HashMap<>();
    private final HashMap<String, Integer> deadlineCount = new HashMap<>();
    private final HashMap<String, Integer> eventCount = new HashMap<>();

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

    public boolean hasTodoDuplicate(String key) {
        return todoCount.get(key) != null;
    }

    public void addNewTodo(String key) {
        todoCount.put(key, 1);
    }

    public void deleteTodo(String key) {
        todoCount.remove(key);
    }

    public boolean hasDeadlineDuplicate(String desc, String deadline) {
        String key = desc + deadline;
        return deadlineCount.get(key) != null;
    }

    public void addNewDeadline(String key) {
        deadlineCount.put(key, 1);
    }

    public void deleteDeadline(String key) {
        deadlineCount.remove(key);
    }

    public boolean hasEventDuplicate(String desc, String from, String to) {
        String key = desc + from + to;
        return eventCount.get(key) != null;
    }

    public void addNewEvent(String key) {
        eventCount.put(key, 1);
    }

    public void deleteEvent(String key) {
        eventCount.remove(key);
    }
}
