import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userReply = "";
        System.out.println("-------------------------------------");
        System.out.println("\tHello: I'm Echo\n\tWhat can I do for you?");
        System.out.println("-------------------------------------");
        while (!userReply.equals("bye")) {
            userReply = input.nextLine();
            if (userReply.equals("bye")) {
                System.out.println("-------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("-------------------------------------");
            } else {
                System.out.println("-------------------------------------");
                System.out.println("\t" + userReply);
                System.out.println("-------------------------------------");
            }

        }

    }
}
