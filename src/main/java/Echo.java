import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        TaskList commands = new TaskList();
        Scanner input = new Scanner(System.in);
        Task userReply = new Task("");
        System.out.println("-------------------------------------");
        System.out.println("Hello: I'm Echo\nWhat can I do for you?");
        System.out.println("-------------------------------------");
        while (!userReply.isTerminate()) {
            userReply = new Task(input.nextLine());
            userReply.checkTask(commands);
        }
    }
}
