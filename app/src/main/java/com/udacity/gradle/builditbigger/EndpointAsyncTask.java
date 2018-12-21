package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

@SuppressWarnings({"ALL", "WeakerAccess"})
@SuppressLint("StaticFieldLeak")
class EndpointAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi apiService = null;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private Callback mCallback;

//    public EndpointAsyncTask() {}

    public EndpointAsyncTask(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected String doInBackground(Context... params) {

        if (apiService == null) { //only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setApplicationName("buildItBigger")
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @SuppressWarnings("RedundantThrows")
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) throws
                                IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            // end options for devappserver
            apiService = builder.build();
        }
        context = params[0];
        try {
            return apiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if (result != null) {
            mCallback.onFinished(result);
        }
//        final Intent intent = new Intent(context, LibJokeActivity.class);
//        intent.putExtra("joke", result);
//        context.startActivity(intent);
    }

    public interface Callback {
        void onFinished(String result);
    }
}
