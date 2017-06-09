package de.fau.amos.virtualledger.android.views.menu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.api.auth.AuthenticationProvider;
import de.fau.amos.virtualledger.android.authentication.login.LoginActivity;
import de.fau.amos.virtualledger.android.views.bankingOverview.addBankAccess.AddBankAccessActivity;
import de.fau.amos.virtualledger.android.views.bankingOverview.expandableList.Fragment.ExpandableBankFragment;
import de.fau.amos.virtualledger.android.dagger.App;
import de.fau.amos.virtualledger.android.views.menu.adapter.MenuAdapter;
import de.fau.amos.virtualledger.android.views.menu.model.ItemSlidingMenu;
import de.fau.amos.virtualledger.android.views.transactionOverview.TransactionOverviewFragment;

public class MainMenu extends AppCompatActivity {

    private static final String TAG = MainMenu.class.getSimpleName();

    @Inject
    AuthenticationProvider authenticationProvider;

    private List<ItemSlidingMenu> slidingItems;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_sliding_tab);
        Bundle bundle = getIntent().getExtras();
        int startingFragment = 1;
        if(bundle != null) {
            startingFragment = bundle.getInt("startingFragment");
        }
        //init
        init();

        //items for slide list
        configureItemsForMenu();

        //set Menu-Icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //select
        listView.setItemChecked(0, true);

        //Close sliding menu
        drawerLayout.closeDrawer(listView);

        replaceFragment(startingFragment);


        //click on items
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> root, View view, int pos, long id) {
                        //title
                        setTitle(slidingItems.get(pos).getImageTitle());
                        //items selected
                        listView.setItemChecked(pos, true);

                        replaceFragment(pos);

                        drawerLayout.closeDrawer(listView);
                    }
                });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.main_menu_drawer_opened, R.string.main_menu_drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        // add the Toggle as Listener
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

    }

    private void init() {
        ((App) getApplication()).getNetComponent().inject(this);
        listView = (ListView) findViewById(R.id.sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        slidingItems = new ArrayList<>();
    }

    private void configureItemsForMenu() {
        slidingItems.add(new ItemSlidingMenu(R.drawable.icon_logout, "Logout"));
        slidingItems.add(new ItemSlidingMenu(R.drawable.bank_accesses, "Bank Access"));
        slidingItems.add(new ItemSlidingMenu(R.drawable.list, "Transaction Overview"));
        listView.setAdapter(new MenuAdapter(this, slidingItems));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.m_add_bank_access:
                final Intent addBankAccessIntent = new Intent(this, AddBankAccessActivity.class);
                startActivity(addBankAccessIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    /**
     * replaces the current fragment with chosen one from the user
     */
    private void replaceFragment(int pos) {
        Fragment fragment;
        switch (pos) {
            case 0:
                executeLogout();
                break;

            case 1:
                fragment = new ExpandableBankFragment();
                openFragment(fragment);
                break;

            case 2:
                fragment = new TransactionOverviewFragment();
                openFragment(fragment);
                break;

            //new Fragments can be added her
            default:
                Log.e(TAG, "Menu item pos: {" + pos + "} not found");
                fragment = new TransactionOverviewFragment();
                openFragment(fragment);
                break;
        }

    }

    private void openFragment(Fragment fragment) {
        if (null != fragment) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void executeLogout() {
        authenticationProvider.logout();
        authenticationProvider.deleteSavedLoginData(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}