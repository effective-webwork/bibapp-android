package de.eww.bibapp.tasks.paia;

import org.json.JSONObject;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import de.eww.bibapp.constants.Constants;

/**
 * @author Christoph Schönfeld - effective WEBWORK GmbH
 * 
 * This file is part of the Android BibApp Project
 * =========================================================
 * performs paia login task
 */
public class PaiaLoginTask extends AbstractPaiaTask
{
	public PaiaLoginTask(Fragment callingFragment)
	{
		super(callingFragment);
	}
	
	@Override
	protected JSONObject doInBackground(String... params)
	{
		String username = params[0];
		String password = params[1];
		
		JSONObject result = new JSONObject();
		
		// get url
		SharedPreferences settings = this.fragment.getActivity().getPreferences(0);
		int spinnerValue = settings.getInt("local_catalog", Constants.LOCAL_CATALOG_DEFAULT);
		
		String paiaUrl = Constants.getPaiaUrl(spinnerValue) + "/auth/login?username=" + username + "&password=" + password + "&grant_type=password";
		
		try
		{
			JSONObject paiaResponse = this.performRequest(paiaUrl);
			
			if ( paiaResponse.has("error") )
			{
					if ( paiaResponse.getString("code").equals("401") )
					{
						// wrong login - return json object with empty access token
						result.put("access_token", "");
					}
			}
			else
			{
				// login correct - store access token
				result.put("access_token", paiaResponse.getString("access_token"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			this.raiseFailure();
		}
		
		return result;
	}
}
