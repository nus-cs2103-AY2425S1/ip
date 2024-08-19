/**
 * This class implements a UI for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class UI {
    public static void startScreen() {
        UI.sectionSeparator();
        System.out.println("Hi I'm Bunbun ^-^\nI'm here to help!");
        UI.sectionSeparator();
    }

    public static void endScreen() {
        UI.sectionSeparator();
        System.out.println("Ok byeeeeee :D");
        UI.sectionSeparator();
    }

    public static void sectionSeparator() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }
}
