import java.util.*;

public class Momo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String horizontalLine = "____________________________________________________________";
        String logo = "⣿⣿⣿⡉⢀⣾⣿⡟⣩⣭⣭⡈⠙⢿⣿⣿⣿⣿⣿⡿⣻⣿⣿⣿⣿⣿⣿⣿⡇⠄\n" +
                "⣿⣿⡗⠄⣼⣿⣿⢸⡿⠉⠉⢻⡆⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠄\n" +
                "⣿⡻⠁⢠⣿⣿⣿⣦⡛⠢⠴⠛⠁⣸⣿⣿⣿⣿⡿⠛⢉⣉⣉⡙⢻⣿⣿⣗⠄⠄\n" +
                "⠷⠁⠄⢰⣿⣿⣿⣷⣬⣭⣼⣷⣿⣿⣿⣿⣿⡏⢀⣾⠟⠛⢿⣿⣄⣿⣿⡏⠄⠄\n" +
                "⠄⠄⠄⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄⠳⢀⣀⡼⢟⣼⣿⡟⠄⠄⠄\n" +
                "⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣒⣲⣶⣾⣿⣿⠏⠄⠄⠄⢠\n" +
                "⠄⠄⠄⠸⣿⣽⣿⣿⣿⣿⣉⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⢠⣷\n" +
                "⠄⠄⠄⠄⢻⣷⢻⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⠄⠄⠄⢀⣾⣿\n" +
                "⠄⠄⠄⠄⠄⢻⣧⡙⢿⣿⣿⣿⣿⣿⡿⣿⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄⢠⣿⣿⣿\n" +
                "⠄⠄⠄⡀⠄⠈⣿⣿⣶⣭⣭⣭⣿⣾⡿⠟⠋⠁⠄⠄⠄⠄⠄⠄⠄⢠⣿⣿⣿⣿\n" +
                "⠄⠄⠎⠄⠄⣨⣿⣿⣿⣿⣿⣿⠋⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⣀⡲⣿⣿⣿⣿";

        System.out.println("Hello! I'm Momo\nWhat can I do for you?");
        String input = sc.nextLine();
        Task[] list = new Task[100];
        int count = 0;

        while (!Objects.equals(input, "bye")) {

            if (Objects.equals(input, "list")) {
                System.out.println(horizontalLine);
                for (int i = 0; list[i] != null; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                System.out.println(horizontalLine);
                input = sc.nextLine();
                continue;
            }

            if (input.startsWith("mark")) {
                System.out.println(horizontalLine);
                int index =  Integer.parseInt(input.split(" ")[1]) - 1;
                Task task = list[index];
                task.markComplete();
                System.out.println(horizontalLine);
                input = sc.nextLine();
                continue;
            }

            if (input.startsWith("unmark")) {
                System.out.println(horizontalLine);
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task task = list[index];
                task.unmark();
                System.out.println(horizontalLine);
                input = sc.nextLine();
                continue;
            }


            list[count] = new Task(input);
            System.out.println(horizontalLine);
            System.out.println("added: " + input);
            System.out.println(horizontalLine);
            input = sc.nextLine();
            count++;
        }

        System.out.println(horizontalLine);
        System.out.println("Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest well... while you still can.\n" + logo);
        System.out.println(horizontalLine);
    }
}
