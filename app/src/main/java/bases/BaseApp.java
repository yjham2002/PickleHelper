package bases;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;

import net.khirr.library.foreground.Foreground;

import kr.co.picklecode.helper.helper.R;

public class BaseApp extends MultiDexApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("BaseApp", "onCreate");
        MobileAds.initialize(this, getResources().getString(R.string.ADMOB_ID));
        this.context = this.getApplicationContext();

        Foreground.Companion.init(this);
    }

    public static Context getContext() {
        return BaseApp.context;
    }
}
