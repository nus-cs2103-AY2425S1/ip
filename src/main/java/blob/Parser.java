package blob;

import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public static void evaluateAction(Ui ui, TaskList tasklist, String action) {
        String[] arr = action.split(" ");

        for (int i = 0; i < arr.length; i++) {
            String act = arr[i].toLowerCase();

            if (act.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                ui.breakLine();
                ui.exit();
                break;

            } else if (act.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < tasklist.size(); j++) {
                    Task t = tasklist.getTask(j);
                    System.out.println(String.format("%d. %s", j + 1, t));
                }
                ui.breakLine();
                break;

            } else if (act.equals("mark")) {
                try {
                    int index = Integer.parseInt(arr[i + 1]) - 1;
                    tasklist.markTask(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasklist.getTask(index));
                    ui.breakLine();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }
                break;

            } else if (act.equals("unmark")) {
                try {
                    int index = Integer.parseInt(arr[i + 1]) - 1;
                    tasklist.unmarkTask(index);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasklist.getTask(index));
                    ui.breakLine();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }
                break;

            } else if (act.equals("delete")) {
                try {
                    int index = Integer.parseInt(arr[i + 1]) - 1;
                    Task t = tasklist.deleteTask(index);
                    System.out.println("Noted, I've removed this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    ui.breakLine();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }
                break;

            } else if (act.equals("todo")) {
                // task name error handling
                if (arr.length == 1) {
                    System.out.println("Sorry, I am unable to generate an empty 'todo' task!");
                    ui.breakLine();
                    break;
                }

                // for task name string
                StringBuilder a = new StringBuilder(arr[i + 1]);
                for (int j = i + 2; j < arr.length; j++) {
                    StringBuilder str = new StringBuilder(" " + arr[j]);
                    a = a.append(str);
                }

                Todo t = new Todo(a.toString(), false);
                tasklist.addTask(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                ui.breakLine();
                break;

            } else if (act.equals("deadline")) {
                int by = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j].equals("/by")) {
                        by = j;
                    }
                }

                // task name error handling
                if (by <= 1) {
                    System.out.println("I require a description for your 'deadline' task 😅!");
                    ui.breakLine();
                    break;
                }

                // task deadline error handling
                if (by == arr.length - 1) {
                    System.out.println("Your deadline task can't not have a deadline! Please enter your task again!");
                    ui.breakLine();
                    break;
                }

                //task deadline only having date or time
                if (by == arr.length - 2) {
                    System.out.println("You need to add both DATE and TIME to your deadline \uD83D\uDD27! (Format: yyyy-mm-dd HH:mm)");
                    ui.breakLine();
                    break;
                }

                // for task name string
                StringBuilder a = new StringBuilder(arr[i + 1]);
                for (int l = i + 2; l < by; l++) {
                    StringBuilder str = new StringBuilder(" " + arr[l]);
                    a = a.append(str);
                }

                // for task deadline string
                StringBuilder sDate = new StringBuilder(arr[by + 1]);
                StringBuilder sTime = new StringBuilder(arr[by + 2]);
                String ISO8601Format = sDate.toString() + "T" + sTime.toString() + ":00";

                try {
                    Deadline d = new Deadline(a.toString(), false, ISO8601Format);
                    tasklist.addTask(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    ui.breakLine();
                } catch (DateTimeParseException e) {
                    System.out.println("Input dates and times not in the format 'yyyy-mm-dd HH:mm'!");
                    ui.breakLine();
                }
                break;

            } else if (act.equals("event")) {
                int start = 0;
                int end = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j].equals("/from")) {
                        start = j;
                    } else if (arr[j].equals("/to")) {
                        end = j;
                    }
                }

                // task name error handling
                if (start <= 1) {
                    System.out.println("What's the name of your event? Please add it 😅!");
                    ui.breakLine();
                    break;
                }

                // start string error handling
                if (end - start <= 1) {
                    System.out.println("When does your event begin? Please add it 😅!");
                    ui.breakLine();
                    break;
                }

                // end string error handling
                if (end == arr.length - 1) {
                    System.out.println("What time does your event end? Please let me know 😅!");
                    ui.breakLine();
                    break;
                }

                //'/from' only having date or time
                if (end - start == 2) {
                    System.out.println("You need to add both DATE and TIME to your event's start \uD83D\uDD27! (Format: yyyy-mm-dd HH:mm)");
                    ui.breakLine();
                    break;
                }

                //'/to' only having date or time
                if (end == arr.length - 2) {
                    System.out.println("You need to add both DATE and TIME to your event's end \uD83D\uDD27! (Format: yyyy-mm-dd HH:mm)");
                    ui.breakLine();
                    break;
                }

                //for task name string
                StringBuilder a = new StringBuilder(arr[i + 1]);
                for (int l = i + 2; l < start; l++) {
                    StringBuilder str = new StringBuilder(" " + arr[l]);
                    a = a.append(str);
                }

                //for start string
                StringBuilder stDate = new StringBuilder(arr[start + 1]);
                StringBuilder stTime = new StringBuilder(arr[start + 2]);
                String startISO8601Format = stDate.toString() + "T" + stTime.toString() + ":00";

                //for end string
                StringBuilder enDate = new StringBuilder(arr[end + 1]);
                StringBuilder enTime = new StringBuilder(arr[end + 2]);
                String endISO8601Format = enDate.toString() + "T" + enTime.toString() + ":00";

                try {
                    Event e = new Event(a.toString(), false, startISO8601Format, endISO8601Format);
                    tasklist.addTask(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    ui.breakLine();
                } catch (DateTimeParseException e) {
                    System.out.println("Input dates and times not in the format 'yyyy-mm-dd HH:mm'!");
                    ui.breakLine();
                }
                break;

            } else {
                System.out.println("ERROR! Unknown Command!");
                ui.breakLine();
                break;
            }
        }
    }
}
