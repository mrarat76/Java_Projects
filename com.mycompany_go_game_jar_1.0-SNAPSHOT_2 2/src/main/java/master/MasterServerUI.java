/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mehdiarat
 */
package master;

import javax.swing.*;
import java.awt.*;

public class MasterServerUI extends JFrame {
    private JTextArea logArea;

    public MasterServerUI() {
        setTitle("Master Server Log");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void log(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
