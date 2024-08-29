import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.lang.*;

public class TheBotFather {

    public static void main(String[] args) {

        TaskList taskList;
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("./data/TheBotFather.txt");

        try {
            taskList = storage.loadFromFile();
        } catch (TheBotFatherException e) {
            Ui.print(e.getMessage());
            return;
        }

        Ui.printGreeting();

        while (true) {
            String input = sc.nextLine();
            Ui.printLine();
            if (Objects.equals(input, "bye")) break;
            StringTokenizer tokens = new StringTokenizer(input);
            String command = tokens.nextToken();
            Task task;
            int index;

            switch (command) {
            case "list":
                try {
                    Ui.print("You never even called me Don once.");
                    taskList.printList();
                } catch (TheBotFatherException e) {
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;


            case "mark":
                try {
                    index = Integer.parseInt(String.valueOf(tokens.nextToken())) - 1;
                    taskList.markAsDone(index);
                    Ui.print("It will be done");
                    taskList.printTask(index);
                } catch (NoSuchElementException e) {
                    Ui.print("Skill issue: Atleast enter a number");
                } catch (NumberFormatException e) {
                    Ui.print("How do you not know what a number is, jeez");
                } catch (TheBotFatherException e) {
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;

            case "unmark":
                try {
                    index = Integer.parseInt(String.valueOf(tokens.nextToken())) - 1;
                    taskList.unmark(index);
                } catch (NoSuchElementException e) {
                    Ui.print("Skill issue: Atleast enter a number");
                } catch (NumberFormatException e) {
                    Ui.print("How do you not know what a number is, jeez");
                } catch (TheBotFatherException e) {
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;


            case "delete":
                try {
                    index = Integer.parseInt(String.valueOf(tokens.nextToken())) - 1;
                    taskList.delete(index);
                    taskList.printCount();
                } catch (NoSuchElementException e) {
                    Ui.print("Skill issue: Atleast enter a number");
                } catch (NumberFormatException e) {
                    Ui.print("How do you not know what a number is, jeez");
                } catch (TheBotFatherException e) {
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;

            case "todo":
                try {
                    task = Todo.makeTodo(tokens);
                    taskList.addTask(task);
                    Ui.print("Leave the gun, take the cannoli.");
                    Ui.print("    " + task);
                    taskList.printCount();
                } catch (TheBotFatherException e) {
                    Ui.print("Why do you want to do a todo if there is no todo to do!");
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;

            case "deadline":
                try {
                    task = Deadline.makeDeadline(tokens);
                    taskList.addTask(task);
                    Ui.print("Look how they massacred my boy.");
                    Ui.print("    " + task);
                    taskList.printCount();
                } catch (TheBotFatherException e) {
                    Ui.print("Son, if there is no Deadline.. GO HOME");
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;

            case "event":
                try {
                    task = Event.makeEvent(tokens);
                    taskList.addTask(task);
                    Ui.print("That's my family, Kay, that's not me.");
                    Ui.print("    " + task);
                    taskList.printCount();
                } catch (TheBotFatherException e) {
                    Ui.print("Kiddo, go sleep, this is not a valid event!");
                    Ui.print(e.getMessage());
                } finally {
                    Ui.printLine();
                }
                break;

            default:
                Ui.print("OOPS!!! I'm sorry, but I don't know what that means :-(. Use \"bye\" if you want to exit the program");
                Ui.printLine();
                break;
            }
        }

        Ui.printGoodBye();
        try {
            storage.toFile(taskList);
        } catch (TheBotFatherException e) {
            Ui.print(e.getMessage());
        }
        sc.close();
        Ui.printLine();

    }


}
