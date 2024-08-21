import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Boombotroz {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Boombotroz\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        int count = 1;
        List<String> ls = new ArrayList<String>();
        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                for (int i = 1; i < count; i++) {
                    System.out.println(String.format("%d. %s", i, ls.get(i-1)));
                }
                echo =  scanner.nextLine();
            } else {
                ls.add(echo);
                count++;
                System.out.println(String.format("added: %s", echo));
                echo =  scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
