package com.whx.vprecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout group;//圆点指示器
    private ImageView[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 6; //每页显示的最大的数量
    private List<ProdctBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    //private int currentPage;//当前页

    private String[] proName = {"名称0", "名称1", "名称2", "名称3", "名称4", "名称5",
            "名称6", "名称7", "名称8", "名称9", "名称10", "名称11", "名称12", "名称13",
            "名称14", "名称15", "名称16", "名称17", "名称18", "名称19"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //添加业务逻辑
        initData();
    }

    private void initView() {
        // TODO Auto-generated method stub
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        group = (LinearLayout) findViewById(R.id.points);
        listDatas = new ArrayList<ProdctBean>();
        for (int i = 0; i < proName.length; i++) {
            listDatas.add(new ProdctBean(proName[i], R.drawable.add_phone));
        }
    }

    private void initData() {
        // TODO Auto-generated method stub
        //总的页数向上取整
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(this, R.layout.item_gridview, null);
            gridView.setAdapter(new MyGridViewAdpter(this, listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof ProdctBean) {
                        System.out.println(obj);
                        Toast.makeText(MainActivity.this, ((ProdctBean) obj).getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));


        //生成相应数量的导航小圆点
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35, 12);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(12, 12);

        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(this);

            if (i == 0) {
                params.setMargins(15, 0, 0, 0);
                ivPoints[i].setLayoutParams(params);
                ivPoints[i].setImageResource(R.drawable.slide);
            } else {
                params1.setMargins(15, 0, 0, 0);
                ivPoints[i].setLayoutParams(params1);
                ivPoints[i].setImageResource(R.drawable.slide_no);
            }
//            ivPoints[i].setPadding(8, 8, 8, 8);
            group.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35, 12);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(12, 12);
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        params.setMargins(15, 0, 0, 0);
                        ivPoints[i].setLayoutParams(params);
                        ivPoints[i].setImageResource(R.drawable.slide);
                    } else {
                        params1.setMargins(15, 0, 0, 0);
                        ivPoints[i].setLayoutParams(params1);
                        ivPoints[i].setImageResource(R.drawable.slide_no);
                    }
                }
            }
        });
    }
}
