package kevinlamcs.android.com.mapsdemo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import kevinlamcs.android.com.mapsdemo.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();
    protected abstract void attachFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (firstTimeCreated(savedInstanceState)) attachFragment();
    }

    private boolean firstTimeCreated(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }

    public void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
