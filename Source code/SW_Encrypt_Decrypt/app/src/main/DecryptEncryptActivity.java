package AAA.com.SW.EncryptorAndDecryptor;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
//
//
// Start of custom imports
import android.os.AsyncTask;
import android.os.storage.StorageVolume;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import java.nio.charset.StandardCharsets;
// for openMTManager();
import androidx.core.content.FileProvider;

public class DecryptEncryptActivity extends AppCompatActivity {
private Stack<String> undoStack = new Stack<>();
private Stack<String> redoStack = new Stack<>();
TextHighlighter highlighter;
	
	public final int REQ_CD_FP_DECRYPT = 101;
	public final int REQ_CD_FP_ENCRYPT = 102;
	
	private String decryptedData = "";
	private String ORIGINAL_FILE_str = "";
	private boolean isSaveDecryptedFile = false;
	private String decryptToPath = "";
	private boolean liveSearch = false;
	
	private ArrayList<HashMap<String, Object>> decryptedList_LLM = new ArrayList<>();
	
	private RelativeLayout RL_MAIN;
	private LinearLayout LAYER_1;
	private LinearLayout LAYER_2;
	private LinearLayout l_decrypt;
	private LinearLayout l_encrypt;
	private TextView textview36;
	private TextView textview25;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear18;
	private LinearLayout linear12;
	private ScrollView vscroll1;
	private LinearLayout linear17;
	private TextView tv_result;
	private Button b1;
	private LinearLayout linear29;
	private Button b_show_hide_write;
	private HorizontalScrollView hscroll4;
	private LinearLayout linear26;
	private LinearLayout linear23;
	private LinearLayout linear20;
	private LinearLayout linear19;
	private Button button6;
	private TextView textview34;
	private Button button1;
	private TextView textview31;
	private Button button14;
	private Button button15;
	private Button button16;
	private TextView textview28;
	private Button button2;
	private TextView textview32;
	private Button button17;
	private TextView textview29;
	private Button button18;
	private LinearLayout linear13;
	private LinearLayout linear16;
	private TextView textview22;
	private Switch switch1;
	private TextView textview21;
	private TextView textview23;
	private Switch switch2;
	private TextView textview24;
	private LinearLayout linear22;
	private LinearLayout linear27;
	private LinearLayout linear25;
	private Button button21;
	private Button button22;
	private LinearLayout linear24;
	private TextView textview33;
	private Button button19;
	private TextView textview35;
	private CheckBox checkbox5;
	private HorizontalScrollView hscroll5;
	private LinearLayout linear28;
	private EditText edittext3;
	private CheckBox checkbox1;
	private CheckBox checkbox2;
	private CheckBox checkbox3;
	private HorizontalScrollView hscroll2;
	private Button button10;
	private TextView textview20;
	private EditText edittext2;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private TextView textview3;
	private HorizontalScrollView hscroll3;
	private EditText edittext1;
	private TextView textview7;
	private Button button5;
	private TextView textview27;
	private Button button11;
	private TextView textview8;
	private TextView textview9;
	private ProgressBar progressbar1;
	private TextView textview26;
	
