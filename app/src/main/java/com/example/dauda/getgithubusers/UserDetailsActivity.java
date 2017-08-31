package com.example.dauda.getgithubusers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UserDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ImageView profileImage = (ImageView) findViewById(R.id.profileImage);
        TextView userNameTextView = (TextView) findViewById(R.id.profileName);
        Button shareProfile = (Button) findViewById(R.id.shareButton);
        TextView userUrl = (TextView) findViewById(R.id.profileUrl);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra(UserAdapter.KEY_NAME);
        String image = intent.getStringExtra(UserAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(UserAdapter.KEY_URL);


        Picasso.with(this)
                .load(image)
                .into(profileImage);


        userNameTextView.setText(userName);
        userUrl.setText(profileUrl);


        userUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer " + userName +
                        ", " + profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }
}
