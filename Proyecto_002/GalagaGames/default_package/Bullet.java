package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Interface_package.IDrawable;
import Interface_package.IMovible;

public class Bullet implements IDrawable, IMovible {
	private int x, y; 
    private int width, height; 
    private int speed;
    private Color color;
    private boolean haColisionado = false; // Nuevo atributo para indicar si la bala ha colisionado
    
    public Bullet(int x, int y, int width, int height, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color= color; 

    }

	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		 this.x += dx;
	        this.y += dy;
	}

	@Override
	public void draw(Graphics g) {
	    if (haColisionado) {
	        g.setColor(Color.BLACK); // Bala que ha colisionado con un enemigo
	    } else {
	        g.setColor(Color.WHITE); // Bala normal
	    }
	    g.fillRect(x, y, width, height); // Dibujar la bala con el color correspondiente
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

	    public int getSpeed() {
	        return speed;
	    }
	    
	    public boolean haColisionado(Alien alien) {
	        // Verificar si la bala ha colisionado con el Alien
	        Rectangle bulletRect = new Rectangle(x, y, width, height);
	        Rectangle alienRect = new Rectangle(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
	        return bulletRect.intersects(alienRect);
	    }

	    public void setHaColisionado(boolean haColisionado) {
	        this.haColisionado = haColisionado;
	    }
	    public void setColor(Color color) {
	        this.color = color;
	    }

}