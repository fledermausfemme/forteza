package com.red.forteza.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.red.forteza.R;
import com.red.forteza.data.api.LocalApi;
import com.red.forteza.data.model.OriginItem;
import com.red.forteza.data.model.OriginItems;
import com.red.forteza.ui.activity.AnatomyActivity;
import com.red.forteza.ui.activity.EmptyActivity;
import com.red.forteza.ui.activity.EvolutionActivity;
import com.red.forteza.ui.activity.OriginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OriginsFragment extends BaseFragment {

    OriginItems mOriginItems;

    public static OriginsFragment newInstance() {
        return new OriginsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_origins;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mOriginItems = LocalApi.get().getOriginItems();
    }

    @OnClick(R.id.button_origin)
    protected void goToOrigin() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OriginItem.ARG, mOriginItems.data.get(0));
        startActivity(OriginActivity.class, bundle, false);
    }

    @OnClick(R.id.button_evolution)
    protected void goToEvolution() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OriginItem.ARG, mOriginItems.data.get(1));
        startActivity(EvolutionActivity.class, bundle, false);
    }

    @OnClick(R.id.button_anatomy)
    protected void goToAnatomy() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OriginItem.ARG, mOriginItems.data.get(2));
        startActivity(AnatomyActivity.class, bundle, false);
    }

    @OnClick(R.id.button_major_components)
    protected void goToComponents() {
        startActivity(EmptyActivity.class, null, false);
    }
}
