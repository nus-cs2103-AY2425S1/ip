package sentinel.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The GeminiApi handles all AI related requests.
 */

public class GeminiApi {
    /**
     * @param input The prompt sent to Gemini.
     * @return The text result from Gemini.
     */

    public static String query(String input) {
        if (input.isBlank()) {
            return null;
        }
        // Load environment variables from .env file
        Map<String, String> envVars = loadEnvVars();
        String apiKey = envVars.get("API_KEY");

        try {
            // Create the URL object with the endpoint
            URL url = new URL("https://generativelanguage.googleapis.com/"
                    + "v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            String jsonPayload = "{\"contents\":[{\"parts\":[{\"text\":\"" + input + "\"}]}]}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }
            //int responseCode = conn.getResponseCode();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("text")) {
                        continue;
                    }
                    return line.substring(line.indexOf(":") + 3);
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * @param dateInput The date string to be formatted.
     * @return The LocalDateTime object after being formatted by Gemini and parsed.
     */

    public static LocalDateTime formatDateTime(String dateInput) {
        String res = query("""
Format this date into {YYYY}-{MM}-{DD}T{Hour}:{Minute}:{Second}.
If there is a missing time, put T00:00:00. Else if there is a missing field or no such date, just return 'null'.
Don't give any explanation or any other answer other than 'null' or '{YYYY}-{MM}-{DD}T{Hour}:{Minute}:{Second}'. \n
            """ + dateInput);
        if (res == null || res.contains("null")) {
            return null;
        }
        String dateString;
        dateString = res.substring(0, 19);
        return LocalDateTime.parse(dateString);

    }

    // Method to load environment variables from .env file
    private static Map<String, String> loadEnvVars() {
        Map<String, String> envVars = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    continue;
                }
                String key = parts[0].trim();
                String value = parts[1].trim();
                envVars.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return envVars;
    }
}
