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
import de.hdodenhof.circleimageview.*;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.widget.CompoundButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

public class AddCircleActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private  Paint paint;
	private  Path.Direction dir = Path.Direction.CW;
	private  float rad;
	private boolean move = false;
	private  DrawV dr;
	private double hfirst = 0;
	
	private FrameLayout frame;
	private LinearLayout linear11;
	private LinearLayout xy;
	private ScrollView linear9;
	private LinearLayout linear10;
	private LinearLayout p1;
	private LinearLayout p2;
	private LinearLayout fill_linear;
	private ImageView full;
	private ImageView imageview4;
	private CheckBox fill_color;
	private TextView textview11;
	private RadioGroup radiogroup1;
	private RadioButton radiobutton1;
	private RadioButton radiobutton2;
	private TextView textview1;
	private LinearLayout xp;
	private LinearLayout yp;
	private TextView textview2;
	private EditText mx;
	private TextView textview3;
	private EditText my;
	private LinearLayout linear13;
	private CircleImageView circleimageview1;
	private TextView code;
	private ImageView back;
	private TextView textview10;
	private ImageView next;
	
	private TimerTask t;
	private Spannable spa;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.add_circle);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		frame = findViewById(R.id.frame);
		linear11 = findViewById(R.id.linear11);
		xy = findViewById(R.id.xy);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		p1 = findViewById(R.id.p1);
		p2 = findViewById(R.id.p2);
		fill_linear = findViewById(R.id.fill_linear);
		full = findViewById(R.id.full);
		imageview4 = findViewById(R.id.imageview4);
		fill_color = findViewById(R.id.fill_color);
		textview11 = findViewById(R.id.textview11);
		radiogroup1 = findViewById(R.id.radiogroup1);
		radiobutton1 = findViewById(R.id.radiobutton1);
		radiobutton2 = findViewById(R.id.radiobutton2);
		textview1 = findViewById(R.id.textview1);
		xp = findViewById(R.id.xp);
		yp = findViewById(R.id.yp);
		textview2 = findViewById(R.id.textview2);
		mx = findViewById(R.id.mx);
		textview3 = findViewById(R.id.textview3);
		my = findViewById(R.id.my);
		linear13 = findViewById(R.id.linear13);
		circleimageview1 = findViewById(R.id.circleimageview1);
		code = findViewById(R.id.code);
		back = findViewById(R.id.back);
		textview10 = findViewById(R.id.textview10);
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
		
		radiobutton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					dir=Path.Direction.CW;
					dr.invalidate();
				}
			}
		});
		
		radiobutton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					dir=Path.Direction.CCW;
					dr.invalidate();
				}
			}
		});
		
		mx.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!(move || mx.getText().toString().equals(""))) {
					p1.setX(Float.parseFloat(mx.getText().toString()));
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
					p1.setY(Float.parseFloat(my.getText().toString()));
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
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), LinetoActivity.class);
				i.putExtra("quad", "arc");
				startActivity(i);
				finish();
			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), LinetoActivity.class);
				i.putExtra("quad", "oval");
				startActivity(i);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		try {
			p1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF57F17));
			p2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF57F17));
			paint = new Paint();
			paint.setColor(Color.GREEN);
			paint.setStrokeWidth(2);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeCap(Paint.Cap.ROUND);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setDither(true);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
			dr=new DrawV(this);
			p1.setTag("d"); 
			p2.setTag ("f");
			xy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFDCEDC8));
			xp.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFFDD835));
			yp.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFFDD835));
			frame.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, 0xFF8BC34A, 0xFFDCEDC8));
			radiobutton1.setChecked(true);
			_setXY();
			try {
				TypedValue typedValue = new TypedValue();
				getApplicationContext().getTheme().resolveAttribute(16843868, typedValue, true);
				next.setBackgroundResource(typedValue.resourceId);
				next.setClickable(true);
			} catch(Exception ze) {
				 
			}
		} catch (Exception e) {
			SketchwareUtil.showMessage(getApplicationContext(), e.toString());
		}
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
			p1.setX(frame.getMeasuredWidth()/2); 
			p1.setY(frame.getMeasuredHeight()/2);
			p2.setX((frame.getMeasuredWidth()/2)-150);
			p2.setY(frame.getMeasuredHeight()/2);
			_getAxis();
			_DragAble(p1);
			_DragAble(p2);
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
			float xx=(p1.getX()+w1)-(p2.getX()+w2);
			float yy=(p2.getY()+h1)-(p2.getY()+h2);
			float r=(float)Math.sqrt((xx*xx)+(yy*yy));
			rad=r;
			path.moveTo((float)(p1.getX()+(w1)),(float)(p1.getY()+h1));
			path.addCircle(p1.getX()+w1,p1.getY()+h1,r,dir);
			_getAxis();
			c.drawPath(path,paint);
		} 
	}
	
	
	public void _getAxis() {
		mx.setText(String.valueOf((long)(p1.getX())));
		my.setText(String.valueOf((long)(p1.getY())));
		code.setText("Paint paint = new Paint();\npaint.setColor(Color.GREEN);\npaint.setStrokeWidth(2);\npaint.setStyle(Paint.Style.STROKE);\npaint.setStrokeCap(Paint.Cap.ROUND);\npaint.setStrokeJoin(Paint.Join.ROUND);\npaint.setDither(true);\npaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));");
		code.setText(code.getText().toString().concat("\nPath path = new Path(); \npath.moveTo(".concat(String.valueOf((long)(p1.getX())))));
		code.setText(code.getText().toString().concat(",".concat(String.valueOf((long)(p1.getY())).concat(");\n//addCircle(x,y,radius,Direction);\npath.addCircle("))));
		code.setText(code.getText().toString().concat(String.valueOf((long)(p1.getX())).concat(",")));
		code.setText(code.getText().toString().concat(String.valueOf((long)(p1.getY())).concat(",")));
		code.setText(code.getText().toString().concat(String.valueOf((long)(rad)).concat(",")));
		if (dir==Path.Direction.CW) {
			code.setText(code.getText().toString().concat("Path.Direction.CW);"));
		}
		else {
			code.setText(code.getText().toString().concat("Path.Direction.CCW);"));
		}
		spa = SpannableStringBuilder.valueOf(code.getText().toString());
		spa.setSpan(new android.text.style.ForegroundColorSpan(Color.parseColor("#F44336")), (int)(code.getText().toString().indexOf("path.addCircle(")), (int)(code.getText().toString().length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		code.setText(spa);
	}
	
	
	public void _DragAble(final View _view) {
		_view.setOnTouchListener(new OnTouchListener() {
			PointF DownPT = new PointF();
			PointF StartPT = new PointF();
			@Override public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:PointF mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
					_view.setX((int)(StartPT.x+mv.x));
					if (_view.getTag().toString().equals("d")){
							_view.setY((int)(StartPT.y+mv.y));
							p2.setY((int)(StartPT.y+mv.y));
						}
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