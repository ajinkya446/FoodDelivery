package com.example.bmcatering.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.bmcatering.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String db_name="BMC.sqlite";
    private static final int db_ver=1;
    SQLiteDatabase db;
    SQLiteQueryBuilder qb;

    public Database(Context context) {
        super(context, db_name,null, db_ver);
    }


    public List<Order> getCarts(){
        db= getReadableDatabase();
       qb= new SQLiteQueryBuilder();

        String[] sqlSelect={"ProductName","ProductId","Quantity","Price","Discount"};
        String sqlTable="OrderDetail";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                result.add(new Order(cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount"))
                ));
            }while (cursor.moveToNext());
        }
        return result;

    }


    public void addToCart(Order order)
    {
        SQLiteDatabase db= getReadableDatabase();
        String query= String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }


    public void cleanCart()
    {
        SQLiteDatabase db= getReadableDatabase();
        String query= String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }


}
