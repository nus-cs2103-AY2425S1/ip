import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stobberi {
    private static final String NAME_OF_CHATBOT = "Stobberi";
    private static final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "What can I do for you?";
    private static final String GOODBYE_GREETING = "Bye. Hope to see you again soon! :)\n";

    private ArrayList<Task> listOfTasks;

    public Stobberi() {
        listOfTasks = new ArrayList<>();
    }

    private String displayForm(String phrase) {
        return
                "_______________________________________________\n"
                        + phrase
                        + "\n_______________________________________________\n";
    }

    private void displayList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            list += i + ". " + listOfTasks.get(i - 1) + "\n";
        }
        System.out.println(displayForm(list));
    }

    private void markTask(int number) {
        listOfTasks.get(number - 1).setDone();
        String done = "Nice! I've marked this task as done:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        System.out.println(displayForm(done));
    }

    private void unmarkTask(int number) {
        listOfTasks.get(number - 1).setNotDone();
        String done = "OK, I've marked this task as not done yet:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        System.out.println(displayForm(done));
    }

    private void delete(int number) {
        Task temp = listOfTasks.get(number - 1);
        listOfTasks.remove(number - 1);
        String done = "Noted. I've removed this task:\n" +
                "  " + temp
                + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        System.out.println(displayForm(done));
    }

    private void displayLastAdded() {
        System.out.println(displayForm(
                "Got it. I've added this task:\n    "
                        + listOfTasks.get(listOfTasks.size() - 1))
                + "Now you have " + listOfTasks.size() + " in the list.");
    }

    private void addTask(String firstWord, String task) throws StobberiException {
        if (task.isEmpty()) {
            throw new EmptyStobberiException(displayForm("That's not a task?! Try again. "));
        }

        if (!firstWord.equals("todo") && !firstWord.equals("deadline") && !firstWord.equals("event")) {
            throw new NoSuchTaskStobberiException(displayForm("Huh! There is no such task?? "));
        }

        if (firstWord.equals("todo")) {
            listOfTasks.add(new ToDos(task));
        } else if (firstWord.equals("deadline")) {
            String[] parts = task.split(" /by ");
            listOfTasks.add(new Deadlines(parts[0], parts[1]));
        } else if (firstWord.equals("event")) {
            String[] parts = task.split(" /from ");
            String[] secondParts = parts[1].split(" /to ");
            listOfTasks.add(new Events(parts[0], secondParts[0], secondParts[1]));
        }
        displayLastAdded();
    }

    private void createList() {
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
                } catch (DateTimeParseException e) {
                    System.out.println("Date and Time needs to be in the format dd-MM-yyyy HHmm'hrs'\n Example: 27-12-2004 1700hrs\n" + e.getMessage());
                }
            }
            temp = scanner.nextLine();
        }
    }

    public void run() {
        ListSaver listSaver = new ListSaver("data/list.txt");
        listOfTasks = listSaver.getList();
        System.out.println(displayForm(HELLO_GREETING));
        createList();
        System.out.println(displayForm(GOODBYE_GREETING));
        listSaver.saveList(listOfTasks);
    }

    public static void main(String[] args) {
        Stobberi stobberi = new Stobberi();
        stobberi.run();
    }
}
