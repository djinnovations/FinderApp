package dj.example.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DJphy on 24-10-2017.
 */

public class CourseData implements Parcelable{

    private int viewType;
    private HomeCourseData homeCourseData;
    private MyCourseData myCourseData;

    public CourseData(int viewType) {
        this.viewType = viewType;
    }

    public void setHomeCourseData(HomeCourseData homeCourseData) {
        this.homeCourseData = homeCourseData;
    }

    public void setMyCourseData(MyCourseData myCourseData) {
        this.myCourseData = myCourseData;
    }

    public int getViewType() {
        return viewType;
    }

    public HomeCourseData getHomeCourseData() {
        return homeCourseData;
    }

    public MyCourseData getMyCourseData() {
        return myCourseData;
    }

    protected CourseData(Parcel in) {
        viewType = in.readInt();
        homeCourseData = in.readParcelable(HomeCourseData.class.getClassLoader());
        myCourseData = in.readParcelable(MyCourseData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(viewType);
        dest.writeParcelable(homeCourseData, flags);
        dest.writeParcelable(myCourseData, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CourseData> CREATOR = new Parcelable.Creator<CourseData>() {
        @Override
        public CourseData createFromParcel(Parcel in) {
            return new CourseData(in);
        }

        @Override
        public CourseData[] newArray(int size) {
            return new CourseData[size];
        }
    };


    public static class HomeCourseData implements Parcelable{


        public String imgUrl;
        public String title;
        public String duration;
        public String totalSwipeCards;
        public String startDate = "Anytime";
        public String fee = "Free";

        public HomeCourseData(String imgUrl, String title, String duration, String totalSwipeCards, String startDate, String fee) {
            this.imgUrl = imgUrl;
            this.title = title;
            this.duration = duration;
            this.totalSwipeCards = totalSwipeCards;
            this.startDate = startDate;
            this.fee = fee;
        }

        public HomeCourseData(String imgUrl, String title, String duration, String totalSwipeCards) {
            this.imgUrl = imgUrl;
            this.title = title;
            this.duration = duration;
            this.totalSwipeCards = totalSwipeCards;
        }

        protected HomeCourseData(Parcel in) {
            imgUrl = in.readString();
            title = in.readString();
            duration = in.readString();
            totalSwipeCards = in.readString();
            startDate = in.readString();
            fee = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(imgUrl);
            dest.writeString(title);
            dest.writeString(duration);
            dest.writeString(totalSwipeCards);
            dest.writeString(startDate);
            dest.writeString(fee);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<HomeCourseData> CREATOR = new Parcelable.Creator<HomeCourseData>() {
            @Override
            public HomeCourseData createFromParcel(Parcel in) {
                return new HomeCourseData(in);
            }

            @Override
            public HomeCourseData[] newArray(int size) {
                return new HomeCourseData[size];
            }
        };
    }


    public static class MyCourseData implements Parcelable {

        public String imgUrl;
        public String title;
        public float percentCompletion;
        public float totalSwipeCards;

        public void setTotalSwipeCards(int totalSwipeCards) {
            this.totalSwipeCards = totalSwipeCards;
        }

        public MyCourseData(String imgUrl, String title, int percentCompletion) {
            this.imgUrl = imgUrl;
            this.title = title;
            this.percentCompletion = percentCompletion;
        }

        protected MyCourseData(Parcel in) {
            imgUrl = in.readString();
            title = in.readString();
            percentCompletion = in.readFloat();
            totalSwipeCards = in.readFloat();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(imgUrl);
            dest.writeString(title);
            dest.writeFloat(percentCompletion);
            dest.writeFloat(totalSwipeCards);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<MyCourseData> CREATOR = new Parcelable.Creator<MyCourseData>() {
            @Override
            public MyCourseData createFromParcel(Parcel in) {
                return new MyCourseData(in);
            }

            @Override
            public MyCourseData[] newArray(int size) {
                return new MyCourseData[size];
            }
        };
    }

}
