package data;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class URLCreator {

    public static boolean sendPostRequest(String title, String description) throws IOException, InterruptedException {
        //setting up the connection
        String NEWSFEED_ADDRESS = "malegro.ddns.net:8080/newsfeed";
        URL url = new URL(NEWSFEED_ADDRESS);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        //Setting up and convert values which will be send
        Map<String, String> arguments = new HashMap<>();
        arguments.put("title", title);
        arguments.put("description", description);
        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String, String> entry: arguments.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        //sending data
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "applocation/x-www-form-urlencoded; charset=UTF-8");
        boolean connect = true;
        while(connect) {
            try {
                http.connect();
                connect = false;
            } catch (BindException e) {
                Thread.sleep(1000);
            }
        }

        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        if(http.getResponseCode() == 400) {
            System.out.println("Something went wrong. " + title + " " + description);
            http.disconnect();
            return false;
        }
        http.disconnect();
        return true;
    }

}
