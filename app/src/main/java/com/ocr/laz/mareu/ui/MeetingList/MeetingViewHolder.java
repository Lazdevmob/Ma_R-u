package com.ocr.laz.mareu.ui.MeetingList;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.databinding.ActivityAddMeetingBinding;
import com.ocr.laz.mareu.databinding.ActivityListMeetingBinding;
import com.ocr.laz.mareu.databinding.MeetingItemBinding;
import com.ocr.laz.mareu.model.Meeting;

import java.util.Date;

/**
 * Created by Lazdev OCR on 16/09/2021.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder {

    //protected final ImageView meetingAvatar;
    protected final TextView meetingDescription;
    protected final TextView meetingGuest;
    protected final ImageButton meetingDeleteBtn;



    public MeetingViewHolder(@NonNull MeetingItemBinding binding) {
        super(binding.getRoot());
        //meetingAvatar = binding.itemListMeetingAvatar;
        meetingDescription = binding.itemListMeetingDesription;
        meetingGuest = binding.itemListMeetingGuest;
        meetingDeleteBtn = binding.itemListMeetingDeleteButton;

    }

    public void displayMeetings(Meeting meeting) {
        //meetingDescription.setText(new StringBuilder().append(meeting.getSubject())
        // .append(" ").append(meeting.getBeginDate()).toString());
        meetingDescription.setText(new StringBuilder().append(meeting.getSubject())
                .append(" - ").append(meeting.getBeginDate()).append(" - salle ")
                .append(meeting.getRoomName()).toString());
        meetingGuest.setText(meeting.getGuest());
    }
}


