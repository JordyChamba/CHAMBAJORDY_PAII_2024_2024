package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Interface_package.IDieable;
import Interface_package.IDrawable;
import Interface_package.IMovible;

public class Contenedor {
    private static final int HEIGHT = 600;
    private List<IDrawable> drawables;
    private List<IMovible> movables;
    private List<Bullet> bullets;
    private static final int PLAYER_TOP_LIMIT = 2 * HEIGHT / 3 + 21;
    private static final int LINE_Y_POSITION = 2 * HEIGHT / 3;
    private boolean gameOver = false;
    private static final int ENEMY_DAMAGE = 25;
    private Hero hero;
    private int enemyMoveDelay = 500;
    private long lastEnemyMoveTime = 0;

    public Contenedor() {
    	drawables = new ArrayList<>();
        movables = new ArrayList<>();
        bullets = new ArrayList<>();

        hero = new Hero(200, 550, 20, 5, "Jugador 1 ", this);
        addDrawable(hero);
        addMovable(hero);

        // Llama al método createAliens() de la clase Alien para obtener la lista de aliens
        List<Alien> aliens = Alien.createAliens(50, 50, 5, 100, 25, 25, 25);
        for (Alien alien : aliens) {
            addDrawable(alien); // Agrega cada alien a la lista de drawables
            addMovable(alien); // Agrega cada alien a la lista de movables
        }
    }

   

    public void addDrawable(IDrawable drawable) {
        drawables.add(drawable);
    }

    public void addMovable(IMovible movable) {
        movables.add(movable);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void update() {
        List<Bullet> balasAEliminar = new ArrayList<>(); // Lista para almacenar las balas que necesitan ser eliminadas
        List<Alien> enemigosAEliminar = new ArrayList<>(); // Lista para almacenar los enemigos que necesitan ser eliminados

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.move(0, -bullet.getSpeed());
            if (bullet.getY() == HEIGHT) {
                bulletIterator.remove();
            } else {
                for (IDrawable drawable : drawables) {
                    if (drawable instanceof Alien && bullet.haColisionado((Alien) drawable)) {
                        bulletIterator.remove();
                        balasAEliminar.add(bullet);
                        enemigosAEliminar.add((Alien) drawable);
                        hero.increaseScore(25);
                        bullet.setHaColisionado(true); // Marcar la bala como colisionada
                        break;
                    }
                }
            }
        }


        // Eliminar las balas que necesitan ser eliminadas
        bullets.removeAll(balasAEliminar);

        // Eliminar los enemigos que necesitan ser eliminados
        for (Alien enemy : enemigosAEliminar) {
            drawables.remove(enemy);
            movables.remove(enemy);
            // Incrementar el puntaje en 25 puntos por cada enemigo eliminado
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastEnemyMoveTime > enemyMoveDelay) {
            moveEnemiesDown();
            lastEnemyMoveTime = currentTime;
        }
        for (IMovible movable : movables) {
            if (movable instanceof Alien) {
                Alien enemy = (Alien) movable;
                if (enemy.getY() >= LINE_Y_POSITION) {
                    hero.decreaseHealth(ENEMY_DAMAGE);
                }
            }
        }

        if (hero.getCurrentHealth() <= 0) {
            gameOver = true;
        }

        for (IMovible movable : movables) {
            movable.move(0, 0);
        }

        // Eliminar las entidades muertas
        Iterator<IDrawable> drawableIterator = drawables.iterator();
        while (drawableIterator.hasNext()) {
            IDrawable drawable = drawableIterator.next();
            if (drawable instanceof IDieable) {
                IDieable morible = (IDieable) drawable;
                if (morible.isdie()) {
                    drawableIterator.remove();
                }
            }
        }

        // Verificar si no hay más enemigos
        if (drawables.isEmpty()) {
            gameOver = true;
        }
    }

    public void dibujar(Graphics g) {
        // Dibujar la línea divisoria
        Limit line = new Limit(0, LINE_Y_POSITION, 800, LINE_Y_POSITION);
        line.draw(g);

        // Dibujar las entidades del juego
        for (IDrawable drawable : drawables) {
            drawable.draw(g);
        }

        // Dibujar la barra de vida
        int barWidth = 100;
        int barHeight = 10;
        int barX = 10;
        int barY = 60;
        double healthPercentage = (double) hero.getCurrentHealth() / hero.getMaxHealth();

        // Dibujar el contorno de la barra de vida
        g.setColor(Color.BLACK);
        g.drawRect(barX, barY, barWidth, barHeight);

        // Dibujar el interior de la barra de vida
        g.setColor(Color.RED);
        g.fillRect(barX, barY, (int) (barWidth * healthPercentage), barHeight);

        // Mostrar "FELICIDADES" si el puntaje del héroe alcanza 125
        if (hero.getScore() >= 125) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("FELICIDADES", 300, 300);
        } else if (gameOver) { // Si no se alcanzó el puntaje de 125 y el juego terminó, mostrar "GAME OVER"
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER", 300, 300);
        }
    }

    private void moveEnemiesDown() {
        for (IMovible movable : movables) {
            if (movable instanceof Alien) {
                movable.move(0, 1); // Mover hacia abajo
            }
        }
    }
    public void heroShoot() {
        if (!gameOver && hero.getScore() < 125) {
            int heroX = hero.getX();
            int heroY = hero.getY();
            Color bulletColor = Color.BLACK; // Por ejemplo, color negro

            Bullet bullet = new Bullet((heroX + hero.getWidth() / 2) - 27, heroY - 15, 5, 10, 10, bulletColor);
            addBullet(bullet);
            addDrawable(bullet);
        }
    }


    public void moveHero(int dx, int dy) {
        hero.move(dx, dy);
    }
    
    public static int getHeight() {
        return HEIGHT;
    }

    public static int getPlayerTopLimit() {
        return PLAYER_TOP_LIMIT;
    }
    
    public Hero getHero() {
        return hero;
    }
}
