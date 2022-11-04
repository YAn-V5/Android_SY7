package com.example.android_sy7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACTS = "create table Contacts("
            +"id integer primary key autoincrement, "
            + "name text, "
            + "tel text, "
            + "sex text)";
    private Context mContext;
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS);
        Toast.makeText(mContext, "Create table succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        switch (i1) {
            case 6:
                ContentValues values = new ContentValues();

                values.put("name", "HU");
                values.put("tel", "987654321");
                values.put("sex", "男");
                db.insert("Contacts", null, values);
                values.clear();
                Log.d("运行","数据插入成功");
//                Log.d("运行",ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//                values.put("author", "祝国强");
//                values.put("price", 46.7);
//                values.put("pages", 360);
//                values.put("name", "医药数理统计方法");
//                values.put("category_id", 2);
//                db.insert("Book", null, values);
//                values.clear();
        }

    }
}
