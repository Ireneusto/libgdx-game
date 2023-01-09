package com.mygdx.game.world.objects;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.mygdx.game.world.Controller;

public class Player extends Objects {

    private Matrix4 transform;
    private Vector3 forward;
    private float speed = 30;

        public Player(btDynamicsWorld world){

            ModelBuilder builder = new ModelBuilder();

            transform = new Matrix4();
            forward = new Vector3();

            model = new ModelInstance(builder.createSphere(2,2,2, 10, 10, GL20.GL_TRIANGLES,
                    new Material(ColorAttribute.createDiffuse(1,1,1, 0.1f)),Usage.Position | Usage.Normal));
            //model.transform.setToTranslation(0,5,0);
            //model.transform.getTranslation(new Vector3(0,5,0));
            btCollisionShape shape = new btSphereShape(1f);
            Vector3 in = new Vector3(0,0,0);
            shape.calculateLocalInertia(1, in);
            createPhisics(world, shape, 1);
            body.setActivationState(Collision.DISABLE_DEACTIVATION);
            body.setGravity(in);

            forward.set(1,0,1).rot(body.getWorldTransform()).nor();
            forward.scl(30);
        }

        public  void update(){
            model.transform.set(body.getWorldTransform());

        }



        public void move(Controller controller, PerspectiveCamera camera) {
            if (controller.isTouched) {

                transform.setTranslation(body.getWorldTransform().getTranslation(new Vector3(0,0,0)));

                transform.rotate(0,1,0, controller.deltaX);
                transform.rotate(1,0,0, controller.deltaY);

                body.setWorldTransform(transform);

                forward.set(0,0,1).rot(body.getWorldTransform()).nor();
                forward.scl(30);

                body.setLinearVelocity(forward);
                /*
                if(body.getLinearVelocity().x < 0 && body.getLinearVelocity().z < 0){
                    body.setLinearVelocity(new Vector3(body.getLinearVelocity().x + controller.deltaX,
                            body.getLinearVelocity().y - controller.deltaY,
                            body.getLinearVelocity().z - controller.deltaX));
                }
                if(body.getLinearVelocity().x < 0 && body.getLinearVelocity().z > 0){
                    body.setLinearVelocity(new Vector3(body.getLinearVelocity().x - controller.deltaX,
                            body.getLinearVelocity().y - controller.deltaY,
                            body.getLinearVelocity().z - controller.deltaX));
                }
                if(body.getLinearVelocity().x > 0 && body.getLinearVelocity().z > 0){
                    body.setLinearVelocity(new Vector3(body.getLinearVelocity().x - controller.deltaX,
                            body.getLinearVelocity().y - controller.deltaY,
                            body.getLinearVelocity().z + controller.deltaX));
                }
                if(body.getLinearVelocity().x > 0 && body.getLinearVelocity().z < 0){
                    body.setLinearVelocity(new Vector3(body.getLinearVelocity().x + controller.deltaX,
                            body.getLinearVelocity().y - controller.deltaY,
                            body.getLinearVelocity().z + controller.deltaX));
                }
            */

            } else {
                body.setLinearVelocity(new Vector3(0, 0, 0));
                //forward.set(0,0,0);
            }

            camera.position.set(body.getWorldTransform().getTranslation(new Vector3(0,0,0)).x-forward.x/5,
                    body.getWorldTransform().getTranslation(new Vector3(0,0,0)).y-forward.y/5,
                    body.getWorldTransform().getTranslation(new Vector3(0,0,0)).z-forward.z/5);

            camera.lookAt(body.getWorldTransform().getTranslation(new Vector3(0,0,0)));
        }
}
