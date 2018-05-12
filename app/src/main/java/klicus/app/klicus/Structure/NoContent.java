package klicus.app.klicus.Structure;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import klicus.app.klicus.MainActivity;
import klicus.app.klicus.R;

public class NoContent extends Fragment {

    private OnFragmentInteractionListener mListener;

    public NoContent() {

    }

    public static NoContent newInstance() {
        NoContent fragment = new NoContent();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_content, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().setTitle(getString(R.string.app_name));
        activity.getSupportActionBar().setSubtitle(getString(R.string.nocontent_title));
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onBackPressed() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
