package com.fourksoft.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.fourksoft.test.model.DataModel;
import com.fourksoft.test.view.ChartView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ChartView chart;
    private DataModel dataA, dataB;

    private int dataChangeInterval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        dataA = generateDataModel();
        dataB = generateDataModel();

        chart = new ChartView(this, dataA);
        setContentView(chart);


        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDataModel();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nextDataModel();
                    }
                });
            }
        }, dataChangeInterval, dataChangeInterval);
    }

    private DataModel generateDataModel() {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < new Random().nextInt(20) + 5; i++) {
            data.add(new Random().nextInt(100));
        }

        return new DataModel(data, Color.DKGRAY);
    }

    private void nextDataModel() {
        if (chart.getCurrentDataModel().equals(dataA))
            chart.setDataModel(dataB);
        else chart.setDataModel(dataA);
    }
}
