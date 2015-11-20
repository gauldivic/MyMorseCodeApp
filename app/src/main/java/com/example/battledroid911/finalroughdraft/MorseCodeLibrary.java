package com.example.battledroid911.finalroughdraft;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import java.util.Hashtable;

/**
 * Created by battledroid911 on 1/18/15.
 */

public class MorseCodeLibrary
{
    private int letterValue; //the integer for the keyIndexTable
    public int styleType; // The integer for the style type
    private StringBuilder debugString;
    private StringBuilder inputString;
    private StringBuilder outputString;
    private String characterString;
    private Hashtable keyIndexTable;
    private String[][] valueArray; // The array that holds the two different styles of output, depending on the styleType value
    private Hashtable letterTable;
    private boolean keyExists;
    Context context;
    private Camera camera;
    //public int inputStringLength;


    //constructor for the MorseCodeLibrary that takes in A 'Context' from another window
    public MorseCodeLibrary(Context mainWindow)
    {
        context = mainWindow;
        keyIndexTable = new Hashtable();
        keyIndexTable.put("A",0);
        keyIndexTable.put("B",1);
        keyIndexTable.put("C",2);
        keyIndexTable.put("D",3);
        keyIndexTable.put("E",4);
        keyIndexTable.put("F",5);
        keyIndexTable.put("G",6);
        keyIndexTable.put("H",7);
        keyIndexTable.put("I",8);
        keyIndexTable.put("J",9);
        keyIndexTable.put("K",10);
        keyIndexTable.put("L",11);
        keyIndexTable.put("M",12);
        keyIndexTable.put("N",13);
        keyIndexTable.put("O",14);
        keyIndexTable.put("P",15);
        keyIndexTable.put("Q",16);
        keyIndexTable.put("R",17);
        keyIndexTable.put("S",18);
        keyIndexTable.put("T",19);
        keyIndexTable.put("U",20);
        keyIndexTable.put("V",21);
        keyIndexTable.put("W",22);
        keyIndexTable.put("X",23);
        keyIndexTable.put("Y",24);
        keyIndexTable.put("Z",25);
        keyIndexTable.put("0",26);
        keyIndexTable.put("1",27);
        keyIndexTable.put("2",28);
        keyIndexTable.put("3",29);
        keyIndexTable.put("4",30);
        keyIndexTable.put("5",31);
        keyIndexTable.put("6",32);
        keyIndexTable.put("7",33);
        keyIndexTable.put("8",34);
        keyIndexTable.put("9",35);
        keyIndexTable.put(".",36);
        keyIndexTable.put(",",37);
        keyIndexTable.put("?",38);
        keyIndexTable.put("'",39);
        keyIndexTable.put("!",40);
        keyIndexTable.put("/",41);
        keyIndexTable.put("(",42);
        keyIndexTable.put(")",43);
        keyIndexTable.put("&",44);
        keyIndexTable.put(":",45);
        keyIndexTable.put(";",46);
        keyIndexTable.put("=",47);
        keyIndexTable.put("+",48);
        keyIndexTable.put("-",49);
        keyIndexTable.put("_",50);
        keyIndexTable.put("\"",51);
        keyIndexTable.put("$",52);
        keyIndexTable.put("@",53);

        letterTable = new Hashtable();
        letterTable.put(0,"A");
        letterTable.put(1,"B");
        letterTable.put(2,"C");
        letterTable.put(3,"D");
        letterTable.put(4,"E");
        letterTable.put(5,"F");
        letterTable.put(6,"G");
        letterTable.put(7,"H");
        letterTable.put(8,"I");
        letterTable.put(9,"J");
        letterTable.put(10,"K");
        letterTable.put(11,"L");
        letterTable.put(12,"M");
        letterTable.put(13,"N");
        letterTable.put(14,"O");
        letterTable.put(15,"P");
        letterTable.put(16,"Q");
        letterTable.put(17,"R");
        letterTable.put(18,"S");
        letterTable.put(19,"T");
        letterTable.put(20,"U");
        letterTable.put(21,"V");
        letterTable.put(22,"W");
        letterTable.put(23,"X");
        letterTable.put(24,"Y");
        letterTable.put(25,"Z");
        letterTable.put(26,"0");
        letterTable.put(27,"1");
        letterTable.put(28,"2");
        letterTable.put(29,"3");
        letterTable.put(30,"4");
        letterTable.put(31,"5");
        letterTable.put(32,"6");
        letterTable.put(33,"7");
        letterTable.put(34,"8");
        letterTable.put(35,"9");
        letterTable.put(36,".");
        letterTable.put(37,",");
        letterTable.put(38,"?");
        letterTable.put(39,"'");
        letterTable.put(40,"!");
        letterTable.put(41,"/");
        letterTable.put(42,"(");
        letterTable.put(43,")");
        letterTable.put(44,"&");
        letterTable.put(45,":");
        letterTable.put(46,";");
        letterTable.put(47,"=");
        letterTable.put(48,"+");
        letterTable.put(49,"-");
        letterTable.put(50,"_");
        letterTable.put(51,"\"");
        letterTable.put(52,"$");
        letterTable.put(53,"@");

        valueArray = new String[][]
        {
            {".-","dah-dit"},//A
            {"-...","dah-di-di-dit"},//B
            {"-.-.","dah-di-dah-dit"},//C
            {"-..","dah-di-dit"},//D
            {".","dit"},//E
            {"..-.","di-di-dah-dit"},//F
            {"--.","dah-dah-dit"},//G
            {"....","di-di-di-dit"},//H
            {"..","di-dit"},//I
            {".---","di-dah-dah-dah"},//J
            {"-.-","dah-di-dah"},//K
            {".-..","dah-di-dah-dah"},//L
            {"--","dah-dah" },//M
            {"-.","dah-dit"},//N
            {"---","dah-dah-dah"},//O
            {".--.","di-dah-dah-dit" },//P
            {"--.-","dah-dah-di-dah"},//Q
            {".-.","di-dah-dit"},//R
            {"...","di-di-dit"},//S
            {"-","dah"},//T
            {"..-","di-di-dah"},//U
            {"...-","di-di-di-dah"},//V
            {".--","di-dah-dah" },//W
            {"-..-","dah-di-di-dah"},//X
            {"-.--","dah-di-dah-dah"},//Y
            {"--..","dah-dah-di-dit"},//Z
            {"-----","dah-dah-dah-dah-dah"},//0
            {".----","di-dah-dah-dah-dah"},//1
            {"..---","di-di-dah-dah-dah"},//2
            {"...--","di-di-di-dah-dah"},//3
            {"....-","di-di-di-di-dah"},//4
            {".....","di-di-di-di-dit"},//5
            {"-....","dah-di-di-di-dit"},//6
            {"--...","dah-dah-di-di-dit"},//7
            {"---..","dah-dah-dah-di-dit"},//8
            {"----.","dah-dah-dah-dah-dit"},//9
            {".-.-.-","di-dah-di-dah-di-dah"},//"."
            {"--..--","dah-dah-di-di-dah-dah"},//","
            {"..--..","di-di-dah-dah-di-dit" },//"?"
            {".----.","di-dah-dah-dah-dah-dit"},//"'"
            {"-.-.--","dah-di-dah-di-dah-dah"},//"!"
            {"-..-.","dah-di-di-dah-dit"},//"/"
            {"-.--.","dah-di-dah-dah-dit"},//"("
            {"-.--.-","dah-di-dah-dah-di-dah"},//")"
            {".-...","di-dah-di-di-dit"},//"&"
            {"---...","dah-dah-dah-di-di-dit"},//":"
            {"-.-.-.","dah-di-dah-di-dah-dit"},//";"
            {"-...-","dah-di-di-di-dah" },//"="
            {".-.-.","di-dah-di-dah-dit" },//"+"
            {"-....-","dah-di-di-di-di-dah" },//"-"
            {"..--.-","di-di-dah-dah-di-dah" },//"_"
            {".-..-.","di-dah-di-di-dah-dit" },// "
            {"...-..-","di-di-di-dah-di-di-dah" },//"$"
            {".--.-.","di-dah-dah-di-dah-dit"}//"@"
        };
    }

