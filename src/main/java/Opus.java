import java.util.Scanner;

public class Opus {

    private Storage storage;
    private TaskList taskList;
    // private Ui ui;

    public Opus(String filePath) {
        // ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Hello! I'm Opus");
        System.out.println(" What can I do for you?");

        while (true) {
            String s = scanner.nextLine();
            String[] words = s.split(" ");

            try {
                if (s.equals("bye")) {
                    storage.save(taskList.getTasks());
                    break;
                } else if (s.equals("list")) {
                    taskList.listTasks();
                } else if (words[0].equals("mark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    taskList.getTask(i).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.getTask(i));
                } else if (words[0].equals("delete")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("   " + taskList.getTask(i));
                    taskList.removeTask(i);
                    System.out.println(" Now you have " + taskList.getSize() + " tasks in the list.");
                } else {
                    if (words[0].equals("todo")) {
                        if (words.length <= 1) {
                            throw new OpusEmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        Task todo = new ToDo(s.substring(5));
                        taskList.addTask(todo);
                    } else if (words[0].equals("deadline")) {
                        String[] parts = s.substring(9).split(" /by ");
                        Task deadline = new Deadline(parts[0], parts[1]);
                        taskList.addTask(deadline);
                    } else if (words[0].equals("event")) {
                        String[] parts = s.substring(6).split(" /from ");
                        String[] parts2 = parts[1].split(" /to ");
                        Task event = new Event(parts[0], parts2[0], parts2[1]);
                        taskList.addTask(event);
                    } else {
                        throw new OpusUnknownCommandException("I'm sorry, but I don't know what that means.");
                    }
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + taskList.getTask(taskList.getSize() - 1));
                    System.out.println(" Now you have " + taskList.getSize() + " tasks in the list.");
                }
            } catch (OpusException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void main(String[] args) {
        new Opus("data/tasks.txt").run();
    }
}
