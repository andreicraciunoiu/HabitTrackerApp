package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDBHelper;

public class EditorActivity extends AppCompatActivity {
    private HabitDBHelper helper;
    private EditText habitName;
    private Spinner daySpinner;
    private EditText difficulty;
    private EditText distance;
    private Spinner enjoymentSpinner;
    private String day;
    private int enjoymentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        habitName = (EditText) findViewById(R.id.edit_habit_name);
        daySpinner = (Spinner) findViewById(R.id.spinner_day);
        difficulty = (EditText) findViewById(R.id.select_difficulty_level);
        distance = (EditText) findViewById(R.id.distance);
        enjoymentSpinner = (Spinner) findViewById(R.id.spinner_enjoyment);
        helper = new HabitDBHelper(this);

        setUpDaySpinner();
        setUpEnjoymentSpinner();
    }

    private void setUpDaySpinner() {

        ArrayAdapter daySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_days_options, android.R.layout.simple_spinner_item);
        daySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        daySpinner.setAdapter(daySpinnerAdapter);

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.day_sunday))) {
                        day = HabitEntry.SUNDAY;
                    } else if (selection.equals(getString(R.string.day_monday))) {
                        day = HabitEntry.MONDAY;
                    } else if (selection.equals(getString(R.string.day_tuesday))) {
                        day = HabitEntry.TUESDAY;
                    } else if (selection.equals(getString(R.string.day_wednesday))) {
                        day = HabitEntry.WEDNESDAY;
                    } else if (selection.equals(getString(R.string.day_thursday))) {
                        day = HabitEntry.THURSDAY;
                    } else if (selection.equals(getString(R.string.day_friday))) {
                        day = HabitEntry.FRIDAY;
                    } else {
                        day = HabitEntry.SATURDAY;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                day = "Sunday";
            }
        });
    }

    private void setUpEnjoymentSpinner() {
        ArrayAdapter daySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.enjoyment_levels, android.R.layout.simple_spinner_item);
        daySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        enjoymentSpinner.setAdapter(daySpinnerAdapter);
        enjoymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.level1))) {
                        enjoymentIndex = HabitEntry.ENJOYMENT_ONE;
                    } else if (selection.equals(getString(R.string.level2))) {
                        enjoymentIndex = HabitEntry.ENJOYMENT_TWO;
                    } else if (selection.equals(getString(R.string.level3))) {
                        enjoymentIndex = HabitEntry.ENJOYMENT_THREE;
                    } else if (selection.equals(getString(R.string.level4))) {
                        enjoymentIndex = HabitEntry.ENJOYMENT_FOUR;
                    } else {
                        enjoymentIndex = HabitEntry.ENJOYMENT_FIVE;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                enjoymentIndex = 1;
            }
        });
    }

    private void insertHabit() {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habitName.getText().toString().trim());
        values.put(HabitEntry.COLUMN_DAY, day);
        values.put(HabitEntry.COLUMN_DIFFICULTY, difficulty.getText().toString().trim());
        values.put(HabitEntry.COLUMN_DISTANCE, String.valueOf(distance.getText().toString()));
        values.put(HabitEntry.COLUMN_ENJOYMENT, enjoymentIndex);
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error adding habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit added with ID:" + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertHabit();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}