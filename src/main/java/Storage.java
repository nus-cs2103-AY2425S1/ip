import java.io.*;

public class Storage {
    static final String fileName = "deez.txt";
    private String filePath;

    private boolean fileExists() {
        return new File(filePath + File.separator + fileName).exists();
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public boolean canLoad() {
        return fileExists();
    }

    public Deez load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath + File.separator + fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Deez) objectInputStream.readObject();
    }

    public void save(Deez deez) {
        if (!fileExists()) {
            File dir = new File(filePath);
            dir.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + File.separator + fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(deez);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeezException("Failed to save.");
        }
    }


}
