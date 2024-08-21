import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

public class Luke {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Task> l = new ArrayList<>();
    String logo =
      " __\n"
      + "| |    _   _ _  _____\n"
      + "| |   | | | | |/ / _ \\\n"
      + "| |__ | |_| |   <  __/\n"
      + "|____| \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Yo! I'm Luke.\nHow's it hanging?");
    String line;
    String[] splittedLine;
    while (true) {
	    line = br.readLine();
      splittedLine = line.split(" ");
	    if (splittedLine[0].equals("bye")) {
        break;
	    } else if (splittedLine[0].equals("list")) {
        for (int i = 0; i < l.size(); i++) {
          System.out.println(String.format("%d. %s", i + 1, l.get(i).toString()));
        }
	    } else if (splittedLine[0].equals("mark")) {
        System.out.println("Nice! I've marked this task as done:");
        Task t = l.get(Integer.parseInt(splittedLine[1]) - 1);
        t.mark();
        System.out.println(t);
      } else if (splittedLine[0].equals("unmark")) {
        System.out.println("OK, I've marked this task as not done yet:");
        Task t = l.get(Integer.parseInt(splittedLine[1]) - 1);
        t.unMark();
        System.out.println(t);
      } else {

          Task t;
          if (splittedLine[0].equals("todo")) {
            t = new Todo(line.substring(4).trim());
            l.add(t);
          } else if (splittedLine[0].equals("deadline")) {
              try {
                  t = new Deadline(line.substring(8).trim());
                  l.add(t);
              } catch (LukeException e) {
                  System.out.println(e.getMessage());
                  continue;
              }
          } else if (splittedLine[0].equals("event")) {
              try {
                  t = new Event(line.substring(5).trim());
                  l.add(t);
              } catch (LukeException e) {
                  System.out.println(e.getMessage());
                  continue;
              }
          } else {
              System.out.printf("Yo! This command \"%s\" doesn't exist.\n", splittedLine[0]);
              continue;
          }
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.printf("Now you have %d tasks in the list.\n", l.size());
        }
    }
    System.out.println("Aight, Cya later.");
    br.close();
  }
}



