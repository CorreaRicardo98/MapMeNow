package e.ricardo.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class Hola extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"item1 select",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"item2 select",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"item3 select",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(getApplicationContext(),"item4 select",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item5:
                Toast.makeText(getApplicationContext(),"item5 select",Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}


