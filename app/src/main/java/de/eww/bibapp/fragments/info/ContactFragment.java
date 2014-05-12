package de.eww.bibapp.fragments.info;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.eww.bibapp.MainActivity;
import de.eww.bibapp.R;

/**
 * @author Christoph Schönfeld - effective WEBWORK GmbH
 * 
 * This file is part of the Android BibApp Project
 * =========================================================
 * Contact Fragment, providing static information about the app distributer
 */
public class ContactFragment extends Fragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// enable option menu
		this.setHasOptionsMenu(true);
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState)
	{
        super.onActivityCreated(savedInstanceState);
        
		this.getActivity().invalidateOptionsMenu();
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_contact_main, container, false);
		
		TextView contactView = (TextView) v.findViewById(R.id.contact_text);
		contactView.setText(this.getText(R.string.contact_text));
		
		ActionBar actionBar = MainActivity.instance.getActionBar();
		
		// set title
		actionBar.setTitle(R.string.actionbar_info);
		actionBar.setSubtitle(R.string.info_button_contact);
		
		// enable up navigation
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		return v;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
	    switch ( item.getItemId() )
	    {
	        case android.R.id.home:
	        	// app icon in action bar clicked; go up
	        	InfoContainerFragment infoFragment = (InfoContainerFragment) this.getActivity().getSupportFragmentManager().findFragmentByTag("info");
	    		infoFragment.up();
	        	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu)
	{
		super.onPrepareOptionsMenu(menu);
		menu.clear();
	}
}