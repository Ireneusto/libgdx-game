package com.mygdx.game.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.mygdx.game.world.objects.Objects;
import com.mygdx.game.world.objects.Player;

public class WorldController {

    private ArrayList<Objects> obj;
    private Controller controller;
    private PerspectiveCamera camera;
    private Player player;

    public WorldController(ArrayList<Objects> obj, PerspectiveCamera camera){
        this.obj = obj;
        controller = new Controller();
        this.camera = camera;
        player = (Player) obj.get(1);
        Gdx.input.setInputProcessor(controller);
    }

    public void update(float delta){
        player.update();
        player.move(controller, camera);
    }
}

