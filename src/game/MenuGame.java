package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import sun.applet.Main;

public class MenuGame extends JFrame {

    public static void main(String[] args) {
        JFrame Menu = new JFrame();
        Menu.setTitle("Battle City");
        Menu.setSize(1000, 675);
        Menu.setResizable(false);
        Menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Menu.setLocationRelativeTo(null);
        Menu.add(new ImgMenu(Menu));
        Menu.setVisible(true);
    }
}

class ImgMenu extends JPanel {

    ImageIcon img;
    Image image;
    ImageIcon img2;
    Image image2;
    public static int y = 360;
    Graphics2D ctx;
    KeySelectMenu key;
    static Audio sound;

    public ImgMenu(JFrame Menu) {
        img = new ImageIcon(getClass().getResource("/res/NES - Battle City - Miscellaneous.png"));
        image = img.getImage();
        img2 = new ImageIcon(getClass().getResource("/res/NES - Battle City - Miscellaneous.png"));
        image2 = img.getImage();
        key = new KeySelectMenu(Menu);
        addKeyListener(key);
        setFocusable(true);
        sound = new Audio();
        sound.blackGroundSound("bonus.mid", 52 * 1000);
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImgMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        t.start();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ctx = (Graphics2D) g;
        ctx.drawImage(image, 0, 0, 995, 646, 0, 0, 255, 224, null);
        ctx.setColor(Color.black);
        ctx.fillRect(305, 350, 450, 200);
        ctx.setColor(Color.white);
        ctx.setFont(new Font("Courier New", 1, 50));
        ctx.drawString("Start game", 375, 390);
        ctx.drawString("How to play", 375, 450);
        ctx.setFont(new Font("Courier New", 1, 40));
        ctx.drawString("Developed by Atthawut Phuangsiri CPE", 80, 510);
        key.draw(ctx, image2);
    }
}

class KeySelectMenu extends KeyAdapter {

    JFrame Menu;

    public KeySelectMenu(JFrame Menu) {
        this.Menu = Menu;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                ImgMenu.y = 360;
                break;
            case KeyEvent.VK_DOWN:
                ImgMenu.y = 420;
                break;
            case KeyEvent.VK_ENTER:
                if (ImgMenu.y == 360) {
                    JFrame window = new JFrame();
                    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    // window.setLocationRelativeTo(null);
                    window.setLocation(150, 20);
                    window.setTitle("Battle City");
                    window.add(new GameLoop(window));
                    window.setSize(1000, 675);
                    window.setResizable(false);
                    Menu.setVisible(false);
                    ImgMenu.sound.stopSound();
                    window.setVisible(true);
                } else if (ImgMenu.y == 420) {
                    JFrame window2 = new JFrame();
                    //window2.setLocationRelativeTo(null);
                    window2.setLocation(150, 20);
                    window2.setTitle("Battle City");
                    window2.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    window2.add(new Explain(window2));
                    window2.setSize(1000, 675);
                    window2.setResizable(false);
                    Menu.setVisible(false);
                    window2.setVisible(true);
                }
                break;
        }
    }

    public void draw(Graphics2D ctx, Image image2) {
        ctx.setColor(Color.black);
        ctx.fillRect(254, 350, 50, 50);
        ctx.drawImage(image2, 254, ImgMenu.y, 254 + 50, ImgMenu.y + 40, 65, 125, 76, 138, null);
    }
}
