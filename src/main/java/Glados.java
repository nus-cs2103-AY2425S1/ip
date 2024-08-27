import java.util.Scanner;
import java.util.ArrayList;

public class Glados {
    private TaskList taskList;

    public Glados() {
        this.taskList = new TaskList();
    }

    private void run() {
        Ui.greet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine().toLowerCase();
            if (input.equals("bye")) {
                Ui.exit();
                break;
            } else {
                try {
                    String query = input.split(" ")[0];
                    switch (query) {
                    case "echo":
                        Ui.echo(input.substring(4, input.length()));
                        break;
                    case "todo":
                        add(TaskType.TODO, input.substring(4, input.length()));
                        break;
                    case "deadline":
                        add(TaskType.DEADLINE, input.substring(8, input.length()));
                        break;
                    case "event":
                        add(TaskType.EVENT, input.substring(5, input.length()));
                        break;
                    case "list":
                        list();
                        break;
                    case "mark":
                        mark(Integer.valueOf(input.substring(5, input.length())));
                        break;
                    case "unmark":
                        unmark(Integer.valueOf(input.substring(7, input.length())));
                        break;
                    case "delete":
                        delete(Integer.valueOf(input.substring(7, input.length())));
                        break;
                    default:
                        throw new CommandNotFoundException();
                    
                    }
                } catch (GladosException e) {
                    Ui.error(e);
                }
            }
        }
        sc.close();
    }

    private void add(TaskType taskType, String input) throws GladosException {
        String[] res = taskList.add(taskType, input);
        Ui.add(res[0], res[1]);
    }

    private void delete(int index) throws TaskNotFoundException{
        String[] res = taskList.delete(index);  
        Ui.delete(res[0], res[1]);
    }

    private void list() {
        ArrayList<Task> res = taskList.list();
        Ui.list(res);
    }

    private void mark(int index) {
        String res = taskList.mark(index);
        Ui.mark(res);
    }

    private void unmark(int index) {
        String res = taskList.unmark(index);
        Ui.unmark(res);
    }

    public static void main(String[] args) {
        new Glados().run();
    }
}
