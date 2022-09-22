package cinema;

import java.util.UUID;

public class Token {
    private UUID token;
    Token(){

    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
