package lgj.example.com.biyesheji.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import lgj.example.com.biyesheji.R;
import lgj.example.com.biyesheji.ui.activity.LogininActivity;

/**
 * Created by yhdj on 2017/9/27.
 */

public class FileFragment extends BaseFragment implements View.OnClickListener {
    private TextView login_tv_userName;
    private Button login_btn_logout;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_file;
    }

    @Override
    public void initView(View view) {
        login_tv_userName= (TextView) view.findViewById(R.id.login_tv_userName);
        login_btn_logout= (Button) view.findViewById(R.id.login_btn_logout);
        login_btn_logout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        BmobUser.logOut();   //清除缓存用户对象
        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
        showToast("退出登录成功");
        Intent i = new Intent(getActivity(),LogininActivity.class);
        startActivity(i);
        getActivity().finish();
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
