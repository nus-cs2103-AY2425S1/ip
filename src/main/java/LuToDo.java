import java.util.Scanner;
public class LuToDo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Utility.greeting();
        while (true) {
            String message = sc.nextLine();
            System.out.print(Utility.LINE);
            if (message.equals("bye")) {
                Utility.bye();
                System.out.print(Utility.LINE);
                break;
            }
            Utility.handleMessage(message);
            System.out.print(Utility.LINE);
        }
    }
}
