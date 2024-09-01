package jbot.util;

import java.util.ArrayList;

import jbot.task.Task;

@SuppressWarnings({"StaticVariableMayNotBeInitialized", "StaticVariableUsedBeforeInitialization"})
public class TaskList {
    private TaskList() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    private static ArrayList<Task> list;
    public static void setList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    public static Task get(int index) {
        return list.get(index);
    }

    public static int size() {
        return list.size();
    }

    public static void add(Task task) {
        list.add(task);
    }

    public static Task remove(int index) {
        return list.remove(index);
    }
}
