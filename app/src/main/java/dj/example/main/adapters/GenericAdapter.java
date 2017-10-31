package dj.example.main.adapters;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dj.example.main.R;
import dj.example.main.model.CourseData;
import dj.example.main.uiutils.TypefaceHelper;
import dj.example.main.utils.IDUtils;
import dj.example.main.utils.RandomUtils;

/**
 * Created by DJphy on 23-10-2017.
 */

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements GenericAdapterInterface{

    public static final int COURSES_HOME = IDUtils.generateViewId();
    public static final int COURSES_MINE = IDUtils.generateViewId();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getRootLayout(viewType), parent, false);
        return getViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder instanceof BaseItemHolder){
                ((BaseItemHolder) holder).onItemViewUpdate(dataList.get(position), holder, position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private List<CourseData> dataList = new ArrayList<>();

    @Override
    public void changeData(final List dataList) throws Exception {
        if (dataList == null)
            return;
        if (dataList.size() <= 0)
            return;
        if (!(dataList.get(0) instanceof CourseData))
            throw new IllegalArgumentException("Required data type \"CourseData\"");
        this.dataList.clear();
        this.dataList.addAll(dataList);
        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 100);
    }

    @Override
    public int getRootLayout(int viewType) {
        if (viewType == COURSES_HOME)
            return R.layout.viewholder_course_primary;
        else if (viewType == COURSES_MINE)
            return R.layout.viewholder_course_my;
        else return 0;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view, int viewType) {
        if (viewType == COURSES_HOME)
            return new PrimaryCourseViewHolder(view);
        else if (viewType == COURSES_MINE)
            return new MyCourseViewHolder(view);
        else return null;
    }

    @Override
    public void setOnClickListener(RecyclerView.ViewHolder holder) {

    }


    class PrimaryCourseViewHolder extends BaseItemHolder implements View.OnClickListener{

        @BindView(R.id.tvCourseDesc)
        TextView tvCourseDesc;
        @BindView(R.id.tvTotalDuration)
        TextView tvTotalDuration;
        @BindView(R.id.tvTotalTopics)
        TextView tvTotalTopics;
        @BindView(R.id.tvReminder)
        TextView tvReminder;
        @BindView(R.id.tvFee)
        TextView tvFee;

        @BindView(R.id.ivCourse)
        ImageView ivCourse;
        @BindView(R.id.ivDuration)
        ImageView ivDuration;
        @BindView(R.id.ivTopics)
        ImageView ivTopics;
        @BindView(R.id.ivReminder)
        ImageView ivReminder;
        @BindView(R.id.ivFee)
        ImageView ivFee;

        public PrimaryCourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setOnClickListener(this);
            setTextViewStyle();
            setIconics();
        }

        private void setIconics() {
            RandomUtils.getInstance().assignMikePenzIcon(GoogleMaterial.Icon.gmd_filter_hdr, ivCourse, R.color.colorAccent);
            RandomUtils.getInstance().assignMikePenzIcon(GoogleMaterial.Icon.gmd_timer, ivDuration, R.color.colorAccent);
            RandomUtils.getInstance().assignMikePenzIcon(GoogleMaterial.Icon.gmd_assignment, ivTopics, R.color.colorAccent);
            RandomUtils.getInstance().assignMikePenzIcon(GoogleMaterial.Icon.gmd_date_range, ivReminder, R.color.colorAccent);
            RandomUtils.getInstance().assignMikePenzIcon(CommunityMaterial.Icon.cmd_currency_inr, ivFee, R.color.colorAccent);
        }

        private void setTextViewStyle() {
            TypefaceHelper.setFont(TypefaceHelper.FontTypes.SNICKLES, tvCourseDesc);
            TypefaceHelper.setFont(TypefaceHelper.FontTypes.QUICKSAND_REGULAR, tvTotalDuration
                    , tvTotalTopics, tvReminder, tvFee);
        }

        @Override
        public void onItemViewUpdate(Object dataObject, RecyclerView.ViewHolder holder, int position) {
            if (dataObject instanceof CourseData){
                CourseData.HomeCourseData data = ((CourseData) dataObject).getHomeCourseData();
                tvTotalDuration.setText(data.duration);
                tvTotalTopics.setText(data.totalSwipeCards);
                tvFee.setText(data.fee);
                tvReminder.setText(data.startDate);
                //tvCourseDesc.setText(data.title);
            }
        }

        @Override
        public void onClick(View v) {

        }


    }


    class MyCourseViewHolder extends BaseItemHolder implements View.OnClickListener{

        /*@BindView(R.id.segmented_progressbar)
        SegmentedProgressBar segmentedProgressBar;*/
        @BindView(R.id.tvCourseDesc)
        TextView tvCourseDesc;
        @BindView(R.id.tvBegin)
        TextView tvBegin;
        @BindView(R.id.tvDiscussion)
        TextView tvDiscussion;
        @BindView(R.id.tvProgress)
        TextView tvProgress;
        @BindView(R.id.viewProgress)
        View viewProgress;
        @BindView(R.id.ivCourse)
        ImageView ivCourse;

        public MyCourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setOnClickListener(this);
            setTextViewStyle();
            setIconics();
        }

        private void setIconics() {
            RandomUtils.getInstance().assignMikePenzIcon(GoogleMaterial.Icon.gmd_filter_hdr, ivCourse, R.color.colorAccent);
        }

        private void setTextViewStyle() {
            TypefaceHelper.setFont(TypefaceHelper.FontTypes.SNICKLES, tvCourseDesc);
            TypefaceHelper.setFont(TypefaceHelper.FontTypes.QUICKSAND_REGULAR, tvBegin
                    , tvDiscussion, tvProgress);
        }

        /*private void setUpWaveProgress() {
            segmentedProgressBar.setContainerColor(Color.GRAY);
            segmentedProgressBar.setFillColor(ResourceReader.getInstance()
                    .getColorFromResource(R.color.colorCourseProgress));
            segmentedProgressBar.incrementCompletedSegments();

            *//*waveProgressbar.setMaxProgress(100);
            waveProgressbar.setText("#00ffffff", 0);//"#FFFF00", 41
            waveProgressbar.setWaveColor(ResourceReader.getInstance().getStringFromResource(R.color.colorCourseProgress)); //"#5b9ef4"
            waveProgressbar.setmWaveSpeed(60);//The larger the value, the slower the vibration*//*
        }*/

        @Override
        public void onItemViewUpdate(Object dataObject, RecyclerView.ViewHolder holder, int position) {
            if (dataObject instanceof CourseData){
                CourseData.MyCourseData data = ((CourseData) dataObject).getMyCourseData();
                //waveProgressbar.setCurrent(data.percentCompletion, ""); // 77, "788M/1024M"
                //segmentedProgressBar.setSegmentCount((int) data.totalSwipeCards);
                //segmentedProgressBar.setCompletedSegments((int) data.percentCompletion);
                //int percent = (int) ((data.percentCompletion/data.totalSwipeCards) * 100);
                tvProgress.setText(String.valueOf(data.percentCompletion/*percent*/).concat("%"));
                setPercentageOnView(viewProgress, (int) data.percentCompletion);
            }
        }

        /**
         * @param value measured in values of 100, max = 100
         */
        private void setPercentageOnView(View view, int value){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            if (value > 100)
                value = 100;
            params.weight = value;
            view.setLayoutParams(params);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public static abstract class BaseItemHolder extends RecyclerView.ViewHolder{

        public BaseItemHolder(View itemView) {
            super(itemView);
        }

        public abstract void onItemViewUpdate(Object dataObject, RecyclerView.ViewHolder holder, int position);
    }

}
