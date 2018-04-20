package com.example.kuldip.attendance;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Kuldip on 1/18/2018.
 */

public class MenuOption  extends AppCompatActivity {
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                break;
            case R.id.shareapp:
                break;

            case R.id.rateus:
                break;

            case R.id.disclaimer:
                showDisclaimer();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showDisclaimer(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Disclaimer");
        View view = LayoutInflater.from(this).inflate(R.layout.menu_disclaimer,null);
        view.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
        dialog.setContentView(view);
        dialog.show();
        dialog.setCancelable(false);
    }



}
