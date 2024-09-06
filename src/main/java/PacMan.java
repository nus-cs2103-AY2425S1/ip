import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PacMan {
    private static final ArrayList<Task> list = new ArrayList<>();

    private static void echo(String text) {
        System.out.println("  " + text);
    }

    private static void greet() {
        echo("Hello! I'm PacMan. How can I help you?");
    }

    private static void exit() {
        echo("Good bye. Hope to see you soon!");
    }

    private static void addList(Task newTask) {
        echo("Got it. I've added this task:");
        echo("  " + newTask);
        list.add(newTask);
        echo("Now you have " + list.size() + " tasks in the list.");
    }

    private static void printList() {
        echo("Here are the tasks in your list:");
        for (int index = 1; index <= list.size(); index = index + 1) {
            echo(index + "." + list.get(index - 1));
        }
    }

    private static void markTask(int index) {
        echo("Got it. I've marked this task done:");
        list.get(index - 1).setMarkDone(true);
        echo("  " + list.get(index - 1));
    }

    private static void unmarkTask(int index) {
        echo("Got it. I've marked this task as not done yet:");
        list.get(index - 1).setMarkDone(false);
        echo("  " + list.get(index - 1));
    }

    private static void addTodo(String task) {
        addList(new Todo(task));
    }

    private static void addDeadline(String task) {
        String[] splitter = task.split("/", 2);
        String taskName = splitter[0];
        String by = splitter[1].split(" ", 2)[1];
        addList(new Deadline(taskName, by));
    }

    private static void addEvent(String task) {
        String[] splitter = task.split("/", 3);
        String taskName = splitter[0];
        String from = splitter[1].split(" ", 2)[1];
        String to = splitter[2].split(" ", 2)[1];
        from = from.substring(0, from.length() - 1);
        addList(new Event(taskName, from, to));
    }

    private static void deleteTask(int index) {
        Task deletedTask = list.remove(index - 1);
        echo("Noted. I've removed this task:");
        echo("  " + deletedTask);
        echo("Now you have " + list.size() + " tasks in the list.");
    }

    private static void taskParser() {
        File directory = new java.io.File("data");
        directory.mkdir();
        File file = new java.io.File("data/PacMan.txt");
        try {
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    String type = data.split("/")[0];
                    boolean isTaskDone = data.split("/")[1].equals("1");
                    String task = data.split("/")[2];
                    if (type.equals("T")) {
                        list.add(new Todo(task));
                    } else if (type.equals("D")) {
                        String by = data.split("/")[3];
                        list.add(new Deadline(task, by));
                    } else if (type.equals("E")){
                        String from = data.split("/")[3];
                        String to = data.split("/")[4];
                        list.add(new Event(task, from, to));
                    }
                    list.get(list.size() - 1).setMarkDone(isTaskDone);
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void taskSaver() {
        File directory = new java.io.File("data");
        directory.mkdir();
        try {
            Files.delete(Paths.get("data/PacMan.txt"));
            FileWriter writer = new FileWriter("data/PacMan.txt", true);
            for (int index = 1; index <= list.size(); index = index + 1) {
                writer.write(list.get(index - 1).toFile());
                if (index != list.size()) {
                    writer.write(System.lineSeparator());
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        taskParser();
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            String type = input.split(" ", 2)[0];
            if (type.equals("bye")) {
                break;
            } else if (type.equals("list")) {
                printList();
            } else if (type.equals("mark")) {
                try {
                    markTask(Integer.parseInt(input.split(" ")[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    echo("I'm sorry, but I can't find the index number :(");
                } catch (NumberFormatException e) {
                    echo("I'm sorry, but it's invalid index :(");
                }
            } else if (type.equals("unmark")) {
                try {
                    unmarkTask(Integer.parseInt(input.split(" ")[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    echo("I'm sorry, but I can't find the index number :(");
                } catch (NumberFormatException e) {
                    echo("I'm sorry, but it's invalid index :(");
                }
            } else if (type.equals("todo")) {
                String task = input.split(" ", 2)[1];
                addTodo(task);
            } else if (type.equals("deadline")) {
                String task = input.split(" ", 2)[1];
                addDeadline(task);
            } else if (type.equals("event")) {
                String task = input.split(" ", 2)[1];
                addEvent(task);
            } else if (type.equals("delete")) {
                try {
                    deleteTask(Integer.parseInt(input.split(" ")[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    echo("I'm sorry, but I can't find the index number :(");
                } catch (NumberFormatException e) {
                    echo("I'm sorry, but it's invalid index :(");
                }
            } else {
                echo("I'm sorry, but I can't understand what you ask :(");
            }
        }
        exit();
        taskSaver();
    }
}
