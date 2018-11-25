package uet.oop.bomberman;

import java.io.File;
import java.io.FileInputStream;
import sun.audio.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import uet.oop.bomberman.gui.Frame;

public class BombermanGame {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
                Music.themeSong();
		new Frame();
	}
}
