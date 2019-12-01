package ir.airport.testweather;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class YekanTextview extends AppCompatTextView {
    public YekanTextview(Context context) {
        super(context);
    }

    public YekanTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface Yekan=Typeface.createFromAsset(context.getAssets(),"BYekan.ttf");
        this.setTypeface(Yekan);
    }
}
