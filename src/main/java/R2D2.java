import java.util.Scanner;
public class R2D2 {
    public static void main(String[] args) {

        class Task {
            protected String description;
            protected boolean isDone;
            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }
            public String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }
            public void setDone(boolean setter) {
                this.isDone = setter;
            }
        }
        //Opening dialogue for the bot
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);

        // Reading input from user
        Scanner reader = new Scanner(System.in);
        Task userTask = new Task(reader.nextLine());

        // init a new database and counter for memory recall
        Task[] database = new Task[100];
        int counter = 1;

        // Main interaction
        while (!(userTask.description.equals("bye"))) {
            if (userTask.description.startsWith("mark")) {
                int index = Integer.parseInt(userTask.description.split(" ")[1]);
                database[index].setDone(true);
                System.out.println(hline);
                System.out.println("Mission accomplished! *bzzt*");
                System.out.println("[" + database[index].getStatusIcon() + "] " + database[index].description);
                System.out.println(hline);
            } else if (userTask.description.startsWith("unmark")) {
                int index = Integer.parseInt(userTask.description.split(" ")[1]);
                database[index].setDone(false);
                System.out.println(hline);
                System.out.println("Argh next time! *bzzt*");
                System.out.println("[" + database[index].getStatusIcon() + "] " + database[index].description);
                System.out.println(hline);
            } else if (!userTask.description.equals("list")) {
                //added into database
                System.out.println(hline);
                System.out.println("Buzzinga! Added to your list: \n" + userTask.description);
                System.out.println(hline);

                //processing in the background (adding to database)
                database[counter] = userTask;
                counter++;
            } else { // list has been typed
                System.out.println(hline);
                for (int i = 1; i < counter; i++) {
                    System.out.println(i + "." + "[" + database[i].getStatusIcon() + "] " + database[i].description);
                }
                System.out.println(hline);
            }
            userTask = new Task(reader.nextLine());
        }

        // standard exit when bye is typed
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }
}
