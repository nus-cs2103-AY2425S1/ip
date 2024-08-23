import java.util.Scanner;

public class Maga {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsUndone() {
            isDone = false;
        }

        public String getDescription() {
            return description;
        }
    }
    public static void main(String[] args) {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // task array
        Task[] arr = new Task[100];
        int count = 0;
        while(!input.equalsIgnoreCase("bye")) {
            // using list command
            if(input.equalsIgnoreCase("list")) {
                System.out.println("Oh dear here are all the tasks in the list, so many, yuuge\n");
                for (int i = 0; i < count; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + arr[i].getStatusIcon() + arr[i].getDescription());
                }

                input = scanner.nextLine();
                continue;
            }

            // marking things as done and undone
            if (input.toLowerCase().startsWith("mark ")) {
                System.out.println("Ya boi Donald took the liberty to mark this done:\n");
                char[] charArray = input.toCharArray();
                Task temp = arr[Character.getNumericValue(charArray[charArray.length - 1]) - 1];
                temp.markAsDone();
                System.out.println(temp.getStatusIcon() + temp.getDescription());
                input = scanner.nextLine();
                continue;
            }

            if (input.toLowerCase().startsWith("unmark ")) {
                System.out.println("Here's the task promised but not completed, just like the dems\n");
                char[] charArray = input.toCharArray();
                Task temp = arr[Character.getNumericValue(charArray[charArray.length - 1]) - 1];
                temp.markAsUndone();
                System.out.println(temp.getStatusIcon() + temp.getDescription());
                input = scanner.nextLine();
                continue;
            }

            // adding things as per normal
            arr[count] = new Task(input);
            count++;
            System.out.println("added: " + input);
            input = scanner.nextLine();
        }

        System.out.println("Yeah goodbye. Remember a vote for me is a vote for America!");
    }
}
