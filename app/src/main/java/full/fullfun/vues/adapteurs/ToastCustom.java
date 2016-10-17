package full.fullfun.vues.adapteurs;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import full.fullfun.R;

import static android.R.id.message;


public class ToastCustom {

    public ToastCustom(Activity activity, int cas){
        //Definition du contexte, de l'inflater, du conteneur du toast et du toast.
        Context context = activity.getApplicationContext();
        LayoutInflater inflater = activity.getLayoutInflater();
        View idToast = activity.findViewById(R.id.toast_id);
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) idToast);

        //instantiation des variables
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        ImageView image  = (ImageView) layout.findViewById(R.id.toast_ico);

        // TODO: 15/10/2016 Passer chaque cas en public static pour plus de propreté et compréhension.
        switch (cas) {
            case 0:
                text.setText("It works !");
                image.setImageResource(R.drawable.ic_flash_on_black_24dp);
                break;
            case 1:
                text.setText("Selectionner au moins un set !");
                image.setImageResource(R.drawable.ic_error_outline_black_24dp);
                break;
            case 2:
                text.setText("Selectionner au moins un joueur !");
                image.setImageResource(R.drawable.ic_error_outline_black_24dp);
                break;
            case 3:
                text.setText("Commencement de la partie !");
                image.setImageResource(R.drawable.ic_flash_on_black_24dp);
                break;
            default:
                break;
        }


        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
