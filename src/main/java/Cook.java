import java.util.Scanner;

public class Cook {
    public static void main(String[] args) {
        // Solution below adapted from https://www.patorjk.com/software/taag/#p=author&v=0&f=Avatar&t=Cook
        String logo = """ 
                          ____  ____  ____  _  __
                         /   _\\/  _ \\/  _ \\/ |/ /
                         |  /  | / \\|| / \\||   /\s
                         |  \\__| \\_/|| \\_/||   \\\s
                         \\____/\\____/\\____/\\_|\\_\\
                                                \s

                         """;
        System.out.println(logo);
        start();

        while (true) {
            // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
            Scanner input = new Scanner(System.in);
            String echo = input.nextLine();
            if (echo.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(echo + "\n");
        }

        exit();
    }

    public static void start() {
        System.out.println("""
                Hello, I'm Cook!
                What can I do for you?
                """);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
