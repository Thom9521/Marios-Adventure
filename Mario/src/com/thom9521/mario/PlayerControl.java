package com.thom9521.mario;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.gameplay.GameState;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.common.JBoxSettings;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

@Required(PositionComponent.class)
public class PlayerControl extends Control {

    private PhysicsComponent physics;

    private AnimatedTexture texture;

    private AnimationChannel animIdle, animWalk;

    public PlayerControl(){

        animIdle = new AnimationChannel("cat.png", 4, 30, 40, Duration.seconds(1),2,2);
        animWalk = new AnimationChannel("cat.png", 4, 30, 40, Duration.seconds(1),1,3);

        texture = new AnimatedTexture(animIdle);

    }

    @Override
    public void onAdded(Entity entity) {
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {

        if(isMoving()){
            texture.setAnimationChannel(animWalk);
        } else{
            texture.setAnimationChannel(animIdle);
        }
        if (Math.abs(physics.getVelocityX()) < 145){ //Player stopper med at gå hvis hastigheden kommer under 140.
            physics.setVelocityX(0);}

    }

    private boolean isMoving(){
        return FXGLMath.abs(physics.getVelocityX()) > 0;

    }

    public void left(){
        getEntity().setScaleX(-1);
        physics.setVelocityX(-150);
    }

    public void right(){
        getEntity().setScaleX(1);
        physics.setVelocityX(150);
    }

    public void jump(){
        if(physics.getVelocityY()==0) {
            physics.setVelocityY(-340);

    }
}


}
