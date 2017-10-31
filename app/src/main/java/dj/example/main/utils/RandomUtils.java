package dj.example.main.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

import dj.example.main.MyApplication;
import dj.example.main.activities.TwoTabsBaseActivity;
import dj.example.main.uiutils.ResourceReader;

/**
 * Created by DJphy on 08-07-2017.
 */

public class RandomUtils {


    private static RandomUtils ourInstance;

    public static RandomUtils getInstance(){
        if (ourInstance == null){
            ourInstance = new RandomUtils();
        }
        return ourInstance;
    }

    private RandomUtils(){

    }

    public void clearInstance(){
        ourInstance = null;
    }

    public boolean isConnected(Context c) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null) {
            if(ni.getType() == 1 && ni.isConnectedOrConnecting()) {
                haveConnectedWifi = true;
            }

            if(ni.getType() == 0 && ni.isConnectedOrConnecting()) {
                haveConnectedMobile = true;
            }
        }

        return haveConnectedWifi || haveConnectedMobile;
    }


    public void launchHome(Activity activity){
        Intent intent = new Intent(activity, TwoTabsBaseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        //finish();
    }

    public void assignMikePenzIcon(IIcon icoToUSe, View view, int colorRsr){
        int color = ResourceReader.getInstance().getColorFromResource(colorRsr);
        Drawable temp = new IconicsDrawable(MyApplication.getInstance())
                .icon(icoToUSe)
                .color(color)
                .sizeDp(20);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(temp);
        }
        if (view instanceof ImageButton){
            ((ImageButton) view).setImageDrawable(temp);
        }
    }

    public static int convertDpToPixel(float dp){
        Resources resources = MyApplication.getInstance().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public boolean isPhone(Context context) {
        boolean isTab = isTablet(context);
        return !isTab;
    }
}
