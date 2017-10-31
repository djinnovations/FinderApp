package dj.example.main.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.androidquery.callback.AjaxStatus;

import java.util.ArrayList;
import java.util.List;

import dj.example.main.R;
import dj.example.main.fragments.MediaContentFragment;
import dj.example.main.utils.IDUtils;
import dj.example.main.utils.IntentKeys;
import dj.example.main.utils.NetworkResultValidator;

/**
 * Created by DJphy on 26-10-2017.
 */

public abstract class MediaDisplayBaseActivity extends BaseActivity {

    //Dont use ButterKnife for this class

    public final int MEDIATYPE_IMAGE = IDUtils.generateViewId();
    public final int MEDIATYPE_VIDEO = IDUtils.generateViewId();

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    /*@Override
    public Activity getSelf() {
        return this;
    }*/

    @Override
    public View getViewForLayoutAccess() {
        return flMediaContainer;
    }

    ProgressBar progressBar;
    FrameLayout flMediaContainer;
    FrameLayout flBodyContainer;

    protected MediaContentFragment mediaContentFragment;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_base_display_media, null);
        super.setContentView(view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        flMediaContainer = (FrameLayout) findViewById(R.id.flMediaContainer);
        flBodyContainer = (FrameLayout) findViewById(R.id.flBodyContainer);
        LayoutInflater.from(this).inflate(layoutResID, flBodyContainer, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaContentFragment == null){
            queryForMediaContent(getQueryUrlForMediaContentDisplay());
        }
    }

    @Override
    public void serverCallEnds(int id, String url, Object json, AjaxStatus status) {
        stopProgress();
        dismissTransOverlay();
        if (id == MEDIA_CONTENT_CALL){
            //// TODO: 28-10-2017 replace dummy
            try {
                boolean success = NetworkResultValidator.getInstance().isResultOK((String) json, status, getViewForLayoutAccess());
                success = true; // remove it
                if (success){
                    mediaContentFragment = new MediaContentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(IntentKeys.MEDIA_CONTENT, prepareDummy());
                    mediaContentFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(flMediaContainer.getId(), mediaContentFragment).commit();
                    return;
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else super.serverCallEnds(id, url, json, status);
    }

    private ArrayList<MediaData> prepareDummy() {
        ArrayList<MediaData> dataList = new ArrayList<>();
        MediaData mediaData = new MediaData(MEDIATYPE_IMAGE, "https://upload.wikimedia.org/wikipedia/commons/7/7c/Aspect_ratio_16_9_example.jpg");
        MediaData mediaData1 = new MediaData(MEDIATYPE_IMAGE, "http://www.johnstevenssafaris.com/wallpapers/16-9_ratio/Guided%20Safaris%20Africa%2009.jpg");
        MediaData mediaData2 = new MediaData(MEDIATYPE_VIDEO, "https://www.joomlashine.com/images/easyblog_articles/572/joomlashine-new-demo-blog.jpg"
                , "http://techslides.com/demos/sample-videos/small.mp4");
        MediaData mediaData3 = new MediaData(MEDIATYPE_IMAGE, "http://www.iamabiker.com/wp-content/uploads/2012/08/Pulsar-200-ns-HD-12.jpg");
        dataList.add(mediaData);
        dataList.add(mediaData1);
        dataList.add(mediaData2);
        dataList.add(mediaData3);
        return dataList;
    }

    public static class MediaData implements Parcelable {

        public int mediaType;
        public String imgUrl;
        public String mediaUrl;

        public MediaData(int mediaType, String imgUrl) {
            this.mediaType = mediaType;
            this.imgUrl = imgUrl;
        }

        public MediaData(int mediaType, String imgUrl, String mediaUrl) {
            this.mediaType = mediaType;
            this.imgUrl = imgUrl;
            this.mediaUrl = mediaUrl;
        }

        protected MediaData(Parcel in) {
            mediaType = in.readInt();
            imgUrl = in.readString();
            mediaUrl = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mediaType);
            dest.writeString(imgUrl);
            dest.writeString(mediaUrl);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<MediaData> CREATOR = new Parcelable.Creator<MediaData>() {
            @Override
            public MediaData createFromParcel(Parcel in) {
                return new MediaData(in);
            }

            @Override
            public MediaData[] newArray(int size) {
                return new MediaData[size];
            }
        };
    }

    //public abstract List<MediaData> getMediaList();

    public abstract String getQueryUrlForMediaContentDisplay();
}
