package klicus.app.klicus.commons;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.User;

public class CommonUtil {

    public static void setTitleSubtitle(ActionBar a, Context c, String subtitle){
        try{
            if(!a.getSubtitle().toString().equalsIgnoreCase(subtitle) || a.getSubtitle().toString().isEmpty()){
                a.setTitle(R.string.app_name);
                a.setSubtitle(subtitle);
            }
        }catch(Exception e){
            Toast.makeText(c.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public static void validateData(Context c, Long id, String name, String email, DatabaseReference users){

        if(id!=0 && name.length() != 0  && email.length() != 0){
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){
                dialog(c, c.getResources(), id, name, email, users);
            } else{
                MDToast mdToast = MDToast.makeText(c.getApplicationContext(), c.getResources().getString(R.string.error_email), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                mdToast.show();
            }

        } else{
            MDToast mdToast = MDToast.makeText(c.getApplicationContext(), c.getResources().getString(R.string.emptytext), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
            mdToast.show();
        }
    }

    private static void dialog(final Context t, final Resources r, final Long ID, final String name, final String email, final DatabaseReference users){
        AlertDialog.Builder builder = new AlertDialog.Builder(t);
        builder.setTitle(r.getString(R.string.title_dialog));
        builder.setMessage(r.getString(R.string.message_dialog));
        builder.setIcon(R.drawable.ic_dialog);
        builder.setPositiveButton(r.getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                MDToast mdToast = MDToast.makeText(t.getApplicationContext(), r.getString(R.string.success), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                mdToast.show();
                users.push().setValue(new User(ID, name, email));
            }
        });
        builder.setNegativeButton(r.getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
