package com.littlecodeshop.straight8;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrontPanel extends AppCompatActivity {

    public static final String TAG ="PDP8";

    public PDP8 the8;
    private LedView pc_leds;
    private LedView acl_leds;
    private LedView ma_leds;
    private LedView mb_leds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        the8 = new PDP8();
        the8.reset();

        setContentView(R.layout.activity_front_panel);

        TextView et = (TextView)findViewById(R.id.textView);
        et.setText(the8.status());

        pc_leds = (LedView)findViewById(R.id.pc_leds);
        acl_leds = (LedView)findViewById(R.id.acl_leds);
        ma_leds = (LedView)findViewById(R.id.ma_leds);
        mb_leds = (LedView)findViewById(R.id.mb_leds);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


    }

    public void updateDisplay(){
        //get the status string
        String status = the8.status();
        Pattern pattern = Pattern.compile("AC:(\\d\\d\\d\\d) L:(\\d) PC:(\\d\\d\\d\\d) MA:(\\d\\d\\d\\d) MB:(\\d\\d\\d\\d) IR:(\\d)");
        Matcher m = pattern.matcher(status);

        if(m.find()){

            Log.d(TAG, "step PC "+m.group(3));
            pc_leds.setValue(Integer.parseInt(m.group(3)));
            acl_leds.setValue(Integer.parseInt(m.group(1)));
            ma_leds.setValue(Integer.parseInt(m.group(4)));
            mb_leds.setValue(Integer.parseInt(m.group(5)));
        }
    }

    /******************* PDP8 interface *********************/

    public void step(View view){
        the8.step();
        TextView et = (TextView)findViewById(R.id.textView);
        et.setText(the8.status());
        updateDisplay();
    }

    public void loadAdd(View view){
        //get the text from edittext
//        EditText srtext = (EditText)findViewById(R.id.editText);
//        String value = srtext.getText().toString();
//        Integer i = Integer.parseInt(value, 8);
//        Log.d(TAG, "loadAdd() returned: " + i );
//        the8.setSR((short) i.shortValue());
//        the8.loadAddress();

        the8.sendChar('\n');
    }

    public void exam(View view){
        //show the value of a selected memory location
        //the8.examine();
        //EditText srtext = (EditText)findViewById(R.id.editText);
        //srtext.setText("yopyop");
        the8.run(1000);
        int c = the8.getTeletypeChar();
        if(c!=-1){
            Log.d("PDP8", "TELETYPR PUCNH "+(char)c);
        }
        updateDisplay();

    }

    public void deposit(View view) {
        the8.deposit();

    }

    public void showTeletype(View view){
        Intent intent = new Intent(this, Teletype.class);
        startActivity(intent);
    }

    public void run(View view){
        new PDP8Runner().execute(the8);

    }



    /*********************************************************/


    //Now lets create a PDP8 runner
    private class PDP8Runner extends AsyncTask<PDP8,String,String> {

        @Override
        protected void onPreExecute(){
            Log.d("PDPRUNNER", " PRE EXECUTE");
        }

        @Override
        protected String doInBackground(PDP8... pdp){
            while(!isCancelled()){
                pdp[0].run(1000);
                int c = pdp[0].getTeletypeChar();
                if(c!=-1){
                    publishProgress("TELETYPE OUT :"+(char)c);
                }
            }
            return "FINISHED";
        }

        @Override
        protected void onProgressUpdate(String... values){
            Log.d("PDPRUNNER", "update :)"+values[0]);
            pc_leds.setValue(0777);

        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }
    }
    
    


}
