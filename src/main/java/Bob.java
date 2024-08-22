import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String name = "Bob";
        System.out.println(line);
        System.out.printf("Hello! I'm %s!\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);
        String[] list = new String[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        while (!word.equals("bye")) {
            System.out.println(line);
            if (word.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(list[i]);
                }
            } else {
                list[index] = String.valueOf(index + 1) + ". " + word;
                System.out.printf("added: %s\n", word);
                index++;
            }
            System.out.println(line);
            word = scanner.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
