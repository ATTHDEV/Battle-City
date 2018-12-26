/*
     จัดการเสียงภายในเกม
 */
package game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class Audio {

    // public static Sound sound1 = new Sound("/sound1.wav");
    /*public static void main(String[] args) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(Audio.class.getResource("42PS_00003.wav"));
            clip.open(ais);
            clip.start();
            clip.loop(10);
            Thread.sleep(300);
            clip.close();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     */
    Clip clip1;
    public Audio() {
    }

    public Audio(String file, int time) {
        Thread t = new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(Audio.class.getResource("/res/"+file));
                clip.open(ais);
                clip.start();
                clip.loop(time);
                Thread.sleep(time);
                clip.close();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {

            }
        });
        t.start();
    }

    public void blackGroundSound(String file, int time) {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    clip1 = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(Audio.class.getResource("/res/"+file));
                    clip1.open(ais);
                    clip1.start();
                    clip1.loop(time);
                    Thread.sleep(time);
                    clip1.close();
                }
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {

            }
        });
        t.start();

    }

    public void stopSound() {
        clip1.stop();
        clip1.close();
    }

}
