package chatbot;

import task.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles parsing of text representation of tasks
 * from and to text files
 *
 * @author celeschai
 */
public class Parser {

    /**
     * Parses task list from text files to create
     * Task objects
     *
     * @param sc scanner that reads source file
     * @param taskList task list object used by chatbot
     */
    public static void parseFromTxtTaskList(Scanner sc, TaskList taskList) {
        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            // Regular expression to capture different parts of the string
            Pattern pattern = Pattern.compile("\\[(\\S)]\\[(.)] (\\S.*)");
            Matcher matcher = pattern.matcher(next);

            if (matcher.matches()) {
                String taskType = matcher.group(1);
                boolean taskMarkedDone = matcher.group(2).equals("X");
                String taskDetails = matcher.group(3);

                if (taskType.equals("T")) {
                    Todo todo = new Todo(taskDetails);
                    if (taskMarkedDone) {
                        todo.markAsDone();
                    }
                    taskList.addTask(todo);
                    continue;
                } else if (taskType.equals("D")) {
                    Pattern patternD = Pattern.compile("(\\S.*) \\(by: (\\S.*)\\)");
                    Matcher matcherD = patternD.matcher(taskDetails);

                    if (matcherD.matches()) {
                        Deadline deadline = new Deadline(matcherD.group(1), matcherD.group(2));
                        if (taskMarkedDone) {
                            deadline.markAsDone();
                        }
                        taskList.addTask(deadline);
                        continue;
                    }
                } else if (taskType.equals("E")) {
                    Pattern patternE = Pattern.compile("(\\S.*) \\(from: (\\S.*) to: (\\S.*)\\)");
                    Matcher matcherE = patternE.matcher(taskDetails);

                    if (matcherE.matches()) {
                        Event event = new Event(matcherE.group(1), matcherE.group(2), matcherE.group(3));
                        if (taskMarkedDone) {
                            event.markAsDone();
                        }
                        taskList.addTask(event);
                        continue;
                    }
                }
            }
            System.out.println("this line is in an invalid format");
        }
        sc.close();
    }
}
