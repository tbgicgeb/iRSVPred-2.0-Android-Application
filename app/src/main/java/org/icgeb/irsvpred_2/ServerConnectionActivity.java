package org.icgeb.irsvpred_2;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;


import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class ServerConnectionActivity extends AppCompatActivity {


    Integer SELECT_FILE = 0;
    Uri imageuri;
    private Bitmap bitmap;
    private ImageView image_1;
    String picturePath, fileName;
    Button bt, bt2, bt3;
    TextView progresText;


    private byte[] imgByte;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    private ProgressBar progressBar, fillingBar;

    String title1 ,title2, title3, title4, title5, title6 ,title7, title8, title9, title10, title11;
    String value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11;



    public static final int STORAGE_PERMISSION_CODE = 1;
    public static final int PERMISSION_REQUEST_CODE = 200;
    public static final int CAMERA_REQUEST_CODE = 102;

    String currentPhotoPath, imageFileName;

    String sv1, sv3;

    public ImageView resultImageFinal;
    RelativeLayout finalResultLayout;
    TextView resultConclusion, rc21, rc22, rc31, rc32, rc41, rc42, rc51, rc52, rc61, rc62, rc71, rc72, rc81, rc82, rc91, rc92, rc101, rc102, rc111, rc112, rc121, rc122;

    Document doc;
    Elements data;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_connection);

        // Text View
        progresText = findViewById(R.id.progressText);

        Toolbar toolbar = findViewById(R.id.toolbarServer);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //Button Activity

        bt = (Button) findViewById(R.id.home_bt_1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(ServerConnectionActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    ipNano();
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    i.setType("image/*");
                    startActivityForResult(i.createChooser(i, "Select File"), SELECT_FILE);

//                    Toast.makeText(ServerConnectionActivity.this, "here re", Toast.LENGTH_SHORT).show();
                }
                else {
                    requestStoragePermission();

//                    Toast.makeText(ServerConnectionActivity.this, "here where", Toast.LENGTH_SHORT).show();
                }
            }
        });



        bt2 = findViewById(R.id.home_bt_2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    ipNano();

                    dispatchTakePictureIntent();
                }
                else {
                    requestPermission();
                }
            }
        });




        bt3 = (Button) findViewById(R.id.home_bt_3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (picturePath == null) {
                    Toast.makeText(ServerConnectionActivity.this, "Please Select an Image", Toast.LENGTH_SHORT).show();
                }

                else if (isNetworkAvailable()){

                    //** Send image and offload image processing task  to server by starting async task **
                    ServerTask task = new ServerTask();
                    task.execute(picturePath);
                }

                else {
                    Toast.makeText(ServerConnectionActivity.this, "Ensure Internet Connectivity", Toast.LENGTH_SHORT).show();
                }



            }

        });





        progressBar = findViewById(R.id.progressBar);
        fillingBar = findViewById(R.id.fillingBar);

        finalResultLayout = findViewById(R.id.finalResultLayout);

        resultImageFinal = findViewById(R.id.resultImageFinal);

        resultConclusion = findViewById(R.id.resultConclusion);

        rc21 = findViewById(R.id.rc21);
        rc22 = findViewById(R.id.rc22);
        rc31 = findViewById(R.id.rc31);
        rc32 = findViewById(R.id.rc32);
        rc41 = findViewById(R.id.rc41);
        rc42 = findViewById(R.id.rc42);
        rc51 = findViewById(R.id.rc51);
        rc52 = findViewById(R.id.rc52);
        rc61 = findViewById(R.id.rc61);
        rc62 = findViewById(R.id.rc62);
        rc71 = findViewById(R.id.rc71);
        rc72 = findViewById(R.id.rc72);
        rc81 = findViewById(R.id.rc81);
        rc82 = findViewById(R.id.rc82);
        rc91 = findViewById(R.id.rc91);
        rc92 = findViewById(R.id.rc92);
        rc101 = findViewById(R.id.rc101);
        rc102 = findViewById(R.id.rc102);
        rc111 = findViewById(R.id.rc111);
        rc112 = findViewById(R.id.rc112);
        rc121 = findViewById(R.id.rc121);
        rc122 = findViewById(R.id.rc122);


    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed since image has to uploaded for prediction")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ServerConnectionActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(ServerConnectionActivity.this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void ipNano() {

        UUID unique_id = UUID.randomUUID();
        String unique_id_string = unique_id.toString();

        // sv1 = server address for file upload;
        // sv3 = server address to fetch results;

    }



    //Camera Permission

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                showMessageOKCancel("You need to allow camera permissions to click and upload pictures",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(ServerConnectionActivity.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        PERMISSION_REQUEST_CODE);
                            }
                        });
            }
        }
    }


    //CAMERA PERMISSION


    private File createImageFile() throws IOException {
        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String camera = "cameraCaptured";
//        String imageFileName = "JPEG_" + timeStamp + "_";
        imageFileName = "JPEG_" + camera;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) ;
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }




    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "org.icgeb.android.irsvpred.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    dispatchTakePictureIntent();
                    // main logic
                } else {
                    Toast.makeText(this, "Permission denied\nKindly allow camera permission from settings", Toast.LENGTH_LONG).show();
                }
                break;
            case STORAGE_PERMISSION_CODE:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                bt.performClick();
            } else {
                Toast.makeText(this, "Permission denied\nKindly allow file permission from settings", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(ServerConnectionActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        image_1 = findViewById(R.id.home_image_1);


        if (requestCode == SELECT_FILE && resultCode == RESULT_OK) {
            imageuri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                image_1.setImageBitmap(bitmap);



                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                imgByte = byteArrayOutputStream.toByteArray();


                String[] projection = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageuri, projection, null, null, null);
                cursor.moveToFirst();

                Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor));

                int columnIndex = cursor.getColumnIndex(projection[0]);
                picturePath = cursor.getString(columnIndex); // returns null
                fileName = picturePath.substring(picturePath.lastIndexOf("/") + 1);

                cursor.close();

                Log.d(TAG, "onActivityResult: imageuri:::   " + imageuri);
                Log.d(TAG, "onActivityResult: picturepath:::   " + picturePath);
                Log.d(TAG, "onActivityResult: filename:::    " + fileName);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {


            File f = new File(currentPhotoPath);
            image_1.setImageURI(Uri.fromFile(f));



            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            imageuri = Uri.fromFile(f) ;
            mediaScanIntent.setData(imageuri);
            this.sendBroadcast(mediaScanIntent);


            picturePath = currentPhotoPath;
            fileName =  imageFileName + ".JPG";
            Log.d(TAG, "onActivityResult: " + imageuri);
            Log.d(TAG, "onActivityResult: " + picturePath);
            Log.d(TAG, "onActivityResult: " + fileName);

        }



    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    private static final String TAG = "ServerActivity";



    public class ServerTask extends AsyncTask<String, Integer, Void> {

        String words;
        String wordfinal;


        //upload photo to server
        HttpURLConnection uploadPhoto(FileInputStream fileInputStream) {




            final String lineEnd = "\r\n";
            final String twoHyphens = "--";
            final String boundary = "*****";

            try {
//                URL url = new URL(SERVERURL);
                URL url = new URL(sv1);
                // Open a HTTP connection to the URL
//                final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // Allow Inputs
                conn.setDoInput(true);
                // Allow Outputs
                conn.setDoOutput(true);
                // Don't use a cached copy.
                conn.setUseCaches(false);

                // Use a post method.
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("enctype", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"fileToUpload\";filename=\"" + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);


                // create a buffer of maximum size
                int bytesAvailable = fileInputStream.available();
                int maxBufferSize = 102400000;
                int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                byte[] buffer = new byte[bufferSize];

                // read file and write it into form...
                int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart form data after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//                publishProgress(SERVER_PROC_STATE);
                // close streams
                fileInputStream.close();
                dos.flush();



                return conn;
            } catch (MalformedURLException ex) {
                Log.e(TAG, "error: " + ex.getMessage(), ex);
                return null;
            } catch (IOException ioe) {
                Log.e(TAG, "error: " + ioe.getMessage(), ioe);
                return null;
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            image_1.setVisibility(View.GONE);
            bt.setVisibility(View.GONE);
            bt2.setVisibility(View.GONE);
            bt3.setVisibility(View.GONE);


            progresText.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            fillingBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(ServerConnectionActivity.this, android.R.anim.fade_in));
//            Toast.makeText(ServerConnectionActivity.this, unique, Toast.LENGTH_LONG).show();
            // Start long running operation in a background thread
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                                fillingBar.setProgress(progressStatus);
//                                textView.setText(progressStatus+"/"+progressBar.getMax());
                            }
                        });
                        try {
                            // Sleep for 430 milliseconds.
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (title1=="" || title2=="" || title3=="" || title4=="" || title5==""){
                progressBar.setVisibility(View.GONE);
                progressBar.startAnimation(AnimationUtils.loadAnimation(ServerConnectionActivity.this, android.R.anim.fade_out));
                fillingBar.setVisibility(View.GONE);
                progresText.setText("ERROR 403   " + "Check Internet Connectivity");


            }
            else {
                progresText.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                progressBar.startAnimation(AnimationUtils.loadAnimation(ServerConnectionActivity.this, android.R.anim.fade_out));
                fillingBar.setVisibility(View.GONE);

                resultImageFinal.setImageURI(imageuri);

                float score = Float.parseFloat(value1);
                score = Math.round(score*100);
//                score = Math.round((score*100)/100);


                resultConclusion.setText("Based on our analysis, your image is "+ title1 +" with accuracy "+ score +"%.");


                rc21.setText(title1);
                rc22.setText(value1);
                rc31.setText(title2);
                rc32.setText(value2);
                rc41.setText(title3);
                rc42.setText(value3);
                rc51.setText(title4);
                rc52.setText(value4);
                rc61.setText(title5);
                rc62.setText(value5);
                rc71.setText(title6);
                rc72.setText(value6);
                rc81.setText(title7);
                rc82.setText(value7);
                rc91.setText(title8);
                rc92.setText(value8);
                rc101.setText(title9);
                rc102.setText(value9);
                rc111.setText(title10);
                rc112.setText(value10);
                rc121.setText(title11);
                rc122.setText(value11);

                finalResultLayout.setVisibility(View.VISIBLE);


            }



        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            finishAffinity();

        }



        @Override
        protected Void doInBackground(String... strings) {


            //Main code for processing image algorithm on the server

            {
                File inputFile = new File(picturePath);
                try {

                    //create file stream for captured image file
                    FileInputStream fileInputStream = new FileInputStream(inputFile);


                    final HttpURLConnection conn = uploadPhoto(fileInputStream);

                    conn.getInputStream();



                } catch (IOException ex) {
                    Log.e(TAG, ex.toString());
                }


                try {
                    

                    doc = Jsoup.connect(sv3).get();

                    data = doc.select("td");

                    title1 = data.select("td").eq(6).text();
                    title2 = data.select("td").eq(9).text();
                    title3 = data.select("td").eq(12).text();
                    title4 = data.select("td").eq(15).text();
                    title5 = data.select("td").eq(18).text();
                    title6 = data.select("td").eq(21).text();
                    title7 = data.select("td").eq(24).text();
                    title8 = data.select("td").eq(27).text();
                    title9 = data.select("td").eq(30).text();
                    title10 = data.select("td").eq(33).text();
                    title11 = data.select("td").eq(36).text();


                    value1 = data.select("td").eq(7).text();
                    value2 = data.select("td").eq(10).text();
                    value3 = data.select("td").eq(13).text();
                    value4 = data.select("td").eq(16).text();
                    value5 = data.select("td").eq(19).text();
                    value6 = data.select("td").eq(22).text();
                    value7 = data.select("td").eq(25).text();
                    value8 = data.select("td").eq(28).text();
                    value9 = data.select("td").eq(31).text();
                    value10 = data.select("td").eq(34).text();
                    value11 = data.select("td").eq(37).text();



                } catch (IOException e) {
                    e.printStackTrace();
                }



                return null;

            }
        }




    }



}