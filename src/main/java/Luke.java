import java.io.*;
import java.util.*;
    
public class Luke {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ArrayList<String> l = new ArrayList<String>();
        String logo =
	          " __                \n"
                + "| |    _   _ _  _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |__ | |_| |   <  __/\n"
                + "|____| \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
	System.out.println("Yo! I'm Luke.\nHow's it hanging?");
	String line = "";
	while (true) {
	    line = br.readLine();
	    if (line.equals("bye")) {
		break;
	    } else if (line.equals("list")) {
		for (int i = 0; i < l.size(); i++) {
		    System.out.println(String.format("%d. %s", i + 1, l.get(i)));
		}
	    } else {
		l.add(line);
		System.out.println("added: " + line);
	    }
	}
	System.out.println("Aight, Cya later.");
	br.close();
    }
}
