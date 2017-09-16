package in.com.decipherzone.tamilcolorado;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;

import in.com.decipherzone.tamilcolorado.fragment.base.BaseFragment;
import in.com.decipherzone.utilites.Constant;

/**
 * Created by Mormukut singh R@J@W@T on 13/9/17.
 * Company Name: DecipherZoneSoftwares
 * URL: www.decipherzone.com
 */
public class ApplicationContext extends Application{
    private static ApplicationContext ourInstance = new ApplicationContext();

    File ROOT_DIR;
    File CACHE_DIR;

    ImageLoader imageLoader;

    public File getCACHE_DIR() {
        return CACHE_DIR;
    }

    public static ApplicationContext getInstance() {
        return ourInstance;
    }

    private ApplicationContext() {
    }



    @Override
    public void onCreate() {
        super.onCreate();
        ROOT_DIR = new File(Environment.getExternalStorageDirectory(), Constant.APP_NAME);
        CACHE_DIR = new File(ROOT_DIR, "Cache");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ourInstance = null;
    }

    private BaseFragment liveFragment = new BaseFragment();
    public void setLiveFragment(BaseFragment liveFragment) {
        this.liveFragment = liveFragment;
    }
    public BaseFragment getLiveFragment() {
        return liveFragment;
    }

    private ImageLoader getImageLoader() {
        if (imageLoader == null) {
            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .cacheOnDisc(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .showImageForEmptyUri(R.drawable.no_image)
                    .showImageOnFail(R.drawable.no_image)
                    .displayer(new FadeInBitmapDisplayer(300)).build();

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ApplicationContext.this).defaultDisplayImageOptions(defaultOptions)
                    .memoryCache(new WeakMemoryCache())
                    .discCacheSize(100 * 1024 * 1024).build();

            ImageLoader.getInstance().init(config);
            imageLoader = ImageLoader.getInstance();
        }
        return imageLoader;
    }

}
