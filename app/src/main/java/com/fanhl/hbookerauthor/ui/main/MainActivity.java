package com.fanhl.hbookerauthor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fanhl.hbookerauthor.R;

public class MainActivity extends AppCompatActivity {
    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
    }

    private void assignViews() {
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_books:
                    mTextMessage.setText(R.string.title_books);
                    return true;
                case R.id.navigation_interaction:
                    mTextMessage.setText(R.string.title_interaction);
                    return true;
                case R.id.navigation_wallet:
                    mTextMessage.setText(R.string.title_interaction);
                    return true;
                case R.id.navigation_author:
                    mTextMessage.setText(R.string.title_interaction);
                    return true;
            }
            return false;
        });
    }

}
