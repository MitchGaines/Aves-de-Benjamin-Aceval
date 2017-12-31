Birds of Benjamín Aceval/Aves de Benjamín Aceval Instructions for Getting Started/Instrucciones para empezar
Updated/Actualizado: 12/30/2017 (Brian Zylich)
Created by/Creado por: Brian Zylich
-----------------------------------------------------------------------------------


English Instructions (Para ver instrucciones en español, mire al final de la página)
-----------------------------------------------------------------------------------
To set up the android programming environment:
	1. Download and install Android Studio - https://developer.android.com/studio/index.html
	2. Choose/create a directory that will hold the Birds of Benjamín Aceval (Aves de Benjamín Aceval) App Project
	3. Download and install Git - https://git-scm.com/downloads
	4. Open Git bash
	5. Use the 'cd' command to navigate to the directory selected in step 2.
	6. Type 'git clone https://bzylich@bitbucket.org/bzylich/aves-de-benjamin-aceval.git'
	7. Type 'git checkout desarrollar'
	8. Open Android Studio and open the project in the directory selected in step 2.
	
	Congratulations, the project should now be set up and ready for you to make changes! :)

To add more bird profiles:
	1. Open the 'bird_profile.xml' file in Android Studio.
	2. Insert the following template and update the fields accordingly:
		<BIRD_PROFILE>
			<ID>35</ID>
			<SCIENTIFIC_NAME>Ciconia maguari</SCIENTIFIC_NAME>
			<BIRD_CALL>Ehehe-ehehe</BIRD_CALL>
		</BIRD_PROFILE>
		Note: ID is from the Guía Para la Identificación de las Aves de Paraguay by Tito Narosky and Dario Yzurieta
	3. Use the http://www.xeno-canto.org/ website to download the bird's call in mp3 format.
	4. Rename this mp3 file according to this format 'birdCallID.mp3', where ID is replaced by the bird's ID (see step 2).
	5. Copy this file into the birdCalls folder inside the assets folder in the App project.
	6. Download an image of the bird in .png format.
	7. If necessary, resize the image to reduce the storage size.
	8. Rename this image according to this format 'bird_imageID.png', where ID is replaced by the bird's ID (see step 2).
	9. Copy this file into the drawable-xxhdpi folder within the res folder in the App project.
	10. Open the 'birdDescriptions.xml', 'birdSizes.xml', and 'commonNames.xml' files in BOTH values-es and values-en folders (to update the Spanish and English values) using Android Studio.
	11. To add the bird description, insert a line in BOTH 'birdDescriptions.xml' files according to the following template:
		<string name="descriptionID"> INSERT DESCRIPTION HERE </string>
		Note: Replace ID with the bird's ID (see step 2).
	12. To add the bird's size, insert a line in BOTH 'birdSizes.xml' files according to the following template:
		<string name="sizeID">INSERT BIRD SIZE (IN CENTIMETERS) HERE</string>
		Note: Replace ID with the bird's ID (see step 2).
	13. To add the bird's common name, insert a line in BOTH 'commonNames.xml' files according to the following template:
		<string name="nameID">INSERT BIRD'S COMMON NAME HERE</string>
		Note: Replace ID with the bird's ID (see step 2).
	
	Congratulations, you just added a bird profile! Now test the application to make sure everything is working properly. :)

To test the application (using an emulator):
	1. Click the green play button to start testing the application.
	2. Choose or create a virtual device.
	3. Click 'OK' to launch the virtual device.
	
	The virtual device should be initialized (this may take a few minutes), then the application should launch automatically. :)

To test the application (using an Android phone):
	1. Connect the Android phone to your computer via a USB cord.
	2. Ensure that USB Debugging (in Settings>Developer Tools) is enabled on your Android phone.
	2. Click the green play button to start testing the application.
	3. Select your device.
	4. Click 'OK'
	
	The application should launch automatically on your Android phone. :)
	
To save your changes (to the repository):
	1. Open Git bash
	2. Use the 'cd' command to navigate to the directory selected to hold this project.
	3. Type 'git status'
	4. Then for each file that you have modified and would like to save to the repository,
		type 'git add FILE_NAME', replacing FILE_NAME accordingly.
	5. Once you have added all of the files you would like to save, type 'git status' again to ensure that your files have been added.
	6. Then, type "git commit -m 'INSERT MESSAGE SAYING WHAT YOU CHANGED HERE' "
	7. Type 'git push' to save your changes to the repository.
	
	Congratulations, you have just saved your changes to the repository. :)

