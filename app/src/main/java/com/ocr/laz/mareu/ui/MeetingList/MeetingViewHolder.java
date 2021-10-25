package com.ocr.laz.mareu.ui.MeetingList;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.laz.mareu.databinding.MeetingItemBinding;
import com.ocr.laz.mareu.model.Meeting;
import com.ocr.laz.mareu.repository.DummyRoomGenerator;

import java.util.Map;

/**
 * Created by Lazdev OCR on 16/09/2021.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder {

    //protected final ImageView meetingAvatar;
    protected final TextView meetingDescription;
    protected final TextView meetingGuest;
    protected final ImageButton meetingDeleteBtn;
    protected final ImageView meetingAvatar;



    public MeetingViewHolder(@NonNull MeetingItemBinding binding) {
        super(binding.getRoot());
        meetingDescription = binding.itemListMeetingDesription;
        meetingGuest = binding.itemListMeetingGuest;
        meetingDeleteBtn = binding.itemListMeetingDeleteButton;
        meetingAvatar = binding.itemListMeetingAvatar;
    }

    public void displayMeeting(@NonNull Meeting meeting) {
        //meetingDescription.setText(new StringBuilder().append(meeting.getSubject())
        // .append(" ").append(meeting.getBeginDate()).toString());
        meetingDescription.setText(new StringBuilder().append(meeting.getSubject())
                .append(" - ").append(meeting.getBeginHour()).append(" - ").append(meeting.getDate()).toString());
        meetingGuest.setText(new StringBuilder().append(" - salle ")
                .append(meeting.getRoomName()).append(" - ").append(meeting.getGuest()).toString());

        Map<String, Integer> roomHashMap = DummyRoomGenerator.generateRoomHashMap();
        if (!meeting.getRoomName().equals("")) {
            int roomColor = roomHashMap.get(meeting.getRoomName());
            meetingAvatar.setColorFilter(roomColor);
        }
    }
}


