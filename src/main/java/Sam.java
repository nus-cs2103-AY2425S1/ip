import java.util.Scanner;

public class Sam {
    public static void main(String[] args) throws SamException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getHorizontalLine());
        System.out.println("Hello! I'm Sam\nWhat can I do for you?");
        System.out.println(getHorizontalLine());
        Items items = new Items();
        while (true) {
            String input = scanner.nextLine();
            System.out.println(getHorizontalLine());
            try{
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(getHorizontalLine());
                    break;
                } else if (input.equals("list")){
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(items.toString());
                    System.out.println(getHorizontalLine());
                }else if (input.startsWith("mark")) {
                    items.markItemDone(input);
                } else if (input.startsWith("unmark")){
                    items.markItemUndone(input);
                } else {
                    String[] parts = input.split(" ");
                    String itemType = parts[0];
                    if ("todo".equals(itemType)) {
                        if (parts.length==1 || "".equals(input.substring(5))) {
                            throw new SamException("Please include name of ToDo task");
                        }
                        items.addItem(new ToDo(input.substring(5)));
                    } else if ("deadline".equals(itemType)) {
                        if (parts.length==1 || "".equals(input.substring(9))) {
                            throw new SamException("Please include name of Deadline task");
                        }
                        String[] Dparts = input.split(" /by ");
                        items.addItem(new Deadline(Dparts[0].substring(9), Dparts[1]));

                    } else if ("event".equals(itemType)){
                        if (parts.length==1 || "".equals(input.substring(6))) {
                            throw new SamException("Please include name of Event task");
                        }
                        String[] Eparts = input.split(" /from | /to ");
                        items.addItem(new Event(Eparts[0].substring(6), Eparts[1], Eparts[2]));
                    } else {
                        throw new SamException();
                    }
                    System.out.println("Got it. I've added this task:\n"+items.getLastAdded());
                    System.out.println(String.format("Now you have %d tasks in the list", items.getSize()));
                    System.out.println(getHorizontalLine());
                }
            } catch (SamException e) {
                System.out.println(e.getMessage());
            } catch (StringIndexOutOfBoundsException e) {

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