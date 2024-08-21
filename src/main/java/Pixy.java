import java.util.List;
import java.util.Scanner;

public class Pixy {

    private String[] list = new String[100];
    private int numOfItems;
    private String inputTask() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        return sc.next();
    }
    private void addToList(String item) {
        list[numOfItems++] = item;
    }
    private void printList() {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println((i + 1) + ". " + list[i]);
        }
    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Pixy. \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Pixy pixy = new Pixy();
        while (true) {
            String item = pixy.inputTask();
            System.out.println("____________________________________________________________\n");
            if (item.equalsIgnoreCase("list")) {
                pixy.printList();
                System.out.println("____________________________________________________________\n");
                continue;
            }
            if (item.equalsIgnoreCase("Bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            }
            pixy.addToList(item);
            System.out.println("added: " +
                    item + "\n____________________________________________________________\n");
        }

    }
}
