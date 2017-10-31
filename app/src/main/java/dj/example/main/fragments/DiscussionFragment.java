package dj.example.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dj.example.main.MyApplication;
import dj.example.main.R;
import dj.example.main.uiutils.TypefaceHelper;

/**
 * Created by User on 28-10-2017.
 */

public class DiscussionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discussion, container, false);
    }

    @BindView(R.id.tv121)
    TextView tv121;
    @BindView(R.id.tvOpen)
    TextView tvOpen;
    @BindView(R.id.tvGroup)
    TextView tvGroup;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setTextViewStyle();
    }

    private void setTextViewStyle() {
        TypefaceHelper.setFont(TypefaceHelper.FontTypes.QUICKSAND_REGULAR, tv121, tvGroup, tvOpen);
    }

    @OnClick(R.id.tv121)
    void discussionClicked(View view){
        int id = view.getId();
        actions(id);
    }


    private void actions(int id){
        if (id == R.id.tv121){

        }else if (id == R.id.tvGroup){

        }else if (id == R.id.tvOpen){

        }
        Toast.makeText(MyApplication.getInstance(), "Feature Coming Soon", Toast.LENGTH_SHORT).show();
        //// TODO: 28-10-2017 implement discussions
    }
}
