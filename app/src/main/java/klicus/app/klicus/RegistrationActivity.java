package klicus.app.klicus;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText txtname, txtemail, txtid;
    Button btnCreateUser;
    String name = "", email = "";
    Long id;
    FirebaseDatabase db;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtemail = (EditText) findViewById(R.id.txtemail);
        txtname = (EditText) findViewById(R.id.txtname);
        txtid = (EditText) findViewById(R.id.txtid);
       // txtusername = (TextInputEditText) findViewById(R.id.txtusername);
       // txtpass = (TextInputEditText) findViewById(R.id.txtpass);
       // txtpassconf = (TextInputEditText) findViewById(R.id.txtpassconf);
        btnCreateUser = (Button) findViewById(R.id.btnCreateUser);


        db =  FirebaseDatabase.getInstance("https://klicus-4b8a7.firebaseio.com");

        users = db.getReference("USERS");


        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtname.getText().toString();
                email = txtemail.getText().toString();
                try{
                    id = Long.parseLong(txtid.getText().toString().trim());
                }catch(NumberFormatException ex){
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.emptytext), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }

                validateData(id, name, email, users);
                name =""; email="";
            }
        });
    }

    public void validateData(Long id, String name, String email, DatabaseReference users){
        if(id!=0 && name.length() != 0  && email.length() != 0){
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){
               // if(pass.equalsIgnoreCase(passconf)){
                    dialog(id, name, email, users);
               /* } else{
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.passdifer), MDToast.LENGTH_SHORT, MDToast.TYPE_WARNING);
                    mdToast.show();
                }*/
            } else{
                MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.error_email), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                mdToast.show();
            }

        } else{
            MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.emptytext), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
            mdToast.show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void dialog(final Long ID, final String name, final String email, final DatabaseReference users){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setTitle(getResources().getString(R.string.title_dialog));
        builder.setMessage(getResources().getString(R.string.message_dialog));
        builder.setIcon(R.drawable.ic_dialog);
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.success), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                mdToast.show();
                users.push().setValue(new User(ID, name, email));
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
