package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.JsonReader;
import com.mygdx.game.world.objects.Ground;
import com.mygdx.game.world.objects.Objects;
import com.mygdx.game.world.objects.Player;

import java.util.ArrayList;

public class World {

    private WorldRenderer renderer;
    private WorldController controller;
    private WorldPhisics phisics;
    private DebugDrawer debug;

    private Ground ground;
    private Player player;

    private ArrayList<Objects> obj;

    public World(){
        obj = new ArrayList<Objects>();
        phisics = new WorldPhisics();
        createStaff();

        renderer = new WorldRenderer();
        controller = new  WorldController(obj, renderer.getCamera());


        debug = new DebugDrawer();
        debug.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
        phisics.getWorld().setDebugDrawer(debug);

    }

    public void update(float delta){

        Gdx.gl.glClearColor(0.3f, 0.6f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        renderer.render(obj);
        phisics.update(delta);
        //debug.begin(renderer.camera);
        //phisics.getWorld().debugDrawWorld();
        //debug.end();

        controller.update(delta);
    }

    private void createStaff(){

        JsonReader jsonReader = new JsonReader();
        G3dModelLoader loader = new G3dModelLoader(jsonReader);

        player = new Player(phisics.getWorld());
        ground = new Ground(phisics.getWorld(), loader);
        obj.add(ground);
        obj.add(player);
    }

}
