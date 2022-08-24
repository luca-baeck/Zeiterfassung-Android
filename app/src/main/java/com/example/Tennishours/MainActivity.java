package com.example.Tennishours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.SeekBar;

import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "Info_Tennishours";

    Button startButton, endButton, confirmButton, hourlywage, completedHours, reset, getBack;
    TextView seekBarInfo, difTime, money, hoursTV,TV4,Balance, scrollViewText;
    SeekBar seekBar;
    ImageView IV4;
    ScrollView scrollView;
    LinearLayout linearLayout;
    Date start, end;

    int oSYear=0;
    int oSMonth=0;
    int oSDate=0;
    int oSHour=0;
    int oSMinute=0;

    int oEYear=0;
    int oEMonth=0;
    int oEDate=0;
    int oEHour=0;
    int oEMinute=0;

    int hours = 0;
    int dates = 0;
    int wage = 0;
    double exacthours = 0;
    String[] datesString;
    String scrollViewTextString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Start");
        System.out.println(String.valueOf(dates));


        SharedPreferences settings = getSharedPreferences(FILE_NAME,0);
        SharedPreferences settings2 = getSharedPreferences("Dates",0);


        hours = settings.getInt("hours",hours);

        dates = settings.getInt("dates",dates);
        System.out.println(String.valueOf(dates));
        datesString = new String[dates];

        loadDates();


        wage = settings.getInt("wage",wage);

        exacthours = hours/10.0;


        reset = findViewById(R.id.reset);
        getBack = findViewById(R.id.getback);
        scrollViewText = findViewById(R.id.textViewtest);
        scrollViewText.setText(scrollViewTextString);
        linearLayout = findViewById(R.id.linearLayout);
        scrollView = findViewById(R.id.scrollView);
        startButton = findViewById(R.id.pickStartBtn);
        startButton.setText("Start");
        endButton = findViewById(R.id.pickEndBtn);
        endButton.setText("End");
        confirmButton = findViewById(R.id.confirm);
        hourlywage = findViewById(R.id.hourlywage);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setVisibility(View.INVISIBLE);
        seekBar.setMax(25);
        seekBar.setProgress(wage);
        seekBarInfo = findViewById(R.id.seekBarInfo);
        seekBarInfo.setVisibility(View.INVISIBLE);
        seekBarInfo.setText(wage + "€");
        difTime = findViewById(R.id.differenceTime);
        difTime.setText("");
        hoursTV = findViewById(R.id.stunden);
        hoursTV.setText("hours: "+ exacthours);
        money = findViewById(R.id.money);
        money.setText((wage*exacthours) + "€");
        TV4 = findViewById(R.id.textView4);
        IV4 = findViewById(R.id.imageView4);
        completedHours = findViewById(R.id.completedhours);
        Balance = findViewById(R.id.stunden2);


        scrollViewText.setVisibility(View.INVISIBLE);
        getBack.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                String t = i + "€";
                seekBarInfo.setText(t);
                hoursTV.setText("hours: "+ exacthours);
                wage = i;
                money.setText((wage*exacthours) + "€");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                seekBar.setVisibility(View.INVISIBLE);
                seekBarInfo.setVisibility(View.INVISIBLE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("wage", seekBar.getProgress());
                editor.commit();
                wage = seekBar.getProgress();
                hoursTV.setText("hours: "+ exacthours);
                money.setText((wage*exacthours) + "€");

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings2 = getSharedPreferences("Dates",0);
                SharedPreferences.Editor editor2 = settings2.edit();
                editor2.clear();
                editor2.commit();
                SharedPreferences settings = getSharedPreferences(FILE_NAME,0);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.commit();
                String scrollViewTextString = "";
                hours = 0;
                dates = 0;
                wage = 0;
                exacthours = 0.0;
                seekBar.setProgress(0);

                loadDates();
                scrollViewText = findViewById(R.id.textViewtest);
                scrollViewText.setText(scrollViewTextString);
                hoursTV.setText("hours: "+ exacthours);
                money.setText((wage*exacthours) + "€");
            }
        });
        getBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setVisibility(View.VISIBLE);
                endButton.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                hourlywage.setVisibility(View.VISIBLE);
                completedHours.setVisibility(View.VISIBLE);
                //seekBarInfo.setVisibility(View.VISIBLE);
                difTime.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                hoursTV.setVisibility(View.VISIBLE);
                //TV4.setVisibility(View.INVISIBLE);
                //seekBar.setVisibility(View.VISIBLE);
                IV4.setVisibility(View.VISIBLE);
                Balance.setVisibility(View.VISIBLE);
                scrollViewText.setVisibility(View.INVISIBLE);
                getBack.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.INVISIBLE);
            }
        });
        completedHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setVisibility(View.INVISIBLE);
                endButton.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.INVISIBLE);
                hourlywage.setVisibility(View.INVISIBLE);
                completedHours.setVisibility(View.INVISIBLE);
                seekBarInfo.setVisibility(View.INVISIBLE);
                difTime.setVisibility(View.INVISIBLE);
                money.setVisibility(View.INVISIBLE);
                hoursTV.setVisibility(View.INVISIBLE);
                //TV4.setVisibility(View.INVISIBLE);
                seekBar.setVisibility(View.INVISIBLE);
                IV4.setVisibility(View.INVISIBLE);
                Balance.setVisibility(View.INVISIBLE);
                scrollViewText.setVisibility(View.VISIBLE);
                getBack.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);


            }
        });
        hourlywage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleHourlyWageButton();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleConfirmButton();
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleStartButton();
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEndButton();
            }
        });

        }

    private void loadDates(){

        datesString = new String[dates];
        SharedPreferences settings2 = getSharedPreferences("Dates",0);

        scrollViewTextString = "";
        for (int i =0;i<dates;i++){
            System.out.println("working...");
            datesString[i] = settings2.getString(String.valueOf(i), null);








        }

        for (String date:datesString
             ) {
            System.out.println(date);
            scrollViewTextString = scrollViewTextString + date + " \n " + " \n ";
        }
        System.out.println(scrollViewTextString);










    }

    private void handleHourlyWageButton(){
        if(seekBar.getVisibility() == View.VISIBLE){
            seekBar.setVisibility(View.INVISIBLE);
            seekBarInfo.setVisibility(View.INVISIBLE);
            return;
        }
        seekBar.setVisibility(View.VISIBLE);
        seekBarInfo.setVisibility(View.VISIBLE);
    }

    private void handleConfirmButton(){

        if(startButton.getText() == "Start" || endButton.getText() == "End"|| difTime.getText()==""){
            Toast.makeText(this,"Choose a valid start or/and a end time!!!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String diff = difTime.getText().toString();
        diff = diff.replace(" hours", "");
        double diffM = Double.parseDouble(diff);
        int addH = (int)(diffM *10);
        hours = hours + addH;
        dates = dates +1;
        System.out.println(String.valueOf(dates));
        SharedPreferences settings = getSharedPreferences(FILE_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("hours", hours);
        editor.putInt("dates", dates);
        editor.commit();
        exacthours = hours/10.0;
        hoursTV.setText("hours: "+ exacthours);
        money.setText((wage*exacthours) + "€");

        String input = "Duration: " + diff + " hours | " + "Start time: " + start;
        String index = String.valueOf(dates-1);
        SharedPreferences settings2 = getSharedPreferences("Dates",0);
        SharedPreferences.Editor editor2 = settings2.edit();
        editor2.putString(index, input);
        editor2.commit();
        loadDates();
        scrollViewText = findViewById(R.id.textViewtest);
        scrollViewText.setText(scrollViewTextString);
        Toast.makeText(this,"Added " + diff +" hours to your completed tasks.",
                Toast.LENGTH_SHORT).show();



    }

    private void handleStartButton() {
        Toast.makeText(this,"Choose start time...",
                Toast.LENGTH_SHORT).show();

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);



        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                start = new GregorianCalendar(oSYear, (oSMonth -1), oSDate, hour, minute).getTime();
                String startTime = start.toString();
               startButton.setTextSize(15);




                startButton.setText(startTime);
                if(end == null ){
                    return;
                }else{
                    dateDifference();
                }
            }
        }, mHour, mMinute, true);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {


                oSYear = year;
                oSMonth = month+1;
                oSDate = date;
                timePickerDialog.show();


            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();




    }

    private void handleEndButton() {
        Toast.makeText(this,"Choose end time...",
                Toast.LENGTH_SHORT).show();

        Calendar c = Calendar.getInstance();
        int mYear =0;
        int mMonth =0;
        int mDay =0;
        if(start!= null){
             mYear = oSYear;
             mMonth = oSMonth-1;
             mDay = oSDate;
        }else{
             mYear = c.get(Calendar.YEAR);
             mMonth = c.get(Calendar.MONTH);
             mDay = c.get(Calendar.DAY_OF_MONTH);
        }

        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);



        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                end = new GregorianCalendar(oEYear, (oEMonth -1), oEDate, hour, minute).getTime();

                System.out.println(oEYear);
                System.out.println(oEMonth);
                System.out.println(oEDate);
                System.out.println(end);




                String entTime = end.toString();
                endButton.setTextSize(15);
                endButton.setText(entTime);
                if(start == null ){
                    return;
                }else{
                    dateDifference();
                }

            }
        }, mHour, mMinute, true);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {


                oEYear = year;
                oEMonth = month+1;
                oEDate = date;

                timePickerDialog.show();


            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();



    }

    private void dateDifference(){
        if(end.getTime() <= start.getTime()){
            return;
        }
        System.out.println(end + " ... " + start);
        long diffInMillies = Math.abs(end.getTime() - start.getTime());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        double diffM = diff;
        diffM = diffM/60;
        diffM = round(diffM, 1);


        difTime.setText(Double.toString(diffM) + " hours");
    }

    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }















}