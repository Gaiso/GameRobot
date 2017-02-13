package com.gaiso.gamerobot.view.adapter;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaiso.gamerobot.R;
import com.gaiso.gamerobot.view.viewholder.DialogItemViewHolder;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class DialogRecyclerAdapter extends RobotRecyclerAdapter {

    private final CharSequence[] mItems;

    public DialogRecyclerAdapter(Context context, @ArrayRes int arrayResId) {
        this(context.getResources().getTextArray(arrayResId));
    }

    private DialogRecyclerAdapter(CharSequence[] items) {
        this.mItems = items;
    }

    public interface OnDialogClickListener extends OnItemClickListener {
        void onDialogClick(CharSequence channel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false);
        return new DialogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DialogItemViewHolder viewHolder = (DialogItemViewHolder) holder;
        final CharSequence channel = mItems[position];
        viewHolder.setContent(channel);
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((OnDialogClickListener) mItemClickListener).onDialogClick(channel);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }

}
