package upload;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;

public class UploadMain extends Activity implements OnClickListener {
	private static final int REQUEST_PICK_FILE = 1;

	private TextView filePath;
	private Button Browse;
	private File selectedFile;
	String js_username ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_main);
		filePath = (TextView) findViewById(R.id.file_path);
		Browse = (Button) findViewById(R.id.browse);
		Browse.setOnClickListener(this);
		js_username = getIntent().getStringExtra("js_username");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.browse:
			Intent intent = new Intent(this, FilePicker.class);
			startActivityForResult(intent, REQUEST_PICK_FILE);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {

			switch (requestCode) {

			case REQUEST_PICK_FILE:

				if (data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {

					selectedFile = new File(
							data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
					filePath.setText(selectedFile.getPath());
					Intent i = new Intent(this, UploadResumeAct.class);
					i.putExtra("path_of_file", selectedFile.getPath());
					i.putExtra("js_username", js_username);
					Toast.makeText(getApplicationContext(),
							"[][][]]+" + selectedFile.getPath(),
							Toast.LENGTH_LONG).show();
					this.finish();
					startActivity(i);
				}
				break;
			}
		}
	}
}
