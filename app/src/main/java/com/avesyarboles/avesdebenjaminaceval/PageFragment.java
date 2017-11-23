package com.avesyarboles.avesdebenjaminaceval;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAUNCHER_APPS_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static java.net.Proxy.Type.HTTP;

/**
 * Created by brian_000 on 4/4/2017.
 */

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private final String DATABASE_NAME = "BirdDatabase";
    private final String USER_INFO_TABLE_NAME = "user_info";
    private final String BIRD_TABLE_NAME = "bird_profiles";
    private final String BIRD_SEEN_NAME = "bird_seen";

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private String userChosenTask;

    private final int TAKE_PHOTO = 314;
    private final int CHOOSE_GALLERY = 823;
    private final int SEND_EMAIL = 408;

    private final String ADMIN_EMAIL = "avesdebenjaminaceval@gmail.com";
    private View messageView;
    private static Uri outputFileUri;


    protected MapView mMapView;

    private int mPage;

    private static Uri currentEmailImageURI;
    private static Bitmap currentEmailImage;
    private InputStream currentEmailImageStream;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        switch(mPage){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        switch(mPage){
            case 0:
                //this will never happen because a MapViewFragment is created instead of a PageFragment
                break;
            case 1:
                view = inflater.inflate(R.layout.activity_bird_list, container, false);
                initBirdList(view);
                break;
            case 2:
                view = inflater.inflate(R.layout.checklist_view, container, false);
                initChecklist(view);
                break;
            case 3:
                view = inflater.inflate(R.layout.report_bird_observation_view, container, false);
                initObservation(view);
                break;
            default:
                break;
        }
        return view;
    }

    private void updateBirdSeenCount(ListView checklist, View pageView, SQLiteDatabase myDB){
        //update bird count
        int total = 0, count = 0;
        Cursor profileRow = myDB.rawQuery("SELECT * FROM " + BIRD_SEEN_NAME + ";", null);
        if(profileRow != null && profileRow.moveToFirst()) {
            boolean moveNext = true;
            while(profileRow != null && moveNext){
                total++;
                if(profileRow.getInt(profileRow.getColumnIndex("SEEN")) > 0){
                    count++;
                }
                moveNext = profileRow.moveToNext();
            }
        }

        int numLogros = 6;
        boolean uno = false, cinco = false, diez = false, veinte = false, treinticinco = false, cincuenta = false;

        Cursor userInfo = myDB.rawQuery("SELECT * FROM " + USER_INFO_TABLE_NAME + " WHERE ID=" + 0 + ";", null);
        if(userInfo != null && userInfo.moveToFirst()){
            for(int i=0; i<numLogros; i++){
                switch(i){
                    case 0:
                        int logroColumna = userInfo.getColumnIndex("LOGRO1");
                        uno = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 1:
                        logroColumna = userInfo.getColumnIndex("LOGRO2");
                        cinco = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 2:
                        logroColumna = userInfo.getColumnIndex("LOGRO3");
                        diez = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 3:
                        logroColumna = userInfo.getColumnIndex("LOGRO4");
                        veinte = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 4:
                        logroColumna = userInfo.getColumnIndex("LOGRO5");
                        treinticinco = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 5:
                        logroColumna = userInfo.getColumnIndex("LOGRO6");
                        cincuenta = userInfo.getInt(logroColumna) != 0;
                        break;
                    default:
                        break;
                }
            }
        }

        //Check to see if an achievement has been earned and update database
        //Ver si un logro ha sido logrado y actualizar el base de datos
        if(!uno && count >= 1){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO1 = "+1+" WHERE ID = 0;";
            myDB.execSQL(query);
            //Notify user that they have earned an achievement
            //Avisar al usuario que ha alcanzado un logro
            notifyAchievement(getContext(), R.mipmap.ic_bronze_medal, R.string.logro1_title);
        }else if(uno && count < 1){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO1 = "+0+" WHERE ID = 0;";
            myDB.execSQL(query);
        }

        if(!cinco && count >= 5){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO2 = "+1+" WHERE ID = 0;";
            myDB.execSQL(query);
            notifyAchievement(getContext(), R.mipmap.ic_silver_medal, R.string.logro2_title);
        }else if(cinco && count < 5){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO2 = "+0+" WHERE ID = 0;";
            myDB.execSQL(query);
        }

        if(!diez && count >= 10){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO3 = "+1+" WHERE ID = 0;";
            myDB.execSQL(query);
            notifyAchievement(getContext(), R.mipmap.ic_gold_medal, R.string.logro3_title);
        }else if(diez && count < 10){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO3 = "+0+" WHERE ID = 0;";
            myDB.execSQL(query);
        }

        if(!veinte && count >= 20){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO4 = "+1+" WHERE ID = 0;";
            myDB.execSQL(query);
            notifyAchievement(getContext(), R.mipmap.ic_bronze, R.string.logro4_title);
        }else if(veinte && count < 20){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO4 = "+0+" WHERE ID = 0;";
            myDB.execSQL(query);
        }

        if(!treinticinco && count >= 35){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO5 = "+1+" WHERE ID = 0;";
            myDB.execSQL(query);
            notifyAchievement(getContext(), R.mipmap.ic_silver, R.string.logro5_title);
        }else if(treinticinco && count < 35){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO5 = "+0+" WHERE ID = 0;";
            myDB.execSQL(query);
        }

        if(!cincuenta && count >= 50){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO6 = "+1+" WHERE ID = 0;";
            myDB.execSQL(query);
            notifyAchievement(getContext(), R.mipmap.ic_gold, R.string.logro6_title);
        }else if(cincuenta && count < 50){
            String query = "UPDATE "+USER_INFO_TABLE_NAME+" SET LOGRO6 = "+0+" WHERE ID = 0;";
            myDB.execSQL(query);
        }

        //update display of bird count
        TextView birdCountTV = (TextView)pageView.findViewById(R.id.birdCountTV);
        birdCountTV.setText(getString(R.string.bird_count_label,count,total));
    }

    private void notifyAchievement(Context context, int imgID, int achievementNameID){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.achievement_notification_title);
        builder.setIcon(imgID);
        // Add the buttons
        builder.setPositiveButton(R.string.confirm_dialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setMessage(getString(R.string.achievement_notification_description, getString(achievementNameID)));
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveCheckboxStatus(int birdID, int seen, SQLiteDatabase myDB){
        String query = "UPDATE "+BIRD_SEEN_NAME+" SET SEEN = "+seen+" WHERE ID = "+birdID+";";
        myDB.execSQL(query);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.chooseGallery)),CHOOSE_GALLERY);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context)
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle(R.string.permissionNecessary);
                    alertBuilder.setMessage(R.string.externalStoragePermissionNecessary);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChosenTask.equals(getString(R.string.takePhoto)))
                        cameraIntent();
                    else if(userChosenTask.equals(getString(R.string.chooseGallery)))
                        galleryIntent();
                } else {
                }
                break;
        }
    }

    public void pickImage() {
        final CharSequence[] items = { getString(R.string.takePhoto), getString(R.string.chooseGallery),
                getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.addPhoto);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=checkPermission(getContext());
                if (items[item].equals(getString(R.string.takePhoto))) {
                    userChosenTask=getString(R.string.takePhoto);
                    if(result)
                        cameraIntent();
                } else if (items[item].equals(getString(R.string.chooseGallery))) {
                    userChosenTask=getString(R.string.chooseGallery);
                    if(result)
                        galleryIntent();
                } else if (items[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_GALLERY) {
            if (data != null) {
                try {
                    currentEmailImage = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentEmailImageURI = data.getData();
            }
        }
        else if(requestCode == TAKE_PHOTO){
            if(data != null) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                currentEmailImage = thumbnail;

                try {
                    File root = Environment.getExternalStorageDirectory();
                    if (root.canWrite()){
                        File pic = new File(root, "pic"+System.currentTimeMillis()+".png");
                        FileOutputStream out = new FileOutputStream(pic);
                        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();

                        currentEmailImageURI = Uri.fromFile(pic);
                    }
                } catch (IOException e) {
                    Log.e("BROKEN", "Could not write file " + e.getMessage());
                }
            }
        }
        else if(requestCode == SEND_EMAIL && resultCode != Activity.RESULT_CANCELED){
            clearMessageComposition();
        }
        Log.println(Log.INFO, "INFO", "URI: "+ currentEmailImageURI);
    }

    private void clearMessageComposition(){
        if(messageView != null) {
            final ImageButton imageSelectionButton = (ImageButton) messageView.findViewById(R.id.selectImageButton);
            final EditText subjectET = (EditText) messageView.findViewById(R.id.subjectET);
            final EditText bodyET = (EditText) messageView.findViewById(R.id.descriptionET);

            subjectET.setText("");
            bodyET.setText("");
            //reset image selected URI and imageButton image
            currentEmailImageURI = null;
            imageSelectionButton.setImageResource(R.drawable.ic_camera);
        }
    }

    private void initObservation(final View thisView){
        messageView = thisView;
        final Button sendButton = (Button)thisView.findViewById(R.id.sendButton);
        final Button cancelButton = (Button)thisView.findViewById(R.id.cancelButton);
        final ImageButton imageSelectionButton = (ImageButton)thisView.findViewById(R.id.selectImageButton);
        final EditText subjectET = (EditText)thisView.findViewById(R.id.subjectET);
        final EditText bodyET = (EditText)thisView.findViewById(R.id.descriptionET);

        imageSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        if(currentEmailImage != null) {
            imageSelectionButton.setImageBitmap(currentEmailImage);
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Add the buttons
                builder.setPositiveButton(R.string.discard, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked confirmation button
                        clearMessageComposition();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.setMessage(R.string.confirm_discard);
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(getActivity())
                        .setType("message/rfc822")
                        .addEmailTo(ADMIN_EMAIL)
                        .setSubject("Mensaje - Aves de Benjamín Aceval: " + subjectET.getText().toString())
                        .setText(bodyET.getText().toString())
                        .setChooserTitle(getResources().getString(R.string.email_chooser_prompt));
                if(currentEmailImageURI != null)
                    intentBuilder.addStream(currentEmailImageURI);
                intentBuilder.startChooser();

                /*Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {ADMIN_EMAIL}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectET.getText());
                emailIntent.putExtra(Intent.EXTRA_TEXT, bodyET.getText());
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("message/rfc822");
                if(currentEmailImageURI != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, currentEmailImageURI);
                }
                startActivityForResult(emailIntent, SEND_EMAIL);*/
            }
        });


    }

    private void initChecklist(final View thisView){
        final ListView checklist = (ListView)thisView.findViewById(R.id.checklistLV);

        final SQLiteDatabase myDB = loadAndUpdateDB();

        final String query = "SELECT rowid _id,* FROM "+BIRD_SEEN_NAME+" ORDER BY ID ASC"; // No trailing ';'

        Cursor cursor = myDB.rawQuery(query, null);

        final ChecklistCursorAdapter adapter = new ChecklistCursorAdapter(
                checklist.getContext(), R.layout.checklist_view_row, cursor, 0 );

        final Context context = getContext();

        checklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {
                int profileId = Integer.parseInt((String)((TextView)itemView.findViewById(R.id.checklistBirdIDTV)).getText());
                //save the checked status of the box for that species
                CheckBox checkBox = (CheckBox)itemView.findViewById(R.id.birdCheckBox);
                checkBox.toggle();
                boolean state = checkBox.isChecked();
                if(state){
                    //box has just been checked
                    //save state as checked for that bird
                    saveCheckboxStatus(profileId, 1, myDB);
                }else{
                    //box has just been unchecked
                    //save state as unchecked for that bird
                    saveCheckboxStatus(profileId, 0, myDB);
                }
                //update bird count and update display of bird count
                updateBirdSeenCount(checklist, thisView, myDB);
                Cursor cursor = myDB.rawQuery(query, null);
                adapter.changeCursor(cursor);
            }
        });

        checklist.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                //update count of birds seen
                //updateBirdSeenCount(checklist, thisView, myDB);
            }
        });

        updateBirdSeenCount(checklist, thisView, myDB);

        checklist.setAdapter(adapter);
    }

    private void initBirdList(View thisView){
        ListView birdList = (ListView)thisView.findViewById(R.id.birdListLV);

        SQLiteDatabase myDB = loadAndUpdateDB();

        String query = "SELECT rowid _id,* FROM "+BIRD_TABLE_NAME+" ORDER BY ID ASC"; // No trailing ';'

        Cursor cursor = myDB.rawQuery(query, null);

        BirdCursorAdapter adapter = new BirdCursorAdapter(
                birdList.getContext(), R.layout.birdlist_listview_row, cursor, 0 );

        final Context context = getContext();

        birdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {
                int profileId = Integer.parseInt((String)((TextView)itemView.findViewById(R.id.birdIDTV)).getText());
                Intent intent = new Intent(context, BirdProfileActivity.class);
                intent.putExtra("id",profileId);
                startActivity(intent);
            }
        });

        birdList.setAdapter(adapter);

        if (myDB != null)
            myDB.close();
    }

    private SQLiteDatabase loadAndUpdateDB(){
        SQLiteDatabase myDB = getContext().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        return myDB;
    }
}