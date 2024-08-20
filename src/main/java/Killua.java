import java.util.ArrayList;
import java.util.Scanner;

public class Killua {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Killua";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    private static void add(String message){
        System.out.println(LINE);
        System.out.println("added: " + message);
        System.out.println(LINE);
    }

    private static void bye(){
        System.out.println(LINE);
        System.out.println(FAREWELL);
        System.out.println(LINE);
    }

    private static void list(ArrayList<String> memo){
        System.out.println(LINE);
        for (int i = 0; i < memo.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, memo.get(i));
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println(GREETING);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> memo = new ArrayList<>();

        while (flag) {
            String command = scanner.nextLine().trim();

            switch(command.toLowerCase()) {
                case "bye" -> {
                    bye();
                    flag = false;
                }
                case "list" -> list(memo);
                default -> {
                    memo.add(command);
                    add(command);
                }
            }
        }

        scanner.close();
    }
}
