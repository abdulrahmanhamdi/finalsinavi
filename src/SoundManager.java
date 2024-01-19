package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {

    private static final String BACKGROUND_MUSIC = "src/resources/skulls_adventure_1.wav";
    private static final String GAME_OVER_SOUND = "src/resources/gameOver.wav";
    private static final String VICTORY_SOUND = "src/resources/youWin.wav";

    private static Clip backgroundMusicClip;

    public static void startBackgroundMusic() {
        try {
            File audioFile = new File(BACKGROUND_MUSIC);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioStream);

            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
    }

    public static void playGameOverSound() {
        playSound(GAME_OVER_SOUND);
    }

    public static void playVictorySound() {
        playSound(VICTORY_SOUND);
    }

    private static void playSound(String soundFile) {
        try {
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
