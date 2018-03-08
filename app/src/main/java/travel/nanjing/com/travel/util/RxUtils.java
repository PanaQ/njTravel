package travel.nanjing.com.travel.util;

import android.util.Log;

import com.handarui.baselib.exception.CommonException;
import com.zhexinit.ov.common.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Func0;

/**
 * Created by wang on 2018/3/5.
 */

public class RxUtils {
    public static <T> Single<T> wrapRestCall(final Observable<ResponseBean<T>> call) {
        return call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<ResponseBean<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(ResponseBean<T> tResponseBean) throws Exception {
                        if (tResponseBean.getCode() == 0) {
                            return Observable.just(tResponseBean.getResult());
                        } else {
                            return Observable.error(new CommonException(tResponseBean.getCode(), tResponseBean.getMessage()));
                        }
                    }
                }, new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                        Log.e("API ERROR", throwable.toString());
                        return Observable.error(throwable);
                    }
                }, new Func0<ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> call() {
                        return Observable.empty();
                    }
                }).singleOrError();
    }

}
