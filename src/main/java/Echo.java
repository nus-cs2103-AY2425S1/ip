import java.util.ArrayList;
import java.util.Scanner;

public class Echo {
    private String separator;
    private String message = "";

    private String indent = "      ";

    private ArrayList<IndividualTask> list = new ArrayList<IndividualTask>();


    public Echo(String separator) {
        this.separator = separator;
    }

    //main methods

    public void echoMessage() {
        while (!this.message.strip().equalsIgnoreCase("bye")) {
            System.out.println("Enter your message: ");
            Scanner scanner = new Scanner(System.in);
            this.message = scanner.nextLine();
            if (this.message.strip().equalsIgnoreCase("bye")) {
                break;
            }
            if (this.message.strip().equalsIgnoreCase("list")) {
                this.getTasks();
                continue;
            }
            else if (this.message.strip().toLowerCase().contains("mark")) {
                String message = this.message.strip().toLowerCase();
                String[] parts = message.split(" ");
                if (parts.length > 1) {
                    int number = Integer.parseInt(parts[parts.length - 1]);
                    String checkMarkOrUnmark = parts[0];
                    System.out.println("Extracted Task number: " + number);
                    IndividualTask curTask = list.get(number - 1);
                    if (checkMarkOrUnmark.equals("mark")) {
                        curTask.markOrUnmark("mark");
                        System.out.println(this.indent + "Okays! I've marked this task as done:" + "\n" + this.formatMarkMessage(curTask));
                    } else {
                        curTask.markOrUnmark("unmark");
                        System.out.println(this.indent + "Okay! I've marked this task as not done:" + "\n" + this.formatMarkMessage(curTask));
                    }
                } else {
                    System.out.println("No Task found after 'mark'.");
                }
                continue;
            }

            if (!this.message.isEmpty()) {
                System.out.println(this.formatAddMessage());
                list.add(new IndividualTask(this.message));
            }

        }
    }



    public void getTasks() {
        System.out.println(this.indent + this.separator);
        for (int i = 0; i < list.size(); i++) {
            String number = String.valueOf(i+1);
            String format = this.formatListMessage(number, list.get(i));
            System.out.println(format);
        }
        System.out.println(this.indent + this.separator);
    }



    //Helper methods
    public String formatAddMessage() {
        return this.indent + this.separator + "\n" + this.indent + "added: " + this.message + "\n" + this.indent  + this.separator;
    }

    public String formatListMessage(String number, IndividualTask task) {
        if (!task.getIcon().isEmpty()) {
            return this.indent + number + ".[" + task.getIcon() + "] " + task.getTaskDescription();
        }
        return this.indent + number + ".[ ] " + task.getTaskDescription();
    }

    public String formatMarkMessage(IndividualTask task) {
        if (!task.getIcon().isEmpty()) {
            return this.indent + this.separator + "\n" + this.indent + "[" + task.getIcon() + "] " + task.getTaskDescription() + "\n" + this.indent + this.separator;
        }
        return  this.indent + this.separator + "\n" + this.indent + "[ ] " + task.getTaskDescription() + "\n" + this.indent + this.separator;

    }

}
