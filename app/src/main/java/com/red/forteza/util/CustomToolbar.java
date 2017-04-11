package com.red.forteza.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.red.forteza.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomToolbar extends FrameLayout {
    private static Paint sPaint;
    @BindView(R.id.layout_toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.container_content)
    FrameLayout contentContainer;
    @BindView(R.id.toolbar_navigation)
    ImageView mNavigation;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.toolbar_logo)
    ImageView mLogo;
    @BindView(R.id.toolbar_actions)
    LinearLayout mActions;


    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.content_toolbar, this, true);
        ButterKnife.bind(this, this);
        setLayerType(LAYER_TYPE_SOFTWARE, sPaint);
        sPaint = new Paint();
        sPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        sPaint.setStyle(Paint.Style.FILL);
        sPaint.setShadowLayer(Res.dp(3), 0, Res.dp(3), context.getResources().getColor(R.color.shadow));
        sPaint.setAntiAlias(true);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight() + ((MarginLayoutParams) getLayoutParams()).bottomMargin, sPaint);
        super.dispatchDraw(canvas);
    }

    public void setTint(int colorResource) {
        int color = Res.color(colorResource);

        mTitle.setTextColor(color);
        mNavigation.setColorFilter(color);
        for (int i = 0; i < mActions.getChildCount(); i++) {
            ((ImageView) mActions.getChildAt(i)).setColorFilter(color);
        }
    }

    public void setNavigation(int resource, OnClickListener listener) {
        ((MarginLayoutParams) mTitle.getLayoutParams()).leftMargin = (int) Res.dp(72);
        mNavigation.setImageResource(resource);
        mNavigation.setOnClickListener(listener);
        mNavigation.setVisibility(View.VISIBLE);
    }

    public void removeNavigation() {
        ((MarginLayoutParams) mTitle.getLayoutParams()).leftMargin = (int) Res.dp(16);
        mNavigation.setVisibility(View.GONE);
        mNavigation.setImageResource(0);
        mNavigation.setOnClickListener(null);
    }

    public void setTitle(String title) {
        if (title != null) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(title);
            mLogo.setVisibility(View.GONE);
        } else {
            mTitle.setVisibility(View.GONE);
            mLogo.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(int stringResID) {
        setTitle(getResources().getString(stringResID));
    }

    public void setActions(Action... actions) {
        setActions(Arrays.asList(actions));
    }

    public void setActions(List<Action> actions) {
        mActions.removeAllViews();
        if (actions != null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            for (Action action : actions) {
                View view = inflater.inflate(R.layout.toolbar_action, mActions, false);
                new ActionView(view).bind(action);
                mActions.addView(view);
            }
        }
        invalidate();
    }

    public View setContent(int layoutResource) {
        mToolbar.setVisibility(View.GONE);
        View content = LayoutInflater.from(getContext()).inflate(layoutResource, contentContainer, false);
        contentContainer.addView(content);
        return content;
    }

    public void setContentVisible(boolean visible) {
        contentContainer.setVisibility(visible ? VISIBLE : GONE);
        mToolbar.setVisibility(visible ? GONE : VISIBLE);
    }

    public static class ActionView {
        View mView;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.label_date)
        TextView text;

        public ActionView(View view) {
            ButterKnife.bind(this, view);
            mView = view;
        }

        public void bind(Action action) {
            if (action.iconRes != 0) {
                icon.setImageResource(action.iconRes);
                icon.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
            } else {
                text.setText(action.text);
                text.setVisibility(View.VISIBLE);
                icon.setVisibility(View.GONE);
            }
            mView.setOnClickListener(action);
        }
    }

    public abstract static class Action implements OnClickListener {
        private String text = null;
        private int iconRes = 0;

        public Action(String text) {
            this.text = text;
        }

        public Action(int resId) {
            iconRes = resId;
        }
    }
}
