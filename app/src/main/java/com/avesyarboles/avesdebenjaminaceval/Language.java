package com.avesyarboles.avesdebenjaminaceval;

/**
 * Created by brian_000 on 21/4/2017.
 */

/**
 * This class defines a language that is supported by this application.  This allows the language to
 * be displayed in the settings as an option in the change language interface.
 * A language consists of the language's name, an icon, and a language code/abbreviation.
 *
 * Este código define un idioma apoyado por la aplicación.  Esto permite que el idioma pueda ser mostrado
 * en los ajustes como un opción en la interfaz para cambiar idiomas.
 * Un idioma consiste del nombre del idioma, una imagen, y un código/una abreviatura del idioma.
 */

public class Language {
    private int languageNameId;
    private int languageIconId;
    private String languageCode;

    public Language(int languageNameId, int languageIconId, String languageCode){
        this.languageNameId = languageNameId;
        this.languageIconId = languageIconId;
        this.languageCode = languageCode;
    }

    public int getLanguageNameId() {
        return languageNameId;
    }

    public int getLanguageIconId() {
        return languageIconId;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
