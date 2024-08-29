import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Loafy {
    public static void main(String[] args) {
        try {
            new File("./data").mkdirs();
            new File("./data/loafy.txt").createNewFile();
        } catch (IOException e) {
            System.out.println("Error: file cannot be created");
        }
        TaskListSaver tls = new TaskListSaver("./data/loafy.txt");
        TaskList tl = new TaskList(tls);
        tl.loadFromTxt(Paths.get("./data/loafy.txt"));

        greeting();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String command = input.nextLine();
            String[] arr = command.split(" ");

            if (arr.length == 0) {
                reply(LoafyException.emptyInput());
            } else if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                reply(tl.view());
            } else if (arr[0].equals("mark")
                    || arr[0].equals("unmark")
                    || arr[0].equals("delete")) {
                if (arr.length != 2) {
                    reply(LoafyException.invalidAction());
                } else {
                    try {
                        int taskId = Integer.parseInt(arr[1]);
                        String msg;
                        if (arr[0].equals("delete")) {
                            msg = tl.delete(taskId);
                        } else {
                            // set isDone to true if "mark", false if "unmark";
                            boolean isDone = arr[0].equals("mark");
                            msg = tl.markTask(isDone, taskId);
                        }
                        reply(msg);
                    } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                        reply(LoafyException.invalidAction());
                    }
                }
            } else if (arr[0].equals("todo")) {
                if (arr.length == 1) {
                    reply(LoafyException.emptyTodo());
                } else {
                    String name = joinRange(arr, 1, arr.length);
                    Task task = new Todo(name);
                    String msg = tl.add(task);
                    reply(msg);
                }
            } else if (arr[0].equals("deadline")) {
                int i = Arrays.asList(arr).indexOf("/by");
                if (i == -1) {
                    reply(LoafyException.noDeadline());
                } else {
                    String name = joinRange(arr, 1, i);
                    String date = joinRange(arr, i + 1, arr.length);
                    if (name.isEmpty() || date.isEmpty()) {
                        reply(LoafyException.noDeadline());
                    } else {
                        LocalDateTime dateTime = parseDate(date);
                        Task task = new Deadline(name, dateTime);
                        String msg = tl.add(task);
                        reply(msg);
                    }
                }
            } else if (arr[0].equals("event")) {
                int fromIndex = Arrays.asList(arr).indexOf("/from");
                int toIndex = Arrays.asList(arr).indexOf("/to");
                if (fromIndex == -1 || toIndex == -1) {
                    reply(LoafyException.noEventDates());
                } else {
                    String name = joinRange(arr, 1, fromIndex);
                    String from = joinRange(arr, fromIndex + 1, toIndex);
                    String to = joinRange(arr, toIndex + 1, arr.length);
                    if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        reply(LoafyException.noEventDates());
                    } else {
                        LocalDateTime fromDateTime = parseDate(from);
                        LocalDateTime toDateTime = parseDate(to);
                        Task task = new Event(name, fromDateTime, toDateTime);
                        String msg = tl.add(task);
                        reply(msg);
                    }
                }
            } else {
                reply(LoafyException.invalidCommand());
            }
        }

        exit();
    }

    static String joinRange(String[] arr, int start, int end) {
        String[] subArr = Arrays.copyOfRange(arr, start, end);
        return String.join(" ", subArr);
    }

    static void reply(String str) {
        System.out.println("Loafy: " + str + "\n");
    }

    static void greeting() {
        reply("Hellooo, I'm Loafy!");
        reply("What can I do for you? :D");
    }

    static void exit() {
        reply("Byeee see you soon! ;)");
    }

    static LocalDateTime parseDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(date, dtf);
    }
}