package com.gaiso.gamerobot.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gaiso.gamerobot.R;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class DialogItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mContent;

    public DialogItemViewHolder(View itemView) {
        super(itemView);
        mContent = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public void setContent(CharSequence content) {
        mContent.setText(content);
    }
}
