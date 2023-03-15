package org.example;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {
    public static void main(String[] args) throws IOException {

        int port = parseSettings("port");

        Server server = new Server();
        server.listen(port);

    }

    public static int parseSettings(String port) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader("settings.json"));
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt((String) jsonObject.get("port"));
    }

}