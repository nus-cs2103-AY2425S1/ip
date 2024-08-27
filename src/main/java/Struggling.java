import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

enum Commands
{
    bye, list, mark, unmark, todo, deadline, event, delete;
}

public class Struggling {
    final private String name = "struggling";
    final private String fileName = "./data/Struggling.txt";

    private ArrayList<Task> taskArr = new ArrayList<>();
    private boolean isActive;

    Struggling() {
        reply(String.format("Hello! I'm %s\nWhat can I do for you?", this.name));

        try {
            this.isActive = true;
            String directory = fileName.substring(0, fileName.lastIndexOf("/"));
            new File(directory).mkdir();
            if(!new File(fileName).createNewFile()) {
                loadTask();
            }
        } catch (FileNotFoundException e) {
            reply("Save file not found, please try again!");
            this.isActive = false;
        } catch (IOException e) {
            reply("Failed to create a save file, please contact the developer!");
            this.isActive = false;
        } catch (StrugglingException e) {
            reply("Save file corrupted, using a fresh save file");
            resetSaveFile();
            this.taskArr.clear();
        }
    }

    private void loadTask() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] args = s.nextLine().split(" \\| ");
            Task task = switch (args[0]) {
                case "T" -> new ToDo(args[2]);
                case "D" -> new Deadline(args[2], LocalDate.parse(args[3]));
                case "E" -> new Event(args[2], args[3], args[4]);
                default -> throw new StrugglingException("Save file corrupted");
            };

            if (Objects.equals(args[1], "1")) {
                task.mark();
            } else if (!Objects.equals(args[1], "0")) {
                throw new StrugglingException("Save file corrupted");
            }

            this.taskArr.add(task);
        }
    }

    private void resetSaveFile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write("");
            fw.close();
        } catch (Exception e) {
            reply("Recovery failed, please contact the developer");
            this.isActive = false;
        }
    }

    public void read(String cmd) {
        try {
            String[] args = cmd.split(" ");

            switch (Commands.valueOf(args[0])) {
                case bye:
                    reply("Bye. Hope to see you again soon!");
                    this.isActive = false;
                    break;
                case list:
                    list();
                    break;
                case mark:
                    markTask(Integer.parseInt(args[1]));
                    break;
                case unmark:
                    unmarkTask(Integer.parseInt(args[1]));
                    break;
                case todo:
                    try {
                        addTask(new ToDo(cmd.substring(5)));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new StrugglingException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case deadline:
                    int byIndex = cmd.indexOf("/by ");
                    String dDescription = cmd.substring(9, byIndex).trim();
                    LocalDate dBy = LocalDate.parse(cmd.substring(byIndex + 4));
                    addTask(new Deadline(dDescription, dBy));
                    break;
                case event:
                    int fromIndex = cmd.indexOf("/from ");
                    int toIndex = cmd.indexOf("/to ");
                    String eDescription = cmd.substring(6, fromIndex).trim();
                    String eFrom = cmd.substring(fromIndex + 6, toIndex).trim();
                    String eTo = cmd.substring(toIndex + 4);
                    addTask(new Event(eDescription, eFrom, eTo));
                    break;
                case delete:
                    deleteTask(Integer.parseInt(args[1]));
                    break;
            }
        } catch (IllegalArgumentException e) {
            reply("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (StrugglingException e) {
            reply(e.getMessage());
        }
    }

    private void reply(String str) {
        String line = "____________________________________________________________";
        StringBuilder indent = new StringBuilder();
        for(String s : str.split("\\R")) {
            indent.append(" ").append(s).append("\n");
        }
        String box = String.format("%s\n%s%s", line, indent, line);
        for(String s : box.split("\\R")) {
            System.out.printf("\t%s\n", s);
        }
        System.out.println();
    }

    private void addTask(Task task) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(task.getState() + System.lineSeparator());
            fw.close();

            this.taskArr.add(task);
            reply(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    task, this.taskArr.size()));
        } catch (IOException e) {
            reply("Saving failed due to IO error, please contact developer!");
            this.isActive = false;
        }
    }

    private  void list() {
        StringBuilder ans = new StringBuilder("Here are the tasks in your list:\n");
        int count = 0;
        for(Task t : this.taskArr) {
            ans.append(String.format("%d. %s\n", ++count, t));
        }

        if(!ans.isEmpty()) {
            ans.deleteCharAt(ans.length() - 1);
        }

        reply(ans.toString());
    }

    private void markTask(int i) {
        int index = i - 1;
        Task t = this.taskArr.get(index);
        t.mark();

        reply(String.format("Nice! I've marked this task as done:\n\t%s", t));
    }

    private void unmarkTask(int i) {
        int index = i - 1;
        Task t = this.taskArr.get(index);
        t.unmark();

        reply(String.format("OK, I've marked this task as not done yet:\n\t%s", t));
    }

    private void deleteTask(int i) {
        int index = i - 1;
        Task t = this.taskArr.remove(index);

        reply(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                t, this.taskArr.size()));
    }

    public boolean isActive() {
        return this.isActive;
    }

    public static void main(String[] args) {
        Struggling bot = new Struggling();
        Scanner sc = new Scanner(System.in);

        do {
            bot.read(sc.nextLine());
        } while (bot.isActive());

    }
}

