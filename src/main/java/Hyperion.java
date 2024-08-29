import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hyperion {
    public static void main(String[] args) {
        System.out.println("Welcome to Hyperion!");
        List<Task> allTasks = FileUtil.loadTasks();
        try {
            String initialValues = new ListAll("", allTasks).message();
            System.out.println(initialValues);
        } catch (CommandFoundButInvalidException e ) {
            System.out.print("");
        }

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean carryOn = true;

        while(carryOn) {
            System.out.println("> ");
            input = scanner.nextLine();

            try {
                Parser parser = new Parser(input, allTasks);
                carryOn = parser.carryOn();

                FileUtil.saveTasks(allTasks);
            } catch (EmptyStringException |
                     CommandFoundButInvalidException |
                     CommandNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
