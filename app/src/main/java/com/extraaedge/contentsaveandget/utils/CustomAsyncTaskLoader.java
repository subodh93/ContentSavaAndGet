package com.extraaedge.contentsaveandget.utils;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.extraaedge.contentsaveandget.adapter.UserAdapter;
import com.extraaedge.contentsaveandget.structure.UserListItem;

import java.util.ArrayList;

/**
 * Created by Deepak_extra_edge on 3/12/2018.
 */

public class CustomAsyncTaskLoader extends AsyncTaskLoader{

    private static final String TAG = "CustomAsyncTaskLoader";
    private ArrayList<UserListItem> userListItemArrayList;
    private UserAdapter mUserAdapter;
    private Context mContext;
    
    public CustomAsyncTaskLoader(Context mContext, ArrayList<UserListItem> userListItemArrayList) {
        super(mContext);
        this.mContext= mContext;
        this.userListItemArrayList=userListItemArrayList;
        Log.d(TAG, "CustomAsyncTaskLoader: ");
    }

    @Override
    public ArrayList<UserListItem> loadInBackground() {

        DatabaseContentHelper contentHelper= new DatabaseContentHelper(mContext);
        contentHelper.getWritableDatabase();
        contentHelper.getUserData();
        return userListItemArrayList;
    }

}
