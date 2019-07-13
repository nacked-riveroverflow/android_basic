package com.example.h86zhu.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements IView{
    public static Model model;
    public RecyclerView recList;
    public TopBar tbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            GridLayoutManager glm = new GridLayoutManager(this, 2);
            recList.setLayoutManager(glm);
        } else {
            GridLayoutManager glm2 = new GridLayoutManager(this, 1);
            recList.setLayoutManager(glm2);
        }

        this.model = new Model(this);
        this.model.addObserver(this);
        tbar = new TopBar(this, model);

        Card_Adpater ca = new Card_Adpater(model);
        recList.setAdapter(ca);
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((GridLayoutManager) recList.getLayoutManager()).setSpanCount(1);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((GridLayoutManager) recList.getLayoutManager()).setSpanCount(2);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateView() {
        ((Card_Adpater)this.recList.getAdapter()).refresh_image();
        ((Card_Adpater)this.recList.getAdapter()).notifyDataSetChanged();
    }
}
