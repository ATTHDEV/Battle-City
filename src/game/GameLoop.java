/*

 */
package game;

import map.Map;
import character.PlayerAttack;
import character.Player;
import character.Player2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author AtthawutCPE
 */
public class GameLoop extends JPanel {

    Graphics2D pDC;
    Player player;
    Player2 player2;
    Map map;
    public static boolean game;
    int confirm;
    Audio sound;

    public GameLoop(JFrame window) {

        map = new Map();
        player = new Player(25 * Map.TILE_WIDTH, 27 * Map.TILE_HEIGHT);
        player2 = new Player2(25 * Map.TILE_WIDTH, 1 * Map.TILE_HEIGHT);
        this.addKeyListener(new KeyInput(player, player2));
        this.setFocusable(true);
        setBackground(Color.black);
        GameLoop.game = true;
        sound = new Audio();
        sound.blackGroundSound("wariobattles1and2.mid", 2 * 60 * 1000 + 17000);
        Thread Game_Loop = new Thread(() -> {
            try {
                while (GameLoop.game) {
                    Thread.sleep(8);
                    repaint();
                }
                if (player.life == false) {
                    confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Player2 ชนะ! ต้องการเล่นอีกครั้ง? ",
                            "Exit confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE);
                    if (confirm == 0) {
                        sound.stopSound();
                        window.setVisible(false);
                        JFrame window_new = new JFrame();
                        window_new.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        //dialog.setLocationRelativeTo(null);
                        //window_new.setLocationRelativeTo(null);;
                        window_new.setLocation(150, 20);
                        window_new.add(new GameLoop(window));
                        window_new.setSize(1000, 675);
                        window_new.setResizable(false);
                        window_new.setVisible(true);
                        player.life=true;
                    } else {
                        this.setVisible(false);
                        System.exit(0);
                    }
                } else if (player2.life == false) {
                    confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Player1 ชนะ! ต้องการเล่นอีกครั้ง? ",
                            "Exit confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE);
                    if (confirm == 0) {
                        sound.stopSound();
                        window.setVisible(false);
                        JFrame window_new = new JFrame();
                        window_new.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        //dialog.setLocationRelativeTo(null);
                        //window_new.setLocationRelativeTo(null);
                        window_new.setLocation(150, 20);
                        window_new.add(new GameLoop(window_new));
                        window_new.setSize(1000, 675);
                        window_new.setResizable(false);
                        window_new.setVisible(true);
                        player2.life=true;
    
                    } else {
                        this.setVisible(false);
                        System.exit(0);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Game_Loop.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pDC = (Graphics2D) g;
        player.draw(pDC);
        player.Update(map, pDC, player2);
        player2.draw(pDC);
        player2.Update(map, pDC, player);
        map.createMap(pDC);
    }

}
