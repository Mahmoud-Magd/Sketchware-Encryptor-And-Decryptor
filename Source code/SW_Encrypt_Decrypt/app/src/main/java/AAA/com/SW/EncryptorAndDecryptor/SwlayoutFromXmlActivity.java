package AAA.com.SW.EncryptorAndDecryptor;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SwlayoutFromXmlActivity extends AppCompatActivity {
	
	private LinearLayout L_MAIN;
	private LinearLayout LAYER_1_input;
	private LinearLayout LAYER_2_result;
	private EditText et_xmlFileName;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private EditText et_xml_input;
	private Button b_generate;
	private LinearLayout linear2;
	private Button button1;
	private HorizontalScrollView hscroll2;
	private ScrollView vscroll2;
	private TextView tv_result;
	
	private AlertDialog.Builder d;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.swlayout_from_xml);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		L_MAIN = findViewById(R.id.L_MAIN);
		LAYER_1_input = findViewById(R.id.LAYER_1_input);
		LAYER_2_result = findViewById(R.id.LAYER_2_result);
		et_xmlFileName = findViewById(R.id.et_xmlFileName);
		hscroll1 = findViewById(R.id.hscroll1);
		linear1 = findViewById(R.id.linear1);
		vscroll1 = findViewById(R.id.vscroll1);
		et_xml_input = findViewById(R.id.et_xml_input);
		b_generate = findViewById(R.id.b_generate);
		linear2 = findViewById(R.id.linear2);
		button1 = findViewById(R.id.button1);
		hscroll2 = findViewById(R.id.hscroll2);
		vscroll2 = findViewById(R.id.vscroll2);
		tv_result = findViewById(R.id.tv_result);
		d = new AlertDialog.Builder(this);
		
		b_generate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String swLayout_str = XmlToSketchwareConverter.convertXmlToSketchware (et_xml_input.getText().toString(), et_xmlFileName.getText().toString());
				i.setClass(getApplicationContext(), DecryptEncryptActivity.class);
				i.putExtra("TEXT", swLayout_str);
				startActivity(i);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), DecryptEncryptActivity.class);
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
	}
	
	@Override
	public void onBackPressed() {
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		if (LAYER_1_input.getVisibility() == View.GONE) {
			LAYER_1_input.setVisibility(View.VISIBLE);
			return ;
		}
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		d.setTitle("Exit?");
		d.setMessage("Are you sure you want to exit?");
		d.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		d.create().show();
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
	}
	
	
	
	
	
	/*
👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻### PRESET METHODS [START] ###👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻
*/
	
	
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
	
	
	/*
👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻### PRESET METHODS [END] ###👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻👨‍💻
*/
	
	
	
	
}