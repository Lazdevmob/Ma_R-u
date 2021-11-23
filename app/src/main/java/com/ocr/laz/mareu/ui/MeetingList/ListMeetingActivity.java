package com.ocr.laz.mareu.ui.MeetingList;

import static android.content.DialogInterface.OnClickListener;
import static android.content.DialogInterface.OnMultiChoiceClickListener;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.databinding.ActivityListMeetingBinding;
import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.model.Meeting;
import com.ocr.laz.mareu.repository.DummyMeetingGenerator;
import com.ocr.laz.mareu.repository.DummyRoomGenerator;
import com.ocr.laz.mareu.repository.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ListMeetingActivity<AlertDialogBuilder> extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener,
        MeetingAdapter.DeleteItemListener {

    private ActivityListMeetingBinding binding;

    private final List<Meeting> mMeetings = new ArrayList<>();
    private MeetingApiService mMeetingApiService = Di.getMeetingApiService();
    private MeetingAdapter adapter;

    private Calendar calendar;

    private String[] listRoomItems;
    boolean[] checkedItem;
    private final List<String> selectedRooms = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gestion changement d'orientation : retour à l'initial (liste de Dummy meetings restaurée et nouvelles réunions supprimées)
        if (savedInstanceState != null) {
            mMeetingApiService.getMeetings().clear();
            mMeetingApiService.getMeetings().addAll(DummyMeetingGenerator.DUMMY_MEETINGS);
        }
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecycler();
        setButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
                dateDialog();
                return true;

            case R.id.option_2:
                roomDialog();
                return true;

            //resetFilter
            case R.id.option_3:
                initList(mMeetingApiService.getMeetings(), true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void roomDialog() {
        MaterialAlertDialogBuilder mBuilder = new MaterialAlertDialogBuilder(this);
        mBuilder.setTitle("room");

        mBuilder.setPositiveButton("ok", new OnClickListener() {
            // Respond to positive button press
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Meeting> filteredRoomList = mMeetingApiService.getMeetingsInGivenRooms(selectedRooms);

                // TODO mettre en place initlist
                initList(filteredRoomList, true);
                selectedRooms.clear();
            }
        });

        listRoomItems = DummyRoomGenerator.generateRoomHashMap().keySet().toArray(new String[0]); // sequential with LinkedHashMap

        checkedItem = new boolean[listRoomItems.length];
        //Single-choice items (initialized with checked item)

        // Respond to item chosen
        mBuilder.setMultiChoiceItems(listRoomItems, checkedItem, new OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (!selectedRooms.contains(listRoomItems[which])) {
                    selectedRooms.add(listRoomItems[which]);
                } else {
                    selectedRooms.remove(listRoomItems[which]);
                }
            }
        });
        mBuilder.show();
    }

    private void dateDialog() {
        calendar = Calendar.getInstance();
        /**
         * definition display date dialog screen
         */
        int Style = AlertDialog.THEME_TRADITIONAL;
        DatePickerDialog datePickerDialog =
                // new DatePickerDialog(this, Style, onDateSetListener, year, month, dayOfMonth);
                new DatePickerDialog(this, Style, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle("select date");
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd/MM/yy");
        String date = simpleDateFormat.format(calendar.getTime());

        List<Meeting> filteredList = mMeetingApiService.getMeetingsAtGivenDate(date);
        initList(filteredList, true);
    }

    private void initRecycler() {
        adapter = new MeetingAdapter(mMeetings, this);
        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.recyclerview.getLayoutManager();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                Objects.requireNonNull(layoutManager).getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(adapter);
        //initList(mMeetingApiService.getMeetings(), true); //effectué par le onResume() (exécuté après onCreate() )
    }

    /**
     * Init the List of meetings
     */
    private void initList(List<Meeting> meetings, boolean doRefresh) {
        mMeetings.clear();
        mMeetings.addAll(meetings);
        if (doRefresh) adapter.notifyDataSetChanged();
    }

    private void setButton() {
        binding.activityAddMeetingFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ListMeetingActivity.this.startActivity(new Intent(this, AddMeetingActivity.class));
    }

    @Override
    public void onDeleteItem(int position, Meeting meeting) {
        mMeetingApiService.deleteMeeting(meeting);
        initList(mMeetingApiService.getMeetings(), false);
        adapter.notifyItemRemoved(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList(mMeetingApiService.getMeetings(), true);
    }
}

