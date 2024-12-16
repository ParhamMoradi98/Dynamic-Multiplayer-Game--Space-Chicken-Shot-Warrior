package Reflection;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ToURL {
    public  Method method;
    public static boolean reflection = false;
    public ToURL(File file , String fullName) throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LoadClass groupLoader = new LoadClass();
        URL url = file.toURI().toURL();
        Class additionClass = groupLoader.load(url, fullName);
        method = additionClass.getMethod("chickenMove" , ArrayList.class);
        reflection = true;
    }
    public Method getMyMethod(){
        return method;
    }
}
