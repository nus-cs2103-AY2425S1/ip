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
                System.out.println("  " + list.get(index) + "\nlogo");
            } else if (inputs[0].equals("unmark")) {
                int index = Integer.parseInt(inputs[1]) - 1;
                list.get(index).markAsNotDone();
                System.out.println(logo + "\nOK, I've marked this task as not done yet:\n");
                System.out.println("  " + list.get(index) + "\nlogo");
            } else if (in.equals("list")) {
                System.out.println(logo);
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
    }

    static class Task {
        private String name;
        private boolean isDone;

        public Task(String name) {
            this.name = name;
            this.isDone = false;
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
            return DoneIcon() + " " +name;
        }
    }
}


