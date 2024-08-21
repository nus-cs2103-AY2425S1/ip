import java.util.Scanner;

public class Sam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getHorizontalLine());
        System.out.println("Hello! I'm Sam\nWhat can I do for you?");
        System.out.println(getHorizontalLine());
        Items items = new Items();
        while (true) {
            String input = scanner.nextLine();
            System.out.println(getHorizontalLine());

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(getHorizontalLine());
                break;
            } else if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                System.out.println(items.toString());
                System.out.println(getHorizontalLine());
            }else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                items.markItemDone(Integer.parseInt(parts[1]));
            } else if (input.startsWith("unmark")){
                String[] parts = input.split(" ");
                items.markItemUndone(Integer.parseInt(parts[1]));
            } else {
                String[] parts = input.split(" ");
                String itemType = parts[0];
                if ("todo".equals(itemType)) {
                    items.addItem(new ToDo(input.substring(5)));
                } else if ("deadline".equals(itemType)) {
                    String[] Dparts = input.split(" /by ");
                    items.addItem(new Deadline(Dparts[0].substring(9), Dparts[1]));

                } else { //event (assuming no error in input for now)
                    String[] Eparts = input.split(" /from | /to ");
                    items.addItem(new Event(Eparts[0].substring(6), Eparts[1], Eparts[2]));
                }
                System.out.println("Got it. I've added this task:\n"+items.getLastAdded());
                System.out.println(String.format("Now you have %d tasks in the list", items.getSize()));
                System.out.println(getHorizontalLine());
            }
        }

        scanner.close();
    }

    private static String getHorizontalLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            line.append("-");
        }
        return line.toString();
    }
}