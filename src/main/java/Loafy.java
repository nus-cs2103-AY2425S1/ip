import java.util.Arrays;
import java.util.Scanner;

public class Loafy {
    public static void main(String[] args) {
        greeting();
        Scanner input = new Scanner(System.in);
        TaskList tl = new TaskList();

        while (true) {
            System.out.print("You: ");
            String command = input.nextLine();
            String[] arr = command.split(" ");

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                reply(tl.view());
            } else if (arr[0].equals("mark")
                    || arr[0].equals("unmark")) {
                // set isDone to true if "mark", false if "unmark"
                boolean isDone = arr[0].equals("mark");
                int taskId = Integer.parseInt(arr[1]);
                String msg = tl.markTask(isDone, taskId);
                reply(msg);
            } else if (arr[0].equals("todo")) {
                String name = joinRange(arr, 1, arr.length);
                Task task = new Todo(name);
                String msg = tl.add(task);
                reply(msg);
            } else if (arr[0].equals("deadline")) {
                int i = Arrays.asList(arr).indexOf("/by");
                String name = joinRange(arr, 1, i);
                String date = joinRange(arr, i + 1, arr.length);
                Task task = new Deadline(name, date);
                String msg = tl.add(task);
                reply(msg);
            } else if (arr[0].equals("event")) {
                int fromIndex = Arrays.asList(arr).indexOf("/from");
                int toIndex = Arrays.asList(arr).indexOf("/to");
                String name = joinRange(arr, 1, fromIndex);
                String from = joinRange(arr, fromIndex + 1, toIndex);
                String to = joinRange(arr, toIndex + 1, arr.length);
                Task task = new Event(name, from, to);
                String msg = tl.add(task);
                reply(msg);
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


}