package com.example.stuantlogin.TimeTableFragments;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.stuantlogin.R;
import com.google.android.material.tabs.TabLayout;

public class TimeTable extends AppCompatActivity {

    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String stringSTD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        tabLayout = findViewById(R.id.tlTimeTable);
        frameLayout = findViewById(R.id.frameLAyoutTImmeTAble);
        fragment = new MonFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLAyoutTImmeTAble, fragment);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragment = new MonFragment();
                        break;
                    case 1:
                        fragment = new TueFragment();
                        break;
                    case 2:
                        fragment = new WedFragment();
                        break;
                    case 3:
                        fragment = new ThuFragment();
                        break;
                    case 4:
                        fragment = new FriFragment();
                        break;
                    case 5:
                        fragment = new SatFragment();
                        break;
                    case 6:
                        fragment = new SunFragment();
                        break;
                }
                FragmentManager frMng = getSupportFragmentManager();
                FragmentTransaction transaction = frMng.beginTransaction();
                transaction.replace(R.id.frameLAyoutTImmeTAble, fragment);
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
