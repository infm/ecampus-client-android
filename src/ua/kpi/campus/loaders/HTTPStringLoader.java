package ua.kpi.campus.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


/**
 * Load strings from HTTP using asyncTask
 *
 * @author Artur Dzidzoiev
 * @version 12/7/13
 */
public class HttpStringLoader extends AsyncTaskLoader<HttpResponse> {
    public final static String URL_STRING = "URL";
    public final static String LOG_TAG = HttpStringLoader.class.getName();
    private final String URL_ADDRESS;

    public HttpStringLoader(Context context, String URL_ADDRESS) {
        super(context);
        Log.d(HttpStringLoader.class.getName(), " loader created");
        this.URL_ADDRESS = URL_ADDRESS;
    }

    @Override
    public HttpResponse loadInBackground() {
        Log.d(LOG_TAG, hashCode() + " loadInBackground start");
        return Http.getString(URL_ADDRESS);
    }

    @Override
    protected void onStartLoading() {
            Log.d(LOG_TAG, hashCode() + " onStartLoading");
            if (takeContentChanged())
                forceLoad();
    }

    @Override
    protected void onStopLoading() {
        Log.d(LOG_TAG, hashCode() + " onStopLoading");
        cancelLoad();
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.d(LOG_TAG, hashCode() + " onAbandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        Log.d(LOG_TAG, hashCode() + " onReset");
    }

}
