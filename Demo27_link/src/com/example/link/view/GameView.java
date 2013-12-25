package com.example.link.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import cmo.example.link.object.LinkInfo;

import com.example.link.board.GameService;
import com.example.link.util.ImageUtil;

public class GameView extends View{

	private GameService gameService;
	private Piece selectdPiece;
	private LinkInfo linkInfo;
	private Paint paint;
	private Bitmap selectImage;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.paint = new Paint();
		this.paint.setColor(Color.RED);
		this.paint.setStrokeWidth(3);
		this.selectImage = ImageUtil.getSelectImage(context);
	}
	public void setLinkInfo(LinkInfo linkinfo)
	{
		this.linkInfo = linkinfo;
	}
	public void setGameService(GameService gameService)
	{
		this.gameService = gameService;
	}
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
	
	}
}