	private Intent FP_decrypt = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent FP_encrypt = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder d;
	private Intent i = new Intent();
	private SharedPreferences DATA;
	private AlertDialog.Builder d0;
	private AlertDialog.Builder d1;
	private AlertDialog.Builder d2;
	private AlertDialog.Builder d4;
	private AlertDialog.Builder d3;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.decrypt_encrypt);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		RL_MAIN = findViewById(R.id.RL_MAIN);
		LAYER_1 = findViewById(R.id.LAYER_1);
		LAYER_2 = findViewById(R.id.LAYER_2);
		l_decrypt = findViewById(R.id.l_decrypt);
		l_encrypt = findViewById(R.id.l_encrypt);
		textview36 = findViewById(R.id.textview36);
		textview25 = findViewById(R.id.textview25);
		hscroll1 = findViewById(R.id.hscroll1);
		linear18 = findViewById(R.id.linear18);
		linear12 = findViewById(R.id.linear12);
		vscroll1 = findViewById(R.id.vscroll1);
		linear17 = findViewById(R.id.linear17);
		tv_result = findViewById(R.id.tv_result);
		b1 = findViewById(R.id.b1);
		linear29 = findViewById(R.id.linear29);
		b_show_hide_write = findViewById(R.id.b_show_hide_write);
		hscroll4 = findViewById(R.id.hscroll4);
		linear26 = findViewById(R.id.linear26);
		linear23 = findViewById(R.id.linear23);
		linear20 = findViewById(R.id.linear20);
		linear19 = findViewById(R.id.linear19);
		button6 = findViewById(R.id.button6);
		textview34 = findViewById(R.id.textview34);
		button1 = findViewById(R.id.button1);
		textview31 = findViewById(R.id.textview31);
		button14 = findViewById(R.id.button14);
		button15 = findViewById(R.id.button15);
		button16 = findViewById(R.id.button16);
		textview28 = findViewById(R.id.textview28);
		button2 = findViewById(R.id.button2);
		textview32 = findViewById(R.id.textview32);
		button17 = findViewById(R.id.button17);
		textview29 = findViewById(R.id.textview29);
		button18 = findViewById(R.id.button18);
		linear13 = findViewById(R.id.linear13);
		linear16 = findViewById(R.id.linear16);
		textview22 = findViewById(R.id.textview22);
		switch1 = findViewById(R.id.switch1);
		textview21 = findViewById(R.id.textview21);
		textview23 = findViewById(R.id.textview23);
		switch2 = findViewById(R.id.switch2);
		textview24 = findViewById(R.id.textview24);
		linear22 = findViewById(R.id.linear22);
		linear27 = findViewById(R.id.linear27);
		linear25 = findViewById(R.id.linear25);
		button21 = findViewById(R.id.button21);
		button22 = findViewById(R.id.button22);
		linear24 = findViewById(R.id.linear24);
		textview33 = findViewById(R.id.textview33);
		button19 = findViewById(R.id.button19);
		textview35 = findViewById(R.id.textview35);
		checkbox5 = findViewById(R.id.checkbox5);
		hscroll5 = findViewById(R.id.hscroll5);
		linear28 = findViewById(R.id.linear28);
		edittext3 = findViewById(R.id.edittext3);
		checkbox1 = findViewById(R.id.checkbox1);
		checkbox2 = findViewById(R.id.checkbox2);
		checkbox3 = findViewById(R.id.checkbox3);
		hscroll2 = findViewById(R.id.hscroll2);
		button10 = findViewById(R.id.button10);
		textview20 = findViewById(R.id.textview20);
		edittext2 = findViewById(R.id.edittext2);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		textview3 = findViewById(R.id.textview3);
		hscroll3 = findViewById(R.id.hscroll3);
		edittext1 = findViewById(R.id.edittext1);
		textview7 = findViewById(R.id.textview7);
		button5 = findViewById(R.id.button5);
		textview27 = findViewById(R.id.textview27);
		button11 = findViewById(R.id.button11);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		progressbar1 = findViewById(R.id.progressbar1);
		textview26 = findViewById(R.id.textview26);
		FP_decrypt.setType("*/*");
		FP_decrypt.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		FP_encrypt.setType("*/*");
		FP_encrypt.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		d = new AlertDialog.Builder(this);
		DATA = getSharedPreferences("DATA", Activity.MODE_PRIVATE);
		d0 = new AlertDialog.Builder(this);
		d1 = new AlertDialog.Builder(this);
		d2 = new AlertDialog.Builder(this);
		d4 = new AlertDialog.Builder(this);
		d3 = new AlertDialog.Builder(this);
		
		LAYER_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview36.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				d3.setTitle("Lags?");
				d3.setMessage("You should save the file and use an external editor!\n\nCuz if it lags, then the data is too big to be handled in the temporary memory.\n\nThis app was mainly designed just to decrypt and encrypt sketchware project files, it still can't perform complex editor functions!\n\nThe ideal work path for now is:\n→ Decrypt & save file here\n→ Edit externally (best if MT Manager)\n→ Reencrypt here");
				d3.setPositiveButton("Understood", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d3.setNegativeButton("Save to file", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						// Leaving it empty to override it later, cuz the default dismiss the dialog.
					}
				});
				AlertDialog dialog3 = d3.create();
				dialog3.show();
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("This will keep d3 open until you dismiss it!")#####(↓↓↓)#####
				dialog3.getButton (DialogInterface.BUTTON_NEGATIVE).setOnClickListener (
				new View.OnClickListener ()   {
					
					
					
					@Override
					public void onClick (View v) {
						
						
						
						
						
						
						// START #####(↓↓↓)#####("")#####(↓↓↓)#####
						d1.setTitle("Save to ..");
						d1.setMessage("Please select the path you want to save to ..");
						EditText et_path = new EditText(DecryptEncryptActivity.this);
						et_path.setHint("Save to path ...");
						d1.setView(et_path);
						d1.setPositiveButton("Save", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								// Leaving it empty to override it later, cuz the default dismiss the dialog, not giving the user the time  to see the error
							}
						});
						d1.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						AlertDialog dialog1 = d1.create();
						dialog1.show();
						// END #####(↑↑↑)#####("")#####(↑↑↑)#####
						
						
						
						
						
						
						// START #####(↓↓↓)#####("This will keep d1 open until you dismiss it!")#####(↓↓↓)#####
						dialog1.getButton (DialogInterface.BUTTON_POSITIVE).setOnClickListener (
						new View.OnClickListener ()   {
							
							
							
							@Override
							public void onClick (View v) {
								
								
								
								
								
								
								// START #####(↓↓↓)#####("")#####(↓↓↓)#####
								String path = et_path.getText().toString();
								if (path.replaceAll("\\s", "").isEmpty()) {
									et_path.setError(null);
									et_path.setError("Target path can't be empty!");
									return ;
								}
								// END #####(↑↑↑)#####("")#####(↑↑↑)#####
								
								
								
								
								
								
								// START #####(↓↓↓)#####("")#####(↓↓↓)#####
								final String data = tv_result.getText().toString();
								if (data.replaceAll("\\s", "").isEmpty()) {
									SketchwareUtil.showMessage(getApplicationContext(), "Data is empty!");
									return ;
								}
								// END #####(↑↑↑)#####("")#####(↑↑↑)#####
								
								
								
								
								
								
								// START #####(↓↓↓)#####("")#####(↓↓↓)#####
								if (FileUtil.isExistFile(path)) {
									
									
									
									// START #####(↓↓↓)#####("")#####(↓↓↓)#####
									d2.setTitle("Already exist!");
									d2.setMessage("File at path\n\n\"" + path + "\"\n\nalready exist!\nDo you want to decrypt the file there anyway?");
									d2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											FileUtil.writeFile(path, data);
											if (FileUtil.isExistFile(path)) {
												SketchwareUtil.showMessage(getApplicationContext(), "Saved to \"" + path + "\"");
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Error!");
											}
										}
									});
									d2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											
										}
									});
									d2.create().show();
									// END #####(↑↑↑)#####("")#####(↑↑↑)#####
									
									
									
									return ;
								}
								// END #####(↑↑↑)#####("")#####(↑↑↑)#####
								
								
								
								
								
								
								// START #####(↓↓↓)#####("")#####(↓↓↓)#####
								FileUtil.writeFile(path, data);
								if (FileUtil.isExistFile(path)) {
									SketchwareUtil.showMessage(getApplicationContext(), "Saved to \"" + path + "\"");
								}
								else {
									SketchwareUtil.showMessage(getApplicationContext(), "Error!");
								}
								dialog1.dismiss();
								dialog3.dismiss();
								// END #####(↑↑↑)#####("")#####(↑↑↑)#####
								
								
								
								
								
								
							}
							
							
							
						});
						// END #####(↑↑↑)#####("This will keep d1 open until you dismiss it!")#####(↑↑↑)#####
						
						
						
						
						
						
					}
					
					
					
				});
				// END #####(↑↑↑)#####("This will keep d3 open until you dismiss it!")#####(↑↑↑)#####
				
				
				
			}
		});
		
		textview25.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview25.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "\"File name\" copied!");
				return true;
			}
		});
		
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				openMtManager();
			}
		});
		
		b_show_hide_write.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear12.getVisibility() == View.GONE) {
					linear12.setVisibility(View.VISIBLE);
					b_show_hide_write.setText("Hide Controls");
				}
				else {
					linear12.setVisibility(View.GONE);
					b_show_hide_write.setText("Show Controls");
				}
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				isSaveDecryptedFile = false;
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				d0.setTitle("Here or external?");
				d0.setMessage("Would you like to decrypt & edit it here, or save it on a file to edit it in an external editor?!\n\nUse external for large files.\n\nBtw, MT Manager is the best one i found yet.");
				d0.setPositiveButton("Save the file", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						// Leaving it empty to override it later, cuz the default dismiss the dialog.
					}
				});
				d0.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d0.setNegativeButton("Here", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						startActivityForResult(FP_decrypt, REQ_CD_FP_DECRYPT);
					}
				});
				AlertDialog dialog0 = d0.create();
				dialog0.show();
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("This will keep d open until you dismiss it!")#####(↓↓↓)#####
				dialog0.getButton (DialogInterface.BUTTON_POSITIVE).setOnClickListener (
				new View.OnClickListener ()   {
					
					
					
					@Override
					public void onClick (View v) {
						
						
						
						
						
						
						// START #####(↓↓↓)#####("")#####(↓↓↓)#####
						d1.setTitle("Decrypt & save to ..");
						d1.setMessage("Please select the path you want to save to ..");
						EditText et_path = new EditText(DecryptEncryptActivity.this);
						et_path.setHint("Save to path ...");
						d1.setView(et_path);
						d1.setPositiveButton("Decrypt & Save", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								// Leaving it empty to override it later, cuz the default dismiss the dialog, not giving the user the time  to see the error
							}
						});
						d1.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						AlertDialog dialog1 = d1.create();
						dialog1.show();
						// END #####(↑↑↑)#####("")#####(↑↑↑)#####
						
						
						
						
						
						
						// START #####(↓↓↓)#####("This will keep d0 open until you dismiss it!")#####(↓↓↓)#####
						dialog1.getButton (DialogInterface.BUTTON_POSITIVE).setOnClickListener (
						new View.OnClickListener ()   {
							
							
							
							@Override
							public void onClick (View v) {
								
								
								
								String path = et_path.getText().toString();
								if (path.replaceAll("\\s", "").isEmpty()) {
									et_path.setError(null);
									et_path.setError("Target path can't be empty!");
									return ;
								}
								if (FileUtil.isExistFile(path)) {
									
									
									
									// START #####(↓↓↓)#####("")#####(↓↓↓)#####
									d2.setTitle("Already exist!");
									d2.setMessage("File at path\n\n\"" + path + "\"\n\nalready exist!\nDo you want to decrypt the file there anyway?");
									d2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											isSaveDecryptedFile = true;
											decryptToPath = path;
											startActivityForResult(FP_decrypt, REQ_CD_FP_DECRYPT);
										}
									});
									d2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											
										}
									});
									d2.create().show();
									// END #####(↑↑↑)#####("")#####(↑↑↑)#####
									
									
									
									return ;
								}
								isSaveDecryptedFile = true;
								decryptToPath = path;
								startActivityForResult(FP_decrypt, REQ_CD_FP_DECRYPT);
								dialog1.dismiss();
								dialog0.dismiss();
								
								
								
							}
							
							
							
						});
						// END #####(↑↑↑)#####("This will keep d0 open until you dismiss it!")#####(↑↑↑)#####
						
						
						
						
						
						
					}
					
					
					
				});
				// END #####(↑↑↑)#####("This will keep d open until you dismiss it!")#####(↑↑↑)#####
				
				
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				String selectedText = tv_result.getText().toString();
				int start = -1;
				int end = -1;
				if (switch2.isChecked()) {
					try {
						Spannable spannable = (Spannable) tv_result.getText();
						start = Selection.getSelectionStart(spannable);
						end = Selection.getSelectionEnd(spannable);
						if (start != -1 && end != -1 && start != end) {
							selectedText = tv_result.getText().subSequence(start, end).toString();
						}
					} catch (Exception e) {
						 
					}
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				i.setClass(getApplicationContext(), EditorActivity.class);
				i.putExtra("TEXT", selectedText);
				i.putExtra("START", start);
				i.putExtra("END", end);
				startActivityForResult(i, 156);
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
		});
		
		button14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				undo();
			}
		});
		
		button15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				redo();
			}
		});
		
		button16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (ORIGINAL_FILE_str.equals(tv_result.getText().toString())) {
					SketchwareUtil.showMessage(getApplicationContext(), "Already the data from original file!");
				}
				else {
					saveUndoRedo();
					updateText(ORIGINAL_FILE_str);
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				String selectedText = tv_result.getText().toString();
				int start = -1;
				int end = -1;
				String toast = "\"Full Text\" copied!";
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (switch2.isChecked()) {
					try {
						Spannable spannable = (Spannable) tv_result.getText();
						start = Selection.getSelectionStart(spannable);
						end = Selection.getSelectionEnd(spannable);
						if (start != -1 && end != -1 && start != end) {
							selectedText = tv_result.getText().subSequence(start, end).toString();
							toast = "\"Selected Text\" copied!";
						}
					} catch (Exception e) {
						 
					}
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", selectedText));
				SketchwareUtil.showMessage(getApplicationContext(), toast);
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
		});
		
		button17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear20.getVisibility() == View.GONE) {
					linear20.setVisibility(View.VISIBLE);
				}
				else {
					linear20.setVisibility(View.GONE);
				}
			}
		});
		
		button18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear23.getVisibility() == View.GONE) {
					linear23.setVisibility(View.VISIBLE);
				}
				else {
					linear23.setVisibility(View.GONE);
					highlighter.resetHighlight();
				}
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					l_decrypt.setVisibility(View.GONE);
				}
				else {
					l_decrypt.setVisibility(View.VISIBLE);
				}
			}
		});
		
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				updateText(decryptedData);
			}
		});
		
		button21.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				highlighter.previousMatch ();
				textview35.setText(String.valueOf(1 + highlighter.currentIndex()) + "/" + highlighter.getMatchCount ());
			}
		});
		
		button22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				highlighter.nextMatch ();
				textview35.setText(String.valueOf(1 + highlighter.currentIndex()) + "/" + highlighter.getMatchCount ());
			}
		});
		
		button19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				search(true);
			}
		});
		
		edittext3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (checkbox5.isChecked()) {
					search(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				highlighter.setCaseSensitive (_isChecked);
				if (checkbox5.isChecked()) {
					search(false);
				}
			}
		});
		
		checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				highlighter.setWholeWord (_isChecked);
				if (checkbox5.isChecked()) {
					search(false);
				}
			}
		});
		
		checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				highlighter.setUseRegex (_isChecked);
				if (checkbox5.isChecked()) {
					search(false);
				}
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				final String path = edittext2.getText().toString();
				if (path.replaceAll("\\s", "").isEmpty()) {
					((EditText)edittext2).setError(null);
					((EditText)edittext2).setError("Please enter file path!");
					return ;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				final String data = tv_result.getText().toString();
				if (data.replaceAll("\\s", "").isEmpty()) {
					SketchwareUtil.showMessage(getApplicationContext(), "Data is empty!");
					return ;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (FileUtil.isExistFile(path)) {
					d.setTitle("Already exist!");
					d.setMessage("File at path\n\n\"" + path + "\"\n\nalready exist!\nDo you want to write it anyway?");
					d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							FileUtil.writeFile(path, data);
						}
					});
					d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					d.create().show();
				}
				else {
					FileUtil.writeFile(path, data);
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (FileUtil.isExistFile(path)) {
					textview20.setText("Saved to \"" + path + "\"");
				}
				else {
					textview20.setText("Error");
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				final String path = edittext1.getText().toString();
				if (path.replaceAll("\\s", "").isEmpty()) {
					((EditText)edittext1).setError(null);
					((EditText)edittext1).setError("Please choose a target location to encrypt to!");
					return ;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (FileUtil.isExistFile(path)) {
					d.setTitle("Already exist!");
					d.setMessage("File at path\n\n\"" + path + "\"\n\nalready exist!\nDo you want to encrypt file to that path anyway?");
					d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							startActivityForResult(FP_encrypt, REQ_CD_FP_ENCRYPT);
						}
					});
					d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					d.create().show();
				}
				else {
					startActivityForResult(FP_encrypt, REQ_CD_FP_ENCRYPT);
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				final String path = edittext1.getText().toString();
				if (path.replaceAll("\\s", "").isEmpty()) {
					((EditText)edittext1).setError(null);
					((EditText)edittext1).setError("Please choose a target location to encrypt to!");
					return ;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (FileUtil.isExistFile(path)) {
					d.setTitle("Already exist!");
					d.setMessage("File at path\n\n\"" + path + "\"\n\nalready exist!\nDo you want to encrypt file to that path anyway?");
					d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							textview9.setText(SketchwareEncryptorAndDecryptor.encryptAndSaveString (tv_result.getText().toString(), edittext1.getText().toString()));
							SketchwareUtil.showMessage(getApplicationContext(), textview9.getText().toString());
						}
					});
					d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					d.create().show();
				}
				else {
					textview9.setText(SketchwareEncryptorAndDecryptor.encryptAndSaveString (tv_result.getText().toString(), edittext1.getText().toString()));
					SketchwareUtil.showMessage(getApplicationContext(), textview9.getText().toString());
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
		});
		
		textview9.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Copied!");
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview9.getText().toString()));
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		if (getIntent().hasExtra("Target")) {
			if ("Encrypt".equals(getIntent().getStringExtra("Target"))) {
				l_decrypt.setVisibility(View.GONE);
				switch1.setChecked(true);
			}
		}
		if (getIntent().hasExtra("TEXT")) {
			decryptedData = getIntent().getStringExtra("TEXT");
			ORIGINAL_FILE_str = decryptedData;
			updateText(decryptedData);
		}
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		if (DATA.contains("Text Only")) {
			switch2.setChecked(true);
		}
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		LAYER_2.setBackgroundColor(0xA1000000);
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*

