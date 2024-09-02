import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class Trackie {
    private TaskList tasklist;

    public Trackie() {
        tasklist = new TaskList();
    }

    public void greet() {
//        System.out.println(
//                "Hello, I'm Trackie. Nice to meet you =)\n"
//                        + "Instructions:\n"
//                        + "To add a todo task: todo [description]\n"
//                        + "To add a deadline task: deadline [description] /by [deadline]\n"
//                        + "To add an event task: event [description /from [start timing] /to [end timing]\n"
//
//        );
//        System.out.println("Type \"list\" to see the list of tasks");
//        System.out.println("Type \"mark\" or \"unmark\" followed by the task number to mark said task as done or undone.");
//        System.out.println("Type \"mark\" or \"unmark\" followed by the task number to mark said task as done or undone.");
//        System.out.println("Type \"bye\" to exit.");
    }

    public void saveAndExit(File f) {
        //save the details to the data file
        tasklist.updateData(f);
        //print goodbye text
        System.out.println("Seeya!");
    }

    public static void main(String[] args) {

        Trackie bot = new Trackie();
        File file = new File("./src/data.txt");
        bot.tasklist.loadTasks(file);

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating a file");
        }

        bot.greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Type something: ");
            String userInput = sc.nextLine();
            String[] arguments = userInput.split(" ");

            if (userInput.equals("bye")) {
                bot.saveAndExit(file);
                break;
            }

            if (arguments[0].equals("list")) {
                if (bot.tasklist.isEmpty()) {
                    System.out.println("You currently have no tasks added.");
                } else {
                    bot.tasklist.listTasks();
                }
            } else if (arguments[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(arguments[1]);
                    bot.tasklist.markTask(index);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e2) {
                    System.out.println("Invalid index.");
                }
            } else if (arguments[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(arguments[1]);
                    bot.tasklist.unmarkTask(index);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e2) {
                    System.out.println("Invalid index.");
                }
            } else if (arguments[0].equals("todo")) {
                try {
                    bot.tasklist.addTodoTask(arguments);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (arguments[0].equals("deadline")) {
                try {
                    bot.tasklist.addDeadlineTask(arguments);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (arguments[0].equals("event")) {
                try {
                    bot.tasklist.addEventTask(arguments);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (arguments[0].equals("delete")) {
                try {
                    int index = Integer.parseInt(arguments[1]);
                    bot.tasklist.deleteTask(index);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e2) {
                    System.out.println("Invalid index");
                }
            } else {
                System.out.println("Invalid command.");
            }

        }

    }
}
