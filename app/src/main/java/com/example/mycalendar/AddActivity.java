package com.example.mycalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    EditText edtText;
    TextView edtDate;
    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.InitializeView();
        this.InitializeListener();

        edtText = findViewById(R.id.edtMemo);
        edtDate = (TextView) findViewById(R.id.textView_date);
        findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edtText.getText().toString();
                //String substr= findViewById(R.id.textView_date);

                if (str.length() > 0) {
                    //Date date=new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String substr = edtDate.getText().toString();
                    //String substr=sdf.format(getString(edtDate.getText()));
                    //String substr=edtDate.getYear;
                    Toast.makeText(AddActivity.this, str + "," + substr, Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent();
                    intent.putExtra("main", str);
                    intent.putExtra("sub", substr);
                    setResult(0, intent);

                    finish();

                }
            }
        });

        findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void InitializeView() {
        textView_Date = (TextView) findViewById(R.id.textView_date);
    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView_Date.setText(year + "년" + (month + 1) + "월" + dayOfMonth + "일");

            }
        };
    }

    public void OnClickHandler(View view) {
        Date date = Calendar.getInstance().getTime();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy", Locale.getDefault()).format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM", Locale.getDefault()).format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(date));

        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, year, month, day);

        dialog.show();
    }
}