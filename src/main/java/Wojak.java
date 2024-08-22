import java.util.Scanner;
import controllers.InputParser;
import controllers.commands.Command;
import models.*;

public class Wojak {


    public static void main(String[] args) {

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Wojak\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);

        InputParser parser = new InputParser();
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            Command command = parser.parse(nextLine);
            command.execute(taskList);
        }



    }
}