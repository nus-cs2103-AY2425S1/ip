import java.util.ArrayList;
import java.util.Scanner;

public class Diomon {
    private static ArrayList<String> taskList = new ArrayList<String>();
    public static void greeting() {
        String greetingMessage = """
                           ________________________________________________________________
                           Hello! I'm Diomon
                           What do you need recorded?
                           ________________________________________________________________
                           """;
        System.out.println(greetingMessage);
    }
    public static void bye() {
        String byeMessage = """
                            ________________________________________________________________
                            Bye. Hope to see you again soon!
                            ________________________________________________________________
                            """;
        System.out.println(byeMessage);
    }
    public static void echo() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            taskList.add(input);
            System.out.println("""
                               ________________________________________________________________
                              """);

            for (int i = 0; i < taskList.size(); i++){
                System.out.printf("%d-> %s\n", i + 1,taskList.get(i));
            }
            System.out.println("""
                               ________________________________________________________________
                               """);
        }
    }

    public static void main(String[] args) {
        greeting();
        echo();
        bye();
    }
}
