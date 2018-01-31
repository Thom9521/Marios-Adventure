package com.thom9521.mario;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;


public class MarioApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(15*70);
        settings.setHeight(10*70);
    }

    private Entity player;

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
            player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.W);
    }

    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("mario.json");
        getAudioPlayer().playSound("themesong.mp3");
        player = getGameWorld().spawn("player",50,600);
         //minY er højden
        getGameScene().getViewport().setBounds(-1500,0,1500,getHeight());
        getGameScene().getViewport().bindToEntity(player, getWidth()/2, getHeight()/2);

        getGameWorld().spawn("enemy", 550,50);

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
            getDisplay().showMessageBox("Level Complete!", () -> {
                System.out.println("Dialog Closed!");
            });
            getAudioPlayer().playSound("1-up.wav");
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                player.removeFromWorld();

                getAudioPlayer().playSound("die.wav");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}
