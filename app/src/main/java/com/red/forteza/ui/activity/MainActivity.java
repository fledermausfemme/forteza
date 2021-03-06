package com.red.forteza.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.red.forteza.R;
import com.red.forteza.data.api.FirebaseApi;
import com.red.forteza.ui.fragment.BaseFragment;
import com.red.forteza.ui.fragment.BasicsTabsFragment;
import com.red.forteza.ui.fragment.EmptyFragment;
import com.red.forteza.ui.fragment.GlossaryFragment;
import com.red.forteza.ui.fragment.GuardsFragment;
import com.red.forteza.ui.fragment.HomeFragment;
import com.red.forteza.ui.fragment.OffenseFragment;
import com.red.forteza.ui.fragment.OriginsFragment;
import com.red.forteza.ui.fragment.TandDTabsFragment;
import com.red.forteza.ui.view.FToolbar;
import com.red.forteza.util.Prefs;
import com.red.forteza.util.Res;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    FToolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.drawer)
    NavigationView mDrawer;

//    @BindView(R.id.firebaseImg)
//    ImageView img;

    private StorageReference mStorageRef;
    FirebaseApi firebase = new FirebaseApi();
    int menuItemID;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mToolbar.setNavigation(R.drawable.ic_menu, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(mDrawer)) {
                    mDrawerLayout.closeDrawer(mDrawer);
                } else {
                    mDrawerLayout.openDrawer(mDrawer);
                }
            }
        });
        mDrawer.setNavigationItemSelectedListener(this);
        swapContent(R.id.nav_home);
        TextView weaponType = (TextView) mDrawer.getHeaderView(0).findViewById(R.id.weapon_type);
        weaponType.setText(Prefs.getWeaponType());

        mStorageRef = FirebaseStorage.getInstance().getReference();
        //        firebase.readImageValue("data/images/position1", getBaseContext(), img);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetToolbar();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        menuItemID = item.getItemId();
        swapContent(menuItemID);

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // TODO: Quiz/whatever needed for testing
    // TODO: Note feature??
    private void swapContent(int itemId) {
        setTitle(null);
        switch (itemId) {
            case R.id.nav_home:
                setTitle(Res.string(R.string.nav_home));
                setContent(HomeFragment.newInstance());
                break;
            case R.id.nav_origin:
                setTitle(Res.string(R.string.nav_origin));
                setContent(OriginsFragment.newInstance());
                break;
            case R.id.nav_stance:
                setTitle(Res.string(R.string.nav_stance));
                setContent(BasicsTabsFragment.newInstance());
                break;
            case R.id.nav_guards:
                setTitle(Res.string(R.string.nav_guards));
                setContent(GuardsFragment.newInstance());
                break;
            case R.id.nav_offense:
                setTitle(Res.string(R.string.nav_offense));
                setContent(OffenseFragment.newInstance());
                break;
            case R.id.nav_defending:
                setTitle(Res.string(R.string.nav_defending));
                setContent(EmptyFragment.newInstance());
                break;
            case R.id.nav_time:
                setTitle(Res.string(R.string.nav_time));
                setContent(TandDTabsFragment.newInstance());
                break;
            case R.id.nav_glossary:
                setTitle(Res.string(R.string.nav_glossary));
                setContent(GlossaryFragment.newInstance());
                break;
//            case R.id.nav_change:
//                setTitle(Res.string(R.string.nav_change));
//                setContent(SettingsFragment.newInstance());
//                break;
        }
    }

    public void pushFragment(BaseFragment fragment) {
        addContentToBackstack(fragment);
    }
}
