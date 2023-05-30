package manage;

import model.Bot;
import model.Bullet;
import model.Map;
import model.Player;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private Player player;
    private Bot bot;
    ArrayList<Bot> listBot;
    private ArrayList<Bullet> playerBullet;
    private ArrayList<Bullet> botBullet;
    private ArrayList<Map> maps;
    private Random random;

    public void initGameManager() {
        player = new Player(300, 430);
        listBot = new ArrayList<>();
        playerBullet = new ArrayList<>();
        botBullet = new ArrayList<>();
        maps = new ArrayList<>();
        readMap("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\map\\map.txt");
        for (int i = 0; i < 3; i ++){
            if (i == 0) {
                bot = new Bot(0, 0);
            }
            if (i == 1){
                bot = new Bot(510 - 33, 0);
            }
            if (i == 2){
                bot = new Bot((510 - 33)/2, 0);
            }
            listBot.add(bot);
        }
        random = new Random();
    }
    private void readMap(String path){
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int row = 0;
            while (line != null){
                for (int i = 0; i < line.length(); i++){
                    int bit = Integer.parseInt(line.charAt(i) + "");
                    Map map = new Map(i * 19,row * 19, bit );
                    maps.add(map);
                }
                line = bufferedReader.readLine();
                row++;
            }
            bufferedReader.close();
            fileReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void draw(Graphics2D g2d) {
        try {
            player.draw(g2d);
            for (Bullet bullet : playerBullet){
                bullet.draw(g2d);
            }
            for (Bullet bullet : botBullet){
                bullet.draw(g2d);
            }
            for (int i = 0; i < listBot.size(); i++) {
                bot = listBot.get(i);
                bot.draw(g2d);
            }
            for (Map map : maps) {
                map.draw(g2d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playerMove(int newOrient) {

        player.changeOrient(newOrient);

        player.move(maps);
    }
    public void playerFire(){
        Bullet bullet = player.fire();
        playerBullet.add(bullet);
    }
    public void botFire(){
        for (int i = 0; i < listBot.size(); i++){
            bot = listBot.get(i);
            Bullet bullet = bot.fire();
            botBullet.add(bullet);
        }
    }
    public boolean Auto(){
        botMove();
        for (int i = playerBullet.size() -1; i >= 0; i--){
            boolean check = playerBullet.get(i).move();
            if (check) {
                playerBullet.remove(i);
            }
        }

        for (Map map : maps){
            boolean check = map.checkBulletPlayer(playerBullet);
            if (!check){
                return false;
            }
        }
        for (Map map : maps){
            boolean check = map.checkBulletBot(botBullet);
            if (!check){
                return false;
            }
        }
        for (int i = botBullet.size() - 1; i >= 0; i--) {
            boolean check = botBullet.get(i).move();
            if (check) {
                botBullet.remove(i);
            }
        }
        for (int i = listBot.size() - 1; i >= 0; i--){
            boolean check = listBot.get(i).checkDie(playerBullet);
            if (!check){
                listBot.remove(i);
            }
        }
            boolean check = player.checkDie(botBullet);
            if (!check){
                return false;
            }
            return true;
    }


    public void botMove() {
        for (int i = 0; i < listBot.size(); i++) {
            int percent = random.nextInt(201);
            if ( percent > 190){
                int newOrient = random.nextInt(4);
                listBot.get(i).changeOrient(newOrient);
            }
            listBot.get(i).move(maps);
        }
        if (listBot.size() <= 2){
            Bot newBot = new Bot(0,0);
            listBot.add(newBot);
        }
        }
    }

