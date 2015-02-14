package ca.purplemad.wallpaper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class Wallpaper extends CordovaPlugin
{
	public static final String SET_WALLPAPER = "setwallpaper";
	URL url;
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
        	JSONObject arg_object = args.getJSONObject(0);
        	String path = arg_object.getString("imagePath");
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this.cordova.getActivity().getApplicationContext());
            if (SET_WALLPAPER.equals(action)) {
                Bitmap bm =  BitmapFactory.decodeFile(path);
                try {
                    wallpaperManager.setBitmap(bm);
                    Toast.makeText(this.cordova.getActivity().getApplicationContext(), "Wallpaper Saved!",Toast.LENGTH_LONG).show();
                    callbackContext.success();
                    return true;
                } catch (Exception e) {
                    callbackContext.error("Error Saving Wallpaper");
                    return false;
                }
	            	  
            }
		callbackContext.error("Invalid action ["+action+"]");
		return false;    
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error("MSG: "+e.getMessage());
            return false;
        } 
    }
}