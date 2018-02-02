package com.thom9521.mario;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.FXGLExceptionHandler;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsControl;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.Position;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.sql.SQLOutput;


public class MarioApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(15 * 70);
        settings.setHeight(10 * 70);
    }

    private Entity player;
    Point2D despawn = new Point2D(50, 600);

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
        getGameWorld().setLevelFromMap("mario.json");
        getAudioPlayer().playSound("themesong.mp3");
        player = getGameWorld().spawn("player", 50, 600);
        //minY er højden
        getGameScene().getViewport().setBounds(-1500, 0, 3000, getHeight());
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2);

        getGameWorld().spawn("enemy", 650, 50);


    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
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
                    // player.getWorld().spawn("player",50,600);

                    player.getControl(PhysicsControl.class).reposition(despawn);


                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {


                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getDisplay().showMessageBox("Game Over!", () -> {
                    player.getWorld().setLevelFromMap("mario.json");
                    getGameWorld().spawn("enemy", 650, 50);
                    player.getControl(PhysicsControl.class).reposition(despawn);
                    getAudioPlayer().playSound("themesong.mp3");

                });

            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}