highlighter = new TextHighlighter (tv_result, vscroll1, hscroll1);

*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		highlighter = new TextHighlighter (tv_result);
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		if ((156 == _requestCode) && ((_resultCode == RESULT_OK) && (null) != (_data))) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				String replace = _data.getStringExtra("REPLACE");
				int start = _data.getIntExtra("START", -1);
				int end = _data.getIntExtra("END", -1);
				saveUndoRedo();
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (-1 != start && (-1 != end && start != end)) {
						SpannableStringBuilder text = new SpannableStringBuilder (decryptedData);
						text.replace (start, end, replace);
						decryptedData = (text).toString();
				}
				else {
						decryptedData = replace;
				}
				updateText(decryptedData);
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
		}
		switch (_requestCode) {
			case REQ_CD_FP_ENCRYPT:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				textview9.setText(SketchwareEncryptorAndDecryptor.encryptAndSaveFile (_filePath.get((int)(0)), edittext1.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), textview9.getText().toString());
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP_DECRYPT:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (_filePath.isEmpty()) {
					tv_result.setText("Invalid file selected!");
					tv_result.setTextColor(0xFFB71C1C);
					SketchwareUtil.showMessage(getApplicationContext(), "Invalid file selected!");
					return ;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				String PATH = _filePath.get((int)(0));
				textview25.setText("File name: " + Uri.parse(PATH).getLastPathSegment());
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				class async extends AsyncTask<String, String, String> {
					
					
					
					@Override
					protected void onPreExecute() {
						
						
						
						LAYER_2.setVisibility(View.VISIBLE);
						textview26.setText("Decrypting ...");
						
						
						
					}
					@Override
					protected String doInBackground(String... _params) {
						
						
						
						decryptedData = SketchwareEncryptorAndDecryptor.decryptFile (PATH);
						return ("");
						
						
						
					}
					@Override
					protected void onPostExecute(String _result) {
						
						
						
						ORIGINAL_FILE_str = decryptedData;
						if (isSaveDecryptedFile) {
							FileUtil.writeFile(decryptToPath, ORIGINAL_FILE_str);
							if (FileUtil.isExistFile(decryptToPath)) {
								d.setTitle("Saved Successfully!");
								d.setMessage("Saved to \"" + decryptToPath + "\"");
								d.setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										
									}
								});
								d.setNeutralButton("Open in MT Manager", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										b1.performClick();
									}
								});
								d.create().show();
							}
							else {
								SketchwareUtil.showMessage(getApplicationContext(), "Error");
							}
						}
						else {
							saveUndoRedo();
							updateText(decryptedData);
						}
						LAYER_2.setVisibility(View.GONE);
						
						
						
					}
					
					
					
				}
				new async().execute("");
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	
	@Override
	public void onBackPressed() {
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		if (l_decrypt.getVisibility() == View.GONE) {
			switch1.setChecked(false);
			return ;
		}
		if (linear12.getVisibility() == View.VISIBLE) {
			b_show_hide_write.performClick();
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
🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪### MORE BLOCKS [START] ###🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪
*/
	
	public void _undoRedo___X() {
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private void saveUndoRedo () {
		
		
		
		String str = tv_result.getText().toString();
		if (! (undoStack.isEmpty()) && undoStack.peek ().equals(str)) {
			return ;
		}
		undoStack.push (str);
		redoStack.clear();
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private void undo () {
		
		
		
		if (undoStack.isEmpty()) {
			SketchwareUtil.showMessage(getApplicationContext(), "Can't undo more!");
			return ;
		}
		redoStack.push (tv_result.getText().toString());
		updateText(undoStack.pop ());
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private void redo () {
		
		
		
		if (redoStack.isEmpty()) {
			SketchwareUtil.showMessage(getApplicationContext(), "Can't redo more!");
			return ;
		}
		undoStack.push (tv_result.getText().toString());
		updateText(redoStack.pop ());
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	public void _updateText___X() {
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private void updateText (String _str) {
		
		
		
		if (switch2.isChecked()) {
			DATA.edit().putString("Text Only", "").commit();
			tv_result.setText(_str);
			tv_result.setTextIsSelectable (true);
		}
		else {
			DATA.edit().remove("Text Only").commit();
			setSpanText(tv_result, _str);
			tv_result.setTextIsSelectable (false);
		}
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	public void setSpanText (TextView _tv_result,
	String decryptedText) {
		
		
		
		class async extends AsyncTask<String, String, SpannableStringBuilder> {
			
			
			
			@Override
			protected void onPreExecute() {
				
				
				
				LAYER_2.setVisibility(View.VISIBLE);
				textview26.setText("Setting Span Text ...");
				
				
				
			}
			@Override
			protected SpannableStringBuilder doInBackground(String... _params) {
				
				
				
				return (getClickableColoredSpan (decryptedText));
				
				
				
			}
			@Override
			protected void onPostExecute(SpannableStringBuilder _result) {
				
				
				
				if ((null) != (_result)) {
					_tv_result.setText(_result);
					_tv_result.setMovementMethod(new android.text.method.LinkMovementMethod()); // Enable clicking
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Couldn't set span text!\n\"SpannableStringBuilder _result\" is null!");
				}
				LAYER_2.setVisibility(View.GONE);
				
				
				
			}
			
			
			
		}
		new async().execute("");
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private SpannableStringBuilder getClickableColoredSpan (String decryptedText) {
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		SpannableStringBuilder spannable = new SpannableStringBuilder();
		BufferedReader reader = new BufferedReader(new StringReader(decryptedText));
		String line;
		int start, end;
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		try {
			while((null) != (line = reader.readLine())) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				start = spannable.length();
				spannable.append(line).append("\n");
				end = spannable.length() - 1;
				int color;
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (line.startsWith("@")) {
					color = 0xFF1A237E;
				}
				else
				if (line.startsWith("{")) {
					color = 0xFF1B5E20;
				}
				else
				if (line.contains(":")) {
					color = 0xFFEF6C00;
				}
				else {
					color = 0xFF000000;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				final String lineF = line;
				ClickableSpan CS = new ClickableSpan () {
					
					
					
					 @Override
					public void onClick(View widget) {
						    Context context = widget.getContext();
						    if (!(context instanceof Activity)) {
							        SketchwareUtil.showMessage(getApplicationContext(), "Error: Unable to start EditorActivity!\nSpan context is not an Activity!");
							        return;
							    }
						    Activity activity = (Activity) context;
						    
						    TextView textView = (TextView) widget;
							Spannable spannableText = (Spannable) textView.getText();
							int spanStart = spannableText.getSpanStart(this);
							int spanEnd = spannableText.getSpanEnd(this);
						    
							String clickedText = textView.getText().subSequence(spanStart, spanEnd).toString();
						    
							Intent intent = new Intent(widget.getContext(), EditorActivity.class);
							intent.putExtra("TEXT", clickedText);  // Send only clicked text
						    intent.putExtra("START", spanStart);
							intent.putExtra("END", spanEnd);
							activity.startActivityForResult(intent, 156);
							
					}
					
					 @Override
					public void updateDrawState(TextPaint ds) {
							ds.setColor(color);  // Apply color here
							ds.setUnderlineText(true);  // Keep underline if needed
					}
					
					
					
				};
				spannable.setSpan (CS, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
			reader.close();
			return (spannable);
		} catch (Exception e) {
			SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
		}
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		SketchwareUtil.showMessage(getApplicationContext(), "Error: couldn't get text span!");
		return (null);
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	public void _search___X() {
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private void search (boolean isResetCurrentIndex) {
		
		
		
		class async extends AsyncTask<String, String, String> {
			
			
			
			@Override
			protected void onPreExecute() {
				
				
				
				LAYER_2.setVisibility(View.VISIBLE);
				textview26.setText("Searching for \"" + edittext3.getText().toString() + "\" ...\n\nIf you can read this, then the app is lagging.\n\nYou should save file and open with an external editor!");
				
				
				
			}
			@Override
			protected String doInBackground(String... _params) {
				
				
				
				highlighter.searchAndHighlight (edittext3.getText().toString(), isResetCurrentIndex);
				return ("");
				
				
				
			}
			@Override
			protected void onPostExecute(String _result) {
				
				
				
				textview33.setText("Results: " + highlighter.getMatchCount ());
				textview35.setText("...");
				LAYER_2.setVisibility(View.GONE);
				
				
				
			}
			
			
			
		}
		new async().execute("");
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	public void _openMtManager___X() {
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	private void openMtManager() {
		    if (!isAppInstalled("bin.mt.plus")) {
			        Toast.makeText(this, "MT Manager is not installed", Toast.LENGTH_SHORT).show();
			        return;
			    }
		
		    Intent intent = getPackageManager().getLaunchIntentForPackage("bin.mt.plus");
		    if (intent != null) {
			        startActivity(intent);
			    } else {
			        Toast.makeText(this, "Failed to open MT Manager", Toast.LENGTH_SHORT).show();
			    }
	}
	
	// Helper method to check if MT Manager is installed
	private boolean isAppInstalled(String packageName) {
		    try {
			        getPackageManager().getPackageInfo(packageName, 0);
			        return true;
			    } catch (PackageManager.NameNotFoundException e) {
			        return false;
			    }
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	/*
🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪### MORE BLOCKS [END] ###🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪
*/
	
	
	
	
	
	
	
	
	
	
	
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