package com.clakestudio.pc.everyday.showdays;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.adddays.AddDayActivity;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.settings.SettingsActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowDaysFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowDaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowDaysFragment extends Fragment implements ShowDaysContract.View {

    //

    private ShowDaysContract.Presenter daysPresenter;

    private ShowDaysAdapter showDaysAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShowDaysFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowDaysFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowDaysFragment newInstance() {
        return new ShowDaysFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_days, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);


        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<Day> days = new ArrayList<>();

        DayItemListener dayItemListener = new DayItemListener() {
            @Override
            public void onDayClicked(Day day) {
                daysPresenter.editCurrentDay(day);
            }
        };

        showDaysAdapter = new ShowDaysAdapter(days, dayItemListener);
        recyclerView.setAdapter(showDaysAdapter);

        daysPresenter.start();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }
    */

    /**
     * Methods from Contract interface
     */


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(@NonNull ShowDaysContract.Presenter presenter) {
        daysPresenter = presenter;
    }


    @Override
    public void showDays() {
        AsyncShowDays asyncShowDays = new AsyncShowDays(showDaysAdapter);
        asyncShowDays.execute(daysPresenter);
    }

    @Override
    public void showAddNewDay(int dayId) {
        Intent intent = new Intent(getContext(), AddDayActivity.class);
        intent.putExtra("dayId", dayId);
        startActivity(intent);

    }

    @Override
    public void showEditCurrentDay(int dayId, String title, String note) {
        Intent intent = new Intent(getContext(), AddDayActivity.class);
        intent.putExtra("dayId", dayId);
        intent.putExtra("title", title);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    @Override
    public void showSettingsActivity() {
        startActivity(new Intent(getContext(), SettingsActivity.class));
        getActivity().finish();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class ShowDaysAdapter extends RecyclerView.Adapter<ShowDaysAdapter.ShowDaysViewHolder> {

        private ArrayList<Day> days;
        private DayItemListener dayItemListener;

        class ShowDaysViewHolder extends RecyclerView.ViewHolder {

            private TextView tvTitle;
            private TextView tvNote;
            private CardView cvDay;
            private TextView tvDayInfo;


            ShowDaysViewHolder(View itemView) {
                super(itemView);

                tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
                tvNote = (TextView) itemView.findViewById(R.id.tvNote);
                tvDayInfo = (TextView) itemView.findViewById(R.id.tvDayInfo);
                cvDay = (CardView) itemView.findViewById(R.id.day);

            }
        }

        public ShowDaysAdapter(ArrayList<Day> days, DayItemListener dayItemListener) {
            this.days = days;
            this.dayItemListener = dayItemListener;
        }


        public ArrayList<Day> getDays() {
            return days;
        }

        public void setDays(ArrayList<Day> days) {
            this.days = days;
        }

        public void replaceData(ArrayList<Day> days) {
            setDays(days);
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public ShowDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day, parent, false);
            final ShowDaysViewHolder showDaysViewHolder = new ShowDaysViewHolder(view);

            showDaysViewHolder.cvDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // click only on the last item
                   // if (showDaysViewHolder.getAdapterPosition() == days.size())
                    dayItemListener.onDayClicked(days.get(showDaysViewHolder.getAdapterPosition()));
                }
            });

            return showDaysViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ShowDaysViewHolder holder, int position) {
            holder.tvTitle.setText(days.get(position).getTitle());
            holder.tvNote.setText(days.get(position).getNote());
            holder.tvDayInfo.setText("Day " + days.get(position).getDayId() + " / " + days.get(position).getDate());
        }

        @Override
        public int getItemCount() {
            return days.size();
        }
    }

    public interface DayItemListener {

        void onDayClicked(Day day);

    }

}


class AsyncShowDays extends AsyncTask<ShowDaysContract.Presenter, Void, List<Day>> {

    private ShowDaysFragment.ShowDaysAdapter showDaysAdapter;

    AsyncShowDays(ShowDaysFragment.ShowDaysAdapter showDaysAdapter) {
        this.showDaysAdapter = showDaysAdapter;
    }


    @Override
    protected List<Day> doInBackground(ShowDaysContract.Presenter... presenter) {
        return presenter[0].getDays();
    }

    @Override
    protected void onPostExecute(List<Day> days) {
        super.onPostExecute(days);
        showDaysAdapter.replaceData((ArrayList<Day>) days);
    }
}