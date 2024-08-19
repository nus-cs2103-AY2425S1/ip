import java.util.Scanner;

public class Susan {
    public static final String INDENTATION = "    ";
	
	public static void main(String[] args) {
		String logo = 
"      ___           ___           ___           ___           ___     \n" +
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

        System.out.println(logo);
		printItem("Hello! I'm Susan!\n" + INDENTATION + "What can I do for you?");
	
		Scanner sc = new Scanner(System.in);
		
		String obj = sc.nextLine();
		while (!obj.equals("bye")) {
			printItem(obj);	
			obj = sc.nextLine();
		}

		printItem("Bye. Hope to see you again soon!");
    }

	public static void printItem(String item) {
		System.out.println(INDENTATION + "____________________________________________________________");
		System.out.println(INDENTATION + item);
		System.out.println(INDENTATION + "____________________________________________________________");
	}
}
