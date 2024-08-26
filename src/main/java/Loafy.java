import java.util.Scanner;
public class Loafy {
    public static void main(String[] args) {
        System.out.println("Loafy: Hellooo, I'm Loafy!\n" +
                "Loafy: What can I do for you? :D\n");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                System.out.println("Loafy: " + command + "\n");
            }
        }

        System.out.println("Loafy: Byeee see you soon! ;)");
    }
}