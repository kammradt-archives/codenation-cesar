package Request;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Request {
    private final String TOKEN = "39dd1c53d21291e538cbde72ec38d2fbba9f38ee";
    private final String URL_GET = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
    private final String URL_POST = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";

    public JSONObject getJson(){
        JSONObject response = new JSONObject();
        try {
            response = Unirest.get(this.URL_GET + this.TOKEN)
                    .asJson()
                    .getBody()
                    .getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void writeJson(JSONObject json, String fileName){
        try {
            FileWriter file = new FileWriter("src/main/resources/"+fileName+".json");
            file.write(json.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJsonFromFile(){
        Path path = Paths.get("src/main/resources/response.json");

        String stringContent = "";
        try {
            stringContent = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(stringContent);
    }

    public JSONObject postJson(String fileName){
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse = Unirest.post(
                    this.URL_POST + this.TOKEN)
                    .field("answer", new File("src/main/resources/"+fileName+".json"))
                    .asJson()
                    .getBody()
                    .getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
