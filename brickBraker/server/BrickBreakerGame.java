package brickBraker.server;

import javax.swing.*;
import java.util.ArrayList;

public class BrickBreakerGame extends JFrame {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;

    //client
    /* 
    private BufferedImage buffer;
    private Graphics bufferGraphics;
    */

    public BrickBreakerGame() {
        //client
        /*
        setTitle("Brick Breaker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = buffer.getGraphics();
        */

        //server
        ball = new Ball(WIDTH / 2, HEIGHT / 2);
        paddle = new Paddle(WIDTH / 2 - Paddle.WIDTH / 2, HEIGHT - 50);
        bricks = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.add(new Brick(i * 100 + 50, j * 40 + 50));
            }
        }

        //client
        /*
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                update();
                repaint();
            }
        });

        timer.start();

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                paddle.move(e.getX());
                repaint();
            }
        });

        setFocusable(true);
        */
    }

    //server
    private void update() {
        ball.move();
        checkCollision();
    }

    private void checkCollision() {
        // Verifica collisione con il paddle
        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.reverseY();
        }

        // Verifica collisione con i mattoni
        for (Brick brick : bricks) {
            if (ball.getBounds().intersects(brick.getBounds())) {
                bricks.remove(brick);
                ball.reverseY();
                break;
            }
        }

        // Verifica collisione con i bordi della finestra
        if (ball.getX() <= 0 || ball.getX() >= WIDTH - Ball.SIZE) {
            ball.reverseX();
        }

        if (ball.getY() <= 0) {
            ball.reverseY();
        }

        // Verifica se il giocatore ha perso
        if (ball.getY() >= HEIGHT) {
            JOptionPane.showMessageDialog(this, "Hai perso!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    //client
    /* 
    @Override
    public void paint(Graphics g) {
        // Disegna sul buffer
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.fillRect(0, 0, WIDTH, HEIGHT);

        ball.draw(bufferGraphics);
        paddle.draw(bufferGraphics);

        for (Brick brick : bricks) {
            brick.draw(bufferGraphics);
        }

        // Copia il buffer sulla finestra
        g.drawImage(buffer, 0, 0, this);
    }
    */

    public static void main(String[] args) {
        //client
        /* 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BrickBreakerGame().setVisible(true);
            }
        });
        */
    }
}
