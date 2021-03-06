package com.red.forteza.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.red.forteza.R;
import com.red.forteza.data.model.OriginItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvolutionActivity extends BaseActivity {

    @BindView(R.id.paragraph1)
    TextView paragraph1;
    @BindView(R.id.paragraph2)
    TextView paragraph2;

    OriginItem mOriginItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackNavigation();
        ButterKnife.bind(this, setContent(R.layout.activity_evolution));

        Bundle extras = getIntent().getExtras();
        mOriginItem = extras.getParcelable(OriginItem.ARG);
        setTitle(mOriginItem.section);

        paragraph1.setText(Html.fromHtml(mOriginItem.paragraphs.get(0).text));
        paragraph2.setText(Html.fromHtml(mOriginItem.paragraphs.get(1).text));

    }
}
