package com.company.laeventa.fragments;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.company.laeventa.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {


    Context context;

    ProgressBar progressStat;
    GraphView graph;

    LineGraphSeries<DataPoint> series;

    int pStatus = 0;
    private Handler handler = new Handler();
    TextView progressStatText;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context  = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);;
        progressStat =  view.findViewById(R.id.circular_progress_track);
        progressStatText = view.findViewById(R.id.circular_progress_track_textview);

        graph = view.findViewById(R.id.graph);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setGraph();
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular_progress_track);
        progressStat.setProgress(0);   // Main Progress
        progressStat.setSecondaryProgress(100); // Secondary Progress
        progressStat.setMax(100); // Maximum Progress
        progressStat.setProgressDrawable(drawable);

        ObjectAnimator animation = ObjectAnimator.ofInt(progressStat, "progress", 0, 80);
        animation.setDuration(2000);
        animation.setInterpolator(new DecelerateInterpolator());

        animation.start();


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 80) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            progressStat.setProgress(pStatus);
                            progressStatText.setText(pStatus + "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(8); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void setGraph() {

        graph.setTitle("weekly views");

        series = new LineGraphSeries();
        series.appendData(new DataPoint(1,3),true,20);
        series.appendData(new DataPoint(2,10),true,20);
        series.appendData(new DataPoint(3,14),true,20);
        series.appendData(new DataPoint(4,5),true,20);
        series.appendData(new DataPoint(5,10),true,20);
        series.appendData(new DataPoint(6,7),true,20);
        series.appendData(new DataPoint(7,18),true,20);
        series.appendData(new DataPoint(8,23),true,20);
        series.appendData(new DataPoint(9,26),true,20);
        series.appendData(new DataPoint(10,32),true,20);
        graph.addSeries(series);
    }
}
