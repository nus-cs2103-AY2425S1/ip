import java.util.Scanner;

public class EchoMind {
//    private String[]  =

    public void sendMessage(String msg) {
        System.out.println("[EchoMind] " + msg);
    }

    public static void main(String[] args) {
        EchoMind em = new EchoMind();
        em.sendMessage("Hello! I'm EchoMind!");
        em.sendMessage("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (true) {
            message = scanner.nextLine().trim();
            if (message.equalsIgnoreCase("bye")) {
                break;
            }
            em.sendMessage(message);
        }
        em.sendMessage("Bye. Hope to see you again soon!");
    }
}
