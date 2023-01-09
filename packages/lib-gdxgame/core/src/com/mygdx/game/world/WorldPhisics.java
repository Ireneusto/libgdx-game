package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;

public class WorldPhisics {

    private btCollisionConfiguration collissionConfig;
    private btDispatcher dispatcher;
    private btBroadphaseInterface broadphase;
    private btConstraintSolver constraintSolver;
    private btDynamicsWorld dynamicsWorld;

    public WorldPhisics(){
        createWorld();
    }

    private void createWorld(){
        Bullet.init();
        //stworz swiat
        collissionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collissionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collissionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -10f, 0));
    }

    public btDynamicsWorld getWorld(){
        return dynamicsWorld;

    }

    public void update(float delta){
        delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
        dynamicsWorld.stepSimulation(delta, 5, 1f/60f);
    }
}
