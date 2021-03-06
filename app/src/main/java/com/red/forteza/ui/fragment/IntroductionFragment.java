package com.red.forteza.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.red.forteza.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroductionFragment extends BaseFragment {

    @BindView(R.id.title_intro)
    TextView introTitle;
    @BindView(R.id.content_intro)
    TextView paragraph1;

    public static IntroductionFragment newInstance(String title, String text) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("text", text);
        IntroductionFragment fragment = new IntroductionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_intro;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        introTitle.setText(getArguments().getString("title"));
        paragraph1.setText(Html.fromHtml(getArguments().getString("text")));

    }
}
