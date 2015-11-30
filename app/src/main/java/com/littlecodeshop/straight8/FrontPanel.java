package com.littlecodeshop.straight8;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        the8 = new PDP8();
        setContentView(R.layout.activity_front_panel);

        TextView et = (TextView)findViewById(R.id.textView);
        et.setText(the8.status());
    }


    public void updateDisplay(){
        //get the status string
        String status = the8.status();
        Pattern pattern = Pattern.compile("AC:(\\d\\d\\d\\d) L:(\\d) PC:(\\d\\d\\d\\d) MA:(\\d\\d\\d\\d) MB:(\\d\\d\\d\\d) IR:(\\d)");
        Matcher m =  pattern.matcher(status);

        if(m.find()){

            Log.d(TAG, "step PC "+m.group(3));
        }
    }

    /******************* PDP8 interface *********************/

    public void step(View view){
        the8.step();
        TextView et = (TextView)findViewById(R.id.textView);
        et.setText(the8.status());
    }

    public void loadAdd(View view){
        //get the text from edittext
        EditText srtext = (EditText)findViewById(R.id.editText);
        String value = srtext.getText().toString();
        Integer i = Integer.parseInt(value,8);
        Log.d(TAG, "loadAdd() returned: " + i );
        the8.setSR((short) i.shortValue());
        the8.loadAddress();
    }

    public void exam(View view){
        //show the value of a selected memory location
        the8.examine();
        EditText srtext = (EditText)findViewById(R.id.editText);
        srtext.setText("yopyop");
    }

    public void deposit(View view){
        the8.deposit();
    }



    /*********************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front_panel, menu);
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
}
