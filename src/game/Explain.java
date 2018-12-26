package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import sun.applet.Main;

public class Explain extends JPanel {

    Graphics2D ctx;
    KeySelectMenu key;
    static Audio sound;
    ImageIcon img;
    Image image;

    public Explain(JFrame w) {
        img = new ImageIcon(getClass().getResource("/res/NES - Battle City - Miscellaneous.png"));
        image = img.getImage();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        ImgMenu.sound.stopSound();
                        w.setVisible(false);
                        JFrame menu = new JFrame();
                        menu.setTitle("Battle City");
                        menu.setSize(1000, 675);
                        menu.setResizable(false);
                        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        //menu.setLocationRelativeTo(null);
                        menu.setLocation(150, 20);
                        menu.add(new ImgMenu(menu));
                        menu.setVisible(true);
                        break;
                }
            }
        });
        setFocusable(true);
        this.setBackground(Color.black);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ctx = (Graphics2D) g;
        ctx.setColor(Color.white);
        ctx.setFont(new Font("tahoma", 1, 20));//
        ctx.drawString("การควบคุมตัวละคร ", 50, 50);
        ctx.drawString("- Player1 ควบคุมโดยกดปุ่ม ลูกศรขึ้น ลง ซ้าย ขวา เเละ กดปุ่ม Enter เพื่อทำการยิง ", 50, 120);
        ctx.drawString("- Player2 ควบคุมโดยกดปุ่ม W S A D เเละ กดปุ่ม Space bar เพื่อทำการยิง", 50, 190);
        ctx.drawString("เงื่อนไขในการชนะเกม ", 50, 260);
        ctx.drawString("- ผู้เล่นที่สามารถทำลายอีกฝ่ายได้ก่อนจะเป็นผู้ชนะทันที ", 50, 310);
        ctx.drawString("- ในเกมจะมีไอเทม 1 ชิ้น(1ชิ้นเท่านั้น) ซึ่งจะปรากฏอยู่ที่จุดกลางของเเผนที่", 50, 380);
        ctx.drawString("  ผู้เล่นที่สามารถเก็บได้จะได้รับความสามารถพิเศษที่ช่วยให้ได้เปรียบอย่างมาก ", 50, 450);
        ctx.drawString("Back to menu", 375 + 50, 520);
        ctx.drawImage(image, 254 + 50, 485, 254 + 100, 485 + 40, 65, 125, 76, 138, null);
    }

}
