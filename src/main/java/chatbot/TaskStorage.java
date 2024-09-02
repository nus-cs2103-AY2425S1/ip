package chatbot;

import java.util.List;

public interface TaskStorage {

    void addTask(Task task);

    Task getTask(int taskIdx);

    int getSize();

    void setTaskAsDone(int taskIdx);

    void setTaskAsNotDone(int taskIdx);

    void deleteTask(int taskIdx);

    /**
     * Finds tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the keyword.
     */
    List<Task> findTasks(String keyword);

}
