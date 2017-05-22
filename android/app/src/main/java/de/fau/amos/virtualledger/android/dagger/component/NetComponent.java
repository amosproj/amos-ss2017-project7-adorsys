package de.fau.amos.virtualledger.android.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import de.fau.amos.virtualledger.android.ExpandableList.Fragment.ExpandableBankFragment;
import de.fau.amos.virtualledger.android.LoginActivity;
import de.fau.amos.virtualledger.android.MainActivity_Menu;
import de.fau.amos.virtualledger.android.RegisterActivity;
import de.fau.amos.virtualledger.android.dagger.module.AppModule;
import de.fau.amos.virtualledger.android.dagger.module.AuthenticationModule;
import de.fau.amos.virtualledger.android.dagger.module.BankingModule;
import de.fau.amos.virtualledger.android.dagger.module.NetModule;
import de.fau.amos.virtualledger.android.deleteaction.DeleteBankAccessAction;
import de.fau.amos.virtualledger.android.deleteaction.DeleteBankAccountAction;

/**
 * Created by Simon on 07.05.2017. taken from https://adityaladwa.wordpress.com/2016/05/09/
 * dagger-2-with-retrofit-and-okhttp-and-gson/)
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class, AuthenticationModule.class, BankingModule.class})
public interface NetComponent {

    /**
     *
     * @param activity
     */
    void inject(RegisterActivity activity);
    void inject(MainActivity_Menu activity);
    void inject(LoginActivity activity);
    void inject(ExpandableBankFragment expandableBankFragment);
}
