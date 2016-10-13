package full.mesrecycleviews.vues;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import full.mesrecycleviews.R;
import full.mesrecycleviews.vues.adapteurs.PageVueAdapteur;
import full.mesrecycleviews.vues.fragments.FragmentJoueurs;
import full.mesrecycleviews.vues.fragments.FragmentSets;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***** Ajout de la toolbar a l'activite principale *****/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /***** Reglage du viewpager pour chaque tabs   *****/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        /***** mise en place de la tabs dans la toolbar *****/
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    /***** Ajout des fraguement a la tabs   *****/
    private void setupViewPager(ViewPager viewPager) {
        PageVueAdapteur adapter = new PageVueAdapteur(getSupportFragmentManager());
        adapter.addFragment(new FragmentJoueurs(), "List");
        adapter.addFragment(new FragmentSets(), "Card");
        viewPager.setAdapter(adapter);
    }

}
