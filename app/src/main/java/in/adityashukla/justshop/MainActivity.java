package in.adityashukla.justshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));

        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.navBar);


     getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_page: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
                        break;

                    case R.id.cat_page: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new CategoryFragment()).commit();
                        break;

                    case R.id.order_page: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new OrderFragment()).commit();
                        break;

                    case R.id.profile_page: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ProfileFragment()).commit();
                        break;

                }
                return true;
            }
        });


    }
}