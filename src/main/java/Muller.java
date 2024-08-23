import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Muller {
    public static void main(String[] args) {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n"
                                + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(true){
            String in = sc.nextLine();
            String[] inputs = in.split(" ", 2);
            if(inputs[0].equals("bye")){
                break;
            } else if (inputs[0].equals("mark")) {
                int index = Integer.parseInt(inputs[1]) - 1;
                list.get(index).markAsDone();
                System.out.println(logo + "\nNice! I've marked this task as done:\n");
                System.out.println("  " + list.get(index) + "\n" + logo);
            } else if (inputs[0].equals("unmark")) {
                int index = Integer.parseInt(inputs[1]) - 1;
                list.get(index).markAsNotDone();
                System.out.println(logo + "\nOK, I've marked this task as not done yet:\n");
                System.out.println("  " + list.get(index) + "\n" + logo);
            } else if (inputs[0].equals("todo")) {
                System.out.println(logo + "\nGot it. I've added this task:\n");
                Task t = new Task(inputs[1]);
                t.setType("T");
                list.add(t);
                System.out.println("  " + t);
                System.out.println("\nNow you have " + list.size() + " tasks in the list.\n" + logo);
            } else if (inputs[0].equals("deadline")) {
                System.out.println(logo + "\nGot it. I've added this task:\n");
                String[] detail = inputs[1].split("/by", 2);
                Task t = new Task(detail[0]);
                t.setType("D");
                t.setDate("(by:" + detail[1] + ")");
                list.add(t);
                System.out.println("  " + t);
                System.out.println("\nNow you have " + list.size() + " tasks in the list.\n" + logo);
            } else if (inputs[0].equals("event")) {
                System.out.println(logo + "\nGot it. I've added this task:\n");
                String[] detail = inputs[1].split("/from");
                String[] ending = detail[1].split("/to");
                Task t = new Task(detail[0]);
                t.setType("E");
                t.setDate("(from:" + ending[0] + "to:" + ending[1] + ")");
                list.add(t);
                System.out.println("  " + t);
                System.out.println("\nNow you have " + list.size() + " tasks in the list.\n" + logo);
            } else if (in.equals("list")) {
                System.out.println(logo);
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i + ": " + list.get(i-1));
                }
                System.out.println(logo);
            } else {
                System.out.println(logo + "\n" + "added: " + in+ "\n" + logo);
                Task t = new Task(in);
                list.add(t);
            }
        }

        System.out.println(logo + "\nBye. Hope to see you again soon!\n" + logo);
        sc.close();
    }

    public static class Task {
        private String name;
        private boolean isDone;
        private String type = "[ ]";
        private String date = "";

        public Task(String name) {
            this.name = name;
            this.isDone = false;
        }

        public void setType(String type) {
            this.type = "[" + type + "]";
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public String DoneIcon() {
            return (isDone ? "[X]" : "[ ]"); // Return X or space depending on isDone
        }

        @Override
        public String toString() {
            return this.type + DoneIcon() + " " + name + this.date;
        }
    }
}


