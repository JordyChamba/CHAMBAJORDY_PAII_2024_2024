package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Interface_package.IDrawable;
import Interface_package.IDieable;
import Interface_package.IMovible;
import Interface_package.IScore;

public class Alien implements IDrawable, IMovible, IDieable, IScore {
    private int x, y;
    private int width, height;
    private boolean dead = false;
    private int score;
    private double velocidadX = 0.1; // Velocidad horizontal más lenta

    public Alien(int x, int y, int width, int height, int score) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = score;
    }
    
    public static List<Alien> createAliens(int startX, int startY, int numEnemies, int gapX, int enemyWidth, int enemyHeight, int score) {
        List<Alien> aliens = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) {
            aliens.add(new Alien(startX + i * gapX, startY, enemyWidth, enemyHeight, score));
        }
        return aliens;
    }

    @Override
    public void draw(Graphics g) {
        // Aumentar el tamaño del cuerpo del alien (triángulo)
        int bodyWidth = width * 3 / 2; // Aumenta el ancho del cuerpo
        int bodyHeight = height * 3 / 2; // Aumenta la altura del cuerpo

        // Dibujar el cuerpo del alien (triángulo)
        g.setColor(Color.GREEN);
        g.fillPolygon(new int[] { x, x + bodyWidth / 2, x + bodyWidth }, new int[] { y + bodyHeight, y, y + bodyHeight }, 3);

        // Aumentar el tamaño de los ojos del alien (círculos)
        int eyeSize = width / 2; // Aumenta el tamaño de los ojos

        // Dibujar los ojos del alien (círculos)
        g.setColor(Color.WHITE);
        g.fillOval(x + bodyWidth / 4 - eyeSize / 2, y + bodyHeight / 4 - eyeSize / 2, eyeSize, eyeSize); // Ojo izquierdo
        g.fillOval(x + bodyWidth / 2 - eyeSize / 2, y + bodyHeight / 4 - eyeSize / 2, eyeSize, eyeSize); // Ojo derecho

        // Aumentar el tamaño de la boca del alien (línea)
        int mouthWidth = bodyWidth / 2; // Aumenta el ancho de la boca

        // Dibujar la boca del alien (línea)
        g.setColor(Color.RED);
        g.drawLine(x + bodyWidth / 4, y + bodyHeight * 3 / 4, x + bodyWidth / 4 + mouthWidth, y + bodyHeight * 3 / 4);
    }


    
    private List<Alien> createAliens() {
        int enemyWidth = 25;
        int enemyHeight = 25;
        int startXTop = 50;
        int startYTop = 50;
        int gapX = 100;
        int numEnemies = 5; // Reduce el número total de enemigos a la mitad

        List<Alien> aliens = new ArrayList<>();

        // Crear enemigos en la parte superior
        for (int i = 0; i < numEnemies; i++) {
            Alien enemy = new Alien(startXTop + i * gapX, startYTop, enemyWidth, enemyHeight, 25);
            aliens.add(enemy);
        }

        return aliens;
    }


    @Override
    public void move(int dx, int dy) {
        // Ignorar los parámetros dx y dy, ya que no los usaremos para mover de manera predeterminada

        // Actualizar la posición horizontal de los enemigos
        x += velocidadX;

        // Verificar los límites de la pantalla en el eje x
        if (x <= 0 || x + width >= getWidth()) {
            // Cambiar la dirección si se alcanzan los límites
            velocidadX *= 1;
            // Mover los enemigos hacia abajo
            y += 1; // Ajusta la velocidad vertical aquí
        }

        // Actualizar la posición vertical de los enemigos (para bajarlos lentamente)
        if (y <= getHeight() - 520) {
            y += 1; // Incrementar la posición en y gradualmente
        }

    }

    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean isdie() {
        return dead;
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
    public int getScore() {
        return score;
    }

    @Override
    public void increaseScore(int points) {
        if (!isdie()) {
            score += points;
            die();
        }
    }
    public boolean haColisionado(Bullet bullet) {
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        Rectangle enemyRect = new Rectangle(x, y, width, height);
        return bulletRect.intersects(enemyRect);
    }
}
