import java.util.Scanner;
public class Echo {
    private static final String lineBreak = "-------------------------------------";
    public static void main(String[] args) {
        TaskList commands = new TaskList();
        Scanner input = new Scanner(System.in);
        Task userReply = new Task("");
        System.out.println(lineBreak + "\nHello: I'm Echo\nWhat can I do for you?\n" + lineBreak);
        while (!userReply.isTerminate()) {
            userReply = new Task(input.nextLine());
            userReply.checkTask(commands);
        }
    }
}
