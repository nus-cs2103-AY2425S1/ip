import java.util.Scanner;
public class LuToDo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print(Utility.greeting());
        while (true) {
            String message = sc.nextLine();
            if (message.equals("bye")) {
                System.out.print(Utility.bye());
                break;
            }
            System.out.print(Utility.line + message + "\n" + Utility.line);
        }


    }
}
