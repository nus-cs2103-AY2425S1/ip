import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Base64;

public class Jackson {

    public static String name = "Jackson";
    private static String secret = "";
    private static final int EXPECTED_SIZE = 100;
    private static final TaskList taskList = new TaskList(EXPECTED_SIZE);

    public static void read_anthem() {
        StringBuilder output = new StringBuilder();
        String out;
        if (secret.isEmpty()) {
            File f = new File("src/main/java/secret_text.txt");
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    output.append(sc.nextLine()).append("\n");
                }
                out = output.toString().strip();
                byte[] decoded = Base64.getDecoder().decode(out);
                out = new String(decoded);
            } catch (FileNotFoundException e) {
                System.out.println("Oops! Secret file not found...");
                return;
            }
        } else {
            out = secret;
        }
        System.out.println(out);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Oi! I'm %s!\nWhat you want me do today ah?\n> ", name);
        String response = sc.nextLine().strip();
        Parser p;
        Task t;

        while (true) {
            try {
                if (response.startsWith("list")) {
                    taskList.show_list();
                } else if (response.startsWith("todo")) {
                    m = TODO.matcher(response);
                    if (!m.find()) throw new SyntaxException("todo");
                    t = new Todo(m.group(1));
                    taskList.add_list(t);
                } else if (response.startsWith("deadline")) {
                    m = DEADLINE.matcher(response);
                    if (!m.find()) throw new SyntaxException("deadline");
                    t = new Deadline(m.group(1), m.group(2));
                    taskList.add_list(t);
                } else if (response.startsWith("event")) {
                    m = EVENT.matcher(response);
                    if (!m.find()) throw new SyntaxException("event");
                    t = new Event(m.group(1), m.group(2), m.group(3));
                    taskList.add_list(t);
                } else if (response.startsWith("mark")) {
                    m = MARK.matcher(response);
                    if (!m.find()) throw new SyntaxException("mark");
                    taskList.mark(Integer.parseInt(m.group(1)) - 1);
                } else if (response.startsWith("unmark")) {
                    m = UNMARK.matcher(response);
                    if (!m.find()) throw new SyntaxException("unmark");
                    taskList.unmark(Integer.parseInt(m.group(1)) - 1);
                } else if (response.startsWith("delete")) {
                    m = DELETE.matcher(response);
                    if (!m.find()) throw new SyntaxException("delete");
                    taskList.delete_list(Integer.parseInt(m.group(1)) - 1);
                } else if (response.startsWith("bye")) {
                    m = BYE.matcher(response);
                    if (!m.find()) throw new SyntaxException("bye");
                    System.out.println("K k bye lah!");
                    return;
                } else if (SECRET.matcher(response).find()) {
                    read_anthem();
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
                    case "bye":
                        System.out.println("bye");
                        break;
                    default:
                        System.out.println("Unknown error...");
                        break;
                }
            } catch (OutOfListException e) {
                int size = taskList.getSize();
                System.out.printf("Alamak, you got %d items on the list only leh...\n", size);
                if (size == 0) {
                    System.out.println("You've got no items in the list! Add some stuff first!");
                } else if (size == 1) {
                    System.out.println("Enter 1 to mark/unmark/delete the task in the list!");
                } else {
                    System.out.printf("Enter a number between 1 and %d when marking/unmarking/deleting tasks!\n", size);
                }
            }
            System.out.print("> ");
            response = sc.nextLine().strip();
        }
    }
}
