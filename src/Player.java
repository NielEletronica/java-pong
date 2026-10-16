import com.raylib.java.Raylib;

public class Player {
    Paddle paddle;
    int upKey, downKey;

    public Player(Paddle paddle, int upKey, int downKey) {
        this.paddle = paddle;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update(Raylib r, int screenHeight) {
        if (r.core.IsKeyDown(upKey))   paddle.moveUp();
        if (r.core.IsKeyDown(downKey)) paddle.moveDown();
        paddle.update(screenHeight);
    }
}