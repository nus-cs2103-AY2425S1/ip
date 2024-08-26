import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Bro {
    static final String line = "   ______________________________________________________\n";
    private static final ArrayList<Task> list = new ArrayList<>();

    private void markTask(int i) {
        list.get(i - 1).mark();
        System.out.print(line + "   Nice! I've marked this task as done:\n"
                + "   " + list.get(i - 1) + "\n" + line);
    }

    private void unmarkTask(int i) {
        list.get(i - 1).unmark();
        System.out.print(line + "   OK, I've marked this task as not done yet:\n"
                + "   " + list.get(i - 1) + "\n" + line);
    }

    private void deleteTask(int i) {
        Task curr = list.get(i - 1);
        list.remove(i - 1);
        System.out.println(line + "   Noted. I've removed this task:");
        System.out.println("   " + curr);
        System.out.printf("   Now you have %d tasks in the list\n%s", list.size(), line);
    }

    private void addTodo(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        }
        Task curr = new Todo(s.trim());
        list.add(curr);
        this.printStatus(curr);
    }

    private void addDeadline(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /by ")) {
            throw new BroException("Please include \"/by\" in Deadline!!!");
        }
        String[] info = s.split(" /by ", 2);
        Task curr = new Deadline(info[0], info[1]);
        list.add(curr);
        this.printStatus(curr);
    }

    private void addEvent(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /from ") || !s.contains(" /to ")) {
            throw new BroException("Please include \"/from\" and \"/to\" in Event!!!");
        }
        String[] info = s.split(" /from | /to ", 3);
        Task curr = new Event(info[0], info[1], info[2]);
        list.add(curr);
        this.printStatus(curr);
    }

    private void printStatus(Task t) {
        System.out.println(line + "   Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.printf("   Now you have %d tasks in the list\n%s", list.size(), line);
    }

    private void saveToFile() {
        try {
            FileWriter f = new FileWriter("./src/main/java/data.txt");
            f.write(this.toString());
            f.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            s.append(String.format("%d.%s\n", i + 1, list.get(i)));
        }
        return s.toString();
    }

    public void loadIn() throws FileNotFoundException, BroException {
        File f = new File("./src/main/java/data.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String curr = s.nextLine();
            switch (curr.charAt(3)) {
                case 'T':
                    addTodo(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        list.get(list.size() - 1).mark();
                    }
                    break;
                case 'E':
                    addEvent(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        list.get(list.size() - 1).mark();
                    }
                    break;
                case 'D':
                    addDeadline(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        list.get(list.size() - 1).mark();
                    }
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    public void run() {
        try {
            loadIn();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nothing to load");
        } catch (BroException be) {
            System.out.println(be.getMessage());
        }
        System.out.println(line + "   Hello! I'm Bro\n   What can I do for you?\n" + line);
        Scanner prompt = new Scanner(System.in);
        String word = prompt.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            if (word.equalsIgnoreCase("list")) {
                int len = list.size();
                System.out.print(line + "   Here are the tasks in your list:\n");
                for (int i = 0; i < len; i++) {
                    System.out.printf("   %d.%s\n", i + 1, list.get(i));
                }
                System.out.print(line);
            } else {
                String action = word.split(" ", 2)[0];
                String info;
                if (word.split(" ", 2).length == 1) {
                    info = "";
                } else {
                    info = word.split(" ", 2)[1];
                }
                try {
                    switch (action.toLowerCase()) {
                        case "mark":
                            this.markTask(Integer.parseInt(info));
                            this.saveToFile();
                            break;
                        case "unmark":
                            this.unmarkTask(Integer.parseInt(info));
                            this.saveToFile();
                            break;
                        case "delete":
                            this.deleteTask(Integer.parseInt(info));
                            this.saveToFile();
                            break;
                        case "todo":
                            this.addTodo(info);
                            this.saveToFile();
                            break;
                        case "deadline":
                            this.addDeadline(info);
                            this.saveToFile();
                            break;
                        case "event":
                            this.addEvent(info);
                            this.saveToFile();
                            break;
                        default:
                            System.out.println(line + "   Well, what are u trying to do here? " +
                                    "I don't quite understand :(\n" + line);
                    }
                } catch (BroException be) {
                    System.out.println(be.getMessage());
                } catch (NumberFormatException nfe) {
                    System.out.println(line + "   Please input a number after mark, " +
                            "unmark or delete!!!\n" + line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(line + "   Input a valid number");
                    System.out.printf("   You only have %d tasks in the list\n%s", list.size(), line);
                }
            }
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        System.out.println(line + "   Bye. Hope to see you again soon!\n" + line);
    }

    public static void main(String[] args) {
        Bro bro = new Bro();
        bro.run();
    }
}