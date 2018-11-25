package uet.oop.bomberman.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá
     * trị kí tự đ�?c được từ ma trận bản đồ trong tệp
     * cấu hình
     */
    private static char[][] _map;

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) {
//         TODO: đ�?c dữ liệu từ tệp cấu hình /levels/Level{level}.txt
//         TODO: cập nhật các giá trị đ�?c được vào _width, _height, _level, _map
//        _height=20;
//        _width=30;
//        _map = new char[20][20];
//        _map[1][1] = 'p';
//        _map[5][5] = '1';
//        _map[10][10] = '*';
//        _map[5][6] = 'x';
//        _map[7][8] = '2';
//        _map[2][3]='b';
//        _map[4][5]='f';
//        _map[6][6]='s';
//        _map[10][10]='#';

        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-project-1\\bomberman-starter-starter-project-1\\res\\levels\\Level1.txt");
            br = new BufferedReader(fr);
            String currentLines;
            currentLines = br.readLine();
            StringTokenizer st = new StringTokenizer(currentLines);
            _level = Integer.parseInt(st.nextToken());
            _height = Integer.parseInt(st.nextToken());
            _width = Integer.parseInt(st.nextToken());
            _map = new char[_height][_width];

            for (int i = 0; i < _height; i++) {
                currentLines = br.readLine();
                for (int j = 0; j < _width; j++) {
                    _map[i][j] = currentLines.charAt(j);
                }
            }

        } catch (Exception e) {
        }
//        _width=31;
    }

    @Override
    public void createEntities() {
        // TODO: t?o c�c Entity c?a m�n ch?i
        // TODO: sau khi t?o xong, g?i _board.addEntity() ?? th�m Entity v�o game

        // TODO: ph?n code m?u ? d??i ?? h??ng d?n c�ch th�m c�c lo?i Entity v�o game
        // TODO: h�y x�a n� khi ho�n th�nh ch?c n?ng load m�n ch?i t? t?p c?u h�nh
        // th�m Wall
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                int pos = x + y * _width;
                switch (_map[y][x]) {
                    case '#': {
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall));
                        
                        break;
                    }
                    case 'p': {
                        _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        Screen.setOffset(0, 0);
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    }
                    case '1': {
                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        Screen.setOffset(0, 0);
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    }
                    case '*': {
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
                        break;
                    }
                    case 'x': {
                        _board.addEntity(pos, new LayeredEntity(x,y, new Portal(x, y, Sprite.portal,_board),new Brick(x, y, Sprite.brick)));
                        break;
                    }
                    case '2': {
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    }
                    case 'b': {
                        _board.addEntity(pos,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new BombItem(x, y, Sprite.powerup_bombs),
                                        new Brick(x, y, Sprite.brick)
                                )
                        );
                        break;
                    }
                    case 'f' : {
                        _board.addEntity(pos,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new FlameItem(x, y, Sprite.powerup_flames),
                                        new Brick(x, y, Sprite.brick)
                                )
                        );
                        break;
                    }
                    case 's' : {
                        _board.addEntity(pos,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new SpeedItem(x, y, Sprite.powerup_speed),
                                        new Brick(x, y, Sprite.brick)
                                )
                        );
                        break;
                    }
                    default: 
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                }
            }
        }

        // thêm Bomber
//        int xBomber = 1, yBomber = 1;
//        _board.addCharacter(new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board));
//        Screen.setOffset(0, 0);
//        _board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//        
//
//        // thêm Enemy
//        int xE = 2, yE = 1;
//        _board.addCharacter(new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//        _board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//        // thêm Brick
//        int xB = 3, yB = 1;
//        _board.addEntity(xB + yB * _width,
//                new LayeredEntity(xB, yB,
//                        new Grass(xB, yB, Sprite.grass),
//                        new Brick(xB, yB, Sprite.brick)
//                )
//        );
//
//        // thêm Item kèm Brick che phủ ở trên
//        int xI = 1, yI = 2;
//        _board.addEntity(xI + yI * _width,
//                new LayeredEntity(xI, yI,
//                        new Grass(xI, yI, Sprite.grass),
//                        new SpeedItem(xI, yI, Sprite.powerup_flames),
//                        new Brick(xI, yI, Sprite.brick)
//                )
//        );
//    }
    }
}
