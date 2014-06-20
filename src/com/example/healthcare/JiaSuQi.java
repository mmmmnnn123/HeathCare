package com.example.healthcare;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

class DrawView extends View
{
	public float currentX=800;
	public float currentY=600;
	public int mIndex=0;
	public float x2=(float)Math.random()*1000;
	public float y2=(float) Math.random()*1700;
	public float x3=(float)Math.random()*1000;
	public float y3=(float) Math.random()*1700;
	public float x4=(float)Math.random()*1000;
	public float y4=(float) Math.random()*1700;
	public float x5=(float)Math.random()*1000;
	public float y5=(float) Math.random()*1700;
	public float x6=(float)Math.random()*1000;
	public float y6=(float) Math.random()*1700;
	
	public DrawView(Context context)
	{
		super(context);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas)
	{   
		super.onDraw(canvas);
		Paint p1=new Paint();
		
		p1.setColor(Color.BLUE);
		canvas.drawCircle(currentX,currentY,50,p1); 
		
		
		/*
		 * Create 5 balls.
		Paint[] P=new Paint[6];
		for(Paint p: P)
		{
			p.setColor(Color.RED);
			canvas.drawCircle((float)Math.random()*1000,(float) Math.random()*1700, 50, p);
		}*/
		Paint p2=new Paint();
		p2.setColor(Color.RED);
		canvas.drawCircle(x2,y2,50,p2); 
		Paint p3=new Paint();
		p3.setColor(Color.RED);
		canvas.drawCircle(x3,y3,50,p3); 
		Paint p4=new Paint();
		p4.setColor(Color.RED);
		canvas.drawCircle(x4,y4,50,p4); 
		Paint p5=new Paint();
		p5.setColor(Color.RED);
		canvas.drawCircle(x5,y5,50,p5); 
		Paint p6=new Paint();
		p6.setColor(Color.RED);
		canvas.drawCircle(x6,y6,50,p6); 
	}
	
}

/*
class FreeBall extends View
{
  public FreeBall(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

  private float x;
  private float y;
  public void setX(float x)
  {
	  this.x=x;
  }
  
  public float getX()
  {
	  return this.x;
  }
  
  public void setY(float y)
  {
	  this.y=y;
  }
  
  public float getY()
  {
	  return this.y;
  }
  
  @SuppressLint("DrawAllocation")
public void onDraw(Canvas canvas)
	{   
		super.onDraw(canvas);
		Paint p=new Paint();
		
		p.setColor(Color.RED);
		canvas.drawCircle(230,450,50,p);
	}
}
*/
public class JiaSuQi extends Activity implements SensorEventListener
{   
	public DrawView draw;
	//public FreeBall freeball;
	private SensorManager mySensorManager;
	Toast toast;
	EditText text;
	float gx,gy;
	float vx=0f;
	float vy=0f;
 	
 
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint({ "NewApi", "ShowToast" })
	public void onCreate(Bundle savedInstanceState)
    {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.jiasuqi);
	 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //to keep the screen never sleep;
	 mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	 LinearLayout lay=(LinearLayout)findViewById(R.id.root);
	
	 draw=new DrawView(this);
	// Verbal =new FreeBall(this);
     lay.addView(draw);
     
    // for (int i=0;i<4;i++)
	//  {   
         
		//  freeball.setX(50+100);//(float) (Math.random()*this.getWindowManager().getDefaultDisplay().getWidth()));
		 // freeball.setY(40+100);//(float) (Math.random()*this.getWindowManager().getDefaultDisplay().getHeight()));
		  
		 // lay.addView(freeball);
	      //freeball.postInvalidate(); 
	//  }
    // try
  //   {
      // createFreeBall(lay);
  //   }
  //   catch(Exception e)
   //  {
    	 
