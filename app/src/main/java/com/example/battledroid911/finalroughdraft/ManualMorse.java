package com.example.battledroid911.finalroughdraft;

/**
 * Created by battledroid911 on 5/3/15.
 */


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ManualMorse extends Fragment
{
    Context context;
    public Button switchButton;
    private Camera camera;
    private View flashLight;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        context = container.getContext();
        PackageManager packageManager = context.getPackageManager();
        flashLight = inflater.inflate(R.layout.manual_morse,container,false);
        switchButton = (Button)flashLight.findViewById(R.id.lightButton);

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {

            switchButton.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent)
                {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        flashLightOn(view);
                        switchButton.setText("ON");
                        return true;
                    }
                    else if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                    {
                        flashLightOff(view);
                        switchButton.setText("OFF");
                        return true;
                    }
                    return false;
                }
            });

        }
        else
        {
            switchButton.setText("Nope");
        }
        return flashLight;

    }

    // taken from
    //http://stackoverflow.com/questions/6068803/how-to-turn-on-camera-flash-light-programmatically-in-android
    public void flashLightOff(View view)
    {
        try
        {
            if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
            {
                camera.stopPreview();
                camera.release();
                camera = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // taken from
    //http://stackoverflow.com/questions/6068803/how-to-turn-on-camera-flash-light-programmatically-in-android
    public void flashLightOn(View view)
    {
        try
        {
            if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
            {
                camera = Camera.open();
                Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}














