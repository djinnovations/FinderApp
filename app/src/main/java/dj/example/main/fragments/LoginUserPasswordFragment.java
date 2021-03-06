package dj.example.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import dj.example.main.R;
import dj.example.main.uiutils.ColoredSnackbar;

/**
 * Created by DJphy on 09-01-2017.
 */

public class LoginUserPasswordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_normal, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        btnLoginAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canContinue()) {
                    String shaUserId = /*RandomUtils.textToSHA256(*/etUserName.getText().toString().trim()/*)*/;
                    String shaPass = /*RandomUtils.textToSHA256(*/etPassword.getText().toString().trim()/*)*/;
                    //((BaseActivity) getActivity()).queryForLogin(shaUserId, shaPass);
                    return;
                }
                ColoredSnackbar.alert(Snackbar.make(btnLoginAcct, "Fill all fields", Snackbar.LENGTH_SHORT)).show();
            }
        });
    }

    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLoginAcct)
    Button btnLoginAcct;

    private boolean canContinue(){
        return !(TextUtils.isEmpty(etPassword.getText().toString()) || TextUtils.isEmpty(etUserName.getText().toString()));
    }
}