    public String convertText(String inputText,int sType)
    {
        inputString = new StringBuilder(inputText);
        outputString = new StringBuilder();
        debugString = new StringBuilder();

        styleType = sType;

        int inputStringLength = inputString.length();

        for (int i = 0; i < inputStringLength; i++)
        {
            characterString = inputString.substring(i,i+1).toUpperCase();
            if(keyCheck(characterString) || isNullOrBlank(characterString))
            {
                if(!isNullOrBlank(characterString))
                {
                    int keyValue = getKey(characterString);
                    debugString.append("\nThe index of: "+characterString+" is: "+keyValue);
                    debugString.append("\nThe morsecode value of: "+characterString+" is: "+getSequence(keyValue,styleType));
                    debugString.append("\n----------------------------");

                    if(i+1 != inputStringLength && isNullOrBlank(inputString.substring(i+1,i+2)) == true)
                    {
                        outputString.append(getSequence(keyValue,styleType)).append(" ");
                    }
                    else
                    {
                        if(i+1 == inputStringLength)
                        {
                            debugString.append("\nEnd of String");
                            debugString.append("\n-------------------------------");
                            outputString.append(getSequence(keyValue,styleType)).append(" ");
                        }
                        else
                        {
                            outputString.append(getSequence(keyValue,styleType)).append("/");
                        }
                    }
                }
                else
                {
                    debugString.append("\nBlank Space");
                    debugString.append("\n---------------------------------");
                }
            }
            else if(keyCheck(characterString) == false)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Invalid Character!");

                StringBuilder invalidString = new StringBuilder();
                invalidString.append(inputString + " contains \"" + characterString + "\" which is not a valid character!");
                alertDialogBuilder.setMessage(invalidString.toString());
                outputString = new StringBuilder(invalidString.toString());

                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                       dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
        Log.i("DEBUG STRING",debugString.toString());
        return outputString.toString();
    }

    private boolean keyCheck(String charString)
    {
        if(keyIndexTable.containsKey(charString))
        {
            keyExists = true;
        }
        else if(!keyIndexTable.containsKey(charString))
        {
            keyExists = false;
        }
        return keyExists;
    }

    private int getKey(String value)
    {
        letterValue = (Integer)keyIndexTable.get(value);
        return letterValue;
    }

    private String getSequence(int letter, int style)
    {
        return valueArray[letter][style];
    }

    //http://alvinalexander.com/blog/post/java/java-method-test-string-null-or-blank
    public static boolean isNullOrBlank(String iString)
    {
        return (iString == null || iString.trim().equals(""));
    }

    public Hashtable getLetterTable()
    {
       return letterTable;
    }

    public Hashtable getKeyIndexTable()
    {
        return keyIndexTable;
    }

    public String[][] getValueArray()
    {
        return valueArray;
    }

    //This function controls the calling of the audio and flash elements of the program
    //I use the calculations for the period, period seconds from wikipedia
    //http://en.wikipedia.org/wiki/Morse_code
    public void playLetter(String charSequence, String tempOutputType)
    {
        String outputType = tempOutputType;

        String tempSeq = charSequence;
        double wpm = 5;
        final double period = 1200 / wpm;
        final double periodSec = period / 1000;
        Log.i("test", "Period is: " + period);
        Log.i("test", "period in seconds:" + period / 1000);
        int tempSequenceLength = tempSeq.length();
        String[] seqArray = new String[tempSequenceLength];
        int sequenceLength = seqArray.length;
        for (int i = 0; i < tempSequenceLength; i++)
        {
            seqArray[i] = String.valueOf(tempSeq.charAt(i));
        }
        for (int i = 0; i < sequenceLength; i++)
        {
            Log.i("test", "char at seqArray[" + i + "}: " + seqArray[i]);
        }
        for (int i = 0; i < sequenceLength; i++)
        {
            if (seqArray[i].equals("."))
            {
                Log.i("test", "I am a: " + seqArray[i]);
                if (outputType.equals("audio"))
                {
                    beep(periodSec, 200);
                    SystemClock.sleep((long) period);
                }
                if (outputType.equals("light"))
                {
                    flashLightOn();
                    SystemClock.sleep((long) period);
                    flashLightOff();
                    SystemClock.sleep((long) period);
                }
            }
            else if (seqArray[i].equals("-"))
            {
                Log.i("test", "I am a: " + seqArray[i]);
                if (outputType.equals("audio"))
                {
                    beep(periodSec * 3, 200);
                    SystemClock.sleep((long) period * 3);
                }
                if (outputType.equals("light"))
                {
                    flashLightOn();
                    SystemClock.sleep((long) period * 3);
                    flashLightOff();
                    SystemClock.sleep((long) period);
                }
            }
            else if (seqArray[i].equals(" "))
            {
                Log.i("test", "I am a: " + seqArray[i]);
                SystemClock.sleep((long) period * 7);
            }
        }
    }

    // code from http://stackoverflow.com/questions/2413426/playing-an-arbitrary-tone-with-android
    //modified more by me by putting in a method so that frequency can be played for a period Inputted and at a frequency that is Inputted so that
    //it can be used in creation of morse code audio
    public void beep(double _period,double _frequency)
    {
        double period = _period;//the length of the beep in seconds, will be in milliseconds
        double frequency = _frequency; //the frequency of the beep

        int sampleRate = 8000;
        double dnumSamples = period * sampleRate;
        dnumSamples = Math.ceil(dnumSamples);
        int numSamples = (int)dnumSamples;
        double sample[] = new double[numSamples];
        byte generatedSnd[] = new byte[2 * numSamples];

        for(int i = 0; i < numSamples; i++)
        {
            sample[i] = Math.sin(frequency * 2 * Math.PI * i / (sampleRate));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalized
        int idx = 0;
        int i = 0;

        int ramp = numSamples / 20;  // Amplitude ramp as a percent of sample count

        for (i = 0; i< ramp; ++i)
        {                                     // Ramp amplitude up (to avoid clicks)
            double dVal = sample[i];
            // Ramp up to maximum
            final short val = (short) ((dVal * 32767 * i/ramp));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        for (i = i; i< numSamples - ramp; ++i)
        {                        // Max amplitude for most of the samples
            double dVal = sample[i];
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        for (i = i; i< numSamples; ++i)
        {                               // Ramp amplitude down
            double dVal = sample[i];
            // Ramp down to zero
            final short val = (short) ((dVal * 32767 * (numSamples-i)/ramp ));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        AudioTrack audioTrack = null;                                   // Get audio track
        try
        {
            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, (int)numSamples*2,
                    AudioTrack.MODE_STATIC);
            audioTrack.write(generatedSnd, 0, generatedSnd.length);     // Load the track
            audioTrack.play();                                          // Play the track
        }
        catch (Exception e)
        {
            Log.e("Error: ",e.toString());
        }

        int x =0;
        do
        {                                                     // Montior playback to find when done
            if (audioTrack != null)
            {
                x = audioTrack.getPlaybackHeadPosition();
            }
            else
            {
                x = numSamples;
            }
        }while (x<numSamples);

        if (audioTrack != null) audioTrack.release();           // Track play done. Release track.
    }

    //turns the flash off
    public void flashLightOff()
    {
        try
        {
            if (context.getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH))
            {
                camera.stopPreview();
                camera.release();
                camera = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // Toast.makeText(getBaseContext(), "Exception flashLightOff",
            //Toast.LENGTH_SHORT).show();
        }
    }

    //this function turns the camera flash on
    //http://stackoverflow.com/questions/6068803/how-to-turn-on-camera-flash-light-programmatically-in-android
    public void flashLightOn()
    {

        try
        {
            if (context.getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH))
            {
                camera = Camera.open();
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
                camera.startPreview();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
//            Toast.makeText(getBaseContext(), "Exception flashLightOn()",
//                    Toast.LENGTH_SHORT).show();
        }
    }


}











