public class Orion {

    public static final String LOGO = "             .__               \n"
                                    + "  ___________|__| ____   ____  \n"
                                    + " /  _ \\_  __ \\  |/  _ \\ /    \\ \n"
                                    + "(  <_> )  | \\/  (  <_> )   |  \\\n"
                                    + " \\____/|__|  |__|\\____/|___|  /\n"
                                    + "                            \\/ \n";

    public static final String BAR = "______________________________________________\n";

    public static void main(String[] args) {
        System.out.println(Orion.BAR);
        System.out.println(Orion.LOGO);

        System.out.println(Orion.BAR);
        System.out.println("Hello from Orion!");
        System.out.println("What do you want to talk about today?");

        System.out.println(Orion.BAR);
        System.out.println("Bye! Hope to see you again soon!");

        System.out.println(Orion.BAR);
    }
}
