import java.util.Scanner;

public class Killua {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Killua";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    private static void echo(String message){
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    private static void bye(){
        System.out.println(LINE);
        System.out.println(FAREWELL);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println(GREETING);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while (flag) {
            String command = scanner.nextLine().trim();

            switch(command.toLowerCase()) {
                case "bye" -> {
                    bye();
                    flag = false;
                }
                default -> echo(command);
            }
        }

        scanner.close();
    }
}
