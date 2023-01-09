package com.mygdx.game.world.objects;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

public class Objects {

    protected ModelInstance model;
    protected btRigidBody body;

    public void render(ModelBatch batch, Environment env){
        batch.render(model, env);
    }

    protected void createPhisics(btDynamicsWorld world, btCollisionShape shape, float mass){
        btRigidBody.btRigidBodyConstructionInfo construction;
        Vector3 centerOfMass = new Vector3();
        //construction = new btRigidBody.btRigidBodyConstructionInfo(0, null, shape, centerOfMass);
        construction = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, centerOfMass);
        body = new btRigidBody(construction);
        world.addRigidBody(body);

    }
}


