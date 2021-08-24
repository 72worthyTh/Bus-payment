package ism.com.worthyth.beep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import ism.com.worthyth.beep.fragments.CompteFragmentAgent;
import ism.com.worthyth.beep.fragments.HistoryFragment;
import ism.com.worthyth.beep.fragments.HomeFragment;


public class WelCome extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("",""));
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            getSupportActionBar().setTitle("SCAN");
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_hist:
                            getSupportActionBar().setTitle("HISTORIQUE");
                            openFragment(HistoryFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_compte:
                            getSupportActionBar().setTitle("MON COMPTE");
                            openFragment(CompteFragmentAgent.newInstance("", ""));
                            return true;


                    }
                    return false;
                }

            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        actionMenu = menu;
       // actionMenu.findItem(R.id.edit).setVisible(true);
        actionMenu.findItem(R.id.save).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.save:                //Save
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(WelCome.this);
                builder.setPositiveButton("Qui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();  // User clicked OK button
                    }
                });

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();                                  }
                });
// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Voulez-vous vraiment vous déconnectez?")
                        .setTitle("Déconnexion");

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
                AlertDialog dialog = builder.create();
                dialog.show();




            }
            return true;
           /* case R.id.edit: {
                Intent intent = new Intent(WelCome.this, Login_Activity.class);
                startActivity(intent);
            }*/


        }
        return true;
    }


}
