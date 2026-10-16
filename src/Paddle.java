import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

public class Paddle {
    public int x, y;
    public int width, height;
    public int speed;

    public Paddle(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.speed = speed;
    }

    public void update(int screenHeight) {
        if (y < 0) y = 0;
        if (y + height > screenHeight) y = screenHeight - height;
    }

    public void moveUp()   { y -= speed; }
    public void moveDown() { y += speed; }


    public void draw(Raylib ray) {
        ray.shapes.DrawRectangle(x, y, width, height, Color.WHITE);
    }
}