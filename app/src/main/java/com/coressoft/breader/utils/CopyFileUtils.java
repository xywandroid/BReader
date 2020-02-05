package com.coressoft.breader.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CopyFileUtils {
	
	public  static File copyAssetsFile(Context context, String filename, String destPath){
		try {
			InputStream is = context.getAssets().open(filename);
			File file = new File(destPath);
			
			FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len = is.read(buffer))!=-1){
				fos.write(buffer, 0, len);
			}
			is.close();
			fos.close();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	// 将指定文件写入SD卡,说明一下,应用程序的数据库是存放到/data/data/包名/databases 下面
	public static String toSDWriteFile(Context context, String fileName)  {
		// 获取assets下的数据库文件流
		try {
			InputStream is = context.getAssets().open(fileName);

			// 获取应用包名
			String sPackage = context.getPackageName();

			File mSaveFile = new File("/data/data/" + sPackage + "/databases/");

			if (!mSaveFile.exists()) {
				mSaveFile.mkdirs();
			}
			String local_file = mSaveFile.getAbsolutePath() + "/" + fileName;

			mSaveFile = new File(local_file);

			if (mSaveFile.exists()) {
				mSaveFile.delete();
			}
			mSaveFile.createNewFile();

			FileOutputStream fos = new FileOutputStream(mSaveFile, true);

			byte[] buffer = new byte[400000];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			mSaveFile = null;
			fos.close();
			is.close();
			return local_file;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}



	}
}
