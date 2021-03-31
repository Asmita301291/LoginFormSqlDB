package com.example.loginformsqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText fname, mname, lname, email, phno, stdid, year, grade,bmonth,bday,byear,birthdate;
    String FName, MName, LName, Email, Phno, Stdid, Year, Grade,Gender,Semister,BirthDate,BDay,BYear;
    Spinner semister;
    RadioGroup gender;
    RadioButton radioButton;
    String stringvalues[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
    ArrayAdapter<String> adapter;
    final Calendar myCalendar = Calendar.getInstance();
    AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname=findViewById(R.id.fname);
        mname=findViewById(R.id.mname);
        lname=findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phno = findViewById(R.id.cono);
        stdid = findViewById(R.id.stdid);
        year = findViewById(R.id.year);
       /* bmonth = findViewById(R.id.bm);
        bday = findViewById(R.id.bd);
        byear = findViewById(R.id.by);*/
        grade = findViewById(R.id.grade);
        semister=findViewById(R.id.semister1);
        gender = findViewById(R.id.gender);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, stringvalues);
        semister.setAdapter(adapter);

        birthdate= (EditText) findViewById(R.id.birthdate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        birthdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        semister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MainActivity.this,"Course is "+stringvalues[i],Toast.LENGTH_SHORT).show();
                Semister = stringvalues[i];
                //Toast.makeText(MainActivity.this,"Semister is "+semi,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "You have not select any item", Toast.LENGTH_SHORT).show();
            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = radioGroup.findViewById(i);
            }
        });
        int selectedid = gender.getCheckedRadioButtonId();
        if (selectedid == -1) {
            // Toast.makeText(MainActivity.this, "You have not selected anything", Toast.LENGTH_SHORT).show();
            /*alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Gender field is empty");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            gender.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(gender, InputMethodManager.SHOW_IMPLICIT);
                            //dismiss the dialog
                        }
                    });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    alertDialog.show();
                }
            });*/

        } else {
            Gender = radioButton.getText().toString();
            //Toast.makeText(MainActivity.this, "" + radioButton.getText(), Toast.LENGTH_SHORT).show();
        }
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birthdate.setText(sdf.format(myCalendar.getTime()));
    }
    public void openSubmitData(View view) {
        FName = fname.getText().toString().trim();
        MName = mname.getText().toString().trim();
        LName = lname.getText().toString().trim();
        BirthDate = birthdate.getText().toString();
        /*BDay = bday.getText().toString();
        BYear = byear.getText().toString();*/
        Email = email.getText().toString().trim();
        Phno = phno.getText().toString();
        Stdid = stdid.getText().toString();
        Year = year.getText().toString();
        Grade = grade.getText().toString();
        Gender = radioButton.getText().toString();
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dfname", FName);
        values.put("dmname", MName);
        values.put("dlname", LName);
        values.put("dbirthdate", BirthDate);
        values.put("demail", Email);
        values.put("dphno", Phno);
        values.put("dstdid", Stdid);
        values.put("dyear", Year);
        values.put("dgrade", Grade);
        values.put("dgender", Gender);
        long rowId = db.insert("user_login", null, values);
       // long rowId = db.insert("user_login_1", null, values);
        Log.e("Row Id", "************" + rowId +" "+FName);
        Toast.makeText(this, "Data Inserted Sucessfully\n" + FName +" " +MName+ " "+LName +" " +BirthDate +" "+Gender+" "+Stdid+" "+ rowId, Toast.LENGTH_LONG).show();
    }
}