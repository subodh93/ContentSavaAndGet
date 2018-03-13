package com.extraaedge.contentsaveandget.homeActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.extraaedge.contentsaveandget.R;
import com.extraaedge.contentsaveandget.adapter.UserAdapter;
import com.extraaedge.contentsaveandget.structure.UserListItem;
import com.extraaedge.contentsaveandget.utils.AppContentProvider;
import com.extraaedge.contentsaveandget.utils.DatabaseContentHelper;

import java.util.ArrayList;
/**
 * <b>GetDataUserActivity</b>
 * <p>This Activity is used for get data in RecyclerView</p>
 * created by Subodh Kumar
* */
public class GetDataUserActivity extends FragmentActivity implements View.OnClickListener,LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "GetDataUserActivity";
    private Context mContext;
    private RecyclerView mRvUserDataList;
    private ArrayList<UserListItem> userListItemArrayList;
    private UserAdapter mAdapter;
    private DatabaseContentHelper mContentHelper;
    private Button mBtNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_user);
        Log.d(TAG, "onCreate: GetDataUserActivity");
        mContext= GetDataUserActivity.this;

        initializeViews();
        getSupportLoaderManager().initLoader(0, null, this);

    }
    /**
     * This Method is used to initializes views of GetDataUserActivity
     * */
    private void initializeViews() {

        mBtNewUser= findViewById(R.id.btAddNewUser);
        userListItemArrayList= new ArrayList<>();
        mRvUserDataList= findViewById(R.id.rvGetUserDataList);
        mRvUserDataList.setHasFixedSize(true);
        mRvUserDataList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter= new UserAdapter(mContext, userListItemArrayList);
        mRvUserDataList.setAdapter(mAdapter);

        mBtNewUser.setOnClickListener(this);

    }

    /**
     * This Method is used to load DatabaseContentHelper Class method.
     * */
    private void loadUserData() {
        mContentHelper = new DatabaseContentHelper(mContext);
        userListItemArrayList= mContentHelper.getUserData();
        mAdapter= new UserAdapter(mContext, userListItemArrayList);
        mRvUserDataList.setAdapter(mAdapter);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.btAddNewUser:
                startActivity(new Intent(mContext,UserRegistrationActivity.class));
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: ");
        loadUserData();
        Uri uri= AppContentProvider.CONTENT_URI;

        return new CursorLoader(mContext,uri,null,null,null,null);

    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        Log.d(TAG, "onLoadFinished: ");

        mRvUserDataList.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: ");
        mRvUserDataList.setAdapter(null);

    }

}
