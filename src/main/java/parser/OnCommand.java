
package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * OnCommand class created to help with listing tasks on relevant dates
 */
public class OnCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        try {
            LocalDate localDate = LocalDate.parse(input.substring(2).trim());
            return TaskList.activitiesOnThisDate(localDate, items, scanner);
        } catch (DateTimeParseException e) {
            System.out.println("The date follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            //return scanner.nextLine();
            return "The date follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!";
        }
    }
}