import java.util.Scanner;
import java.util.ArrayList;
public class Echo {
    public static void main(String[] args) {
        TaskList commands = new TaskList();
        Scanner input = new Scanner(System.in);
        Task userReply = new Task("");
        System.out.println("-------------------------------------");
        System.out.println("\tHello: I'm Echo\n\tWhat can I do for you?");
        System.out.println("-------------------------------------");
        while (!userReply.isTerminate()) {
            userReply = new Task(input.nextLine());
            userReply.checkTask(commands);
        }
    }
}
