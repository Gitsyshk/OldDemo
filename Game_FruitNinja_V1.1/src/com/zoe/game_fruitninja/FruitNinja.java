package com.zoe.game_fruitninja;

import com.zoe.framework.Screen;
import com.zoe.framework.impl.AndroidGame;

public class FruitNinja extends AndroidGame {

	@Override
	public Screen getStartScreen() {		
		return new LoadingScreen(this);
	}

}
