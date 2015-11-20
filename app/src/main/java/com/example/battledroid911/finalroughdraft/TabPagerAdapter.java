package com.example.battledroid911.finalroughdraft;

/**
 * Created by battledroid911 on 1/18/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// TabPagerAdapter for the morse code project
public class TabPagerAdapter extends FragmentStatePagerAdapter
{
    //Constructor for the TabPagerAdapter
    public TabPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public Fragment getItem(int tabNumber)
    {
        switch(tabNumber)
        {
            //Fragment for Morse code converter
            case 0:
            {
                return new MorseCodeConverter();
            }

            //Fragment for Reference Tab
            case 1:
            {
                return new ReferenceTable();
            }

            //Fragment for Manual input Tab
            case 2:
            {
                return new ManualMorse();
            }
        }
        return null;
    }

    //get the number of tabs
    @Override
    public int getCount()
    {
        return 3;
    }


}









