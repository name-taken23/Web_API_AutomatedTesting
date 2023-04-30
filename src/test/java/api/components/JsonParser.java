package api.components;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {

    static ObjectMapper objectMapper = new ObjectMapper();


    public static Map<String, String> createMap(String file) {
        Map<String, String> map =  new HashMap<>();

        String json;
        try {
            json = readFileAsString(file);
            map = objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String readFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
