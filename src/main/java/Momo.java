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

            String taskType = input.split(" ")[0];

            switch (taskType) {
                case "todo" -> {
                    String task = input.split(" ",2)[1];
                    list[count] = new Todo(task);
                }
                case "deadline" -> {
                    String desc = input.split(" ",2)[1];
                    String task =  desc.split("/",2)[0];
                    String by = desc.split("/",2)[1];
                    list[count] = new Deadline(task, by);
                }
                case "event" -> {
                    String desc = input.split(" ",2)[1];
                    String task =  desc.split("/",2)[0];
                    String from = desc.split("/",3)[1];
                    String to = desc.split("/",3)[2];

                    list[count] = new Event(task, from, to);
                }
            }

            System.out.println(horizontalLine);
            System.out.println("Noted. I've added this task: \n " + list[count]);
            System.out.println(String.format("Now you have %d task(s) in the list", count + 1));
            System.out.println(horizontalLine);
            input = sc.nextLine();
            count++;
        }

        System.out.println(horizontalLine);
        System.out.println("Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest well.... wħɨłɇ ɏøᵾ sŧɨłł ȼȺn\n" + logo);
        System.out.println(horizontalLine);
    }
}
