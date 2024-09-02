package potong;

import potong.command.Command;
import potong.command.ExitCommand;
import potong.exceptions.IllegalInputPotongException;
import potong.exceptions.PotongException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Potong {

    private static final String LINE = "_________________________";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Potong() throws FileNotFoundException, IllegalInputPotongException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(tasks);
    }

    public void run() {
        ui.showIntroduction();
        while (true) {
            String userInput = ui.getUserCommand();
            Command c = Parser.parse(userInput);
            assert c != null;
            try {
                String message = c.execute(tasks, storage, ui);
                ui.print(message);
            } catch (PotongException | IOException e) {
                throw new RuntimeException(e);
            }
            if (c instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Potong().run();
        } catch (FileNotFoundException | IllegalInputPotongException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public static void main(String[] args) throws potong.exceptions.PotongException, IOException {
        System.out.println("Hello! I'm potong.Potong");
        System.out.println("What can I do for you?\n");
        System.out.println(potong.Potong.LINE);
        potong.TaskList data = new potong.TaskList();
        potong.Storage storage = new potong.Storage(data);
        Scanner input = new Scanner(System.in);

        while (true) {
            String userInput = input.nextLine();
            System.out.println(potong.Potong.LINE);
            if (userInput.equals("bye")) {
                data.writeToStorage(storage);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(data);
                System.out.println(potong.Potong.LINE);
            } else if (userInput.startsWith("mark")) {
                int index = Integer.valueOf(userInput.substring(5));
                System.out.println(data.mark(index));
                System.out.println(potong.Potong.LINE);
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.valueOf(userInput.substring(7));
                System.out.println(data.unmark(index));
                System.out.println(potong.Potong.LINE);
            } else if (userInput.startsWith("delete")) {
                int index = Integer.valueOf(userInput.substring(7));
                System.out.println(data.delete(index));
                System.out.println(potong.Potong.LINE);
            } else if (userInput.startsWith("todo")) {
                String todo = userInput.substring(5).trim();
                System.out.println(data.add(new potong.task.ToDoTask(todo)));
                System.out.println(potong.Potong.LINE);
            } else if (userInput.startsWith("deadline")) {
                String desc = userInput.substring(9).trim();
                String task = desc.split("/", 2)[0].trim();
                String deadline = desc.split("/", 2)[1].trim().substring(3);
                System.out.println(data.add(new potong.task.DeadlineTask(task, deadline)));
                System.out.println(potong.Potong.LINE);
            } else if (userInput.startsWith("event")) {
                String desc = userInput.substring(6).trim();
                String task = desc.split("/", 3)[0].trim();
                String start = desc.split("/", 3)[1].trim().substring(5);
                String end = desc.split("/", 3)[2].trim().substring(3);
                System.out.println(data.add(new potong.task.EventTask(task, start, end)));
                System.out.println(potong.Potong.LINE);
            } else {
                throw new potong.exceptions.PotongException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
//            System.out.println(data.add(new potong.task.Task(userInput)));
//            System.out.println(potong.Potong.LINE);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(potong.Potong.LINE);

    }
    */

}
