package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import Interface_package.IDrawable;
import Interface_package.IShootable;
import Interface_package.ILife;
import Interface_package.IDieable;
import Interface_package.IMovible;
import Interface_package.IScore;
import Interface_package.IUsername;

public class Hero implements IDrawable, IMovible, IDieable, IShootable, IScore, ILife, IUsername {

    private int x, y;
    private int maxHealth;
    private int currentHealth;
    private int width, height;
    private int score;
    private String username;
    private boolean dead = false;
    private boolean shooting = false; // Variable para controlar el disparo continuo

    private Contenedor gameContainer;

    public Hero(int x, int y, int width, int height, String username, Contenedor gameContainer) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = 0;
        this.username = username;
        this.gameContainer = gameContainer;
        this.maxHealth = 100;
        this.currentHealth = this.maxHealth;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        int[] xPoints = {x, x - width / 2, x + width / 2};
        int[] yPoints = {y - height, y + height, y + height};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g.fillPolygon(triangle);

        // Establecer una nueva fuente
        Font font = new Font("Arial", Font.BOLD, 12); // Cambiar la fuente, el tamaño y el estilo según tus preferencias
        g.setFont(font);

        // Dibujar el nombre de usuario
        g.drawString("Player: " + getUsername(), 10, 20);

        // Dibujar el puntaje
        g.drawString("Score: " + getScore(), 10, 40);

        // Dibujar la barra de vida
        int barWidth = 100; // Ancho de la barra de vida
        int barHeight = 10; // Altura de la barra de vida
        int barX = 10; // Posición X de la barra de vida
        int barY = 60; // Posición Y de la barra de vida
        double healthPercentage = (double) currentHealth / maxHealth; // Porcentaje de vida actual

        // Dibujar el contorno de la barra de vida
        g.setColor(Color.BLACK);
        g.drawRect(barX, barY, barWidth, barHeight);

        // Dibujar el interior de la barra de vida
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, (int) (barWidth * healthPercentage), barHeight);

        // Dibujar el texto de la vida actual
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        int textWidth = g.getFontMetrics().stringWidth("LIFE: " + getCurrentHealth());
        int textX = barX + (barWidth - textWidth) / 2; // Centrar el texto horizontalmente
        int textY = barY + barHeight + 15; // Posicionar el texto debajo de la barra de vida
        g.drawString("LIFE: " + getCurrentHealth(), textX, textY);
    }

    @Override
    public void move(int dx, int dy) {
        // Calcular las nuevas coordenadas
        int newX = this.x + dx;
        int newY = this.y + dy;

        int playerBottom = newY + height;
        if (newX >= 0 && newX + width <= 800 && newY >= 0 && playerBottom <= gameContainer.getHeight() && newY >= gameContainer.getPlayerTopLimit()) {
            // Actualizar las coordenadas del héroe si está dentro de los límites
            this.x = newX;
            this.y = newY;
        }
    }
    public void keyPressed1(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int speed = 5; // Velocidad de movimiento

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                moveLeft(speed);
                break;
            case KeyEvent.VK_RIGHT:
                moveRight(speed);
                break;
            case KeyEvent.VK_UP:
                moveUp(speed);
                break;
            case KeyEvent.VK_DOWN:
                moveDown(speed);
                break;
            case KeyEvent.VK_SPACE:
            	 if (!shooting) { // Solo iniciar el disparo si no se está disparando actualmente
                     shooting = true;
                     startShooting(); // Método para iniciar el disparo continuo
                 }
                break;
            default:
                break;
        }
    }
    private void startShooting() {
        Timer shootTimer = new Timer(500, new ActionListener() { // Configura un temporizador para disparar cada 100 milisegundos
            @Override
            public void actionPerformed(ActionEvent e) {
                gameContainer.heroShoot(); // Dispara
            }
        });
        shootTimer.start(); // Inicia el temporizador
    }

    @Override
    public void shoop() {
        int bulletX = this.x + this.width / 2;
        int bulletY = this.y;
        int bulletWidth = 5;
        int bulletHeight = 10;
        int bulletSpeed = 10;
        Color bulletColor = Color.BLACK; // Por ejemplo, color negro

        Bullet bullet = new Bullet(bulletX, bulletY, bulletWidth, bulletHeight, bulletSpeed, bulletColor);

        gameContainer.addBullet(bullet);
    
    }
    public void moveLeft(int speed) {
        x -= speed;
    }

    public void moveRight(int speed) {
        x += speed;
    }

    public void moveUp(int speed) {
        y -= speed;
    }

    public void moveDown(int speed) {
        y += speed;
    }
    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean isdie() {
        return dead;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void decreaseHealth(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    @Override
    public void increaseHealth(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    @Override
    public void setMaxHealth(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    @Override
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void increaseScore(int points) {
        score += points;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
    
 
}
