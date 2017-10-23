package lgj.example.com.biyesheji.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import lgj.example.com.biyesheji.R;

/**
 * Created by Administrator on 2017/10/23.
 */

public class ShowAnnounceActivity extends BaseActivity {
    private TextView tv_show_title,tv_show_content;
    @Override
    protected void initData() {
        tv_show_title= (TextView) findViewById(R.id.tv_show_title);
        tv_show_content= (TextView) findViewById(R.id.tv_show_content);
        Intent intent = getIntent();
        String title =  intent.getStringExtra("title");
        String content =  intent.getStringExtra("content");
        tv_show_title.setText(title);
        tv_show_content.setText(content);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_show_announce;
    }
}
