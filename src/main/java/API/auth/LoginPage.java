package API.auth;

import org.json.JSONObject;

public class LoginPage {
    public String loginEndpoint = "/api/rest/login";
    public String loginPayload(String user, String pass) {
        JSONObject body = new JSONObject();
        body.put("usernameOrEmail", user);
        body.put("password", pass);
        return body.toString();
    }
}