package au.com.deangoddard_17822594.assignment_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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


    EditText todoDesc, local, status ;
    Button btnAddto,btnDelete,btnEdit,btnVAll,homeBtnTodo;
    SQLiteDatabase dbtodo ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        //DataBase Stuff

        dbtodo = openOrCreateDatabase("todoDB", Context.MODE_PRIVATE, null);
        dbtodo.execSQL("CREATE TABLE IF NOT EXISTS todo (name VARCHAR, location VARCHAR, status VARCHAR, CONSTRAINT pkey PRIMARY KEY (name));");

        local = (EditText) findViewById(R.id.editLocation);
        status = (EditText) findViewById(R.id.editStatus);
        todoDesc = (EditText) findViewById(R.id.editdesc);

        //Buttons
        btnAddto=(Button)findViewById(R.id.btnAddto);
        btnEdit=(Button)findViewById(R.id.btnEditTodo);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnVAll=(Button)findViewById(R.id.btnVAll);
        homeBtnTodo=(Button)findViewById(R.id.homeBtnTodo);


        //Listeners
        btnAddto.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnVAll.setOnClickListener(this);
        homeBtnTodo.setOnClickListener(this);



    }

    public void onClick(View view)
    {


        if(view==btnAddto) {




           //todo code needs to be added into the next couple of lines


            dbtodo.execSQL("INSERT INTO todo VALUES('" + todoDesc.getText() + "','" + local.getText() +
                   "','" + status.getText() + "');");

            Toast.makeText(getApplicationContext(), "Task has been added!",
                    Toast.LENGTH_LONG).show();

            //}

        }
        if(view==btnEdit) {




            //todo edit code needs to be added into the next couple of lines


            dbtodo.execSQL("INSERT INTO todo VALUES('" + todoDesc.getText() + "','" + local.getText() +
                    "','" + status.getText() + "');");

            Toast.makeText(getApplicationContext(), "Task has been added!",
                    Toast.LENGTH_LONG).show();

            //}

        }

        if(view==btnDelete) {




            //todo del code needs to be added into the next couple of lines


            Cursor c=dbtodo.rawQuery("SELECT * FROM todo WHERE name='"+ todoDesc.getText()+"'", null);
            if(c.moveToFirst())
            {
                dbtodo.execSQL("DELETE FROM todo WHERE Name='" + todoDesc.getText() + "'");

                Toast.makeText(getApplicationContext(), "Task has been DELETED!",
                        Toast.LENGTH_LONG).show();
            }

            clearText();

        }

        if(view==btnVAll)
        {
            Cursor c=dbtodo.rawQuery("SELECT * FROM todo", null);
            if(c.getCount()==0)
            {
                Toast.makeText(getApplicationContext(), "No Records Found!",
                        Toast.LENGTH_LONG).show();

                return;
            }

            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Task Description: "+c.getString(0)+"\n");
                buffer.append("Location: " + c.getString(1) + "\n");
                buffer.append("Status: "+c.getString(2)+"\n");


            }
            Message("All Tasks", buffer.toString());
        }

        if(view==homeBtnTodo)
        {

            startActivity(new Intent(Todo.this, MainActivity.class));

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



    public void clearText()
    {
        todoDesc.setText("");
        local.setText("");
        status.setText("");


    }


    public void Message(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

