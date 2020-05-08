package com.example.mycalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Button btnAdd;
    List<Memo> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memoList=new ArrayList<>();
        memoList.add(new Memo("분리수거하기","2020-05-06",1));

        recyclerView=findViewById(R.id.recyclerview);

        //리사이클러뷰는 리니어레이아웃매니저를 사용해야함
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter=new RecyclerAdapter(memoList);
        recyclerView.setAdapter(recyclerAdapter);
        btnAdd=findViewById(R.id.btnAdd);

        //버튼에 onClick리스너를 달아서 버튼 클릭시 addActivity
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //새로운 메모작성
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== 0){
            String strMain=data.getStringExtra("main");
            String strSub=data.getStringExtra("sub");

            Memo memo=new Memo(strMain,strSub,0);
            recyclerAdapter.addItem(memo);
            recyclerAdapter.notifyDataSetChanged();

        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
        private List<Memo> listdata;

        public RecyclerAdapter(List<Memo> listdata){
            this.listdata=listdata;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
            return new ItemViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }


        @Override
        //onBindViewHolder는 데이터를 레이아웃에 어떻게 넣어줄지를 정함.
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            Memo memo=listdata.get(i);

            itemViewHolder.maintext.setText(memo.getMaintext());

            itemViewHolder.subtext.setText(memo.getSubtext());

            //imageview는 0일때와 1일때의 색상이 다름,
            if(memo.getIsdone()==0){ //0일때는 회색
                itemViewHolder.img.setBackgroundColor(Color.LTGRAY);
            }
            else{ //1일때는 녹색
                itemViewHolder.img.setBackgroundColor(Color.GREEN);
            }
        }

        //리스트추가
        void addItem(Memo memo){
            listdata.add(memo);
        }

        //리스트 삭제
        void removeItem(int position){
            listdata.remove(position);
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            private TextView maintext;
            private TextView subtext;
            private ImageView img;

            public ItemViewHolder(@NonNull View itemView){
                super(itemView);

                maintext=itemView.findViewById(R.id.item_maintext);
                subtext=itemView.findViewById(R.id.item_subtext);

                //subtext=itemView.findViewById(R.id.textView_date);
                img=itemView.findViewById(R.id.item_image);


            }
        }
    }
}



