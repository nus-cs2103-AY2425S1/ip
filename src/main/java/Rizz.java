import java.util.Scanner;
import java.util.ArrayList;

public class Rizz {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> arrList = new ArrayList<>();

    private void greet() {
        String str = "\t____________________________________________________________\n" +
                "\tHello! I'm Rizz\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    private void echo(String textInput) {
        arrList.add(new Task(textInput));
        System.out.println("\tadded: " + textInput + "\n");
    }

    private void exit() {
        String str =  "\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    private void outputList() {
        if (arrList.isEmpty()) {
            System.out.println("\tNo items in the list.\n");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < arrList.size(); i++) {
                strBuilder.append("\t").append(i + 1).append(". ").append(arrList.get(i)).append("\n");
            }
            String str = "\t____________________________________________________________\n" +
                    "\tHere are the tasks in your list:\n" + strBuilder +
                    "\t____________________________________________________________\n";
            System.out.println(str);
        }
    }

    private void markTask(int index) {
        if (index >= 1 && index <= arrList.size()) {
            Task task = arrList.get(index - 1);
            task.markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + task + "\n");
        } else {
            System.out.println("\tInvalid task number.\n");
        }
    }

    private void unmarkTask(int index) {
        if (index >= 1 && index <= arrList.size()) {
            Task task = arrList.get(index - 1);
            task.unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + task + "\n");
        } else {
            System.out.println("\tInvalid task number.\n");
        }
    }

    private void caseCheck() {
        while (true) {
            String textInput = this.input.nextLine();
            if (textInput.equals("bye")) {
                this.exit();
                break;
            } else if (textInput.equals("list")) {
                this.outputList();
            } else if (textInput.startsWith("mark ")) {
                int index = Integer.parseInt(textInput.split(" ")[1]);
                this.markTask(index);

            } else if (textInput.startsWith("unmark ")) {
                int index = Integer.parseInt(textInput.split(" ")[1]);
                this.unmarkTask(index);
            } else {
                this.echo(textInput);
            }
        }
    }


    public static void main(String[] args) {
        Rizz rizz = new Rizz();
        rizz.greet();
        rizz.caseCheck();
    }
}
