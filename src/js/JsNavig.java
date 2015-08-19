package js;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import upload.UploadMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.connection;

public class JsNavig extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	String s[] = { "My Account", "Search For Jobs", "Search Companies",
			"Edit Profile", "Log Out" };
	SimpleAdapter s1;

	static SimpleAdapter s2;
	static SimpleAdapter my_account_adap;
	ArrayList<HashMap<String, String>> a1;

	static ArrayList<HashMap<String, String>> a2;
	static ArrayList<HashMap<String, String>> my_account_a_list;
	ListView l1;
	int img[] = { R.drawable.abc_btn_check_material, R.drawable.search,
			R.drawable.abc_ic_menu_copy_mtrl_am_alpha,
			R.drawable.abc_ic_menu_cut_mtrl_alpha,
			R.drawable.abc_ic_menu_paste_mtrl_am_alpha };
	String id[] = { "image", "name" };
	int cnt[] = { R.id.coImageView, R.id.coTextView };
	private CharSequence mTitle;
	String js_name = "Raman";

	// static String js_username = "raman.2020@gmail.com";
	static String js_username;
	static TextView t_salary;
	static TextView t_xp;
	static TextView id_from_table;
	static Button upload_resume_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_js_navig);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// ***************** getting username*******************
		js_username = getIntent().getStringExtra("js_username");

		l1 = (ListView) findViewById(R.id.jsList);
		add();
		s1 = new SimpleAdapter(this, a1, R.layout.co_nav_list_of_items, id, cnt);
		l1.setAdapter(s1);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(s[position])).commit();
	}

	public void onSectionAttached(String string) {
		switch (string) {
		case "Search For Jobs":
			mTitle = "Search For Jobs";
			break;
		case "My Account":
			mTitle = "My Account";
			break;
		case "Edit Profile":
			mTitle = "Edit Profile";
			break;
		case "Search Companies":
			mTitle = "Search Companies";
			break;

		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.js_navig, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements
			OnSeekBarChangeListener {

		SeekBar seek_salary, seek_xp;

		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(String sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putString(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.try11, container, false);
			String s_xp[] = { "No experience required", "0 Year", "1 year",
					"2 years", "3 years", "4 years", "5years",
					"More than 5 years" };
			String s_location[] = { "Any Where", "Patiala", "Chandigarh",
					"Chennai", "Hyderabad", "Bangalore", "Pune" };

			Spinner spin1 = (Spinner) rootView.findViewById(R.id.xp_spinner);
			Spinner spin2 = (Spinner) rootView
					.findViewById(R.id.location_spinner);
			ArrayAdapter<String> a44 = new ArrayAdapter<String>(
					rootView.getContext(),
					android.R.layout.simple_spinner_item, s_xp);
			ArrayAdapter<String> a55 = new ArrayAdapter<String>(
					rootView.getContext(),
					android.R.layout.simple_spinner_item, s_location);
			spin1.setAdapter(a44);
			spin2.setAdapter(a55);

			String a = getArguments().getString("section_number");
			if (a == "Edit Profile") {
				rootView = inflater.inflate(R.layout.js_edit_profile,
						container, false);
				seek_salary = (SeekBar) rootView.findViewById(R.id.seekBar1);
				seek_xp = (SeekBar) rootView.findViewById(R.id.seekBar2);
				t_salary = (TextView) rootView.findViewById(R.id.seek1_change);
				t_xp = (TextView) rootView.findViewById(R.id.seek2_change);
				upload_resume_btn = (Button) rootView
						.findViewById(R.id.button2);
				t_salary.setText("");
				t_xp.setText("");
				seek_salary.setOnSeekBarChangeListener(this);
				seek_xp.setOnSeekBarChangeListener(this);
				upload_resume_btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent(getActivity(), UploadMain.class);
						i.putExtra("js_username", js_username);
						startActivity(i);

					}
				});
			} else if (a == "Log Out") {
				Intent i = new Intent(getActivity().getApplicationContext(),
						MainActivity.class);
				getActivity().finish();
				startActivity(i);
			} else if (a == "My Account") {

				rootView = inflater.inflate(R.layout.js_my_account_list,
						container, false);
				ListView my_account_list_of_matching_jobs = (ListView) rootView
						.findViewById(R.id.my_account_list_view);
				String id[] = { "company", "title", "city", "xp", "salary",
						"d_added", "id" };
				int cnt2[] = { R.id.textView2, R.id.textView1, R.id.textView4,
						R.id.textView3, R.id.textView5, R.id.textView7,
						R.id.hidden_id };
				// adding to the list using function
				my_account_list_function(getActivity().getApplicationContext());

				my_account_adap = new SimpleAdapter(getActivity()
						.getApplicationContext(), my_account_a_list,
						R.layout.js_list_design, id, cnt2) {
					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						View v = super.getView(position, convertView, parent);
						final TextView hid_hit = (TextView) v
								.findViewById(R.id.hidden_id);
						Button b = (Button) v.findViewById(R.id.apply_btn);
						b.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {

								apply_and_add(getActivity().getApplication(),
										hid_hit.getText().toString());

							}
						});
						return v;
					}

				};

				my_account_list_of_matching_jobs.setAdapter(my_account_adap);
				my_account_list_of_matching_jobs
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								View v = parent.getChildAt(position);

								TextView id_text = (TextView) v
										.findViewById(R.id.hidden_id);
								Intent i = new Intent(getActivity(),
										JsExpandJob.class);
								i.putExtra("id", id_text.getText().toString());
								i.putExtra("js_username", js_username);
								startActivity(i);

							}
						});

			}
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((JsNavig) activity).onSectionAttached(getArguments().getString(
					ARG_SECTION_NUMBER));
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (seekBar.equals(seek_salary)) {
				t_salary.setText(String.valueOf(progress) + "per_month");
			} else if (seekBar.equals(seek_xp)) {
				t_xp.setText(String.valueOf(progress) + "Year(s)");
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}
	}

	public void add() {
		a1 = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < s.length; i++) {
			HashMap<String, String> h1 = new HashMap<>();
			h1.put("image", String.valueOf(img[i]));
			h1.put("name", s[i]);
			a1.add(h1);

		}
	}

	public void get(View v) {
		int index[] = new int[100];
		ListView list_of_search_jobs = (ListView) findViewById(R.id.l21);
		String id[] = { "company", "title", "description", "city", "xp", "id" };
		int cnt2[] = { R.id.textView2, R.id.textView1, R.id.textView6,
				R.id.textView4, R.id.textView3, R.id.hidden_id };
		a2 = new ArrayList<HashMap<String, String>>();

		// *******************looping for search*************************

		EditText e1 = (EditText) findViewById(R.id.try_edit_text);
		Spinner spiny1 = (Spinner) findViewById(R.id.location_spinner);
		Spinner spiny2 = (Spinner) findViewById(R.id.xp_spinner);
		String[] splited = e1.getText().toString().split("\\s+");
		for (int i = 0; i < splited.length; i++) {

			add_search_jobs(splited[i], index, spiny1.getSelectedItem()
					.toString(), spiny2.getSelectedItem().toString());
			s2 = new SimpleAdapter(getApplicationContext(), a2,
					R.layout.js_list_design, id, cnt2) {
				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					View v = super.getView(position, convertView, parent);
					final TextView hid_hit1 = (TextView) v
							.findViewById(R.id.hidden_id);
					Button b = (Button) v.findViewById(R.id.apply_btn);
					b.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							apply_and_add(JsNavig.this, hid_hit1.getText()
									.toString());
						}
					});
					return v;
				}

			};
			list_of_search_jobs.setAdapter(s2);

		}
		list_of_search_jobs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				View v = parent.getChildAt(position);
				id_from_table = (TextView) v.findViewById(R.id.hidden_id);

				Intent i = new Intent(JsNavig.this, JsExpandJob.class);
				i.putExtra("id", id_from_table.getText().toString());
				i.putExtra("js_username", js_username);
				startActivity(i);

			}

		});

	}

	public void add_search_jobs(String skill, int[] arr_of_index,
			String location1, String xp1) {
		Toast.makeText(getApplicationContext(), location1 + ":" + xp1, 1000)
				.show();
		try {

			connection con = new connection();
			String path = con.path + "searchJobs_v2.php?skill="
					+ URLEncoder.encode(skill, "UTF-8") + "&location="
					+ URLEncoder.encode(location1, "UTF-8") + "&xp="
					+ URLEncoder.encode(xp1, "UTF-8");
			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();
				if (s.equals("No result found")) {
					Toast.makeText(this, s, 1000).show();
				} else {
					Toast.makeText(this, s, 1000).show();
					String rs = "";

					JSONArray ja = new JSONArray(s);
					for (int i = 0; i < ja.length(); i++) {
						JSONObject json = ja.getJSONObject(i);
						int p = Integer.valueOf(json.getString("id"));
						if (arr_of_index[p] == 0) {
							arr_of_index[p] = 1;
							rs = rs + json.toString();
							HashMap<String, String> h2 = new HashMap<String, String>();
							h2.put("company", json.getString("company"));
							h2.put("title", json.getString("title"));
							h2.put("city", json.getString("city"));
							h2.put("xp", json.getString("xp"));
							h2.put("id", json.getString("id"));
							a2.add(h2);
						}
					}
				}

			}

			else {
				Toast.makeText(getApplicationContext(),
						"check your internet connecttion.Server error:" + th,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), 1000).show();
		}
	}

	public static void my_account_list_function(Context conte) {
		my_account_a_list = new ArrayList<HashMap<String, String>>();
		String js_my_account_skills = null;
		try {

			connection con = new connection();
			String path = con.path
					+ "js_get_jobs_acc_to_skills.php?js_username="
					+ URLEncoder.encode(js_username, "UTF-8");

			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();
				Toast.makeText(conte, s, 1000).show();
				String rs = "";

				JSONArray ja = new JSONArray(s);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject json = ja.getJSONObject(i);
					rs = rs + json.toString();

					js_my_account_skills = json.getString("skills");

				}

			}

			String path2 = con.path + "searchJobs.php?skill="
					+ URLEncoder.encode(js_my_account_skills, "UTF-8");
			HttpGet hg2 = new HttpGet(path2);
			HttpResponse hr2 = con.hp.execute(hg2);

			int th2 = hr2.getStatusLine().getStatusCode();
			if (th2 == 200) {
				String s = EntityUtils.toString(hr2.getEntity()).trim();
				if (s.equals("No result found")) {
					Toast.makeText(conte, s, 1000).show();
				} else {
					Toast.makeText(conte, s, 1000).show();
					String rs = "";

					JSONArray ja = new JSONArray(s);
					for (int i = 0; i < ja.length(); i++) {
						JSONObject json = ja.getJSONObject(i);
						rs = rs + json.toString();
						HashMap<String, String> h2 = new HashMap<String, String>();
						h2.put("company", json.getString("company"));
						h2.put("title", json.getString("title"));
						h2.put("city", json.getString("city"));
						h2.put("xp", json.getString("xp"));
						h2.put("id", json.getString("id"));
						my_account_a_list.add(h2);

					}
				}

			}

			else {
				Toast.makeText(conte,
						"check your internet connecttion.Server error:" + th,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(conte, e.toString(), 1000).show();
		}
	}

	public void js_add_details(View v) {
		TextView skill_TextView = (TextView) findViewById(R.id.js_profile_skill);
		if (skill_TextView.getText().toString().equals("")) {
			Toast.makeText(this, "Enter Skills", 1000).show();
		} else {
			try {
				connection con = new connection();

				// **************************************************************************
				// ////////////////////////////////////////
				// //////////////////complete it name of user and adding into
				// table

				String path = con.path
						+ "js_profile_details.php?name="
						+ URLEncoder.encode(js_name, "UTF-8")
						+ "&username="
						+ URLEncoder.encode(js_username, "UTF-8")
						+ "&exp_salary="
						+ URLEncoder.encode(t_salary.getText().toString(),
								"UTF-8")
						+ "&xp="
						+ URLEncoder.encode(t_xp.getText().toString(), "UTF-8")
						+ "&skills="
						+ URLEncoder.encode(
								skill_TextView.getText().toString(), "UTF-8");
				HttpGet hg = new HttpGet(path);

				HttpResponse hr = con.hp.execute(hg);

				int th = hr.getStatusLine().getStatusCode();
				if (th == 200) {
					String ss = EntityUtils.toString(hr.getEntity()).trim();
					Toast.makeText(this, ss, 1000).show();
					// Intent i = new Intent(this, EmpNavigation.class);
					// i.putExtra("username", email.getText().toString());
					// startActivity(i);

				}

				else {
					Toast.makeText(
							getApplicationContext(),
							"check your internet connecttion.Server error:"
									+ th, Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(this, e.toString(), 1000).show();
			}

		}
	}

	public static void apply_and_add(Context conte, String id) {
		try {
			connection con = new connection();
			String path = con.path + "js_id_username_into_table.php?id=" + id
					+ "&js_username=" + URLEncoder.encode(js_username, "UTF-8");
			HttpGet hg = new HttpGet(path);

			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String ss = EntityUtils.toString(hr.getEntity()).trim();
				if (ss.equals("")) {
					Toast.makeText(conte, "Applied", 100).show();
				} else {
					Toast.makeText(conte, ss, 1000).show();
				}
			}

			else {
				Toast.makeText(conte,
						"check your internet connecttion.Server error:" + th,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(conte, e.toString(), 1000).show();
		}
	}
}
