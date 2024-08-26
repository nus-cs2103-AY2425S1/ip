import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    public void addTask(String info, TaskType type) throws InsufficientInfoException {
        if (info.isBlank()) {
            throw new InsufficientInfoException(type);
        } else {
            Task task = Task.of(info.substring(1), type);
            list.add(task);
            System.out.printf("Got it. I've added this task:\n %s\nNow you have %s tasks in the list.\n", task, list.size());
        }
    }

    public void deleteTask(int index) {
        Task removed = list.remove(index - 1);
        System.out.printf("Noted. I've removed this task:\n %s\nNow you have %s tasks in the list.\n", removed, list.size());
    }

    public void markTaskAsDone(int index) {
        list.get(index - 1).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        list.get(index - 1).markAsUndone();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        if (list.isEmpty())
            string.append("Your task list is empty. Try adding tasks: \n1. todo <Task Title> \n2. event <Task Title> /from <Start Date> /to <End Date> \n3. deadline <Task Title> /by <Due Date>");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                string.append("\n");
            }
            string.append(Integer.toString(i + 1)).append(". ").append(list.get(i).toString());
        }

        return string.toString();
    }
}