package com.zoe.client;

public class State {
	private float temperature;
	private boolean flag;
	public State()
	{
		temperature = 20.0f;
		flag = false;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
