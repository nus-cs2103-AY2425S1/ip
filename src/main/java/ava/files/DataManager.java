package ava.files;

import ava.task.Task;
import ava.task.tasks.Todo;

class DataManager {

    static String serialize(Task task) {
        return task.serialize();
    }

    static Task deserialize(String task) {
        return new Todo(task);
    }
}
