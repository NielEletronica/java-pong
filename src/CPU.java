public class CPU {
    Paddle paddle;

    public CPU(Paddle paddle) {
        this.paddle = paddle;
    }

    public void update(Ball ball, int screenHeight) {
        float paddleCenter = paddle.y + paddle.height / 2f;

        if (paddleCenter < ball.y - 10)      paddle.moveDown();
        else if (paddleCenter > ball.y + 10) paddle.moveUp();

        paddle.update(screenHeight);
    }
}