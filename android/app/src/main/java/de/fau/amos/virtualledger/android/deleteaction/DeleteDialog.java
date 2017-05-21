package de.fau.amos.virtualledger.android.deleteaction;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;

import de.fau.amos.virtualledger.android.functions.Function;
import de.fau.amos.virtualledger.android.functions.Consumer;

/**
 * Created by sebastian on 21.05.17.
 */

public class DeleteDialog<T> {

    private ListFragment listenedObject;
    private T listenedModel;
    private Function<T, String> getName;
    private Consumer<T> approvedAction;

    public DeleteDialog(ListFragment listenedObject, T listenedModel, Function<T, String> getName, Consumer<T> approvedAction) {
        this.listenedObject = listenedObject;
        this.listenedModel = listenedModel;
        this.getName = getName;
        this.approvedAction = approvedAction;
    }

    public void show() {
        AlertDialog.Builder alert = new AlertDialog.Builder(listenedObject.getActivity());
        alert.setTitle("DELETE CONFIRMATION");
        alert.setMessage("Are you sure to delete " + this.getName.apply(listenedModel));
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                approvedAction.accept(listenedModel);

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alert.show();

    }
}