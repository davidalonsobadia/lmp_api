Problemas encontrados a la hora de subir el archivo WAR del LMP-API con las librerías y referencias a WSO” (debido a que hemos creado el RemoteUMClient.class):

1.	No se puedes subir librerías en el WAR (dentro de la carpeta WEB-INF/lib) que hagan referencia a apache axis2 ni a wso2.
2.	En la carpeta WEB-INF/lib del WAR, debemos añadir la librería org.wso2.carbon.um.ws.api_4.2.2.jar
3.	Debemos tener muy presente la versión de JAVA que estamos usando para compilar, ya que puede ser que la 7 y 8 no estén aceptadas.

Cuando nos aparezca un error de OutOfMemory al subir el WAR en el AS, es un problema del propio AS. Debemos reiniciarlo y ya esta.

Update: Hemos actualizado el pom.xml para hacer que la creación del WAR sea un poc más rápida y efectiva. Concretamente:

1. Hemos cambiado el scope de las dependencias "axis" a "provided" para que aí no sean añadidos en el WAR.
2. Hemos añadido las librerias externas de wso2 necesarias para compilar de esta manera:
		<dependency>
 			<groupId>external.lib.wso2</groupId>
 			<artifactId>wso2-carbon-authenticator-stub</artifactId>
 			<version>4.2.0</version>
 			<scope>system</scope>
 			<systemPath>${basedir}/src/main/resources/lib/org.wso2.carbon.authenticator.stub_4.2.0.jar</systemPath>
 		</dependency>
	* en el path que aparece en systemPath, la librería está físcamente allí
3. Al tener scope "system", estas librerias no serán añadidas al WAR. Sin embargo, necesitamos que la librería org.wso2.carbon.um.ws.api_4.2.2.jar
   se copie en el WAR. Esto lo hemos conseguido instalando la dependencia con el comando:
   mvn install:install-file  -Dfile="C:\Users\david.alonso\Desktop\external_lib_lmpApiBackend\org.wso2.carbon.um.ws.api_4.2.2.jar" -DgroupId=external.lib.wso2 -DartifactId=wso2-carbon-um-ws-api -Dversion=4.2.2 -Dpackaging=jar
   y eliminando el <scope> y <systemPath> de ésta dependencia.
   ** Es posible que debamos repetir alguno de estos pasos cuando clonamos el proyecto desde Git antes de ejecutarlo. 