import java.util.Scanner;
public class Loafy {
    public static void main(String[] args) {
        System.out.println("Loafy: Hellooo, I'm Loafy!\n" +
                "Loafy: What can I do for you? :D\n");
        Scanner input = new Scanner(System.in);
        TaskList tl = new TaskList();

        while (true) {
            System.out.print("You: ");
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(tl.view());
            } else {
                Task task = new Task(command);
                String msg = tl.add(task);
                System.out.println("Loafy: " + msg + "\n");
            }
        }

        System.out.println("Loafy: Byeee see you soon! ;)");
    }
}