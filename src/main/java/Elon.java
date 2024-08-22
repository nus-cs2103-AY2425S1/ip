import java.util.ArrayList;
import java.util.Scanner;
public class Elon {
    Action action = new Action();
    private ArrayList<Task> list = new ArrayList<>();

    public String[] nextInput(Scanner scanner) {
        return scanner.nextLine().split(" ");
    }
    private void Run() throws ElonException {
        action.greet();
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                action.listTasks(list);
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("mark")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                action.markTask(index, list);
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("unmark")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                action.unmarkTask(index, list);
                inputArr = this.nextInput(scanner);
                continue;
            } else {
                if (inputArr[0].equals("todo")) {
                    try {
                        action.addToDo(inputArr, list);
                    } catch (ElonException e) {
                        System.out.println(e);
                    }
                } else if (inputArr[0].equals("deadline")) {
                    try {
                        action.addDeadline(inputArr, list);
                    } catch (ElonException e) {
                        System.out.println(e);
                    }
                } else if (inputArr[0].equals("event")) {
                    try {
                        action.addEvent(inputArr, list);
                    } catch (ElonException e) {
                        System.out.println(e);
                    }
                } else {
                    throw new ElonException("Error. Invalid command.");
                }
                action.endAddTask(list.size());
                inputArr = this.nextInput(scanner);
            }
        }
        scanner.close();
        action.exit();
    }
    public static void main(String[] args) {
        Elon elon = new Elon();
        try {
            elon.Run();
        } catch (ElonException e) {
            System.out.println(e);
        }  finally {
        }
    }
}
