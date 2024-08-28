import ekud.Ekud;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Ekud(sc).run();
        sc.close();
    }
}
