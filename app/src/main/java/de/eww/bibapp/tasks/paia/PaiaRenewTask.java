package de.eww.bibapp.tasks.paia;

import org.json.JSONObject;

import android.content.SharedPreferences;
import de.eww.bibapp.PaiaHelper;
import de.eww.bibapp.constants.Constants;
import de.eww.bibapp.fragments.AccountBorrowedFragment;

/**
 * @author Christoph Schönfeld - effective WEBWORK GmbH
 * 
 * This file is part of the Android BibApp Project
 * =========================================================
 * performs paia renew task
 */
public class PaiaRenewTask extends AbstractPaiaTask
{
	public PaiaRenewTask(AccountBorrowedFragment fragment)
	{
		super(fragment);
	}
	
	@Override
	protected JSONObject doInBackground(String... params)
	{
		String jsonString = params[0];
		
		// get url
		SharedPreferences settings = this.fragment.getActivity().getPreferences(0);
		int spinnerValue = settings.getInt("local_catalog", Constants.LOCAL_CATALOG_DEFAULT);
		
		String paiaUrl = Constants.getPaiaUrl(spinnerValue) + "/core/" + PaiaHelper.getUsername() + "/renew?access_token=" + PaiaHelper.getAccessToken();
		
		JSONObject paiaResponse = new JSONObject();
		
		try
		{
			this.setPostParameters(jsonString);
			paiaResponse = this.performRequest(paiaUrl);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			this.raiseFailure();
		}
		
		return paiaResponse;
	}
	
	@Override
	protected void onPostExecute(JSONObject result)
	{
		((AccountBorrowedFragment) this.fragment).onRenew(result);
	}
}
