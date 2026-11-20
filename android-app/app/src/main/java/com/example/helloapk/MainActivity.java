package com.example.helloapk;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 极简示例：整个界面用代码搭出来，不依赖任何第三方库，
 * 因此构建时只需要 Android SDK 本身，云端构建非常稳定。
 */
public class MainActivity extends Activity {

    private int count = 0;
    private TextView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(48, 48, 48, 48);
        root.setBackgroundColor(Color.parseColor("#0F1419"));

        TextView title = new TextView(this);
        title.setText("Hello, Android!");
        title.setTextSize(28);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);

        TextView hint = new TextView(this);
        hint.setText("这个 APK 由 GitHub Actions 在云端构建");
        hint.setTextSize(14);
        hint.setTextColor(Color.parseColor("#8B98A9"));
        hint.setGravity(Gravity.CENTER);
        hint.setPadding(0, 16, 0, 36);

        counterView = new TextView(this);
        counterView.setText("点击次数：0");
        counterView.setTextSize(20);
        counterView.setTextColor(Color.parseColor("#4ADE80"));
        counterView.setGravity(Gravity.CENTER);
        counterView.setPadding(0, 0, 0, 24);

        Button button = new Button(this);
        button.setText("点我 +1");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counterView.setText("点击次数：" + count);
            }
        });

        root.addView(title);
        root.addView(hint);
        root.addView(counterView);
        root.addView(button);

        setContentView(root);
    }
}
