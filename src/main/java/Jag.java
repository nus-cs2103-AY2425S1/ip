import java.util.Scanner;

public class Jag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = """
                ------------------
                Hello! I'm Jag
                What can I do for you?
                ------------------""";

        String bye = """
                ------------------
                Bye. Hope to see you again soon!
                ------------------""";

        System.out.println(logo);
        String answer = scanner.nextLine();

        System.out.println("------------------");
        System.out.println(answer);
        System.out.println("------------------");

        while (!answer.equals("bye")) {
            answer = scanner.nextLine();
            System.out.println("------------------");
            System.out.println(answer);
            System.out.println("------------------");
        }

        System.out.println(bye);
        scanner.close();

    }
}