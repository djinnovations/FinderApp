package dj.example.main.fragments;

import android.support.v4.util.Pair;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dj.example.main.R;

/**
 * Created by User on 28-10-2017.
 */

public class TextParagraphPagerAdapter extends PagerAdapter {

    List<Pair<String, String>> dataList = new ArrayList<>();

    public TextParagraphPagerAdapter(List<Pair<String, String>> dataList) {
        try {
            this.dataList = new ArrayList<>(dataList);
        } catch (Exception e) {
            e.printStackTrace();
            this.dataList = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        try {
            return dataList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Pair<String, String> data = dataList.get(position);
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_pager_paragraph_text, container, true);
        TextView tvParagraph = (TextView) view.findViewById(R.id.tvParagraph);
        tvParagraph.setText(data.first);
        TextView tvViewMore = (TextView) view.findViewById(R.id.tvViewMore);
        if (!TextUtils.isEmpty(data.second)) {
            tvViewMore.setVisibility(View.VISIBLE);
            tvViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchWeb(data.second);
                }
            });
        }
        return view;
    }

    private void launchWeb(String contentUrl) {

    }
}
