import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GeminiAPI {
    public static void main(String[] a) {
        // Load environment variables from .env file
        Map<String, String> envVars = loadEnvVars(".env");
        String apiKey = envVars.get("API_KEY");

        try {
            // Create the URL object with the endpoint
            URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            String jsonPayload = "{\"contents\":[{\"parts\":[{\"text\":\"Format this date into YYYY-MM-DD. If there is no day, month, or year, or no such date, return null: 31 feb 1830\"}]}]}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("UTF-8"));
                os.flush();
            }
            int responseCode = conn.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
            String dateString;
            LocalDateTime date;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("text")) {
                        dateString = line.substring(line.indexOf(":")+3);
                        System.out.println("dateString=" + dateString);
                        break;
                    }
                }
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load environment variables from .env file
    private static Map<String, String> loadEnvVars(String filePath) {
        Map<String, String> envVars = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    envVars.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return envVars;
    }
}
