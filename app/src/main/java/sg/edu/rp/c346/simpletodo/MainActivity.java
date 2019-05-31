package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

  //Declare fields
  EditText etInput;
  Button btnAdd, btnClear, btnDelete;
  ListView listView;
  Spinner spinner;
  ArrayList<String> arrayListToDo;
  ArrayAdapter<String> aaToDo;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Linking UI
    etInput = findViewById(R.id.editTextInput);
    btnAdd = findViewById(R.id.btnAdd);
    btnClear = findViewById(R.id.btnClear);
    btnDelete = findViewById(R.id.btnDelete);
    listView = findViewById(R.id.listView);
    spinner = findViewById(R.id.spinner);

    //Setting up array adapter
    arrayListToDo = new ArrayList<>();
    aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListToDo);
    listView.setAdapter(aaToDo);

    //add to list view
    btnAdd.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        if(etInput.getText().toString().isEmpty())
        {
          Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
        }
        else
        {
          arrayListToDo.add(etInput.getText().toString());
          aaToDo.notifyDataSetChanged();
          etInput.setText("");
        }
      }
    });

    //clear list view
    btnClear.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        arrayListToDo.clear();
        aaToDo.notifyDataSetChanged();
      }
    });

    //Delete list view index
    btnDelete.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        if(etInput.getText().toString().isEmpty())
        {
          Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
          return;
        }

        int position = Integer.parseInt(etInput.getText().toString());

        if(arrayListToDo.isEmpty()) //If empty
        {
          Toast.makeText(MainActivity.this, "You do not have any task to remove", Toast.LENGTH_SHORT).show();
          return;
        }
        else if(position > arrayListToDo.size()) //index not in array
        {
          Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
          return;
        }
        else
        {
          arrayListToDo.remove(position);
        }
        etInput.setText("");
        aaToDo.notifyDataSetChanged();
      }
    });

    //Listview delete
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      {
        arrayListToDo.remove(position);
        aaToDo.notifyDataSetChanged();
      }
    });

    //Spinner select add task
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
      {
        switch(position)
        {
          case 0: //Add task
            etInput.setHint("Type in a new task here");
            btnDelete.setEnabled(false);
            btnAdd.setEnabled(true);
            break;
          case 1: //Remove tasks
            etInput.setHint("Type in the index of the task to be removed");
            btnAdd.setEnabled(false);
            btnDelete.setEnabled(true);
            break;
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent)
      {

      }
    });
  }
}
