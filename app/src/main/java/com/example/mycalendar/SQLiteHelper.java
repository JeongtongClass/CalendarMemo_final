package com.example.mycalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper {

    private static final String dbName="myMemotest1";
    private static final String table1="MemoTable";
    private static final int dbVersion=1;

    //db관련객체
    private OpenHelper opener;
    private SQLiteDatabase db;
    private Context context;

    public SQLiteHelper(Context context) {
        this.context = context;
        this.opener=new OpenHelper(context,dbName,null,dbVersion);
        db=opener.getWritableDatabase();
    }


    private class OpenHelper extends SQLiteOpenHelper{

        public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //onCreate에서 DB생성 , 생성된 DB가 없을때 한번만 호출됨.
            String create="CREATE TABLE "+table1+"("+
                    "seq integer PRIMARY KEY AUTOINCREMENT, "+
                    "maintext text,"+
                    "subtext text,"+
                    "timetext text,"+
                    "isdone integer)";

            sqLiteDatabase.execSQL(create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ table1);
            onCreate(sqLiteDatabase);
        }

        @Override
        public void close(){
            db.close();
        }
    }

    //INSERT INTO MemoTable VALUES(NULL,"MAINTEXT","SUBTEXT",0,"TimeTEXT"); (NULL,'"+memo.maintext+"','"+memo.subtext+"',"+memo.getIsdone()+");";
    public void insertMemo(Memo memo){
        String sql="INSERT INTO "+table1+" VALUES(NULL,'"+memo.maintext+"','"+memo.subtext+"','"+memo.timetext+"',"+memo.getIsdone()+");";
        db.execSQL(sql);
    }

    //데이터삭제 DELETE FROM Memo Table WHERE seq=0; =>MemoTable의 0번쨰 지우기
    public  void deleteMemo(int position){
        String sql="DELETE FROM "+table1+" WHERE seq="+position+";";
        db.execSQL(sql);
    }

    //데이터조회 SELECT * FROM MemoTable;
    public ArrayList<Memo> selectAll(){

        String sql="SELECT * FROM "+table1;
        ArrayList<Memo> list=new ArrayList<>();

        Cursor results=db.rawQuery(sql,null);
        //db=this.getReadableDatabase();

        results.moveToFirst();

        while(!results.isAfterLast()){  //  목록순서(seq) ,maintext, subtext, timetext, isdone
            /*Log.d("d", Integer.toString(results.getInt(0)));
            Log.d("d1", results.getString(1));
            Log.d("d2", results.getString(2));
            Log.d("d3", results.getString(3)); // time
            Log.d("d4", Integer.toString(results.getInt(4))); // done*/
            Memo memo=new Memo(results.getInt(0), results.getString(1), results.getString(2), results.getString(3), results.getInt(4));
            list.add(memo);
            results.moveToNext();
        }
        results.close();
        return list;
    }
}
