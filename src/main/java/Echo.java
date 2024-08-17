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
        Scanner scanner = new Scanner(System.in);
        while (!this.message.strip().equalsIgnoreCase("bye")) {
            System.out.println("Enter your message:");
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
                        System.out.println(this.indent + "Okays! I've marked this task as done:" + "\n" + this.formatMessage(curTask));
                    } else if (checkMarkOrUnmark.equals("unmark")) {
                        curTask.markOrUnmark("unmark");
                        System.out.println(this.indent + "Okay! I've marked this task as not done:" + "\n" + this.formatMessage(curTask));
                    } else {
                        System.out.println("Not a valid command.");
                    }
                } else {
                    System.out.println("No Task found after 'mark'.");
                }
                continue;
            } else if (this.message.strip().toLowerCase().contains("delete")) {
                String message = this.message.strip().toLowerCase();
                String[] parts = message.split(" ");
                if (parts.length > 1 && parts[0].equals("delete")) {
                    int number = Integer.parseInt(parts[parts.length - 1]);
                    System.out.println("Extracted Task number: " + number);
                    IndividualTask curTask = list.get(number - 1);
                    list.remove(number - 1);
                    System.out.println(this.indent + "Alrighty! I will remove the task: " + "\n" + this.formatMessage(curTask));
                } else {
                    System.out.println("No Task found.");
                }
                continue;
            }
            if (!this.message.isEmpty()) {
                try {
                    processMessage(this.message);
                } catch (MentalHealthException e) {
                    System.out.println(this.indent + this.separator);
                    System.out.println(this.indent + "OOPS!!! " + e.getMessage());
                    System.out.println(this.indent + this.separator);
                }
            }

        }
        scanner.close();
    }

    public void processMessage(String msg) throws MentalHealthException {
        String[] message = msg.split(" ");
        if (message[0].equalsIgnoreCase("todo")) {
            if (message.length < 2 || message[1].trim().isEmpty()) {
                throw new MentalHealthException("The description of a todo cannot be empty.");
            }
            String type = "todo";
            String todo = this.message.substring(type.length()).trim();
            ToDo newTodo = new ToDo(todo);
            list.add(newTodo);
            System.out.println(this.indent + "Okays! I've added this task:" + "\n" + this.formatMessage(newTodo));
        }
        else if (message[0].equalsIgnoreCase("deadline")) {
            String[] parts = this.message.split(" /by ", 2);
            if (parts.length == 2) {
                String type = "deadline";
                String description = parts[0].substring(type.length()).trim(); // get the action
                String by = parts[1].trim(); // get the date
                Deadline newDeadline = new Deadline(description, by);
                list.add(newDeadline);
                System.out.println(this.indent + "Okays! I've added this task:" + "\n" + this.formatMessage(newDeadline));
            } else {
                throw new MentalHealthException("The string doesn't contain the expected format for a deadline.");
            }

        }
        else if (message[0].equalsIgnoreCase("event")) {
            String[] parts = this.message.split(" /from ", 2);
            if (parts.length == 2) {
                String type = "event";
                String description = parts[0].substring(type.length()).trim(); // get the action
                String[] secondPart = parts[1].split(" /to ", 2); //split at /to
                if (secondPart.length == 2) {
                    String from =  secondPart[0].trim();
                    String to = secondPart[1].trim();
                    Event newEvent = new Event(description, from, to);
                    list.add(newEvent);
                    System.out.println(this.indent + "Okays! I've added this task:" + "\n" + this.formatMessage(newEvent));
                } else {
                    throw new MentalHealthException("The string doesn't contain the '/to' part.");
                }
            } else {
                throw new MentalHealthException("The string doesn't contain the '/from' part.");
            }
        } else {
            throw new MentalHealthException("I'm sorry, but I don't know what that means :-(");
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
    public String formatMessage(IndividualTask task) {
        return this.indent + this.separator + "\n" + this.indent + task + "\n" + this.indent + "Now you have " + list.size() + " tasks in the list." + "\n" + this.indent  + this.separator;
    }

    public String formatListMessage(String number, IndividualTask task) {
        return this.indent + number + "." + task.toString();
    }


}
