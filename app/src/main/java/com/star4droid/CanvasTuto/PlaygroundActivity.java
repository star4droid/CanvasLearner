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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.AdapterView;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import com.star4droid.CanvasTuto.porter.*;

public class PlaygroundActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> map = new HashMap<>();
	private  porter.PaintSettings selected;
	private  Point point;
	private double pos = 0;
	private  DrawV dr;
	
	private ArrayList<HashMap<String, Object>> PathsLM = new ArrayList<>();
	
	private LinearLayout linear1;
	private FrameLayout frame;
	private LinearLayout pathsLinear;
	private ImageView picker;
	private ScrollView vscroll1;
	private TextView textview1;
	private ListView pathsLV;
	private LinearLayout linear;
	private TextView codes;
	private LinearLayout linearOfCodes;
	private ImageView addCode;
	
	private Intent i = new Intent();
	private AlertDialog customD;
	private TimerTask ttimer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.playground);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		frame = findViewById(R.id.frame);
		pathsLinear = findViewById(R.id.pathsLinear);
		picker = findViewById(R.id.picker);
		vscroll1 = findViewById(R.id.vscroll1);
		textview1 = findViewById(R.id.textview1);
		pathsLV = findViewById(R.id.pathsLV);
		linear = findViewById(R.id.linear);
		codes = findViewById(R.id.codes);
		linearOfCodes = findViewById(R.id.linearOfCodes);
		addCode = findViewById(R.id.addCode);
		
		pathsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (0 == _position) {
					double n=1;
					while(_listmap(PathsLM, "path".concat(String.valueOf((long)(n))), "name")) {
						n++;
					}
					map = new HashMap<>();
					map.put("name", "path".concat(String.valueOf((long)(n))));
					map.put("path", new PaintSettings());
					PathsLM.add(map);
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
				else {
					linearOfCodes.removeAllViews();
					selected =(porter.PaintSettings)(PathsLM.get((int)_position).get("path")); 
					pos = _position;
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
					for (View v:selected.codes){
						try {
							((ViewGroup)v.getParent()).removeView(v);
						} catch(Exception ex){}
						linearOfCodes.addView(v);
					}
				}
			}
		});
		
		pathsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position > 0) {
					PathsLM.remove((int)(_position));
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
				return true;
			}
		});
		
		addCode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(selected==null)) {
					final AlertDialog customD = new AlertDialog.Builder(PlaygroundActivity.this).create();
					LayoutInflater customDLI = getLayoutInflater();
					View customDCV = (View) customDLI.inflate(R.layout.codes2add, null);
					customD.setView(customDCV);
					final Button moveTo = (Button)
					customDCV.findViewById(R.id.moveTo);
					final Button lineTo = (Button)
					customDCV.findViewById(R.id.lineTo);
					final Button quadTo = (Button)
					customDCV.findViewById(R.id.quadTo);
					final Button cubicTo = (Button)
					customDCV.findViewById(R.id.cubicTo);
					final Button arcTo = (Button)
					customDCV.findViewById(R.id.arcTo);
					final Button addCircle = (Button)
					customDCV.findViewById(R.id.addCircle);
					final Button addOval = (Button)
					customDCV.findViewById(R.id.addOval);
					final Button addRect = (Button)
					customDCV.findViewById(R.id.addRect);
					final Button cancel = (Button)
					customDCV.findViewById(R.id.cancel);
					customD.show();
					cancel.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							customD.dismiss();
						}
					});
					moveTo.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							moveto m=new moveto(PlaygroundActivity.this);
							linearOfCodes.addView(m);
							selected.codes.add(m);
							customD.dismiss();
							_update_current_withCodes();
						}
					});
					lineTo.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							lineto m=new lineto(PlaygroundActivity.this);
							linearOfCodes.addView(m);
							selected.codes.add(m);
							customD.dismiss();
							_update_current_withCodes();
						}
					});
					cubicTo.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							cubicto m=new cubicto(PlaygroundActivity.this);
							linearOfCodes.addView(m);
							selected.codes.add(m);
							customD.dismiss();
							_update_current_withCodes();
						}
					});
					quadTo.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							quadto m=new quadto(PlaygroundActivity.this);
							linearOfCodes.addView(m);
							selected.codes.add(m);
							customD.dismiss();
							_update_current_withCodes();
						}
					});
				}
			}
		});
	}
	
	private void initializeLogic() {
		dr= new DrawV(this);
		frame.addView(dr);
		dr.setLayoutParams(new FrameLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (ViewGroup.LayoutParams.MATCH_PARENT)));
		map = new HashMap<>();
		map.put("name", "add");
		PathsLM.add(map);
		pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
		frame.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)8, (int)1, 0xFFFFC107, 0xFFFFECB3));
		_DragAble(picker);
		try {
				((ViewGroup)picker.getParent()).removeView(picker);
		} catch (Exception exp65676) {
				 
		}
		((ViewGroup) frame).addView(picker);
		pos = 1;
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		_update_current_withCodes();
	}
	public boolean _listmap(final ArrayList<HashMap<String, Object>> _lmp, final String _str, final String _key) {
		double n=0;
		boolean b=false;
		for(int _repeat13 = 0; _repeat13 < (int)(_lmp.size()); _repeat13++) {
			if (_lmp.get((int)n).containsKey(_key)) {
				if (_str.equals(_lmp.get((int)n).get(_key).toString())) {
					b=true;
					break;
				}
			}
			n++;
		}
		return b;
	}
	
	
	public void _update_current_withCodes() {
		if (!(selected==null)) {
			selected.path=new Path();
			for (int i = 0; i < ((ViewGroup) linearOfCodes).getChildCount(); i++) {
				View v = ((ViewGroup)linearOfCodes).getChildAt(i);
				if (v instanceof moveto) {
					moveto m=(moveto)v;
					selected.path.moveTo(m.p.x,m.p.y);
				} else {
					if (v instanceof lineto) {
						lineto m=(lineto)v;
						selected.path.lineTo(m.p.x,m.p.y);
					} else {
						if (v instanceof cubicto) {
							cubicto m=(cubicto)v;
							selected.path.cubicTo(m.p.x,m.p.y,m.p2.x,m.p2.y,m.p3.x,m.p3.y);
						} else {
							if (v instanceof quadto) {
								quadto m=(quadto)v;
								selected.path.quadTo(m.p.x,m.p.y,m.p2.x,m.p2.y);
							} else {
								 
							}
						}
					}
				}
			}
		}
		if(dr!=null){
dr.invalidate();
}
	}
	
	
	public void _extra() {
	}
	class moveto extends LinearLayout {
		public moveto(Context ctx)  {
			super(ctx);
			LayoutInflater thisLL = getLayoutInflater(); 
			View thisVV = thisLL.inflate(R.layout.tow, null);
			this.removeAllViews();
			((LinearLayout) this).addView(thisVV);
			final TextView text = (TextView)
			thisVV.findViewById(R.id.text);
			final ImageView img = (ImageView)
			thisVV.findViewById(R.id.img);
			final LinearLayout linear = (LinearLayout)
			thisVV.findViewById(R.id.linear);
			final ImageView up = (ImageView)
			thisVV.findViewById(R.id.up);
			final ImageView down = (ImageView)
			thisVV.findViewById(R.id.down);
			linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFE6EE9C));
			img.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p);
				}
			});
			this.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					double position = ((ViewGroup)_view.getParent()).indexOfChild(_view);
					selected.codes.remove((int)position);
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
					return false;
				}
			});
			final View _v=this;
			up.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position-1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
			down.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position+1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
		}
		Point p=new Point(0,0);
		int position=0;
	}
	class lineto extends LinearLayout {
		public lineto(Context ctx)  {
			super(ctx);
			LayoutInflater thisLL = getLayoutInflater(); 
			View thisVV = thisLL.inflate(R.layout.tow, null);
			this.removeAllViews();
			((LinearLayout) this).addView(thisVV);
			final TextView text = (TextView)
			thisVV.findViewById(R.id.text);
			final ImageView up = (ImageView)
			thisVV.findViewById(R.id.up);
			final ImageView down = (ImageView)
			thisVV.findViewById(R.id.down);
			final ImageView img = (ImageView)
			thisVV.findViewById(R.id.img);
			text.setText("LineTo");
			final LinearLayout linear = (LinearLayout)
			thisVV.findViewById(R.id.linear);
			linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFE6EE9C));
			img.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p);
				}
			});
			this.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					double position = ((ViewGroup)_view.getParent()).indexOfChild(_view);
					selected.codes.remove((int)position);
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
					return false;
				}
			});
			final View _v=this;
			up.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position-1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
			down.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position+1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
		}
		Point p=new Point(0,0);
	}
	public class DrawV extends View {
		public DrawV(Context ctx){
			super(ctx);
		} 
		protected void onDraw(Canvas c){
			super.onDraw(c);
			if(selected!=null) c.drawPath(selected.path,selected.paint);
			double n=0;
			for(int _repeat33 = 0; _repeat33 < (int)(PathsLM.size()); _repeat33++) {
				if (n > 0) {
					Path path= ((porter.PaintSettings)(PathsLM.get((int)n).get("path"))).path;
					Paint paint= ((porter.PaintSettings)(PathsLM.get((int)n).get("path"))).paint;
					c.drawPath(path,paint);
				}
				n++;
			}
		}
	}
	class quadto extends LinearLayout {
		public quadto(Context ctx)  {
			super(ctx);
			LayoutInflater thisLL = getLayoutInflater(); 
			View thisVV = thisLL.inflate(R.layout.tow, null);
			this.removeAllViews();
			((LinearLayout) this).addView(thisVV);
			final TextView text = (TextView)
			thisVV.findViewById(R.id.text);
			final ImageView img = (ImageView)
			thisVV.findViewById(R.id.img);
			final ImageView img2 = (ImageView)
			thisVV.findViewById(R.id.img2);
			final ImageView up = (ImageView)
			thisVV.findViewById(R.id.up);
			final ImageView down = (ImageView)
			thisVV.findViewById(R.id.down);
			img2.setVisibility(View.VISIBLE);
			text.setText("quadTo");
			final LinearLayout linear = (LinearLayout)
			thisVV.findViewById(R.id.linear);
			linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFE6EE9C));
			img.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p);
				}
			});
			this.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					double position = ((ViewGroup)_view.getParent()).indexOfChild(_view);
					selected.codes.remove((int)position);
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
					return false;
				}
			});
			img2.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p2);
				}
			});
			final View _v=this;
			up.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position-1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
			down.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position+1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
		}
		Point p=new Point(0,0);
		Point p2=new Point(0,0);
	}
	class cubicto extends LinearLayout {
		public cubicto(Context ctx)  {
			super(ctx);
			LayoutInflater thisLL = getLayoutInflater(); 
			View thisVV = thisLL.inflate(R.layout.tow, null);
			this.removeAllViews();
			((LinearLayout) this).addView(thisVV);
			final TextView text = (TextView)
			thisVV.findViewById(R.id.text);
			final ImageView img = (ImageView)
			thisVV.findViewById(R.id.img);
			final ImageView img2 = (ImageView)
			thisVV.findViewById(R.id.img2);
			final ImageView img3 = (ImageView)
			thisVV.findViewById(R.id.img3);
			img2.setVisibility(View.VISIBLE);
			img3.setVisibility(View.VISIBLE);
			text.setText("cubicTo");
			final LinearLayout linear = (LinearLayout)
			thisVV.findViewById(R.id.linear);
			final ImageView up = (ImageView)
			thisVV.findViewById(R.id.up);
			final ImageView down = (ImageView)
			thisVV.findViewById(R.id.down);
			linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFFE6EE9C));
			img.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p);
				}
			});
			this.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					double position = ((ViewGroup)_view.getParent()).indexOfChild(_view);
					selected.codes.remove((int)position);
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
					return false;
				}
			});
			img2.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p2);
				}
			});
			img3.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					_pick4(p3);
				}
			});
			final View _v=this;
			up.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position-1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
			down.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					double position = ((ViewGroup)_v.getParent()).indexOfChild(_v);
					try {
						selected.codes.remove((int)position);
						selected.codes.add((int)position+1,_v);
					} catch (Exception e) {
						 
					}
					pathsLV.setAdapter(new PathsLVAdapter(PathsLM));
				}
			});
		}
		Point p=new Point(0,0);
		Point p2=new Point(0,0);
		Point p3=new Point(0,0); 
	}
	
	
	public void _pick4(final Point _point) {
		picker.setVisibility(View.VISIBLE);
		point =_point;
		picker.setX(point.x);
		picker.setY(point.y);
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
					StartPT = new PointF(_view.getX(), _view.getY());
					point.x=(int)(_view.getX());
					point.y=(int)(_view.getY());
					_update_current_withCodes();
					break; //xenon
					case MotionEvent.ACTION_DOWN : 
					DownPT.x = event.getX();
					DownPT.y = event.getY();
					StartPT = new PointF(_view.getX(), _view.getY());
					break;
					case MotionEvent.ACTION_UP :
					point.x=(int)(_view.getX());
					point.y=(int)(_view.getY());
					picker.setVisibility(View.GONE);
					_update_current_withCodes();
					break;
					default : break;
				} return true;
			}});
		/* Made by XenonDry!!! */
	}
	
	public class PathsLVAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public PathsLVAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.pp_csv, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final ImageView setting = _view.findViewById(R.id.setting);
			
			if (_position == 0) {
				textview1.setText("add");
				setting.setVisibility(View.GONE);
				imageview1.setImageResource(R.drawable.ic_add_black);
			}
			else {
				textview1.setText(_data.get((int)_position).get("name").toString());
				imageview1.setImageResource(R.drawable.ic_gesture_black);
			}
			setting.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					porter.ps =(porter.PaintSettings)(PathsLM.get((int)_position).get("path")); 
					if (!(porter.ps.paint==null)) {
						i.setClass(getApplicationContext(), PaintModifyActivity.class);
						startActivity(i);
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "null reference!");
					}
				}
			});
			try {
				if ((_position == pos) && (_position > 0)) {
					if (setting.getVisibility() == View.VISIBLE) {
						linear1.setBackgroundColor(0xFFF1F8E9);
						selected =(porter.PaintSettings)(PathsLM.get((int)_position).get("path"));
						linearOfCodes.removeAllViews();
						for (View v:selected.codes){
							try {
								((ViewGroup)v.getParent()).removeView(v);
							} catch(Exception ex){}
							linearOfCodes.addView(v);
						}
					}
				}
			} catch (Exception e) {
				 
			}
			
			return _view;
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