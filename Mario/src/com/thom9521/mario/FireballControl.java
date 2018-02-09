package com.thom9521.mario;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class FireballControl extends Control {

    private PhysicsComponent physics;

    private LocalTimer jumpTimer;



    @Override
    public void onAdded(Entity entity) {
        jumpTimer = FXGL.newLocalTimer();
        jumpTimer.capture();
        }


    @Override
    public void onUpdate(Entity entity, double tpf) {

            if (jumpTimer.elapsed(Duration.seconds(Math.random()*5+3))) {
                jump();
                jumpTimer.capture();
            }
        }



    public void jump() {
        physics.setVelocityY(-630);
    }

}
