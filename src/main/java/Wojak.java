import java.io.File;
import java.util.Scanner;
import controllers.InputParser;
import controllers.commands.Command;
import controllers.errors.InvalidCommandError;
import controllers.errors.InvalidInputError;
import lib.DbDriverInterface;
import lib.FileDbDriver;
import models.*;

public class Wojak {


    public static void main(String[] args) {

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Wojak\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
        Scanner sc = new Scanner(System.in);

        InputParser parser = new InputParser();
        DbDriverInterface dbDriver = new FileDbDriver();
        TaskList taskList = new TaskList(dbDriver);

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            try {
                Command command = parser.parse(nextLine);
                command.execute(taskList);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}