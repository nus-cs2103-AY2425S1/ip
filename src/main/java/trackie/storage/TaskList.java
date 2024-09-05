package trackie.storage;

import trackie.tasks.Deadline;
import trackie.tasks.Event;
import trackie.tasks.Task;
import trackie.tasks.Todo;
import trackie.ui.TrackieException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTodoTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Correct usage: todo [desc]");
        }
        StringBuilder sb = new StringBuilder();
        int ptr = 1;
        while (ptr < arguments.length) {
            sb.append(arguments[ptr]).append(" ");
            ptr++;
        }
        String desc = sb.substring(0, sb.length() - 1);
        Task instance = new Todo(desc);
        tasks.add(instance);

        System.out.printf("Added: [T][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void addDeadlineTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Correct usage: deadline [desc] /by [yyyy-mm-dd]");
        }

        String desc;
        String deadline;

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        while (!arguments[ptr].equals("/by")) {
            if (ptr == arguments.length - 1) {
                throw new TrackieException("Correct usage: deadline [desc] /by [yyyy-mm-dd]");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new TrackieException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        //retrieve the deadline
        ptr++;
        while (ptr < arguments.length) {
            sb2.append(arguments[ptr]).append(' ');
            ptr++;
        }

        if (sb2.isEmpty()) {
            throw new TrackieException("Deadline cannot be empty!");
        } else {
            deadline = sb2.substring(0, sb2.length() - 1);
        }

        try {
            Task instance = new Deadline(desc, deadline);
            tasks.add(instance);

            System.out.printf("Added: [D][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
            System.out.printf("You now have %d task(s) in total.\n", tasks.size());
        } catch (DateTimeParseException e) {
            System.out.println("Please use the correct format: yyyy-mm-dd");
            System.out.println("(Also check that you are entering a valid date!)");
        }

    }

    public void addEventTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
        }

        String desc = "";
        String start = "";
        String end = "";

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        while (!arguments[ptr].equals("/from")) {
            if (ptr == arguments.length - 1) {
                throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new TrackieException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        ptr++;
        if (ptr >= arguments.length) {
            throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
        }
        //retrieve the start time
        StringBuilder sb2 = new StringBuilder();
        while (!arguments[ptr].equals("/to")) {
            if (ptr == arguments.length - 1) {
                throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
            }
            sb2.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb2.isEmpty()) {
            throw new TrackieException("Start timing cannot be empty!");
        } else {
            start = sb2.substring(0, sb2.length() - 1);
        }

        ptr++;
        //retrieve the end time
        StringBuilder sb3 = new StringBuilder();
        while (ptr < arguments.length) {
            sb3.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb3.isEmpty()) {
            throw new TrackieException("End timing cannot be empty!");
        } else {
            end = sb3.substring(0, sb3.length() - 1);
        }

        Task instance = new Event(desc, start, end);
        tasks.add(instance);

        System.out.printf("Added: [E][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void listTasks() {
        int counter = 1;
        for (Task t : tasks) {
            System.out.println(String.format("%d. [%s][%s] %s", counter, t.getTaskType(), t.getStatusIcon(), t.getTaskInfo()));
            counter++;
        }
    }

    public void markTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Please specify an index to mark!");
        }

        int index = Integer.parseInt(arguments[1]);

        if (index < 1 || index > tasks.size()) {
            throw new TrackieException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markDone();
        System.out.println("Amazing! The specified task is now marked as complete:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void unmarkTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Please specify an index to unmark!");
        }

        int index = Integer.parseInt(arguments[1]);

        if (index < 1 || index > tasks.size()) {
            throw new TrackieException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markUndone();
        System.out.println("Alright, the specified task has been marked undone:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void deleteTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Please specify an index to unmark!");
        }

        int index = Integer.parseInt(arguments[1]);

        if (index < 1 || index > tasks.size()) {
            throw new TrackieException("Invalid index.");
        }

        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Yes boss, I have removed the following task:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }
}
