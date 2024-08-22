package chatbot;

public interface TaskStorage {

    void addTask(Task task);

    Task getTask(int taskIdx);

    int getSize();

    void setTaskAsDone(int taskIdx);

    void setTaskAsNotDone(int taskIdx);

    void deleteTask(int taskIdx);

}
