package com.muhaiminurabir.record7;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import com.karan.churi.PermissionManager.PermissionManager;

public class MainActivity extends AppCompatActivity {

    PermissionManager permissionManager;

    private Boolean onService = false;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchService(view);
            }
        });

        init();
        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(MainActivity.this);
    }

    private void init() {
        radioGroup = findViewById(R.id.radioGroup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    private void switchService(View view) {
        if (onService) offService(view);
        else onService(view);
    }

    private int getAudioSourceId() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioDef:
                return 0;
            case R.id.radioMic:
                return 1;
            case R.id.radioUp:
                return 2;
            case R.id.radioDown:
                return 3;
            case R.id.radioCall:
                return 4;
            case R.id.radioCam:
                return 5;
            case R.id.radioReco:
                return 6;
            case R.id.radioCommu:
                return 7;
            case R.id.radioRemote:
                return 8;
            case R.id.radioUnpro:
                return 9;
            default:
                return 1;
        }
    }

    private void onService(View view) {
        Intent phoneCall = new Intent(getApplicationContext(), CallRecService.class);
        phoneCall.putExtra(ProcessingBase.IntentKey.INSTANCE.getPHONE_NUMBER(), "+821093984100");
        phoneCall.putExtra(ProcessingBase.IntentKey.INSTANCE.getTYPE_CALL(), ProcessingBase.TypeCall.INSTANCE.getINC());
        phoneCall.putExtra(ProcessingBase.IntentKey.INSTANCE.getAUDIO_SOURCE(), getAudioSourceId());
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(phoneCall);
        } else {
            startService(phoneCall);
        }
        Snackbar.make(view, "CallRecService started", Snackbar.LENGTH_SHORT).show();
        onService = true;
    }

    private void offService(View view) {
        stopService(new Intent(getApplicationContext(), CallRecService.class));
        Snackbar.make(view, "CallRecService stopped", Snackbar.LENGTH_SHORT).show();
        onService = false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        permissionManager.checkResult(requestCode, permissions, grantResults);
    }
}
