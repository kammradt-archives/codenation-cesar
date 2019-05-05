import CaesarCipher.CaesarCipher;
import Request.Request;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        JSONObject json = request.getJsonFromFile();

        String textoCifrado = json.optString("cifrado");
        CaesarCipher caesarCipher = new CaesarCipher(textoCifrado);

        String decoded = caesarCipher.decode(6);
        json = json.put("decifrado", decoded);

        try {
            String sha1 = caesarCipher.sha1(decoded);
            json = json.put("resumo_criptografico", sha1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        request.writeJson(json, "answer");

        JSONObject response_post = request.postJson("answer");
        System.out.println(response_post);

    }
}
