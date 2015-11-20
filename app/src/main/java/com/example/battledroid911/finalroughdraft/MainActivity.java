package com.example.battledroid911.finalroughdraft;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity
        .TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;


public class MainActivity extends FragmentActivity {




    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "EIddDD3bphvuq7m9uNM8SXnuq";
    private static final String TWITTER_SECRET = "XoWhASETLEZyNQrTe8PAH0bcPoyqsMJsLGeSqvrE4Y4isfkffr";

    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar ActionBar;
    public Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        //Fabric.with(this, new Twitter(authConfig));
        Fabric.with(this, new TweetComposer(), new Twitter(authConfig), new Crashlytics());
        //  //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.tab_pager);
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        Tab = (ViewPager) findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        ActionBar = getActionBar();
                        assert ActionBar != null;
                        ActionBar.setSelectedNavigationItem(position);
                    }
                }
        );

        Tab.setAdapter(TabAdapter);
        ActionBar = getActionBar();

        assert ActionBar != null;
        ActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Tab.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }
        };
        ActionBar.addTab(ActionBar.newTab().setText("Converter").setTabListener(tabListener));
        ActionBar.addTab(ActionBar.newTab().setText("Reference").setTabListener(tabListener));
        ActionBar.addTab(ActionBar.newTab().setText("Manual").setTabListener(tabListener));
        //b = (Button)TabAdapter.getItem(1).getView().findViewById(R.id.shareButton);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getFragmentManager().findFragmentById(R.id.main);

        if(fragment !=null)
        {
            fragment.onActivityResult(requestCode, resultCode, data);
            Button b = (Button)fragment.getView().findViewById(R.id.shareButton);

            b.setVisibility(View.VISIBLE);
        }
        Button b = (Button) Tab.findViewById(R.id.shareButton);
        TwitterLoginButton tlb = (TwitterLoginButton)Tab.findViewById(R.id.login_button);
tlb.onActivityResult(requestCode, resultCode, data);
        tlb.setVisibility(View.GONE);
        b.setVisibility(View.VISIBLE);

       // Activity test = fragment.getActivity();
        //Button b = (Button)ActionBar..getView().findViewById(R.id.shareButton);

        //b.setVisibility(View.VISIBLE);
        //test.findViewById(R.id.shareButton);
        //sButton.setVisibility(View.VISIBLE);

    }
}
