package com.operr.singlylinkedlist.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.operr.singlylinkedlist.R;

import java.util.ArrayList;

import com.operr.singlylinkedlist.model.LinkedList;

import java.util.List;

/**
 * Created by Dhimant on 11/5/17.
 */

public class SinglyLinkedListActivity extends AppCompatActivity {

    private Context mContext;

    private Spinner mSpinner;
    private Button btnSubmit;
    private TextView txtDisplayData;

    private EditText edtData;
    private EditText edtPosition;

    private ArrayAdapter<String> mAdapter;
    private List<String> mList;

    private LinkedList mLinkedList;
    private int mSelectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singly_linked_list);

        mContext = this;

        mLinkedList = new LinkedList();

        mSpinner = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnAdd);
        txtDisplayData = (TextView) findViewById(R.id.txtDisplayData);
        edtData = (EditText) findViewById(R.id.edtData);
        edtPosition = (EditText) findViewById(R.id.edtPosition);

        mList = new ArrayList<>();
        mList.add("Singly Linked List Operations");
        mList.add("1. insert at begining");
        mList.add("2. insert at end");
        mList.add("3. insert at position");
        mList.add("4. delete at position");
        mList.add("5. check empty");
        mList.add("6. get size");
        mList.add("7. delete all elements");

        edtData.setEnabled(false);
        edtPosition.setEnabled(false);
        btnSubmit.setEnabled(false);
        edtData.setVisibility(View.INVISIBLE);
        edtPosition.setVisibility(View.INVISIBLE);
        btnSubmit.setVisibility(View.INVISIBLE);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mList);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedPosition = i;
                edtData.setText("");
                edtPosition.setText("");
                controlHideShow();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtData.getVisibility() == View.VISIBLE && edtData.getText().toString().trim().length() == 0) {
                    return;
                }

                if (edtPosition.getVisibility() == View.VISIBLE && edtPosition.getText().toString().trim().length() == 0) {
                    return;
                }

                performOperation();
            }
        });

    }

    private void controlHideShow() {

        switch (mSelectedPosition) {
            case 1:
                edtData.setEnabled(true);
                edtPosition.setEnabled(false);
                btnSubmit.setEnabled(true);
                edtData.setVisibility(View.VISIBLE);
                edtPosition.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                break;
            case 2:
                edtData.setEnabled(true);
                edtPosition.setEnabled(false);
                btnSubmit.setEnabled(true);
                edtData.setVisibility(View.VISIBLE);
                edtPosition.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                break;
            case 3:
                edtData.setEnabled(true);
                edtPosition.setEnabled(true);
                btnSubmit.setEnabled(true);
                edtData.setVisibility(View.VISIBLE);
                edtPosition.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                break;
            case 4:
                edtData.setEnabled(false);
                edtPosition.setEnabled(true);
                btnSubmit.setEnabled(true);
                edtData.setVisibility(View.INVISIBLE);
                edtPosition.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                break;
            case 5:
                edtData.setEnabled(false);
                edtPosition.setEnabled(false);
                btnSubmit.setEnabled(false);
                edtData.setVisibility(View.INVISIBLE);
                edtPosition.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.INVISIBLE);
                performOperation();
                break;
            case 6:
                edtData.setEnabled(false);
                edtPosition.setEnabled(false);
                btnSubmit.setEnabled(false);
                edtData.setVisibility(View.INVISIBLE);
                edtPosition.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.INVISIBLE);
                performOperation();
                break;
            case 7:
                edtData.setEnabled(false);
                edtPosition.setEnabled(false);
                btnSubmit.setEnabled(false);
                edtData.setVisibility(View.INVISIBLE);
                edtPosition.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.INVISIBLE);
                performOperation();
                break;
            default:
                //showMessage("Wrong Entry");
                break;
        }

    }

    private void performOperation() {

        if (mSelectedPosition == 0) {
            showMessage("Invalid option");
            return;
        }

        switch (mSelectedPosition) {
            case 1:
                mLinkedList.insertAtStart(Integer.parseInt(edtData.getText().toString()));
                break;
            case 2:
                mLinkedList.insertAtEnd(Integer.parseInt(edtData.getText().toString()));
                break;
            case 3:
                int pos = Integer.parseInt(edtPosition.getText().toString());
                if (pos <= 1 || pos > mLinkedList.getSize())
                    showMessage("Invalid position");
                else
                    mLinkedList.insertAtPos(Integer.parseInt(edtData.getText().toString()), Integer.parseInt(edtPosition.getText().toString()));
                break;
            case 4:
                int p = Integer.parseInt(edtPosition.getText().toString());
                if (p < 1 || p > mLinkedList.getSize())
                    showMessage("Invalid position");
                else
                    mLinkedList.deleteAtPos(p);
                break;
            case 5:
                showMessage("Empty status = " + mLinkedList.isEmpty());
                break;
            case 6:
                showMessage("Size = " + mLinkedList.getSize());
                break;
            case 7:
                mLinkedList.deleteAll();
                break;
            default:
                showMessage("Wrong Entry");
                break;
        }

        setData();

    }

    public void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    public void setData() {
        txtDisplayData.setText(mLinkedList.displayData() + "");
        edtData.setText("");
        edtPosition.setText("");
        if (mSelectedPosition == 6) {
            txtDisplayData.setText("Singly Linked List Size = " + mLinkedList.getSize());
        }
    }

}
