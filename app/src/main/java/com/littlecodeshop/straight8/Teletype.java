package com.littlecodeshop.straight8;

import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class Teletype extends AppCompatActivity {

    PDP8 the8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teletype);

        the8 = new PDP8();
        the8.reset();

        TextView printerView = (TextView)findViewById(R.id.printerView);



        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d("TELETYPE", "onKeyUp() called with: " + "keyCode = [" + keyCode + "], event = [" + event.getKeyCharacterMap().get(keyCode,event.getMetaState()) + "]");

        return false;
    }
}
