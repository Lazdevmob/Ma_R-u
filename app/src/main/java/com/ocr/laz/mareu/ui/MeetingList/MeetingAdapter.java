package com.ocr.laz.mareu.ui.MeetingList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.laz.mareu.databinding.MeetingItemBinding;
import com.ocr.laz.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Lazdev OCR on 15/09/2021.
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private List<Meeting> mMeetings;
    private final DeleteItemListener callback;

    public MeetingAdapter(List<Meeting> items, DeleteItemListener deleteItemListener) {
        mMeetings = items;
        callback = deleteItemListener;
    }

    interface DeleteItemListener {
        void onDeleteItem(int Position, Meeting meeting);
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MeetingItemBinding binding = MeetingItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.displayMeeting(meeting);

        holder.meetingDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDeleteItem(holder.getAdapterPosition(), meeting);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}
