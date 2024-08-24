package TodoList;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.CowExceptions;
import message.Message;
import message.Messages;
import tasks.Task;

public class TodoList {
    private ArrayList<Task> todoList = new ArrayList<>();

    public TodoList() {

    }

    /**
     * Adds a new item to the todo list.
     * @param task A Tasks.Task object to be added.
     * @return The update list of tasks.Todo items.
     */
    public ArrayList<Task> add(Task task) {
        todoList.add(task);
        return todoList;
    }

    /**
     * Get Tasks.Task in TodoList.TodoList
     * @param i The Index you want to retrieve
     * @return The Tasks.Task at index i
     */
    public Task get(int i) {
        return todoList.get(i);
    }

    public void markTask(int i) throws CowExceptions {
        try {
            Task t = todoList.get(i);
            t.markAsDone();
            Message.printMarked(t);
        } catch (IndexOutOfBoundsException e) {
            throw new CowExceptions(Messages.INDEX_OUT_OF_RANGE);
        }

    }

    public void unmarkTask(int i) throws CowExceptions {
        try {
            Task t = todoList.get(i);
            t.unmarkAsDone();
            Message.printUnmarked(t);
        } catch (IndexOutOfBoundsException e) {
            throw new CowExceptions(Messages.INDEX_OUT_OF_RANGE);
        }

    }

    /**
     * Returns a string that consists of the number of tasks in the list.
     * @return A string that consists of the number of tasks in the list.
     */
    public String count() {
        return "Now you have " + this.todoList.size() + " tasks in the list.";
    }

    /**
     * Removes the task at the index.
     * @param i index of task to remove.
     * @return The removed Tasks.Task.
     */
    public Task delete(int i) {
        return todoList.remove(i);
    }
    public String getSaveData() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < todoList.size(); i++) {
            result.append(todoList.get(i).getSaveData());
            if (i != todoList.size() - 1) result.append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a filtered TodoList.TodoList with elements with date equal to deadline
     * @param date Targeted date
     * @return A TodoList.TodoList
     */
    public TodoList getDueAt (LocalDate date) {
        TodoList filteredTodo = new TodoList();
        this.todoList.stream()
                .filter(t -> t.getDate().equals(date))
                .forEach(filteredTodo::add);
        return filteredTodo;
    }

    @Override
    public String toString() {
        String string = "Here are the tasks in your list:\n";
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
