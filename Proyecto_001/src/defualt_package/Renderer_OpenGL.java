package defualt_package;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.jogamp.opengl.*;


public class Renderer_OpenGL extends JFrame {
    private GLJPanel glCanvas;
    private String figuraSeleccionada;

    public Renderer_OpenGL() {
        setTitle("OpenGL Shapes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Botones
        JButton btnCuadrado = new JButton("Cuadrado");
        JButton btnCirculo = new JButton("Círculo");
        JButton btnTriangulo = new JButton("Triángulo");

        // Acciones de los botones
        btnCuadrado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                figuraSeleccionada = "Cuadrado";
                glCanvas.repaint();
            }
        });

        btnCirculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                figuraSeleccionada = "Círculo";
                glCanvas.repaint();
            }
        });

        btnTriangulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                figuraSeleccionada = "Triángulo";
                glCanvas.repaint();
            }
        });

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1));
        panelBotones.add(btnCuadrado);
        panelBotones.add(btnCirculo);
        panelBotones.add(btnTriangulo);

        // Panel de OpenGL
        glCanvas = new GLJPanel(new GLCapabilities(null));
        glCanvas.setPreferredSize(new Dimension(400, 400));

        // Agregar GLEventListener para el dibujo de las figuras
        glCanvas.addGLEventListener(new GLEventListener() {
            public void display(GLAutoDrawable drawable) {
                GL2 gl = drawable.getGL().getGL2();
                drawShape(gl);
            }

            public void dispose(GLAutoDrawable drawable) {}

            public void init(GLAutoDrawable drawable) {}

            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
        });

        // Configuración del layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.WEST);
        getContentPane().add(glCanvas, BorderLayout.CENTER);

        setVisible(true);
    }

    private void drawShape(GL2 gl) {
        if (figuraSeleccionada == null) return;

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        
        if (figuraSeleccionada.equals("Cuadrado")) {
            drawSquare(gl);
        } else if (figuraSeleccionada.equals("Círculo")) {
            drawCircle(gl);
        } else if (figuraSeleccionada.equals("Triángulo")) {
            drawTriangle(gl);
        }
        
        gl.glFlush();
    }

    private void drawSquare(GL2 gl) {
        float[] color = getRandomColor();
        gl.glColor3f(color[0], color[1], color[2]);
        
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.5f, -0.5f);
        gl.glVertex2f(0.5f, -0.5f);
        gl.glVertex2f(0.5f, 0.5f);
        gl.glVertex2f(-0.5f, 0.5f);
        gl.glEnd();
    }

    private void drawCircle(GL2 gl) {
        float[] color = getRandomColor();
        gl.glColor3f(color[0], color[1], color[2]);
        
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(0.0f, 0.0f);
        for (int i = 0; i <= 360; i++) {
            double angle = Math.toRadians(i);
            float x = (float) Math.cos(angle) * 0.5f;
            float y = (float) Math.sin(angle) * 0.5f;
            gl.glVertex2f(x, y);
        }
        gl.glEnd();
    }

    private void drawTriangle(GL2 gl) {
        float[] color = getRandomColor();
        gl.glColor3f(color[0], color[1], color[2]);
        
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(0.0f, 0.5f);
        gl.glVertex2f(-0.5f, -0.5f);
        gl.glVertex2f(0.5f, -0.5f);
        gl.glEnd();
    }

    private float[] getRandomColor() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        return new float[]{r, g, b};
    }

    @Override
    public void paint(Graphics g) {
        // Este método debería estar vacío o ser eliminado para evitar el NullPointerException
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Renderer_OpenGL());
    }
}