To update the application on the Google Play store:
	1. Go to https://play.google.com/apps/publish/
	2. Log in with the email: avesdebenjaminaceval@gmail.com and the password: Karuguaha'eTekove
	3. Click on the Aves de Benjamín Aceval app.
	4. Click on Version Management on the left.
	5. Select Versions of the Application.
	6. Click Manage Production Version.
	7. Click Create Version.
	8. In Android Studio, open the app/build.gradle file.
	9. Increment the versionCode and change the versionName to reflect your update.
	10. In Android Studio, generate a signed apk by going to Build>Generate Signed APK...
		Select the Key Store Path to point to the .jks file in the project folder.
		Enter the Key Store Password: Karuguaha'eTekove
		Enter the Key Password: Karuguaha'eTekove
		Click Next, then click Finish.
	11. Click Browse Files and select the apk generated in step 10.
	12. Add a description of new features added in BOTH English and Spanish.
	13. Click Save at the bottom of the page.
	14. Click Review.
	15. Make sure all details are correct, then click Begin Launch of Production.
	
	Congratulations, the App should soon be updated! :) (it may take a few hours/days for the update to be approved and show up in the Play Store)
	


If you have any questions, please email brian.zylich@gmail.com
-----------------------------------------------------------------------------------



Instrucciones en español
-----------------------------------------------------------------------------------
Para establecer el ambiente del desarrollo Android:
	1. Descargue e instale Android Studio - https://developer.android.com/studio/index.html
	2. Elija/crea una carpeta para contener el proyecto del desarrollo de la aplicación Aves de Benjamín Aceval
	3. Descargue e instale Git - https://git-scm.com/downloads
	4. Abra Git bash
	5. Utilice el comando 'cd' para encontrar la carpeta creada en la segunda etapa.
	6. Teclee 'git clone https://bzylich@bitbucket.org/bzylich/aves-de-benjamin-aceval.git'
	7. Teclee 'git checkout desarrollar'
	8. Abra Android Studio y abra el proyecto encontrado en la carpeta indicada en la segunda etapa.
	
	Felicitaciones, el proyecto ya debería estar establecido y preparado para los cambios que usted hará! :)

Para añadir más pérfiles de aves:
	1. Abra el archivo 'bird_profile.xml' en Android Studio.
	2. Inserte esta plantilla y actualice los campos como corresponde:
		<BIRD_PROFILE>
			<ID>INSERTE EL NUMERO DE IDENTIFICACION AQUI</ID>
			<SCIENTIFIC_NAME>INSERTE EL NOMBRE SCIENTIFICO AQUI</SCIENTIFIC_NAME>
			<BIRD_CALL>INSERTE EL CANTO DEL PAJARO AQUI</BIRD_CALL>
		</BIRD_PROFILE>
		Nota: El número de identificación del pájaro es del Guía Para la Identificación de las Aves de Paraguay por Tito Narosky y Dario Yzurieta
	3. Use el sitio web http://www.xeno-canto.org/ para descargar el canto del ave en mp3.
	4. Renombre este archivo mp3 utilizando esta plantilla 'birdCallID.mp3', donde 'ID' debería ser reemplazado por el número de identificación del pájaro (ver la segunda etapa).
	5. Copie este archivo a la carpeta 'birdCalls' dentro de la carpeta 'assets' en este proyecto.
	6. Descargue una imagen del ave en el formato .png
	7. Si es necesario, cambie el tamaño de la imagen para reducir el tamaño de almacenamiento.
	8. Renombre esta imagen según esta plantilla 'bird_imageID.png', donde 'ID' debería ser reemplazado por el número de identificación del pájaro (ver la segunda etapa).
	9. Copie este archivo en la carpeta 'drawable-xxhdpi' dentro de la carpeta 'res' en este proyecto.
	10. Abra los archivos 'birdDescriptions.xml', 'birdSizes.xml', y 'commonNames.xml' dentro de AMBAS carpetas 'values-es' y 'values-en' (para actualizar el español y el inglés) utilizando Android Studio.
	11. Para añadir la descripción del ave, inserte una línea en AMBOS archivos 'birdDescriptions.xml' según esta plantilla:
		<string name="descriptionID"> INSERTE DESCRIPCION AQUI </string>
		Nota: Reemplace 'ID' con el número de identificación del pájaro (ver la segunda etapa).
	12. Para añadir el tamaño del ave, inserte una línea en AMBOS archivos 'birdSizes.xml' según esta plantilla:
		<string name="sizeID">INSERTE EL TAMANO DEL AVE (EN CENTIMETROS) AQUI</string>
		Nota: Reemplace 'ID' con el número de identificación del pájaro (ver la segunda etapa).
	13. Para añadir el nombre común del ave, inserte una línea en AMBOS archivos 'commonNames.xml' según esta plantilla:
		<string name="nameID">INSERTE EL NOMBRE COMUN DEL AVE AQUI</string>
		Nota: Reemplace 'ID' con el número de identificación del pájaro (ver la segunda etapa).
	
	Felicitaciones, usted ha añadido un pérfil de ave!  Ahora, usted debería probar la aplicación para verficar que todo esté en orden. :)

