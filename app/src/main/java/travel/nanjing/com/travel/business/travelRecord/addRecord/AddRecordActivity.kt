package travel.nanjing.com.travel.business.travelRecord.addRecord

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import com.handarui.iqfun.business.base.BaseVMActivity
import com.tbruyelle.rxpermissions.RxPermissions
import me.nereo.multi_image_selector.MultiImageSelector
import me.nereo.multi_image_selector.MultiImageSelectorActivity
import rx.functions.Action1
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.databinding.ActivityAddRecordBinding
import travel.nanjing.com.travel.util.BitmapUtils
import travel.nanjing.com.travel.util.ProviderUtil
import travel.nanjing.com.travel.util.Utils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddRecordActivity : BaseVMActivity<AddRecordActivity, AddRecordViewModel>() {

    val PICTURE_IMAGE = 1
    val Take_Photo = 2
    private val TAG = "AddRecordActivity"
    internal var takeImageFile: File? = null

    override fun initViewModel() {
        viewModel = AddRecordViewModel(this)
    }

    lateinit var binding: ActivityAddRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityAddRecordBinding>(this, R.layout.activity_add_record)
        binding.viewModel = this.viewModel
        binding.imageView2.setOnClickListener({ startPicture() })
        binding.imageView3.setOnClickListener({ startActionCamera() })
    }

    /**
     * 调用相机拍照
     */
    fun startActionCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            if (Utils.existSDCard())
                takeImageFile = File(Environment.getExternalStorageDirectory(), "/DCIM/camera/")
            else
                takeImageFile = Environment.getDataDirectory()
            takeImageFile = createFile(takeImageFile!!, "IMG_", ".jpg")
            if (takeImageFile != null) {
                // 默认情况下，即不需要指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                // 照相机有自己默认的存储路径，拍摄的照片将返回一个缩略图。如果想访问原始图片，
                // 可以通过data extra能够得到原始图片位置。即，如果指定了目标uri，data就没有数据，
                // 如果没有指定uri，则data就返回有数据！
                val uri: Uri
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    uri = Uri.fromFile(takeImageFile)
                } else {
                    /*
                      7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                      并且这样可以解决MIUI系统上拍照返回size为0的情况
                     */
                    uri = FileProvider.getUriForFile(this, ProviderUtil.getFileProviderName(this), takeImageFile)
                    //加入uri权限 要不三星手机不能拍照
                    val resInfoList = packageManager.queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY)
                    for (resolveInfo in resInfoList) {
                        val packageName = resolveInfo.activityInfo.packageName
                        grantUriPermission(packageName, uri, Intent
                                .FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                }

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            }
        }
        startActivityForResult(takePictureIntent, Take_Photo)
    }

    /**
     * 选择相册
     */
    fun showImageSelector() {
        MultiImageSelector.create(this)
                .showCamera(false) // 是否显示相机. 默认为显示
                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
                .start(this, PICTURE_IMAGE)
    }


    fun startPicture() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(Action1<Boolean> { aBoolean ->
                    if (aBoolean!!) {
                        showImageSelector()
                    } else {

                    }
                })
    }

    /**
     * 根据系统时间、前缀、后缀产生一个文件
     */
    fun createFile(folder: File, prefix: String, suffix: String): File {
        if (!folder.exists() || !folder.isDirectory) folder.mkdirs()
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
        val filename = prefix + dateFormat.format(Date(System.currentTimeMillis())) + suffix
        return File(folder, filename)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICTURE_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取选取相册图片的地址
                val path = data?.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)
                Log.d(TAG, "picture  path =" + path!![0])
                val newPath = BitmapUtils.compress(this, path[0])
//                myAvaIv.setImageURI(Uri.parse("file://" + newPath.absolutePath))
            }
        }
        if (requestCode == Take_Photo) {
            if (resultCode == RESULT_OK) {
                val newPath = BitmapUtils.compress(this, takeImageFile?.absolutePath)
                Log.d(TAG, "picture  path =" + newPath)
//                myAvaIv.setImageURI(Uri.parse("file://" + newPath.absolutePath))
            }
        }
    }
}
