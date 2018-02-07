package com.thom9521.mario;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.FXGLExceptionHandler;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsControl;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.Position;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.inc;


public class MarioApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(15 * 70);
        settings.setHeight(10 * 70);
    }

    private Entity player;
    Point2D despawn = new Point2D(50, 600);
    Point2D despawn4 = new Point2D(70, 235);

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.UP);
    }

    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("mario3.json");
        getAudioPlayer().playSound("themesong.mp3");
        //getGameScene().setBackgroundRepeat("mountains.jpg");
        player = getGameWorld().spawn("player", 70, 600);
        getGameScene().getViewport().setBounds(-1500, 0, 3000, getHeight());
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2);

        getGameWorld().spawn("enemy", 390, 240);



    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                getGameState().increment("score",+50);
                getAudioPlayer().playSound("coin.wav");
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                getDisplay().showMessageBox("Level 1 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                    getAudioPlayer().playSound("1-up.wav");

                    player.getWorld().setLevelFromMap("mario2.json");
                     player.getWorld().spawn("player",90,900);
                    getGameWorld().spawn("enemy", 1050, 50);
                    getGameWorld().spawn("enemy", 1485, 50);
                    player.getControl(PhysicsControl.class).reposition(despawn);


                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR2) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door2) {
                getDisplay().showMessageBox("Level 2 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                    getAudioPlayer().playSound("1-up.wav");
                    player.getWorld().setLevelFromMap("mario3.json");
                    player.getControl(PhysicsControl.class).reposition(despawn);
                    getGameWorld().spawn("enemy", 290, 600);



                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR3) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door3) {
                getDisplay().showMessageBox("Level 3 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                    getAudioPlayer().playSound("1-up.wav");
                    player.getWorld().setLevelFromMap("mario4.json");
                    getGameScene().getViewport().setBounds(-1500, 0, 3000, 1050);
                    getGameWorld().spawn("enemy3", 450, 235);
                    getGameWorld().spawn("enemy3",570, 235);
                    getGameWorld().spawn("enemy2", 600, 400);
                    getGameWorld().spawn("enemy2", 900, 600);
                    getGameWorld().spawn("enemy3", 1600, 450);
                    getGameWorld().spawn("enemy3", 1500, 450);
                    getGameWorld().spawn("enemy3", 1400, 450);
                    player.getControl(PhysicsControl.class).reposition(despawn4);


                });

            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR4) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door4) {
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("dothemario.mp3");
                getDisplay().showMessageBox("THE END", () -> {
                    System.out.println("Dialog Closed!");
                });
                getDisplay().showMessageBox("Level 4 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getDisplay().showMessageBox("Game Over!", () -> {
                    player.getWorld();
                    player.getControl(PhysicsControl.class).reposition(despawn);
                    getAudioPlayer().playSound("themesong.mp3");

                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY2) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy2) {


                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getDisplay().showMessageBox("Game Over!", () -> {
                    // player.getWorld().setLevelFromMap("mario.json");
                    player.getWorld();
                    // enemy.removeFromWorld();
                    //getGameWorld().spawn("enemy", 650, 50);
                    player.getControl(PhysicsControl.class).reposition(despawn4);
                    getAudioPlayer().playSound("themesong.mp3");

                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY3) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy3) {
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getDisplay().showMessageBox("Game Over!", () -> {
                    player.getWorld();
                    player.getControl(PhysicsControl.class).reposition(despawn4);
                    getAudioPlayer().playSound("themesong.mp3");

                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DANGER) {
            @Override
            protected void onCollisionBegin(Entity player, Entity danger) {


                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getDisplay().showMessageBox("Game Over!", () -> {
                    player.getWorld();
                   // getGameWorld().spawn("enemy", 650, 50);
                    player.getControl(PhysicsControl.class).reposition(despawn);
                    getAudioPlayer().playSound("themesong.mp3");

                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DANGER2) {
            @Override
            protected void onCollisionBegin(Entity player, Entity danger2) {


                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getDisplay().showMessageBox("Game Over!", () -> {
                    player.getWorld();
                    // getGameWorld().spawn("enemy", 650, 50);
                    player.getControl(PhysicsControl.class).reposition(despawn4);
                    getAudioPlayer().playSound("themesong.mp3");

                });

            }
        });


    }

    @Override
    protected void initUI() {
        Text uiScore = getUIFactory().newText("",50);
        uiScore.setTranslateX(getWidth()-1000);
        uiScore.setTranslateY(50);
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());

        getGameScene().addUINode(uiScore);
    }
      @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("stageColor", Color.GOLD);
        vars.put("score", 0);
    }


    public static void main(String[] args) {
        launch(args);

    }
}
