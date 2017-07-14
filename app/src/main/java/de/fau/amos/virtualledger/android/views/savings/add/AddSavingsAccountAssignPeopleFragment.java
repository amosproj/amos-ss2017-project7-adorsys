package de.fau.amos.virtualledger.android.views.savings.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.model.SavingsAccount;

public class AddSavingsAccountAssignPeopleFragment extends AddSavingsAccountPage {
    @SuppressWarnings("unused")
    private static final String TAG = AddSavingsAccountAssignPeopleFragment.class.getSimpleName();
    private PeopleAssignedListener peopleAssignetListener;
    private SavingsAccount dataModel;

    @Override
    public void consumeDataModel(SavingsAccount account) {
        this.dataModel = account;
        if (this.peopleAssignetListener != null){
            this.peopleAssignetListener.updateText();
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.saving_accounts_add_friends, container, false);
        ButterKnife.bind(this, view);

        ListView peopleList = (ListView) view.findViewById(R.id.list);
        ArrayList<String> exampleList = new ArrayList<>();
        exampleList.add("Me");
        exampleList.add("Donald Duck");
        exampleList.add("Dagorbert Duck");


        TextView conclusionTextView = (TextView) view.findViewById(R.id.conclusion_text);

        this.peopleAssignetListener = new PeopleAssignedListener(conclusionTextView, conclusionTextView.getText().toString(), this.dataModel);
        peopleList.setAdapter(new PeopleAdapter(getActivity(), R.id.list, exampleList, peopleAssignetListener));
        return view;
    }


    @Override
    public void fillInData(final SavingsAccount savingsAccount) {

    }
}