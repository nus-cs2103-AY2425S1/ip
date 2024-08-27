import javafx.scene.control.skin.TextAreaSkin;
import java.util.Scanner;

public class SlothingWaffler {

    private static final Ui ui = new Ui();
    private final TaskList tasks;
    private final Storage storage;

    public SlothingWaffler(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new TaskList(storage);
    }

    public static void main(String[] args) {
        new SlothingWaffler("data.txt").run();
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {
                String input = scanner.nextLine();
                String[] split = input.split(" ", 2);

                if (split[0].strip().equals("bye")) {
                    ui.exit();
                    this.storage.save(tasks.getTasks());
                    break;
                }
                switch (split[0].strip()) {
                case "list" -> tasks.displayTaskList();
                case "mark" -> tasks.markTask(Integer.parseInt(split[1]) - 1);
                case "delete" -> tasks.deleteTask(Integer.parseInt(split[1]) - 1);
                case "todo" -> tasks.addTodoTask(split);
                case "deadline" -> tasks.addDeadlineTask(split);
                case "event" -> tasks.addEventTask(split);
                default -> throw new SlothingWafflerException("Please give instructions that the Slothing Waffler can understand :(");
                }
            } catch (SlothingWafflerException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("YUM. The Waffler is ready for your next command!");
            }
        }
        scanner.close();
    }

}
