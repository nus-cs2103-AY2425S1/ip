import java.util.Scanner;
import java.util.ArrayList;

public class Susan {
    public static final String INDENTATION = "    "; 
	public static final String logo = "      ___           ___           ___           ___           ___     \n" +
		"     /\\  \\         /\\__\\         /\\  \\         /\\  \\         /\\__\\    \n" +
		"    /::\\  \\       /:/  /        /::\\  \\       /::\\  \\       /::|  |   \n" +
		"   /:/\\ \\  \\     /:/  /        /:/\\ \\  \\     /:/\\:\\  \\     /:|:|  |   \n" +
		"  _\\:\\~\\ \\  \\   /:/  /  ___   _\\:\\~\\ \\  \\   /::\\~\\:\\  \\   /:/|:|  |__ \n" +
		" /\\ \\:\\ \\ \\__\\ /:/__/  /\\__\\ /\\ \\:\\ \\ \\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\\n" +
		" \\:\\ \\:\\ \\/__/ \\:\\  \\ /:/  / \\:\\ \\:\\ \\/__/ \\/__\\:\\/:/  / \\/__|:|/:/  /\n" +
		"  \\:\\ \\:\\__\\    \\:\\  /:/  /   \\:\\ \\:\\__\\        \\::/  /      |:/:/  / \n" +
		"   \\:\\/:/  /     \\:\\/:/  /     \\:\\/:/  /        /:/  /       |::/  /  \n" +
		"    \\::/  /       \\::/  /       \\::/  /        /:/  /        /:/  /   \n" +
		"     \\/__/         \\/__/         \\/__/         \\/__/         \\/__/    \n";
	public static final String intro = "Meow. I'm Susan!\n" + INDENTATION + "What can I do for you?";
	public static final String outro = "Meow. Hope to see you again soon!";
	public static ArrayList<String> items = new ArrayList<>();


	public static void main(String[] args) { 
        System.out.println(logo);
		printItem(intro);
		
		Scanner sc = new Scanner(System.in);
  
		String obj = sc.nextLine();
		while (!obj.equals("bye")) {
			handleItem(obj);
            obj = sc.nextLine();
        }
	
		printItem(outro);
    }

	public static void handleItem(String item) {
		if (item.equals("list")) {
			printList(items);
		} else {
			items.add(item);
			printItem("added: " + item);
		}
	}

	public static void printList(ArrayList<String> ls) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < items.size(); i++ ) {
			String s = items.get(i);
			res.append((i+1) + ". " + s);
			if (i != items.size() - 1) {
				res.append("\n" + INDENTATION);	
			}
		}
		printItem(res.toString());
	}

	public static void printItem(String item) {
		System.out.println(INDENTATION + "____________________________________________________________");
		System.out.println(INDENTATION + item);
		System.out.println(INDENTATION + "____________________________________________________________");
	}
}
