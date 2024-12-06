package com.example.wsw;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNaView = findViewById(R.id.bottom_navigation);
        bottomNaView.setOnNavigationItemSelectedListener(this::handleNavigationItemSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_settings) {
            bottomNaView.setSelectedItemId(R.id.nav_settings);
            return true;
        } else if (id == R.id.menu_open) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Открыть файл")
                    .setMessage("Вы действительно хотите открыть файл?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        Toast.makeText(MainActivity.this, "Файл открыт!",
                                Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Отмена", null)
                    .show();
            return true;
        } else if (id == R.id.menu_save) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Сохранение...");
            progressDialog.show();
            new Handler().postDelayed(() -> {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Файл успешно сохранен!",
                        Toast.LENGTH_SHORT).show();
            }, 1500);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean handleNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            setFragment(new BlankFragment());
            return true;
        } else if (itemId == R.id.nav_settings) {
            setFragment(new SecondFragment());
            return true;
        }
        return false;
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.selectedMenu, fragment)
                .commit();
    }
}