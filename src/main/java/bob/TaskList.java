package bob;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void listTasks(Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    public void markTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                ui.showTaskMarked(task.mark());
            } else {
                throw new ChatBotException("Invalid task number for marking.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for marking.");
        }
    }

    public void unmarkTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                ui.showTaskUnmarked(task.unmark());
            } else {
                throw new ChatBotException("Invalid task number for unmarking.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for unmarking.");
        }
    }

    public void deleteTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                tasks.remove(index);
                ui.showTaskDeleted(task.delete(), tasks.size());
            } else {
                throw new ChatBotException("Invalid task number for deleting.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for deleting.");
        }
    }

    public void addToDo(String phrase, Ui ui) throws ChatBotException {
        if (phrase.length() <= 5) {
            throw new ChatBotException("The description of a todo cannot be empty.");
        }
        String description = phrase.substring(5);
        Task newTask = new ToDo(description, TaskType.TODO);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks.size());
    }

    public void addDeadline(String phrase, Ui ui) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /by ");
            String description = parts[0].substring(9);
            String by = parts[1];
            Task newTask = new Deadline(description, by, TaskType.DEADLINE);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChatBotException("Invalid format for deadline. " +
                    "Correct format: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    public void addEvent(String phrase, Ui ui) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /from | /to ");
            String description = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            Task newTask = new Event(description, from, to, TaskType.EVENT);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChatBotException("Invalid format for event. " +
                    "Correct format: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
    }

    public void printTasksOnDate(String date, Ui ui) throws ChatBotException {
        LocalDate searchDate;
        try {
            searchDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd.");
        }

        ui.showLine();
        System.out.println("Here are your tasks on " + searchDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                LocalDate taskDate = ((Deadline) task).getDeadline().toLocalDate();
                if (taskDate.equals(searchDate)) {
                    System.out.println(task);
                }
            } else if (task instanceof Event) {
                LocalDate taskDate = ((Event) task).getFrom().toLocalDate();
                if (taskDate.equals(searchDate)) {
                    System.out.println(task);
                }
            }
        }
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param phrase The input phrase containing the find command and the keyword to search for.
     * @param ui The user interface object used to display results to the user.
     * @throws ChatBotException If the keyword is empty or if there is an error during the search.
     */
    public void searchKeyword(String phrase, Ui ui) throws ChatBotException {
        if (phrase.length() <= 5) {
            throw new ChatBotException("The key of a find command cannot be empty.");
        }

        String[] tmp = phrase.split(" ", 2);
        String key = tmp[1];
        boolean found = false;
        ArrayList<Task> tasksWithKey = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(key.toLowerCase())) {
                tasksWithKey.add(task);
                found = true;
            }
        }

        if (found) {
            ui.showTasksFound(tasksWithKey);
        } else {
            ui.showError("No tasks found containing the key: " + key);
        }
    }

}