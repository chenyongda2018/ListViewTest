package com.example.chen.listviewtest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chen.listviewtest.first.FirstActivity;
import com.example.chen.listviewtest.second.SecondActivity;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<ListContent> listContentList = new LinkedList<>();
    private Context context;
    private ListAdapter adapter;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        //初始化列表内容
        initListContent();
        adapter = new ListAdapter(listContentList,context);
        ListView listView =(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (listContentList.get(position).getName()) {
                    case "第一个事件":
                        Intent intent0 = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent0);
                        break;
                    case "第二个事件":
                        Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent1);
                        break;
                    case "第三个事件":
                        Intent intent2 = new Intent(MainActivity.this,ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    case "第四个事件":
                        Intent intent3 = new Intent(MainActivity.this,FourthActivity.class);
                        startActivity(intent3);
                        break;
                    case "第五个事件":
                        Intent intent4 = new Intent(MainActivity.this,FifthActivity.class);
                        startActivity(intent4);
                        break;
                    case "第六个事件":
                        Intent intent5 = new Intent(MainActivity.this,SixthActivity.class);
                        startActivity(intent5);
                        break;
                    case "第七个事件":
                        Intent intent6 = new Intent(MainActivity.this,SeventhActivity.class);
                        startActivity(intent6);
                        break;
                    case "第八个事件":
                        Intent intent7 = new Intent(MainActivity.this,EighthActivity.class);
                        startActivity(intent7);
                        break;
                    case "第九个事件":
                        Intent intent8 = new Intent(MainActivity.this,NinethActivity.class);
                        startActivity(intent8);
                        break;
                    case "第十个事件":
                        Intent intent9 = new Intent(MainActivity.this,TenthActivity.class);
                        startActivity(intent9);
                        break;

                }
            }
        });

        //item长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                builder = new AlertDialog.Builder(MainActivity.this);
                alert = builder
                        //设置对话框的内容
                        .setMessage("是否删除此条内容？")
                        //设置对话框的按钮
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listContentList.remove(position);
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).create();
                alert.show();
                return true;

            }
        });




    }
    //初始化列表文字内容
    private void initListContent() {
        ListContent firstList = new ListContent("第一个事件",R.id.check_box);
        listContentList.add(firstList);
        ListContent secondList = new ListContent("第二个事件",R.id.check_box);
        listContentList.add(secondList);
        ListContent thirdList = new ListContent("第三个事件",R.id.check_box);
        listContentList.add(thirdList);
        ListContent forthList = new ListContent("第四个事件",R.id.check_box);
        listContentList.add(forthList);
        ListContent fifthList = new ListContent("第五个事件",R.id.check_box);
        listContentList.add(fifthList);
        ListContent sixthList = new ListContent("第六个事件",R.id.check_box);
        listContentList.add(sixthList);
        ListContent seventhList = new ListContent("第七个事件",R.id.check_box);
        listContentList.add(seventhList);
        ListContent eigthList = new ListContent("第八个事件",R.id.check_box);
        listContentList.add(eigthList);
        ListContent ninethList = new ListContent("第九个事件",R.id.check_box);
        listContentList.add(ninethList);
        ListContent tenthList = new ListContent("第十个事件",R.id.check_box);
        listContentList.add(tenthList);
    }

}