Para probar la aplicación (sin teléfono Android, utilizando un emulador):
	1. Pulse el botón verde play para empezar probando la aplicación.
	2. Elija o cree un aparato virtual.
	3. Pulse 'OK' para encender el aparato virtual.
	
	El aparato virtual ya debería estar encendido (este proceso puede tardar unos minutos). La aplicación debería empezar automáticamente. :)

Para probar la aplicación (utilizando un teléfono Android):
	1. Conecte el teléfono Android a tu computadora con un cable USB.
	2. Asegure que 'Depuración por USB' (Ajustes>Opciones de desarrollo) está habilitado en su teléfono Android.
	2. Pulse el botón verde play para empezar probando la aplicación.
	3. Elija su aparato.
	4. Pulse 'OK'
	
	La aplicación debería empezar automáticamente en su teléfono Android. :)
	
Para guardar sus cambios (al repositorio):
	1. Abra Git bash
	2. Use el comando 'cd' para encontrar la carpeta elegida para este proyecto.
	3. Teclee 'git status'
	4. Luego, para cada archivo que usted ha modificado o que usted le gustaría guardar al repositorio,
		teclee 'git add NOMBRE_DEL_ARCHIVO', reemplazando NOMBRE_DEL_ARCHIVO como corresponde.
	5. Después, teclee 'git status' otra vez para asegurar que todos sus archivos han sido añadido.
	6. A continuación, teclee "git commit -m 'INSERTE MENSAJE AQUI PARA DECIR LO QUE HA CAMBIADO' "
	7. Teclee 'git push' para guardar sus cambios al repositorio.
	
	Felicitaciones, usted ha guardado sus cambios al repositorio. :)

Para actualizar la aplicación en la tienda Google Play:
	1. Navegue al sitio web https://play.google.com/apps/publish/
	2. Inicie sesión con el correo electrónico: avesdebenjaminaceval@gmail.com y la contraseña: Karuguaha'eTekove
	3. Pulse en la aplicación Aves de Benjamín Aceval.
	4. Pulse en Gestión de versiones a la izquierda.
	5. Pulse en Versiones de la aplicación.
	6. Pulse en Administrar versión de producción.
	7. Pulse en Crear versión.
	8. Dentro de Android Studio, abra el archivo app/build.gradle
	9. Aumente el valor de versionCode por uno y cambie el valor de versionName correspondiente a su actualización.
	10. Dentro de Android Studio, genere un apk firmado por siguiendo Build>Generate Signed APK...
		Elija la ruta al Key Store para indicar el archivo .jks que se puede encontrar en la carpeta del proyecto.
		Introduzca la contraseña para el almacén de claves: Karuguaha'eTekove
		Introduzca la contraseña para el clave: Karuguaha'eTekove
		Pulse Continuar, después pulse Finalizar.
	11. Pulse Examinar Archivos y seleccione el apk generado en la etapa 10.
	12. Añada una descripción de lo nuevo que usted ha añadido en inglés y español.
	13. Pulse Guardar al final de la página.
	14. Pulse Revisar.
	15. Asegura que todos los detalles son correctos, después pulse Iniciar Lanzamiento a Producción.
	
	Felicitaciones, la aplicación debería estar actualizado muy pronto! :) (puede tardar varias horas/días hasta que la actualización esté aprobado y los cambios estén evidentes en la tienda Google Play)
	


Si tienes alguna pregunta, mandeme un correo electrónico con la dirección brian.zylich@gmail.com