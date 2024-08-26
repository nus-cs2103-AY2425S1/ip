import java.util.Scanner;
import java.util.function.Consumer;

public class Loafy {
    public static void main(String[] args) {

        Consumer<String> reply = msg -> System.out.println("Loafy: " + msg + "\n");
        reply.accept("Hellooo, I'm Loafy!");
        reply.accept("What can I do for you? :D");

        Scanner input = new Scanner(System.in);
        TaskList tl = new TaskList();

        while (true) {
            System.out.print("You: ");
            String command = input.nextLine();
            String[] arr = command.split(" ");

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                reply.accept(tl.view());
            } else if (arr[0].equals("mark")
                    || arr[0].equals("unmark")) {
                // set isDone to true if "mark", false if "unmark"
                boolean isDone = arr[0].equals("mark");
                int taskId = Integer.parseInt(arr[1]);
                String msg = tl.markTask(isDone, taskId);
                reply.accept(msg);
            } else {
                Task task = new Task(command);
                String msg = tl.add(task);
                reply.accept(msg);
            }
        }

        reply.accept("Byeee see you soon! ;)");
    }
}