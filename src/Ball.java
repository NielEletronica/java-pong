import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

public class Ball {
    public float x, y;
    public float speedX, speedY;
    public int radius;
    public int ballSpeed;

    public float speedMultiplier = 1f;
    public float maxMultiplier = 2f;
    public float acceleration = 0.002f;

    public Ball(float x, float y, int radius, int ballSpeed) {
        this.x = x;
        this.y = y;

        this.ballSpeed = ballSpeed;
        this.radius = radius;

        this.speedMultiplier = 1f;
    }

    public int update(Raylib ray, int screenWidth, int screenHeight, Paddle leftPaddle, Paddle rightPaddle) {

        if (speedMultiplier < maxMultiplier) speedMultiplier += acceleration;

        x += speedX * speedMultiplier;
        y += speedY * speedMultiplier;

        Rectangle leftRec = new Rectangle(leftPaddle.x, leftPaddle.y, leftPaddle.width, leftPaddle.height);
        Rectangle rightRec = new Rectangle(rightPaddle.x, rightPaddle.y, rightPaddle.width, rightPaddle.height);

        if (speedX < 0 && ray.shapes.CheckCollisionCircleRec(new Vector2(x, y), radius, leftRec)) {
            speedX = Math.min(Math.abs(speedX) * 1.05f, 15f);
            x = (leftPaddle.x + leftPaddle.width + radius);
        }

        if (speedX > 0 && ray.shapes.CheckCollisionCircleRec(new Vector2(x, y), radius, rightRec)) {
            speedX = -Math.min(Math.abs(speedX) * 1.05f, 15f);
            x = (rightPaddle.x - radius);
        }

        if (y - radius <= 0 || y + radius >= screenHeight) speedY *= -1;

        if (x - radius <= 0) return -1;            // CPU scores
        if (x + radius >= screenWidth) return 1;   // player scores
        return 0;

    }

    public void reset(int centerX, int centerY, int dirX, int dirY) {
        this.x = centerX;
        this.y = centerY;
        this.speedX = ballSpeed * dirX;
        this.speedY = ballSpeed * dirY;
        this.speedMultiplier = 1f;
    }

    public void draw(Raylib ray) {
        ray.shapes.DrawCircle((int)x, (int)y, radius, Color.WHITE);
    }
}