package ism.com.worthyth.beep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import ism.com.worthyth.beep.fragments.CompteFragmentAgent;
import ism.com.worthyth.beep.fragments.HistoryFragment;
import ism.com.worthyth.beep.fragments.HomeFragmentAgent;
import ism.com.worthyth.beep.fragments.RetraitFragment;


public class ProActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);
        bottomNavigation = findViewById(R.id.bottom_navigationpro);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragmentAgent.newInstance("",""));
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_containerpro, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_homea:
                            getSupportActionBar().setTitle("MES CHAUFFERS");
                            openFragment(HomeFragmentAgent.newInstance("", ""));
                            return true;
                        case R.id.navigation_hista:
                            getSupportActionBar().setTitle("Operations des chauffeurs");
                            openFragment(HistoryFragment.newInstance("", ""));
                            return true;
                        /*case R.id.navigation_comptea:
                            getSupportActionBar().setTitle("MON COMPTE");
                            openFragment(CompteFragmentAgent.newInstance("", ""));
                            return true;
                        case R.id.navigation_retrait:
                            getSupportActionBar().setTitle("EFFECTUER LE RETRAIT");
                            openFragment(RetraitFragment.newInstance("", ""));
                            return true;*/


                    }
                    return false;
                }

            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        actionMenu = menu;
        //actionMenu.findItem(R.id.edit).setVisible(true);
        actionMenu.findItem(R.id.save).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.save:                //Save
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProActivity.this);
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
                Intent intent = new Intent(AgentActivity.this, Login_Activity.class);
                startActivity(intent);
            }*/


        }
        return true;
    }


}

