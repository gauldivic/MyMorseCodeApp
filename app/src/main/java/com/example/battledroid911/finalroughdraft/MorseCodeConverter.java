package com.example.battledroid911.finalroughdraft;

/**
 * Created by battledroid911 on 1/18/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity
        .TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

//MorseCodeConverter class - this class takes input from the user and outputs the text and can produce audio and use
//the flash on the device if it has one to produce "flash morse code"
public class MorseCodeConverter extends Fragment
{
    public Button convertButton;
    public EditText inputText;

    StringBuilder sb;
    Context context;
    MorseCodeLibrary mcLib; //unintentional mcdonalds style named variable
    TextView outputText;
    View mainScreen;
    CheckBox audio,light;
    public TwitterLoginButton login_Button;
    public Button sButton;
    public Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        context = getActivity();
        mcLib = new MorseCodeLibrary(context);
        //Fabric.with(this, new TweetComposer());
        mainScreen = inflater.inflate(R.layout.main,container,false);
        convertButton = (Button)mainScreen.findViewById(R.id.inputButton);
        inputText = (EditText)mainScreen.findViewById(R.id.inputEditText);
        outputText = (TextView)mainScreen.findViewById(R.id.resultText);
        audio = (CheckBox)mainScreen.findViewById(R.id.audioCheckBox);
        light = (CheckBox)mainScreen.findViewById(R.id.flashCheckBox);
        sButton = (Button)mainScreen.findViewById(R.id.shareButton);
        b = (Button)mainScreen.findViewById(R.id.customLogin);
        login_Button = (TwitterLoginButton)
                mainScreen.findViewById(R.id.login_button);

        login_Button.setCallback(new Callback<TwitterSession>()
        {

            @Override
            public void success(Result<TwitterSession> result)
            {
                Log.i("OUTPUT", "WORKING");
                // Do something with result, which provides a
                // TwitterSession for making API calls
                //loginButton.setEnabled(false);
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                //login_Button.setVisibility(View.GONE);
               // sButton.setVisibility(View.VISIBLE);


            }

            @Override
            public void failure(TwitterException exception)
            {
                Log.i("OUTPUT","ERROR THING");
                // Do something on failure
                Toast.makeText(context,"Input cannot be empty!",Toast.LENGTH_LONG).show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                login_Button.performClick();
            }
        });

        if(!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            light.setEnabled(false);
        }

        convertButton.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    b.setVisibility(View.VISIBLE);
                    //loginButton.setVisibility(View.GONE);
                    hideSoftKeyboard();
                    sb = new StringBuilder(inputText.getText());

                    if(!mcLib.isNullOrBlank(sb.toString()))
                    {
                        Log.i("TAG", sb.substring(0, 1));
                        String tempString = mcLib.convertText(sb.toString(),0);
                        Log.i("OUTPUT",mcLib.convertText(sb.toString(),0));
                        Log.i("OUTPUT",String.valueOf(mcLib.getKeyIndexTable().size()));

                        Log.i("CHECK","Light checked: "+light.isChecked()+" Audio checked: "+audio.isChecked());

                        outputText.setText(tempString);

                        if(light.isChecked())
                        {
                            mcLib.playLetter(tempString,"light");
                        }
                        if(audio.isChecked())
                        {
                            mcLib.playLetter(tempString,"audio");
                        }

                        return true;
                    }
                    else
                    {
                        Toast.makeText(context,"Input cannot be empty!",Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });
        sButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TweetComposer.Builder builder = new TweetComposer.Builder(context)
                        .text(outputText.getText().toString());
                builder.show();
                //login_Button.setVisibility(View.GONE);

            }
        });



        return mainScreen;

    }



    //this hides the soft-keyboard
    public void hideSoftKeyboard()
    {
        Activity activity = getActivity();
        //InputMethodManager inputMethodManager = ()

        if(activity.getCurrentFocus() != null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
        }
    }

}






