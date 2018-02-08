package com.thom9521.doraemon;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class EnemyControl2 extends Control {

    private PhysicsComponent physics;

    private LocalTimer walkTimer;
    private LocalTimer walkTimer2;

    @Override
    public void onAdded(Entity entity) {
        walkTimer = FXGL.newLocalTimer();
        walkTimer.capture();

        walkTimer2 = FXGL.newLocalTimer();
        walkTimer2.capture();
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        if (walkTimer.elapsed(Duration.seconds(3))) {
            walkLeft();
            walkTimer.capture();
        } else if(walkTimer2.elapsed(Duration.seconds(6.05))) {
            walkRight();
            walkTimer2.capture();
        }


    }


    public void walkLeft() {
        physics.setVelocityX(-500);
    }

    public void walkRight(){
        physics.setVelocityX(500);
    }
}
