package kr.ac.hansung.bababob;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.ac.hansung.bababob.SchoolCafeteria.SchoolCafeteria;
import kr.ac.hansung.bababob.SchoolCafeteria.SchoolCafeteriaAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    private static TimelineFragment instance;
    private DatabaseReference mPostReference;
    private RecyclerView rvReview;
    private TimelineAdapter adapter;
    private FirebaseAuth mAuth;

    public TimelineFragment() {
        // Required empty public constructor
        //
    }

    public static TimelineFragment getInstance(){
        if(instance == null)
            instance = new TimelineFragment();
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

        mPostReference = FirebaseDatabase.getInstance().getReference("Review");
        //mCommentReference = FirebaseDatabase.getInstance().getReference("Comments");
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        rvReview = (RecyclerView) view.findViewById(R.id.timeline_recycler_view);
        adapter = new TimelineAdapter(getActivity(), "");
        rvReview.setAdapter(adapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        rvReview.setItemAnimator(itemAnimator);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
