package com.thom9521.mario;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsControl;
import com.almasb.fxgl.physics.box2d.common.JBoxSettings;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.texture.Texture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MarioApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(15 * 70);
        settings.setHeight(10 * 70);
        settings.setTitle("Marios Adventure");
        settings.setVersion("1.0");

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
        getGameWorld().setLevelFromMap("mario5.json");
        getAudioPlayer().playMusic("themesong.mp3");
        getGameScene().setBackgroundRepeat("forrest.png");
        player = getGameWorld().spawn("player", 70, 600);
        getGameScene().getViewport().setBounds(-1500, 0, 5000, getHeight());
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2);
        getGameWorld().spawn("enemy", 390, 240);

        getAudioPlayer().setGlobalMusicVolume(0.1);
        getAudioPlayer().setGlobalSoundVolume(1);


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

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.CHEST) {
            @Override
            protected void onCollisionBegin(Entity player, Entity chest) {
                chest.removeFromWorld();
                getGameState().increment("score",+1000);
                getAudioPlayer().playSound("chest.wav");
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.HEART) {
            @Override
            protected void onCollisionBegin(Entity player, Entity heart) {
                heart.removeFromWorld();
                getGameState().increment("lives",+1);
                getAudioPlayer().playSound("heart.wav");
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.GIRL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity girl) {
               getDisplay().showMessageBox("You saved you girlfirend! \nNice one ;)", () ->{});
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                getDisplay().showMessageBox("Level 1 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                    getAudioPlayer().playSound("1-up.wav");

                    player.getWorld().setLevelFromMap("mario2.json");
                    getGameScene().setBackgroundRepeat("mountains.jpg");
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
                    getGameScene().setBackgroundRepeat("jungle.png");
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
                    getGameScene().setBackgroundRepeat("iceland.jpg");
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
            protected void onCollisionBegin(Entity player, Entity door2) {
                getDisplay().showMessageBox("Level 4 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                    getAudioPlayer().playSound("1-up.wav");
                    player.getWorld().setLevelFromMap("mario5.json");
                    getGameScene().getViewport().setBounds(-1500, 0, 5000, getHeight());
                    getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2);
                    player.getControl(PhysicsControl.class).reposition(despawn);

                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR5) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door4) {
                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().setGlobalMusicVolume(100);
                getAudioPlayer().playMusic("dothemario.mp3");
                if (getGameState().getInt("score")== 4550){
                getDisplay().showMessageBox("THE END! \n\nYou got the highest score possible! \nGood Job!", () -> {
                    System.out.println("Dialog Closed!");
                }); }else {
                getDisplay().showMessageBox("THE END! \n\nBut seems like you missed some coins! \n\nTry again to" +
                        " get all of them!", () -> {
                    System.out.println("Dialog Closed!");
                });
            }getDisplay().showMessageBox("Level 5 Complete!", () -> {}); }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getGameState().increment("lives",-1);
                if (getGameState().getInt("lives") == 0){
                    getDisplay().showMessageBox("Game over!", ()-> {
                        player.getWorld().setLevelFromMap("mario.json");
                        getGameScene().setBackgroundRepeat("forrest.png");
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("enemy", 390, 240);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }
                else {
                    getDisplay().showMessageBox("Try again!", () -> {
                    player.getWorld();
                    player.getControl(PhysicsControl.class).reposition(despawn);
                        getAudioPlayer().playMusic("themesong.mp3");
                });
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY2) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy2) {

                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getGameState().increment("lives",-1);
                if (getGameState().getInt("lives") == 0){
                    getDisplay().showMessageBox("Game over!", ()-> {
                        player.getWorld().setLevelFromMap("mario.json");
                        getGameScene().setBackgroundRepeat("forrest.png");
                        getGameScene().getViewport().setBounds(-1500, 0, 3000, getHeight());
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("enemy", 390, 240);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }
                else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn4);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.ENEMY3) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy3) {
                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getGameState().increment("lives",-1);
                if (getGameState().getInt("lives") == 0){
                    getDisplay().showMessageBox("Game over!", ()-> {
                        player.getWorld().setLevelFromMap("mario.json");
                        getGameScene().setBackgroundRepeat("forrest.png");
                        getGameScene().getViewport().setBounds(-1500, 0, 3000, getHeight());
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("enemy", 390, 240);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }
                else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn4);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DANGER) {
            @Override
            protected void onCollisionBegin(Entity player, Entity danger) {

                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getGameState().increment("lives",-1);
                if (getGameState().getInt("lives") == 0){
                    getDisplay().showMessageBox("Game over!", ()-> {
                        player.getWorld().setLevelFromMap("mario.json");
                        getGameScene().setBackgroundRepeat("forrest.png");
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("enemy", 390, 240);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }
                else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DANGER2) {
            @Override
            protected void onCollisionBegin(Entity player, Entity danger2) {
                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getGameState().increment("lives",-1);
                if (getGameState().getInt("lives") == 0){
                    getDisplay().showMessageBox("Game over!", ()-> {
                        player.getWorld().setLevelFromMap("mario.json");
                        getGameScene().setBackgroundRepeat("forrest.png");
                        getGameScene().getViewport().setBounds(-1500, 0, 3000, getHeight());
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("enemy", 390, 240);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }
                else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn4);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DANGER3) {
            @Override
            protected void onCollisionBegin(Entity player, Entity danger3) {

                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("die.wav");
                getGameState().increment("lives",-1);
                if (getGameState().getInt("lives") == 0){
                    getDisplay().showMessageBox("Game over!", ()-> {
                        player.getWorld().setLevelFromMap("mario5.json");
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }
                else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getAudioPlayer().playMusic("themesong.mp3");
                    });
                }

            }
        });

    }

    @Override
    protected void initUI() {
        Text uiScore = getUIFactory().newText("Score",50);
        uiScore.setTranslateX(getWidth()-960);
        uiScore.setTranslateY(70);
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());
        getGameScene().addUINode(uiScore);

        Text uiLives = getUIFactory().newText("Lives",50);
        uiLives.setTranslateX(getWidth()-960);
        uiLives.setTranslateY(150);
        uiLives.fillProperty().bind(getGameState().objectProperty("stageColorL"));
        uiLives.textProperty().bind(getGameState().intProperty("lives").asString());
        getGameScene().addUINode(uiLives);

        Texture scoreTexture = getAssetLoader().loadTexture("marioCoinBig.png");
        scoreTexture.setTranslateX(getWidth()-1030);
        scoreTexture.setTranslateY(25);
        getGameScene().addUINode(scoreTexture);

        Texture livesTexture = getAssetLoader().loadTexture("heart.png");
        livesTexture.setTranslateX(getWidth()-1030);
        livesTexture.setTranslateY(105);
        getGameScene().addUINode(livesTexture);
    }
      @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("stageColor", Color.GOLD);
        vars.put("score", 0);

        vars.put("stageColorL", Color.RED);
        vars.put("lives", 3);
    }

    public static void main(String[] args) {
        launch(args);

    }}
