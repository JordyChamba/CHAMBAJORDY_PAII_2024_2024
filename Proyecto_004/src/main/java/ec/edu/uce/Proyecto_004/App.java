package ec.edu.uce.Proyecto_004;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Model.HibernateStudentRepository;
import Model.Student;
import Model.StudentRepository;

import java.awt.*;


public class App {

    private final StudentRepository studentRepository;

    public App(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        StudentRepository studentRepository = new HibernateStudentRepository();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Estudiantes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(245, 245, 245)); // Color de fondo

            frame.add(panel);

            JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            formPanel.setBackground(Color.white); // Color de fondo

            JLabel ciLabel = new JLabel("CI:");
            JTextField ciField = new JTextField();
            JLabel idLabel = new JLabel("ID:");
            JTextField idField = new JTextField();
            JLabel nameLabel = new JLabel("Nombre:");
            JTextField nameField = new JTextField();
            JLabel lastnameLabel = new JLabel("Apellido:");
            JTextField lastnameField = new JTextField();
            JLabel ageLabel = new JLabel("Edad:");
            JTextField ageField = new JTextField();

            ciLabel.setForeground(new Color(50, 120, 200)); // Color del texto
            idLabel.setForeground(new Color(50, 120, 200)); // Color del texto
            nameLabel.setForeground(new Color(50, 120, 200)); // Color del texto
            lastnameLabel.setForeground(new Color(50, 120, 200)); // Color del texto
            ageLabel.setForeground(new Color(50, 120, 200)); // Color del texto

            formPanel.add(ciLabel);
            formPanel.add(ciField);
            formPanel.add(idLabel);
            formPanel.add(idField);
            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(lastnameLabel);
            formPanel.add(lastnameField);
            formPanel.add(ageLabel);
            formPanel.add(ageField);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPanel.setBackground(new Color(245, 245, 245)); // Color de fondo

            JButton addButton = new JButton("Agregar Estudiante");
            addButton.setBackground(new Color(50, 120, 200)); // Color de fondo del botón
            addButton.setForeground(Color.white); // Color del texto del botón
            addButton.addActionListener(e -> {
                long ci = Long.parseLong(ciField.getText());
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String lastname = lastnameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Student student = new Student(ci, id, name, lastname, age);
                studentRepository.save(student);
                JOptionPane.showMessageDialog(frame, "Estudiante agregado correctamente.");
            });

            JButton deleteButton = new JButton("Eliminar Estudiante");
            deleteButton.setBackground(new Color(50, 120, 200)); // Color de fondo del botón
            deleteButton.setForeground(Color.white); // Color del texto del botón
            deleteButton.addActionListener(e -> {
                long ci = Long.parseLong(ciField.getText());
                studentRepository.delete(ci);
                JOptionPane.showMessageDialog(frame, "Estudiante eliminado correctamente.");
            });

            JButton searchButton = new JButton("Buscar por CI");
            searchButton.setBackground(new Color(50, 120, 200)); // Color de fondo del botón
            searchButton.setForeground(Color.white); // Color del texto del botón
            searchButton.addActionListener(e -> {
                long ci = Long.parseLong(ciField.getText());
                Student student = studentRepository.findByCI(ci);
                if (student != null) {
                    idField.setText(String.valueOf(student.getId()));
                    nameField.setText(student.getName());
                    lastnameField.setText(student.getLastname());
                    ageField.setText(String.valueOf(student.getAge()));
                } else {
                    JOptionPane.showMessageDialog(frame, "No se encontró ningún estudiante con el CI proporcionado.");
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(searchButton);

            panel.add(formPanel, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
