import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

import static com.raylib.java.core.input.Keyboard.KEY_S;
import static com.raylib.java.core.input.Keyboard.KEY_W;

static final int SCREEN_WIDTH = 800;
static final int SCREEN_HEIGHT = 600;

static int PADDLE_WIDTH = 10;
static int PADDLE_HEIGHT = 100;

public static int scorePlayer = 0;
public static int scoreCpu = 0;

static int randomDir() {
    return ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
}

void main() {

    Raylib ray = new Raylib();
    ray.core.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "PONG");
    ray.core.SetTargetFPS(60);

    Ball ball = new Ball(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 20, 4);

    Paddle leftPaddle = new Paddle(10, SCREEN_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, 7);
    Paddle rightPaddle = new Paddle(SCREEN_WIDTH - PADDLE_WIDTH - 10, SCREEN_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, 7);

    Player p1 = new Player(leftPaddle, KEY_W, KEY_S);
    CPU cpu = new CPU(rightPaddle);

    ball.reset(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, randomDir(), randomDir());

    while (!ray.core.WindowShouldClose()) {

        int result = ball.update(ray, SCREEN_WIDTH, SCREEN_HEIGHT, leftPaddle, rightPaddle);

        if (result == 1) {
            scorePlayer++;
            ball.reset(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, -1, randomDir());
        }
        if (result == -1) {
            scoreCpu++;
            ball.reset(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 1, randomDir());
        }

        leftPaddle.update(SCREEN_HEIGHT);
        rightPaddle.update(SCREEN_HEIGHT);

        p1.update(ray, SCREEN_HEIGHT);
        cpu.update(ball, SCREEN_HEIGHT);

        ray.core.BeginDrawing();
        ray.core.ClearBackground(Color.BLACK);

        ray.shapes.DrawLine(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT, Color.WHITE);

        ray.text.DrawText(ray.text.TextFormat("%d", scorePlayer), SCREEN_WIDTH / 4, 20, 80, Color.WHITE);
        ray.text.DrawText(ray.text.TextFormat("%d", scoreCpu), SCREEN_WIDTH * 3 / 4, 20, 80, Color.WHITE);

        ball.draw(ray);
        leftPaddle.draw(ray);
        rightPaddle.draw(ray);

        ray.core.EndDrawing();
    }

    ray.core.CloseWindow();
}