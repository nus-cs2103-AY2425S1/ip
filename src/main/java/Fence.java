import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Fence {

    private ArrayList<Task> items = new ArrayList<>();

    public void greet() {
        System.out.println("nihao! I'm Fence |=|=|=|=|=|");
    }

    public void exit() {
        System.out.println("have good day :)");
    }

    public void add(Task task) {
        System.out.println("added: " + task);
        items.add(task);
    }

    public void list() {
        System.out.println("!plans:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }
    }

    public void mark(int i) {
        Task task = items.get(i-1);
        task.complete();
        System.out.println("good job");
        System.out.println(task);
    }

    public void unmark(int i) {
        Task task = items.get(i-1);
        task.undo();
        System.out.println("huh?");
        System.out.println(task);
    }

    public void count() {
        System.out.println("Now you have " + items.size() +
                ((items.size() == 1) ? " item " : " items ") + "in the list.");
    }

    public void delete(int i) {
        System.out.println("removed: " + items.get(i-1));
        System.out.println("(we never make plans)");
        items.remove(i - 1);
    }

    public void saveAppend(Task task) {
        File taskFile = new File("./data/fence.txt");
        try {
            FileWriter fw = new FileWriter(taskFile, true);
            fw.write(task.toTxt() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void saveRewrite() {
        File taskFile = new File("./data/fence.txt");
        try {
            if (taskFile.delete()) {
                System.out.println("deleted");
            }
            taskFile.createNewFile();
            FileWriter fw = new FileWriter(taskFile, true);
            for (Task item : items) {
                fw.write(item.toTxt() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void read() {
        File dataDir = new File("./data");
        dataDir.mkdirs();
        File taskFile = new File("./data/fence.txt");
        try {
            taskFile.createNewFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(command);
                String firstWord = st.nextToken();
                String status = st.nextToken();
                boolean isDone = status.equals("(DONE)");
                if (firstWord.equals("TODO")) {
                    try {
                        String desc = st.nextToken();
                        while (st.hasMoreTokens()) {
                            desc = desc + " " + st.nextToken();
                        }
                        Todo todo = new Todo(desc);
                        this.items.add(todo);
                    } catch (NoSuchElementException e) {
                        System.out.println("Data file corrupted");
                    }
                    if (isDone) {
                        this.items.get(this.items.size() - 1).complete();
                    }
                } else if (firstWord.equals("DEADLINE")) {
                    String desc = st.nextToken();
                    String by = "";
                    boolean descDone = false;
                    while (st.hasMoreTokens()) {
                        String nextWord = st.nextToken();
                        if (nextWord.equals("/by")) {
                            descDone = true;
                            by = st.nextToken();
                            continue;
                        }
                        if (descDone) {
                            by = by + " " + nextWord;
                        } else {
                            desc = desc + " " + nextWord;
                        }
                    }
                    Deadline deadline = new Deadline(desc, by);
                    this.items.add(deadline);
                    if (isDone) {
                        this.items.get(this.items.size() - 1).complete();
                    }
                } else if (firstWord.equals("EVENT")) {
                    String desc = st.nextToken();
                    String from = "";
                    String to = "";
                    boolean descDone = false;
                    boolean fromDone = false;
                    while (st.hasMoreTokens()) {
                        String nextWord = st.nextToken();
                        if (nextWord.equals("/from")) {
                            descDone = true;
                            from = st.nextToken();
                            continue;
                        }
                        if (nextWord.equals("/to")) {
                            fromDone = true;
                            to = st.nextToken();
                            continue;
                        }
                        if (fromDone) {
                            to = to + " " + nextWord;
                        } else if (descDone) {
                            from = from + " " + nextWord;
                        } else {
                            desc = desc + " " + nextWord;
                        }
                    }
                    Event event = new Event(desc, from, to);
                    this.items.add(event);
                    if (isDone) {
                        this.items.get(this.items.size() - 1).complete();
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Fence fence = new Fence();
        fence.read();
        fence.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(command);
            String firstWord = st.nextToken();
            if (command.equals("bye")) {
                fence.exit();
                break;
            } else if (command.equals("list")) {
                fence.list();
            } else if (firstWord.equals("todo")) {
                try {
                    String desc = st.nextToken();
                    while (st.hasMoreTokens()) {
                        desc = desc + " " + st.nextToken();
                    }
                    Todo todo = new Todo(desc);
                    fence.add(todo);
                    fence.count();
                    fence.saveAppend(todo);
                } catch (NoSuchElementException e) {
                    System.out.println("doing nothing");
                }
            } else if (firstWord.equals("deadline")) {
                String desc = st.nextToken();
                String by = "";
                boolean descDone = false;
                while (st.hasMoreTokens()) {
                    String nextWord = st.nextToken();
                    if (nextWord.equals("/by")) {
                        descDone = true;
                        by = st.nextToken();
                        continue;
                    }
                    if (descDone) {
                        by = by + " " + nextWord;
                    } else {
                        desc = desc + " " + nextWord;
                    }
                }
                Deadline deadline = new Deadline(desc, by);
                fence.add(deadline);
                fence.count();
                fence.saveAppend(deadline);
            } else if (firstWord.equals("event")) {
                String desc = st.nextToken();
                String from = "";
                String to = "";
                boolean descDone = false;
                boolean fromDone = false;
                while (st.hasMoreTokens()) {
                    String nextWord = st.nextToken();
                    if (nextWord.equals("/from")) {
                        descDone = true;
                        from = st.nextToken();
                        continue;
                    }
                    if (nextWord.equals("/to")) {
                        fromDone = true;
                        to = st.nextToken();
                        continue;
                    }
                    if (fromDone) {
                        to = to + " " + nextWord;
                    } else if (descDone) {
                        from = from + " " + nextWord;
                    } else {
                        desc = desc + " " + nextWord;
                    }
                }
                Event event = new Event(desc, from, to);
                fence.add(event);
                fence.count();
                fence.saveAppend(event);
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                int i = Integer.parseInt(st.nextToken());
                if (firstWord.equals("mark")) {
                    fence.mark(i);
                    fence.saveRewrite();
                } else {
                    fence.unmark(i);
                    fence.saveRewrite();
                }
            } else if (firstWord.equals("delete")) {
                int i = Integer.parseInt(st.nextToken());
                fence.delete(i);
                fence.count();
                fence.saveRewrite();
            } else {
                System.out.println("fence is programmed to track your tasks and has long lost all ability " +
                        "to do other things ");
            }
        }
    }
}
