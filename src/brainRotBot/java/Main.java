public class Main {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.home") + "/ip/data/brainRot.txt";
        BrainRot brainRot = new BrainRot(filePath);
        brainRot.run();
    }
}
