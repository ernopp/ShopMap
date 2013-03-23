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
import android.widget.Toast;

public class DrawView extends View {
    Paint paint = new Paint();
    int x;
    int y ;
    int rectangleWitdth;
    int rowLocation [][];
    int mapId;
    int circleRadius ;
    //64 items
    int mapsItemOrders[][]=
    	{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41},
    		{20, 7, 17, 1, 39, 41, 36, 24, 40, 22, 31, 16, 21, 0, 5, 4, 37, 33, 8, 23, 29, 19, 13, 3, 34, 2, 18, 15, 28, 10, 26, 11, 14, 27, 35, 12, 32, 9, 30, 6, 38, 25},
    		{32, 5, 13, 39, 28, 27, 12, 29, 4, 10, 23, 15, 24, 30, 33, 38, 20, 41, 34, 9, 14, 11, 36, 19, 40, 1, 0, 22, 35, 18, 17, 37, 21, 2, 3, 8, 6, 25, 26, 31, 7, 16} };
    
    ArrayList<Item> allItems;
    ArrayList<Item> selectedItems;
    
    public DrawView(Context context, ArrayList<Item> allIts, int mapId) {
    	super(context);
    	
    	//put all the items in the order necessary for this map (mapID)
    	this.allItems = new ArrayList<Item>();
    	for(int i=0; i<mapsItemOrders[mapId].length; i++){
    		this.allItems.add(allIts.get(mapsItemOrders[mapId][i]));
    	}
    	
        paint.setColor(Color.BLACK);
        this.mapId = mapId;
    }

    @SuppressLint("NewApi")
	@Override
    public void onDraw(Canvas canvas) {
    		x = canvas.getWidth();
    		y = canvas.getHeight();
    		rectangleWitdth = x/20;
    		
    		drawMapRows(canvas, this.mapId);
    		
    		//populates the xPosition & yPosition fields of the selectedItems
    		generateItemLocations(canvas);		

        	this.selectedItems = new ArrayList<Item>();
        	for(Item i : this.allItems){
        		if(i.isSelected()){
        			this.selectedItems.add(i);
        			Log.i("test", i.getName() + i.getID());
        		}
        	}
        	
    		
    		drawCategoryCircle(canvas,this.selectedItems);
    		
    }
    
    public void drawMapRows(Canvas canvas, int mapNumber)
    {
    	int rectangleWitdth = x/20;
    	
    	//Creating the Paint object for the drawing the rows
    	Paint rowPaint = new Paint();
    	rowPaint.setColor(Color.BLACK);
		rowPaint.setStyle(Paint.Style.STROKE);
		
		if(mapNumber == 0)
    	{
    	
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
    	} else if (mapNumber ==1 )
    	{
			//2nd map 
			rowLocation[1][0]= x/4-rectangleWitdth/2;
			rowLocation[1][1]=y/11;
			rowLocation[1][2]=x/4+rectangleWitdth/2;
			rowLocation[1][3]=7*y/11;
			
			//Horizontal Row
			rowLocation[0][0]= x/5-rectangleWitdth/2;
			rowLocation[0][1]=8*y/10;
			rowLocation[0][2]=4*x/5+rectangleWitdth/2;
			rowLocation[0][3]=10*y/12;
			
			rowLocation[2][0]= x*3/4-rectangleWitdth/2;
			rowLocation[2][1]=y/11;
			rowLocation[2][2]=x*3/4+rectangleWitdth/2;
			rowLocation[2][3]=7*y/11;
    	}
		
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
    	circleRadius = canvas.getWidth()/75;
    	Paint itemPaint = new Paint();
		itemPaint.setColor(Color.RED);
		itemPaint.setStyle(Paint.Style.FILL);
		
		Paint textPaint = new Paint();
		textPaint.setColor(Color.BLUE);
		textPaint.setStyle(Paint.Style.FILL);
		textPaint.setTextSize(circleRadius*3);
		
		
	

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
    	
    	//IMPORTANT
    	int numberOfItemPerRow = 7;
    	
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
						
						if(i>=this.allItems.size()){
							return;
						}
						else{
							this.allItems.get(i).setXPosition(xcord);
							this.allItems.get(i).setYPosition(ycord);
							this.allItems.get(i).setRowSide(rowSide);
						}
						i++;
						rowHeightCursor= rowHeightCursor+2;
					}
				}
			}
		}
    }
    
    
} //class end
