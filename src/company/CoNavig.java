package company;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.connection;

import download.DownloadFile;

import android.app.Activity;
import android.sax.StartElementListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CoNavig extends ActionBarActivity implements
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
	String s[] = { "Post A Job For Free", "Search An Employee",
			"View your Jobs", "Log Out" };
	SimpleAdapter s1;
	static SimpleAdapter s2;
	static SimpleAdapter s3;
	ArrayList<HashMap<String, String>> a1;
	static ArrayList<HashMap<String, String>> a2;
	static ArrayList<HashMap<String, String>> a3;
	ListView l1;
	int img[] = { R.drawable.abc_btn_check_material, R.drawable.emphire,
			R.drawable.abc_ic_menu_copy_mtrl_am_alpha,
			R.drawable.abc_btn_check_to_on_mtrl_015 };
	String id[] = { "image", "name" };
	int cnt[] = { R.id.coImageView, R.id.coTextView };
	private CharSequence mTitle;
	static TextView id_from_table;
	static String co_username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_co_navig);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// *******************gettting username********************
		co_username = getIntent().getStringExtra("co_username");
		Toast.makeText(this, co_username, 100).show();
		l1 = (ListView) findViewById(R.id.coList);
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
		case "Post A Job For Free":
			mTitle = "Post A Job For Free";
			break;

		case "View your Jobs":
			mTitle = "View your Jobs";
			break;
		case "Search for an Employee":
			mTitle = "Search An Employee";
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
			getMenuInflater().inflate(R.menu.co_navig, menu);
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
	public static class PlaceholderFragment extends Fragment {
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
			View rootView = inflater.inflate(R.layout.co_post_job, container,
					false);
			String a = getArguments().getString("section_number");
			if (a == "Post A Job For Free") {
				rootView = inflater.inflate(R.layout.co_post_job, container,
						false);
				ArrayAdapter<String> a1;
				String s[] = { "No experience required", "1 year", "2 years",
						"3 years", "4 years", "5years", "More than 5 years" };

				Spinner s1 = (Spinner) rootView
						.findViewById(R.id.co_post_job_spinner);
				a1 = new ArrayAdapter<String>(rootView.getContext(),
						android.R.layout.simple_spinner_item, s);
				s1.setAdapter(a1);
				// ////////////////////////////////////////
				// //////////////////complete it

			} else if (a == "Search An Employee") {
				rootView = inflater.inflate(R.layout.try22, container, false);
				String s_xp[] = { "No experience required", "0 Year", "1 year",
						"2 years", "3 years", "4 years", "5years",
						"More than 5 years" };
				String s_location[] = { "Any Where", "Patiala", "Chandigarh",
						"Chennai", "Hyderabad", "Bangalore", "Pune" };

				Spinner spin1 = (Spinner) rootView
						.findViewById(R.id.xp_spinner);
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
			} else if (a == "View your Jobs") {
				rootView = inflater.inflate(R.layout.view_your_job_list,
						container, false);
				ListView view_job = (ListView) rootView
						.findViewById(R.id.view_job_list);
				String id[] = { "company", "title", "description", "city",
						"xp", "salary", "d_added", "id" };
				int cnt3[] = { R.id.textView2, R.id.textView1, R.id.textView6,
						R.id.textView4, R.id.textView3, R.id.textView5,
						R.id.textView7, R.id.hidden_id };
				view_jobs_function(getActivity().getApplicationContext());
				s3 = new SimpleAdapter(getActivity().getApplicationContext(),
						a3, R.layout.js_list_design, id, cnt3);
				view_job.setAdapter(s3);
				view_job.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						View v = parent.getChildAt(position);
						id_from_table = (TextView) v
								.findViewById(R.id.hidden_id);
						Toast.makeText(getActivity().getApplicationContext(),
								"hi" + id_from_table.getText().toString(), 1000)
								.show();
						JSONArray ja = null;
						try {
							connection con = new connection();
							String path = con.path
									+ "get_js_usernames_on_view_job.php?id="
									+ URLEncoder.encode(id_from_table.getText()
											.toString(), "UTF-8");

							HttpGet hg = new HttpGet(path);

							HttpResponse hr = con.hp.execute(hg);

							int th = hr.getStatusLine().getStatusCode();
							if (th == 200) {
								String ss = EntityUtils
										.toString(hr.getEntity()).trim();
								Toast.makeText(
										getActivity().getApplicationContext(),
										ss, 1000).show();
								String rs = "";
								if (ss.equals("No results found")) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"Sorry!No one applied", 1000)
											.show();
								} else {
									ja = new JSONArray(ss);
									for (int i = 0; i < ja.length(); i++) {
										JSONObject json = ja.getJSONObject(i);
										rs = rs + json.toString();

									}
								}
							}

							else {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"check your internet connecttion.Server error:"
												+ th, Toast.LENGTH_LONG).show();
							}
						} catch (Exception e) {
							Toast.makeText(
									getActivity().getApplicationContext(),
									e.toString(), 1000).show();
						}
						if (ja != null) {
							Intent i = new Intent(getActivity(),
									CoExpandEmployees.class);

							i.putExtra("array", ja.toString());
							startActivity(i);
						}
					}

				});
			} else if (a == "Log Out") {
				Intent i = new Intent(getActivity().getApplicationContext(),
						MainActivity.class);
				getActivity().finish();
				startActivity(i);
			}

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((CoNavig) activity).onSectionAttached(getArguments().getString(
					ARG_SECTION_NUMBER));
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

	public void hello(View v) {
		Toast.makeText(getApplicationContext(), "Hi", 1000).show();
	}

	public void get(View v) {

		int index[] = new int[100];
		ListView list_of_search_js = (ListView) findViewById(R.id.l21_22);
		String id[] = { "name", "exp_salary", "xp", "skills", "id" };
		int cnt[] = { R.id.textView1, R.id.textView2, R.id.textView3,
				R.id.textView4, R.id.hidden_id };
		EditText e1 = (EditText) findViewById(R.id.try22_edit_text);
		String[] splited = e1.getText().toString().split("\\s+");
		a2 = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < splited.length; i++) {
			add_search_jobs(splited[i], index);
			s2 = new SimpleAdapter(getApplicationContext(), a2,
					R.layout.co_search_js_design, id, cnt) {
				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					View v = super.getView(position, convertView, parent);
					final TextView hid_hit = (TextView) v
							.findViewById(R.id.hidden_id);
					Button b = (Button) v.findViewById(R.id.button1);
					b.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {

							download_resume(getApplication(), hid_hit.getText()
									.toString());

						}
					});
					return v;
				}

			};
			list_of_search_js.setAdapter(s2);
		}

	}

	public void add_search_jobs(String skill, int[] arr_of_index) {

		try {

			connection con = new connection();
			String path = con.path + "searchJs.php?skills="
					+ URLEncoder.encode(skill, "UTF-8");
			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();
				if (s.equals("No result found")) {
					Toast.makeText(this, s, 1000).show();
				} else {
					Toast.makeText(this, s, 1000).show();

					JSONArray ja = new JSONArray(s);
					for (int i = 0; i < ja.length(); i++) {
						JSONObject json = ja.getJSONObject(i);
						int p = Integer.valueOf(json.getString("id"));
						if (arr_of_index[p] == 0) {
							arr_of_index[p] = 1;
							HashMap<String, String> h2 = new HashMap<String, String>();
							h2.put("name", json.getString("name"));
							h2.put("exp_salary", json.getString("exp_salary"));
							h2.put("xp", json.getString("xp"));
							h2.put("skills", json.getString("skills"));
							h2.put("id", json.getString("username"));
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

	public static void view_jobs_function(Context conte) {
		a3 = new ArrayList<HashMap<String, String>>();
		try {

			connection con = new connection();
			String path = con.path + "view_your_job.php?username="
					+ URLEncoder.encode(co_username, "UTF-8");
			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();
				Toast.makeText(conte, s, 1000).show();
				if (!(s.equals("No results found"))) {

					JSONArray ja = new JSONArray(s);
					for (int i = 0; i < ja.length(); i++) {
						JSONObject json = ja.getJSONObject(i);
						HashMap<String, String> h2 = new HashMap<String, String>();
						h2.put("company", json.getString("company"));
						h2.put("title", json.getString("title"));
						h2.put("description", json.getString("description"));
						h2.put("city", json.getString("city"));
						h2.put("xp", json.getString("xp"));
						h2.put("salary", json.getString("salary"));
						h2.put("d_added", json.getString("d_added"));
						h2.put("id", json.getString("id"));
						a3.add(h2);

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

	public static void get_user_from_array(Context conte) {
		ListView list_of_search_js = (ListView) ((Activity) conte)
				.findViewById(R.id.l21_22);
		String id[] = { "name", "exp_salary", "xp", "skills" };
		int cnt[] = { R.id.textView1, R.id.textView2, R.id.textView3,
				R.id.textView4 };
		// add_search_jobs1();
		// s2 = new SimpleAdapter(getApplicationContext(), a2,
		// R.layout.co_search_js_design, id, cnt);
		list_of_search_js.setAdapter(s2);
	}

	void add_search_jobs1() {

		a2 = new ArrayList<HashMap<String, String>>();
		try {

			connection con = new connection();
			String path = con.path + "searchJs.php?skills=" + "AND";

			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();
				Toast.makeText(this, s, 1000).show();
				String rs = "";

				JSONArray ja = new JSONArray(s);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject json = ja.getJSONObject(i);
					rs = rs + json.toString();
					HashMap<String, String> h2 = new HashMap<String, String>();
					h2.put("name", json.getString("name"));
					h2.put("exp_salary", json.getString("exp_salary"));
					h2.put("xp", json.getString("xp"));
					h2.put("skills", json.getString("skills"));
					a2.add(h2);

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

	public void download_resume(Context conte, String id) {
		Intent i = new Intent(conte, DownloadFile.class);
		i.putExtra("js_username", id);
		startActivity(i);

	}

}
