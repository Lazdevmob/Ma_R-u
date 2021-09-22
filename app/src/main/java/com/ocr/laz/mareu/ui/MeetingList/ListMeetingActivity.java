package com.ocr.laz.mareu.ui.MeetingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ocr.laz.mareu.databinding.ActivityListMeetingBinding;
import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.model.Meeting;
import com.ocr.laz.mareu.service.MeetingApiService;

import java.util.List;
import java.util.Objects;

public class ListMeetingActivity extends AppCompatActivity implements View.OnClickListener, MeetingAdapter.DeleteItemListener, MeetingAdapter.EditMeetingListener {


    /**
     * du recyclerview
     * ldev
     */

    //private RecyclerView recyclerView;
    //private FloatingActionButton fab;


    private ActivityListMeetingBinding binding;
    private List<Meeting> mMeetings;
    private MeetingApiService mMeetingApiService = Di.getMeetingApiService();
    private MeetingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        confiRecycler();
        setButton();
    }


    private void confiRecycler() {
        mMeetings = mMeetingApiService.getMeetings();
        adapter = new MeetingAdapter(mMeetings, this, this);
        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.recyclerview.getLayoutManager();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                Objects.requireNonNull(layoutManager).getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(adapter);
      //adapter.notifyDataSetChanged();

    }

    private void setButton() {
        binding.activityAddMeetingFab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ListMeetingActivity.this.startActivity(new Intent(this, AddMeetingActivity.class));
        Toast.makeText(v.getContext(), "To be implemented !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDeleteItem(int position, Meeting meeting) {
        mMeetingApiService.deleteMeeting(meeting);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onEditMeeting(int adapterPosition, Meeting meeting) {
        Toast.makeText(this, "to be implemented when activity is created", Toast.LENGTH_SHORT).show();
    }

   @Override
   protected void onResume() {
       super.onResume();
       adapter.notifyDataSetChanged();
   }
}
