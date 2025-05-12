package common;

public class Message {

    public enum Type {
        NONE,
        CLIENTIDS,
        MSGFROMCLIENT,
        MSGFROMSERVER,
        TOCLIENT,
        DISCONNECT,
        PLAYER_LEFT,
        RESTART,
        PASS,
        GAME_OVER,
        YOUR_ID,
        CHECK_CONNECTION,
        USERNAME,
        OPPONENT_NAME
    }

    public static String GenerateMsg(Type type, String data) {
        return type + "#" + data;
    }
}
