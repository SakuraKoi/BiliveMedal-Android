package sakura.kooi.android.bilivemedal.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;

public class MedalInfoView extends FrameLayout {
    private MedalLogoView medalLogoView;
    private TextView guardLabel;
    private TextView streamerLabel;
    private TextView pointLabel;
    private TextView todayLabel;
    private TextView maxLabel;
    private TextView timeLabel;
    public MedalInfoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public MedalInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MedalInfoView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_medal_info, this);
        medalLogoView = findViewById(R.id.medalLogo);
        guardLabel = findViewById(R.id.textGuard);
        streamerLabel = findViewById(R.id.textStreamer);
        pointLabel = findViewById(R.id.textPoint);
        todayLabel = findViewById(R.id.textToday);
        maxLabel = findViewById(R.id.textMax);
        timeLabel = findViewById(R.id.textTime);
    }

    public void update(MedalEntity.DataBean.UserBean.MedalBean medal) {
        medalLogoView.update(medal.medalName, medal.medalColor, medal.level);
        guardLabel.setText(toGuardLevelString(medal.guardType));
        streamerLabel.setText(medal.targetName);
        pointLabel.setText(String.valueOf(medal.intimacy));
        todayLabel.setText(String.valueOf(medal.todayIntimacy));
        maxLabel.setText(String.valueOf(medal.dayLimit));
        timeLabel.setText(medal.receiveTime);
    }

    private String toGuardLevelString(int guardLevel) {
        if (guardLevel == 0) return "";
        if (guardLevel == 1) return "总督";
        if (guardLevel == 2) return "提督";
        if (guardLevel == 3) return "舰长";
        return "未知";
    }
}
