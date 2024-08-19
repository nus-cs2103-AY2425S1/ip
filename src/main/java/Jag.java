import java.util.Scanner;

public class Jag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String greetings = """
                ------------------
                Hello! I'm Jag
                What can I do for you?
                ------------------""";
        String bye = """
                ------------------
                Bye. Hope to see you again soon!
                ------------------""";
        String[] list_items = new String[100];

        System.out.println(greetings);
        String answer = scanner.nextLine();



        System.out.println("------------------");
        System.out.println("added: " + answer);
        list_items[0] = answer;
        System.out.println("------------------");

        int i = 1;
        while (!answer.equals("bye") && !answer.equals("list")) {
            answer = scanner.nextLine();

            if (answer.equals("bye") || answer.equals("list")) {
                break;
            }

            list_items[i] = answer;
            i += 1;
            System.out.println("------------------");
            System.out.println("added: " + answer);
            System.out.println("------------------");
        }

        if (answer.equals("list")) {
            System.out.println("------------");
            for (int j = 0; j < i; j++) {
                int counter = j + 1;
                System.out.println(counter + ". " + list_items[j]);
            }
            System.out.println("------------");
            answer = scanner.nextLine();
        }

        if (answer.equals("bye")) {
            System.out.println(bye);
            scanner.close();
            return;
        }

        scanner.close();

    }
}