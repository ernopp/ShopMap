package com.example.shop_map;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    int x;
    int y ;
    int rectangleWitdth;
    int rowLocation [][];
    
    ArrayList<Item> selectedItems;
    
    public DrawView(Context context, ArrayList<Item> selectedItems) {
    	super(context);
    	this.selectedItems = selectedItems;
        paint.setColor(Color.BLACK);
    }

    @SuppressLint("NewApi")
	@Override
    public void onDraw(Canvas canvas) {
    		x = canvas.getWidth();
    		y = canvas.getHeight();
    		rectangleWitdth = x/20;
    		
    		drawMapRows(canvas);
    		
    		//populates the xPosition & yPosition fields of the selectedItems
    		generateItemLocations(canvas);
    		drawCategoryCircle(canvas,selectedItems);
    		
    		for (Item i :selectedItems)
    		{
	    			Log.i("CATE",i.getName());;
    		}
    }
    
    public void drawMapRows(Canvas canvas)
    {
    	int rectangleWitdth = x/20;
    	
    	//Creating the Paint object for the drawing the rows
    	Paint rowPaint = new Paint();
    	rowPaint.setColor(Color.BLACK);
		rowPaint.setStyle(Paint.Style.STROKE);
		
		//Setting up the location of each of the 3 rows
		rowLocation = new int [3][4];
		rowLocation[1][0]= x/2-rectangleWitdth/2;
		rowLocation[1][1]=y/10;
		rowLocation[1][2]=x/2+rectangleWitdth/2;
		rowLocation[1][3]=8*y/10;
		
		rowLocation[0][0]= x/4-rectangleWitdth/2;
		rowLocation[0][1]=y/10;
		rowLocation[0][2]=x/4+rectangleWitdth/2;
		rowLocation[0][3]=8*y/10;
		
		rowLocation[2][0]= x*3/4-rectangleWitdth/2;
		rowLocation[2][1]=y/10;
		rowLocation[2][2]=x*3/4+rectangleWitdth/2;
		rowLocation[2][3]=8*y/10;
		
		//Creating the rectangle object representing each row
		Rect row1= new Rect();
		Rect row2= new Rect();
		Rect row3= new Rect();
		
		//Assigning the coordinates of each
		
		
		row1.set(rowLocation[0][0],rowLocation[0][1],rowLocation[0][2],rowLocation[0][3]);
		row2.set(rowLocation[1][0],rowLocation[1][1],rowLocation[1][2],rowLocation[1][3]);
		row3.set(rowLocation[2][0],rowLocation[2][1],rowLocation[2][2],rowLocation[2][3]);
		
		//Drawing the rows
		canvas.drawRect(row1, rowPaint);
		canvas.drawRect(row2, rowPaint);
		canvas.drawRect(row3, rowPaint);
    }
    
    public void drawCategoryCircle(Canvas canvas, ArrayList<Item> selectedItems)
    {
    	Paint itemPaint = new Paint();
		itemPaint.setColor(Color.RED);
		itemPaint.setStyle(Paint.Style.FILL);
		
		Paint textPaint = new Paint();
		textPaint.setColor(Color.BLUE);
		textPaint.setStyle(Paint.Style.FILL);
		
		int circleRadius = 3;

		for (Item i : selectedItems)
		{
			canvas.drawCircle(i.getxPosition(), i.getyPosition(),circleRadius,itemPaint);
			
			if(i.getRowSide()== 1)
			{
				canvas.drawText(i.getName(), i.getxPosition()+ circleRadius, i.getyPosition(), textPaint);
			}else
			{
	
					int rowSpacing = rowLocation[2][0]- rowLocation[1][2];
					int xPosit =  rowSpacing;
					for(int k =1; k<12;k++)
					{
						if(i.getName().length()==k)
						{
							canvas.drawText(i.getName(),i.getxPosition()- k*xPosit/10 -circleRadius , i.getyPosition(), textPaint);
						}
					}
					
			}
		}
	}
    
    public void generateItemLocations(Canvas canvas)
    {
    	int xcord;
		int ycord;
    	
    	int rowLength =rowLocation[2][3]- rowLocation[2][1];
    	int numberOfItemPerRow = 8;
    	
    	Paint itemPaint = new Paint();
		itemPaint.setColor(Color.RED);
		itemPaint.setStyle(Paint.Style.FILL);
		
		int i=0;
		
    	//Looping through each row 
    	for (int rowNumber=0; rowNumber<3;rowNumber++)
    	{
			//Looping through each rowSide of the row
			for(int rowSide=0;rowSide<2;rowSide++)
			{
				int rowHeightCursor = 0;
				{
					for (int itemLocation =0; itemLocation<numberOfItemPerRow;itemLocation++ )
					{	
						//Setting the X coordinate
						if(rowSide ==0)
						{
							xcord = rowLocation[rowNumber][0];
						}else
						{
							
							xcord = rowLocation[rowNumber][2];
						}
							
						if(rowNumber == 1)
						{
							ycord = rowLocation[rowNumber][1]+ (rowHeightCursor+1)*rowLength/(numberOfItemPerRow*2);
						}else
						{
						
							ycord = rowLocation[rowNumber][1]+ rowHeightCursor*rowLength/(numberOfItemPerRow*2);
						}
						
						if(i>=this.selectedItems.size()){
							return;
						}
						else{
							this.selectedItems.get(i).setXPosition(xcord);
							this.selectedItems.get(i).setYPosition(ycord);
							this.selectedItems.get(i).setRowSide(rowSide);
						}
						i++;
						rowHeightCursor= rowHeightCursor+2;
					}
				}
			}
		}
    }
    
    
} //class end
