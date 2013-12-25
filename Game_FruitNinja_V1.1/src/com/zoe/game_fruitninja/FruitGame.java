package com.zoe.game_fruitninja;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FruitGame {

	private static final int SCORE = 10;
	private static final float TICK_INITIAL = 0.06f;
	
	public boolean gameover = false;
	private float tickTime = 0.0f;
	public List<Fruit> fruitList = new ArrayList<Fruit>();
	public FruitGame()
	{
		throwFruit();
	}
	public void throwFruit()
	{
		Random random = new Random();
		int num = random.nextInt(3)+1;
		for(int i = 1;i<=num;i++)
		{
			fruitList.add(new Fruit(100+random.nextInt(440), 480, random.nextInt(5)));
		}
	}
	public void update(float deltaTime)
	{
		if(gameover)
			return;
		tickTime += deltaTime;
		while(tickTime > TICK_INITIAL)
		{
			tickTime -= TICK_INITIAL;
			for(int i = 0 ;i<fruitList.size();i++)
			{
				if(fruitList.get(i).Changer() == false)
					fruitList.remove(i);
			}
			if(fruitList.size() == 0)
				
				{
					fruitList.clear();
					throwFruit();
				}
		}		
	}
}