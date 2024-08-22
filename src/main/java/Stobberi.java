import java.util.ArrayList;
import java.util.Scanner;
public class Stobberi {
    private static String nameOfChatBot = "Stobberi";
    private static String helloGreeting =
            "Hello! I'm " + nameOfChatBot + ".\n"
            + "What can I do for you?";
    private static String goodByeGreeting = "Bye. Hope to see you again soon! :)\n";
    private static ArrayList<Task> listOfTasks= new ArrayList<Task>();

    private static String displayForm(String phrase) {
        return
                "___________________________________________\n"
                + phrase
                + "\n___________________________________________\n";
    }

    private static void createList() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        while (!temp.equals("bye")) {
            if (temp.equals("list")) {
                String list = "Here are the tasks in your list:\n";
                for (int i = 1; i < listOfTasks.size() + 1; i++) {
                    list += i + ". " + listOfTasks.get(i - 1) + "\n";
                }
                System.out.println(displayForm(list));
            } else {
                String[] parts = temp.split(" ");
                if (parts.length == 2 && parts[1].matches("\\d+")) {
                    int number = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        listOfTasks.get(number - 1).setDone();
                        String done = "Nice! I've marked this task as done:\n" +
                                "  ";
                        done += listOfTasks.get(number - 1).toString();
                        System.out.println(displayForm(done));
                        temp = scanner.nextLine();
                        continue;
                    } else if (parts[0].equals("unmark")) {
                        listOfTasks.get(number - 1).setNotDone();
                        String done = "OK, I've marked this task as not done yet:\n" +
                                "  ";
                        done += listOfTasks.get(number - 1).toString();
                        System.out.println(displayForm(done));
                        temp = scanner.nextLine();
                        continue;
                    }
                }

                listOfTasks.add(new Task(temp));
                System.out.println(displayForm("added: " + temp));
            }
            temp = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println(displayForm(helloGreeting));
        createList();
        System.out.println(displayForm(goodByeGreeting));
    }
}
