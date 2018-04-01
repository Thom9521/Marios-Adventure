package com.thom9521.mario;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.SceneFactory;
import com.almasb.fxgl.scene.menu.FXGLDefaultMenu;
import com.almasb.fxgl.scene.menu.MenuType;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.Node;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * Shows how to use custom background in menus.
 */
public class MarioSceneFactory extends SceneFactory {

    /**
     * Metode, der overordnet set håndterer baggrund og title i Main Menu screen.
     *
     * @param app Tager parameter "app" af typen "GameApplication" fra FXGL.
     * @return FXGLMenu Returnerer "FXGLMenu".
     */
    @NotNull
    @Override
    public FXGLMenu newMainMenu(@NotNull GameApplication app) {
        return new MarioMenu(app, MenuType.MAIN_MENU) {
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("jungle.png");
            }

            //Overskriften i Main Menu-skærmen (Anvend ikke nogen!)
            @Override
            protected Node createTitleView(String title) {
                return new Text("");
            }
        };
    }

    /**
     * Metode, der overordnet set håndterer baggrund og title i Game Menu screen.
     *
     * @param app Tager parameter "app" af typen "GameApplication" fra FXGL.
     * @return FXGLMenu Returnerer "FXGLMenu".
     */
    @NotNull
    @Override
    public FXGLMenu newGameMenu(@NotNull GameApplication app) {
        return new MarioMenu(app, MenuType.GAME_MENU) {
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("mountains.jpg");
            }

            //Overskriften i Game Menu-skærmen (Anvend ikke nogen!)
            @Override
            protected Node createTitleView(String title) {
                return new Text("");
            }
        };
    }
}