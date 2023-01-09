package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.mygdx.game.world.objects.Ground;
import com.mygdx.game.world.objects.Objects;

import java.util.ArrayList;

public class WorldRenderer {

    private ModelBatch batch;
    PerspectiveCamera camera;
    private Environment environment;
    private CameraInputController control;

    public WorldRenderer(){
        batch = new ModelBatch();

        createCamera();
        createEnvironment();
    }

    private void createCamera(){
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(5f, 5f, 5f);
        camera.lookAt(0,0,0);
        camera.near = -1f;
        camera.far = 200f;
        camera.update();
        //control = new CameraInputController(camera);
        //Gdx.input.setInputProcessor(control);

    }

    private void createEnvironment(){
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 0.8f));
        environment.add(new DirectionalLight().set(1f,1f, -1f, -1f, -0.8f, -0.2f));
        environment.add(new PointLight().set(Color.BLUE,0,5,0,10 ));
    }

    public void render(ArrayList<Objects> obj){
        camera.update();
        //control.update();
        batch.begin(camera);
        for(int i = 0; i < obj.size(); i++) {
            obj.get(i).render(batch, environment);
        }
        batch.end();
    }

    public PerspectiveCamera getCamera(){
        return camera;
    }
}
