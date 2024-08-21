import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Darwin {
    static final String NAME = "Darwin";
    static final String END = "bye";
    static final String CHECK_LIST = "list";
    private ArrayList<String> taskList = new ArrayList<>();

    private static void reply(String in) {
        System.out.println(in);
        String line = "-".repeat(30);
        System.out.println(line);
    }

    private void addTask(String task) {
        this.taskList.add(task);
        String added = String.format("added: %s", task);
        Darwin.reply(added);
    }

    private void getTaskList() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            String task = String.format("%d. %s", i + 1, this.taskList.get(i));
            taskList.append(task).append("\n");
        }
        Darwin.reply(String.valueOf(taskList));
    }

    public void initChat() {
        String startMsg = String.format("Hello! I'm %s\nWhat can I do for you?", NAME);
        String endMsg = "Bye. Hope to see you again soon!";
        Darwin.reply(startMsg);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String in = scanner.nextLine();
            if (in.equals(Darwin.END)) {
                Darwin.reply(endMsg);
                break;
            } else if (in.equals(Darwin.CHECK_LIST)) {
                this.getTaskList();
            } else {
                this.addTask(in);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Darwin darwin = new Darwin();
        darwin.initChat();
    }
}
