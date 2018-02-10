package com.thom9521.mario;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;


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
                .viewFromTextureWithBBox("marioEnemy.png")
                .bbox(new HitBox(BoundingShape.box(20, 25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShroomControl())
                .build();
    }

    @Spawns("shell")
    public Entity newEnemy2(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(MarioType.SHELL)
                .from(data)
                .viewFromTextureWithBBox("marioShell.png")
                .bbox(new HitBox(BoundingShape.box(20, 25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShellControl())
                .build();
    }
    @Spawns("shell2")
    public Entity newShell2(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(MarioType.SHELL2)
                .from(data)
                .viewFromTextureWithBBox("marioShell.png")
                .bbox(new HitBox(BoundingShape.box(20, 25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShellControl())
                .build();
    }

    @Spawns("enemy3") //Samme enemy som enemy 1, men bruges til at spawne player et andet sted.
    public Entity newEnemy3(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(MarioType.ENEMY3)
                .from(data)
                //.viewFromNodeWithBBox(new Rectangle(30,30,Color.RED))
                .viewFromTextureWithBBox("marioEnemy.png")
                .bbox(new HitBox(BoundingShape.box(20, 25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShroomControl())
                .build();
    }

    @Spawns("fireball")
    public Entity newFireball(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(MarioType.FIREBALL)
                .from(data)
                .viewFromTextureWithBBox("fireball.png")
                .bbox(new HitBox(BoundingShape.box(20, 25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new FireballControl())
                .build();
    }

    @Spawns("bowser")
    public Entity newBowser(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(MarioType.BOWSER)
                .from(data)
                .viewFromTexture("bowser.png")
                .bbox(new HitBox(BoundingShape.box(65, 65)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new BowserControl())
                .build();
    }

    @Spawns("danger")
    public Entity newDanger(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DANGER)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(1246.62, 65)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("danger2")
    public Entity newDanger2(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DANGER2)
                .from(data)
                .viewFromTextureWithBBox("iceSpike.png")
                .bbox(new HitBox(BoundingShape.box(65, 65)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("danger3")
    public Entity newDanger3(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DANGER3)
                .from(data)
                .viewFromTextureWithBBox("lava.png")
                .bbox(new HitBox(BoundingShape.box(559.63, 112.16)))
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

    @Spawns("door3")
    public Entity newDoor3(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DOOR3)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("door4")
    public Entity newDoor4(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DOOR4)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("door5")
    public Entity newDoor5(SpawnData data) {
        return Entities.builder()
                .type(MarioType.DOOR5)
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
                .bbox(new HitBox(BoundingShape.box(32, 32)))
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
                .viewFromTextureWithBBox("marioCoin.png")
                .bbox(new HitBox(BoundingShape.box(25, 28)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("chest")
    public Entity newChest(SpawnData data) {
        return Entities.builder()
                .type(MarioType.CHEST)
                .from(data)
                .viewFromTextureWithBBox("chest.png")
                .bbox(new HitBox(BoundingShape.box(25, 28)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("heart")
    public Entity newHeart(SpawnData data) {
        return Entities.builder()
                .type(MarioType.HEART)
                .from(data)
                .viewFromTextureWithBBox("marioHeart.png")
                .bbox(new HitBox(BoundingShape.box(25, 28)))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("girl")
    public Entity newGirl(SpawnData data) {
        return Entities.builder()
                .type(MarioType.GIRL)
                .from(data)
                .viewFromTextureWithBBox("dorami.png")
                .bbox(new HitBox(BoundingShape.box(39, 42)))
                .with(new CollidableComponent(true))
                .build();

    }
}