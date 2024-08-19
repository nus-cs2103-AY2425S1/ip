import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.ArrayList;

public class Jackson {

    public static String name = "Jackson";

    private static final Pattern TODO = Pattern.compile("^todo (.+)$");
    private static final Pattern DEADLINE = Pattern.compile("^deadline (.+) /by (.+)$");
    private static final Pattern EVENT = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
    private static final Pattern MARK = Pattern.compile("^mark ([0-9]+)$");
    private static final Pattern UNMARK = Pattern.compile("^unmark ([0-9]+)$");
    private static final Pattern LIST = Pattern.compile("^list$");
    private static final Pattern DELETE = Pattern.compile("^delete ([0-9]+)$");

    private static final int EXPECTED_SIZE = 100;
    private static ArrayList<Task> tasks = new ArrayList<>(EXPECTED_SIZE);

    public static void add_list(Task task) {
        System.out.println("Ya la, adding this task to your list!");
        tasks.add(task);
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", tasks.size());
    }

    public static void delete_list(int index) throws OutOfListException, InvalidIndexException {
        if (index < 0 || index >= tasks.size()) throw new OutOfListException();
        Task task = tasks.remove(index);
        System.out.println("Deleting now hor!");
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", tasks.size());
    }

    public static void show_list() {
        if (tasks.isEmpty()) {
            System.out.println("Nothing in list lah!");
        } else {
            System.out.println("Here's your list lor!");
            Task curr;
            for (int i = 0; i < tasks.size(); i++) {
                curr = tasks.get(i);
                System.out.printf("%d. %s\n", i + 1, curr);
            }
        }
    }

    public static void mark(int index) throws OutOfListException {
        if (index <= 0 || index > tasks.size()) throw new OutOfListException();
        Task curr = tasks.get(index);
        System.out.println("Solid lah, marked already");
        curr.mark();
        System.out.printf("\t%s\n", curr);
    }

    public static void unmark(int index) throws OutOfListException {
        if (index < 0 || index >= tasks.size()) throw new OutOfListException();
        Task curr = tasks.get(index);
        System.out.println("Walao, ok la I unmark already...");
        curr.unmark();
        System.out.printf("\t%s\n", curr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Oi! I'm %s!\nWhat you want me do today ah?\n> ", name);
        String response = sc.nextLine().strip();
        Matcher m;
        Task t;

        while (!response.equals("bye")) {
            try {
                if (response.startsWith("list")) {
                    // match regex
                    m = LIST.matcher(response);
                    if (!m.find()) throw new SyntaxException("list");
                    show_list();
                } else if (response.startsWith("todo")) {
                    m = TODO.matcher(response);
                    if (!m.find()) throw new SyntaxException("todo");
                    t = new Todo(m.group(1));
                    add_list(t);
                } else if (response.startsWith("deadline")) {
                    m = DEADLINE.matcher(response);
                    if (!m.find()) throw new SyntaxException("deadline");
                    t = new Deadline(m.group(1), m.group(2));
                    add_list(t);
                } else if (response.startsWith("event")) {
                    m = EVENT.matcher(response);
                    if (!m.find()) throw new SyntaxException("event");
                    t = new Event(m.group(1), m.group(2), m.group(3));
                    add_list(t);
                } else if (response.startsWith("mark")) {
                    m = MARK.matcher(response);
                    if (!m.find()) throw new SyntaxException("mark");
                    mark(Integer.parseInt(m.group(1)) - 1);
                } else if (response.startsWith("unmark")) {
                    m = UNMARK.matcher(response);
                    if (!m.find()) throw new SyntaxException("unmark");
                    unmark(Integer.parseInt(m.group(1)) - 1);
                } else if (response.startsWith("delete")) {
                    m = DELETE.matcher(response);
                    if (!m.find()) throw new SyntaxException("delete");
                    delete_list(Integer.parseInt(m.group(1)) - 1);
                } else {
                    throw new UnsupportedException();
                }
            } catch (UnsupportedException e) {
                System.out.println("Harh? What you talking about?");
                System.out.println("Try entering a recognized command:");
                System.out.println("1. todo [task-name]\n2. deadline [task-name] /by [due-date]\n3. event [task-name]" +
                        " /from [start-date] /to [end-date]\n4. mark [index]\n5. unmark [index]\n6. list\n7. bye");
            } catch (SyntaxException e) {
                System.out.println("Wrong format leh...");
                System.out.println("Try formatting your command as such:");
                switch (e.getMessage()) {
                    case "todo":
                        System.out.println("todo [task-name]");
                        break;
                    case "deadline":
                        System.out.println("deadline [task-name] /by [due-date]");
                        break;
                    case "event":
                        System.out.println("event [task-name] /from [start-date] /to [end-date]");
                        break;
                    case "list":
                        System.out.println("list");
                        break;
                    case "mark":
                        System.out.println("mark [index]");
                        break;
                    case "unmark":
                        System.out.println("unmark [index]");
                        break;
                    case "delete":
                        System.out.println("delete [index]");
                }
            } catch (OutOfListException e) {
                System.out.printf("Alamak, you got %d items on the list only leh...\n", tasks.size());
                if (tasks.isEmpty()) {
                    System.out.println("You've got no items in the list! Add some stuff first!");
                } else if (tasks.size() == 1) {
                    System.out.println("Enter 1 to mark/unmark/delete it!");
                } else {
                    System.out.printf("Enter a number between 1 and %d when marking/unmarking/deleting tasks!\n", tasks.size());
                }
            } catch (InvalidIndexException e) {
                System.out.println("Alamak, list number start from 1 you still put less than 1?");
                System.out.println("Try inputting a valid index above 1");
            }
            System.out.print("> ");
            response = sc.nextLine().strip();
        }

        System.out.println("K k, bye lah!");
    }
}
