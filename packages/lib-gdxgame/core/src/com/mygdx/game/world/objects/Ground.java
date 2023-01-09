package com.mygdx.game.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;

public class Ground extends Objects {

    public Ground(btDynamicsWorld world, G3dModelLoader loader){
        createModel(loader);
        btCollisionShape shape = new btBvhTriangleMeshShape(model.model.meshParts);
        createPhisics(world, shape, 0);

    }

    private void createModel(G3dModelLoader loader){

        model = new ModelInstance(loader.loadModel(Gdx.files.internal("objects/koszyk.g3dj")));
        //model.transform.setToTranslation(0,0,0);
    }

}
