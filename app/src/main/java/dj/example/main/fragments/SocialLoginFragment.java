package dj.example.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dj.example.main.R;
import dj.example.main.uiutils.UiRandomUtils;

/**
 * Created by DJphy on 25-01-2017.
 */

public class SocialLoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_social, container, false);
    }

    @BindView(R.id.btnFbLogin)
    Button btnFbLogin;
    @BindView(R.id.btnGoogleLogin)
    SignInButton btnGoogleLogin;
    @BindView(R.id.tvAcctLogin)
    TextView tvAcctLogin;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        UiRandomUtils.getInstance().setGooglePlusButtonText("Sign in with Google", btnGoogleLogin);
    }


    @OnClick(R.id.btnGoogleLogin)
    void performGlLogin(){
        /*if (getActivity() instanceof AwignLoginActivity){
            ((AwignLoginActivity) getActivity()).getmSocialLoginInstance().onGoogleLogin(getActivity());
        }*/
    }

    @OnClick(R.id.tvAcctLogin)
    void launchNormalLoginScreen(){
        //RandomUtils.getInstance().launchNormalSignIn(getActivity());
    }
}
