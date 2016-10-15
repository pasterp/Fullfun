package full.fullfun.vues.adapteurs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import full.fullfun.R;

/**
 * Created by 1634836 on 15/10/2016.
 */

public class ToastCustom {

    public ToastCustom(String message, Context context, LayoutInflater inflater, View idToast){

        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) idToast);

        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(context);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
