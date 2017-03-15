package nyc.c4q.jonathancolon.inContaq.contactlist.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.db.chart.animation.Animation;
import com.db.chart.view.LineChartView;

import org.parceler.Parcels;

import java.util.ArrayList;

import nyc.c4q.jonathancolon.inContaq.R;
import nyc.c4q.jonathancolon.inContaq.contactlist.Contact;
import nyc.c4q.jonathancolon.inContaq.contactlist.activities.ContactListActivity;
import nyc.c4q.jonathancolon.inContaq.utlities.graphs.DailyGraph;
import nyc.c4q.jonathancolon.inContaq.utlities.graphs.MonthlyGraph;
import nyc.c4q.jonathancolon.inContaq.utlities.sms.Sms;
import nyc.c4q.jonathancolon.inContaq.utlities.sms.SmsHelper;


public class ContactStatsFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private LineChartView lineGraph;
    private Spinner dateSpinner;
    private ArrayAdapter<CharSequence> spinnerArrayAdapter;
    private Button monthlyBtn, weeklyBtn, dailyBtn;
    private ArrayList<Sms> lstSms;

    public static ContactStatsFragment newInstance() {
        ContactStatsFragment fragment = new ContactStatsFragment();
        Bundle b = new Bundle();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_stats, container, false);

        initViews(view);
        Contact contact = unwrapParcelledContact();
        lstSms = SmsHelper.getAllSms(getActivity(), contact);

        if (savedInstanceState == null) {
            showMonthlyGraphFragment();
        }

        return view;
    }

    private void showMonthlyGraphFragment() {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.graph_frag_container, new MonthlyGraphFragment())
                .commit();
    }

    private void showDailyGraphFragment() {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.graph_frag_container, new DailyGraphFragment())
                .commit();
    }

    private void initViews(View view) {
                dateSpinner = (Spinner) view.findViewById(R.id.date_spinner);
        spinnerArrayAdapter = ArrayAdapter.createFromResource(
                view.getContext(),
                R.array.date_spinner_array,
                R.layout.date_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.date_spinner_dropdown_item);
        dateSpinner.setAdapter(spinnerArrayAdapter);
        dateSpinner.setOnItemSelectedListener(this);
        monthlyBtn = (Button) view.findViewById(R.id.monthly_btn);
        weeklyBtn = (Button) view.findViewById(R.id.weekly_btn);
        dailyBtn = (Button) view.findViewById(R.id.daily_btn);
        monthlyBtn.setOnClickListener(this);
        weeklyBtn.setOnClickListener(this);
        dailyBtn.setOnClickListener(this);
    }

    @Nullable
    private Contact unwrapParcelledContact() {
        return Parcels.unwrap(getActivity().getIntent().getParcelableExtra(ContactListActivity.PARCELLED_CONTACT));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Contact contact = unwrapParcelledContact();
        switch (String.valueOf(parent.getItemAtPosition(position))) {
            case "WEEKLY":
                // TODO: 3/8/17 if last sent text == to 7 days + last sent text date then, notification
//                mContactNotification = new ContactNotification();
//                mContactNotification.startNotification(getContext(), contact);
                break;
            case "2 WEEKS":
                // TODO: 3/8/17 if last sent text == to 14 days + last sent text date then, notification
                break;
            case "3 WEEKS":
                // TODO: 3/8/17 if last sent text == to 21 days + last sent text date then, notification
                break;
            case "MONTHLY":
                // TODO: 3/8/17 if last sent text == to 30 days + last sent text date then, notification
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {

        DailyGraph dailyGraph = new DailyGraph(getContext(), lineGraph, lstSms);
        MonthlyGraph monthlyGraph = new MonthlyGraph(getContext(), lineGraph, lstSms);

        LineChartView dailyLcv = dailyGraph.getLineGraph();
        LineChartView monthlyLcv = monthlyGraph.getLineGraph();

        switch (v.getId()) {
            case R.id.monthly_btn:
                showMonthlyGraphFragment();
                break;
            case R.id.weekly_btn:
                break;
            case R.id.daily_btn:
                showDailyGraphFragment();
                break;
            default:
                monthlyGraph.showMonthlyGraph();
                break;
        }
    }
    private void lineGraphUpdate() {
        lineGraph.reset();
        lineGraph.invalidate();
        lineGraph.notifyDataUpdate();
    }

}
