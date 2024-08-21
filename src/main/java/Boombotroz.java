import java.util.Scanner;

public class Boombotroz {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Boombotroz\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while (!echo.equals("bye")) {
            System.out.println(echo);
            echo =  scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
