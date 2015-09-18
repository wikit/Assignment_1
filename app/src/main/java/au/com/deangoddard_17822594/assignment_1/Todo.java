package au.com.deangoddard_17822594.assignment_1;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Todo extends Activity implements OnClickListener{


    //EditText editFName, editLName, editAge, editAdd, editGender;
    Button btnAddto,btnDelete,btnModify,btnView,btnViewAll,homeBtn;
    SQLiteDatabase dbtodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        //DataBase Stuff

        dbtodo = openOrCreateDatabase("todoDB", Context.MODE_PRIVATE, null);
        dbtodo.execSQL("CREATE TABLE IF NOT EXISTS todo (name VARCHAR, location VARCHAR, status VARCHAR, CONSTRAINT pkey PRIMARY KEY (name));");

        //Buttons
        btnAddto=(Button)findViewById(R.id.btnAddto);
        //btnDelete=(Button)findViewById(R.id.DelBut);
        //btnModify=(Button)findViewById(R.id.EditBut);
        //btnView=(Button)findViewById(R.id.ViewBut);
        //btnViewAll=(Button)findViewById(R.id.btnViewAll);
        //homeBtn=(Button)findViewById(R.id.homebutton);


        //Listeners
        btnAddto.setOnClickListener(this);
        //btnDelete.setOnClickListener(this);
        //btnModify.setOnClickListener(this);
        //btnView.setOnClickListener(this);
        //btnViewAll.setOnClickListener(this);
        //homeBtn.setOnClickListener(this);


    }

    public void onClick(View view)
    {


        if(view==btnAddto) {




           // if (editFName.getText().toString().length() == 0 ||
                 //   editLName.getText().toString().length() == 0 ||
                //    editAge.getText().toString().length() == 0 ||
                //    editAdd.getText().toString().length() == 0) {

               // Toast.makeText(getApplicationContext(), "Missing Details!",
                    //    Toast.LENGTH_LONG).show();

              //  return;
          //  }


          //  dbtodo.execSQL("INSERT INTO todo VALUES('" + editFName.getText() + "','" + editLName.getText() +
               //     "','" + editAge.getText() + "','" + editGender.getText() + "','" + editAdd.getText() + "');");

            Toast.makeText(getApplicationContext(), "Task has been added!",
                    Toast.LENGTH_LONG).show();




            //}
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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
