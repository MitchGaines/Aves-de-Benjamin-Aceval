package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.DialogInterface;
import android.renderscript.Allocation;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Created by brian_000 on 28/4/2017.
 *
 * This class represents an achievement that can be earned by a user.
 * An achievement consists of a title, description, and an image to be displayed when the user earns
 * the achievement.
 *
 * Este código representa un logro que puede ser alcanzado por un usuario.
 * Un logro consiste de un título, una descripción, y una imagen que está fijado cuando el usuario
 * alcance el logro.
 */

public class Logro extends AppCompatImageButton {
    private String titulo;
    private String descripcion;
    private int imgID;

    public Logro(final Context context, final String titulo, final String descripcion, int imgID, boolean logrado) {
        super(context);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imgID = imgID;

        if(logrado){
            setImageResource(R.mipmap.ic_check);
            setBackgroundResource(imgID);
        }else {
            setBackgroundResource(imgID);
        }
        this.setScaleType(ScaleType.FIT_CENTER);
        //Create a dialog that pops up when the image of an achievement is clicked.  The dialog box
        //will display the description of the achievement.
        //Crear un diálogo que aparece cuando una imagen de un logro es tocado.  El diálogo consiste
        //de la descripción del logro.
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(titulo);
                //Add the buttons.
                //Añadir los botones.
                builder.setPositiveButton(R.string.confirm_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setMessage(descripcion);
                //Create and show the dialog.
                //Crear y mostrar el diálogo.
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void init(){
        final View linearLayout = ((LinearLayout)(((GridLayout)(this.getParent())).getParent()));
        final Logro thisLogro = this;
        linearLayout.post(new Runnable() {
            @Override
            public void run() {
                //Ensure that the images of the achievements are the right size, such that there are
                //3 in each row.
                //Asegurar que las imagenes de los logros son del tamaño correcto para que hay 3 en
                //cada hilera.
                int size = linearLayout.getWidth()/3;
                thisLogro.getLayoutParams().width = size;
                thisLogro.getLayoutParams().height = size;
                thisLogro.requestLayout();
            }
        });
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getImgID() {
        return imgID;
    }

}
