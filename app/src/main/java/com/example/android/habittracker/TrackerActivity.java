package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDBHelper;

public class TrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrackerActivity.this, EditorActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        readData();
    }

    private Cursor readData() {
        HabitDBHelper helper = new HabitDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_DAY,
                HabitEntry.COLUMN_DIFFICULTY,
                HabitEntry.COLUMN_DISTANCE,
                HabitEntry.COLUMN_ENJOYMENT};
        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        TextView displayText = (TextView) findViewById(R.id.text_view_habit);
        try {
            if (cursor.getCount() == 0) {
                displayText.setText(null);
            } else {
                displayText.setText("The habits table contains " + cursor.getCount() + " Habits. \n\n");
                displayText.append(HabitEntry._ID + " - " +
                        HabitEntry.COLUMN_HABIT_NAME + " - " +
                        HabitEntry.COLUMN_DAY + " - " +
                        HabitEntry.COLUMN_DIFFICULTY + " - " +
                        HabitEntry.COLUMN_DISTANCE + " - " +
                        HabitEntry.COLUMN_ENJOYMENT + "\n");

                int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
                int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
                int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DAY);
                int difficultyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DIFFICULTY);
                int distanceColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DISTANCE);
                int enjoymentColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_ENJOYMENT);

                while (cursor.moveToNext()) {
                    int currentID = cursor.getInt(idColumnIndex);
                    String currentName = cursor.getString(nameColumnIndex);
                    String currentDay = cursor.getString(dayColumnIndex);
                    String currentDifficulty = cursor.getString(difficultyColumnIndex);
                    int currentDistance = cursor.getInt(distanceColumnIndex);
                    int currentEnjoyment = cursor.getInt(enjoymentColumnIndex);
                    displayText.append(("\n" + currentID + " - " + currentName + " - " + currentDay + " - " +
                            currentDifficulty + " - " + currentDistance + " meters - " + currentEnjoyment + " stars"));
                }
            }
        } finally {
            cursor.close();
        }
        return cursor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_habit_data:
                Toast.makeText(this, "Feature will be implemented", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete_all_entries:
                Toast.makeText(this, "Feature will be implemented", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}