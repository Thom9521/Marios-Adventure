package com.thom9521.mario;


import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.PushbackInputStream;
import java.io.PushbackReader;

@SetEntityFactory
public class MarioFactory implements EntityFactory {

    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return Entities.builder()
                .type(MarioType.PLATFORM)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(MarioType.ENEMY)
                .from(data)
                //.viewFromNodeWithBBox(new Rectangle(30,30,Color.RED))
                .viewFromTextureWithBBox("enemy.png")
                .bbox(new HitBox(BoundingShape.box(20,25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new EnemyControl())
                .build();
    }

    @Spawns("danger")
    public Entity newDanger(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DANGER)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(65,65)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DOOR)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("door2")
    public Entity newDoor2(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DOOR2)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return Entities.builder()
                .type(MarioType.PLAYER)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(32,42)))
                //.viewFromNodeWithBBox(new Rectangle(30,30, Color.BLUE))
                .with(physics)
                .with(new IrremovableComponent())
                .with(new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data) {
        return Entities.builder()
                .type(MarioType.COIN)
                .from(data)
                //.viewFromNodeWithBBox(new Circle(data.<Integer>get("width") / 2, Color.GOLD))
                .viewFromTextureWithBBox("coin.png")
                .bbox(new HitBox(BoundingShape.box(25,28)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("imagelayer")
    public Entity newImagelayer(SpawnData data){
        return Entities.builder()
                .from(data)
                .build();
    }
}