package com.example.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.model.UserData;
import com.example.myapplication.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context c;
    private ArrayList<UserData> userList;

    public UserAdapter(Context c, ArrayList<UserData> userList) {
        this.c = c;
        this.userList = userList;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView dataName;
        TextView hostName;
        ImageView mMenus;

        public UserViewHolder(View v) {
            super(v);
            dataName = v.findViewById(R.id.mTitle);
            hostName = v.findViewById(R.id.mSubTitle);
            mMenus = v.findViewById(R.id.mMenus);
            mMenus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupMenus(v);
                }
            });
        }

        private void popupMenus(View v) {
            PopupMenu popupMenus = new PopupMenu(c, v);
            popupMenus.inflate(R.menu.show_menu);
            popupMenus.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if(itemId == R.id.connect){
                    return true;
                }else if (itemId == R.id.disconnect){
                    return true;
                }else{
                    return false;
                }
            });
            popupMenus.show();
            try {
                Field popup = PopupMenu.class.getDeclaredField("mPopup");
                popup.setAccessible(true);
                Object menu = popup.get(popupMenus);
                Class<?> menuClass = menu.getClass();
                Method setForceShowIcon = menuClass.getDeclaredMethod("setForceShowIcon", boolean.class);
                setForceShowIcon.invoke(menu, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserData newList = userList.get(position);
        holder.dataName.setText(newList.getDataName());
        holder.hostName.setText(newList.getHostName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}