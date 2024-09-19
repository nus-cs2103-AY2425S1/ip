package cow.todolist;

import java.time.LocalDate;
import java.util.ArrayList;

import cow.exceptions.CowExceptions;
import cow.message.Messages;
import cow.message.Ui;
import cow.tasks.Task;

/**
 * Creates a TodoList.
 **/
public class TodoList {
    private final ArrayList<Task> todoList = new ArrayList<>();

    public TodoList() {

    }

    /**
     * Adds a new item to the todo list.
     *
     * @param task A Tasks.Task object to be added.
     * @return The update list of tasks.Todo items.
     */
    public ArrayList<Task> add(Task task) {
        todoList.add(task);
        return todoList;
    }

    /**
     * Marks the task as index as done.
     *
     * @param i index to mark.
     * @throws CowExceptions if index is invalid.
     */
    public void markTask(int i, Ui ui) throws CowExceptions {
        try {
            Task t = todoList.get(i);
            t.markAsDone();
            ui.printMarked(t);
        } catch (IndexOutOfBoundsException e) {
            throw new CowExceptions(Messages.INDEX_OUT_OF_RANGE);
        }

    }

    /**
     * Unmarks the task as index as done.
     *
     * @param i index to mark.
     * @throws CowExceptions if index is invalid.
     */
    public void unmarkTask(int i, Ui ui) throws CowExceptions {
        try {
            Task t = todoList.get(i);
            t.unmarkAsDone();
            ui.printUnmarked(t);
        } catch (IndexOutOfBoundsException e) {
            throw new CowExceptions(Messages.INDEX_OUT_OF_RANGE);
        }

    }

    /**
     * Returns a string that consists of the number of tasks in the list.
     *
     * @return A string that consists of the number of tasks in the list.
     */
    public String count() {
        return "Now you have " + this.todoList.size() + " tasks in the list.";
    }

    /**
     * Removes the task at the index.
     *
     * @param i index of task to remove.
     * @return The removed Tasks.Task.
     */
    public Task delete(int i) throws CowExceptions {
        try {
            Task t = todoList.remove(i);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new CowExceptions(Messages.INDEX_OUT_OF_RANGE);
        }
    }

    public String getSaveData() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < todoList.size(); i++) {
            result.append(todoList.get(i).getSaveData());
            if (i != todoList.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Returns a filtered TodoList with elements with date equal to deadline.
     *
     * @param date Targeted date.
     * @return A TodoList.
     */
    public TodoList getDueAt(LocalDate date) {
        TodoList filteredTodo = new TodoList();
        this.todoList.stream()
                .filter(t -> t.getDate().equals(date))
                .forEach(filteredTodo::add);
        return filteredTodo;
    }

    /**
     * Returns a filtered TodoList with elements with description
     * containing substring.
     *
     * @param substring substring to find.
     * @return a TodoList.
     */
    public TodoList getFilteredByDesc(String substring) {
        TodoList filteredTodo = new TodoList();
        this.todoList.stream()
                .filter(t -> t.getContainsSubString(substring))
                .forEach(filteredTodo::add);
        return filteredTodo;
    }

    @Override
    public String toString() {
        String string = "";
        int size = this.todoList.size();
        for (int i = 0; i < size; i++) {
            string += String.format("%d.%s", i + 1, todoList.get(i));
            if (i != size - 1) {
                string += "\n";
            }
        }
        return string;
    }
}
