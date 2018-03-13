package com.extraaedge.contentsaveandget.adapter;
import static java.util.Collections.addAll;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extraaedge.contentsaveandget.R;
import com.extraaedge.contentsaveandget.structure.UserListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>UserAdapter</b>
 * <p>This Adapter class used for setText of User Data in a list</p>
 * created by Subodh Kumar
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<UserListItem> userListItemArrayList;
    private LayoutInflater mLayoutInflater;

    public UserAdapter(final Context mContext, final ArrayList<UserListItem> userListItemArrayList) {
        this.mContext = mContext;
        this.userListItemArrayList = userListItemArrayList;
        mLayoutInflater = (LayoutInflater) this.mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

    }
    public void setData(ArrayList<UserListItem> data) {
        data.clear();
        if (data != null) {
            addAll(data);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_user_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.fullName.setText(userListItemArrayList.get(position).getName());
        holder.city.setText(userListItemArrayList.get(position).getCity());
        holder.email.setText(userListItemArrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userListItemArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fullName, city, email;

        public MyViewHolder(final View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.tvFullName);
            city = itemView.findViewById(R.id.tvCityName);
            email = itemView.findViewById(R.id.tvEmailId);
        }
    }
}
