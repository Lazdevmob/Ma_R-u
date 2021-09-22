package com.ocr.laz.mareu.ui.MeetingList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.laz.mareu.databinding.MeetingItem2Binding;
import com.ocr.laz.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Lazdev OCR on 15/09/2021.
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private List<Meeting> mMeetings;
    private final DeleteItemListener callback;
    private final EditMeetingListener callback2;


    public MeetingAdapter(List<Meeting> items, DeleteItemListener deleteItemListener, EditMeetingListener editMeetingListerner) {
        mMeetings = items;
        callback = deleteItemListener;
        callback2 = editMeetingListerner;
    }

    interface DeleteItemListener {
        void onDeleteItem(int Position, Meeting meeting);
    }

    interface EditMeetingListener {
        void onEditMeeting(int adapterPosition, Meeting meeting);
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.meeting_item2,parent,false);
        MeetingItem2Binding binding = MeetingItem2Binding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.displayMeetings(meeting);

        holder.meetingDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDeleteItem(holder.getAdapterPosition(), meeting);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callback2.onEditMeeting(holder.getAdapterPosition(), meeting);
                return false;
            }
        });
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Meeting meeting = mMeetings.get(position);
//        holder.displayMeetings(meeting);
//       // holder.displayMeetings(mMeetings.get(position));
//
//    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    //    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//       //
//       // public final ImageView;
//
//        private final ImageView meetingAvatar;
//        private final TextView meetingDescription;
//        private final ImageButton meetingDeleteBtn;
//
//        public ViewHolder(@NonNull MeetingItem2Binding binding) {
//            super(binding.getRoot());
//            meetingAvatar = binding.itemListMeetingAvatar;
//            meetingDescription = binding.itemListMeetingDesription;
//            meetingDeleteBtn = binding.itemListMeetingDeleteButton;
//        }
//
//        public void displayMeetings(Meeting meeting) {
//            meetingDescription.setText( meeting.getSubject());
//        }
//    }
}
