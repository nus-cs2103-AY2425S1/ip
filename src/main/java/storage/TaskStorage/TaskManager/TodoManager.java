package storage.TaskStorage.TaskManager;

import task.Task;
import task.ToDo;

public class TodoManager extends TaskManager {
    /**
     * Creates a {@code ToDo} object corresponding to a todo task.
     *
     * @param todoBody   String storing todo description.
     * @param todoStatus Character representing todo status.
     * @return {@code ToDo} object created.
     */
    @Override
    public Task convertToTaskObject(String todoBody, char todoStatus) {
        Task todoTask = new ToDo(todoBody);
        assert todoStatus == 'X' | todoStatus == ' ' : "Invalid todo status extracted.";
        if (todoStatus == 'X') {
            todoTask.markAsDone();
        }
        return todoTask;
    }
}
