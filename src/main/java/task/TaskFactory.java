package task;

import exception.IllegalTaskArgumentException;
import exception.IllegalTaskTypeException;

import java.util.Objects;

public class TaskFactory {
    public static Task userInputToTask(String taskTypeStr, String taskArgs) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        TaskType taskType = TaskType.fromName(taskTypeStr);
        switch (taskType) {
            case TODO:
                if (taskArgs.isEmpty()) {
                    throw new IllegalTaskArgumentException();
                }
                return new ToDo(taskArgs);
            case DEADLINE:
                if (!taskArgs.contains(" /by ")) {
                    throw new IllegalTaskArgumentException();
                }
                String[] deadlineParts = taskArgs.split(" /by ", 2);
                return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            case EVENT:
                if (!taskArgs.contains(" /from ") || !taskArgs.contains(" /to ")) {
                    throw new IllegalTaskArgumentException();
                }
                String[] eventParts = taskArgs.split(" /from | /to ", 3);
                return new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
            default:
                throw new IllegalTaskArgumentException();
        }
    }
    public static Task dbToTask(String taskStr) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        TaskType taskType = TaskType.fromSymbol(args[0]);
        Task task;
        switch (taskType) {
            case TODO:
                if (args.length != 3) {
                    throw new IllegalTaskArgumentException();
                }
                task = new ToDo(args[2]);
                if (args[1].equals("1")) {
                    task.markDone();
                } else if (args[1].equals("0")){
                    task.unmarkDone();
                } else {
                    throw new IllegalTaskArgumentException();
                }
                return task;
            case DEADLINE:
                if (args.length != 4) {
                    throw new IllegalTaskArgumentException();
                }
                task = new Deadline(args[2], args[3]);
                if (args[1].equals("1")) {
                    task.markDone();
                } else if (args[1].equals("0")){
                    task.unmarkDone();
                } else {
                    throw new IllegalTaskArgumentException();
                }
                return task;
            case EVENT:
                if (args.length != 5) {
                    throw new IllegalTaskArgumentException();
                }
                task = new Event(args[2], args[3], args[4]);
                if (args[1].equals("1")) {
                    task.markDone();
                } else if (args[1].equals("0")){
                    task.unmarkDone();
                } else {
                    throw new IllegalTaskArgumentException();
                }
                return task;
            default:
                throw new IllegalTaskTypeException();
        }
    }
}
