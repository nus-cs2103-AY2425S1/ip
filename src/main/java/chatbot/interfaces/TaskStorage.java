package chatbot.interfaces;

public interface TaskStorage<T> {
    TaskStorageResult<T> getTasks();
    TaskStorageResult<T> addTask(String[] inputParts, T t);
    TaskStorageResult<T> deleteTask(String[] inputParts);
    TaskStorageResult<T> findTasks(String[] inputParts);
    TaskStorageResult<T> setTaskDone(String[] inputParts, boolean status);
    TaskStorageResult<T> saveTasks();
    TaskStorageResult<T> loadTasks();
}
