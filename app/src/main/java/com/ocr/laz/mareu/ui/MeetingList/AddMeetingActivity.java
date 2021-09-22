package com.ocr.laz.mareu.ui.MeetingList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ocr.laz.mareu.databinding.ActivityAddMeetingBinding;
import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.model.Meeting;
import com.ocr.laz.mareu.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityAddMeetingBinding binding;
    private MeetingApiService mMeetingApiService = Di.getMeetingApiService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //getSupportActionBar().setTitle("New mail");
        setButton();
    }


    private void setButton() {
        binding.saveMeetingButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        onSubmit();
    }


    private void onSubmit() {
        String subject = binding.textFieldSubject.getEditText().getText().toString();
        String date = binding.textFieldDate.getEditText().getText().toString();
        String hour = binding.textFieldHour.getEditText().getText().toString();
        String roomName = binding.textFieldRoom.getEditText().getText().toString();
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
}


