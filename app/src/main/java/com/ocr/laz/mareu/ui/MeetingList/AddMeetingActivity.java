package com.ocr.laz.mareu.ui.MeetingList;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.laz.mareu.databinding.ActivityAddMeetingBinding;
import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.model.Meeting;
import com.ocr.laz.mareu.model.Room;
import com.ocr.laz.mareu.service.DummyRoomGenerator;
import com.ocr.laz.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener,View.OnClickListener, AdapterView.OnItemSelectedListener {

    ActivityAddMeetingBinding binding;
    private MeetingApiService mMeetingApiService = Di.getMeetingApiService();
    //private final List<Room> rooms= new ArrayList<>();
    private String roomSelected;
    int hour;
    int minutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //getSupportActionBar().setTitle("New meeting");
        setContentView(view);
        setButton();
        initSpinner();
       //binding.textFieldHour2.setOnClickListener(new View.OnClickListener() {
       //Override
       //public void onClick(View v) {
       //popTimePicker();
       //}
       //});
        //popTimePicker();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.saveMeetingButton) {
            onSubmit();
        }
       if (view == binding.textFieldHour2) {
           popTimePicker();
       }
    }

   public void popTimePicker() {
       TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       hour = hourOfDay;
                       minutes = minute;
                       binding.textFieldHour2.setText(String.format(Locale.getDefault(),"%dh%d",hour,minutes));
                   }
               };
               int Style = AlertDialog.THEME_HOLO_DARK;
               TimePickerDialog timePickerDialog =
                       new TimePickerDialog(this, onTimeSetListener, hour, minutes, true);

               timePickerDialog.setTitle("select time");
               timePickerDialog.show();
           }
 //     });
 // }



    private void initSpinner() {
        List<Room> rooms= new ArrayList<>(DummyRoomGenerator.generateRooms());
        ArrayAdapter<Room> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.textFieldRoom2.setAdapter(adapter);
        //binding.spinnerRoom.setAdapter(adapter);
        //binding.spinnerRoom.setOnItemSelectedListener(this);

    }

@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // roomSelected = binding.spinnerRoom.getItemAtPosition(position).toString();
        roomSelected = binding.textFieldRoom2.toString();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setButton() {
        binding.saveMeetingButton.setOnClickListener(this);
        binding.textFieldHour2.setOnClickListener(this);

    }


   // @Override
   // public void onClick(View view) {
   //     if (view == binding.saveMeetingButton) {
   //         onSubmit();
   //     }
   // }


    private void onSubmit() {
        String subject = binding.textFieldSubject.getEditText().getText().toString();
        String date = binding.textFieldDate.getEditText().getText().toString();
        String hour = binding.textFieldHour.getEditText().getText().toString();
        String roomName = binding.textFieldRoom2.getText().toString();

        //String roomName = binding.textFieldRoom.getEditText().getText().toString();
        //String roomName = roomSelected;
        String guest = binding.textFieldGuest.getEditText().getText().toString();
        //String content = binding.textFieldContent.getEditText().getText().toString();


//      if (recipient.isEmpty()) {
//          binding.textFieldRecipient.setError("Please type a recipient");
//          return;
//      }
//      if (subject.isEmpty()) {
//          binding.textFieldSubject.setError("Please type a subject");
//          return;
//      }
//      if (content.isEmpty()) {
//          binding.textFieldContent.setError("Please type a text");
//          return;
//      }
             //Meeting meeting = new Meeting(subject,guest);
        mMeetingApiService.createMeeting(new Meeting(subject,date,guest, roomName));
        //Toast.makeText(this,"Mail sent !",Toast.LENGTH_SHORT).show();finish();

        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}


