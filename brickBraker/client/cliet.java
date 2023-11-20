package brickBraker.client;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import brickBraker.server.Ball;
import brickBraker.server.Brick;
import brickBraker.server.BrickBreakerGame;
import brickBraker.server.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;


public class cliet extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BrickBreakerGame().setVisible(true);
            }
        });
    }

    private BufferedImage buffer;
    private Graphics bufferGraphics;

    public void BrickBreakerGame() {
        //client
        setTitle("Brick Breaker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = buffer.getGraphics();

        //client
        
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
        
    }

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
}
