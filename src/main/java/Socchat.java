import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class Socchat {
    private static Scanner scanner = new Scanner(System.in);
    enum Command{
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE
    }
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        greet();

        while (true) {
            String[] strToken;
            try{
                String input = scanner.next();
                Command command = getCommand(input);
                System.out.print("> ");
                switch (command) {
                    case BYE:
                        exit();
                        break;
                    case LIST:
                        taskList.list();
                        break;
                    case MARK:
                        String taskIndexString = scanner.nextLine().trim();
                        taskList.setMark(taskIndexString,true);
                        break;
                    case UNMARK:
                        taskIndexString = scanner.nextLine().trim();
                        taskList.setMark(taskIndexString, false);
                        break;
                    case TODO:
                        strToken = stringTokenize("TODO");
                        taskList.addTodo(strToken);
                        break;
                    case DEADLINE:
                        strToken = stringTokenize("DEADLINE");
                        taskList.addDeadline(strToken);
                        break;
                    case EVENT:
                        strToken = stringTokenize("EVENT");
                        taskList.addEvent(strToken);
                        break;
                    case DELETE:
                        taskIndexString = scanner.nextLine().trim();
                        taskList.delete(taskIndexString);
                        break;
                }
            } catch (SocchatException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    public static Command getCommand(String input) throws SocchatException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SocchatException("Uh Ohh! Socchat does not understand this...");
        }

    }
    public static String[] stringTokenize(String command) {
        String str = command + scanner.nextLine();
        String[] strToken = str.split(" /");
        return strToken;
    }

    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }



}

