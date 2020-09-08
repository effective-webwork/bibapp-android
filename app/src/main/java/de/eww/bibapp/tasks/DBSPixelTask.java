package de.eww.bibapp.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;

import de.eww.bibapp.R;
import de.eww.bibapp.URLConnectionHelper;

public class DBSPixelTask extends AsyncTask<Void, Void, Void> {

    Context mContext;

	public DBSPixelTask(Context context) {
        mContext = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		URLConnectionHelper urlConnectionHelper = new URLConnectionHelper(mContext.getResources().getString(R.string.bibapp_tracking_url), mContext);

		try {
			urlConnectionHelper.configure();
			urlConnectionHelper.connect(null);

			InputStream input = new BufferedInputStream(urlConnectionHelper.getInputStream());

			String httpResponse = urlConnectionHelper.readStream(input);
			Log.v("DBS", httpResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			urlConnectionHelper.disconnect();
		}

		return null;
	}
}
