import java.io.*;
    
public class Luke {

    public static String getRequest(BufferedReader br) throws IOException {
	String line = br.readLine();
	return line;
    }
    
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
	    }
	    System.out.println("Luke: " + line);
	}
	System.out.println("Aight, Cya later.");
	br.close();
    }
    
}
