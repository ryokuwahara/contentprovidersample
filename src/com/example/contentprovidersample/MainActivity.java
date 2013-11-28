package com.example.contentprovidersample;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView1;
		List<String> list = new ArrayList<String>();
		listView1 = (ListView) findViewById(R.id.listView1);



		ContentResolver Resolver = getContentResolver();

		// 取得対象指定
		// String[] projection = new String[] {
		// MediaStore.Audio.AudioColumns.TITLE,
		// MediaStore.Audio.AudioColumns.DATA };

		// String[] projection = new String[] {
		// MediaStore.Images.ImageColumns.TITLE,
		// MediaStore.Images.ImageColumns.DATA,
		// MediaStore.Images.ImageColumns.SIZE };

		// 取得対象指定 名称・電話番号
		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };

		// ContentsProviderデータ取得（スマホ内部のオーディオ情報）
		// Cursor cursor = Resolver.query(
		// MediaStore.Audio.Media.INTERNAL_CONTENT_URI, projection, null,
		// null, null);

		// // ContentsProviderデータ取得（スマホの画像）
		// Cursor cursor = Resolver.query(
		// MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
		// null, null);

		// ContentsProviderデータ取得（連絡帳）
		Cursor cursor = Resolver.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
				null, null, null);

		if (cursor != null && 0 < cursor.getCount()) {
			cursor.moveToFirst();

			do {
				// Log.i("contentprovider",
				// "TITLE:"
				// + cursor.getString(cursor
				// .getColumnIndex(MediaStore.Audio.AudioColumns.TITLE)));
				// Log.i("contentprovider",
				// "DATA:"
				// + cursor.getString(cursor
				// .getColumnIndex(MediaStore.Audio.AudioColumns.DATA)));

				// Log.i("contentprovider",
				// "TITLE:"
				// + cursor.getString(cursor
				// .getColumnIndex(MediaStore.Images.ImageColumns.TITLE)));
				// Log.i("contentprovider",
				// "DATA:"
				// + cursor.getString(cursor
				// .getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
				// Log.i("contentprovider",
				// "SIZE:"
				// + cursor.getInt(cursor
				// .getColumnIndex(MediaStore.Images.ImageColumns.SIZE)));

				Log.i("contentprovider",
						"DISPLAY_NAME:"
								+ cursor.getString(cursor
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

				Log.i("contentprovider",
						"NUMBER:"
								+ cursor.getString(cursor
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
				
				list.add(cursor.getString(cursor
						.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
				
			} while (cursor.moveToNext());

		}

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, list);

		listView1.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
