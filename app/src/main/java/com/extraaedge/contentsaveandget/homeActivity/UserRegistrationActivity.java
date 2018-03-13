package com.extraaedge.contentsaveandget.homeActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.extraaedge.contentsaveandget.R;
import com.extraaedge.contentsaveandget.utils.AppContentProvider;
import com.extraaedge.contentsaveandget.utils.DatabaseContentHelper;

/**
 * <b>UserRegistrationActivity</b>
 * <p>This Class is used to save data in sqlite database</p>
 * created by Subodh Kumar
 */
public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UserRegistrationActivit";
    private Context mContext;
    private EditText mEtFullName, mEtCityName, mEtEmail;
    private Button mBtSubmit, mBtGetData;
    private DatabaseContentHelper mContentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: UserRegistrationActivity");
        mContext = UserRegistrationActivity.this;
        getSupportActionBar().setTitle(R.string.user_registration);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initializeViews();
    }

    /*
    * This Method is Created for initialize Views of UserRegistrationActivity
    * */
    private void initializeViews() {

        mEtFullName = findViewById(R.id.etFullName);
        mEtCityName = findViewById(R.id.etCityName);
        mEtEmail = findViewById(R.id.etEmail);
        mBtSubmit = findViewById(R.id.btSubmit);
        mBtGetData = findViewById(R.id.btGetData);

        mBtSubmit.setOnClickListener(this);
        mBtGetData.setOnClickListener(this);

    }

    /**
     * Add new User In Sqlite Database.
     */
    private void insertUserData() {

        mContentHelper = new DatabaseContentHelper(mContext);
        mContentHelper.getWritableDatabase();
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(DatabaseContentHelper.PERSON_COLUMN_FULL_NAME, mEtFullName.getText().toString());
        values.put(DatabaseContentHelper.PERSON_COLUMN_CITY, mEtCityName.getText().toString());
        values.put(DatabaseContentHelper.PERSON_COLUMN_EMAIL, mEtEmail.getText().toString());

        Uri uri = getContentResolver().insert(AppContentProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();

        mEtEmail.setText("");
        mEtFullName.setText("");
        mEtCityName.setText("");
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btSubmit:
                insertUserData();
                break;

            case R.id.btGetData:
                startActivity(new Intent(mContext, GetDataUserActivity.class));
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
