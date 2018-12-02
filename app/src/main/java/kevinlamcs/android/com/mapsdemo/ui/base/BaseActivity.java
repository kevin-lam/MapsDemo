package kevinlamcs.android.com.mapsdemo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {

    protected abstract int getLayoutId();
    protected abstract V getViewModel();
    protected abstract void subscribeToViewModelChanges();

    private V viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        viewModel = getViewModel();
        subscribeToViewModelChanges();
    }

    @Override
    protected void onDestroy() {
        unSubscribeToViewModelChanges();
        super.onDestroy();
    }

    private void unSubscribeToViewModelChanges() {
        viewModel.clearDisposable();
    }
}
