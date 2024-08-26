import java.util.Scanner;

public class Mylo {
    private static void separator() {
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) throws NoSuchCommandException {
        TaskList list = new TaskList();

        String name = "Mylo";
        String greet = "Hello! Thanks for using " + name + ".";
        String opening_query = "What can I help you?";
        String goodbye = "Goodbye. Have a nice day ahead!";

        System.out.println(greet);
        System.out.println(opening_query);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            String[] keys = input.split(" ");
            try {
                switch (keys[0]) {
                    case "list":
                        separator();
                        System.out.println(list);
                        separator();
                        break;
                    case "mark":
                        separator();
                        list.markTaskAsDone(Integer.parseInt(keys[1]));
                        separator();
                        break;
                    case "unmark":
                        separator();
                        list.markTaskAsUndone(Integer.parseInt(keys[1]));
                        separator();
                        break;
                    case "todo":
                        separator();
                        list.addTask(input.substring(4), TaskType.TODO);
                        separator();
                        break;
                    case "deadline":
                        separator();
                        list.addTask(input.substring(8), TaskType.DEADLINE);
                        separator();
                        break;
                    case "event":
                        separator();
                        list.addTask(input.substring(5), TaskType.EVENT);
                        separator();
                        break;
                    case "who are you":
                        separator();
                        System.out.println("I'm " + name);
                        separator();
                        break;
                    default:
                        throw new NoSuchCommandException(input);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                input = scanner.nextLine();
            }
        }

        separator();
        System.out.println(goodbye);
        separator();
        scanner.close();
    }
}
