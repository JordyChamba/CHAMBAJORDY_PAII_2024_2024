package default_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class JFrame_Virtual extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private JPanel gamePanel;
    private Contenedor gameContainer;
    private Timer gameTimer;
    private int delay = 1000 / 60;
    private int speed = 10;

    public JFrame_Virtual() {
        setTitle("Galaga ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);
                gameContainer.dibujar(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(800, 600));
        add(gamePanel);

        gameContainer = new Contenedor();

        addKeyListener(this);

        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.addKeyListener(this);

        gameTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameContainer.update();
                gamePanel.repaint();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        gameTimer.start();
    }

   

    public static void main(String[] args) {
        new JFrame_Virtual();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        gameContainer.getHero().keyPressed1(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            gameContainer.getHero().setShooting(false); // Dejar de disparar cuando se suelta la tecla de espacio
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Este método no necesita hacer nada en este caso, pero debes dejarlo implementado debido a la interfaz KeyListener
    }

}
