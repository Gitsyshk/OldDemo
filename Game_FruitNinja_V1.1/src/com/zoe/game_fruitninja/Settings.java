package com.zoe.game_fruitninja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.zoe.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;// 控制声音标志位
	public static int highscores;// 保存最高分

	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readFile(".FruitNinja")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			highscores = Integer.parseInt(in.readLine());
		} catch (IOException e) {

		} catch (NumberFormatException e) {

		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {

			}
		}
	}

	public static void save(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeFile(".FruitNinja")));
			out.write(Boolean.toString(soundEnabled));
			out.write(Integer.toString(highscores));

		} catch (IOException e) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {

			}
		}
	}
}
