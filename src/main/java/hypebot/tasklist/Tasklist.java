package hypebot.tasklist;

import hypebot.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static hypebot.common.Messages.*;

public class Tasklist {
    private final ArrayList<Task> TASKS;

    public Tasklist() {
        TASKS = new ArrayList<>();
    }

    public Tasklist(List<Task> tasks) {
        this();
        TASKS.addAll(tasks);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public int size() {
        return TASKS.size();
    }

    public Task getTaskByIndex(int index) {
        return TASKS.get(index);
    }

    public void add(Task task) {
        TASKS.add(task);
    }

    public Task delete(int task) throws IndexOutOfBoundsException {
        try {
            return TASKS.remove(task);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(TASK_NUMBER_TO_DELETE_OUT_OF_BOUNDS_ERROR);
        }
    }

    public void mark(int idx) throws IndexOutOfBoundsException {
        try {
            TASKS.get(idx).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(TASK_NUMBER_TO_MARK_OUT_OF_BOUNDS_ERROR);
        }
    }

    public void unmark(int idx) throws IndexOutOfBoundsException {
        try {
            TASKS.get(idx).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(TASK_NUMBER_TO_UNMARK_OUT_OF_BOUNDS_ERROR);
        }
    }

    public Tasklist getHappeningOn(LocalDate date) {
        List<Task> tasksOnDate = TASKS.stream().filter(task -> task.isHappeningOn(date)).toList();
        return new Tasklist(tasksOnDate);
    }

    /**
     * Takes in a search query from user input parsed by Parser sent to a FindCommand,
     * then returns a Tasklist of Tasks with the keyword in the Task name.
     *
     * @param searchQuery Search query from user to search Tasks with the search query in the name.
     * @return Tasklist of Tasks containing search query in name.
     */
    public Tasklist getNameContains(String searchQuery) {
        List<Task> tasksWithSearchQuery = TASKS.stream().filter(task -> {
                Pattern pattern = Pattern.compile(searchQuery, Pattern.CASE_INSENSITIVE);
                return pattern.matcher(task.getName().toLowerCase()).matches();
        }).toList();
        return new Tasklist(tasksWithSearchQuery);
    }

    @Override
    public String toString() {
        StringBuilder listMessage = new StringBuilder();
        for (int i = 0; i < TASKS.size(); i++) {
            listMessage.append(i + 1).append(". ").append(TASKS.get(i)).append("\n");
        }
        return listMessage.toString();
    }
}
