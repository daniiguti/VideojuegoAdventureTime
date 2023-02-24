package com.mygdx.game;

import com.badlogic.gdx.Game;

import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.GameReadyScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameWinScreen;

public class MainGame extends Game {
	//Instancias de las pantallas para poder utilizar el setScene y poder cambiar de screen
	private GameReadyScreen grs;
	private GameScreen gs;
	private GameOverScreen gos;
	private GameWinScreen gws;

	/**
	 * Constructor:
	 *  Inicializacion de las variables de las pantallas
	 * 	Se le pasa this, pq en cada clase de las pantallas, tenemos que ser capaces de acceder a esta, pq esta es la unica
	 * 	capaz de cambiar de pantalla
	 */
	@Override
	public void create () {
		grs = new GameReadyScreen(this);
		gs = new GameScreen(this);
		gos = new GameOverScreen(this);
		gws = new GameWinScreen(this);

		//Ponemos la pantalla de game ready screen
		setScreen(this.grs);
	}

	public GameOverScreen getGameOverScreen(){
		return this.gos;
	}
	public GameReadyScreen getGameReadyScreen() {
		return this.grs;
	}
	public GameScreen getGameScreen() {
		return this.gs;
	}
	public GameWinScreen getGameWinScreen() {
		return this.gws;
	}

	public void setGrs(GameReadyScreen grs) {
		this.grs = grs;
	}
	public void setGs(GameScreen gs) {
		this.gs = gs;
	}
	public void setGos(GameOverScreen gos) {
		this.gos = gos;
	}
	public void setGws(GameWinScreen gws) {
		this.gws = gws;
	}
}

