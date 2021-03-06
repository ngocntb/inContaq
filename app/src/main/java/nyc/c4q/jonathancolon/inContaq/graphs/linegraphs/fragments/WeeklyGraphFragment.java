package nyc.c4q.jonathancolon.inContaq.graphs.linegraphs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.view.LineChartView;

import org.parceler.Parcels;

import java.util.ArrayList;

import nyc.c4q.jonathancolon.inContaq.R;
import nyc.c4q.jonathancolon.inContaq.graphs.linegraphs.WeeklyGraph;
import nyc.c4q.jonathancolon.inContaq.sms.model.Sms;


public class WeeklyGraphFragment extends Fragment {


    private LineChartView lineGraph;
    private ArrayList<Sms> smsList;

    public WeeklyGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        lineGraph = (LineChartView) view.findViewById(R.id.daily_chart);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            smsList = Parcels.unwrap(bundle.getParcelable("smslist"));
        }
        showWeeklyGraph();

        return view;
    }

    private void showWeeklyGraph() {
        WeeklyGraph weeklyGraph = new WeeklyGraph(getContext(), lineGraph, smsList);
        weeklyGraph.showWeeklyGraph();
    }

}

