package dj.example.main.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dj.example.main.R;
import dj.example.main.fragments.DiscussionFragment;
import dj.example.main.uiutils.ResourceReader;
import dj.example.main.utils.NetworkResultValidator;
import dj.example.main.utils.URLHelper;

/**
 * Created by User on 28-10-2017.
 */

public class OneCourseDetailsActivity extends MediaDisplayBaseActivity {

    @Override
    public String getQueryUrlForMediaContentDisplay() {
        return URLHelper.getInstance().getCourseMediaContentUrl("contentIDhere");
    }


    @Override
    public Activity getSelf() {
        return this;
    }


    @BindView(R.id.vpParagraph)
    ViewPager vpParagraph;
    @BindView(R.id.flDiscussionContainer)
    FrameLayout flDiscussionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course_one);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(flDiscussionContainer.getId(), new DiscussionFragment()).commit();
    }


    @Override
    public void serverCallEnds(int id, String url, Object json, AjaxStatus status) {
        if (id == MEDIA_CONTENT_CALL){
            super.serverCallEnds(id, url, json, status);
            try {
                boolean success = NetworkResultValidator.getInstance().isResultOK((String) json, status, getViewForLayoutAccess());
                success = true; // remove it
                if (success){
                    vpParagraph.setAdapter(new ParagraphPagerAdapter(prepareTempPara()));
                }

            }catch (Exception ex){

            }
        }
    }

    private List<String> prepareTempPara() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            StringBuilder sb = new StringBuilder();
            sb.append("item - ")
                    .append(String.valueOf(i))
                    .append("\n")
                    .append("Dharma is a concept of central importance in Indian philosophy and religion. \n" +
                            "It has multiple meanings in Hinduism, Buddhism, and Jainism.\n" +
                            "It is difficult to provide a single concise definition for dharma,\n" +
                            "as the word has a long and varied history and straddles a complex set of meanings and interpretations. \n" +
                            "There is no equivalent single word translation for dharma in western languages.");

            list.add(sb.toString());
        }
        return list;
    }


    class ParagraphPagerAdapter extends PagerAdapter {

        /*private final int[] sDrawables = {R.drawable.image_1, R.drawable.image_1, R.drawable.image_1,
                R.drawable.image_1, R.drawable.image_1, R.drawable.image_1};*/

        @Override
        public int getCount() {
            if (dataList != null) {
                return dataList.size();
            } else
                return 0;
        }

        private List<String> dataList = new ArrayList<>();

        public ParagraphPagerAdapter(List<String> dataList) {
            try {
                this.dataList = new ArrayList<>(dataList);
            } catch (Exception e) {
                e.printStackTrace();
                this.dataList = new ArrayList<>();
            }
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final String data = dataList.get(position);
            TextView textView = new TextView(OneCourseDetailsActivity.this);
            textView.setTextColor(ResourceReader.getInstance().getColorFromResource(R.color.colorMaterialBlack));
            textView.setTextSize(16);
            textView.setText(data);
            textView.setMovementMethod(new ScrollingMovementMethod());
            container.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
