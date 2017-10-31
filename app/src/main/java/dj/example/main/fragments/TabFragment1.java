package dj.example.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dj.example.main.R;
import dj.example.main.adapters.GenericAdapter;
import dj.example.main.model.CourseData;
import dj.example.main.uiutils.DisplayProperties;
import dj.example.main.utils.RandomUtils;

/**
 * Created by DJphy on 10-07-2017.
 */

public class TabFragment1 extends SingleMenuFragment{

    private GenericAdapter genericAdapter;

    @Override
    public boolean isAddSnapper() {
        return false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        genericAdapter = new GenericAdapter();
        super.onViewCreated(view, savedInstanceState);
        setDummyData();
    }

    private void setDummyData() {
        List<CourseData> dataList = new ArrayList<>();
        for (int i= 0; i < 8; i++) {
            CourseData.MyCourseData myCourseData = new CourseData.MyCourseData("", "yo yo", (i*10));
            /*CourseData.HomeCourseData homeCourseData = new CourseData.HomeCourseData("", "yo"
                    , String.valueOf((i*2)), String.valueOf((i*10)));*/
            myCourseData.setTotalSwipeCards(25);
            CourseData data = new CourseData(GenericAdapter.COURSES_MINE);
            data.setMyCourseData(myCourseData);
            //data.setHomeCourseData(homeCourseData);
            dataList.add(data);
        }
        try {
            genericAdapter.changeData(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return genericAdapter;
    }

    @Override
    public void changeData(List dataList) {

    }

    @Override
    protected void setBodySettings(LinearLayout bodyLayout) {
        bodyLayout.setPadding(RandomUtils.convertDpToPixel(8), 0
                , RandomUtils.convertDpToPixel(8), 0);
    }
}
