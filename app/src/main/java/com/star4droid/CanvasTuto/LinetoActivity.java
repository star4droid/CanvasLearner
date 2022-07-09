package com.star4droid.CanvasTuto;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.SeekBar;
import de.hdodenhof.circleimageview.*;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

public class LinetoActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private  DrawV dr;
	private  Paint paint;
	private boolean move = false;
	private boolean quad = false;
	private double hfirst = 0;
	private boolean cubic = false;
	private boolean arc = false;
	private  float left = 0;
	private  float top;
	private  float right;
	private  float bottom;
	private boolean oval = false;
	private  Path.Direction dir = Path.Direction.CW;
	private boolean rect = false;
	
	private FrameLayout frame;
	private LinearLayout xy;
	private LinearLayout lxy;
	private LinearLayout angles;
	private ScrollView linear9;
	private LinearLayout linear10;
	private LinearLayout p1;
	private LinearLayout p2;
	private LinearLayout p3;
	private LinearLayout p4;
	private LinearLayout fill_linear;
	private ImageView full;
	private ImageView imageview4;
	private CheckBox fill_color;
	private TextView moveT;
	private LinearLayout xp;
	private LinearLayout yp;
	private TextView xx;
	private EditText mx;
	private TextView yy;
	private EditText my;
	private TextView lineT;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private LinearLayout linear;
	private LinearLayout linearc;
	private LinearLayout lxp;
	private LinearLayout lyp;
	private TextView tx;
	private EditText lx;
	private TextView ty;
	private EditText ly;
	private LinearLayout linear18;
	private LinearLayout linear17;
	private TextView textview10;
	private EditText x2;
	private TextView txxx;
	private EditText y2;
	private LinearLayout linear20;
	private LinearLayout linear21;
	private TextView textview12;
	private EditText x3;
	private TextView textview11;
	private EditText y3;
	private LinearLayout linear23;
	private LinearLayout linear24;
	private CheckBox fm;
	private TextView textview13;
	private SeekBar sa;
	private TextView textview14;
	private SeekBar swa;
	private LinearLayout linear13;
	private CircleImageView circleimageview1;
	private TextView code;
	private ImageView back;
	private TextView title;
	private ImageView next;
	
	private TimerTask t;
	private Intent intent = new Intent();
	private Spannable spa;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.lineto);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		frame = findViewById(R.id.frame);
		xy = findViewById(R.id.xy);
		lxy = findViewById(R.id.lxy);
		angles = findViewById(R.id.angles);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		p1 = findViewById(R.id.p1);
		p2 = findViewById(R.id.p2);
		p3 = findViewById(R.id.p3);
		p4 = findViewById(R.id.p4);
		fill_linear = findViewById(R.id.fill_linear);
		full = findViewById(R.id.full);
		imageview4 = findViewById(R.id.imageview4);
		fill_color = findViewById(R.id.fill_color);
		moveT = findViewById(R.id.moveT);
		xp = findViewById(R.id.xp);
		yp = findViewById(R.id.yp);
		xx = findViewById(R.id.xx);
		mx = findViewById(R.id.mx);
		yy = findViewById(R.id.yy);
		my = findViewById(R.id.my);
		lineT = findViewById(R.id.lineT);
		linear14 = findViewById(R.id.linear14);
		linear15 = findViewById(R.id.linear15);
		linear = findViewById(R.id.linear);
		linearc = findViewById(R.id.linearc);
		lxp = findViewById(R.id.lxp);
		lyp = findViewById(R.id.lyp);
		tx = findViewById(R.id.tx);
		lx = findViewById(R.id.lx);
		ty = findViewById(R.id.ty);
		ly = findViewById(R.id.ly);
		linear18 = findViewById(R.id.linear18);
		linear17 = findViewById(R.id.linear17);
		textview10 = findViewById(R.id.textview10);
		x2 = findViewById(R.id.x2);
		txxx = findViewById(R.id.txxx);
		y2 = findViewById(R.id.y2);
		linear20 = findViewById(R.id.linear20);
		linear21 = findViewById(R.id.linear21);
		textview12 = findViewById(R.id.textview12);
		x3 = findViewById(R.id.x3);
		textview11 = findViewById(R.id.textview11);
		y3 = findViewById(R.id.y3);
		linear23 = findViewById(R.id.linear23);
		linear24 = findViewById(R.id.linear24);
		fm = findViewById(R.id.fm);
		textview13 = findViewById(R.id.textview13);
		sa = findViewById(R.id.sa);
		textview14 = findViewById(R.id.textview14);
		swa = findViewById(R.id.swa);
		linear13 = findViewById(R.id.linear13);
		circleimageview1 = findViewById(R.id.circleimageview1);
		code = findViewById(R.id.code);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		next = findViewById(R.id.next);
		
		fill_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				fill_color.setChecked(!fill_color.isChecked());
			}
		});
		
		full.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (hfirst == 0) {
					hfirst = frame.getHeight();
					frame.setLayoutParams(new LinearLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (ViewGroup.LayoutParams.MATCH_PARENT)));
					full.setImageResource(R.drawable.ic_fullscreen_exit_black);
				}
				else {
					frame.setLayoutParams(new LinearLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (hfirst)));
					hfirst = 0;
					full.setImageResource(R.drawable.open);
				}
			}
		});
		
		fill_color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					paint.setStyle(Paint.Style.FILL_AND_STROKE);
				}
				else {
					paint.setStyle(Paint.Style.STROKE);
				}
				dr.invalidate();
			}
		});
		
		mx.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || mx.getText().toString().equals(""))) {
					if(!(arc|oval|rect)) p1.setX(Float.parseFloat(mx.getText().toString()));
					top = Float.parseFloat(mx.getText().toString());
					dr.invalidate();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		my.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || my.getText().toString().equals(""))) {
					if(!(arc|oval|rect)) p1.setY(Float.parseFloat(my.getText().toString()));
					bottom = Float.parseFloat(my.getText().toString());
					dr.invalidate();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		lx.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || lx.getText().toString().equals(""))) {
					if(!(arc|oval)) p2.setX(Float.parseFloat(lx.getText().toString()));
					left = Float.parseFloat(lx.getText().toString());
					dr.invalidate();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		ly.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || ly.getText().toString().equals(""))) {
					if(!(arc|oval)) p2.setY(Float.parseFloat(ly.getText().toString()));
					right = Float.parseFloat(ly.getText().toString());
					dr.invalidate();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		x2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || x2.getText().toString().equals(""))) {
					p3.setX(Float.parseFloat(x2.getText().toString()));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		y2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || y2.getText().toString().equals(""))) {
					p3.setY(Float.parseFloat(y2.getText().toString()));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		x3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || x3.getText().toString().equals(""))) {
					p3.setX(Float.parseFloat(x3.getText().toString()));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		y3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || y3.getText().toString().equals(""))) {
					p3.setX(Float.parseFloat(y3.getText().toString()));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		fm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					dir = Path.Direction.CCW;
				}
				else {
					dir = Path.Direction.CW;
				}
				dr.invalidate();
				_getAxis();
			}
		});
		
		sa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				dr.invalidate();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
		
		swa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				dr.invalidate();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), LinetoActivity.class);
				if (quad) {
					intent.putExtra("quad", "");
				}
				if (cubic) {
					intent.putExtra("quad", "true");
				}
				if (oval) {
					intent.setClass(getApplicationContext(), AddCircleActivity.class);
				}
				if (arc) {
					intent.putExtra("quad", "cubic");
				}
				if (rect) {
					intent.putExtra("quad", "oval");
				}
				startActivity(intent);
				finish();
			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (rect) {
					
				}
				else {
					if (quad) {
						intent.setClass(getApplicationContext(), LinetoActivity.class);
						intent.putExtra("quad", "cubic");
					}
					else {
						if (cubic) {
							intent.setClass(getApplicationContext(), LinetoActivity.class);
							intent.putExtra("quad", "arc");
						}
						else {
							if (arc) {
								intent.setClass(getApplicationContext(), AddCircleActivity.class);
							}
							else {
								if (oval) {
									intent.setClass(getApplicationContext(), LinetoActivity.class);
									intent.putExtra("quad", "rect");
								}
								else {
									intent.setClass(getApplicationContext(), LinetoActivity.class);
									intent.putExtra("quad", "true");
								}
							}
						}
					}
					startActivity(intent);
					finish();
				}
			}
		});
	}
	
	private void initializeLogic() {
		dr=new DrawV(this);
		if (getIntent().hasExtra("quad")) {
			quad = getIntent().getStringExtra("quad").equals("true");
			cubic = getIntent().getStringExtra("quad").equals("cubic");
			arc = getIntent().getStringExtra("quad").equals("arc");
			oval = getIntent().getStringExtra("quad").equals("oval");
			rect = getIntent().getStringExtra("quad").equals("rect");
			if (quad) {
				title.setText("quadTo Tutorial");
				lineT.setText("quadTo");
				p3.setVisibility(View.VISIBLE);
				linear.setVisibility(View.VISIBLE);
				back.setVisibility(View.VISIBLE);
				tx.setText("x1:");
				ty.setText("x2:");
				fill_linear.setVisibility(View.VISIBLE);
			}
			if (cubic) {
				title.setText("cubicTo Tutorial");
				lineT.setText("cubicTo");
				p3.setVisibility(View.VISIBLE);
				p4.setVisibility(View.VISIBLE);
				linear.setVisibility(View.VISIBLE);
				linearc.setVisibility(View.VISIBLE);
				back.setVisibility(View.VISIBLE);
				tx.setText("x1:");
				ty.setText("x2:");
				fill_linear.setVisibility(View.VISIBLE);
			}
			if (arc) {
				title.setText("arcTo Tutorial");
				tx.setText("LEFT: ");
				ty.setText("RIGHT: ");
				xx.setText("TOP: ");
				yy.setText("BOTTOM: ");
				lineT.setText("");
				moveT.setText("");
				angles.setVisibility(View.VISIBLE);
				p2.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				fill_linear.setVisibility(View.VISIBLE);
			}
			if (oval) {
				back.setVisibility(View.VISIBLE);
				title.setText("addOval Tutorial");
				tx.setText("LEFT: ");
				ty.setText("RIGHT: ");
				xx.setText("TOP: ");
				yy.setText("BOTTOM: ");
				lineT.setText("");
				moveT.setText("");
				fm.setText("Direction: CCW");
				angles.setVisibility(View.VISIBLE);
				((LinearLayout) angles).getChildAt((int) 1).setVisibility(View.GONE);
				((LinearLayout) angles).getChildAt((int) 0).setVisibility(View.GONE);
				p2.setVisibility(View.GONE);
				fill_linear.setVisibility(View.VISIBLE);
			}
			if (rect) {
				back.setVisibility(View.VISIBLE);
				title.setText("addRect Tutorial");
				tx.setText("LEFT: ");
				ty.setText("RIGHT: ");
				xx.setText("TOP: ");
				yy.setText("BOTTOM: ");
				lineT.setText("");
				moveT.setText("");
				fm.setText("Direction: CCW");
				angles.setVisibility(View.VISIBLE);
				((LinearLayout) angles).getChildAt((int) 1).setVisibility(View.GONE);
				((LinearLayout) angles).getChildAt((int) 0).setVisibility(View.GONE);
				p1.setVisibility(View.GONE);
				p2.setVisibility(View.GONE);
				fill_linear.setVisibility(View.VISIBLE);
			}
		}
		frame.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, 0xFF8BC34A, 0xFFDCEDC8));
		xy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFDCEDC8));
		lxy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFDCEDC8));
		xp.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFFDD835));
		yp.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFFDD835));
		lxp.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFFDD835));
		lyp.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFFDD835));
		angles.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFDCEDC8));
		try {
			TypedValue typedValue = new TypedValue();
			getApplicationContext().getTheme().resolveAttribute(16843868, typedValue, true);
			next.setBackgroundResource(typedValue.resourceId);
			next.setClickable(true);
		} catch(Exception ze) {
			 
		}
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_setXY();
					}
				});
			}
		};
		_timer.schedule(t, (int)(500));
		p1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF57F17));
		p2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF57F17));
		p3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF57F17));
		p4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF57F17));
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(2);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setDither(true);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	}
	
	public void _setXY() {
		if (frame.getMeasuredWidth()==0) {
			t = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_setXY();
						}
					});
				}
			};
			_timer.schedule(t, (int)(100));
		}
		else {
			p1.setX(5); 
			p1.setY(frame.getMeasuredHeight()/2);
			p2.setX(frame.getMeasuredWidth()-80);
			p2.setY(frame.getMeasuredHeight()/2);
			p3.setX(frame.getMeasuredWidth()/2);
			p3.setY(frame.getMeasuredHeight()/2);
			p4.setX(frame.getMeasuredWidth()/4);
			p4.setY(frame.getMeasuredHeight()/2);
			left =100;
			top = 0;
			right =500;
			bottom =250;
			if (oval || arc) {
				p1.setX(250); 
				p1.setY(50);
			}
			_getAxis();
			_DragAble(p1);
			_DragAble(p2);
			_DragAble(p3);
			_DragAble(p4);
			if (oval || (arc || (cubic || (rect || quad)))) {
				mx.setText(String.valueOf((long)(top)));
				my.setText(String.valueOf((long)(bottom)));
				lx.setText(String.valueOf((long)(left)));
				ly.setText(String.valueOf((long)(right)));
			}
			dr = new DrawV(this);
			frame.addView(dr);
			dr.setLayoutParams(new FrameLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (ViewGroup.LayoutParams.MATCH_PARENT)));
			dr.invalidate();
		}
	}
	class DrawV extends View {
		public DrawV(Context ctx){
			super(ctx);
			setLayerType(View.LAYER_TYPE_HARDWARE,null);
			setBackgroundColor(Color.TRANSPARENT);
		}
		@Override
		protected void onDraw(Canvas c){
			super.onDraw(c);
			Path path = new Path();
			float w1=p1.getMeasuredWidth()/2;
			float h1=p1.getMeasuredHeight()/2;
			float w2=p2.getMeasuredWidth()/2;
			float h2=p2.getMeasuredHeight()/2;
			float w3=p3.getMeasuredWidth()/2;
			float h3=p3.getMeasuredHeight()/2;
			float w4=p4.getMeasuredWidth()/2;
			float h4=p4.getMeasuredHeight()/2;
			
			path.moveTo(p1.getX()+w1,p1.getY()+h1);
			if (!(quad|cubic|arc|rect|oval))path.lineTo((float)(p2.getX()+w2),(float)(p2.getY()+h2));
			if(quad) path.quadTo(p3.getX()+w3,p3.getY()+h3,(float)(p2.getX()+w2),(float)(p2.getY()+h2));
			if(cubic) path.cubicTo(p4.getX()+w4,p4.getY()+h4,p3.getX()+w3,p3.getY()+h3,(float)(p2.getX()+w2),(float)(p2.getY()+h2));
			if (oval) path.addOval(left,top,right,bottom,dir);
			if (rect) path.addRect(left,top,right,bottom,dir);
			if (arc) path.arcTo(left,top,right,bottom,(float)(sa.getProgress()),(float)(swa.getProgress()),fm.isChecked());
			if(arc) _getAxis();
			c.drawPath(path,paint);
		} 
	}
	
	
	public void _DragAble(final View _view) {
		_view.setOnTouchListener(new OnTouchListener() {
			PointF DownPT = new PointF();
			PointF StartPT = new PointF();
			@Override public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:PointF mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
					_view.setX((int)(StartPT.x+mv.x));
					_view.setY((int)(StartPT.y+mv.y));
					dr.invalidate();
					_getAxis();
					StartPT = new PointF(_view.getX(), _view.getY());
					break; //xenon
					case MotionEvent.ACTION_DOWN : 
					move=true;
					DownPT.x = event.getX();
					DownPT.y = event.getY();
					StartPT = new PointF(_view.getX(), _view.getY());
					break;
					case MotionEvent.ACTION_UP : 
					move=false;
					break;
					default : break;
				} return true;
			}});
		/* Made by XenonDry!!! */
	}
	
	
	public void _getAxis() {
		if (arc) {
			code.setText("Paint paint = new Paint();\npaint.setColor(Color.GREEN);\npaint.setStrokeWidth(2);\npaint.setStyle(Paint.Style.STROKE);\npaint.setStrokeCap(Paint.Cap.ROUND);\npaint.setStrokeJoin(Paint.Join.ROUND);\npaint.setDither(true);\npaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));");
			code.setText(code.getText().toString().concat("\nPath path = new Path(); \npath.moveTo(".concat(String.valueOf((long)(p1.getX())).concat(",".concat(String.valueOf((long)(p1.getY())))).concat(");\npath.arcTo("))));
			code.setText(code.getText().toString().concat(String.valueOf((long)(left)).concat(",").concat(String.valueOf((long)(top)).concat(",".concat(String.valueOf((long)(right)).concat(",".concat(String.valueOf((long)(bottom)).concat(",".concat(String.valueOf((long)(sa.getProgress())).concat(",".concat(String.valueOf((long)(swa.getProgress())).concat(",".concat(((Object)(fm.isChecked())).toString() .concat(");\ncanvas.drawPath(path,paint);"))))))))))))));
			spa = SpannableStringBuilder.valueOf(code.getText().toString());
			spa.setSpan(new android.text.style.ForegroundColorSpan(Color.parseColor("#F44336")), (int)(code.getText().toString().indexOf("path.arcTo(")), (int)(code.getText().toString().length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			code.setText(spa);
		}
		else {
			if (oval) {
				String dirS="Path.Direction.CW";
				if (fm.isChecked()) {
					dirS="Path.Direction.CCW";
				}
				code.setText("Paint paint = new Paint();\npaint.setColor(Color.GREEN);\npaint.setStrokeWidth(2);\npaint.setStyle(Paint.Style.STROKE);\npaint.setStrokeCap(Paint.Cap.ROUND);\npaint.setStrokeJoin(Paint.Join.ROUND);\npaint.setDither(true);\npaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));");
				code.setText(code.getText().toString().concat("\nPath path = new Path(); \npath.moveTo(".concat(String.valueOf((long)(p1.getX())).concat(",".concat(String.valueOf((long)(p1.getY())))).concat(");\npath.addOval("))));
				code.setText(code.getText().toString().concat(String.valueOf((long)(left)).concat(",").concat(String.valueOf((long)(top)).concat(",".concat(String.valueOf((long)(right)).concat(",".concat(String.valueOf((long)(bottom)).concat(",".concat(dirS.concat(");\ncanvas.drawPath(path,paint);"))))))))));
				spa = SpannableStringBuilder.valueOf(code.getText().toString());
				spa.setSpan(new android.text.style.ForegroundColorSpan(Color.parseColor("#F44336")), (int)(code.getText().toString().indexOf("path.addOval(")), (int)(code.getText().toString().length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				code.setText(spa);
			}
			else {
				if (rect) {
					String dirS="Path.Direction.CW";
					if (fm.isChecked()) {
						dirS="Path.Direction.CCW";
					}
					code.setText("Paint paint = new Paint();\npaint.setColor(Color.GREEN);\npaint.setStrokeWidth(2);\npaint.setStyle(Paint.Style.STROKE);\npaint.setStrokeCap(Paint.Cap.ROUND);\npaint.setStrokeJoin(Paint.Join.ROUND);\npaint.setDither(true);\npaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));");
					code.setText(code.getText().toString().concat("\nPath path = new Path(); \npath.moveTo(".concat(String.valueOf((long)(p1.getX())).concat(",".concat(String.valueOf((long)(p1.getY())))).concat(");\npath.addRect("))));
					code.setText(code.getText().toString().concat(String.valueOf((long)(left)).concat(",").concat(String.valueOf((long)(top)).concat(",".concat(String.valueOf((long)(right)).concat(",".concat(String.valueOf((long)(bottom)).concat(",".concat(dirS.concat(");\ncanvas.drawPath(path,paint);"))))))))));
					spa = SpannableStringBuilder.valueOf(code.getText().toString());
					spa.setSpan(new android.text.style.ForegroundColorSpan(Color.parseColor("#F44336")), (int)(code.getText().toString().indexOf("path.addRect(")), (int)(code.getText().toString().length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					code.setText(spa);
				}
				else {
					mx.setText(String.valueOf((long)(p1.getX())));
					my.setText(String.valueOf((long)(p1.getY())));
					lx.setText(String.valueOf((long)(p2.getX())));
					ly.setText(String.valueOf((long)(p2.getY())));
					x2.setText(String.valueOf((long)(p3.getX())));
					y2.setText(String.valueOf((long)(p3.getY())));
					x3.setText(String.valueOf((long)(p4.getX())));
					y3.setText(String.valueOf((long)(p4.getY())));
					code.setText("Paint paint = new Paint();\npaint.setColor(Color.GREEN);\npaint.setStrokeWidth(2);\npaint.setStyle(Paint.Style.STROKE);\npaint.setStrokeCap(Paint.Cap.ROUND);\npaint.setStrokeJoin(Paint.Join.ROUND);\npaint.setDither(true);\npaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));");
					code.setText(code.getText().toString().concat("\nPath path = new Path(); \npath.moveTo(".concat(String.valueOf((long)(p1.getX())))));
					String target = "";
					if (quad) {
						target = "quadTo(".concat(String.valueOf((long)(p3.getX())).concat(",".concat(String.valueOf((long)(p3.getY())).concat(","))));
					}
					else {
						if (cubic) {
							target = "cubicTo(".concat(String.valueOf((long)(p4.getX())).concat(",".concat(String.valueOf((long)(p4.getY())).concat(","))).concat(String.valueOf((long)(p3.getX())).concat(",".concat(String.valueOf((long)(p3.getY())).concat(",")))));
						}
						else {
							if (oval) {
								
							}
							else {
								target = "lineTo(";
							}
						}
					}
					code.setText(code.getText().toString().concat(",".concat(String.valueOf((long)(p1.getY())).concat(");\npath.".concat(target)))));
					code.setText(code.getText().toString().concat(String.valueOf((long)(p2.getX())).concat(",")));
					code.setText(code.getText().toString().concat(String.valueOf((long)(p2.getY())).concat(");\ncanvas.drawPath(path,paint);")));
					spa = SpannableStringBuilder.valueOf(code.getText().toString());
					spa.setSpan(new android.text.style.ForegroundColorSpan(Color.parseColor("#F44336")), (int)(code.getText().toString().indexOf("path."+target)), (int)(code.getText().toString().length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					code.setText(spa);
				}
			}
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}