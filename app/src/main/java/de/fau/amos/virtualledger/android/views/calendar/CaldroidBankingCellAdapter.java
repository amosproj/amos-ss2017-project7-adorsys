package de.fau.amos.virtualledger.android.views.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.Locale;
import java.util.Map;

import de.fau.amos.virtualledger.R;
import hirondelle.date4j.DateTime;

/**
 * Created by Georg on 10.06.2017.
 */

public class CaldroidBankingCellAdapter extends CaldroidGridAdapter {

    TextView dateTextView;
    TextView amountDeltaTextView;
    TextView amountTextView;

    View cellView;

    /**
     * Constructor
     *
     * @param context
     * @param month
     * @param year
     * @param caldroidData
     * @param extraData
     */
    public CaldroidBankingCellAdapter(Context context, int month, int year, Map<String, Object> caldroidData, Map<String, Object> extraData) {
        super(context, month, year, caldroidData, extraData);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cellView = convertView;

        //TODO get real data
        double amountDelta = 100.00;
        double amount = 500.00;

        // load custom cell
        if (convertView == null) {
            cellView = inflater.inflate(R.layout.calendar_view_cell, null);
        }
        dateTextView = (TextView) cellView.findViewById(R.id.calendar_view_cell_date);
        amountDeltaTextView = (TextView) cellView.findViewById(R.id.calendar_view_cell_amount_delta);
        amountTextView = (TextView) cellView.findViewById(R.id.calendar_view_cell_amount);

        styleDefault();
        changeAmountDeltaTextColor(amountDelta);
        changeAmountBackgroundColor(amount);

        DateTime dateTime = this.datetimeList.get(position);

        // Set color of the dates in previous / next month
        if (dateTime.getMonth() != month) {
            styleDayOutOfMonth();
        }

        // Set color of today
        if(dateTime.equals(getToday())) {
            styleToday();
        }

        dateTextView.setText("" + dateTime.getDay());
        amountTextView.setText(getFormatedDouble(amount));
        setAmountDeltaText(amountDelta);

        // Set custom color if required
        setCustomResources(dateTime, cellView, dateTextView);

        return cellView;
    }

    private void styleDefault() {
        dateTextView.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void styleToday() {
        dateTextView.setBackgroundColor(Color.GRAY);
    }

    private void setAmountDeltaText(double amountDelta) {
        String amountDeltaText = getFormatedDouble(amountDelta);
        if (amountDelta < 0) {
            amountDeltaText = "-" + amountDeltaText;
        }
        if (amountDelta == 0) {
            amountDeltaText = "";
        }else {
            amountDeltaText = "+" + amountDeltaText;
        }
        amountDeltaTextView.setText(amountDeltaText);
    }

    private void changeAmountBackgroundColor(double amount) {
        if (amount < 0) {
            int redColor = ContextCompat.getColor(cellView.getContext(), R.color.colorNegativeAmount);
            amountTextView.setBackgroundColor(redColor);
        } else if (amount == 0) {
            int blueColor = ContextCompat.getColor(cellView.getContext(), R.color.colorBankingOverview);
            amountTextView.setBackgroundColor(blueColor);
        } else {
            int greenColor = ContextCompat.getColor(cellView.getContext(), R.color.colorBankingOverviewLightGreen);
            amountTextView.setBackgroundColor(greenColor);
        }
    }

    private void changeAmountDeltaTextColor(double amountDelta) {
        if (amountDelta < 0) {
            int redColor = ContextCompat.getColor(cellView.getContext(), R.color.colorNegativeAmount);
            amountDeltaTextView.setTextColor(redColor);
        } else if (amountDelta == 0) {
            int blueColor = ContextCompat.getColor(cellView.getContext(), R.color.colorBankingOverview);
            amountDeltaTextView.setTextColor(blueColor);
        } else {
            int greenColor = ContextCompat.getColor(cellView.getContext(), R.color.colorBankingOverviewLightGreen);
            amountDeltaTextView.setTextColor(greenColor);
        }
    }

    private void styleDayOutOfMonth() {
        dateTextView.setTypeface(Typeface.DEFAULT);
    }

    private String getFormatedDouble(double number) {
        return String.format(Locale.GERMAN, "%.0f", number);
    }
}