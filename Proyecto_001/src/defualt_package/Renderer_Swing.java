package defualt_package;

/*
 * @author: Jordy_Chamba
 * Titulo: Contenedores
 * Deber1
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Renderer_Swing extends JFrame implements ActionListener {
    private JButton btnCirculo, btnCuadrado, btnTriangulo;
    private JPanel panelDibujo;
    private String figuraSeleccionada;

    public Renderer_Swing() {
        setTitle("Renderer Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear botones
        btnCirculo = new JButton("Círculo");
        btnCuadrado = new JButton("Cuadrado");
        btnTriangulo = new JButton("Triángulo");

        // Agregar ActionListener a los botones
        btnCirculo.addActionListener(this);
        btnCuadrado.addActionListener(this);
        btnTriangulo.addActionListener(this);

        // Panel para dibujo
        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar el objeto seleccionado
                if (figuraSeleccionada != null) {
                    dibujarFigura(g, figuraSeleccionada);
                }
            }
        };
        panelDibujo.setPreferredSize(new Dimension(300, 300));

        // Configurar diseño de la ventana
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCirculo);
        panelBotones.add(btnCuadrado);
        panelBotones.add(btnTriangulo);

        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.NORTH);
        add(panelDibujo, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCirculo) {
            // Marcar el círculo como seleccionado
            figuraSeleccionada = "Círculo";
        } else if (e.getSource() == btnCuadrado) {
            // Marcar el cuadrado como seleccionado
            figuraSeleccionada = "Cuadrado";
        } else if (e.getSource() == btnTriangulo) {
            // Marcar el triángulo como seleccionado
            figuraSeleccionada = "Triángulo";
        }
        // Repintar el panel de dibujo para mostrar la figura seleccionada
        panelDibujo.repaint();
    }

    private void dibujarFigura(Graphics g, String figura) {
        // Dibujar la figura seleccionada
        if (figura.equals("Círculo")) {
            g.setColor(Color.RED);
            g.fillOval(100, 100, 100, 100); // Dibujar un círculo
        } else if (figura.equals("Cuadrado")) {
            g.setColor(Color.BLUE);
            g.fillRect(100, 100, 100, 100); // Dibujar un cuadrado
        } else if (figura.equals("Triángulo")) {
            g.setColor(Color.GREEN);
            int[] xPoints = {150, 100, 200};
            int[] yPoints = {100, 200, 200};
            g.fillPolygon(xPoints, yPoints, 3); // Dibujar un triángulo
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Renderer_Swing());
    }
}
