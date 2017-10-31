package dj.example.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dj.example.main.R;
import dj.example.main.activities.BaseActivity;
import dj.example.main.activities.MediaDisplayBaseActivity;
import dj.example.main.MyApplication;
import dj.example.main.customviews.HackyViewPager;
import dj.example.main.uiutils.ResourceReader;
import dj.example.main.utils.IntentKeys;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by DJphy on 27-10-2017.
 */

public class MediaContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_media, container, false);
    }

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private MediaPagerAdapter mediaPagerAdapter;

    public MediaPagerAdapter getMediaPagerAdapter() {
        return mediaPagerAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        List<MediaDisplayBaseActivity.MediaData> list = null;
        try {
            list = getArguments().getParcelableArrayList(IntentKeys.MEDIA_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            ((BaseActivity) getActivity()).setWarningMsg("Internal Error");
            return;
        }
        view_pager.setAdapter(mediaPagerAdapter = new MediaPagerAdapter(list));
    }


    class MediaPagerAdapter extends PagerAdapter {

        /*private final int[] sDrawables = {R.drawable.image_1, R.drawable.image_1, R.drawable.image_1,
                R.drawable.image_1, R.drawable.image_1, R.drawable.image_1};*/

        @Override
        public int getCount() {
            return dataList.size();
        }

        private List<MediaDisplayBaseActivity.MediaData> dataList = new ArrayList<>();

        public MediaPagerAdapter(List<MediaDisplayBaseActivity.MediaData> dataList) {
            try {
                this.dataList = new ArrayList<>(dataList);
            } catch (Exception e) {
                e.printStackTrace();
                this.dataList = new ArrayList<>();
            }
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final MediaDisplayBaseActivity.MediaData mediaData = dataList.get(position);
            View returnView;
            if (mediaData.mediaType == ((MediaDisplayBaseActivity) getActivity()).MEDIATYPE_IMAGE) {
                PhotoView photoView = new PhotoView(container.getContext());
                // photoView.setImageResource(sDrawables[position]);
                setImgUrl(photoView, mediaData.imgUrl);
                // Now just add PhotoView to ViewPager and return it
                container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                //((HackyViewPager) view_pager).setLocked(true);
                returnView = photoView;
            } else if (mediaData.mediaType == ((MediaDisplayBaseActivity) getActivity()).MEDIATYPE_VIDEO) {
                RelativeLayout relativeLayout = new RelativeLayout(getActivity());

                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                ViewGroup.LayoutParams layoutParams1 = new ViewGroup
                        .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(layoutParams1);
                setImgUrl(imageView, mediaData.imgUrl);

                ImageView imageViewDummy = new ImageView(getActivity());
                imageViewDummy.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                        .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                imageViewDummy.setLayoutParams(layoutParams);
                imageViewDummy.setImageDrawable(ResourceReader.getInstance().getDrawableFromResId(R.drawable.icon_video_play));

                imageViewDummy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        proceedToPlay(mediaData.mediaUrl);
                    }
                });

                relativeLayout.addView(imageView);
                relativeLayout.addView(imageViewDummy);

                container.addView(relativeLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                //((HackyViewPager) view_pager).setLocked(true);
                returnView = relativeLayout;
            } else returnView = (View) super.instantiateItem(container, position);
            return returnView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public void setImgUrl(ImageView imgView, String url) {
            Log.d(TAG, "setImgUrl() - imgurl: " + url);
            Picasso.with(getActivity())
                    .load(url)
                    .into(imgView);

        }
    }

    private void proceedToPlay(String mediaUrl) {
        //// TODO: 28-10-2017  create player
        Toast.makeText(MyApplication.getInstance(), "Feature Coming Soon", Toast.LENGTH_SHORT).show();
    }

    private final String TAG = "MediaContentFragment";
}
