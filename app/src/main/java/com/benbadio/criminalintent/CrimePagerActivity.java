package com.benbadio.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benba on 7/4/2016.
 */
public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks{
    public static final String EXTRA_CRIME_ID = "com.benbadio.criminalintent.crime_id";

    @BindView(R.id.activity_crime_pager_view_pager) ViewPager mViewPager;

    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent i = new Intent(packageContext, CrimePagerActivity.class);
        i.putExtra(EXTRA_CRIME_ID, crimeId);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        ButterKnife.bind(this);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for(int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
