package lgj.example.com.biyesheji.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import lgj.example.com.biyesheji.R;
import lgj.example.com.biyesheji.ui.activity.ShowAnnounceActivity;
import lgj.example.com.biyesheji.ui.activity.WriteAnnounceActivity;
import lgj.example.com.biyesheji.ui.adapter.AnnounceListViewAdapter;
import lgj.example.com.biyesheji.ui.bean.Announcement;
import lgj.example.com.biyesheji.ui.bean.MyUser;

/**
 * Created by yhdj on 2017/9/27.
 */

public class MainFragment extends BaseFragment{

    private ImageButton imgBtn_write;
    //判断是否为管理员
    private Boolean isMgn;

    private ListView mListView;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        imgBtn_write= (ImageButton) view.findViewById(R.id.imgBtn_write);
        //初始化listview
        mListView = (ListView)view.findViewById(R.id.listview_announcement);


    }


    @Override
    public void initListener() {
        //判断是否为管理员
        isMgn = (Boolean) MyUser.getObjectByKey("isMgn");
        imgBtn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMgn==true){
                    Intent i=new Intent(getActivity(), WriteAnnounceActivity.class);
                    startActivity(i);
                }else{
                    showToast("只有管理员才可发布公告");
                }
            }
        });
        //给listview设置点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获得点击时当前位置的title，和content
                String title = (String) ((TextView)view.findViewById(R.id.title_announce)).getText();
                String content=(String) ((TextView)view.findViewById(R.id.content_announce)).getText();
                //将title和content传递到展示的页面
                Intent intent = new Intent(getActivity().getApplicationContext(), ShowAnnounceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("content", content);
                intent.putExtras(bundle);
        //这里一定要获取到所在Activity再startActivity()；
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
       //初始化公告了listciew的数据
        final List<Announcement> mList=new ArrayList<Announcement>();

        BmobQuery<Announcement> bmobQuery = new BmobQuery<Announcement>();
        bmobQuery.addQueryKeys("title,content,createdAt");
        bmobQuery.findObjects(new FindListener<Announcement>() {
            @Override
            public void done(List<Announcement> list, BmobException e) {
                if(e==null){
                    for (int i = list.size()-1; i >= 0; i--) {
                        Announcement announcement=new Announcement();
                        announcement.setTitle(list.get(i).getTitle());
                        announcement.setContent(list.get(i).getContent());
                        announcement.setTime("发布时间:"+list.get(i).getCreatedAt());
                        mList.add(announcement);
                    }
                    if(mList.size()!=0){
                        mListView.setAdapter(new AnnounceListViewAdapter(getActivity(), mList));
                    }
                }
            }
        });
    }


    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
