package styleandfont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

//import com.bashir.kutob.R;


/**
 * Created by Bassam on 10/14/2014.
 */public class CustomButtona extends Button {

    public CustomButtona(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomButtona(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);

    }

    public CustomButtona(Context context)
    {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs)
    {
        if (attrs!=null)
        {
            // TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomeFont);

            String fontName = "arbia.TTF";
            // if (Locale.getDefault().getDisplayLanguage().toString().equals("English"))
            //  //   fontName = a.getString(R.styleable.CustomeFont_DroidSansArabic);
            // else
            // fontName = a.getString(R.styleable.CustomeFont_DroidSansArabic);



            if (fontName!=null)
            {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(),"chang.ttf");
                setTypeface(myTypeface);
            }
            // a.recycle();
        }
    }
}
