import java.util.*;
import java.lang.*;

public class TheBotFather {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLine();
        printGreeting();
        printHorse();
        printLine();

        while (true) {
            String input = sc.nextLine();
            printLine();
            if (Objects.equals(input, "bye")) break;
            StringTokenizer tokens = new StringTokenizer(input);
            String command = tokens.nextToken();
            Task task;
            int index;

            switch (command) {
                case "list":
                    try {
                        print("You never even called me Don once.");
                        printList(taskList);
                    } catch (TheBotFatherException e) {
                        print(e.getMessage());
                    } finally {
                        printLine();
                    }
                    break;


                case "mark":
                    try {
                        index = Integer.parseInt(String.valueOf(tokens.nextToken())) - 1;
                        task = taskList.get(index);
                        task.markAsDone();
                        print("It will be done");
                        print("    " + task.toString());
                    } catch (NoSuchElementException e) {
                        print("Skill issue: Atleast enter a number");
                    } catch (NumberFormatException e) {
                        print("How do you not know what a number is, jeez");
                    } catch (IndexOutOfBoundsException e) {
                        print("To be a real man you need to know how to count, you don't even have those many tasks son");
                    } finally {
                        printLine();
                    }
                    break;

                case "unmark":
                    try {
                        index = Integer.parseInt(String.valueOf(tokens.nextToken())) - 1;
                        task = taskList.get(index);
                        task.unmark();
                        print("A man who doesn't spend time with his family can never be a real man.");
                        print("    " + task.toString());
                    } catch (NoSuchElementException e) {
                        print("Skill issue: Atleast enter a number");
                    } catch (NumberFormatException e) {
                        print("How do you not know what a number is, jeez");
                    } catch (IndexOutOfBoundsException e) {
                        print("To be a real man you need to know how to count, you don't even have those many tasks son");
                    } finally {
                        printLine();
                    }
                    break;


                case "todo":
                    try {
                        task = makeTodo(tokens);
                        taskList.add(task);
                        print("Leave the gun, take the cannoli.");
                        print("    " + task.toString());
                        print(Task.getCOUNT());
                    } catch (NoSuchElementException e) {
                        print("Why do you want to do a todo if there is no todo to do!");
                    } catch (TheBotFatherException e) {
                        print(e.getMessage());
                    } finally {
                        printLine();
                    }
                    break;

                case "deadline":
                    try {
                        task = makeDeadline(tokens);
                        taskList.add(task);
                        print("Look how they massacred my boy.");
                        print("    " + task.toString());
                        print(Task.getCOUNT());
                    } catch (NoSuchElementException e) {
                        print("Son, if there is no Deadline.. GO HOME");
                        print("If you have a deadline type deadline description /by date");
                    } catch (TheBotFatherException e) {
                        print(e.getMessage());
                    } finally {
                        printLine();
                    }
                    break;

                case "event":
                    try {
                        task = makeEvent(tokens);
                        taskList.add(task);
                        print("That's my family, Kay, that's not me.");
                        print("    " + task.toString());
                        print(Task.getCOUNT());
                    } catch (NoSuchElementException e) {
                        print("Kiddo, go sleep, this is not a valid event!");
                        print("If you have an event type event description /from start /to end");
                    } catch (TheBotFatherException e) {
                        print(e.getMessage());
                    } finally {
                        printLine();
                    }
                    break;


                default:
                    print("OOPS!!! I'm sorry, but I don't know what that means :-(. Use \"bye\" if you want to exit the program");
                    printLine();
                    break;
            }
        }

        printGoodBye();
        sc.close();
        printLine();

    }

    private static Todo makeTodo(StringTokenizer tokens) throws TheBotFatherException{
        StringBuilder description = new StringBuilder();
        String token = tokens.nextToken();
        description.append(token).append(" ");
        while (tokens.hasMoreTokens()) {
            token = tokens.nextToken();
            description.append(token).append(" ");
        }
        return new Todo(description.toString());
    }

    private static Deadline makeDeadline(StringTokenizer tokens) throws TheBotFatherException{
        StringBuilder description = new StringBuilder();
        StringBuilder by = new StringBuilder();

        String token = tokens.nextToken();
        while (!token.equals("/by")) {
            description.append(token).append(" ");
            token = tokens.nextToken();
        }

        token = tokens.nextToken();
        by.append(token).append(" ");
        while (tokens.hasMoreTokens()) {
            token = tokens.nextToken();
            by.append(token).append(" ");
        }

        return new Deadline(description.toString(), by.toString());
    }

    private static Event makeEvent(StringTokenizer tokens) throws TheBotFatherException{
        StringBuilder description = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        String token = tokens.nextToken();

        while (!token.equals("/from")) {
            description.append(token).append(" ");
            token = tokens.nextToken();
        }

        token = tokens.nextToken();
        while (!token.equals("/to")) {
            from.append(token).append(" ");
            token = tokens.nextToken();
        }

        token = tokens.nextToken();
        to.append(token).append(" ");
        while (tokens.hasMoreTokens()) {
            token = tokens.nextToken();
            to.append(token).append(" ");
        }

        return new Event(description.toString(), from.toString(), to.toString());
    }


    public static void printList(ArrayList<Task> taskList) throws TheBotFatherException{
       int size = taskList.size();
       if (size < 1) throw new TheBotFatherException("How do I print what is not there?");
       for (int i = 0; i < size; i++) {
            print(i + 1 + ". " + taskList.get(i));
       }
    }

    public static void print(String str) {
        System.out.println("    " + str);
    }

    public static void printGreeting() {
        System.out.println("    Hello! I'm The BotFather");
        System.out.println("    I’m gonna make you an offer you can’t refuse.");
    }

    public static void printGoodBye() {
        System.out.println("    What are you worried about, if I wanted to kill you, you'd be dead already.");
    }

    public static void printLine() {
        System.out.println("    —————————————————————————————————————————————————————————————————");
    }

    public static void printHorse() {
        System.out.println("\t                                 |\\    /|");
        System.out.println("\t                              ___| \\,,/_/");
        System.out.println("\t                           ---__/ \\/    \\");
        System.out.println("\t                          __--/     (D)  \\");
        System.out.println("\t                          _ -/    (_      \\");
        System.out.println("\t                         // /       \\_ /  -\\");
        System.out.println("\t   __-------_____--___--/           / \\_ O o)");
        System.out.println("\t  /                                 /   \\__/");
        System.out.println("\t /                                 /");
        System.out.println("\t||          )                   \\_/\\");
        System.out.println("\t||         /              _      /  |");
        System.out.println("\t| |      /--______      ___\\    /\\  :");
        System.out.println("\t| /   __-  - _/   ------    |  |   \\ \\");
        System.out.println("\t |   -  -   /                | |     \\ )");
        System.out.println("\t |  |   -  |                 | )     | |");
        System.out.println("\t  | |    | |                 | |    | |");
        System.out.println("\t  | |    < |                 | |   |_/");
        System.out.println("\t  < |    /__\\                <  \\");
        System.out.println("\t  /__\\                       /___\\");
    }
}