   //  }
     new Thread(new MyBall()).start();
     
	
    }
    
	private void endGame(Toast toast)
	{  
		/* Crash, do not know the reason.
		AlertDialog.Builder endDia = new AlertDialog.Builder(this);
		endDia.setTitle("Oh,你挂了！");
		endDia.setMessage("继续玩吗？");
		endDia.setPositiveButton("不，推出", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					JiaSuQi.this.finish();
			}			
		});
		
		endDia.setNegativeButton("继续", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
					
		});
		endDia.create().show();
		*/
	 toast = Toast.makeText(this,"Oh,你挂了!", 1000);
			   toast.setGravity(Gravity.CENTER, 0, 0);
			   LinearLayout toastView = (LinearLayout) toast.getView();
			   ImageView imageCodeProject = new ImageView(getApplicationContext());
			   imageCodeProject.setImageResource(R.drawable.ic_launcher);
			   toastView.addView(imageCodeProject, 0);
			   toast.show();

	}
    
    @SuppressWarnings("deprecation")
	public void onResume()
    {
      super.onResume();
      mySensorManager.registerListener(this,
    		  mySensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
    		  SensorManager.SENSOR_DELAY_GAME);
    }
    
    public void onStop()
    {
    	mySensorManager.unregisterListener(this);
    	super.onStop();	
    }
    
 
	  class MyBall implements Runnable
	    {


		public void run()
	     {
	    	 while(!Thread.currentThread().isInterrupted())
	    	 {
	    		 try
	    		 {
	    			 Thread.sleep(1);
	    		 }
	    		 catch(Exception e)
	    		 {
	    			 e.printStackTrace();
	    		 }
		    	 update();
		    	 draw.postInvalidate(); 
		       	 if((hitBall(draw.currentX,draw.currentY,draw.x2,draw.y2,50,50))
		           		 ||(hitBall(draw.currentX,draw.currentY,draw.x3,draw.y3,50,50))
		           		 ||(hitBall(draw.currentX,draw.currentY,draw.x4,draw.y4,50,50))
		           		 ||(hitBall(draw.currentX,draw.currentY,draw.x5,draw.y5,50,50))
		           		 ||(hitBall(draw.currentX,draw.currentY,draw.x6,draw.y6,50,50))==true)
		            {                   
		           	// endGame(toast);
		            }
		        	
	    	 }
   
	    }
	     
	     
	     // update the position of the mainBall.
	     private void update()
	     {
	    	@SuppressWarnings("deprecation")
	    	int width = getWindowManager().getDefaultDisplay().getWidth(); 
	     	@SuppressWarnings("deprecation")
	    	int height=getWindowManager().getDefaultDisplay().getHeight();
	     	
	          if((draw.currentX>=(width-50))||(draw.currentX<=50))
	          {
		         	 vx=(float) (-vx*4/5.0);
	                 vx=vx+gx*0.01f;
	          }
	          else	        
	        	  vx=vx+gx*0.01f;
	            
	          if((draw.currentY>=(height-225))||(draw.currentY<=50))
	          {
		         	 vy=(float) (-vy*4.0/5.0);	
	                 vy=vy+gy*0.01f;
	          }
	          else
	        	  vy=vy+gy*0.01f;
	      	  draw.currentX=(float) (draw.currentX+vx*0.1);
	          draw.currentY=(float) (draw.currentY+vy*0.1);   

	     }
	    }
	  
	 /* 
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@SuppressWarnings("null")
	private void createFreeBall(LinearLayout lay)
	  {		  
		  for (int i=0;i<4;i++)
		  {   
			  FreeBall freeball = null;
			  freeball.setX((float) (Math.random()*this.getWindowManager().getDefaultDisplay().getWidth()));
			  freeball.setY((float) (Math.random()*this.getWindowManager().getDefaultDisplay().getHeight()));
			  freeball=new FreeBall(this);
			  lay.addView(freeball);
		      freeball.postInvalidate(); 
		  }
	  }*/
	
	// Judge myBall whether hit the freeBall or not.
	private boolean hitBall(float myBall_x,float myBall_y,float freeBall_x,float freeBall_y,float myBallRadius,float freeBallRadius)
	{
		boolean hit=false;
    	float length=(float) Math.sqrt((freeBall_x-myBall_x)*(freeBall_x-myBall_x)+(freeBall_y-myBall_y)*(freeBall_y-myBall_y));
		if(myBallRadius+freeBallRadius>length)
		{
			hit=true;
		}
		return hit;
	}

	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub		
	}
	

	@Override
	// Get the accelerate speed on the X and Y, based on the orientation of the mobile device.
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		float[] values=arg0.values;
		StringBuilder sd=new StringBuilder();
		sd.append("X:");
		sd.append(values[0]);
		sd.append("Y:");
		sd.append(values[1]);
		sd.append("Z:");
		sd.append(values[2]);
		//text.setText(sd.toString());
		gx=(float) -(10*Math.sin(values[2]*Math.PI/180));
		gy=(float) -(10*Math.sin(values[1]*Math.PI/180));
	}
}

    