package klicus.app.klicus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main extends AppCompatActivity{

    Button btnMain;
    TextView txtCreateUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnMain = (Button) findViewById(R.id.btnLogin);
        txtCreateUser = (TextView) findViewById(R.id.txtCreateUser);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, MainActivity.class);
                startActivity(intent);

            }
        });

        txtCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

}
