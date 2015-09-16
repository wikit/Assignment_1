package au.com.deangoddard_17822594.assignment_1;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import android.widget.EditText;
import android.widget.Toast;

public class Friends extends Activity implements OnClickListener
{
    EditText editFName, editLName, editPhone;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,homeBtn;
    SQLiteDatabase db;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elmain);
        //Friends text input
        editFName =(EditText)findViewById(R.id.editFName);
        editLName =(EditText)findViewById(R.id.editName);
        editPhone =(EditText)findViewById(R.id.editPhoneNo);
        
        
        //Buttons
        
        btnAdd=(Button)findViewById(R.id.AddBut);
        btnDelete=(Button)findViewById(R.id.DelBut);
        btnModify=(Button)findViewById(R.id.EditBut);
        btnView=(Button)findViewById(R.id.ViewBut);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        homeBtn=(Button)findViewById(R.id.homebutton);

        //Listeners
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        homeBtn.setOnClickListener(this);
        
        //Database
        db=openOrCreateDatabase("FriendDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS friend (FName VARCHAR,name VARCHAR,marks VARCHAR);");
    }
    public void onClick(View view)
    {
        if(view==btnAdd)
        {
            if(editFName.getText().toString().length()==0||
                    editLName.getText().toString().length()==0||
                    editPhone.getText().toString().length()==0)
            {

                Toast.makeText(getApplicationContext(), "Missing Details!",
                        Toast.LENGTH_LONG).show();

                return;
            }


           // if()

            db.execSQL("INSERT INTO friend VALUES('" + editFName.getText() + "','" + editLName.getText() +
                    "','" + editPhone.getText() + "');");

            Toast.makeText(getApplicationContext(), "Friend has been added!",
                    Toast.LENGTH_LONG).show();


            clearText();
        }
        if(view==btnDelete)
        {
            if(editFName.getText().toString().trim().length()==0)
            {
                Toast.makeText(getApplicationContext(), "ERROR Please enter First Name",
                        Toast.LENGTH_LONG).show();

                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM friend WHERE FName='"+ editFName.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM friend WHERE FName='" + editFName.getText() + "'");

                Toast.makeText(getApplicationContext(), "Friend has been DELETED!",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid First Name!",
                        Toast.LENGTH_LONG).show();

            }
            clearText();
        }
        if(view==btnModify)
        {
            if(editFName.getText().toString().trim().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Please Enter First Name!",
                        Toast.LENGTH_LONG).show();

                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM friend WHERE FName='"+ editFName.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("UPDATE friend SET name='" + editLName.getText() + "',marks='" + editPhone.getText() +
                        "' WHERE FName='" + editFName.getText() + "'");

                Toast.makeText(getApplicationContext(), "Friend has been Modified!",
                        Toast.LENGTH_LONG).show();


            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Name!",
                        Toast.LENGTH_LONG).show();

            }
            clearText();
        }
        if(view==btnView)
        {
            if(editFName.getText().toString().trim().length()==0)
            {

                Toast.makeText(getApplicationContext(), "Please enter First Name!",
                        Toast.LENGTH_LONG).show();

                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM friend WHERE FName='"+ editFName.getText()+"'", null);
            if(c.moveToFirst())
            {
                editLName.setText(c.getString(1));
                editPhone.setText(c.getString(2));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Name!",
                        Toast.LENGTH_LONG).show();
                clearText();
            }
        }
        if(view==btnViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM friend", null);
            if(c.getCount()==0)
            {
                Toast.makeText(getApplicationContext(), "INo Records Found!",
                        Toast.LENGTH_LONG).show();

                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("First Name: "+c.getString(0)+"\n");
                buffer.append("Last Name: " + c.getString(1) + "\n");
                buffer.append("Phone Number: "+c.getString(2)+"\n\n");
            }
            Message("Friend Details", buffer.toString());
        }
        if(view==homeBtn)
        {

            startActivity(new Intent(Friends.this, MainActivity.class));

        }

    }
    public void Message(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        editFName.setText("");
        editLName.setText("");
        editPhone.setText("");
        editFName.requestFocus();
    }
}