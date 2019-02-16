package hch.hooney.docexampleproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import hch.hooney.docexampleproject.Fragments.Main.HomeFragment;
import hch.hooney.docexampleproject.Fragments.Main.MapFragment;
import hch.hooney.docexampleproject.Fragments.Main.MyPageFragment;
import hch.hooney.docexampleproject.MyApp.MyApplication;

public class MainActivity extends AppCompatActivity {
    enum DefineTabs{
        Home, Map, MyPage
    }

    private final String TAG = MainActivity.class.getSimpleName();
    private Fragment home, map, myPage;
    private DefineTabs nowTab;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(nowTab != DefineTabs.Home){
                        switchFragment(home);
                        nowTab = DefineTabs.Home;
                    }
                    return true;
                case R.id.navigation_map:
                    if(nowTab != DefineTabs.Map){
                        switchFragment(map);
                        nowTab = DefineTabs.Map;
                    }
                    return true;
                case R.id.navigation_mypage:
                    if(nowTab != DefineTabs.MyPage){
                        switchFragment(myPage);
                        nowTab = DefineTabs.MyPage;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For 해시!
        Log.d(TAG, MyApplication.getKeyHash(this));

        //For Firebase Messeging Token
        Log.d(TAG, "F token : " + FirebaseInstanceId.getInstance().getInstanceId());

        init();
    }



    private void init(){
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        home = new HomeFragment();
        map = new MapFragment();
        myPage = new MyPageFragment();

        //첫 Main 페이지
        switchFragment(home);
        nowTab = DefineTabs.Home;

        setEvent();
    }

    private void setEvent(){
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void switchFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
    }

}
