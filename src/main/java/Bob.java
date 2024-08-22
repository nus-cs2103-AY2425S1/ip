import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String name = "Bob";
        System.out.println(line);
        System.out.printf("Hello! I'm %s!\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);
        Task[] list = new Task[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        while (!word.equals("bye")) {
            System.out.println(line);
            if (word.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.printf("%d.%s\n", i + 1, list[i]);
                }
            } else {
                String[] tmp = word.split(" ", 3);
                if (tmp[0].equals("mark")) {
                    int num = Integer.parseInt(tmp[1]) - 1;
                    System.out.println(list[num].mark());
                } else if (tmp[0].equals("unmark")) {
                    int num = Integer.parseInt(tmp[1]) - 1;
                    System.out.println(list[num].unmark());
                } else {
                    list[index] = new Task(word);
                    System.out.printf("added: %s\n", word);
                    index++;
                }
            }
            System.out.println(line);
            word = scanner.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
