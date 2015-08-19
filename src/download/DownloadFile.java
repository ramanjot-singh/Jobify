package download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.myproject.R;
import com.example.myproject.R.id;
import com.example.myproject.R.layout;
import com.example.myproject.R.menu;
import com.example.myproject.connection;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DownloadFile extends Activity {
	protected static final String TAG = null;
	ProgressBar pb;
	Dialog dialog;
	int downloadedSize = 0;
	int totalSize = 0;
	TextView cur_val, t1;
	String dwnload_file_path;
	String js_username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_file);
		connection con = new connection();
		// js_username = "aa.pngcats.jpg";
		js_username = "aa.png" + getIntent().getStringExtra("js_username")
				+ ".pdf";
		dwnload_file_path = con.path + "/uploads/" + js_username;
		Button b = (Button) findViewById(R.id.b1);

		t1 = (TextView) findViewById(R.id.tv1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showProgress(dwnload_file_path);

				new Thread(new Runnable() {
					public void run() {
						downloadFile();
					}
				}).start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.download_file, menu);
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

	void downloadFile() {

		try {
			URL url = new URL(dwnload_file_path);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			// connect
			urlConnection.connect();

			// set the path where we want to save the file
			File SDCardRoot = Environment.getExternalStorageDirectory();
			// File SDCardRoot = new File("/storage/sdcard1");
			// create a new file, to save the downloaded file
			// File file = new File(SDCardRoot, "downloaded_file.png");
			File file = new File(SDCardRoot, js_username);
			// t1.setText(file.getPath() + "" + file.getName());
			FileOutputStream fileOutput = new FileOutputStream(file);

			// Stream used for reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();

			// this is the total size of the file which we are downloading
			totalSize = urlConnection.getContentLength();

			runOnUiThread(new Runnable() {
				public void run() {
					pb.setMax(totalSize);
				}
			});

			// create a buffer...
			byte[] buffer = new byte[1024];
			int bufferLength = 0;

			while ((bufferLength = inputStream.read(buffer)) > 0) {
				fileOutput.write(buffer, 0, bufferLength);
				downloadedSize += bufferLength;
				// update the progressbar //
				runOnUiThread(new Runnable() {
					public void run() {
						pb.setProgress(downloadedSize);
						float per = ((float) downloadedSize / totalSize) * 100;
						cur_val.setText("Downloaded " + downloadedSize
								+ "KB / " + totalSize + "KB (" + (int) per
								+ "%)");
					}
				});
			}
			// close the output stream when complete //
			fileOutput.close();
			runOnUiThread(new Runnable() {
				public void run() {
					// pb.dismiss(); // if you want close it..

				}
			});

		} catch (final MalformedURLException e) {
			showError("Error : MalformedURLException " + e);
			e.printStackTrace();
		} catch (final IOException e) {

			showError("Error : IOException " + e);
			e.printStackTrace();

		} catch (final Exception e) {
			showError("Error : Please check your internet connection " + e);
		}
	}

	void showError(final String err) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(DownloadFile.this, err, 100).show();
				Toast.makeText(DownloadFile.this, "No Resume To show", 1000)
						.show();
				dialog.cancel();
			}
		});
	}

	void showProgress(String file_path) {
		dialog = new Dialog(DownloadFile.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.myprogressdialog);
		dialog.setTitle("Download Progress");

		TextView text = (TextView) dialog.findViewById(R.id.tv1);
		text.setText("Downloading file from ... " + file_path);
		cur_val = (TextView) dialog.findViewById(R.id.cur_pg_tv);
		cur_val.setText("Starting download...");
		dialog.show();

		pb = (ProgressBar) dialog.findViewById(R.id.progress_bar);
		pb.setProgress(0);

	}

	public void note() {
		final int id = 1;

		final NotificationManager mNotifyManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
		final Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("Picture Download")
				.setContentText("Download in progress")
				.setSmallIcon(R.drawable.abc_btn_check_material);
		// Start a lengthy operation in a background thread
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				// Do the "lengthy" operation 20 times
				for (incr = 0; incr <= 100; incr += 5) {
					// Sets the progress indicator to a max value, the
					// current completion percentage, and "determinate"
					// state
					mBuilder.setProgress(100, incr, false);
					// Displays the progress bar for the first time.
					mNotifyManager.notify(id, mBuilder.build());
					// Sleeps the thread, simulating an operation
					// that takes time
					try {
						// Sleep for 5 seconds
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						Log.d(TAG, "sleep failure");
					}
				}
				// When the loop is finished, updates the notification
				mBuilder.setContentText("Download complete")
				// Removes the progress bar
						.setProgress(0, 0, false);
				mNotifyManager.notify(id, mBuilder.build());
			}
		}
		// Starts the thread by calling the run() method in its Runnable
		).start();
	}

}
