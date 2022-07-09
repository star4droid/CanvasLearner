package com.star4droid.CanvasTuto;

import com.star4droid.CanvasTuto.SplashActivity;
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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.widget.AdapterView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

public class MainActivity extends Activity {
	
	private ArrayList<HashMap<String, Object>> Lm = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private ImageView imageview1;
	private TextView textview1;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.putExtra("quad", "");
				if (_position == 0) {
					i.setClass(getApplicationContext(), PlaygroundActivity.class);
				}
				if (_position == 1) {
					i.setClass(getApplicationContext(), LinetoActivity.class);
				}
				if (_position == 2) {
					i.putExtra("quad", "true");
					i.setClass(getApplicationContext(), LinetoActivity.class);
				}
				if (_position == 3) {
					i.putExtra("quad", "cubic");
					i.setClass(getApplicationContext(), LinetoActivity.class);
				}
				if (_position == 4) {
					i.putExtra("quad", "arc");
					i.setClass(getApplicationContext(), LinetoActivity.class);
				}
				if (_position == 5) {
					i.setClass(getApplicationContext(), AddCircleActivity.class);
				}
				if (_position == 6) {
					i.putExtra("quad", "oval");
					i.setClass(getApplicationContext(), LinetoActivity.class);
				}
				if (_position == 7) {
					i.putExtra("quad", "rect");
					i.setClass(getApplicationContext(), LinetoActivity.class);
				}
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "PlayGround");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - lineTo");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - quadTo");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - cubicTo");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - arcTo");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - addCircle");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - addOval");
			Lm.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Path - addRect");
			Lm.add(_item);
		}
		
		listview1.setAdapter(new Listview1Adapter(Lm));
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.tuto_csv, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView name = _view.findViewById(R.id.name);
			
			name.setText(_data.get((int)_position).get("name").toString());
			if (0 == _position) {
				imageview1.setImageResource(R.drawable.flat_phone);
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