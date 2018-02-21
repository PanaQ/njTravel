package travel.nanjing.com.travel.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import travel.nanjing.com.travel.MyApplication;

/**
 * Created by zx on 2016/12/30 0030.
 */
public class BitmapUtils {

    private static final String TAG = "BitmapUtils";

    public static Bitmap compressBitmapSize(int size, Bitmap mbitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        mbitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        while (byteArrayOutputStream.size() / 1024 > size) {
            byteArrayOutputStream.reset();
            mbitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream);
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);

        return bitmap;
    }

    public static File saveFile(Context context, Bitmap bm, String fileName) {
        String path = getSavePhotoPath(context, fileName);
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("Aaaaa", bm + "");
        Log.d("Aaaaa", bos + "");
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        try {
            if (bos != null) {
                bos.flush();
                bos.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
            return myCaptureFile;
        }
        return myCaptureFile;
    }

    public static Bitmap compressBitmapSize(Bitmap bitmap) {
//将图片压缩成200k以内
//声明一个输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//第一次压缩
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int position = 100;
        while (baos.toByteArray().length / 1024 > 3 * 1024) {
            baos.reset();
            position -= 10;
            if (position <= 0) {
                position = 10;
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, position, baos);

        }
        return BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
    }

    /**
     * 按比例缩放
     *
     * @param path
     * @param newH
     * @param newW
     * @return
     */
    public static Bitmap scaleBitmap(Context context, int path, int newH, int newW) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(context.getResources(), path);

        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        int size = 1;
        //计算缩放比例
        int scaleW = outWidth / newW;
        int scaleH = outHeight / newH;
        //取缩放比例大的
        size = scaleH > scaleW ? scaleH : scaleW;
//
        //设置缩放比例
        options.inSampleSize = size;
        //解析完整的
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), path);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, newW, newH,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
        //返回收缩,后压缩的图片
//        return compressBitmapSize(1500, bitmap);
    }


    public static Bitmap getBitMapFromFile(File takeImageFile) {

        takeImageFile = BitmapUtils.compress(MyApplication.instance, takeImageFile.getPath());

        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置为true,表示解析Bitmap对象，该对象不占内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(String.valueOf(takeImageFile), options);
        //设置缩放比例
        options.inSampleSize = 1;
        //设置为false,解析Bitmap对象加入到内存中
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(takeImageFile), options);

        return bitmap;
    }

    public static File compress(Context context, String oldPath) {
        String newPath = getSavePhotoPath(context, oldPath);
        File newFile = new File(newPath);
        //文件读取
        readFileByBytes(oldPath, newPath);

        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(newPath, options1);
        float picHeight = options1.outHeight;
        float picWidth = options1.outWidth;
        Log.i(TAG, "picHeight :" + picHeight + ";;picWidth :" + picWidth);
        Log.i(TAG, "newFile length :" + newFile.length());
        if (newFile.length() > 2 * 1024 * 1024) {
            //压缩图片  newPath
            Bitmap bitmap = compress(context, newPath, 2 * 1024 * 1024 * 4);
//            Bitmap bitmap = compressBitmapSize(context, newPath, 3* 1024 * 1024 );
//        Bitmap bitmap = compressImage(context,newPath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int options = 90;//压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            Log.e(TAG, "压缩后的大小为 = " + Arrays.toString(baos.toByteArray()));
            Log.e(TAG, "压缩后的大小为 = " + baos.size());

            try {
                FileOutputStream fos = new FileOutputStream(newFile);
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return newFile;
    }

    /**
     * @param imagePath 图片路径
     * @param reqSize   目标大小（单位字节）
     * @return 压缩到目标大小的bitmap(null, 表示压缩失败)
     */
    public static Bitmap compress(@NonNull Context context, @NonNull String imagePath, int reqSize) {
        Bitmap currBitmap = null;
        if (!new File(imagePath).exists()) {
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            final int beginInSampleSize = 4;//2的2次方,采用二分法以4为中间点,循环增大或减小inSampleSize压缩图片
            options.inSampleSize = beginInSampleSize;

            for (; ; ) {
                try {
                    currBitmap = BitmapFactory.decodeFile(imagePath, options);
                    int currSize = getSizeInBytes(currBitmap);
                    if (currSize > reqSize) {
                        currBitmap.recycle();
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                options.inSampleSize *= 2;//必须是2的n次方，设置成其他的值不会对压缩的结果产生任何变化
            }

            if (options.inSampleSize <= beginInSampleSize) {//按beginInSampleSize压缩的时候就已经小于reqSize,需要继续减小inSampleSize找一个最接近reqSize
                Bitmap preCurrBitmap = currBitmap;
                final int currInSampleSize = options.inSampleSize;
                for (int i = currInSampleSize / 2; i >= 1; i /= 2) {
                    options.inSampleSize = i;
                    try {
                        currBitmap = BitmapFactory.decodeFile(imagePath, options);
                        int currSize = getSizeInBytes(currBitmap);
                        if (currSize > reqSize) {
                            currBitmap.recycle();
                            currBitmap = preCurrBitmap;
                            break;
                        } else {
                            preCurrBitmap.recycle();
                            if (currSize < reqSize) {
                                preCurrBitmap = currBitmap;
                            } else {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return currBitmap;
    }


    public static String getFileName(String picPath) {
        return picPath.substring(picPath.lastIndexOf("/") + 1);
    }

    public static String getSavePhotoPath(Context context, String picturePath) {
        // 压缩图片保存的目录路径 /data/data/com.example.fileoperation/files
        String dirPath = context.getFilesDir().getAbsolutePath();
        // 压缩后图片保存的文件名
        String fileName = System.currentTimeMillis() + getFileName(picturePath);

        File dir = new File(dirPath);
        File file = new File(dir, fileName);//将要保存图片的路径，android推荐这种写法，将目录名和文件名分开，不然容易报错。
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getAbsolutePath();
    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String oldPath, String newPath) {
        File file = new File(oldPath);
        InputStream in = null;
        OutputStream out = null;
        try {
            // 一次读多个字节
            byte[] tempbytes = new byte[1024];
            in = new FileInputStream(oldPath);
            out = new FileOutputStream(newPath);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((in.read(tempbytes)) != -1) {
                out.write(tempbytes);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    out.flush();
                    out.close();
                    File newFile = new File(newPath);
                } catch (IOException e1) {
                }
            }
        }
    }

    public static int getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException npe) {
                // Swallow exception and try fallbacks.
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }

        // Estimate for earlier platforms.
        return bitmap.getWidth() * bitmap.getRowBytes();
    }
}
