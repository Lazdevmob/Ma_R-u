package com.ocr.laz.mareu.ui.MeetingList;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.databinding.ActivityAddMeetingBinding;
import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.model.Meeting;
import com.ocr.laz.mareu.model.Room;
import com.ocr.laz.mareu.repository.DummyRoomGenerator;
import com.ocr.laz.mareu.repository.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener
{

    ActivityAddMeetingBinding binding;
    private MeetingApiService mMeetingApiService = Di.getMeetingApiService();
    private Calendar calendar;
    private List<Room> rooms;
    private String[] listGuestItems;
    boolean[] checkedItems;
    private List<String> selectedGuests = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        calendar = Calendar.getInstance();
        setButton();
        initSpinner();
        listGuestItems = getResources().getStringArray(R.array.Guest_List);
        checkedItems = new boolean[listGuestItems.length];
    }

    @Override
    public void onClick(View view) {
        if (view == binding.saveMeetingButton) {
            onSubmit();
        }
        if (view == binding.textFieldHour2) {
            popTimePicker();
        }
        if (view == binding.textFieldDate2) {
            popDatePicker();
        }
        if (view == binding.textFieldGuest2) {
            guestDialog();
        }

    }

    public void popTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            /**
             *  setting time for fields
             */
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                String hour = simpleDateFormat.format(calendar.getTime());
                binding.textFieldHour2.setText(hour);
            }
        };

        /**
         * definition display time dialog screen
         */
        int Style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, Style, onTimeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        timePickerDialog.setTitle("select time");
        timePickerDialog.show();
    }

    public void popDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            /**
             *  setting date for fields
             */
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd/MM/yy");
                String date = simpleDateFormat.format(calendar.getTime());
                binding.textFieldDate2.setText(date);
            }
        };

        /**
         * definition display date dialog screen
         */
        int Style = AlertDialog.THEME_HOLO_DARK;
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, Style, onDateSetListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.setTitle("select date");
        datePickerDialog.show();
    }

    private void initSpinner() {
        rooms = DummyRoomGenerator.generateRooms();
        ArrayAdapter<Room> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.textFieldRoom2.setAdapter(adapter);
    }

    private void guestDialog() {
        MaterialAlertDialogBuilder mGBuilder = new MaterialAlertDialogBuilder(this);
        mGBuilder.setTitle("Select Guest");

        mGBuilder.setPositiveButton("ok", (dialog, which) -> {
            String guests = selectedGuests.toString()
                    .replace("[", "").replace("]", "");
            binding.textFieldGuest2.setText(guests);
        });

        //retablir les cases cochées
        mGBuilder.setMultiChoiceItems(listGuestItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (!selectedGuests.contains(listGuestItems[which])) {
                    selectedGuests.add(listGuestItems[which]);
                }
                else { selectedGuests.remove(listGuestItems[which]);
                }
                checkedItems[which] = isChecked;
            }
        });
        mGBuilder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void setButton() {
        binding.saveMeetingButton.setOnClickListener(this);
        binding.textFieldHour2.setOnClickListener(this);
        binding.textFieldDate2.setOnClickListener(this);
        binding.textFieldGuest.setOnClickListener(this);
        binding.textFieldGuest2.setOnClickListener(this);
    }

    private void onSubmit() {
        String subject = binding.textFieldSubject.getEditText().getText().toString();
        String date = binding.textFieldDate.getEditText().getText().toString();
        String beginHour = binding.textFieldHour.getEditText().getText().toString();
        String roomName = binding.textFieldRoom2.getText().toString();
        String guest = binding.textFieldGuest.getEditText().getText().toString();

        //if (subject.isEmpty()) {
        //    binding.textFieldSubject.setError("Please type a subject");
        //    return;
        //}
        // if (date.isEmpty()) {
        //     binding.textFieldDate.setError("Please choose a date");
        //     return;
        // }

        // if (beginHour.isEmpty()) {
        //     binding.textFieldHour.setError("Please choose an hour");
        //     return;
        // }

        // if (roomName.isEmpty()) {
        //     binding.textFieldRoom.setError("Please choose a room");
        //     return;
        // }

        // if (guest.isEmpty()) {
        //     binding.textFieldGuest.setError("Please choose guest");
        //     return;
        // }

        mMeetingApiService.createMeeting(new Meeting(subject, beginHour, date, guest, roomName));
        finish();
    }
}




