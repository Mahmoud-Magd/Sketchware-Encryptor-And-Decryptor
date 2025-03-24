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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private String selectedFilePath = "";
	private String originalFilePath = "";
	
	private LinearLayout linear1;
	private TextView textview4;
	private LinearLayout linear7;
	private TextView textview1;
	private LinearLayout linear2;
	private Button button3;
	private LinearLayout linear6;
	private TextView textview2;
	private LinearLayout linear5;
	private Button button1;
	private LinearLayout linear4;
	private Button button2;
	private LinearLayout linear3;
	private TextView textview3;
	
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview4 = findViewById(R.id.textview4);
		linear7 = findViewById(R.id.linear7);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		button3 = findViewById(R.id.button3);
		linear6 = findViewById(R.id.linear6);
		textview2 = findViewById(R.id.textview2);
		linear5 = findViewById(R.id.linear5);
		button1 = findViewById(R.id.button1);
		linear4 = findViewById(R.id.linear4);
		button2 = findViewById(R.id.button2);
		linear3 = findViewById(R.id.linear3);
		textview3 = findViewById(R.id.textview3);
		d = new AlertDialog.Builder(this);
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SwlayoutFromXmlActivity.class);
				startActivity(i);
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("WARNINGS!!");
				d.setMessage("* The encrypted FILES at\n\n\".../.sketchware/data/$m.projectID/$m.fileName\"\n\nARE VERY VERY VERY IMPORTANT for your sketchware project, so please EDIT WITH CAUTION!!!\n\n* You COULD LOSE YOUR PROJECT in the process, if not backedup!\n\n* Please BACKUP the file you want to edit FIRST, or even better backup the entire project for safety.\n\n* Don't change the activity main methods names.\n\n* Don't change the file format.\n\n* This app is a DECRYPTOR & ENCRYPTOR NOT AN EDITOR, we added the simple editor function just to make it easier & faster for people to make simple small changes.\nBut for complex edits or for large files or if the app lags for any reason, you should save the file and use an external code editor.");
				d.setPositiveButton("Understood", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.setNeutralButton("Show benefits", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						textview3.performClick();
					}
				});
				d.create().show();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), DecryptEncryptActivity.class);
				i.putExtra("Target", "Decrypt");
				startActivity(i);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), DecryptEncryptActivity.class);
				i.putExtra("Target", "Encrypt");
				startActivity(i);
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Benefits");
				d.setMessage("* Change pages names & order!\n\n* Change variables, lists, more blocks & components names & order.\n\n* Change main events order! (You can change name as well, but not adviced).\n\n* Search (Block, variables, lists, components & methods)\n\n* Update old blocks.\n\n* Manipulate libraries.\n\n* Copying entire activities and xml pages, to same or other projects.\n\n* Generating sketchware layout page straight from xml. (This was added to sw v7.0.0, better use it from there)\n\nand many more!!\n\nBasically you can manually modify anything in the project, if you know what you're doing!");
				d.setPositiveButton("Understood", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.setNeutralButton("Show warnings", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						textview2.performClick();
					}
				});
				d.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*

// This block is needed, cuz sketchware doesn't add the json library unless the user used a json related block!


*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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