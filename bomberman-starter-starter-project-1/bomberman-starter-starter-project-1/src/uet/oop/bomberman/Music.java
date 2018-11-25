/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman;

/**
 *
 * @author Admin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.*;
public class Music {
    
    
    public static void themeSong() throws FileNotFoundException, IOException
    {
        InputStream music = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-project-1\\bomberman-starter-starter-project-1\\res\\sound\\themesound.wav"));
        AudioStream audios = new AudioStream(music);
        AudioPlayer.player.start(audios);
    }
    public static void explodeSong() throws FileNotFoundException, IOException
    {
        InputStream bomb = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-project-1\\bomberman-starter-starter-project-1\\res\\sound\\bomb_bang.wav"));
        AudioStream bomb_bang = new AudioStream(bomb);
        AudioPlayer.player.start(bomb_bang);
    }
    public static void bomberdieSong() throws FileNotFoundException, IOException
    {
        InputStream bomber = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-project-1\\bomberman-starter-starter-project-1\\res\\sound\\bomber_die.wav"));
        AudioStream bomber_die = new AudioStream(bomber);
        AudioPlayer.player.start(bomber_die);
    }
    public static void monsterdieSong() throws FileNotFoundException, IOException
    {
        InputStream monster = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-project-1\\bomberman-starter-starter-project-1\\res\\sound\\monster_die.wav"));
        AudioStream monster_die = new AudioStream(monster);
        AudioPlayer.player.start(monster_die);
    }
}
