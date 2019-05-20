package klicus.app.klicus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdesekamdem.library.mdtoast.MDToast;

import klicus.app.klicus.commons.CommonUtil;
import klicus.app.klicus.data.Services;

import static klicus.app.klicus.commons.CommonConstants.db;

public class FragmentRegist extends Fragment {

    EditText txtname, txtemail, txtid;
    Button btnCreateUser;
    String name = "", email = "";
    Long id = 0L;

    DatabaseReference users;

    public static FragmentRegist newInstance() {
        FragmentRegist fragment = new FragmentRegist();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtil.setTitleSubtitle(((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity().getApplicationContext(), getString(R.string.nav_signin));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_regist, container, false);

        txtemail = view.findViewById(R.id.txtemail);
        txtname =  view.findViewById(R.id.txtname);
        txtid = view.findViewById(R.id.txtid);

        btnCreateUser = view.findViewById(R.id.btnCreateUser);

        users = db.getReference("USERS");


        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    id = Long.parseLong(txtid.getText().toString().trim());
                    name = txtname.getText().toString();
                    email = txtemail.getText().toString();
                }catch(Exception ex){
                    MDToast mdToast = MDToast.makeText(getContext().getApplicationContext(), getString(R.string.emptytext), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }

                CommonUtil.validateData(getContext(), id, name, email, users);
                name =""; email="";
            }
        });

        CommonUtil.setTitleSubtitle(((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity().getApplicationContext(), getString(R.string.nav_signin));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().getActionBar()!=null) {
            MainActivity activity = (MainActivity) getActivity();
            activity.getSupportActionBar().setTitle(getString(R.string.app_name));
            activity.getSupportActionBar().setSubtitle(getString(R.string.nav_signin));
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    public void onBackPressed() {
        Activity activity = getActivity();

        if (activity != null) {
            activity.onBackPressed();
        }
    }
}
