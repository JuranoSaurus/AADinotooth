package gif.dino.dinotooth;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    View addMessage;

    //Dialog를 표시하기 위한 Button
    Button btnDatePicker;

    ImageView imgMorningThumb;
    ImageView imgAfternoonThumb;
    ImageView imgDinnerThumb;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StateFragment newInstance(String param1, String param2) {
        StateFragment fragment = new StateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StateFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        addMessage = inflater.inflate(R.layout.fragment_state, container, false);

        btnDatePicker = (Button) addMessage.findViewById(R.id.btnDate);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        imgMorningThumb = (ImageView) addMessage.findViewById(R.id.img_morning_thumb);
        imgAfternoonThumb = (ImageView) addMessage.findViewById(R.id.img_afternoon_thumb);
        imgDinnerThumb = (ImageView) addMessage.findViewById(R.id.img_dinner_thumb);

        setThumbIcon();

        //Dialog에 출력하기 위한 현재 시스템 날짜를 구하여 전역변수에 미리 셋팅한다.
        int[] date = DateTimeHelper.getInstance().getDate();
        DataConstants.YEAR = date[0];
        DataConstants.MONTH = date[1];
        DataConstants.DAY = date[2];

        return addMessage;
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

    /**날짜 선택 팝업을 표시한다.*/
    public void showDatePickerDialog() {
        //원복처리에 사용될 임시값 - 원본 데이터를 백업한다.
        final int temp_yy = DataConstants.YEAR;
        final int temp_mm = DataConstants.MONTH;
        final int temp_dd = DataConstants.DAY;

        //Dialog 객체의 생성 --> "Context, 이벤트 핸들러, 년, 월, 일" 을 파라미터로 전달한다.
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            //Dialog에서 사용자가 날짜를 선택하고 "확인" 버튼을 누르면 동작하는 이벤트
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //사용자의 선택값을 전역변수에 설정한다.
                DataConstants.YEAR = year;
                DataConstants.MONTH = monthOfYear + 1;
                DataConstants.DAY = dayOfMonth;
                //버튼에 날짜출력
                btnDatePicker.setText(DataConstants.YEAR + "년 " + DataConstants.MONTH + "월 " + DataConstants.DAY + "일");
                setThumbIcon();
            }
        }, DataConstants.YEAR, DataConstants.MONTH - 1, DataConstants.DAY);

        /**사용자가 Back키를 눌렀을 때, 동작하는 이벤트 정의*/
        /* --> 구글 표준 API에서는 다이얼로그에 취소버튼이 포함되어 있지 않다.
         *     하지만, 일부 제조사들은 "취소" 버튼을 임의로 넣고 있기 때문에
         *     코드로 "취소" 버튼을 정의하는 것은 적합하지 않다.
         *     그러므로, 창에서 취소처리가 발생하는 경우에 대한 이벤트를 정의한다.
         * --> import android.content.DialogInterface.OnCancelListener;
         */
//        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface arg0) {
//                //백업해 두었던 값을 원복시킨다.
//                DataConstants.YEAR = temp_yy;
//                DataConstants.MONTH = temp_mm;
//                DataConstants.DAY = temp_dd;
//            }
//        });
//

        //Dialog의 화면 표시
        dialog.show();
    }

    public void setThumbIcon(){
        if(DataConstants.MORNING_GRADE == 1){
            imgMorningThumb.setImageResource(R.mipmap.icon_thumb);
        }else{
            imgMorningThumb.setImageResource(R.mipmap.icon_thumb_down);
        }
        if(DataConstants.LUNCH_GRADE == 1){
            imgAfternoonThumb.setImageResource(R.mipmap.icon_thumb);
        }else{
            imgAfternoonThumb.setImageResource(R.mipmap.icon_thumb_down);
        }
        if(DataConstants.DINNER_GRADE == 1){
            imgDinnerThumb.setImageResource(R.mipmap.icon_thumb);
        }else{
            imgDinnerThumb.setImageResource(R.mipmap.icon_thumb_down);
        }
    }
}
