package AAA.com.SW.EncryptorAndDecryptor;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
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
import java.util.regex.*;
import org.json.*;
//
//
// Start of custom imports
// for formatJson()
import com.google.gson.*;
// for showReplaceDialog()
import android.text.method.ScrollingMovementMethod;

public class EditorActivity extends AppCompatActivity {
	
	private String Header = "";
	private String Body = "";
	private String prettyPrintBody = "";
	
	private TextView textview23;
	private HorizontalScrollView hscroll1;
	private Button b_show_hide_write;
	private LinearLayout linear12;
	private ScrollView vscroll1;
	private LinearLayout linear16;
	private TextView tv_jsonError;
	private EditText edittext3;
	private LinearLayout linear13;
	private LinearLayout linear17;
	private LinearLayout linear21;
	private LinearLayout linear18;
	private LinearLayout linear20;
	private TextView textview21;
	private Switch switch1;
	private TextView textview22;
	private Button button1;
	private Button button3;
	private Button button4;
	private Button button2;
	private TextView textview24;
	private EditText edittext4;
	private TextView textview25;
	private Button button10;
	private Button button11;
	private HorizontalScrollView hscroll2;
	private Button button12;
	private TextView textview20;
	private EditText edittext2;
	
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editor);
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
		textview23 = findViewById(R.id.textview23);
		hscroll1 = findViewById(R.id.hscroll1);
		b_show_hide_write = findViewById(R.id.b_show_hide_write);
		linear12 = findViewById(R.id.linear12);
		vscroll1 = findViewById(R.id.vscroll1);
		linear16 = findViewById(R.id.linear16);
		tv_jsonError = findViewById(R.id.tv_jsonError);
		edittext3 = findViewById(R.id.edittext3);
		linear13 = findViewById(R.id.linear13);
		linear17 = findViewById(R.id.linear17);
		linear21 = findViewById(R.id.linear21);
		linear18 = findViewById(R.id.linear18);
		linear20 = findViewById(R.id.linear20);
		textview21 = findViewById(R.id.textview21);
		switch1 = findViewById(R.id.switch1);
		textview22 = findViewById(R.id.textview22);
		button1 = findViewById(R.id.button1);
		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		button2 = findViewById(R.id.button2);
		textview24 = findViewById(R.id.textview24);
		edittext4 = findViewById(R.id.edittext4);
		textview25 = findViewById(R.id.textview25);
		button10 = findViewById(R.id.button10);
		button11 = findViewById(R.id.button11);
		hscroll2 = findViewById(R.id.hscroll2);
		button12 = findViewById(R.id.button12);
		textview20 = findViewById(R.id.textview20);
		edittext2 = findViewById(R.id.edittext2);
		d = new AlertDialog.Builder(this);
		
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
		
		edittext3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textview23.setText("Text length: " + _charSeq.length());
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				edittext3.setText(formatJson (edittext3.getText().toString(), _isChecked));
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext3.setText(getIntent().hasExtra("TEXT") ? getIntent().getStringExtra("TEXT") : "");
				SketchwareUtil.showMessage(getApplicationContext(), "Reset to original!");
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if (linear21.getVisibility() == View.GONE) {
					linear21.setVisibility(View.VISIBLE);
					return ;
				}
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				String text = edittext3.getText().toString();
				String between = edittext4.getText().toString();
				String toast = "\"Full text\" duplicated!";
				int start = edittext3.getSelectionStart();
				int end = edittext3.getSelectionEnd();
				int cutsor = text.length();
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				
				
				
				// START #####(↓↓↓)#####("")#####(↓↓↓)#####
				if ((-1 == start) || ((-1 == end) || ((start == end) || ((0 == edittext3.getSelectionStart()) && (edittext3.getText().toString().length() == edittext3.getSelectionEnd()))))) {
					text = text + between + text;
					cutsor = text.length();
				}
				else {
					String selected_txt = edittext3.getText().toString().substring((int)(edittext3.getSelectionStart()), (int)(edittext3.getSelectionEnd()));
					SpannableStringBuilder builder = new SpannableStringBuilder (text);
					String replacement = selected_txt + between + selected_txt;
					builder.replace (start, end, replacement);
					text = (builder).toString();
					toast = "\"Selected text\" duplicated!";
					cutsor = start + replacement.length();
				}
				edittext3.setText(text);
				edittext3.setSelection(cutsor);
				SketchwareUtil.showMessage(getApplicationContext(), toast);
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
				// END #####(↑↑↑)#####("")#####(↑↑↑)#####
				
				
				
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((EditText)edittext3).selectAll();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String copy = "";
				String toast = "";
				if ((edittext3.getSelectionStart() == edittext3.getSelectionEnd()) || ((0 == edittext3.getSelectionStart()) && (edittext3.getText().toString().length() == edittext3.getSelectionEnd()))) {
					copy = edittext3.getText().toString();
					toast = "\"Full text\" copied!";
				}
				else {
					copy = edittext3.getText().toString().substring((int)(edittext3.getSelectionStart()), (int)(edittext3.getSelectionEnd()));
					toast = "\"Selected text\" copied!";
				}
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", copy));
				SketchwareUtil.showMessage(getApplicationContext(), toast);
			}
		});
		
		textview25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear21.setVisibility(View.GONE);
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().hasExtra("TEXT") && edittext3.getText().toString().equals(getIntent().getStringExtra("TEXT"))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Nothing changed to replace!");
					return ;
				}
				Runnable run_ = new Runnable() {
					
					
					
					@Override
					public void run() {
						
						
						
						try {
							Intent ii = new Intent();
							ii.putExtra ("REPLACE", edittext3.getText().toString());
							ii.putExtra ("START", getIntent().getIntExtra("START", -1));
							ii.putExtra ("END", getIntent().getIntExtra("END", -1));
							setResult (RESULT_OK, ii);
							finish();
						} catch (Exception e) {
							SketchwareUtil.showMessage(getApplicationContext(), "Data are too large!!\nWrite it instead, Or use external editor.\n\n" + e.getMessage());
						}
						
						
						
					}
					
					
					
				};
				showReplaceDialog(EditorActivity.this, String.valueOf(getIntent().getStringExtra("TEXT")), edittext3.getText().toString(), run_);
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
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
		
		button12.setOnClickListener(new View.OnClickListener() {
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
				final String data = edittext3.getText().toString();
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
	}
	
	private void initializeLogic() {
		edittext3.setText(getIntent().hasExtra("TEXT") ? getIntent().getStringExtra("TEXT") : "");
	}
	
	
	@Override
	public void onBackPressed() {
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		if (linear12.getVisibility() == View.VISIBLE) {
			b_show_hide_write.performClick();
			return ;
		}
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
		
		
		
		// START #####(↓↓↓)#####("")#####(↓↓↓)#####
		if (getIntent().hasExtra("TEXT") && edittext3.getText().toString().equals(getIntent().getStringExtra("TEXT"))) {
			finish();
		}
		else {
			d.setTitle("Exit editor?");
			d.setMessage("Are you sure you want to discard changes & exit editor?");
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
		}
		// END #####(↑↑↑)#####("")#####(↑↑↑)#####
		
		
		
	}
	
	
	
	
	
	/*
🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪### MORE BLOCKS [START] ###🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪
*/
	
	public void _json___X() {
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	public String formatJson (String json,
	boolean isPretty) {
		
		
		
		try {
			JsonElement jsonElement = JsonParser.parseString (json);
			Gson gson = isPretty ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
			String result = gson.toJson (jsonElement);
			tv_jsonError.setVisibility(View.GONE);
			return (result);
		} catch (Exception e) {
			tv_jsonError.setText("Just to be clear!\nThis is NOT a valid json structure!\nBut i'm gonna try to force it anyway!");
			tv_jsonError.setVisibility(View.VISIBLE);
		}
		return (forceFormatJson (json, isPretty));
		
		
		
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	public static String forceFormatJson(String input, boolean isPretty) {
		    Gson gson = isPretty ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
		    StringBuilder result = new StringBuilder();
		    StringBuilder jsonBuffer = new StringBuilder();
		
		    extractAndFormatJson(input, gson, result, jsonBuffer);
		
		    // If there's still unprocessed JSON, treat it as non-JSON and append it
		    if (jsonBuffer.length() > 0) {
			        result.append(jsonBuffer);
			    }
		
		    return result.toString().trim();
	}
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	
	
	
	// START #####(↓↓↓)#####(
	//* Extracts and formats valid JSON parts while keeping non-JSON text unchanged.
	//* Appends processed JSON to `result`, and returns any unprocessed JSON in `jsonBuffer`.
	//)#####(↓↓↓)#####
	private static void extractAndFormatJson(String input, Gson gson, StringBuilder result, StringBuilder jsonBuffer) {
		    Stack<Character> stack = new Stack<>();
		    boolean insideJson = false;
		
		    for (int i = 0; i < input.length(); i++) {
			        char ch = input.charAt(i);
			
			        if (ch == '{' || ch == '[') {
				            stack.push(ch);
				            insideJson = true;
				        }
			
			        if (insideJson) {
				            jsonBuffer.append(ch);
				        } else {
				            result.append(ch); // Keep non-JSON text as is
				        }
			
			        if ((ch == '}' && !stack.isEmpty() && stack.peek() == '{') ||
			            (ch == ']' && !stack.isEmpty() && stack.peek() == '[')) {
				            stack.pop();
				        }
			
			        // If JSON block is complete, process it
			        if (insideJson && stack.isEmpty()) {
				            try {
					                JsonElement jsonElement = JsonParser.parseString(jsonBuffer.toString());
					                result.append(gson.toJson(jsonElement));
					            } catch (JsonSyntaxException e) {
					                result.append(jsonBuffer); // If parsing fails, keep original
					            }
				
				            jsonBuffer.setLength(0); // Reset buffer
				            insideJson = false;
				        }
			    }
	}
	// END #####(↑↑↑)#####(
	//* Extracts and formats valid JSON parts while keeping non-JSON text unchanged.
	//* Appends processed JSON to `result`, and returns any unprocessed JSON in `jsonBuffer`.
	//)#####(↑↑↑)#####
	
	
	
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	// END #####(↑↑↑)#####("")#####(↑↑↑)#####
	
	
	
	{
	}
	
	
	public void _dialog___X() {
	}
	
	
	
	// START #####(↓↓↓)#####("")#####(↓↓↓)#####
	
	
	
	// START #####(↓↓↓)#####("REPLACE DIALOG (MAIN LOGIC)")#####(↓↓↓)#####
	public void showReplaceDialog(Context context, String originalText, String newText, Runnable onReplace) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    builder.setTitle("Confirm Replacement");
		
		    LinearLayout layout = new LinearLayout(context);
		    layout.setOrientation(LinearLayout.VERTICAL);
		    layout.setPadding(50, 30, 50, 30);
		
		    // Original Text Section
		    TextView text1 = new TextView(context);
		    text1.setText("Are you sure you want to replace this ↓↓↓");
		    text1.setTypeface(null, Typeface.BOLD);
		    text1.setTextColor(Color.BLACK);
		    
		    LinearLayout originalContainer = createScrollableTextView(context, originalText);
		
		    TextView showMore1 = new TextView(context);
		    showMore1.setText("Show More");
		    showMore1.setTextColor(Color.BLUE);
		    showMore1.setPadding(20, 10, 0, 10);
		    showMore1.setGravity(Gravity.END);
		
		    // Process original text
		    int originalLines = countLines(originalText);
		    String collapsedOriginalText = getCollapsedText(originalText, 5);
		    TextView originalTextView = (TextView) originalContainer.getTag(); // Get textView from created container
		    originalTextView.setText(originalLines > 5 ? collapsedOriginalText : originalText);
		    showMore1.setVisibility(originalLines > 5 ? View.VISIBLE : View.GONE);
		
		    // Toggle Show More / Show Less for Original Text
		    showMore1.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View _view) {
				            if (showMore1.getText().equals("Show More")) {
					                
					                // Create LayoutParams with MATCH_PARENT width and 0 height, then set weight to 1
					                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					                    LinearLayout.LayoutParams.MATCH_PARENT, 
					                    0,  // Height must be 0 when using weight
					                    1   // Weight set to 1
					                 );
					                 
					                // Apply the parameters to the container
					                originalContainer.setLayoutParams(params);
					                
					                originalTextView.setText(originalText);
					                showMore1.setText("Show Less");
					                
					            } else {
					                
					                // Create LayoutParams with MATCH_PARENT width and 0 height, then set weight to 1
					                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					                    LinearLayout.LayoutParams.MATCH_PARENT, 
					                    LinearLayout.LayoutParams.WRAP_CONTENT);
					                    
					                // Apply the parameters to the container
					                originalContainer.setLayoutParams(params);
					                
					                originalTextView.setText(collapsedOriginalText);
					                showMore1.setText("Show More");
					            }
				        }
			    });
		
		    // New Text Section
		    TextView text2 = new TextView(context);
		    text2.setPadding(0, 100, 0, 0);
		    text2.setText("With this ↓↓↓");
		    text2.setTypeface(null, Typeface.BOLD);
		    text2.setTextColor(Color.BLACK);
		    
		    LinearLayout newContainer = createScrollableTextView(context, newText);
		
		    TextView showMore2 = new TextView(context);
		    showMore2.setText("Show More");
		    showMore2.setTextColor(Color.BLUE);
		    showMore2.setPadding(20, 10, 0, 10);
		    showMore2.setGravity(Gravity.END);
		
		    // Process new text
		    int newLines = countLines(newText);
		    String collapsedNewText = getCollapsedText(newText, 5);
		    TextView newTextView = (TextView) newContainer.getTag(); // Get textView from created container
		    newTextView.setText(newLines > 5 ? collapsedNewText : newText);
		    showMore2.setVisibility(newLines > 5 ? View.VISIBLE : View.GONE);
		
		    // Toggle Show More / Show Less for New Text
		    showMore2.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View _view) {
				            if (showMore2.getText().equals("Show More")) {
					                
					                // Create LayoutParams with MATCH_PARENT width and 0 height, then set weight to 1
					                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					                    LinearLayout.LayoutParams.MATCH_PARENT, 
					                    0,  // Height must be 0 when using weight
					                    1   // Weight set to 1
					                 );
					                 
					                // Apply the parameters to the container
					                newContainer.setLayoutParams(params);
					                
					                newTextView.setText(newText);
					                showMore2.setText("Show Less");
					            } else{
					                
					                // Create LayoutParams with MATCH_PARENT width and 0 height, then set weight to 1
					                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					                    LinearLayout.LayoutParams.MATCH_PARENT, 
					                    LinearLayout.LayoutParams.WRAP_CONTENT);
					                    
					                // Apply the parameters to the container
					                newContainer.setLayoutParams(params);
					                
					                newTextView.setText(collapsedNewText);
					                showMore2.setText("Show More");
					            }
				        }
			    });
		
		    // Add views to layout
		    layout.addView(text1);
		    layout.addView(showMore1);
		    layout.addView(originalContainer);
		    
		    layout.addView(text2);
		    layout.addView(showMore2);
		    layout.addView(newContainer);
		
		    // Add layout
		    builder.setView(layout);
		
		    // "Replace" button
		    builder.setPositiveButton("Replace", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface _dialog, int _which) {
				            if (onReplace != null) onReplace.run();
				            _dialog.dismiss();
				        }
			    });
		    // "Cancel" button
		    builder.setNegativeButton("Cancel", null);
		
		    // Show the dialog
		    builder.show();
	}
	
	// END #####(↑↑↑)#####("REPLACE DIALOG (MAIN LOGIC)")#####(↑↑↑)#####
	
	
	
	
	
	
	// START #####(↓↓↓)#####("Creates a container with both vertical and horizontal scrolling for text.")#####(↓↓↓)#####
	private LinearLayout createScrollableTextView(Context context, String text) {
		    LinearLayout container = new LinearLayout(context);
		    container.setOrientation(LinearLayout.VERTICAL);
		    container.setBackgroundColor(Color.BLACK);
		    container.setPadding(5, 5, 5, 5);
		
		    // Create border
		    GradientDrawable border = new GradientDrawable();
		    border.setColor(Color.BLACK);
		    border.setStroke(5, Color.parseColor("#ffc107")); // Yellow border
		    container.setBackground(border);
		
		    // Create inner ScrollView (vertical)
		    ScrollView verticalScrollView = new ScrollView(context);
		    verticalScrollView.setFillViewport(true);
		    verticalScrollView.setLayoutParams(new ViewGroup.LayoutParams(
		            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // Adjust height as needed
		
		    // Create HorizontalScrollView
		    HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
		    horizontalScrollView.setFillViewport(true);
		
		    // Create TextView
		    TextView textView = new TextView(context);
		    textView.setPadding(20, 10, 20, 10);
		    textView.setTextColor(Color.WHITE);
		    textView.setMovementMethod(new ScrollingMovementMethod());
		
		    // Add TextView to HorizontalScrollView
		    horizontalScrollView.addView(textView);
		
		    // Add HorizontalScrollView to VerticalScrollView
		    verticalScrollView.addView(horizontalScrollView);
		
		    // Add VerticalScrollView to the container
		    container.addView(verticalScrollView);
		
		    // Store textView in container for later reference
		    container.setTag(textView);
		
		    return container;
	}
	// END #####(↑↑↑)#####("Creates a container with both vertical and horizontal scrolling for text.")#####(↑↑↑)#####
	
	
	
	
	
	
	// START #####(↓↓↓)#####("countLines")#####(↓↓↓)#####
	public static int countLines(String text) {
		    if (text == null || text.isEmpty()) return 0;
		
		    int lines = 0;
		    try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
			        while (reader.readLine() != null) {
				            lines++;
				        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		    return lines;
	}
	// END #####(↑↑↑)#####("countLines")#####(↑↑↑)#####
	
	
	
	
	
	
	// START #####(↓↓↓)#####("getCollapsedText")#####(↓↓↓)#####
	private static String getCollapsedText(String text, int maxLines) {
		    if (text == null || text.isEmpty()) return text;
		
		    StringBuilder collapsed = new StringBuilder();
		    int lines = 0;
		
		    try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
			        String line;
			        while ((line = reader.readLine()) != null) {
				            collapsed.append(line).append("\n");
				            lines++;
				            if (lines >= maxLines) break;
				        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		
		    return collapsed.toString().trim() + "\n...";
	}
	// END #####(↑↑↑)#####("getCollapsedText")#####(↑↑↑)#####
	
	
	
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