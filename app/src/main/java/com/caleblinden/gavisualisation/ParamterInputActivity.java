package com.caleblinden.gavisualisation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.caleblinden.gavisualisation.GeneticAlgorithm.GAParameters;
import com.caleblinden.gavisualisation.ViewUtils.ZoomOutPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParamterInputActivity extends FragmentActivity implements ParameterIntroductionFragment.OnFragmentInteractionListener {

    @BindView(R.id.onboarding_viewpager) ViewPager viewPager;
    @BindView(R.id.tabDots) TabLayout tabLayout;

    private int populationSize = 100;
    private double mutationRate = 0.05;
    private double uniformRate = 3;
    private int tournamentSize = 10;
    private boolean elitism = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();

        setupOnboarding();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupOnboarding() {
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ParameterIntroductionFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

    @Override
    public void saveInputValue(int type, int value, double doubleValue) {
        GAParameters param = GAParameters.fromValue(type);
        if (param == null) {
            return;
        }
        switch (param) {
            case POPULATION_SIZE:
                populationSize = value;
                break;
            case MUTATION_RATE:
                mutationRate = doubleValue;
                break;
            case UNIFORM_RATE:
                uniformRate = doubleValue;
                break;
            case TOURNAMENT_SIZE:
                tournamentSize = value;
                break;
            case ELITISM:
                elitism = false;
                break;
        }
    }

    @Override
    public void onFragmentInteraction() {
        startActivity(MainActivity.getStartIntent(this, mutationRate, uniformRate, tournamentSize, null, populationSize));
    }
}
