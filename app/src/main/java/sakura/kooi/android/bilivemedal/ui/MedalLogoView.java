package sakura.kooi.android.bilivemedal.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import sakura.kooi.android.bilivemedal.R;

public class MedalLogoView extends FrameLayout {
    private TextView label;
    private TextView level;
    private LinearLayout layout;
    public MedalLogoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public MedalLogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MedalLogoView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_medal_logo, this);
        label = findViewById(R.id.textLabel);
        level = findViewById(R.id.textLevel);
        layout = findViewById(R.id.layoutBackground);
    }

    public void update(String name, int color, int level) {
        this.label.setText(name);
        this.level.setText(String.valueOf(level));
        int r = (color >> 16) & 0xff;
        int g = (color >>  8) & 0xff;
        int b = (color      ) & 0xff;
        int argb = (255 & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
        layout.setBackgroundColor(argb);
        this.level.setTextColor(argb);
    }
}
