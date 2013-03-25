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
    //42 items
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
    		
    		//Draw the rows of the map
    		drawMapRows(canvas, this.mapId);
    		
    		
    		//populates the xPosition & yPosition fields of the selectedItems
    		//depending on the Map we are drawing on
    		if(this.mapId == 0)
    		{
    			generateItemLocationsMap1(canvas);
    		}else 
    		{
    			generateItemLocationsMap2(canvas);
    		}
    	
    		//Selecting the Items that we want to display on the Map
        	this.selectedItems = new ArrayList<Item>();
        	for(Item i : this.allItems){
        		if(i.isSelected()){
        			this.selectedItems.add(i);
        			Log.i("test", i.getName() + i.getID());
        		}
        	}
        	
    		drawItemOnMap(canvas,this.selectedItems);
        	
    		
    }
    
    /*
     * Depending on which Map the user selected this Method will 
     * draw the rows of each supermarket 
     */
    public void drawMapRows(Canvas canvas, int mapNumber)
    {
    	int rectangleWitdth = x/20;
    	
    	//Creating the Paint object for the drawing the rows
    	Paint rowPaint = new Paint();
    	rowPaint.setColor(Color.BLACK);
		rowPaint.setStyle(Paint.Style.STROKE);
		
		//Entering the Coordinates of each Row in the market
		if(mapNumber == 0)
    	{
    	
			//Row in the center 
	    	rowLocation = new int [3][4];
			rowLocation[1][0]= x/2-rectangleWitdth/2;
			rowLocation[1][1]=y/10;
			rowLocation[1][2]=x/2+rectangleWitdth/2;
			rowLocation[1][3]=8*y/10;
			
			//Row at the left
			rowLocation[0][0]= x/4-rectangleWitdth/2;
			rowLocation[0][1]=y/10;
			rowLocation[0][2]=x/4+rectangleWitdth/2;
			rowLocation[0][3]=8*y/10;
			
			//Row at the right
			rowLocation[2][0]= x*3/4-rectangleWitdth/2;
			rowLocation[2][1]=y/10;
			rowLocation[2][2]=x*3/4+rectangleWitdth/2;
			rowLocation[2][3]=8*y/10;
    	} else if (mapNumber ==1 )
    	{
    		rowLocation = new int [6][4];
			//2nd map 
    		//Row at the left
			rowLocation[0][0]= x/4-rectangleWitdth/2;
			rowLocation[0][1]=2*y/22;
			rowLocation[0][2]=x/4+rectangleWitdth/2;
			rowLocation[0][3]=9*y/22;
			
			rowLocation[1][0]= x/4-rectangleWitdth/2;
			rowLocation[1][1]=10*y/22;
			rowLocation[1][2]=x/4+rectangleWitdth/2;
			rowLocation[1][3]=17*y/22;
			
			//Row in the center 
			rowLocation[2][0]= x/2-rectangleWitdth/2;
			rowLocation[2][1]=y/11;
			rowLocation[2][2]=x/2+rectangleWitdth/2;
			rowLocation[2][3]=9*y/22;
			
			//Row in the center 
			rowLocation[3][0]= x/2-rectangleWitdth/2;
			rowLocation[3][1]=10*y/22;
			rowLocation[3][2]=x/2+rectangleWitdth/2;
			rowLocation[3][3]=17*y/22;
			
			//Row at the left
			rowLocation[4][0]= x*3/4-rectangleWitdth/2;
			rowLocation[4][1]=y/11;
			rowLocation[4][2]=x*3/4+rectangleWitdth/2;
			rowLocation[4][3]=9*y/22;
			
			rowLocation[5][0]= x*3/4-rectangleWitdth/2;
			rowLocation[5][1]=5*y/11;
			rowLocation[5][2]=x*3/4+rectangleWitdth/2;
			rowLocation[5][3]=17*y/22;
			
			
    	}
		
		//Creating the rectangle object representing each row
		Rect row1= new Rect();
		Rect row2= new Rect();
		Rect row3= new Rect();
		Rect row4= new Rect();
		Rect row5= new Rect();
		Rect row6= new Rect();
		//Assigning the coordinates of each
		
		
		row1.set(rowLocation[0][0],rowLocation[0][1],rowLocation[0][2],rowLocation[0][3]);
		row2.set(rowLocation[1][0],rowLocation[1][1],rowLocation[1][2],rowLocation[1][3]);
		row3.set(rowLocation[2][0],rowLocation[2][1],rowLocation[2][2],rowLocation[2][3]);
		
		
		
		//Drawing the rows
		canvas.drawRect(row1, rowPaint);
		canvas.drawRect(row2, rowPaint);
		canvas.drawRect(row3, rowPaint);
		
		//Drawing the additional Rows that the 2nd map have
		if(mapNumber ==1)
		{
			row4.set(rowLocation[3][0],rowLocation[3][1],rowLocation[3][2],rowLocation[3][3]);
			row5.set(rowLocation[4][0],rowLocation[4][1],rowLocation[4][2],rowLocation[4][3]);
			row6.set(rowLocation[5][0],rowLocation[5][1],rowLocation[5][2],rowLocation[5][3]);

			canvas.drawRect(row4, rowPaint);
			canvas.drawRect(row5, rowPaint);
			canvas.drawRect(row6, rowPaint);
		}
    }
    
    /*
     * This Map will take each Item that it was passed as parameter
     * and will draw it on the Map after its position was generated 
     * by the method GenerateLocationsMap for each Map
     */
    public void drawItemOnMap(Canvas canvas, ArrayList<Item> selectedItems)
    {
    	circleRadius = canvas.getWidth()/75;
    	
    	//This is the Paint used for drawing the Circle
    	Paint itemPaint = new Paint();
		itemPaint.setColor(Color.RED);
		itemPaint.setStyle(Paint.Style.FILL);
		
		//Paint used to write the Text of the item
		Paint textPaint = new Paint();
		textPaint.setColor(Color.BLUE);
		textPaint.setStyle(Paint.Style.FILL);
		textPaint.setTextSize(circleRadius*3);
		
		
		for (Item i : selectedItems)
		{
			canvas.drawCircle(i.getxPosition(), i.getyPosition(),circleRadius,itemPaint);
			
			//If the name of the Item is too long we will truncate it 
			String tempName;
			if (i.getName().length() >8)
			{
				tempName = i.getName().substring(0, 8);
				tempName = tempName + ".";
			}
			else {
				tempName = i.getName()+" ";
			}
			
			//The origin that we have to specify in order to write text is on the left side of 
			//the word. Since we write from left to right, we want to avoid writing into the rows
			//thus we have to consider the case where we are on the right side of the row and when we are
			// on the left side
			
			//Case we are on right side
			if(i.getRowSide()== 1)
			{
				canvas.drawText(tempName, i.getxPosition()+ circleRadius, i.getyPosition(), textPaint);
			}
			
			//Case where we are to the right and we need to push the origin to the right
			// to avoid writing into the rows
			else
			{
					int rowSpacing = rowLocation[2][0]- rowLocation[1][2];
					int xPosit =  rowSpacing;
					for(int k =1; k<10;k++)
					{
						if(tempName.length()==k)
						{
							canvas.drawText(tempName,i.getxPosition()- k*xPosit/11 -circleRadius , i.getyPosition(), textPaint);
						}
					}
					
			}
		}
	}
    
    /*
     * This method will dynamically generate a location (x,y) for each item
     * All items will be given a location, the location will be stored in the
     * fields of the object Item
     * This method will give locations for Map 1 
     */
    public void generateItemLocationsMap1(Canvas canvas)
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
					//Looping through Each Item 
					for (int itemLocation =0; itemLocation<numberOfItemPerRow;itemLocation++ )
					{	
						//Assign the Xcordinate depending on which side of the row we are at
						if(rowSide ==0)
						{
							xcord = rowLocation[rowNumber][0];
						}else
						{
							
							xcord = rowLocation[rowNumber][2];
						}
						
						//Assign the Ycordinate depending on which row we are at
						//In order not to have the words of adjacent rows override one another
						//We made the items located in the middle rows a small offset in the Y axe
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
							
							//Storing the coordinates in their corresponding field in the object Item
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
    
    /*
     * This method will dynamically generate a location (x,y) for each item
     * All items will be given a location, the location will be stored in the
     * fields of the object Item
     * This method will give locations for Map 1 
     */
    
    public void generateItemLocationsMap2(Canvas canvas)
    {
    	int xcord;
		int ycord;
		
		Paint itemPaint = new Paint();
		itemPaint.setColor(Color.RED);
		itemPaint.setStyle(Paint.Style.FILL);
		
    	int rowLength =rowLocation[2][3]- rowLocation[2][1];

    	int numberOfItemPerRow = 4;
    	
    
		
		int i=0;
		
    	//Looping through each row 
    	for (int rowNumber=0; rowNumber<6;rowNumber++)
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
							
						//Assign the Ycordinate depending on which row we are at
						//In order not to have the words of adjacent rows override one another
						//We made the items located in the middle rows a small offset in the Y axe
						if(rowNumber == 2 || rowNumber == 3)
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
							//Storing the coordinates in their corresponding field in the object Item
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
