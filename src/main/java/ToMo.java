import java.util.Scanner;

public class ToMo {
    public static void main(String[] args) {
        System.out.println("What's up, it's ToMo here!");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("How can I help you?");
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                break;
            } else {
                System.out.println(cmd);
            }
        }
        sc.close()
        System.out.println("Bye, see you later!");
    }
}