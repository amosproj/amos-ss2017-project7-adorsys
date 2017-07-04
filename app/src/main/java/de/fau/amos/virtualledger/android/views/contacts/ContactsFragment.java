package de.fau.amos.virtualledger.android.views.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.logging.Logger;

import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.dagger.App;
import de.fau.amos.virtualledger.dtos.Contact;
import de.fau.amos.virtualledger.android.views.shared.transactionList.DataListening;
import de.fau.amos.virtualledger.android.views.shared.transactionList.Supplier;

/**
 * Created by Simon on 01.07.2017.
 */

public class ContactsFragment extends Fragment implements DataListening {

    private ContactsAdapter adapter;
    private ListView contactListView;
    private Supplier<Contact> contactSupplier;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        /*((App) getActivity().getApplication()).getNetComponent().inject(this);*/
        super.onActivityCreated(savedInstanceState);

        this.contactSupplier = new ContactsSupplier(getActivity());
        this.contactSupplier.addDataListeningObject(this);
        adapter = new ContactsAdapter(getActivity(), R.id.contacts_list, contactSupplier.getAll());
        contactListView.setAdapter(adapter);
        this.contactSupplier.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_list, container, false);
        this.contactListView = (ListView) view.findViewById(R.id.contacts_list);
        return view;
    }

    @Override
    public void notifyDataChanged() {
        this.adapter.clear();
        List<Contact> allContacts= this.contactSupplier.getAll();
        logger().info("Refreshing contacts overview with " + allContacts.size() + " contacts from"+this.contactSupplier);
        this.adapter.addAll(allContacts);
        this.adapter.notifyDataSetChanged();
    }

    private Logger logger() {
        return Logger.getLogger(this.getClass().getCanonicalName() + "{" + this.toString() + "}");
    }

    @Override
    public void onPause() {
        super.onPause();
        this.contactSupplier.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.contactSupplier.onResume();
    }

}
