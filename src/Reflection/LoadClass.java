package Reflection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LoadClass extends ClassLoader{
    public LoadClass() {

    }
    public Class load(URL url, String classFullName){
        Class result=null;
        byte[] classData;
        classData=getClassData(url,classFullName);
        result= defineClass(classFullName,classData,0,classData.length);
        return result;
    }

    private byte[] getClassData(URL url, String classFullName) {
        ByteArrayOutputStream buffer= new ByteArrayOutputStream();

        try {
            URLConnection connection=url.openConnection();
            InputStream inputStream=connection.getInputStream();
            int data=inputStream.read();
            while (data !=-1){

                buffer.write(data);
                data=inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toByteArray();

    }
}
