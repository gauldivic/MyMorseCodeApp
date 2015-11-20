package com.example.battledroid911.finalroughdraft;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Hashtable;

/**
 * Created by battledroid911 on 4/29/15.
 */
public class ReferenceTable extends Fragment
{
    private TableLayout refTable;
    private TextView symbolText;
    private TextView symbolRepresentation;
    public ImageView speakerImg;
    private View referenceTableView;
    protected Hashtable letterTable;
    private String[][] valueArray;
    private String letterValue;
    private String characterString;
    MorseCodeLibrary mcLib;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        context = container.getContext();
        mcLib = new MorseCodeLibrary(context);
        referenceTableView = inflater.inflate(R.layout.reference_table,container,false);

        refTable = (TableLayout)referenceTableView.findViewById(R.id.referenceTable);
        letterTable = mcLib.getLetterTable();

        valueArray  = mcLib.getValueArray();

        int letterTableSize = letterTable.size();

        for(int i = 0;i<letterTableSize;i++)
        {
            TableRow row = (TableRow)inflater.inflate(R.layout.row_layout,null);
            speakerImg = (ImageView)row.findViewById(R.id.speakerIcon);

            symbolText = (TextView)row.findViewById(R.id.symbol);
            symbolRepresentation = (TextView)row.findViewById(R.id.symbol_representation);
            letterValue = (String)letterTable.get(i);
            characterString = valueArray[i][0];
            symbolText.setText(letterValue);
            symbolRepresentation.setText(characterString);

            row.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    TextView clickedTextView = (TextView)view.findViewById(R.id.symbol_representation);
                    String symbolText = (String)clickedTextView.getText();
                    Log.i("SYMBOL",symbolText);
                    mcLib.playLetter(symbolText,"audio");
                }
            });
            refTable.addView(row);
        }

        return referenceTableView;

    }

}







