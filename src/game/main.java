/*
    
 */
package game;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author AtthawutCPE
 */
public class main extends JFrame {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Battle City");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        window.setLocation(150, 20);
        window.add(new GameLoop(window));
        window.setSize(1000,675);
        window.setResizable(false);
        window.setVisible(true);
    }

}
