package nyc.c4q.jonathancolon.inContaq.contactlist.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fontometrics.Fontometrics;

import org.parceler.Parcels;

import nyc.c4q.jonathancolon.inContaq.R;
import nyc.c4q.jonathancolon.inContaq.contactlist.AlertDialogCallback;
import nyc.c4q.jonathancolon.inContaq.contactlist.Contact;
import nyc.c4q.jonathancolon.inContaq.sqlite.ContactDatabaseHelper;
import nyc.c4q.jonathancolon.inContaq.utilities.bitmap.SetContactImageWorkerTask;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactInfoFragment extends Fragment implements AlertDialogCallback<Integer> {
    Contact contact;
    private  TextView name, mobilePhone, email, polaroidName, address, editButton;
    private  ImageView contactImageIV, backgroundImageIV;
    private EditText editName, editMobile, editEmail, editAddress;
    private FloatingActionButton saveButton;
    private CardView infoCard;

    private SQLiteDatabase db;
    private int selection;


    public static ContactInfoFragment newInstance() {

        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle b = new Bundle();

        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_info, container, false);

        contact = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("Parcelled Contact"));

        infoCard = (CardView) view.findViewById(R.id.contact_info_card);

        name = (TextView) view.findViewById(R.id.name);
        mobilePhone = (TextView) view.findViewById(R.id.mobile_phone);
        email = (TextView) view.findViewById(R.id.email);
        polaroidName = (TextView) view.findViewById(R.id.contact_name);
        address = (TextView) view.findViewById(R.id.address);

        editName = (EditText) view.findViewById(R.id.edit_name);
        editMobile = (EditText) view.findViewById(R.id.edit_mobile_phone);
        editEmail = (EditText) view.findViewById(R.id.edit_email);
        editAddress = (EditText) view.findViewById(R.id.edit_address);

        saveButton = (FloatingActionButton) view.findViewById(R.id.save_button);
        editButton = (TextView) view.findViewById(R.id.edit_option);

        contactImageIV = (ImageView) view.findViewById(R.id.contact_img);
        backgroundImageIV = (ImageView) view.findViewById(R.id.background_image);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildSaveEditDialog();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableEditContactMode(contact);
            }
        });

        displayContactInfo(contact);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        displayContactInfo(contact);
        polaroidName.setTypeface(Fontometrics.amatic_bold(getActivity()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void displayContactInfo(Contact contact) {
        String nameValue = contact.getFirstName() + " " + contact.getLastName();
        ContactDatabaseHelper dbHelper = ContactDatabaseHelper.getInstance(getActivity());
        db = dbHelper.getWritableDatabase();

        //Asynchronously load bitmaps from Contact object
        if (contact.getBackgroundImage() != null) {
            setContactImage(contact.getBackgroundImage(), backgroundImageIV);
        }
        if (contact.getContactImage() != null) {
            setContactImage(contact.getContactImage(), contactImageIV);

        }
        if (contact.getCellPhoneNumber() != null || contact.getCellPhoneNumber() != "") {
            mobilePhone.setVisibility(View.VISIBLE);
            editMobile.setVisibility(View.VISIBLE);
            editMobile.setText(contact.getCellPhoneNumber());
        }
        if (contact.getEmail() != null || contact.getEmail() != "") {
            email.setVisibility(View.VISIBLE);
            editEmail.setVisibility(View.VISIBLE);
            editEmail.setText(contact.getEmail());
        }
        if (contact.getAddress() != null || contact.getAddress() != "") {
            address.setVisibility(View.VISIBLE);
            editAddress.setVisibility(View.VISIBLE);
            editAddress.setText(contact.getAddress());
        }
            polaroidName.setText(nameValue);
            editName.setText(nameValue);
        }

    private void setContactImage(byte[] bytes, ImageView imageView){
        SetContactImageWorkerTask task = new SetContactImageWorkerTask(imageView);
        task.execute(bytes);
    }

    private String parsePhoneNumber(String input){
        return input.replaceAll("[()\\s-]+?!@#$%^&*()_", "");
    }

    private void enableEditContactMode(final Contact contact){
        infoCard.animate()
                .alpha(1.0f)
                .setDuration(3000)
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        infoCard.getLayoutParams();
                        super.onAnimationStart(animation);

                        name.setVisibility(View.VISIBLE);
                        mobilePhone.setVisibility(View.VISIBLE);
                        email.setVisibility(View.VISIBLE);
                        address.setVisibility(View.VISIBLE);


                        editName.setVisibility(View.VISIBLE);
                        editMobile.setVisibility(View.VISIBLE);
                        editEmail.setVisibility(View.VISIBLE);
                        editAddress.setVisibility(View.VISIBLE);

                        if (contact.getEmail() == null){
                            email.setAlpha(0);
                            editEmail.setAlpha(0);}
                        if (contact.getAddress() == null){
                            address.setAlpha(0);
                            editAddress.setAlpha(0);
                        }
                        if (contact.getCellPhoneNumber() == null){
                            mobilePhone.setAlpha(0);
                            editMobile.setAlpha(0);
                        }

                        mobilePhone.animate().alpha(1.0f).setDuration(300);
                        email.animate().alpha(1.0f).setDuration(500);
                        address.animate().alpha(1.0f).setDuration(700);
                        editMobile.animate().alpha(1.0f).setDuration(300);
                        editEmail.animate().alpha(1.0f).setDuration(500);
                        editAddress.animate().alpha(1.0f).setDuration(700);

                        editName.setEnabled(true);
                        editMobile.setEnabled(true);
                        editEmail.setEnabled(true);
                        editAddress.setEnabled(true);
                        saveButton.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
    }

    public void buildSaveEditDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        // Setting Dialog Title
        alertDialog.setTitle("Save Changes?");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to save your changes?");

        // Setting Icon to Dialog

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                // Write your code here to invoke YES event
                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                selection = 1;
                alertDialogCallback(selection);

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(getActivity(), "You clicked on NO", Toast.LENGTH_SHORT).show();

                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void alertDialogCallback(Integer ret) {
        ret = selection;
        if (ret == 1){
            String mText = editName.getText().toString().trim();

            if (mText.trim().length() > 0) {

                String name = mText;
                String lastName = "";
                String firstName = "";
                if (name.split("\\w+").length > 1) {

                    lastName = name.substring(name.lastIndexOf(" ") + 1);
                    firstName = name.substring(0, name.lastIndexOf(' '));
                } else {
                    firstName = name;
                }
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
            }


            contact.setCellPhoneNumber(parsePhoneNumber(editMobile.getText().toString()));
            String email = editEmail.getText().toString();
            String address = editAddress.getText().toString();

            contact.setEmail(email);
            contact.setAddress(address);

            cupboard().withDatabase(db).put(contact);
        }
    }
}

