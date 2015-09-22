package com.example.lixinyang.androidbanner_demo;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isDraging;
    private int ITEMCOUNT=10000;
    private final int Duration_Scroll=3000;
    private  int[] imgres=new int[]{
            R.mipmap.b1,R.mipmap.b2,R.mipmap.b3,R.mipmap.b4,R.mipmap.b5
    };
    private Handler mHandler=new Handler();
    private ViewPager mViewPager;
    private MyPagerIndicate myPagerIndicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        myPagerIndicate= (MyPagerIndicate) findViewById(R.id.pagerindicate);

        initpagerindicate();
        initviewpager();
        autoScroll();
    }

    private void initpagerindicate() {
         myPagerIndicate= (MyPagerIndicate) findViewById(R.id.pagerindicate);

    }

    private void autoScroll() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isDraging)
                {
                int currentitem=mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(currentitem + 1);
                }
                mHandler.postDelayed(this,Duration_Scroll);
            }
        },Duration_Scroll);
    }

    private void initviewpager() {
        final ViewPager mViewPager= (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fm=getSupportFragmentManager();
        mViewPager.setAdapter(new BannerAdapter(fm));
        mViewPager.setCurrentItem(ITEMCOUNT / 2);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                myPagerIndicate.Update(positionOffset);
                myPagerIndicate.setcurrentindex(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state)
                {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Toast.makeText(getApplicationContext(),"you are draging",Toast.LENGTH_SHORT).show();
                        isDraging=true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:

                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isDraging=false;
                        break;
                    default:
                        break;

                }

            }
        });

    }


   private class BannerAdapter extends FragmentPagerAdapter
   {


       public BannerAdapter(FragmentManager fm) {
           super(fm);
       }

       @Override
       public Fragment getItem(int i) {
           BannerItemFragment fragment=new BannerItemFragment();
           fragment.setImageRes(imgres[i % imgres.length]);
           return fragment;
       }

       @Override
       public int getCount() {
           return ITEMCOUNT;
       }
   }
}
