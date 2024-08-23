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
    private static void displayList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            list += i + ". " + listOfTasks.get(i - 1) + "\n";
        }
        System.out.println(displayForm(list));
    }
    private static void markTask(int number) {
        listOfTasks.get(number - 1).setDone();
        String done = "Nice! I've marked this task as done:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        System.out.println(displayForm(done));
    }
    private static void unmarkTask(int number) {
        listOfTasks.get(number - 1).setNotDone();
        String done = "OK, I've marked this task as not done yet:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        System.out.println(displayForm(done));
    }
    private static void delete(int number) { // Make tasks singular and plural in the future
        Task temp = listOfTasks.get(number - 1);
        listOfTasks.remove(number - 1);
        String done = "Noted. I've removed this task:\n" +
                "  " + temp
                + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        System.out.println(displayForm(done));
    }
    private static void displayLastAdded() {
        System.out.println(displayForm(
                "Got it. I've added this task:\n    "
                + listOfTasks.get(listOfTasks.size() - 1))
                + "Now you have " + listOfTasks.size() + " in the list.");
    }
    private static void addTask(String firstWord, String task) throws StobberiException {
        if (task.isEmpty()) {
            throw new EmptyStobberiException(displayForm("That's not a task?! Try again. "));
        }

        if (!firstWord.equals("todo") && !firstWord.equals("deadline") && !firstWord.equals("event")) {
            throw new NoSuchTaskStobberiException(displayForm("Huh! There is no such task?? "));
        }

        if (firstWord.equals("todo")) {
            listOfTasks.add(new ToDos(task));
            displayLastAdded();
        } else if (firstWord.equals("deadline")) {
            String[] parts = task.split("/by ");
            listOfTasks.add(new Deadlines(parts[0], parts[1]));
            displayLastAdded();
        } else if (firstWord.equals("event")) {
            String[] parts = task.split("/from ");
            String[] secondParts = parts[1].split("/to ");
            listOfTasks.add(new Events(parts[0], secondParts[0], secondParts[1]));
            displayLastAdded();
        }
    }

    private static void createList() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        while (!temp.equals("bye")) {
            if (temp.equals("list")) {
                displayList();
            } else {
                String[] parts = temp.split(" ");
                String firstWord = parts[0];
                String restOfTask = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
                if (parts.length == 2 && parts[1].matches("\\d+")) {
                    int number = Integer.parseInt(parts[1]);
                    if (firstWord.equals("mark")) {
                        markTask(number);
                        temp = scanner.nextLine();
                        continue;
                    } else if (firstWord.equals("unmark")) {
                        unmarkTask(number);
                        temp = scanner.nextLine();
                        continue;
                    } else if (firstWord.equals("delete")) {
                        delete(number);
                        temp = scanner.nextLine();
                        continue;
                    }
                }
                try {
                    addTask(firstWord, restOfTask);
                } catch (StobberiException e) {
                    System.out.println(e.getMessage());
                }
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
