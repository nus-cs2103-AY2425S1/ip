import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void loadTasks(File f) {
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] arguments = s.split("\\|");

                // add tasks according to their types
                if (arguments[0].equals("T")) {
                    tasks.add(new Todo(arguments[2], Integer.parseInt(arguments[1])));
                } else if (arguments[0].equals("D")) {
                    tasks.add(new Deadline(arguments[2], arguments[3], Integer.parseInt(arguments[1])));
                } else if (arguments[0].equals("E")) {
                    tasks.add(new Event(arguments[2], arguments[3], arguments[4], Integer.parseInt(arguments[1])));
                }
            }
            this.listTasks();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateData(File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Task t : tasks) {
            String data = "";
            int status = t.getStatusIcon().equals("X") ? 1 : 0;
            if (t instanceof Todo) {
                data = String.format("T|%d|%s", status, t.getDescription());
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                data = String.format("D|%d|%s|%s", status, d.getDescription(), d.getDeadline());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                data = String.format("E|%d|%s|%s|%s", status, e.getDescription(), e.getStart(), e.getEnd());
            }

            try {
                FileWriter fw = new FileWriter(f, true);
                fw.write(data);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTodoTask(String[] arguments) throws DukeException {
        if (arguments.length == 1) {
            throw new DukeException("Incorrect usage!");
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

    public void addDeadlineTask(String[] arguments) throws DukeException {
        if (arguments.length == 1) {
            throw new DukeException("Incorrect usage!");
        }

        String desc = "";
        String deadline = "";

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        while (!arguments[ptr].equals("/by")) {
            if (ptr == arguments.length - 1) {
                throw new DukeException("Incorrect usage!");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
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
            throw new DukeException("Deadline cannot be empty!");
        } else {
            deadline = sb2.substring(0, sb2.length() - 1);
        }


        Task instance = new Deadline(desc, deadline);
        tasks.add(instance);

        System.out.printf("Added: [D][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void addEventTask(String[] arguments) throws DukeException {
        if (arguments.length == 1) {
            throw new DukeException("Incorrect usage!");
        }

        String desc = "";
        String start = "";
        String end = "";

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        while (!arguments[ptr].equals("/from")) {
            if (ptr == arguments.length - 1) {
                throw new DukeException("Incorrect usage!");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        ptr++;
        if (ptr >= arguments.length) {
            throw new DukeException("Incorrect usage!");
        }
        //retrieve the start time
        StringBuilder sb2 = new StringBuilder();
        while (!arguments[ptr].equals("/to")) {
            if (ptr == arguments.length - 1) {
                throw new DukeException("Incorrect usage!");
            }
            sb2.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb2.isEmpty()) {
            throw new DukeException("Start timing cannot be empty!");
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
            throw new DukeException("End timing cannot be empty!");
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
            System.out.println(String.format("%d. [%s][%s] %s",
                    counter, t.getTaskType(), t.getStatusIcon(), t.getTaskInfo()));
            counter++;
        }
    }

    public void markTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markDone();
        System.out.println("Amazing! The specified task is now marked as complete:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void unmarkTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markUndone();
        System.out.println("Alright, the specified task has been marked undone:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Yes boss, I have removed the following task:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }
}
