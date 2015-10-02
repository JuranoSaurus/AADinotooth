package gif.dino.dinotooth;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EduFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EduFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EduFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //list view
    View addMessage;
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EduFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EduFragment newInstance(String param1, String param2) {
        EduFragment fragment = new EduFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EduFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private class ViewHolder {
        public ImageView mIcon;
        public TextView mText;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        addMessage = inflater.inflate(R.layout.fragment_edu, container, false);

        mListView = (ListView) addMessage.findViewById(R.id.listview_edu);

        mAdapter = new ListViewAdapter(getActivity());
        mListView.setAdapter(mAdapter);

        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_1), "이를 닦아요");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_2), "뽀로로 어린이 양치 캠페인");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_3), "2080 키즈 치카치카 가이드");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_4), "2080 로보카폴리 치카치카 가이드");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_5), "비카 오큐걸의 올바른 칫솔질");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_6), "코코몽과 양치하기");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_7), "아기공룡둘리 이닦기노래");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_8), "디보와 함께하는 이닦기 노래");
        mAdapter.addItem(getResources().getDrawable(R.mipmap.img_edu_9), "아이 챌린지 치카치카");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ListData mData = mAdapter.mListData.get(position);
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=edBJm6NdIiU"));
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=3blrxTRsprk"));
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=E2bDNc8jPlc"));
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=KDmwfT4Qxe8"));
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Sd-dV8i2SiE\n"));
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8dJNFelsbb8"));
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bLe_Cz0wZOM"));
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=IDa4STocM8M"));
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=U-oUaXJU2P0&index=2&list=PLfaheItvoU6NPQ75-gjteIm7G2loqglJz"));
                        startActivity(intent);
                        break;

                }
            }
        });

        return addMessage;
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();

        public ListViewAdapter(Context mContext) {
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.edu_listview, null);

                holder.mIcon = (ImageView) convertView.findViewById(R.id.mImage);
                holder.mText = (TextView) convertView.findViewById(R.id.mText);


                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);

            if (mData.mIcon != null) {
                holder.mIcon.setVisibility(View.VISIBLE);
                holder.mIcon.setImageDrawable(mData.mIcon);
            }else{
                holder.mIcon.setVisibility(View.GONE);
            }

            holder.mText.setText(mData.mTitle);


            return convertView;
        }

        public void addItem(Drawable icon, String mTitle){
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.mIcon = icon;
            addInfo.mTitle = mTitle;

            mListData.add(addInfo);
        }

        public void remove(int position){
            mListData.remove(position);
            dataChange();
        }

        public void sort(){
            Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
            dataChange();
        }

        public void dataChange(){
            mAdapter.notifyDataSetChanged();
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }




}
